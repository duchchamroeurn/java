<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false"%>

<html>
<head>
<title>Add New Contact</title>
</head>
<body>
	<c:url var="post_url" value="/save_contact"/>
	<form:form action="${post_url}" modelAttribute="contact" enctype="multipart/form-data" method="POST">
	<table border="1" style="border-collapse: collapse;" cellpadding="10">
		<tr>
			<th>photo</th>
			<td><input type="file" name="file" /></td>
		</tr>
		<tr>
			<th>name</th>
			<td><form:input type="text" path="name" /></td>
		</tr>
		<tr>
			<th>city</th>
			<td><form:input type="text" path="city" /></td>
		</tr>
		<tr>
			<th>tel</th>
			<td><form:input type="text" path="tel" /></td>
		</tr>
		<tr>	
			<th>email</th>
			<td><form:input type="text" path="email" /></td>
		</tr>
	</table>
	<br />
	<input type="submit" value="Save" />
	</form:form>
</body>
</html>