package com.acme.samples.notification.plugin;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.exoplatform.commons.api.notification.NotificationContext;
import org.exoplatform.commons.api.notification.model.ArgumentLiteral;
import org.exoplatform.commons.api.notification.model.NotificationInfo;
import org.exoplatform.commons.api.notification.plugin.BaseNotificationPlugin;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.commons.utils.ListAccess;
import org.exoplatform.container.xml.InitParams;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.social.core.identity.model.Identity;
import org.exoplatform.social.core.identity.model.Profile;
import org.exoplatform.social.core.manager.RelationshipManager;
//This class extends BaseNotificationPlugin to push new notification type of profile updating event
public class UpdateProfilePlugin extends BaseNotificationPlugin {
	public final static ArgumentLiteral<Profile> PROFILE = new ArgumentLiteral<Profile>(Profile.class, "profile");
	private static final Log LOG = ExoLogger.getLogger(UpdateProfilePlugin.class);
	public final static String ID = "UpdateProfilePlugin";
	public UpdateProfilePlugin(InitParams initParams) {
		super(initParams);
	}
	@Override
	public String getId() {
		return ID;
	}
	@Override
	public boolean isValid(NotificationContext ctx) {
		return true;
	}
	@Override
	protected NotificationInfo makeNotification(NotificationContext ctx) {
		Profile profile = ctx.value(PROFILE);
		Set<String> receivers = new HashSet<String>();
		RelationshipManager relationshipManager = CommonsUtils.getService(RelationshipManager.class);
		Identity updatedIdentity = profile.getIdentity();
		ListAccess<Identity> listAccess = relationshipManager.getConnections(updatedIdentity);
		try {
			Identity[] relationships = relationshipManager.getConnections(updatedIdentity).load(0, listAccess.getSize());
			for(Identity i : relationships) {
				receivers.add(i.getRemoteId());
			}
			} catch (Exception ex) {
			LOG.error(ex.getMessage(), ex);
		}
		return NotificationInfo.instance()
		.setFrom(updatedIdentity.getRemoteId())
		.to(new ArrayList<String>(receivers))
		.setTitle(updatedIdentity.getProfile().getFullName() + " updated his/her profile.<br/>")
		.key(getId());
	}
}