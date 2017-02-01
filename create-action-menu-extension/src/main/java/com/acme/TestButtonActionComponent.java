package com.acme;
import javax.jcr.Node;
import org.exoplatform.ecm.webui.component.explorer.UIJCRExplorer;
import org.exoplatform.ecm.webui.component.explorer.control.filter.HasRemovePermissionFilter;
import org.exoplatform.ecm.webui.component.explorer.control.filter.IsCheckedOutFilter;
import org.exoplatform.ecm.webui.component.explorer.control.filter.IsNotLockedFilter;
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
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.webui.ext.manager.UIAbstractManager;
import org.exoplatform.webui.ext.manager.UIAbstractManagerComponent;

@ComponentConfig(
        events = {
                @EventConfig(listeners = TestButtonActionComponent.TestButtonActionListener.class)
        })
public class TestButtonActionComponent extends UIAbstractManagerComponent {
    private static final List<UIExtensionFilter> FILTERS =
            Arrays.asList(  new UIExtensionFilter[]{
                    new IsNotLockedFilter(),
                    new IsCheckedOutFilter(),
                    new HasRemovePermissionFilter()
            });

    private static final Log LOG = ExoLogger.getLogger(TestButtonActionListener.class.getName());
    @Override
    public Class<? extends UIAbstractManager> getUIAbstractManagerClass() {
        return null;
    }
    @UIExtensionFilters
    public List<UIExtensionFilter> getFilters() {
        LOG.info("Button created ");
        return FILTERS;
    }
    public static class TestButtonActionListener extends UIActionBarActionListener<TestButtonActionComponent> {

        @Override
        protected void processEvent(Event<TestButtonActionComponent> event) throws Exception {
        }
    }

}
