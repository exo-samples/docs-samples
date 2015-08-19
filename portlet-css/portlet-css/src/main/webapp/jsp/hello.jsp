<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="org.exoplatform.services.resources.ResourceBundleService"%>
<%@ page import="org.exoplatform.container.PortalContainer"%>
<portlet:defineObjects />
<%
	String contextPath = request.getContextPath();
	ResourceBundle resource = portletConfig.getResourceBundle(request.getLocale());
%>
<div class="HelloPortlet1">
	<span><%=resource.getString("com.acme.samples.HelloPortlet.Hello")%></span>
</div>
<div class="HelloPortlet2">
	<span><%=resource.getString("com.acme.samples.HelloPortlet.Msg1")%></span>
</div>
<div class="HelloPortlet3">
	<span><%=resource.getString("com.acme.samples.HelloPortlet.Msg2")%></span>
</div>