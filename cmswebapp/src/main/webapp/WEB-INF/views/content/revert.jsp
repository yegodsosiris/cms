<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<%@ include file="../include/head.jsp" %>
<body>
<%@ include file="../include/navbar.jsp" %>

	<div id="maincontainer" class="container-fluid">
	
		<ul class="breadcrumb">
		  <li><a href="${contextpath}/generic">Home</a> <span class="divider">/</span></li>
		  <li class="active">Revert ${contentModel.key}</li>
		</ul>
		
		<div class="bs-docs-example">${contentModel.comments}</div>
	
		<%@ include file="../include/edit/module.jsp" %>
		
		
		<form:form method="POST" commandName="contentModel">
			<%@ include file="edit_content.jsp" %>
			<input type="submit" value="Use this Content" class="btn btn-primary"/>
			<a class="btn btn-primary" href="${contextpath}/r/${contentModel.key}">Cancel</a>
		</form:form>
	</div>
	<%@ include file="../include/edit/javascript.jsp" %>
	</body>
</html>
