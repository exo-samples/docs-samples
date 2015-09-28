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

import java.nio.file.Path;
import java.util.List;

import org.exoplatform.calendar.client.model.*;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jul 22, 2015  
 */
public interface ExoCalendarConnectorInterface {
  
  // Calendar : GET, GET, POST, PUT, DELETE.
  public ExoCalendarCollection getCalendars() throws Exception;  
  public ExoCalendar getCalendarById(String calendar_id) throws Exception;  
  public String createCalendar(ExoCalendar calendar) throws Exception;  
  public int updateCalendar(ExoCalendar calendar, String calendar_id) throws Exception;  
  public int deleteCalendar(String calendar_id) throws Exception;  
  
  // Event : GET, GET, POST, PUT, DELETE.
  public ExoEventCollection getEventsByCalendarId(String calendar_id) throws Exception;  
  public ExoEvent getEventById(String event_id) throws Exception;  
  public String createEvent(ExoEvent event, String calendar_id) throws Exception;  
  public int updateEvent(ExoEvent event, String event_id) throws Exception;
  public int deleteEvent(String event_id) throws Exception;
  
  // Task : GET, GET, POST, PUT, DELETE.
  public ExoTaskCollection getTasksByCalendarId(String calendar_id) throws Exception;
  public ExoTask getTaskById(String task_id) throws Exception;
  public String createTask(ExoTask task, String calendar_id) throws Exception;
  public int updateTask(ExoTask task, String task_id) throws Exception;
  public int deleteTask(String task_id) throws Exception;
  
  // Attachment (of event) : GET, GET, POST, DELETE.
  public AttachmentCollection getAttachmentsByEventId(String event_id) throws Exception;
  public Attachment getAttachmentById(String attachment_id) throws Exception;
  public String createAttachment(List<Path> paths, String event_id) throws Exception;
  public int deleteAttachment(String event_id) throws Exception;
  
  // Invitation : GET, GET, POST, PUT, DELETE.
  public InvitationCollection getInvitationsByEventId(String event_id) throws Exception;
  public Invitation getInvitationById(String invitation_id) throws Exception;
  public String createInvitation(Invitation invitation, String event_id) throws Exception;
  public int updateInvitation(Invitation invitation, String invitation_id) throws Exception;
  public int deleteInvitation(String invitation_id) throws Exception;
}