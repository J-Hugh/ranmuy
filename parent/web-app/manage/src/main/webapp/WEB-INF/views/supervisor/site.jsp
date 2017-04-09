
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/public.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>厂商信息</title>

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
	                            <strong>场所信息</strong>
	                        </li>
	                    </ol>
		            </div>
		            <div class="ibox-content">
						<div id="toolbar" class="well">
							<form id="form-site-seach" role="form" class="form-inline">
								<div class="form-group">
									<label for="exampleInputEmail2" class="sr-only">用户名</label>
									<input name="wordKey" type="text" placeholder="场所编码 / 场所名称" class="form-control">
								</div>
								<button id="btn-search-site" class="btn btn-primary" type="button" data-loading-text="查询中..." autocomplete="off" style="margin-bottom: 0px;">
									&nbsp;查询&nbsp;
								</button>
								<!-- <button class="btn btn-white" type="submit" style="margin-bottom: 0px;">
									&nbsp;删除&nbsp;
								</button> -->
							</form>
						</div>
						<table id="site-table"
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
							data-query-params="Site.seachFlowParams">
							<thead>
								<!-- <th data-checkbox="true"></th> -->
								<!-- <th data-field="uState">编辑</th> -->
								<th data-field="uAccount">所属城市</th>
								<th data-field="qqNumber">场所类型</th>
								<th data-field="phoneNumber">场所编码</th>
								<th data-field="phoneNumber">场所名称</th>
								<th data-field="phoneNumber">经度</th>
								<th data-field="phoneNumber">纬度</th>
								<th data-field="regDate" data-formatter="dateTime_formatter">刷新时间</th>
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
	<script src="<%=basePath%>moojs/supervisor/site.js?v=1"></script>

	<script type="text/javascript">
		
	</script>
</body>
</html>