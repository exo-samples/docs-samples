package org.exoplatform.samples.birthday;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.exoplatform.commons.utils.ListAccess;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.identity.model.Identity;
import org.exoplatform.social.core.identity.model.Profile;
import org.exoplatform.social.core.manager.IdentityManager;
import org.exoplatform.social.core.manager.RelationshipManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

@RestController
@RequestMapping("connections")
@Tag(name = "/my-connections-birthday-webapp/rest/connections", description = "Manages connections birthday") // NOSONAR
public class MyConnectionsBirthdaySpringRESTService {
  // Logger for our class
  private static final Log LOG = ExoLogger.getLogger(MyConnectionsBirthdaySpringRESTService.class);

  // Service used to load user identities
  @Autowired
  private IdentityManager identityManager;

  //  Service used to manage relationships with other users
  @Autowired
  private RelationshipManager relationshipManager;

  private static final DateTimeFormatter PARSER = DateTimeFormatter
      .ofPattern("yyyy-MM-dd", Locale.getDefault());

  private static String BIRTHDAY_PROPERTY = "birthday";

  @GetMapping(produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
  @Secured("users")
  public Response getMyConnectionsBirthday() {

    // Load current authenticated user
    String authenticatedUserName = ConversationState.getCurrent().getIdentity().getUserId();

    try {
      // Load the identity of the current user
      Identity authenticatedUser = identityManager.getOrCreateUserIdentity(authenticatedUserName);
      // Load list of people connected with him
      ListAccess<Identity> connections = relationshipManager.getConnections(authenticatedUser);
      Identity[] connectionsIdentities = connections.load(0, connections.getSize());

      // Create a JSON array that will be returned with the response
      JSONArray jsonArray = new JSONArray();
      for (Identity connection : connectionsIdentities) {
        JSONObject connectionJSON = new JSONObject();
        connectionJSON.put("userName", connection.getRemoteId());
        connectionJSON.put("fullName", connection.getProfile().getFullName());
        connectionJSON.put("avatar", connection.getProfile().getAvatarUrl());
        connectionJSON.put(BIRTHDAY_PROPERTY, connection.getProfile().getProperty(BIRTHDAY_PROPERTY));
        jsonArray.put(connectionJSON);
      }

      // IF everything is OK, we return a HTTP 200 response with the Json array
      return Response.ok(jsonArray.toString()).build();
    } catch (Exception e) {
      // Something went wrong, an error is logged and a HTTP 500 error is returned
      LOG.error("Error getting user connections for {}", authenticatedUserName, e);
      return Response.serverError().build();
    }
  }

  @PostMapping
  @Secured("users")
  public Response updateBirthday(@Parameter(description = "birthday", required = true) @RequestParam("birthday") String birthday){
    String authenticatedUserName = ConversationState.getCurrent().getIdentity().getUserId();

    try {
      // Do a parsing to the received date and throw an exception if it is not valid
      LocalDate.parse(birthday, PARSER);
      // get the user identity and upda te the profile
      Identity authenticatedUser = identityManager.getOrCreateUserIdentity(authenticatedUserName);
      Profile authenticatedUserProfile = authenticatedUser.getProfile();
      // set the received birthday in the user profile
      authenticatedUserProfile.setProperty(BIRTHDAY_PROPERTY, birthday);
      identityManager.updateProfile(authenticatedUserProfile);

      return Response.ok().build();
    } catch (DateTimeParseException ex) {
      LOG.error("Could not parse date {}", birthday, ex);
      return Response.serverError().build();
    } catch (Exception e) {
      LOG.error("Could not update profile of user {}", authenticatedUserName, e);
      return Response.serverError().build();
    }
  }
}
