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
 * Jul 22, 2015  
 */
public class ExoEvent {
  
  public String to;
  public String[] attachments;
  public String from;
  public String[] categories;
  public String categoryId;
  public String availability;
  public Repeat repeat;
  public Reminder[] reminder;
  public String privacy;
  public String recurrenceId;
  public String[] participants;
  public String originalEvent;
  public String calendar;
  public String subject;
  public String description;
  public String location;
  public String priority;
  public String href;
  public String id;

  public String getTo() {
    return to;
  }
  public void setTo(String to) {
    this.to = to;
  }
  public String[] getAttachments() {
    return attachments;
  }
  public void setAttachments(String[] attachments) {
    this.attachments = attachments;
  }
  public String getFrom() {
    return from;
  }
  public void setFrom(String from) {
    this.from = from;
  }
  public String[] getCategories() {
    return categories;
  }
  public void setCategories(String[] categories) {
    this.categories = categories;
  }
  public String getCategoryId() {
    return categoryId;
  }
  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }
  public String getAvailability() {
    return availability;
  }
  public void setAvailability(String availability) {
    this.availability = availability;
  }
  public Repeat getRepeat() {
    return repeat;
  }
  public void setRepeat(Repeat repeat) {
    this.repeat = repeat;
  }
  public Reminder[] getReminder() {
    return reminder;
  }
  public void setReminder(Reminder[] reminder) {
    this.reminder = reminder;
  }
  public String getPrivacy() {
    return privacy;
  }
  public void setPrivacy(String privacy) {
    this.privacy = privacy;
  }
  public String getRecurrenceId() {
    return recurrenceId;
  }
  public void setRecurrenceId(String recurrenceId) {
    this.recurrenceId = recurrenceId;
  }
  public String[] getParticipants() {
    return participants;
  }
  public void setParticipants(String[] participants) {
    this.participants = participants;
  }
  public String getOriginalEvent() {
    return originalEvent;
  }
  public void setOriginalEvent(String originalEvent) {
    this.originalEvent = originalEvent;
  }
  public String getCalendar() {
    return calendar;
  }
  public void setCalendar(String calendar) {
    this.calendar = calendar;
  }
  public String getSubject() {
    return subject;
  }
  public void setSubject(String subject) {
    this.subject = subject;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public String getLocation() {
    return location;
  }
  public void setLocation(String location) {
    this.location = location;
  }
  public String getPriority() {
    return priority;
  }
  public void setPriority(String priority) {
    this.priority = priority;
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
  public class Repeat {
    public String[] exclude;
    public String repeatOn;
    public String repeateBy;
    public int every;
    public Boolean enabled;
    public End end;
    public String type;
    
    public String[] getExclude() {
      return exclude;
    }
    public void setExclude(String[] exclude) {
      this.exclude = exclude;
    }
    public String getRepeatOn() {
      return repeatOn;
    }
    public void setRepeatOn(String repeatOn) {
      this.repeatOn = repeatOn;
    }
    public String getRepeateBy() {
      return repeateBy;
    }
    public void setRepeateBy(String repeateBy) {
      this.repeateBy = repeateBy;
    }
    public int getEvery() {
      return every;
    }
    public void setEvery(int every) {
      this.every = every;
    }
    public Boolean getEnabled() {
      return enabled;
    }
    public void setEnabled(Boolean enabled) {
      this.enabled = enabled;
    }
    public End getEnd() {
      return end;
    }
    public void setEnd(End end) {
      this.end = end;
    }
    public String getType() {
      return type;
    }
    public void setType(String type) {
      this.type = type;
    }

    public class End {
      public String value;
      public String type;
      
      public String getValue() {
        return value;
      }
      public void setValue(String value) {
        this.value = value;
      }
      public String getType() {
        return type;
      }
      public void setType(String type) {
        this.type = type;
      }
    }
  }
  public class Reminder {
    public String summary;
    public int repeatInterval;
    public String eventId;
    public FromDateTime fromDateTime;
    public int alarmBefore;
    public String reminderType;
    public String emailAddress;
    public Boolean repeat;
    public String reminderOwner;
    public String description;
    public String id;
    
    public String getSummary() {
      return summary;
    }
    public void setSummary(String summary) {
      this.summary = summary;
    }
    public int getRepeatInterval() {
      return repeatInterval;
    }
    public void setRepeatInterval(int repeatInterval) {
      this.repeatInterval = repeatInterval;
    }
    public String getEventId() {
      return eventId;
    }
    public void setEventId(String eventId) {
      this.eventId = eventId;
    }
    public FromDateTime getFromDateTime() {
      return fromDateTime;
    }
    public void setFromDateTime(FromDateTime fromDateTime) {
      this.fromDateTime = fromDateTime;
    }
    public int getAlarmBefore() {
      return alarmBefore;
    }
    public void setAlarmBefore(int alarmBefore) {
      this.alarmBefore = alarmBefore;
    }
    public String getReminderType() {
      return reminderType;
    }
    public void setReminderType(String reminderType) {
      this.reminderType = reminderType;
    }
    public String getEmailAddress() {
      return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
      this.emailAddress = emailAddress;
    }
    public Boolean getRepeat() {
      return repeat;
    }
    public void setRepeat(Boolean repeat) {
      this.repeat = repeat;
    }
    public String getReminderOwner() {
      return reminderOwner;
    }
    public void setReminderOwner(String reminderOwner) {
      this.reminderOwner = reminderOwner;
    }
    public String getDescription() {
      return description;
    }
    public void setDescription(String description) {
      this.description = description;
    }
    public String getId() {
      return id;
    }
    public void setId(String id) {
      this.id = id;
    }

    public class FromDateTime {
      public int day;
      public int timeZoneOffset;
      public int date;
      public int hours;
      public int minutes;
      public int month;
      public int seconds;
      public int time;
      public int year;
      
      public int getDay() {
        return day;
      }
      public void setDay(int day) {
        this.day = day;
      }
      public int getTimeZoneOffset() {
        return timeZoneOffset;
      }
      public void setTimeZoneOffset(int timeZoneOffset) {
        this.timeZoneOffset = timeZoneOffset;
      }
      public int getDate() {
        return date;
      }
      public void setDate(int date) {
        this.date = date;
      }
      public int getHours() {
        return hours;
      }
      public void setHours(int hours) {
        this.hours = hours;
      }
      public int getMinutes() {
        return minutes;
      }
      public void setMinutes(int minutes) {
        this.minutes = minutes;
      }
      public int getMonth() {
        return month;
      }
      public void setMonth(int month) {
        this.month = month;
      }
      public int getSeconds() {
        return seconds;
      }
      public void setSeconds(int seconds) {
        this.seconds = seconds;
      }
      public int getTime() {
        return time;
      }
      public void setTime(int time) {
        this.time = time;
      }
      public int getYear() {
        return year;
      }
      public void setYear(int year) {
        this.year = year;
      }
    }
  }
}
