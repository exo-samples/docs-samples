<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="javax.portlet.PortletMode" %>
<%@ page import="javax.portlet.PortletPreferences" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<portlet:defineObjects/>
<%
	PortletURL viewURL = renderResponse.createRenderURL();
	viewURL.setPortletMode(PortletMode.VIEW);
	PortletURL actionURL = renderResponse.createActionURL();
	PortletPreferences preferences = renderRequest.getPreferences();
	String borderColor = preferences.getValue("border_color", "transparent");
%>
<div style="border: solid 1px <%=borderColor%>">
	<a href="<%=viewURL%>">Click here to switch to view mode!</a>
	<p></p>
	<form action="<%=actionURL%>" method="POST">
		<label>Select border color:</label>
		<select name="border_color">
			<option value="transparent" <%=(borderColor == "transparent" ? "selected=\"selected\"" : "")%>>None</option>
			<option value="red" <%=(borderColor == "red" ? "selected=\"selected\"" : "")%>>Red</option>
			<option value="blue" <%=(borderColor == "blue" ? "selected=\"selected\"" : "")%>>Blue</option>
		</select>
		<input type="submit" value="Save"/>
	</form>
</div>