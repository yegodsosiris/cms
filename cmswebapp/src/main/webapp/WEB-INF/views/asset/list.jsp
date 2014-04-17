<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ include file="../include/head.jsp"%>
<body>
	<%@ include file="../include/navbar.jsp"%>

	<div id="maincontainer" class="container-fluid">
	<%@ include file="../include/breadcrumb-tabs.jsp" %>
	<a class="btn btn-primary" href="${contextpath}/asset/new/${type}" class="navbar-text">New ${type}</a>
	<p>
	<table class="table table-striped table-hover table-bordered">
	<thead>
		<tr>
			<th>File</th>
			<th>Key</th>
			<th>Size</th>
			<th>Version</th>
			<th>Language</th>
			<th>Last Modified</th>
			<th>Versions</th>
			<th>Delete</th>
		</tr>
	</thead>
		<tbody>
		<c:forEach var="asset" items="${assets}">
			<tr>
				<td>
					<%@ include file="icon_resolver.jsp" %>
					<a href="${contextpath}/asset/edit/${asset.id}">${asset.originalName}</a></td>
				<td>${asset.key}</td>
				<td>${asset.readableFileSize}</td>
				<td>${asset.version}</td>
				<td>${asset.language}</td>
				<td>${asset.lastModified}</td>
				<td><a href="${contextpath}/asset/versions/${asset.key}">versions</a></td>
				<td><a href="${contextpath}/asset/delete/${asset.id}">delete</a></td>
			</tr>
		</c:forEach>
	</tbody>
	</table>
	
	<div class="btn-group">
		<a class="btn "href="${contextpath}/asset/all/${type}">Available ${type}s</a>    
		<a class="btn" href="${contextpath}/asset/${type}">Site ${type}s</a>
	</div>

	</div>
</body>
</html>
