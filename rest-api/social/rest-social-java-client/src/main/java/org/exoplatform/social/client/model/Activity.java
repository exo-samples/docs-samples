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
 * Oct 1, 2015  
 */
public class Activity {
  
  public String id;
  public String title;
  public String body;
  public String link;
  public String type;
  public String href;
  public String identity;
  
  public Owner owner;  
  public class Owner {
    public String id;
    public String href;
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
  }
  
  public Mention[] mentions;
  public class Mention {
    public String id;
    public String href;
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
  }
  
  public String comments;
  public String likes;
  public String createDate;
  public String updateDate;
  
  public ActivityStream activityStream;
  public class ActivityStream {
    public String type;
    public String id;
    public String getType() {
      return type;
    }
    public void setType(String type) {
      this.type = type;
    }
    public String getId() {
      return id;
    }
    public void setId(String id) {
      this.id = id;
    }
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getBody() {
    return body;
  }
  public void setBody(String body) {
    this.body = body;
  }
  public String getLink() {
    return link;
  }
  public void setLink(String link) {
    this.link = link;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
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
  public Owner getOwner() {
    return owner;
  }
  public void setOwner(Owner owner) {
    this.owner = owner;
  }
  public Mention[] getMentions() {
    return mentions;
  }
  public void setMentions(Mention[] mentions) {
    this.mentions = mentions;
  }
  public String getComments() {
    return comments;
  }
  public void setComments(String comments) {
    this.comments = comments;
  }
  public String getLikes() {
    return likes;
  }
  public void setLikes(String likes) {
    this.likes = likes;
  }
  public String getCreateDate() {
    return createDate;
  }
  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }
  public String getUpdateDate() {
    return updateDate;
  }
  public void setUpdateDate(String updateDate) {
    this.updateDate = updateDate;
  }
  public ActivityStream getActivityStream() {
    return activityStream;
  }
  public void setActivityStream(ActivityStream activityStream) {
    this.activityStream = activityStream;
  }
  
}
