
<script type="text/javascript">
	$(document).ready(function() {
		$('.dropdown-toggle').dropdown();
	});
</script>
<div class="navbar">
	<div class="navbar-inner">
		<div class=container>
			<a class="brand" href="${contextpath}/">Content Management
				(${website.name})</a>
			<div class="pull-right">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"> New <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li>
									<a href="${contextpath}/content/new" class="navbar-text">Content</a>
								</li>
								<li>
									<a href="${contextpath}/asset/new/image" class="navbar-text">Image</a>
								</li>
								<li>
									<a href="${contextpath}/asset/new/document" class="navbar-text">Document</a>
								</li>
								<li>
									<a href="${contextpath}/asset/new/video" class="navbar-text">Video</a>
								</li>
								<li>
									<a href="${contextpath}/asset/new/audio" class="navbar-text">Audio</a>
								</li>
							</ul>
						</li>

						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"> Change Language <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<c:forEach items="${locales}" var="locale">
									<li>
										<a href="${contextpath}/language/${locale.language}" class="navbar-text">${locale.displayLanguage}</a>
									</li>
								</c:forEach>
							</ul>
						</li>
						<li>
							<a href="${contextpath}/logout" class="navbar-text"	data-bind="click: $root.logout">Logout</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<!-- container -->
	</div>
</div>