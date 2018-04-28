<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<meta charset="UTF-8">

Language <a href="?language=en">English</a> | <a href="?language=kh">Khmer</a>

<style type="text/css">
.error{
	color: #f00;
}
.errorBlock{
	background-color: #fee;
}
</style>

<h1><spring:message code="form.title" /></h1>
<form:form action="${pageContext.request.contextPath}/save" modelAttribute="student" enctype="multipart/form-data" method="POST">
<form:errors path="student.*" cssClass="errorBlock" />
<table>
	<tr>
		<th><spring:message code="form.name" /></th>
		<td><form:input type="text" path="name" cssErrorClass="error" /> <form:errors path="name" cssClass="error" /></td>
	</tr>
	<tr>
		<th><spring:message code="form.hobby" /></th>
		<td><form:input type="text" path="hobby" cssErrorClass="error" /> <form:errors path="hobby" cssClass="error" /></td>
	</tr>
	<tr>
		<th><spring:message code="form.age" /></th>
		<td><form:input type="text" path="age" cssErrorClass="error" /> <form:errors path="age" cssClass="error" /> min=3, max=30</td>
	</tr>
	<tr>
		<th><spring:message code="form.dob" /></th>
		<td><form:input type="text" path="dob" cssErrorClass="error" /> <form:errors path="dob" cssClass="error" /> dd/MM/YY</td>
	</tr>
	<tr>
		<th>File</th>
		<td><input type="file" id="file" /></td>
	</tr>
	<tr>
		<th><spring:message code="form.address" /></th>
		<td>
			<spring:message code="form.houseNo" />: <form:input type="text" path="address.houseNo" />
			<spring:message code="form.streetNo" />: <form:input type="text" path="address.streetNo" />
			<spring:message code="form.city" />: <form:input type="text" path="address.city" />
			<spring:message code="form.country" />: <form:input type="text" path="address.country" />
		</td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" value="Save" /></td>
	</tr>
</table>
</form:form>