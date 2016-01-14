package com.acme.samples;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.portlet.GenericPortlet;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.PortletException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletURL;
public class SharingPRPPortlet extends GenericPortlet {
	@Override
	public void processAction(ActionRequest request, ActionResponse response) throws IOException, PortletException {
		response.setRenderParameter("current_time", new Date(System.currentTimeMillis()).toString());
	}
	@Override
	public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		PortletURL actionURL = response.createActionURL();
		PrintWriter w = response.getWriter();
		w.write("<p>Click <a href=\"" + actionURL.toString() + "\">here</a> to execute processAction()</p>");
		w.write("<span>" + request.getParameter("current_time") + "</span>");
		w.close();
	}
}