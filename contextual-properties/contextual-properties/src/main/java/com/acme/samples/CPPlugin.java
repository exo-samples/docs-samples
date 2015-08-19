package com.acme.samples;
import java.util.Map;
import java.util.Date;
import org.exoplatform.portal.application.state.AbstractContextualPropertyProviderPlugin;
import javax.xml.namespace.QName;
import org.exoplatform.container.xml.InitParams;
import org.exoplatform.portal.webui.application.UIPortlet;
public class CPPlugin extends AbstractContextualPropertyProviderPlugin {
	private QName myQName;
	public CPPlugin (InitParams params) {
		super(params);
		this.myQName = new QName(namespaceURI, "current_time");
	}
	@Override
	public void getProperties(UIPortlet portletWindow, Map<QName, String[]> properties) {
		addProperty(properties, myQName, new Date(System.currentTimeMillis()).toString());
	}
}