
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


<link rel="stylesheet" href="<%=resource%>plugins/bootstrap-table/export/bootstrap-table.css">
<link rel="stylesheet" href="<%=resource%>plugins/bootstrap-table/export/examples.css">


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
	<div class="wrapper wrapper-content" >
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						 
						<div id="toolbar" class="well" style="margin-bottom: 3px;">
							<form id="form-num-seach" role="form" class="form-inline">
								<div class="form-group">
									<label for="exampleInputEmail2" class="sr-only">购买商</label> 
										<select class="form-control" name="par[buyer]">
											<option value="CHELAILE">车来了</option>
                                            <option value="DIDI">滴滴</option>
                                            <option value="GAODE">高德</option>
                                        </select>
								</div>
								
								<div class="form-group" id="data_5">
                                   <label for="exampleInputEmail2" class="sr-only">范围选择</label> 
                                    <div class="input-daterange input-group" id="datepicker">
                                        <input type="text" id="input-start" value="2016-12-1" class="form-control" name="par[start]" />
                                        <span class="input-group-addon">到</span>
                                        <input type="text" id="input-end" value="2016-12-31" class="form-control" name="par[end]" />
                                    </div>
                                </div>
                                <button id="btn-search-chart" class="btn btn-sm btn-primary"
									type="button" style="margin-bottom: 0px;">&nbsp;查询&nbsp;</button>
								<button id="btn-back" class="btn btn-sm btn-primary"
									type="button" style="margin-bottom: 0px;">&nbsp;返回&nbsp;</button>
							</form>
						</div>
						
						<div id="main" style="width: 100%;height: calc(100% - 60px);"></div>
						
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Mainly scripts -->
	<script src="<%=resource%>framework/jquery-2.1.1.min.js"></script>
	

	<script async="" src="<%=resource%>plugins/bootstrap-table/export/analytics.js"></script>
	<script src="<%=resource%>plugins/bootstrap-table/export/jquery.min.js"></script>
	<script src="<%=resource%>plugins/bootstrap-table/export/bootstrap.min.js"></script>
	<script src="<%=resource%>plugins/bootstrap-table/export/bootstrap-table.js"></script>
	<script src="<%=resource%>plugins/bootstrap-table/export/bootstrap-table-export.js"></script>
	<script src="<%=resource%>plugins/bootstrap-table/export/tableExport.js"></script>
	<script src="<%=resource%>plugins/bootstrap-table/export/ga.js"></script>

    
	<script src="<%=resource%>plugins/jQuery-slimScroll/jquery.slimscroll.min.js"></script>
	<script src="<%=resource%>plugins/pace/pace.min.js"></script>

	<!-- Bootstrap-Treeview plugin javascript -->
	<script src="<%=resource%>plugins/treeview/bootstrap-treeview.min.js"></script>

	<script src="<%=resource%>plugins/datepicker/js/bootstrap-datepicker.js"></script>

	<!-- Toastr script -->
	<script src="<%=resource%>plugins/toastr/toastr.min.js"></script>
	<!-- 弹窗 -->
	<script src="<%=resource%>plugins/sweetalert-master/sweetalert-dev.js"></script>
	
	<!-- baidu echarts -->
	<script src="<%=resource %>plugins/echarts3/echarts.min(1).js"></script>
	<!-- 引入 vintage 主题 -->
	<script src="<%=resource %>plugins/echarts3/macarons.js"></script>
	<script src="<%=resource %>plugins/echarts3/china.js"></script>
	
    <script src="<%=basePath%>moojs/moo-util-1.js"></script>
	
	<script src="<%=basePath%>moojs/lbs/busNum.js?v=1"></script>

	<script type="text/javascript">
		
	</script>
</body>
</html>