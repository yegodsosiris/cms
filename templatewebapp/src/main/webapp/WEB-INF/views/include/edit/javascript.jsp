<script type="text/javascript" src="${contextpath}/resources/js/edit_area/edit_area_full.js"></script>
	<script type="text/javascript">
		editAreaLoader.init({
			id : "content"		// textarea id
			<c:if test="${template.type != 'css'}">,syntax: "html"</c:if>
			<c:if test="${template.type == 'css'}">,syntax: "css"</c:if>
			<c:if test="${template.type == 'javascript'}">,syntax: "js"</c:if>
			,start_highlight: true		// to display with highlight mode on start-up
		});
		$(function() {
			$('#comments').val('');
			<c:if test="${message !=null}">
			$('#success').modal()
			</c:if>
		})
	</script>