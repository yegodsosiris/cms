	<ul class="breadcrumb">
	  <li><a href="${contextpath}/content/generic">Home</a> <span class="divider">/</span></li>
	  <li class="active">Content</li>
	</ul>
	<ul class="nav nav-tabs">
	  <li<c:if test="${type=='generic' }"> class="active"</c:if>><a href="${contextpath}/content/generic">Generic Content</a></li>
	  <li<c:if test="${type=='image' }"> class="active"</c:if>><a href="${contextpath}/asset/image">Images</a></li>
	  <li<c:if test="${type=='document' }"> class="active"</c:if>><a href="${contextpath}/asset/document">Documents</a></li>
	  <li<c:if test="${type=='video' }"> class="active"</c:if>><a href="${contextpath}/asset/video">Video</a></li>
	  <li<c:if test="${type=='audio' }"> class="active"</c:if>><a href="${contextpath}/asset/audio">Audio</a></li>
	</ul>