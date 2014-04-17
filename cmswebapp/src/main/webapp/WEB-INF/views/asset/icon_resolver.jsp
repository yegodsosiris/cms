<c:if test="${type =='image'}">
	<img width=50px src='${contextpath}/images/${asset.fileName}'>
</c:if>
<c:if test="${type !='image'}">
	<img src='${contextpath}/resources/img/icon/${asset.extn}.png' onerror="ImgIconError(this)">
</c:if>