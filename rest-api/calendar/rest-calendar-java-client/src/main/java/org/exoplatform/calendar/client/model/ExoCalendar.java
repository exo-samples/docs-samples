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
package org.exoplatform.calendar.client.model;


/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jul 21, 2015  
 */
public class ExoCalendar {
  
  String editPermission;
  String viewPermission;
  String privateURL;
  String publicURL;
  String icsURL;
  String color;
  String name;
  String type;
  String owner;
  String timeZone;
  String description;
  String[] groups;
  String href;
  String id;
  
  public String getEditPermission() {
    return editPermission;
  }
  public void setEditPermission(String editPermission) {
    this.editPermission = editPermission;
  }
  public String getViewPermission() {
    return viewPermission;
  }
  public void setViewPermission(String viewPermission) {
    this.viewPermission = viewPermission;
  }
  public String getPrivateURL() {
    return privateURL;
  }
  public void setPrivateURL(String privateURL) {
    this.privateURL = privateURL;
  }
  public String getPublicURL() {
    return publicURL;
  }
  public void setPublicURL(String publicURL) {
    this.publicURL = publicURL;
  }
  public String getIcsURL() {
    return icsURL;
  }
  public void setIcsURL(String icsURL) {
    this.icsURL = icsURL;
  }
  public String getColor() {
    return color;
  }
  public void setColor(String color) {
    this.color = color;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getOwner() {
    return owner;
  }
  public void setOwner(String owner) {
    this.owner = owner;
  }
  public String getTimeZone() {
    return timeZone;
  }
  public void setTimeZone(String timeZone) {
    this.timeZone = timeZone;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public String[] getGroups() {
    return groups;
  }
  public void setGroups(String[] groups) {
    this.groups = groups;
  }
  public String getHref() {
    return href;
  }
  public void setHref(String href) {
    this.href = href;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  
  
}
