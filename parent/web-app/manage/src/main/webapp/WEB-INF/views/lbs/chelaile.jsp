 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%@ include file="../../inc/public.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>MINImoo开放平台</title>

   	<link href="<%=resource %>framework/bootstrap-3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=resource %>font-awesome/css/font-awesome.min.css" rel="stylesheet">

    <!-- Morris -->
    <link href="<%=resource %>plugins/morris/morris.css" rel="stylesheet">

    <!-- Gritter -->
    <link href="<%=resource %>plugins/Gritter/css/jquery.gritter.css" rel="stylesheet">

    <link href="<%=resource %>css/animate.css" rel="stylesheet">
    <link href="<%=resource %>css/style.min.css" rel="stylesheet">
	<style type="text/css">
		.panel-heading{padding: 0px 0px;}
		.ibox {margin-bottom: 0px;}
	</style>
</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content">
	
<div class="row animated fadeInRight">
    <div class="col-sm-12">
        <div class="ibox float-e-margins">
            <div class="ibox-content">
               <!--  <div class="row">
                    <div class="col-sm-12">
                       <div id="main" style="width: 100%;height: calc(100% - 90px)">
                       	
                       </div>
                    </div>
                </div> -->
                
                <div class="row" >
                    <div class="col-lg-12">
                        <div class="panel blank-panel" style="height: calc(100% - 17px)">
                            <div class="panel-heading">
                                <div class="panel-options">
                                    <ul class="nav nav-tabs">
                                        <li class="active"><a data-toggle="tab" href="tabs_panels.html#tab-1">准实时数据</a>
                                        </li>
                                        <li class=""><a data-toggle="tab" href="tabs_panels.html#tab-2">历史车辆统计</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="panel-body">
                                <div class="tab-content">
                                    <div id="tab-1" class="tab-pane active">
										<div id="main" style="width: 100%;height: calc(100% - 40px);"></div>
                                    </div>
                                    <div id="tab-2" class="tab-pane">
                                    	<h1>功能暂无开放</h1>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</div>

  	<!-- Mainly scripts -->
    <script src="<%=resource %>framework/jquery-2.1.1.min.js"></script>
    <script src="<%=resource %>framework/bootstrap-3.3.4/js/bootstrap.min.js"></script>
    <script src="<%=resource %>plugins/metisMenu/metisMenu.min.js"></script>
    <script src="<%=resource %>plugins/jQuery-slimScroll/jquery.slimscroll.min.js"></script>
	
	<!-- baidu echarts -->
	<script src="<%=resource %>plugins/echarts3/echarts.min(1).js"></script>
	<!-- 引入 vintage 主题 -->
	<script src="<%=resource %>plugins/echarts3/macarons.js"></script>
	<script src="<%=resource %>plugins/echarts3/china.js"></script>
	<%-- <script src="<%=basePath %>moojs/echarts/gps.js"></script> --%>
	<script src="<%=basePath %>moojs/echarts/chelailegps.js?v=2.0"></script>
	
	<script type="text/javascript">
		
	</script>

</body>
</html>