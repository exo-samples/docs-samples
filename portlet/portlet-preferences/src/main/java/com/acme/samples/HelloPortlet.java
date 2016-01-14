package com.acme.samples;
import java.io.IOException;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.PortletException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
public class HelloPortlet extends GenericPortlet {
	@Override
	protected void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		PortletRequestDispatcher dispatcher = getPortletContext().getRequestDispatcher("/jsp/view.jsp");
		dispatcher.forward(request, response);
	}
	@Override
	protected void doEdit(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		PortletRequestDispatcher dispatcher = getPortletContext().getRequestDispatcher("/jsp/edit.jsp");
		dispatcher.forward(request, response);
	}
	@Override
	public void processAction(ActionRequest request, ActionResponse response) throws IOException, PortletException {
		String borderColor = request.getParameter("border_color");
		PortletPreferences preferences = request.getPreferences();
		preferences.setValue("border_color", borderColor);
		preferences.store();
		response.setPortletMode(PortletMode.VIEW);
	}
}