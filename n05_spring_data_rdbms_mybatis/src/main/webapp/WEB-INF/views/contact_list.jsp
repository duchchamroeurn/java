<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>Contact List</title>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/DataTables-1.10.7/media/css/jquery.dataTables.css"/>">
<script type="text/javascript" src="<c:url value="/resources/DataTables-1.10.7/media/js/jquery.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/DataTables-1.10.7/media/js/jquery.dataTables.js"/>"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#tbl').DataTable();
	});
</script>

</head>
<body>
	<table id="tbl">
		<thead>
			<tr>
				<th>photo</th>
				<th>name</th>
				<th>city</th>
				<th>tel</th>
				<th>email</th>
				<th>action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${data}" var="row" varStatus="status">
			<tr>
				<td><img alt="" src="${context}/${row.photo}" width="100" /></td>
				<td>${row.name}</td>
				<td>${row.city}</td>
				<td>${row.tel}</td>
				<td>${row.email}</td>
				<td>
					<a href="<c:url value="/update_contact/${row.id}"/>">Update</a> 
					|
					<a href="<c:url value="/delete_contact/${row.id}"/>">Delete</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<br />
	<input type="button" value="Add New Contact" onclick="window.open('<c:url value="/add_new_contact"/>','_self')" />
</body>

</html>