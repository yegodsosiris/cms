<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ include file="../include/head.jsp"%>
<body>
	<%@ include file="../include/navbar.jsp"%>

	<div id="maincontainer" class="container-fluid">
	
	<ul class="breadcrumb">
	  <li><a href="${contextpath}/">Home</a> <span class="divider">/</span></li>
	  <c:if test="${type != null}">
	    <li>
		  	<a href="${contextpath}/asset/${type}">
		  		<c:if test="${type=='generic' }">Generic Content</c:if>
		  		<c:if test="${type=='document' }">Documents</c:if>
		  		<c:if test="${type=='image' }">Images</c:if>
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
			<th>Language</th>
			<th>Type</th>
			<th>Last Modified</th>
			<th>Comments</th>
			<th>Delete</th>
		</tr>
	</thead>
		<tbody>
		<c:forEach var="asset" items="${assets}">
			<tr>
				<td>
					<%@ include file="icon_resolver.jsp" %> 
					<a href="${contextpath}/asset/revert/${asset.id}">${asset.originalName}</a></td>
				<td>${asset.version}</td>
				<td>${asset.language}</td>
				<td>${asset.type}</td>
				<td>${asset.lastModified}</td>
				<td>${asset.comments}</td>
				<td><a href="${contextpath}/asset/delete/${asset.id}">delete</a></td>
			</tr>
		</c:forEach>
	</tbody>
	</table>
	
	<div class="btn-group">
		<a class="btn "href="${contextpath}/asset/versions/all/${key}">Available Versions</a>    
		<a class="btn" href="${contextpath}/asset/versions/${key}">Site Versions</a>
	</div>	

	</div>
</body>
</html>
