    <script type="text/javascript">  
        $(document).ready(function () {  
            $('.dropdown-toggle').dropdown();  
        });  
   </script>  
<div class="navbar">
  <div class="navbar-inner">
  <div class=container>
    <a class="brand" href="${contextpath}/page">Templates (${website.name})</a>
    <div class="pull-right">
    	<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</a>
	    <div class="nav-collapse collapse">
		    <ul class="nav">
			    <li>
			   	 	<a href="${contextpath}/template" class="navbar-text">New</a>
			    </li>
			     
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						Change Site
						<b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						    <li>
		   	 					<a href="${contextpath}/website/gb" class="navbar-text">UK</a>
						    </li>
						    <li>
						   	 	<a href="${contextpath}/website/us" class="navbar-text">USA</a>
						    </li>
						    <li>
						   	 	<a href="${contextpath}/website/de" class="navbar-text">Germany</a>
						    </li>
						    <li>
						   	 	<a href="${contextpath}/website/fr" class="navbar-text">France</a>
						    </li>
						    <li>
						   	 	<a href="${contextpath}/website/hk" class="navbar-text">Hong Kong</a>
						    </li>
						    <li>
						   	 	<a href="${contextpath}/website/es" class="navbar-text">Spain</a>
						    </li>	
					</ul>
			</li>
			    <li>
			    	<a href="${contextpath}/logout" class="navbar-text" data-bind="click: $root.logout">Logout</a>
			    </li>
		    </ul>
	    </div>
    </div>
    </div> <!-- container -->
  </div>
</div>