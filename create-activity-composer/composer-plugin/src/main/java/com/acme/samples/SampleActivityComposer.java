package com.acme.samples;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import org.exoplatform.social.core.activity.model.ExoSocialActivity;
import org.exoplatform.social.core.activity.model.ExoSocialActivityImpl;
import org.exoplatform.social.core.application.PeopleService;
import org.exoplatform.social.core.identity.model.Identity;
import org.exoplatform.social.core.identity.provider.OrganizationIdentityProvider;
import org.exoplatform.social.core.identity.provider.SpaceIdentityProvider;
import org.exoplatform.social.core.space.model.Space;
import org.exoplatform.social.core.space.spi.SpaceService;
import org.exoplatform.social.webui.Utils;
import org.exoplatform.social.webui.activity.UIDefaultActivity;
import org.exoplatform.social.webui.composer.UIActivityComposer;
import org.exoplatform.social.webui.composer.UIComposer;
import org.exoplatform.social.webui.composer.UIComposer.PostContext;
import org.exoplatform.social.webui.profile.UIUserActivitiesDisplay;
import org.exoplatform.social.webui.profile.UIUserActivitiesDisplay.DisplayMode;
import org.exoplatform.social.webui.space.UISpaceActivitiesDisplay;
import org.exoplatform.web.application.ApplicationMessage;
import org.exoplatform.webui.application.WebuiRequestContext;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIApplication;
import org.exoplatform.webui.core.UIComponent;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;
import org.exoplatform.webui.form.UIFormStringInput;
import org.exoplatform.webui.form.UIFormTextAreaInput;
@ComponentConfig(template = "war:/groovy/com/acme/samples/SampleActivityComposer.gtmpl", events = {
	@EventConfig(listeners = SampleActivityComposer.CheckinActionListener.class),
	@EventConfig(listeners = UIActivityComposer.CloseActionListener.class),
	@EventConfig(listeners = UIActivityComposer.SubmitContentActionListener.class),
@EventConfig(listeners = UIActivityComposer.ActivateActionListener.class) })
public class SampleActivityComposer extends UIActivityComposer {
	public static final String LOCATION = "location";
	private String location_ = "";
	private boolean isLocationValid_ = false;
	private Map<String, String> templateParams;
	public SampleActivityComposer() {
		setReadyForPostingActivity(false);
		UIFormStringInput inputLocation = new UIFormStringInput("InputLocation", "InputLocation", null);
		addChild(inputLocation);
	}
	public void setLocationValid(boolean isValid) {
		isLocationValid_ = isValid;
	}
	public boolean isLocationValid() {
		return isLocationValid_;
	}
	public void setTemplateParams(Map<String, String> tempParams) {
		templateParams = tempParams;
	}
	public Map<String, String> getTemplateParams() {
		return templateParams;
	}
	public void clearLocation() {
		location_ = "";
	}
	public String getLocation() {
		return location_;
	}
	private void setLocation(String city, WebuiRequestContext requestContext) {
		location_ = city;
		if (location_ == null || location_ == "") {
			UIApplication uiApp = requestContext.getUIApplication();
			uiApp.addMessage(new ApplicationMessage("Invalid location!", null, ApplicationMessage.ERROR));
			return;
		}
		templateParams = new LinkedHashMap<String, String>();
		templateParams.put(LOCATION, location_);
		setLocationValid(true);
	}
	@Override
	public void onActivate(Event<UIActivityComposer> uiActivityComposer) {
	}
	@Override
	public void onSubmit(Event<UIActivityComposer> uiActivityComposer) {
	}
	@Override
	public void onClose(Event<UIActivityComposer> uiActivityComposer) {
	}
	/* called when user clicks "Share" button.
		* create and save activity.
	*/
	@Override
	public void onPostActivity(PostContext postContext,	UIComponent uiComponent, WebuiRequestContext requestContext, String postedMessage) throws Exception {
		if (postContext == UIComposer.PostContext.SPACE){
			UISpaceActivitiesDisplay uiDisplaySpaceActivities = (UISpaceActivitiesDisplay) getActivityDisplay();
			Space space = uiDisplaySpaceActivities.getSpace();
			Identity spaceIdentity = Utils.getIdentityManager().getOrCreateIdentity(SpaceIdentityProvider.NAME,
			space.getPrettyName(),
			false);
			ExoSocialActivity activity = new ExoSocialActivityImpl(Utils.getViewerIdentity().getId(),
			SpaceService.SPACES_APP_ID,
			postedMessage,
			null);
			activity.setType(UIDefaultActivity.ACTIVITY_TYPE);
			Utils.getActivityManager().saveActivityNoReturn(spaceIdentity, activity);
			uiDisplaySpaceActivities.init();
			} else if (postContext == PostContext.USER) {
			UIUserActivitiesDisplay uiUserActivitiesDisplay = (UIUserActivitiesDisplay) getActivityDisplay();
			Identity ownerIdentity = Utils.getIdentityManager().getOrCreateIdentity(OrganizationIdentityProvider.NAME,
			uiUserActivitiesDisplay.getOwnerName(), false);
			if (postedMessage.length() > 0) {
				postedMessage += "<br>";
			}
			if (this.getLocation() != null && this.getLocation().length() > 0) {
				postedMessage += String.format("%s checked in at %s.", ownerIdentity.getProfile().getFullName(), this.getLocation());
				} else {
				postedMessage += String.format("%s checked in at Nowhere.", ownerIdentity.getProfile().getFullName());
			}
			ExoSocialActivity activity = new ExoSocialActivityImpl(Utils.getViewerIdentity().getId(),
			PeopleService.PEOPLE_APP_ID,
			postedMessage,
			null);
			activity.setType(UIDefaultActivity.ACTIVITY_TYPE);
			activity.setTemplateParams(templateParams);
			this.clearLocation();
			Utils.getActivityManager().saveActivityNoReturn(ownerIdentity, activity);
			this.setLocationValid(false);
			if (uiUserActivitiesDisplay.getSelectedDisplayMode() == DisplayMode.MY_SPACE) {
				uiUserActivitiesDisplay.setSelectedDisplayMode(DisplayMode.ALL_ACTIVITIES);
			}
		}
	}
	public static class CheckinActionListener extends EventListener<SampleActivityComposer> {
		// this is called on event "Checkin" (when users clicks Check-in button).
		@Override
		public void execute(Event<SampleActivityComposer> event) throws Exception {
			WebuiRequestContext requestContext = event.getRequestContext();
			SampleActivityComposer sampleActivityComposer = event.getSource();
			String city;
			try {
				city = requestContext.getRequestParameter(OBJECTID).trim();
				} catch (Exception e) {
				System.out.println("Exception when getting OBJECTID!");
				return;
			}
			if (city != null && city.length() > 0) {
				sampleActivityComposer.setLocationValid(true);
				} else {
				sampleActivityComposer.setLocationValid(false);
			}
			sampleActivityComposer.setLocation(city, requestContext);
			if (sampleActivityComposer.location_ != null && sampleActivityComposer.location_.length() > 0) {
				requestContext.addUIComponentToUpdateByAjax(sampleActivityComposer);
				event.getSource().setReadyForPostingActivity(true);
			}
		}
	}
}