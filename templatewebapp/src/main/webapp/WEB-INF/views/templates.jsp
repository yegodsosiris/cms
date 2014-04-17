<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ include file="include/head.jsp"%>
<body>
	<%@ include file="include/navbar.jsp"%>

	<div id="maincontainer" class="container-fluid">
	<ul class="breadcrumb">
	  <li><a href="${contextpath}/page">Home</a> <span class="divider">/</span></li>
	  <li class="active">Templates</li>
	</ul>
	<ul class="nav nav-tabs">
	  <li<c:if test="${type=='page' }"> class="active"</c:if>><a href="${contextpath}/page">Pages</a></li>
	  <li<c:if test="${type=='component' }"> class="active"</c:if>><a href="${contextpath}/component">Page Components</a></li>
	  <li<c:if test="${type=='layout' }"> class="active"</c:if>><a href="${contextpath}/layout">Layouts</a></li>
	  <li<c:if test="${type=='css' }"> class="active"</c:if>><a href="${contextpath}/css">CSS</a></li>
	  <li<c:if test="${type=='javascript' }"> class="active"</c:if>><a href="${contextpath}/javascript">JavaScript</a></li>
	  <li<c:if test="${type=='content' }"> class="active"</c:if>><a href="${contextpath}/content">CMS Content</a></li>
	  <li<c:if test="${type=='widget' }"> class="active"</c:if>><a href="${contextpath}/widget">Widgets</a></li>
	  <li<c:if test="${type=='widgetComponent' }"> class="active"</c:if>><a href="${contextpath}/widgetComponent">Widget Component</a></li>
	</ul>
	
	<table class="table table-striped table-hover table-bordered">
	<thead>
		<tr>
			<th>Key</th>
			<th>Type</th>
			<th>Version</th>
			<th>Website</th>
			<th>Last Modified</th>
			<th>Description</th>
			<th>Versions</th>
			<th>Delete</th>
		</tr>
	</thead>
		<tbody>
		<c:forEach var="template" items="${templates}">
			<tr>
				<td><a href="${contextpath}/template/${template.id}">${template.key}</a></td>
				<td>${template.type}</td>
				<td>${template.version}</td>
				<td>${template.website}</td>
				<td>${template.lastModified}</td>
				<td>${template.description}</td>
				<td><a href="${contextpath}/r/${template.key}">versions</a></td>
				<td><a href="${contextpath}/delete/${template.id}">delete</a></td>
			</tr>
		</c:forEach>
	</tbody>
	</table>
	
	<div class="btn-group">
		<a class="btn "href="${contextpath}/a/${type}">Available Templates</a>    
		<a class="btn" href="${contextpath}/${type}">Site Templates</a>
	</div>

	</div>
</body>
</html>
