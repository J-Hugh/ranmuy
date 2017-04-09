
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
	<div id="list" class="wrapper wrapper-content animated" >
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
								<div class="form-group">
									<label for="exampleInputEmail2" class="sr-only">城市</label> 
										<select class="form-control" name="par[city]">
										 	<option value="">全部</option>
                                            <option value="beijing">北京</option>
                                            <option value="shanghai">上海</option>
                                            <option value="guangzhou">广州</option>
                                            <option value="shenzhen">深圳</option>
                                            <option value="kunming">昆明</option>
                                            <option value="changsha">长沙</option>
                                            <option value="fuzhou">福州</option>
                                            <option value="tianjin">天津</option>
                                            <option value="foshan">佛山</option>
                                        </select>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail2" class="sr-only">月份</label> 
									<select class="form-control" name="par[year]">
                                        <option value="2016">2016</option>
                                        <option value="2017" selected="selected">2017</option>
                                        <option value="2018">2018</option>
                                    </select>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail2" class="sr-only">月份</label> 
									<select class="form-control" name="par[month]">
                                        <option selected="selected" value="1">一月</option>
                                        <option value="2">二月</option>
                                        <option value="3">三月</option>
                                        <option value="4">四月</option>
                                        <option value="5">五月</option>
                                        <option value="6">六月</option>
                                        <option value="7">七月</option>
                                        <option value="8">八月</option>
                                        <option value="9">九月</option>
                                        <option value="10">十月</option>
                                        <option value="11">十一月</option>
                                        <option value="12">十二月</option>
                                    </select>
								</div>
								
								<button id="btn-search-num" class="btn btn-sm btn-primary"
									type="button" style="margin-bottom: 0px;">&nbsp;查询&nbsp;</button>
								<button id="btn-down" class="btn btn-sm btn-primary"
									type="button" style="margin-bottom: 0px;">&nbsp;下载&nbsp;</button>
								<button id="btn-chart" class="btn btn-sm btn-primary"
									type="button" style="margin-bottom: 0px;">&nbsp;统计图&nbsp;</button>
							</form>
						</div>
						
						<table id="num-table"
								data-url="<%=basePath%>lbs/busnum.moo"
								data-side-pagination="server" data-height="400"
								data-striped="true" data-click-to-select="true"
								data-pagination-h-align="left"
								data-pagination-detail-h-align="right"
								data-classes="table table-hover"
				               	data-query-params="BusNum.seachFlowParams">
							<thead>
								<th data-field="dateStr">日期</th>
								<th data-field="busNum">车辆数</th>
								<th data-field="city">城市</th>
								<th data-field="company">购买商</th>
							</thead>
						</table>
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
	<!-- 表单校验 -->
	<script src="<%=resource%>plugins/validate/js/jquery.validationEngine-zh_CN.js"></script>
	<script src="<%=resource%>plugins/validate/js/jquery.validationEngine.js?v=0.1"></script>
    
    <script src="<%=basePath%>moojs/moo-util-1.js"></script>
	
	<script src="<%=basePath%>moojs/lbs/busNum.js?v=1"></script>

	<script type="text/javascript">
		
	</script>
</body>
</html>