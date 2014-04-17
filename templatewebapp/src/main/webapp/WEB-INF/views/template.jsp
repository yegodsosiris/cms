<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<%@ include file="include/head.jsp" %>
<body>
<%@ include file="include/navbar.jsp" %>

	<div id="maincontainer" class="container-fluid">

		<ul class="breadcrumb">
		  <li><a href="${contextpath}/page">Home</a> <span class="divider">/</span></li>
		  <li>
		  	<a href="${contextpath}/${template.type}">
		  		<c:if test="${template.type=='page' }">Pages</c:if>
		  		<c:if test="${template.type=='layout' }">Layouts</c:if>
		  		<c:if test="${template.type=='content' }">CMS Content</c:if>
		  		<c:if test="${template.type=='widget' }">Widgets</c:if>
		  		<c:if test="${template.type=='component' }">Page Components</c:if>
		  		<c:if test="${template.type=='css' }">CSS</c:if>
		  		<c:if test="${template.type=='javascript' }">JavaScript</c:if>
		  	</a> <span class="divider">/</span>
		  </li>
		  <li class="active">Edit ${template.key}</li>
		</ul>
		
		<%@ include file="include/edit/module.jsp" %>
		<form:form method="POST" commandName="template">
			<%@ include file="edit_template.jsp" %>
			<input type="submit" value="Save Template" class="btn btn-primary"/>
			<a class="btn btn-primary" href="${contextpath}/${template.type}">Cancel</a>
		</form:form>
	
	</div>
	<%@ include file="include/edit/javascript.jsp" %>
	</body>
</html>
