
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/public.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>LBS</title>

<link href="<%=resource%>framework/bootstrap-3.3.4/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=resource%>font-awesome/css/font-awesome.min.css" rel="stylesheet">

<link href="<%=resource%>css/animate.css" rel="stylesheet">
<link href="<%=resource%>css/style.min.css" rel="stylesheet">

<link href="<%=resource%>plugins/treeview/bootstrap-treeview.min.css" rel="stylesheet">
<link href="<%=resource%>plugins/sweetalert-master/sweetalert.css" rel="stylesheet">

<link href="<%=resource%>plugins/datepicker/css/bootstrap-datepicker3.css" rel="stylesheet">

<link href="<%=resource%>plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet">
<link href="<%=resource%>plugins/toastr/toastr.min.css" rel="stylesheet">
<link href="<%=resource%>plugins/validate/css/validationEngine.jquery.css?v=0.1" rel="stylesheet">
<style type="text/css">
.ibox {
	margin-bottom: 0px;
}

.wrapper-content {
	padding-bottom: 0px;
}
</style>
</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					
					<div class="ibox-content">
						<div id="toolbar" class="well">
							<form id="form-marker-seach" role="form" class="form-inline">
								<div class="form-group">
									<label for="exampleInputEmail2" class="sr-only">MAC</label> <input
										name="par[mac]" type="text" placeholder="设备MAC"
										class="form-control">
								</div>
								<div class="form-group">
									<label for="exampleInputEmail2" class="sr-only">日期</label> 
									<input id="data_1" name="par[date]" type="text" placeholder="日期，默认为查询当日" class="form-control">
								</div>
								<button id="btn-search-marker" class="btn btn-sm btn-primary"
									type="button" style="margin-bottom: 0px;">&nbsp;查询&nbsp;</button>
								<div class="form-group">
									<label for="exampleInputEmail2" class="sr-only">线路名称</label> 
									<input name="lineName" type="text" placeholder="线路名称" class="form-control">
								</div>
								<button id="btn-loading-line" class="btn btn-sm btn-primary"
									type="button" style="margin-bottom: 0px;">&nbsp;加载&nbsp;</button>
							</form>
						</div>
						<div id="container" style="height: calc(100% - 60px);">
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div id="control" style="width: 30%;">
            <div class="col-sm-12">
                <div class="ibox">
                	<!-- <div class="ibox-title">
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div> -->
                    <div class="ibox-content" style="padding-bottom: 5px;">
                        <div class="project-list">
							<!-- <div class="input-group">
									<input name="uPassword" type="text" placeholder="登陆密码"
										class="form-control validate[required]">
									<div class="input-group-btn">
										<button id="btn-random" tabindex="-1" class="btn btn-white" type="button">查询线路</button>
									</div>
								</div> -->
                        	<table class="table table-hover" style="margin-bottom: 0px;">
                            	<tbody>
                                
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
	</div>
	
	<!-- Mainly scripts -->
	<script src="<%=resource%>framework/jquery-2.1.1.min.js"></script>
	<script src="<%=resource%>framework/bootstrap-3.3.4/js/bootstrap.min.js"></script>
	
	<script src="<%=resource%>plugins/pace/pace.min.js"></script>

	<script src="<%=resource%>plugins/datepicker/js/bootstrap-datepicker.js"></script>
	<%-- <script src="<%=resource%>plugins/datepicker/locales/bootstrap-datepicker.zh-CN.min.js"</script> --%>

	<!-- Toastr script -->
	<script src="<%=resource%>plugins/toastr/toastr.min.js"></script>
	
 	<!-- <script src="http://cache.amap.com/lbs/static/es5.min.js"></script> -->
    <!-- <script src="http://webapi.amap.com/maps?v=1.3&key=7868c147e8a9dbacd76bcba472029431&plugin=AMap.LineSearch"></script> -->
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=vGLMlLOiZVnA7qhXPv1ddyXE2QIlC8fK"></script>
    <script src="<%=basePath%>moojs/moo-util-1.js"></script>
	<script src="<%=basePath%>moojs/device/map.js?v=1"></script>
</body>
</html>