package org.exoplatform.samples.notification.mail;

import org.exoplatform.commons.api.notification.NotificationContext;
import org.exoplatform.commons.api.notification.channel.template.AbstractTemplateBuilder;
import org.exoplatform.commons.api.notification.model.MessageInfo;
import org.exoplatform.commons.api.notification.model.NotificationInfo;
import org.exoplatform.commons.api.notification.service.template.TemplateContext;
import org.exoplatform.commons.notification.template.TemplateUtils;
import org.exoplatform.commons.utils.CommonsUtils;

import java.io.Writer;

import static org.exoplatform.samples.notification.plugin.UpdateProfilePlugin.USER_FULL_NAME;
import static org.exoplatform.samples.notification.plugin.UpdateProfilePlugin.USER_PROFILE_URL;

// All template builders should extend org.exoplatform.commons.api.notification.channel.template.AbstractTemplateBuilder
public class UpdateProfileMailNotificationBuilder extends AbstractTemplateBuilder{

  // each template builder will override function makeMessage which returns a MessageInfo object that represents the notification to be sent
  @Override
  protected MessageInfo makeMessage(NotificationContext ctx) {

    // retrieve notification from the context
    NotificationInfo notification = ctx.getNotificationInfo();
    // Detect the language of the receiver
    String language = getLanguage(notification);

    // Create a templateContext for the Mail notification
    TemplateContext templateContext = new TemplateContext(notification.getKey().getId(), language);

    // Fill it with data, we should put all the variables with their values that will be replaced later in the notification template
    // they will be prefixed with the $ symbol inside the template
    templateContext.put("USER_FULL_NAME", notification.getValueOwnerParameter(USER_FULL_NAME.getKey()));

    templateContext.put("USER_PROFILE_URL", CommonsUtils.getCurrentDomain() + notification.getValueOwnerParameter(USER_PROFILE_URL.getKey()));

    // compute the mail subject
    String subject = TemplateUtils.processSubject(templateContext);

    // compute the mail body
    String body = TemplateUtils.processGroovy(templateContext);
    //binding the exception in case it was thrown by processing template
    ctx.setException(templateContext.getException());

    // Create and return the MessageInfo object
    MessageInfo messageInfo = new MessageInfo();
    return messageInfo.subject(subject).body(body).end();
  }

  @Override
  protected boolean makeDigest(NotificationContext notificationContext, Writer writer) {
    // This plugin does not generate a Digest email (daily/weekly)
    return false;
  }
}
