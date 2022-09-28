package org.exoplatform.samples.notification;

import org.exoplatform.commons.api.notification.NotificationContext;
import org.exoplatform.commons.api.notification.model.PluginKey;
import org.exoplatform.commons.notification.impl.NotificationContextImpl;
import org.exoplatform.samples.notification.plugin.UpdateProfilePlugin;
import org.exoplatform.social.core.identity.model.Profile;
import org.exoplatform.social.core.profile.ProfileLifeCycleEvent;
import org.exoplatform.social.core.profile.ProfileListenerPlugin;

/**
 *  This class extends ProfileListenerPlugin to trigger avatar/experience updating events
 *  and plug them into UpdateProfilePlugin as notifications
 */
public class SocialProfileListener extends ProfileListenerPlugin {
  @Override
  public void avatarUpdated(ProfileLifeCycleEvent event) {
    sendNotification(event);
  }

  @Override
  public void bannerUpdated(ProfileLifeCycleEvent event) {
    sendNotification(event);
  }

  @Override
  public void experienceSectionUpdated(ProfileLifeCycleEvent event) {
    sendNotification(event);
  }

  @Override
  public void contactSectionUpdated(ProfileLifeCycleEvent event) {
    sendNotification(event);
  }

  @Override
  public void createProfile(ProfileLifeCycleEvent event) {}

  @Override
  public void aboutMeUpdated(ProfileLifeCycleEvent event) {
    sendNotification(event);
  }

  @Override
  public void basicInfoUpdated(ProfileLifeCycleEvent event) {
    sendNotification(event);
  }

  @Override
  public void headerSectionUpdated(ProfileLifeCycleEvent event) {
    sendNotification(event);
  }

  private void sendNotification(ProfileLifeCycleEvent event) {
    // Load user profile
    Profile profile = event.getProfile();
    // Create the notification context and append the profile object
    NotificationContext ctx = NotificationContextImpl.cloneInstance().append(UpdateProfilePlugin.PROFILE, profile);
    // Call notification executor that will load the notification type configuration and create a new notification to be sent to concerned users
    ctx.getNotificationExecutor().with(ctx.makeCommand(PluginKey.key(UpdateProfilePlugin.ID))).execute(ctx);
  }
}
