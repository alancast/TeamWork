<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Smart Room</title>
<link rel="stylesheet" href="resources/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/bootstrap3/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="resources/css/dashboard.css">
<link rel="stylesheet" href="resources/css/offcanvas.css">
<link rel="stylesheet" href="resources/css/mycss.css">
<script type="text/javascript" src="resources/javascript/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="resources/bootstrap3/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/javascript/offcanvas.js"></script>
<script type="text/javascript" src="resources/javascript/myfunctions.js"></script>
</head>
<body>
<div class="navbar navbar-cisco navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle Nav</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<div class="company-logo"></div>
		</div><!-- /.navbar-header -->
		<div class="collapse navbar-collapse">
			<!-- 
			<ul class="nav navbar-nav">
				<li class="active"><a href="home">Home</a></li>
			</ul>
			-->
		</div><!-- /.nav-collapse -->
	</div>
</div>

<div class="container-fluid">
	<div class="row row-offcanvas row-offcanvas-left">
	
		<!-- Nav area (offcanvas) -->
		<!-- Sidebar Nav, being used as a tab-like structured -->
		<div class="col-xs-9 col-sm-2 sidebar-offcanvas" id="sidebar" role="navigation">
			<ul class="nav nav-sidebar">
				<li class="active"><a id="overview" data-toggle="tab">Overview</a></li>
				<li><a class="report-gen" buildingID="SJC-1" data-toggle="tab">SJC Building 1</a></li>
				<li><a class="report-gen" buildingID="SJC-3" data-toggle="tab">SJC Building 3</a></li>
				<li><a class="report-gen" buildingID="SJC-12" data-toggle="tab">SJC Building 12</a></li>
				<li><a class="report-gen" buildingID="SJC-MR3" data-toggle="tab">SJC MR3</a></li>
			</ul>
        </div><!--/span-->
	
		<!-- full-width on xsmall devices, 9/12 on small or above -->
		<div class="col-xs-12 col-sm-10">
			<p class="visible-xs">
				<button type="button" class="btn btn-warning btn-xs" data-toggle="offcanvas">Toggle Nav</button>
			</p>
			
			<!-- Main viewing area -->
			<div id="report-area">
				<jsp:include page="resources/shared/overview.jsp"></jsp:include>
			</div>
		</div>
		
		
	</div>
</div>
</body>
</html>