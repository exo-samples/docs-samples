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
 * Sep 29, 2015  
 */
public class User {
  
  public String id;
  public String href;
  public String identity;
  public String username;
  public String password;
  public String firstname;
  public String lastname;
  public String fullname;
  public String gender;
  public String position;
  public String email;
  public String avatar;
  public Phone[] phones;
  
  public class Phone {
    public String phoneType;
    public String phoneNumber;
    public String getPhoneType() {
      return phoneType;
    }
    public void setPhoneType(String phoneType) {
      this.phoneType = phoneType;
    }
    public String getPhoneNumber() {
      return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
    }
  }
  
  public Experience[] experiences;
  
  public class Experience {
    public String company;
    public String description;
    public String position;
    public String skills;
    public Boolean isCurrent;
    public String startDate;
    public String endDate;
    public String getCompany() {
      return company;
    }
    public void setCompany(String company) {
      this.company = company;
    }
    public String getDescription() {
      return description;
    }
    public void setDescription(String description) {
      this.description = description;
    }
    public String getPosition() {
      return position;
    }
    public void setPosition(String position) {
      this.position = position;
    }
    public String getSkills() {
      return skills;
    }
    public void setSkills(String skills) {
      this.skills = skills;
    }
    public Boolean getIsCurrent() {
      return isCurrent;
    }
    public void setIsCurrent(Boolean isCurrent) {
      this.isCurrent = isCurrent;
    }
    public String getStartDate() {
      return startDate;
    }
    public void setStartDate(String startDate) {
      this.startDate = startDate;
    }
    public String getEndDate() {
      return endDate;
    }
    public void setEndDate(String endDate) {
      this.endDate = endDate;
    }
  }

  public IMS[] ims;
  
  public class IMS {
    public String imType;
    public String imId;
    public String getImType() {
      return imType;
    }
    public void setImType(String imType) {
      this.imType = imType;
    }
    public String getImId() {
      return imId;
    }
    public void setImId(String imId) {
      this.imId = imId;
    }
  }
  
  public URL[] urls;
  
  public class URL {
    public String url;
    public String getUrl() {
      return url;
    }
    public void setUrl(String url) {
      this.url = url;
    }
  }
  
  public Boolean deleted;
  
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
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getFirstname() {
    return firstname;
  }
  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }
  public String getLastname() {
    return lastname;
  }
  public void setLastname(String lastname) {
    this.lastname = lastname;
  }
  public String getFullname() {
    return fullname;
  }
  public void setFullname(String fullname) {
    this.fullname = fullname;
  }
  public String getGender() {
    return gender;
  }
  public void setGender(String gender) {
    this.gender = gender;
  }
  public String getPosition() {
    return position;
  }
  public void setPosition(String position) {
    this.position = position;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getAvatar() {
    return avatar;
  }
  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }
  public Phone[] getPhones() {
    return phones;
  }
  public void setPhones(Phone[] phones) {
    this.phones = phones;
  }
  public Experience[] getExperiences() {
    return experiences;
  }
  public void setExperiences(Experience[] experiences) {
    this.experiences = experiences;
  }
  public IMS[] getIms() {
    return ims;
  }
  public void setIms(IMS[] ims) {
    this.ims = ims;
  }
  public URL[] getUrls() {
    return urls;
  }
  public void setUrls(URL[] urls) {
    this.urls = urls;
  }
  public Boolean getDeleted() {
    return deleted;
  }
  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }
}
