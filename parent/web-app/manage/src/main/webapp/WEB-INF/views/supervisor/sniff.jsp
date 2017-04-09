
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/public.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>嗅探信息</title>

<link
	href="<%=resource%>framework/bootstrap-3.3.4/css/bootstrap.min.css"
	rel="stylesheet">
<link href="<%=resource%>font-awesome/css/font-awesome.min.css"
	rel="stylesheet">

<link href="<%=resource%>css/animate.css" rel="stylesheet">
<link href="<%=resource%>css/style.min.css" rel="stylesheet">

<link href="<%=resource%>plugins/treeview/bootstrap-treeview.min.css"
	rel="stylesheet">
<link href="<%=resource%>plugins/sweetalert-master/sweetalert.css"
	rel="stylesheet">

<link href="<%=resource%>plugins/bootstrap-table/bootstrap-table.css"
	rel="stylesheet">
<link href="<%=resource%>plugins/toastr/toastr.min.css"
	rel="stylesheet">
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
	<div class="wrapper wrapper-content">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
		            <div class="ibox-title">
		                <ol class="breadcrumb">
	                        <li>
	                            <a>主页</a>
	                        </li>
	                        <li>
	                            <strong>嗅探日志</strong>
	                        </li>
	                    </ol>
		            </div>
		            <div class="ibox-content">
						<div id="toolbar" class="well">
							<form id="form-sniff-seach" role="form" class="form-inline">
								<div class="form-group">
									<label for="exampleInputEmail2" class="sr-only">查询条件</label>
									<input name="wordKey" type="text" placeholder="用户MAC / 设备MAC" class="form-control">
								</div>
								<button id="btn-search-sniff" class="btn btn-primary" type="button" data-loading-text="查询中..." autocomplete="off" style="margin-bottom: 0px;">
									&nbsp;查询&nbsp;
								</button>
								<!-- <button class="btn btn-white" type="submit" style="margin-bottom: 0px;">
									&nbsp;删除&nbsp;
								</button> -->
							</form>
						</div>
						<table id="sniff-table"
							data-url="<%=basePath%>system/users.moo"
							data-toolbar="#toolbar" 
							data-side-pagination="server"
							data-pagination="true" 
							data-page-list="[15, 30, 45]"
							data-height="400" 
							data-page-size="15" 
							data-striped="true"
							data-click-to-select="true" 
							data-pagination-h-align="left"
							data-pagination-detail-h-align="right"
							data-classes="table table-hover"
							data-query-params="Sniff.seachFlowParams">
							<thead>
								<th data-field="uAccount">设备MAC</th>
								<th data-field="qqNumber">用户MAC</th>
								<th data-field="phoneNumber">场所编码</th>
								<th data-field="regDate">场所名称</th>
								<th data-field="phoneNumber">时间</th>
								<th data-field="phoneNumber">SSID</th>
								<th data-field="phoneNumber">设备场强</th>
								<th data-field="phoneNumber">加密类型</th>
								<th data-field="phoneNumber">身份类型</th>
							</thead>
						</table>
		            </div>
		        </div>
			</div>
		</div>
	</div>


	<!-- Mainly scripts -->
	<script src="<%=resource%>framework/jquery-2.1.1.min.js"></script>
	<script
		src="<%=resource%>framework/bootstrap-3.3.4/js/bootstrap.min.js"></script>

	<script
		src="<%=resource%>plugins/jQuery-slimScroll/jquery.slimscroll.min.js"></script>
	<script src="<%=resource%>plugins/pace/pace.min.js"></script>

	<!-- Bootstrap-Treeview plugin javascript -->
	<script src="<%=resource%>plugins/treeview/bootstrap-treeview.min.js"></script>
	<script src="<%=resource%>plugins/bootstrap-table/bootstrap-table.js"></script>


	<!-- Toastr script -->
	<script src="<%=resource%>plugins/toastr/toastr.min.js"></script>

	<script src="<%=basePath%>moojs/moo-util-1.js"></script>
	<script src="<%=basePath%>moojs/supervisor/sniff.js?v=1"></script>

</body>
</html>