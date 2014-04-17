<ul class="breadcrumb">
  <li><a href="${contextpath}/">Home</a> <span class="divider">/</span></li>
  <li>
  	<a href="${contextpath}/asset/${model.asset.type}">
  		<c:if test="${asset.type=='image' }">Images</c:if>
  	</a> <span class="divider">/</span>
  </li>
  <li class="active">Edit ${model.asset.key}</li>
</ul>