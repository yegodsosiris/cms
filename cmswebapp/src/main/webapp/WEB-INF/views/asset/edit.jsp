<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<%@ include file="../include/head.jsp" %>
<body>
<%@ include file="../include/navbar.jsp" %>

	<div id="maincontainer" class="container-fluid">

		<!-- ============= breadcrumb =============== -->
		<ul class="breadcrumb">
		  <li><a href="${contextpath}/">Home</a> <span class="divider">/</span></li>
		  <li>
		  	<a href="${contextpath}/asset/${model.asset.type}">
		  		<c:if test="${model.asset.type=='image' }">Images</c:if>
		  		<c:if test="${model.asset.type=='document' }">Documents</c:if>
		  		<c:if test="${model.asset.type=='audio' }">Audio</c:if>
		  		<c:if test="${model.asset.type=='video' }">Video</c:if>
		  	</a> <span class="divider">/</span>
		  </li>
		  <li class="active">Edit ${model.asset.key}</li>
		</ul>
		<!-- ============= breadcrumb =============== -->
		
		<c:set var="btntext" value="Save"/>
		<%@ include file="include_edit_asset.jsp" %>
	
	</div>
	</body>
</html>
