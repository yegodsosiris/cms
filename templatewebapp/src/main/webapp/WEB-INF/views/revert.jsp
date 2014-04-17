<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<%@ include file="include/head.jsp" %>
<body>
<%@ include file="include/navbar.jsp" %>

	<div id="maincontainer" class="container-fluid">
	
		<ul class="breadcrumb">
		  <li><a href="${contextpath}/page">Home</a> <span class="divider">/</span></li>
		  <li><a href="${contextpath}/layout">Layouts</a> <span class="divider">/</span></li>
		  <li class="active">Revert ${template.key}</li>
		</ul>
		
		<div class="bs-docs-example">${template.comments}</div>
	
		<%@ include file="include/edit/module.jsp" %>
		
		
		<form:form method="POST" commandName="template">
			<%@ include file="edit_template.jsp" %>
			<input type="submit" value="Use this Template" class="btn btn-primary"/>
			<a class="btn btn-primary" href="${contextpath}/r/${template.key}">Cancel</a>
		</form:form>
	</div>
	<%@ include file="include/edit/javascript.jsp" %>
	</body>
</html>
