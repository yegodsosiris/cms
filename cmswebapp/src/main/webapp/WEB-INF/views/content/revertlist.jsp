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
		  	<a href="${contextpath}/content/${type}">
		  		<c:if test="${type=='generic' }">Generic Content</c:if>
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
		<c:forEach var="content" items="${contents}">
			<tr>
				<td><a href="${contextpath}/content/revert/${content.id}">${content.key}</a></td>
				<td>${content.version}</td>
				<td>${content.language}</td>
				<td>${content.type}</td>
				<td>${content.lastModified}</td>
				<td>${content.comments}</td>
				<td><a href="${contextpath}/content/delete/${content.id}">delete</a></td>
			</tr>
		</c:forEach>
	</tbody>
	</table>
	
	<div class="btn-group">
		<a class="btn "href="${contextpath}/content/versions/all/${key}">Available Content</a>    
		<a class="btn" href="${contextpath}/content/versions/${key}">Site Content</a>
	</div>	

	</div>
</body>
</html>
