<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="javax.portlet.PortletMode" %>
<%@ page import="javax.portlet.PortletPreferences" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<portlet:defineObjects/>
<%
	PortletURL editURL = renderResponse.createRenderURL();
	editURL.setPortletMode(PortletMode.EDIT);
	PortletPreferences preferences = renderRequest.getPreferences();
	String borderColor = preferences.getValue("border_color", "transparent");
%>
<div style="border: solid 1px <%=borderColor%>">
	<a href="<%=editURL%>">Click here to switch to edit mode!</a>
</div>