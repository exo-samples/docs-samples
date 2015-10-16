/*
 * Copyright (C) 2003-2015 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.social.client.rest.connector;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URLEncoder;

import org.exoplatform.social.client.model.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by The eXo Platform SAS Author : eXoPlatform exo@exoplatform.com Sep
 * 29, 2015
 */
public class ExoSocialConnector implements ExoSocialConnectorInterface {

  public String            BASE_URL;

  public static final Gson gson = new GsonBuilder().create();

  // Constructor 1.
  public ExoSocialConnector(String base_url) {
    this.BASE_URL = base_url;
  }

  // Constructor 2.
  public ExoSocialConnector(String base_url, String username, String password) {
    this.BASE_URL = base_url;
    final String _username = username;
    final String _password = password;

    Authenticator.setDefault(new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(_username, _password.toCharArray());
      }
    });
  }

  // Constructor 3.
  public ExoSocialConnector(String base_url, Authenticator authenticator) {
    this.BASE_URL = base_url;
    Authenticator.setDefault(authenticator);
  }
  
  // User
  public UserCollection getUsers() throws Exception {
    String url = BASE_URL + ServiceInfo.getUsersUri() + "?returnSize=true";
    String json = HttpUtils.get(url);
    return gson.fromJson(json, UserCollection.class);
  }
  
  public User getUserById(String username) throws Exception {
    String url = BASE_URL + ServiceInfo.getUserUri(username);
    String json = HttpUtils.get(url);
    return gson.fromJson(json, User.class);
  }
  
  public String createUser(User user) throws Exception {
    String url = BASE_URL + ServiceInfo.getUsersUri();
    String json = gson.toJson(user);
    return HttpUtils.post(json, url);
  }
  
  public int updateUser(User user) throws Exception {
    String url = BASE_URL + ServiceInfo.getUserUri(user.getUsername());
    String json = gson.toJson(user);
    return HttpUtils.put(json, url);
  }
  
  public int deleteUser(String username) throws Exception {
    String url = BASE_URL + ServiceInfo.getUserUri(username);
    return HttpUtils.delete(url);
  }
  
  public UserCollection getUserConnections(String username) throws Exception {
    String url = BASE_URL + ServiceInfo.getUserConnectionsUri(username) + "?returnSize=true";
    String json = HttpUtils.get(url);
    return gson.fromJson(json, UserCollection.class);
  }
  
  public SpaceCollection getUserSpaces(String username) throws Exception {
    String url = BASE_URL + ServiceInfo.getUserSpacesUri(username) + "?returnSize=true";
    String json = HttpUtils.get(url);
    return gson.fromJson(json, SpaceCollection.class);
  }
  
  public ActivityCollection getUserActivities(String username) throws Exception {
    String url = BASE_URL + ServiceInfo.getUserSpacesUri(username) + "?returnSize=true";
    String json = HttpUtils.get(url);
    return gson.fromJson(json, ActivityCollection.class);
  }
  
  public ActivityCollection getUserActivities(String username, int offset, String after, String before) throws Exception {
    String url = BASE_URL + ServiceInfo.getUserActivitiesUri(username) + "?returnSize=true&offset=" + offset
        + "&after=" + URLEncoder.encode(after, "UTF-8") + "&before=" + URLEncoder.encode(before, "UTF-8");
    String json = HttpUtils.get(url);
    return gson.fromJson(json, ActivityCollection.class);
  }
  
  public String postSimpleActivity(String username, String message) throws Exception {
    Activity activity = new Activity();
    activity.setTitle(message);
    String url = BASE_URL + ServiceInfo.getUserActivitiesUri(username);
    return (HttpUtils.post(gson.toJson(activity), url));
  }
  
  //UserRelationship
  public UserRelationshipCollection getUserRelationships() throws Exception {
    String url = BASE_URL + ServiceInfo.getUserRelationshipsUri() + "?returnSize=true";
    String json = HttpUtils.get(url);
    return gson.fromJson(json, UserRelationshipCollection.class);
  }
  
  public UserRelationship getUserRelationshipById(String id) throws Exception {
    String url = BASE_URL + ServiceInfo.getUserRelationshipUri(id);
    String json = HttpUtils.get(url);
    return gson.fromJson(json, UserRelationship.class);
  }
  
  public UserRelationship sendConnectionRequest(String sender, String receiver) throws Exception {
    String url = BASE_URL + ServiceInfo.getUserRelationshipsUri();
    UserRelationship relationship = new UserRelationship();
    relationship.setSender(sender);
    relationship.setReceiver(receiver);
    relationship.setStatus("pending");
    String json = HttpUtils.post(gson.toJson(relationship), url);
    return gson.fromJson(json, UserRelationship.class);
  }
  
  public int acceptConnectionRequest(String id) throws Exception {
    String url = BASE_URL + ServiceInfo.getUserRelationshipUri(id);
    UserRelationship relationship = new UserRelationship();
    relationship.setStatus("confirmed");
    return HttpUtils.put(gson.toJson(relationship), url);
  }
  
  public UserRelationship createUserRelationship(UserRelationship connection) throws Exception {
    String url = BASE_URL + ServiceInfo.getUserRelationshipsUri();
    String json = HttpUtils.post(gson.toJson(connection), url);
    return gson.fromJson(json, UserRelationship.class);
  }
  
  public int deleteConnection(String id) throws Exception {
    String url = BASE_URL + ServiceInfo.getUserRelationshipUri(id);
    return HttpUtils.delete(url);
  }
  
  //Space
  public Space createSpace(Space space) throws Exception {
    String url = BASE_URL + ServiceInfo.getSpacesUri();
    String json = HttpUtils.post(gson.toJson(space), url);
    return gson.fromJson(json, Space.class);
  }
  
  //SpaceMembership
  public String createSpaceMembership(SpaceMembership membership) throws Exception {
    String url = BASE_URL + ServiceInfo.getSpaceMembershipsUri();
    return HttpUtils.post(gson.toJson(membership), url);
  }
  
  //Activity
  public Activity createUserActivity(String username, Activity activity) throws Exception {
    String url = BASE_URL + ServiceInfo.getUserActivitiesUri(username);
    String json = HttpUtils.post(gson.toJson(activity), url);
    return gson.fromJson(json, Activity.class);
  }
  
  public String likeActivity(String activity_id) throws Exception {
    String url = BASE_URL + ServiceInfo.getLikesUri(activity_id);
    return HttpUtils.post(url);
  }
  
  //Comment
  public String createComment(String activity_id, Comment comment) throws Exception {
    String url = BASE_URL + ServiceInfo.getCommentsUri(activity_id);
    return HttpUtils.post(gson.toJson(comment), url);
  }

}
