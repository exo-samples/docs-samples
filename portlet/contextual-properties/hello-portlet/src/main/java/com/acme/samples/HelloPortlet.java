package com.acme.samples;
import java.io.IOException;
import java.util.Date;
import java.io.PrintWriter;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.PortletException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
public class HelloPortlet extends GenericPortlet {
	@Override
	public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		PortletRequestDispatcher dispatcher = getPortletContext().getRequestDispatcher("/jsp/view.jsp");
		dispatcher.include(request, response);
	}
}