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
    snedNotification(event);
  }

  @Override
  public void bannerUpdated(ProfileLifeCycleEvent event) {
    snedNotification(event);
  }

  @Override
  public void experienceSectionUpdated(ProfileLifeCycleEvent event) {
    snedNotification(event);
  }

  @Override
  public void contactSectionUpdated(ProfileLifeCycleEvent event) {
    snedNotification(event);
  }

  @Override
  public void createProfile(ProfileLifeCycleEvent event) {
    snedNotification(event);
  }

  private void snedNotification(ProfileLifeCycleEvent event) {
    Profile profile = event.getProfile();
    NotificationContext ctx = NotificationContextImpl.cloneInstance().append(UpdateProfilePlugin.PROFILE, profile);
    ctx.getNotificationExecutor().with(ctx.makeCommand(PluginKey.key(UpdateProfilePlugin.ID))).execute(ctx);
  }

}
