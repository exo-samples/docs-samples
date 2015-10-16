package exo.samples.assembly;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.exoplatform.social.client.model.*;
import org.exoplatform.social.client.rest.connector.ExoSocialConnector;
import org.exoplatform.social.client.rest.connector.HttpUtils;
import org.omg.CORBA._PolicyStub;

public class Executor {

  public static ExoSocialConnector connector;
  public static String USERNAME, PASSWORD, BASE_URL;
  
  public static final int NUMBER_OF_USERS = 5;
  public static final String USERNAME_PREFIX = "user";
  public static final String FIRSTNAME = "Test";
  public static final String LASTNAME_PREFIX = "User";
  public static final String EMAIL_SUFFIX = "@example.com";
  public static final String DEFAULT_PASSWORD = "exo"; //default password of new user
  public static final int NUMBER_OF_SPACES = 2;
  public static final String SPACE_PREFIX = "space";

  public static void main(String[] args) throws Exception {
    
    USERNAME = "root";
    PASSWORD = "gtn";
    BASE_URL = "http://localhost:8080";
    connector = new ExoSocialConnector(BASE_URL);
    
    // Login as root
    login(USERNAME, PASSWORD);
    
    // Create 100 users
    for (int i = 0; i < NUMBER_OF_USERS; i++) {
      User user = new User();
      user.setUsername(USERNAME_PREFIX + i);
      user.setFirstname(FIRSTNAME);
      user.setLastname(LASTNAME_PREFIX + i);
      user.setEmail(USERNAME_PREFIX + i + EMAIL_SUFFIX);
      connector.createUser(user);
    }
    
    // Connect everyone together
    for (int i = 0; i < (NUMBER_OF_USERS -1); i++) {
      for (int j = (i+1); j < NUMBER_OF_USERS; j++) {
        UserRelationship connection = new UserRelationship();
        connection.setSender(USERNAME_PREFIX + i);
        connection.setReceiver(USERNAME_PREFIX + j);
        connection.setStatus("confirmed");
        connector.createUserRelationship(connection);
      }
    }
    
    // Create 10 spaces
    String[] space_ids = new String[NUMBER_OF_SPACES];
    for (int i = 0; i < NUMBER_OF_SPACES; i ++) {
      Space space = new Space();
      space.setDisplayName(SPACE_PREFIX + i);
      Space created_space = connector.createSpace(space);
      space_ids[i] = created_space.getId();
    }
    
    // Add everyone to every space
    for (int i = 0; i < NUMBER_OF_USERS; i++) {
      for (int j = 0; j < NUMBER_OF_SPACES; j ++) {
        SpaceMembership membership = new SpaceMembership();
        membership.setUser(USERNAME_PREFIX + i);
        membership.setSpace(SPACE_PREFIX + j);
        connector.createSpaceMembership(membership);
      }
    }
    
    // Login as user0 and post a message
    login(USERNAME_PREFIX + 0, DEFAULT_PASSWORD);
    Activity activity = new Activity();
    activity.setTitle("I got number 0");
    Activity created_activity = connector.createUserActivity(USERNAME_PREFIX + 0, activity);
    String activity_id = created_activity.getId();
    System.out.println(activity_id);
    
    // Login as other users and like and comment
    for (int i = 1; i < NUMBER_OF_USERS; i++) {
      login(USERNAME_PREFIX + i, DEFAULT_PASSWORD);
      connector.likeActivity(activity_id);
      Comment comment = new Comment();
      //comment.setBody("I got number " + i); //won't work due to SOC-5129
      comment.setTitle("I got number " + i); //work-around SOC-5129
      connector.createComment(activity_id, comment);
    }
  }
  
  @SuppressWarnings("restriction")
  public static void login(String username, String password) {
    final String username_ = username;
    final String password_ = password;
    //http://bugs.java.com/bugdatabase/view_bug.do?bug_id=6626700
    sun.net.www.protocol.http.AuthCacheValue.setAuthCache(new sun.net.www.protocol.http.AuthCacheImpl());
    Authenticator.setDefault(new Authenticator() {
      @Override
      public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username_, password_.toCharArray());
      }
    });
  }

  public static void userTestCRUD() throws Exception {
    final String created = Long.toString(System.currentTimeMillis());
    System.out.println("Code: " + created);
    login(USERNAME, PASSWORD); //login as root
    // print number of users
    UserCollection users = connector.getUsers();
    System.out.println("Number of users: " + users.getUsers().length);
    
    // create user
    User user = new User();
    String username = "user_" + created;
    user.setUsername(username);
    user.setFirstname("Test");
    user.setLastname("Test");
    user.setEmail(username + "@example.com");
    System.out.println(connector.gson.toJson(user));
    connector.createUser(user);
    
    // login as new user and update info
    login(username, DEFAULT_PASSWORD);
    user.setFirstname("TestA");
    user.setLastname("TestA");
    user.setEmail(username + "@exampleA.com");
    System.out.println(connector.gson.toJson(user));
    System.out.println(connector.updateUser(user));
    
    // get user info
    User edited_user = connector.getUserById(username);
    System.out.println(connector.gson.toJson(edited_user));
    
    // login root and delete the user
    login(USERNAME, PASSWORD);
    connector.deleteUser(username);
    users = connector.getUsers();
    System.out.println("Number of users: " + users.getUsers().length);
  }
  
  public static void userTestPassword() throws Exception {
    //set password for new user
    String created = Long.toString(System.currentTimeMillis());
    System.out.println("Code: " + created);
    String username = "user_" + created;
    String password = "gtngtn123";
    login(USERNAME, PASSWORD);
    User user = new User();
    user.setUsername(username);
    user.setPassword(password);
    user.setFirstname("Test");
    user.setLastname("Test");
    user.setEmail(username + "@example.com");
    connector.createUser(user);
    
    //new user logins and updates password
    login(username, password);
    //System.out.println(HttpUtils.get("http://localhost:8080/rest/private/echo/whoami"));
    String newpass = "exoexo123";
    user.setPassword(newpass);
    user.setEmail(username + "@exampleA.com"); //work around SOC-5119
    System.out.println(connector.gson.toJson(user));
    connector.updateUser(user);
    login(username, newpass);
    System.out.println(connector.gson.toJson(connector.getUserById(username)));
  }
  
  public static void userTestActivity() throws Exception {
    // get activities of today - in local timezone
    login(USERNAME, PASSWORD);
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.clear(Calendar.MINUTE);
    cal.clear(Calendar.SECOND);
    cal.clear(Calendar.MILLISECOND);
    Date start_of_day = cal.getTime();
    Date end_of_day = new Date(cal.getTimeInMillis() + 1000*60*60*24 -1);
    
    // format dates in "yyyy-MM-dd HH:mm:ss" in UTC timezone
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    df.setTimeZone(TimeZone.getTimeZone("UTC"));
    String after = df.format(start_of_day);
    String before = df.format(end_of_day);
    System.out.println(after);
    System.out.println(before);
    
    // query in loop
    // due to SOC-5125, we can't rely on returnSize to get total number of items.
    // work-around by comparing array length and limit.
    int offset = 0;
    List<Activity> activities = new ArrayList<Activity>();
    ActivityCollection activityCollection = connector.getUserActivities(USERNAME, offset, after, before);
    int size = activityCollection.getSize();
    int limit = activityCollection.getLimit();
    if (activityCollection.activities != null) {
      activities.addAll(Arrays.asList(activityCollection.activities));
      //while (activities.size() < size) {
      while ((activityCollection.activities != null) && !(activityCollection.activities.length < limit)) {
        offset = offset + limit;
        activityCollection = connector.getUserActivities(USERNAME, offset, after, before);
        activities.addAll(Arrays.asList(activityCollection.activities));
      }
    }
    System.out.println(activities.size());
  }
  
  public static void userSimplePost() throws Exception {
    login(USERNAME, PASSWORD);
    System.out.println(connector.postSimpleActivity(USERNAME, "Hey I posted this from a Java app."));
  }

  public static void userRelationshipTest() throws Exception {
    //create user1, user2
    login(USERNAME, PASSWORD);
    String created = Long.toString(System.currentTimeMillis());
    String username1 = "u1_" + created;
    String username2 = "u2_" + created;
    User user1 = new User();
    user1.setUsername(username1);
    user1.setFirstname("Test");
    user1.setLastname("Test");
    user1.setEmail(username1 + "@example.com");
    User user2 = new User();
    user2.setUsername(username2);
    user2.setFirstname("Test");
    user2.setLastname("Test");
    user2.setEmail(username2 + "@example.com");
    connector.createUser(user1);
    connector.createUser(user2);
    
    //user1 sends connection request
    login(username1, DEFAULT_PASSWORD);
    UserRelationship relationship = connector.sendConnectionRequest(username1, username2);
    
    //user2 accepts
    login(username2, DEFAULT_PASSWORD);
    connector.acceptConnectionRequest(relationship.getId());
    
    //get connections of user2
    UserRelationshipCollection relationshipCollection = connector.getUserRelationships();
    System.out.println(connector.gson.toJson(relationshipCollection));
  }
}
