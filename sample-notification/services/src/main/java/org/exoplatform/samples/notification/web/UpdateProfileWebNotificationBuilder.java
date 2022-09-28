package org.exoplatform.samples.notification.web;

import org.exoplatform.commons.api.notification.NotificationContext;
import org.exoplatform.commons.api.notification.NotificationMessageUtils;
import org.exoplatform.commons.api.notification.channel.template.AbstractTemplateBuilder;
import org.exoplatform.commons.api.notification.model.ChannelKey;
import org.exoplatform.commons.api.notification.model.MessageInfo;
import org.exoplatform.commons.api.notification.model.NotificationInfo;
import org.exoplatform.commons.api.notification.service.template.TemplateContext;
import org.exoplatform.commons.notification.template.TemplateUtils;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.webui.utils.TimeConvertUtils;

import java.io.Writer;
import java.util.Calendar;
import java.util.Locale;

import static org.exoplatform.samples.notification.plugin.UpdateProfilePlugin.USER_FULL_NAME;
import static org.exoplatform.samples.notification.plugin.UpdateProfilePlugin.USER_PROFILE_URL;
// All template builders should extend org.exoplatform.commons.api.notification.channel.template.AbstractTemplateBuilder
public class UpdateProfileWebNotificationBuilder extends AbstractTemplateBuilder {
  // each template builder will override function makeMessage which returns a MessageInfo object that represents the notification to be sent
  @Override
  protected MessageInfo makeMessage(NotificationContext ctx) {
    // retrieve notification from the context
    NotificationInfo notification = ctx.getNotificationInfo();
    // Detect the language of the receiver
    String language = getLanguage(notification);

    // Create a templateContext for the Mail notification, note that we need to specify the channel here, otherwise it will take the default channel : Mail channel
    TemplateContext templateContext = TemplateContext.newChannelInstance(ChannelKey.key("WEB_CHANNEL"), notification.getKey().getId(), language);
    // Fill it with data, we should put all the variables with their values that will be replaced later in the notification template
    templateContext.put("USER_FULL_NAME", notification.getValueOwnerParameter(USER_FULL_NAME.getKey()));
    templateContext.put("USER_PROFILE_URL", CommonsUtils.getCurrentDomain() + notification.getValueOwnerParameter(USER_PROFILE_URL.getKey()));

    // the following variables should be present in all web notification
    templateContext.put("NOTIFICATION_ID", notification.getId());
    templateContext.put("MESSAGE", notification.getTitle());
    templateContext.put("READ",
            Boolean.TRUE.equals(Boolean.valueOf(notification.getValueOwnerParameter(NotificationMessageUtils.READ_PORPERTY.getKey()))) ? "read" : "unread");
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(notification.getLastModifiedDate());
    templateContext.put("LAST_UPDATED_TIME", TimeConvertUtils.convertXTimeAgoByTimeServer(cal.getTime(), "EE, dd yyyy", new Locale(language), TimeConvertUtils.YEAR));

    // compute the on-site notification content
    String body = TemplateUtils.processGroovy(templateContext);
    //binding the exception in case it was thrown by processing template
    ctx.setException(templateContext.getException());

    // Create the MessageInfo object
    MessageInfo messageInfo = new MessageInfo();
    return messageInfo.body(body).end();
  }

  @Override
  protected boolean makeDigest(NotificationContext notificationContext, Writer writer) {
    return false;
  }
}

