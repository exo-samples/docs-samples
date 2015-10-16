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

import org.exoplatform.social.client.model.*;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Sep 29, 2015  
 */
public interface ExoSocialConnectorInterface {
  
  //User
  public UserCollection getUsers() throws Exception;
  public User getUserById(String username) throws Exception;
  public String createUser(User user) throws Exception;
  public int updateUser(User user) throws Exception;
  public int deleteUser(String username) throws Exception;
  public UserCollection getUserConnections(String username) throws Exception;
  public SpaceCollection getUserSpaces(String username) throws Exception;
  public ActivityCollection getUserActivities(String username) throws Exception;
  public ActivityCollection getUserActivities(String username, int offset, String after, String before) throws Exception;
  public String postSimpleActivity(String username, String message) throws Exception;
  
  //UserRelationship
  public UserRelationshipCollection getUserRelationships() throws Exception;
  public UserRelationship getUserRelationshipById(String id) throws Exception;
  public UserRelationship sendConnectionRequest(String sender, String receiver) throws Exception;
  public int acceptConnectionRequest(String id) throws Exception;
  public UserRelationship createUserRelationship(UserRelationship connection) throws Exception;
  public int deleteConnection(String id) throws Exception;
  
  //Space
  public Space createSpace(Space space) throws Exception;
  
  //SpaceMembership
  public String createSpaceMembership(SpaceMembership membership) throws Exception;
  
  //Activity
  public Activity createUserActivity(String username, Activity activity) throws Exception;
  public String likeActivity(String activity_id) throws Exception;
  
  //Comment
  public String createComment(String activity_id, Comment comment) throws Exception;

}
