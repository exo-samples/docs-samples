package com.acme.samples;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.portlet.GenericPortlet;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.PortletException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
public class ConsumingPRPPortlet extends GenericPortlet {
	@Override
	public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		Map<String, String[]> paramNames = request.getPublicParameterMap();
		PrintWriter w = response.getWriter();
		for (String name : paramNames.keySet()) {
			String value = request.getParameter(name);
			w.write("<p>" + "*<b>" + name + "</b>: " + value + "</p>");
		}
		w.close();
	}
}