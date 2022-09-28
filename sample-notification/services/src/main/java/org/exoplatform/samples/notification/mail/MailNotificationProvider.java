package org.exoplatform.samples.notification.mail;

import org.exoplatform.commons.api.notification.annotation.TemplateConfig;
import org.exoplatform.commons.api.notification.annotation.TemplateConfigs;
import org.exoplatform.commons.api.notification.channel.template.TemplateProvider;
import org.exoplatform.commons.api.notification.model.PluginKey;
import org.exoplatform.container.xml.InitParams;
import org.exoplatform.samples.notification.plugin.UpdateProfilePlugin;

// Add the URL to load the template used by the notification plugin
@TemplateConfigs(templates = {
        @TemplateConfig(pluginId = UpdateProfilePlugin.ID, template = "war:/templates/notification/mail/updateProfileMailNotification.gtmpl")
})
// All template providers should extend org.exoplatform.commons.api.notification.channel.template.TemplateProvider class
public class MailNotificationProvider extends TemplateProvider {

  //constructor where we add the builder of mail notification to the list of templateBuilders inside the server
  public MailNotificationProvider(InitParams initParams) {
    super(initParams);
    this.templateBuilders.put(PluginKey.key(UpdateProfilePlugin.ID), new UpdateProfileMailNotificationBuilder());
  }
}
