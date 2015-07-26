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

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jul 21, 2015  
 */
public class ServiceInfo {
  
  public static final String REST_URL = "/rest/private/v1/calendar";
  
  // Calendar
  public static String getCalendarsUri() {
    return REST_URL + "/calendars";
  }
  
  public static String getCalendarUri(String id) {
    return REST_URL + "/calendars/" + id;
  }
  
  // Event
  public static String getEventsUri(String calendar_id) {
    return REST_URL + "/calendars/" + calendar_id + "/events";
  }
  
  public static String getEventUri(String event_id) {
    return REST_URL + "/events/" + event_id;
  }
  
  // Task
  public static String getTasksUri(String calendar_id) {
    return REST_URL + "/calendars/" + calendar_id + "/tasks";
  }
  
  public static String getTaskUri(String task_id) {
    return REST_URL + "/tasks/" + task_id;
  }
  
  // Occurrences
  public static String getOccurrencesUri(String event_id) {
    return REST_URL + "/events/" + event_id + "/occurrences";
  }
  
  // Attachment (now it is for event only)
  public static String getAttachmentsUri(String event_id) {
    return REST_URL + "/events/" + event_id + "/attachments";
  }
  
  public static String getAttachmentUri(String attachment_id) {
    return REST_URL + "/attachments/" + attachment_id;
  }
  
  // Invitation
  public static String getInvitationsUri(String event_id) {
    return REST_URL + "/events/" + event_id + "/invitations";
  }
  
  public static String getInvitationUri(String invitation_id) {
    return REST_URL + "/invitations/" + invitation_id;
  }
}
