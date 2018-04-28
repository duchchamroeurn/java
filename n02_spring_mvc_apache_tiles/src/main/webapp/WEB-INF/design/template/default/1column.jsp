<%@ page contentType="text/html; charset=utf-8" session="true" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<tiles:insertAttribute name="default.head" />
</head>
<body style="margin: 0px;">
<div id="wrapper">
	<div>
		<div id="header">
			<tiles:insertAttribute name="default.header" />
	    </div>
	    <div id="menu">
	    	<tiles:insertAttribute name="default.menu" />
	    </div>
	</div>
	<div id="content">
		<tiles:insertAttribute name="default.content" />
	</div>
	<div id="footer">
		<tiles:insertAttribute name="default.footer" />
	</div>
</div>
</body>
</html>