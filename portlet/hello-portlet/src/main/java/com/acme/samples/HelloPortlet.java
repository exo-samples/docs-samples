package com.acme.samples;
import java.io.IOException;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderMode;
public class HelloPortlet extends GenericPortlet {
	@RenderMode(name = "view")
	public void Hello(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		PortletRequestDispatcher prDispatcher = getPortletContext().getRequestDispatcher("/jsp/hello.jsp");
		prDispatcher.include(request, response);
	}
}