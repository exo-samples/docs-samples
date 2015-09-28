package exo.samples.assembly;

import java.io.PrintWriter;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.exoplatform.calendar.client.model.*;
import org.exoplatform.calendar.client.rest.connector.ExoCalendarConnector;

public class Executor {
  
  public static ExoCalendarConnector connector;
	
	public static void main(String[] args) throws Exception {
		
	  // Examines arguments. Set up authenticator.
	  final String USERNAME, PASSWORD, BASE_URL;
	  try {
	    USERNAME = args[0];
	    PASSWORD = args[1];
	    BASE_URL = args[2];
	  } catch (ArrayIndexOutOfBoundsException e) {
	    System.out.println("Missing arguments!!!");
	    return;
	  }
	  Authenticator.setDefault(new Authenticator() {
	    @Override
	    public PasswordAuthentication getPasswordAuthentication() {
	      return new PasswordAuthentication(USERNAME, PASSWORD.toCharArray());
	    }
	  });
	  
	  // Create the connector.
	  connector = new ExoCalendarConnector(BASE_URL);
	  
		// Execute tests.
	  calendarTest1();
	  eventTest1();
	  taskTest1();
	  attachmentTest1();
	  invitationTest1();
	}
	
	public static void calendarTest1() throws Exception {
	  String created = Long.toString(System.currentTimeMillis());
    System.out.println(created);
    
    // Create calendar.
    ExoCalendar calendar = new ExoCalendar();
    calendar.setType("0");
    calendar.setName(created);
    connector.createCalendar(calendar);
    
    // Get calendars.
    // Get back the id (deal with CAL-1154).
    String calendar_id = null;
    ExoCalendar[] calendars = connector.getCalendars().getData();
    int len = calendars.length;
    for (int i = 0; i < len; i++) {
      if (calendars[i].getName().equals(created)) {
        calendar_id = calendars[i].getId();
      }
    }
    System.out.println("Calendar created, id : " + calendar_id);
    
    // Get calendar.
    ExoCalendar new_calendar = connector.getCalendarById(calendar_id);
    System.out.println(ExoCalendarConnector.gson.toJson(new_calendar));
    
    // Update calendar.
    new_calendar.setDescription("update des");
    System.out.println("Update calendar, response code : " + connector.updateCalendar(new_calendar, calendar_id));
    
    // Delete the calendar.
    System.out.println("Delete calendar, response code : " + connector.deleteCalendar(calendar_id));
	}
	
	public static void eventTest1() throws Exception {
	  String created = Long.toString(System.currentTimeMillis());
	  System.out.println(created);
	  
	  // Create calendar.
	  ExoCalendar calendar = new ExoCalendar();
	  calendar.setType("0");
	  calendar.setName(created);
	  connector.createCalendar(calendar);
	  
	  // Get back the id (deal with CAL-1154).
	  String calendar_id = null;
	  ExoCalendar[] calendars = connector.getCalendars().getData();
	  int len = calendars.length;
	  for (int i = 0; i < len; i++) {
	    if (calendars[i].getName().equals(created)) {
	      calendar_id = calendars[i].getId();
	    }
	  }
	  System.out.println("Calendar created, id : " + calendar_id);
	  
    // Create event.
	  ExoEvent event = new ExoEvent();
	  event.setSubject(created);
	  Date from = new Date((new Date()).getTime() + TimeUnit.DAYS.toMillis(1)); //from = tomorrow
	  Date to = new Date(from.getTime() + TimeUnit.HOURS.toMillis(4)); //to = from + 4 hours
	  event.setFrom((new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")).format(from));
	  event.setTo((new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")).format(to));
	  System.out.println(ExoCalendarConnector.gson.toJson(event));
	  String href = connector.createEvent(event, calendar_id);
	  System.out.println("Event created, href : " + href);
	  
	  // Get events, get event.
	  ExoEvent[] events = connector.getEventsByCalendarId(calendar_id).getData();
	  len = 0; len = events.length; String event_id = null;
	  for (int i = 0; i < len; i++) {
	    if (events[i].getSubject().equals(created)) {
	      event_id = events[i].getId();
	    }
	  }
	  ExoEvent new_event = connector.getEventById(event_id);
	  System.out.println("Event found, its from is : " + new_event.getFrom());
	  
	  // Update the event.
	  new_event.setDescription(created);
	  System.out.println("Update event, response code : " + connector.updateEvent(new_event, event_id));
	  
	  // Delete the event.
	  System.out.println("Delete event, response code : " + connector.deleteEvent(event_id));
	  
	  // Delete the calendar.
	  System.out.println("Delete calendar, response code : " + connector.deleteCalendar(calendar_id));
	}
	
	public static void taskTest1() throws Exception {
	  String created = Long.toString(System.currentTimeMillis());
	  System.out.println(created);
	  
	  // Create calendar.
    ExoCalendar calendar = new ExoCalendar();
    calendar.setType("0");
    calendar.setName(created);
    connector.createCalendar(calendar);
    
    // Get back the id (deal with CAL-1154).
    String calendar_id = null;
    ExoCalendar[] calendars = connector.getCalendars().getData();
    int len = calendars.length;
    for (int i = 0; i < len; i++) {
      if (calendars[i].getName().equals(created)) {
        calendar_id = calendars[i].getId();
      }
    }
    System.out.println("Calendar created, id : " + calendar_id);
    
    // Create task.
    ExoTask task = new ExoTask();
    task.setName(created);
    Date from = new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1)); // from = tomorrow
    Date to = new Date(from.getTime() + TimeUnit.HOURS.toMillis(4)); // to = from + 4 hours
    task.setFrom(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(from));
    task.setTo(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(to));
    System.out.println("Task created, href : " + connector.createTask(task, calendar_id));
    
    // Get tasks, get task.
    ExoTask[] tasks = connector.getTasksByCalendarId(calendar_id).getData();
    len = 0; len = tasks.length;
    String task_id = null;
    for (int i = 0; i < len; i++) {
      if (tasks[i].getName().equals(created)) {
        task_id = tasks[i].getId();
      }
    }
    ExoTask new_task = connector.getTaskById(task_id);
    System.out.println("Task found, its from is : " + new_task.getFrom());
    
    // Update task.
    new_task.setNote("update note");
    System.out.println("Update task, response code is : " + connector.updateTask(new_task, task_id));
    
    // Delete task.
    System.out.println("Delete task, response code is : " + connector.deleteTask(task_id));
    
    // Delete the calendar.
    System.out.println("Delete calendar, response code : " + connector.deleteCalendar(calendar_id));
	}
	
	public static void attachmentTest1() throws Exception {
	  String created = Long.toString(System.currentTimeMillis());
    System.out.println(created);
    
    List<Path> paths = new ArrayList<Path>();
    PrintWriter writer = new PrintWriter(created + ".txt", "UTF-8");
    writer.println("This is a test file");
    writer.close();
    writer = new PrintWriter(created + ".xml", "UTF-8");
    writer.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
    writer.println("<root>test</root>");
    writer.close();
    paths.add(Paths.get(created + ".txt"));
    paths.add(Paths.get(created + ".xml"));
    
    // Create calendar.
    ExoCalendar calendar = new ExoCalendar();
    calendar.setType("0");
    calendar.setName(created);
    connector.createCalendar(calendar);
    
    // Get back the id (deal with CAL-1154).
    String calendar_id = null;
    ExoCalendar[] calendars = connector.getCalendars().getData();
    int len = calendars.length;
    for (int i = 0; i < len; i++) {
      if (calendars[i].getName().equals(created)) {
        calendar_id = calendars[i].getId();
      }
    }
    System.out.println("Calendar created, id : " + calendar_id);
    
    // Create event.
    ExoEvent event = new ExoEvent();
    event.setSubject(created);
    Date from = new Date((new Date()).getTime() + TimeUnit.DAYS.toMillis(1)); //from = tomorrow
    Date to = new Date(from.getTime() + TimeUnit.HOURS.toMillis(4)); //to = from + 4 hours
    event.setFrom((new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")).format(from));
    event.setTo((new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")).format(to));
    System.out.println(ExoCalendarConnector.gson.toJson(event));
    String href = connector.createEvent(event, calendar_id);
    System.out.println("Event created, href : " + href);
    
    // Get events, get event.
    ExoEvent[] events = connector.getEventsByCalendarId(calendar_id).getData();
    len = 0; len = events.length; String event_id = null;
    for (int i = 0; i < len; i++) {
      if (events[i].getSubject().equals(created)) {
        event_id = events[i].getId();
      }
    }
    ExoEvent new_event = connector.getEventById(event_id);
    System.out.println("Event found, its from is : " + new_event.getFrom());
    
    // Create attachments.
    connector.createAttachment(paths, event_id);
    
    // Get the attachments.
    AttachmentCollection attachmentCollection = connector.getAttachmentsByEventId(event_id);
    System.out.println(ExoCalendarConnector.gson.toJson(attachmentCollection));
    Attachment[] attachments = attachmentCollection.getData();
    String attachment_id = attachments[0].getId();
    Attachment attachment = connector.getAttachmentById(attachment_id);
    System.out.println(ExoCalendarConnector.gson.toJson(attachment));
    
    // Delete the attachment.
    System.out.println("delete attachment, response code : " + connector.deleteAttachment(attachment_id));
	}
	
	public static void invitationTest1() throws Exception {
	  String created = Long.toString(System.currentTimeMillis());
    System.out.println(created);
    
    List<Path> paths = new ArrayList<Path>();
    PrintWriter writer = new PrintWriter(created + ".txt", "UTF-8");
    writer.println("This is a test file");
    writer.close();
    writer = new PrintWriter(created + ".xml", "UTF-8");
    writer.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
    writer.println("<root>test</root>");
    writer.close();
    paths.add(Paths.get(created + ".txt"));
    paths.add(Paths.get(created + ".xml"));
    
    // Create calendar.
    ExoCalendar calendar = new ExoCalendar();
    calendar.setType("0");
    calendar.setName(created);
    connector.createCalendar(calendar);
    
    // Get back the id (deal with CAL-1154).
    String calendar_id = null;
    ExoCalendar[] calendars = connector.getCalendars().getData();
    int len = calendars.length;
    for (int i = 0; i < len; i++) {
      if (calendars[i].getName().equals(created)) {
        calendar_id = calendars[i].getId();
      }
    }
    System.out.println("Calendar created, id : " + calendar_id);
    
    // Create event.
    ExoEvent event = new ExoEvent();
    event.setSubject(created);
    Date from = new Date((new Date()).getTime() + TimeUnit.DAYS.toMillis(1)); //from = tomorrow
    Date to = new Date(from.getTime() + TimeUnit.HOURS.toMillis(4)); //to = from + 4 hours
    event.setFrom((new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")).format(from));
    event.setTo((new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")).format(to));
    System.out.println(ExoCalendarConnector.gson.toJson(event));
    String href = connector.createEvent(event, calendar_id);
    System.out.println("Event created, href : " + href);
    
    // Get events, get event.
    ExoEvent[] events = connector.getEventsByCalendarId(calendar_id).getData();
    len = 0; len = events.length; String event_id = null;
    for (int i = 0; i < len; i++) {
      if (events[i].getSubject().equals(created)) {
        event_id = events[i].getId();
      }
    }
    ExoEvent new_event = connector.getEventById(event_id);
    System.out.println("Event found, its from is : " + new_event.getFrom());
    
    // Create invitation.
    Invitation invitation = new Invitation();
    invitation.setParticipant("root");
    invitation.setStatus("");
    System.out.println("invitation created : href : " + connector.createInvitation(invitation, event_id));
    
    // Get invitations.
    InvitationCollection invitationCollection = connector.getInvitationsByEventId(event_id);
    System.out.println(ExoCalendarConnector.gson.toJson(invitationCollection));
    Invitation[] invitations = invitationCollection.getData();
    
    // Delete invitation of root.
    len = 0; len = invitations.length;
    for (int i = 0; i < len; i++) {
      if (invitations[i].getParticipant().equals("root")) {
        System.out.println("Invitation to root found: " 
                           + ExoCalendarConnector.gson.toJson(connector.getInvitationById(invitations[i].getId())));
        System.out.println("delete invitation, response code : " + connector.deleteInvitation(invitations[i].getId()));
      }
    }
	}
}
