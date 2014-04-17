<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ include file="../include/head.jsp"%>
<body>
	<%@ include file="../include/navbar.jsp"%>

	<div id="maincontainer" class="container-fluid">
	<%@ include file="../include/breadcrumb-tabs.jsp" %>
	<a class="btn btn-primary" href="${contextpath}/content/new" class="navbar-text">New content</a>
	<p>
	<table class="table table-striped table-hover table-bordered">
	<thead>
		<tr>
			<th>Key</th>
			<th>Type</th>
			<th>Version</th>
			<th>Language</th>
			<th>Last Modified</th>
			<th>Versions</th>
			<th>Delete</th>
		</tr>
	</thead>
		<tbody>
		<c:forEach var="content" items="${contents}">
			<tr>
				<td><a href="${contextpath}/content/edit/${content.id}">${content.key}</a></td>
				<td>${content.type}</td>
				<td>${content.version}</td>
				<td>${content.language}</td>
				<td>${content.lastModified}</td>
				<td><a href="${contextpath}/content/versions/${content.key}">versions</a></td>
				<td><a href="${contextpath}/content/delete/${content.id}">delete</a></td>
			</tr>
		</c:forEach>
	</tbody>
	</table>
	
	<div class="btn-group">
		<a class="btn "href="${contextpath}/content/all/${type}">Available Content</a>    
		<a class="btn" href="${contextpath}/content/${type}">Site Content</a>
	</div>

	</div>
</body>
</html>
