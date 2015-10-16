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
public class Comment {
  
  public String id;
  public String href;
  public String identity;
  public String poster;
  public String body;
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
  
  public String createDate;
  public String updateDate;
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
  public String getPoster() {
    return poster;
  }
  public void setPoster(String poster) {
    this.poster = poster;
  }
  public String getBody() {
    return body;
  }
  public void setBody(String body) {
    this.body = body;
  }
  public Mention[] getMentions() {
    return mentions;
  }
  public void setMentions(Mention[] mentions) {
    this.mentions = mentions;
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
  
  //add field title to work-around SOC-5129.
  public String title;
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }

}
