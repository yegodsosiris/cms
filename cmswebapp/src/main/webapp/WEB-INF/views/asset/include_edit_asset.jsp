		<h3>${model.asset.originalName}</h3>
		<c:if test="${errors != null }">
			<span style="color:red">${errors}</span>
		</c:if>
		<c:if test="${model.asset.type =='image' && editmode}">
			<img width=350px src='${contextpath}/images/${model.asset.fileName}'>
		</c:if>
				
		<%@ include file="../include/edit/module.jsp" %>
		<form:form commandName="model" enctype="multipart/form-data" method="POST">
			<form:hidden path="asset.type"/>
			<c:if test="${editmode}">
				<form:hidden path="asset.id"/>
				<form:hidden path="asset.language"/>
				<form:hidden path="asset.originalName"/>
				<form:hidden path="asset.version"/>
				<form:hidden path="asset.fileName"/>
				<form:hidden path="asset.key"/>
			</c:if>
			<div>
				<c:if test="${model.asset.id !=null}">
					<h4>Key: ${model.asset.key}</h4>
				</c:if>
				<c:if test="${model.asset.id ==null}">
		        	<form:label path="asset.key">Key</form:label>
		        	<form:input path="asset.key" id="key"/><span id="keyError" class="inputError"></span>
		        </c:if>
			</div>
			<div>
				<input type="file" name="file" id=file/>
			</div>
			<div class="btn-group">
				<input type="submit" value="${btntext}"  class="btn btn-primary validSubmit"/>
				<a class="btn btn-primary" href="${contextpath}/asset/${model.asset.type}">Cancel</a>
			</div>
		</form:form>

	<%@ include file="../include/edit/javascript.jsp" %>
	