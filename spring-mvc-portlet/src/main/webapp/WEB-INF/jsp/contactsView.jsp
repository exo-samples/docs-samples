<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<table border = "1">
	<tr>
		<th style="text-align:left">Name</th>
		<th style="text-align:left">Email</th>
	</tr>
	<c:forEach items = "${contacts}" var ="contact">
		<tr>
			<td>${contact.displayName}</td>
			<td>${contact.email}</td>
		</tr>
	</c:forEach>
</table>