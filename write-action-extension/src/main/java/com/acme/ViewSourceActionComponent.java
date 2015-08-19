package com.acme;
import java.util.Arrays;
import java.util.List;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.ext.filter.UIExtensionFilter;
import org.exoplatform.webui.ext.filter.UIExtensionFilters;
import org.exoplatform.wiki.commons.Utils;
import org.exoplatform.wiki.mow.core.api.wiki.PageImpl;
import org.exoplatform.wiki.webui.UIWikiContentDisplay;
import org.exoplatform.wiki.webui.UIWikiPageContentArea;
import org.exoplatform.wiki.webui.UIWikiPortlet;
import org.exoplatform.wiki.webui.control.action.core.AbstractEventActionComponent;
import org.exoplatform.wiki.webui.control.filter.IsViewModeFilter;
import org.exoplatform.wiki.webui.control.listener.MoreContainerActionListener;
@ComponentConfig (
template = "app:/templates/wiki/webui/control/action/AbstractActionComponent.gtmpl",
events = {
	@EventConfig(listeners = ViewSourceActionComponent.ViewSourceActionListener.class)
}
)
public class ViewSourceActionComponent extends AbstractEventActionComponent {
	public static final String ACTION = "ViewSource";
	private static final List<UIExtensionFilter> FILTERS = Arrays.asList(new UIExtensionFilter[] { new IsViewModeFilter() });
	@UIExtensionFilters
	public List<UIExtensionFilter> getFilters() {
		return FILTERS;
	}
	@Override
	public String getActionName() {
		return ACTION;
	}
	@Override
	public boolean isAnchor() {
		return false;
	}
	public static class ViewSourceActionListener extends MoreContainerActionListener<ViewSourceActionComponent> {
		@Override
		protected void processEvent(Event<ViewSourceActionComponent> event) throws Exception {
			UIWikiPortlet wikiPortlet = event.getSource().getAncestorOfType(UIWikiPortlet.class);
			UIWikiContentDisplay contentDisplay = wikiPortlet.findFirstComponentOfType(UIWikiPageContentArea.class)
			.getChildById(UIWikiPageContentArea.VIEW_DISPLAY);
			PageImpl wikipage = (PageImpl) Utils.getCurrentWikiPage();
			contentDisplay.setHtmlOutput(wikipage.getContent().getText());
			event.getRequestContext().addUIComponentToUpdateByAjax(contentDisplay);
		}
	}
}