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
package org.exoplatform.calendar.client.rest.connector;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.nio.file.Path;
import java.util.List;

import org.exoplatform.calendar.client.model.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jul 21, 2015  
 */
public class ExoCalendarConnector implements ExoCalendarConnectorInterface {
  
  private String BASE_URL;
  
  public static final Gson gson = new GsonBuilder().create();
  
  // Constructor 1.
  public ExoCalendarConnector(String base_url) {
    this.BASE_URL = base_url;
  }
  
  // Constructor 2.
  public ExoCalendarConnector(String base_url, String username, String password) {
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
  public ExoCalendarConnector(String base_url, Authenticator authenticator) {
    this.BASE_URL = base_url;
    Authenticator.setDefault(authenticator);
  }
  
  public ExoCalendarCollection getCalendars() throws Exception {
    String url = BASE_URL + ServiceInfo.getCalendarsUri();
    String json = HttpUtils.get(url);
    return gson.fromJson(json, ExoCalendarCollection.class);
  }
  
  public String createCalendar(ExoCalendar calendar) throws Exception {
    String url = BASE_URL + ServiceInfo.getCalendarsUri();
    String json = gson.toJson(calendar);
    return HttpUtils.post(json, url);
  }
  
  public ExoCalendar getCalendarById(String id) throws Exception {
    String url = BASE_URL + ServiceInfo.getCalendarUri(id);
    String json = HttpUtils.get(url);
    return gson.fromJson(json, ExoCalendar.class);
  }
  
  public int updateCalendar(ExoCalendar calendar, String id) throws Exception {
    String url = BASE_URL + ServiceInfo.getCalendarUri(id);
    String json = gson.toJson(calendar);
    return HttpUtils.put(json, url);
  }

  public int deleteCalendar(String id) throws Exception {
    String url = BASE_URL + ServiceInfo.getCalendarUri(id);
    return HttpUtils.delete(url);
  }
  
  public ExoEventCollection getEventsByCalendarId(String calendar_id) throws Exception {
    String url = BASE_URL + ServiceInfo.getEventsUri(calendar_id);
    String json = HttpUtils.get(url);
    return gson.fromJson(json, ExoEventCollection.class);
  }
  
  public ExoEvent getEventById(String event_id) throws Exception {
    String url = BASE_URL + ServiceInfo.getEventUri(event_id);
    String json = HttpUtils.get(url);
    return gson.fromJson(json, ExoEvent.class);
  }
  
  public String createEvent(ExoEvent event, String calendar_id) throws Exception {
    String url = BASE_URL + ServiceInfo.getEventsUri(calendar_id);
    String json = gson.toJson(event);
    return HttpUtils.post(json, url);
  }
  
  public int updateEvent(ExoEvent event, String event_id) throws Exception {
    String url = BASE_URL + ServiceInfo.getEventUri(event_id);
    String json = gson.toJson(event);
    return HttpUtils.put(json, url);
  }
  
  public int deleteEvent(String event_id) throws Exception {
    String url = BASE_URL + ServiceInfo.getEventUri(event_id);
    return HttpUtils.delete(url);
  }
  
  public ExoTaskCollection getTasksByCalendarId(String calendar_id) throws Exception {
    String url = BASE_URL + ServiceInfo.getTasksUri(calendar_id);
    String json = HttpUtils.get(url);
    return gson.fromJson(json, ExoTaskCollection.class);
  }
  
  public ExoTask getTaskById(String task_id) throws Exception {
    String url = BASE_URL + ServiceInfo.getTaskUri(task_id);
    String json = HttpUtils.get(url);
    return gson.fromJson(json, ExoTask.class);
  }
  
  public String createTask(ExoTask task, String calendar_id) throws Exception {
    String url = BASE_URL + ServiceInfo.getTasksUri(calendar_id);
    String json = gson.toJson(task);
    return HttpUtils.post(json, url);
  }
  
  public int updateTask(ExoTask task, String task_id) throws Exception {
    String url = BASE_URL + ServiceInfo.getTaskUri(task_id);
    String json = gson.toJson(task);
    return HttpUtils.put(json, url);
  }
  
  public int deleteTask(String task_id) throws Exception {
    String url = BASE_URL + ServiceInfo.getTaskUri(task_id);
    return HttpUtils.delete(url);
  }
  
  /**
   * Get attachments of an event specified by id.
   */
  public AttachmentCollection getAttachmentsByEventId(String event_id) throws Exception {
    String url = BASE_URL + ServiceInfo.getAttachmentsUri(event_id);
    String json = HttpUtils.get(url);
    return gson.fromJson(json, AttachmentCollection.class);
  }
  
  /**
   * Get an attachment by its id.
   */
  public Attachment getAttachmentById(String attachment_id) throws Exception {
    String url = BASE_URL + ServiceInfo.getAttachmentUri(attachment_id);
    String json = HttpUtils.get(url);
    return gson.fromJson(json, Attachment.class);
  }
  
  /**
   * Attach files to an event.
   */
  public String createAttachment(List<Path> paths, String event_id) throws Exception {
    String url = BASE_URL + ServiceInfo.getAttachmentsUri(event_id);
    return HttpUtils.upload(paths, url);
  }
  
  /**
   * Delete an attachment specified by its id.
   */
  public int deleteAttachment(String attachment_id) throws Exception {
    String url = BASE_URL + ServiceInfo.getAttachmentUri(attachment_id);
    return HttpUtils.delete(url);
  }
  
  /**
   * Get invitations of an event specified by id.
   */
  public InvitationCollection getInvitationsByEventId(String event_id) throws Exception {
    String url = BASE_URL + ServiceInfo.getInvitationsUri(event_id);
    String json = HttpUtils.get(url);
    return gson.fromJson(json, InvitationCollection.class);
  }
  
  /**
   * Get an invitation by its id.
   */
  public Invitation getInvitationById(String invitation_id) throws Exception {
    String url = BASE_URL + ServiceInfo.getInvitationUri(invitation_id);
    String json = HttpUtils.get(url);
    return gson.fromJson(json, Invitation.class);
  }
  
  /**
   * Create an invitation of a given event.
   */
  public String createInvitation(Invitation invitation, String event_id) throws Exception {
    String url = BASE_URL + ServiceInfo.getInvitationsUri(event_id);
    String json = gson.toJson(invitation);
    return HttpUtils.post(json, url);
  }
  
  /**
   * Update an invitation specified by its id.
   */
  public int updateInvitation(Invitation invitation, String invitation_id) throws Exception {
    String url = BASE_URL + ServiceInfo.getInvitationUri(invitation_id);
    String json = gson.toJson(invitation);
    return HttpUtils.put(json, url);
  }
  
  /**
   * Delete an invitation specified by its id.
   */
  public int deleteInvitation(String invitation_id) throws Exception {
    String url = BASE_URL + ServiceInfo.getInvitationUri(invitation_id);
    return HttpUtils.delete(url);
  }
}
