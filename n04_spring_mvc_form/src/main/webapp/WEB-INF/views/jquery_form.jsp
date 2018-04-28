<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<script type="text/javascript" src="<spring:url value="/js/jquery.min.js"></spring:url>"></script>

<script type="text/javascript">var ctx = "${pageContext.request.getContextPath()}";</script>
<script type="text/javascript" src="<spring:url value="/js/jquery_form.js" />"></script>

<form id="form" action="" method="" />

Name: <input type="text" name="name" value="Sangkhim" />
<br />
Sex: <input type="text" name="sex" value="Male" />
<br />
Address houseNo: <input type="text" name="address.houseNo" value="11" />
<br />
Address streetNo:<input type="text" name="address.streetNo" value="434" />
<br />
Address city:<input type="text" name="address.city" value="Phnom Penh" />
<br />
Address country:<input type="text" name="address.country" value="Cambodia" />
<br />
Single File: <input type="file" id="file-upload" name="file-upload" />
<br />
<img id="file-upload-img" src="" style="width: 100px; height: 100px;" />
<br />
Multiple File: <input type="file" id="files-upload" name="files-upload" />
<div id="files-upload-img"></div>

</form>

formSerializeResult:
<div id="formSerializeResult"></div>
<hr/>
formData1Result:
<div id="formData1Result"></div>
<hr/>
formData2Result:
<div id="formData2Result"></div>
<hr/>
jsonDataResult:
<div id="jsonDataResult"></div>
<hr/>
<input type="button" id="btn-submit" value="Submit" />