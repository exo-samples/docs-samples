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
 * Oct 6, 2015  
 */
public class Identity {
  
  public String id;
  public String href;
  public String providerId;
  public GlobalId globalId;
  
  public class GlobalId {
    public String localId;
    public String domain;
    public String getLocalId() {
      return localId;
    }
    public void setLocalId(String localId) {
      this.localId = localId;
    }
    public String getDomain() {
      return domain;
    }
    public void setDomain(String domain) {
      this.domain = domain;
    }
  }
  
  public Boolean deleted;
  public User profile;
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
  public String getProviderId() {
    return providerId;
  }
  public void setProviderId(String providerId) {
    this.providerId = providerId;
  }
  public GlobalId getGlobalId() {
    return globalId;
  }
  public void setGlobalId(GlobalId globalId) {
    this.globalId = globalId;
  }
  public Boolean getDeleted() {
    return deleted;
  }
  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }
  public User getProfile() {
    return profile;
  }
  public void setProfile(User profile) {
    this.profile = profile;
  }

}
