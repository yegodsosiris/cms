<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<%@ include file="../include/head.jsp" %>
<body>
<%@ include file="../include/navbar.jsp" %>

	<div id="maincontainer" class="container-fluid">
	
		<ul class="breadcrumb">
		  <li><a href="${contextpath}/">Home</a> <span class="divider">/</span></li>
		  <li class="active">Revert ${assetModel.key}</li>
		</ul>
		
		<div class="bs-docs-example">${contentModel.comments}</div>
		
		<c:set var="btntext" value="Revert to this version"/>
		<%@ include file="include_edit_asset.jsp" %>
	</div>
	</body>
</html>
