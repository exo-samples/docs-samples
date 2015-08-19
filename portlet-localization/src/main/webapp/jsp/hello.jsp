<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ page import="java.util.ResourceBundle"%>
<portlet:defineObjects />
<%
	String contextPath = request.getContextPath();
	ResourceBundle resource = portletConfig.getResourceBundle(request.getLocale());
%>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/skin/Stylesheet.css"/>
<div class="HelloPortlet1">
	<span><%=resource.getString("com.acme.samples.HelloPortlet.Hello")%></span>
</div>
<div class="HelloPortlet2">
	<span><%=resource.getString("com.acme.samples.HelloPortlet.Msg1")%></span>
</div>
<div class="HelloPortlet3">
	<span><%=resource.getString("com.acme.samples.HelloPortlet.Msg2")%></span>
</div>