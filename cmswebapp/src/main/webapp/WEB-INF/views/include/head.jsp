<!DOCTYPE html>
<html>
<!-- <html manifest="manifest_1.0.mf">  -->
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
	
	<link href="${contextpath}/resources/css/start/jquery-ui-1.9.1.custom.css" rel="stylesheet">
	<link href="${contextpath}/resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="${contextpath}/resources/css/bootstrap-responsive.css" rel="stylesheet"> 
	<link href="${contextpath}/resources/css/main.css" rel="stylesheet" media="screen">
	
	<script>
		var contextpath='${contextpath}';

		ImgIconError = function (source){
			source.onerror = null;
			source.src = '${contextpath}/resources/img/icon/default.png';
			return true;
		}
		
	</script>
	<%@include file="libraries.jsp"%>
	<title>Content Management</title>
</head>