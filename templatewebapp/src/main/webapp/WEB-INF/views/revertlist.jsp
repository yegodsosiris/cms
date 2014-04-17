<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ include file="include/head.jsp"%>
<body>
	<%@ include file="include/navbar.jsp"%>

	<div id="maincontainer" class="container-fluid">
	
	<ul class="breadcrumb">
	  <li><a href="${contextpath}/page">Home</a> <span class="divider">/</span></li>
	  <c:if test="${type != null}">
	    <li>
		  	<a href="${contextpath}/${type}">
		  		<c:if test="${type=='page' }">Pages</c:if>
		  		<c:if test="${type=='layout' }">Layouts</c:if>
		  		<c:if test="${type=='content' }">CMS Content</c:if>
		  		<c:if test="${type=='widget' }">Widgets</c:if>
		  		<c:if test="${type=='component' }">Page Components</c:if>
		  	</a> <span class="divider">/</span>
		  </li>
	  </c:if>
	  <li class="active">${key}</li>
	</ul>
	
	<table class="table table-striped table-hover table-bordered">
	<thead>
		<tr>
			<th>Key</th>
			<th>Version</th>
			<th>Website</th>
			<th>Type</th>
			<th>Last Modified</th>
			<th>Comments</th>
			<th>Delete</th>
		</tr>
	</thead>
		<tbody>
		<c:forEach var="template" items="${templates}">
			<tr>
				<td><a href="${contextpath}/revert/${template.id}">${template.key}</a></td>
				<td>${template.version}</td>
				<td>${template.website}</td>
				<td>${template.type}</td>
				<td>${template.lastModified}</td>
				<td>${template.comments}</td>
				<td><a href="${contextpath}/delete/${template.id}">delete</a></td>
			</tr>
		</c:forEach>
	</tbody>
	</table>
	
	<div class="btn-group">
		<a class="btn "href="${contextpath}/ra/${key}">Available Templates</a>    
		<a class="btn" href="${contextpath}/${key}">Site Templates</a>
	</div>	

	</div>
</body>
</html>
