package com.acme.samples.notification;
import java.io.Writer;
import org.exoplatform.commons.api.notification.NotificationContext;
import org.exoplatform.commons.api.notification.channel.AbstractChannel;
import org.exoplatform.commons.api.notification.channel.template.AbstractTemplateBuilder;
import org.exoplatform.commons.api.notification.channel.template.TemplateProvider;
import org.exoplatform.commons.api.notification.model.ChannelKey;
import org.exoplatform.commons.api.notification.model.MessageInfo;
import org.exoplatform.commons.api.notification.model.NotificationInfo;
import org.exoplatform.commons.api.notification.model.PluginKey;
import org.exoplatform.commons.notification.lifecycle.SimpleLifecycle;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
//This class extends AbstractChannel to define a new notification channel which sends messages through console panel
public class ConsoleChannel extends AbstractChannel {
	private static final Log LOG = ExoLogger.getLogger(ConsoleChannel.class);
	private final static String ID = "CONSOLE_CHANNEL";
	private final ChannelKey key = ChannelKey.key(ID);
	public ConsoleChannel() {
		super(new SimpleLifecycle());
	}
	@Override
	public String getId() {
		return ID;
	}
	@Override
	public ChannelKey getKey() {
		return key;
	}
	@Override
	public void dispatch(NotificationContext ctx, String userId) {
		LOG.info(String.format("CONSOLE:: %s will receive the message from pluginId: %s",
		userId,
		ctx.getNotificationInfo().getKey().getId()));
	}
	@Override
	public void registerTemplateProvider(TemplateProvider provider) {}
	@Override
	protected AbstractTemplateBuilder getTemplateBuilderInChannel(PluginKey key) {
		return new AbstractTemplateBuilder() {
			@Override
			protected MessageInfo makeMessage(NotificationContext ctx) {
				return null;
			}
			@Override
			protected boolean makeDigest(NotificationContext ctx, Writer writer) {
				return false;
			}
		};
	}
	}	
