	<form:hidden path="version" />
	<form:hidden path="language"/>
	<form:hidden path="type"/>
    <table style="width:100%">
	    <tr>
	        <td>
	        	<table width="100%">
	        		<tr>
				        <td>
				        	<form:label path="key">Key</form:label>
				        	<form:input path="key" id="key"/><span id="keyError" class="inputError"></span>
	        			</td>
	        		</tr>
	        	</table>
	        </td>
	    </tr>
	    <tr>
	        <td colspan="2">
	        	<form:label path="body">Body</form:label>
	        	<form:textarea path="body"/>
	        </td>
	    </tr>
	    <!-- 
	    <tr>
	        <td colspan="2">
	        	<form:label path="comments">Comments</form:label>
	        	<form:textarea path="comments"  cssStyle="height: 150px; width: 100%;"/>
	        </td>
	    </tr>
	     -->
	</table>  