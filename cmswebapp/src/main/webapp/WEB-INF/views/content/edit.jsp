<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<%@ include file="../include/head.jsp" %>
<body>
<%@ include file="../include/navbar.jsp" %>

	<div id="maincontainer" class="container-fluid">

		<ul class="breadcrumb">
		  <li><a href="${contextpath}/">Home</a> <span class="divider">/</span></li>
		  <li>
		  	<a href="${contextpath}/content/${contentModel.type}">
		  		<c:if test="${contentModel.type=='generic' }">Generic Content</c:if>
		  	</a> <span class="divider">/</span>
		  </li>
		  <li class="active">Edit ${contentModel.key}</li>
		</ul>
		
		<%@ include file="../include/edit/module.jsp" %>
		<form:form method="POST" commandName="contentModel">
			<%@ include file="edit_content.jsp" %>
			<input type="submit" value="Save Content" class="btn btn-primary validSubmit"/>
			<a class="btn btn-primary" href="${contextpath}/">Cancel</a>
		</form:form>
	
	</div>
	<%@ include file="../include/edit/javascript.jsp" %>
	</body>
</html>
