	<form:hidden path="version" />
	<form:hidden path="website"/>
    <table style="width:100%">
	    <tr>
	        <td>
	        	<table width="100%">
	        		<tr>
	        			<td width="170px">
				        	<form:label path="type">Type</form:label>
				        	<form:select path="type" items="${types}"/>
	        			</td>
	        			
	        			<td rowspan="2" style="padding-left:10px">
	        				<form:label path="description">Description</form:label>
	        				<form:textarea path="description"  cssStyle="height: 100px; width: 100%;"/>
	        			</td>
	        		</tr>
	        		<tr>
				        <td>
				        	<form:label path="key">Key</form:label>
				        	<form:input path="key" />
	        			</td>
	        		</tr>
	        	</table>
	        </td>
	    </tr>
	    <tr>
	        <td colspan="2">
	        	<form:label path="content">Content</form:label>
	        	<form:textarea path="content"  cssStyle="height: 350px; width: 100%;"/>
	        </td>
	    </tr>
	    <tr>
	        <td colspan="2">
	        	<form:label path="comments">Comments</form:label>
	        	<form:textarea path="comments"  cssStyle="height: 150px; width: 100%;"/>
	        </td>
	    </tr>
	</table>  