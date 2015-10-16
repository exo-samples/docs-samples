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
package org.exoplatform.social.client.model;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Sep 30, 2015  
 */
public class Space {
  
  public String id;
  public String href;
  public String identity;
  public String groupId;
  public Application[] applications;
  
  public class Application {
    public String id;
    public String displayName;
    public String getId() {
      return id;
    }
    public void setId(String id) {
      this.id = id;
    }
    public String getDisplayName() {
      return displayName;
    }
    public void setDisplayName(String displayName) {
      this.displayName = displayName;
    }
  }
  
  public String managers;
  public String members;
  public String displayName;
  public String description;
  public String url;
  public String avatarUrl;
  public String visibility;
  public String subscription;
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getHref() {
    return href;
  }
  public void setHref(String href) {
    this.href = href;
  }
  public String getIdentity() {
    return identity;
  }
  public void setIdentity(String identity) {
    this.identity = identity;
  }
  public String getGroupId() {
    return groupId;
  }
  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }
  public Application[] getApplications() {
    return applications;
  }
  public void setApplications(Application[] applications) {
    this.applications = applications;
  }
  public String getManagers() {
    return managers;
  }
  public void setManagers(String managers) {
    this.managers = managers;
  }
  public String getMembers() {
    return members;
  }
  public void setMembers(String members) {
    this.members = members;
  }
  public String getDisplayName() {
    return displayName;
  }
  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
  public String getAvatarUrl() {
    return avatarUrl;
  }
  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }
  public String getVisibility() {
    return visibility;
  }
  public void setVisibility(String visibility) {
    this.visibility = visibility;
  }
  public String getSubscription() {
    return subscription;
  }
  public void setSubscription(String subscription) {
    this.subscription = subscription;
  }
   
}
