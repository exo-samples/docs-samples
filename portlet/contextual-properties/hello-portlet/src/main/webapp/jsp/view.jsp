<%
	String navigation_uri = request.getParameter("navigation_uri");
	String page_name = request.getParameter("page_name");
	String site_type = request.getParameter("site_type");
	String site_name = request.getParameter("site_name");
	String window_width = request.getParameter("window_width");
	String window_height = request.getParameter("window_height");
	String window_show_info_bar = request.getParameter("window_show_info_bar");
	String current_time = request.getParameter("current_time");
%>
<style>
	#contextual_properties td:last-child {font-style: italic}
	#contextual_properties tr, td {padding: 5px}
</style>
<table border="1" id="contextual_properties" style="width: auto; border-spacing: 5px">
	<tr><td>navigation_uri</td><td><%=navigation_uri%></td></tr>
	<tr><td>page_name</td><td><%=page_name%></td></tr>
	<tr><td>site_type</td><td><%=site_type%></td></tr>
	<tr><td>site_name</td><td><%=site_name%></td></tr>
	<tr><td>window_width</td><td><%=window_width%></td></tr>
	<tr><td>window_height</td><td><%=window_height%></td></tr>
	<tr><td>window_show_info_bar</td><td><%=window_show_info_bar%></td></tr>
	<tr><td>current_time</td><td><%=current_time%></td></tr>
</table>