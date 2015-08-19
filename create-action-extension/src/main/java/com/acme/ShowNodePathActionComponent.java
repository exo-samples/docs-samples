package com.acme;
import javax.jcr.Node;
import org.exoplatform.ecm.webui.component.explorer.UIJCRExplorer;
import org.exoplatform.ecm.webui.component.explorer.control.listener.UIActionBarActionListener;
import org.exoplatform.web.application.ApplicationMessage;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIComponent;
import org.exoplatform.webui.event.Event;
import java.util.List;
import java.util.Arrays;
import org.exoplatform.webui.ext.filter.UIExtensionFilter;
import org.exoplatform.webui.ext.filter.UIExtensionFilters;
import com.acme.MyUIFilter;
@ComponentConfig(
events = { @EventConfig(listeners = ShowNodePathActionComponent.ShowNodePathActionListener.class) })
public class ShowNodePathActionComponent extends UIComponent {
	public static class ShowNodePathActionListener extends UIActionBarActionListener<ShowNodePathActionComponent> {
		@Override
		protected void processEvent(Event<ShowNodePathActionComponent> event) throws Exception {
			UIJCRExplorer uiJCRExplorer = event.getSource().getAncestorOfType(UIJCRExplorer.class);
			Node node = uiJCRExplorer.getCurrentNode();
			event.getRequestContext()
			.getUIApplication()
			.addMessage(new ApplicationMessage("Node path:" + node.getPath(), null, ApplicationMessage.INFO));
		}
		/*
			* Add filters (MyUIFilter in this example)
		*/
		private static final List<UIExtensionFilter> FILTERS = Arrays.asList(new UIExtensionFilter[] {new MyUIFilter()});
		@UIExtensionFilters
		public List<UIExtensionFilter> getFilters() {
			return FILTERS;
		}
	}
}