<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<meta charset="UTF-8">

<table>
	<tr>
		<th><spring:message code="form.name" /></th>
		<td>${student.name}</td>
	</tr>
	<tr>
		<th><spring:message code="form.hobby" /></th>
		<td>${student.hobby}</td>
	</tr>
	<tr>
		<th><spring:message code="form.age" /></th>
		<td>${student.age}</td>
	</tr>
	<tr>
		<th><spring:message code="form.dob" /></th>
		<td>${student.dob}</td>
	</tr>
	<tr>
		<th><spring:message code="form.address" /></th>
		<td>
			<spring:message code="form.houseNo" />: ${student.address.houseNo}
			<spring:message code="form.streetNo" />: ${student.address.streetNo}
			<spring:message code="form.city" />: ${student.address.city}
			<spring:message code="form.country" />: ${student.address.country}
		</td>
	</tr>
</table>