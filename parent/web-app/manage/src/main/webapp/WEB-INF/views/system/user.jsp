
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/public.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>用户管理</title>

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
	<div class="wrapper wrapper-content">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<!-- <div class="ibox-title">
						<ol class="breadcrumb">
							<li><a>主页</a></li>
							<li><a>系统设置</a></li>
							<li><strong>系统管理员</strong></li>
						</ol>
					</div> -->
					<div id="toolbar" class="well">
						<form id="form-user-seach" role="form" class="form-inline">
							<div class="form-group">
								<label for="exampleInputEmail2" class="sr-only">用户名</label> <input
									name="keyWord" type="text" placeholder="用户名"
									class="form-control">
							</div>
							<button id="btn-search-user" class="btn btn-sm btn-primary"
								type="button" style="margin-bottom: 0px;">&nbsp;查询&nbsp;</button>
							<button data-toggle="modal" href="#append-user-modal-div"
								class="btn btn-sm btn-primary" type="button"
								style="margin-bottom: 0px;">&nbsp;新增&nbsp;</button>
						</form>
					</div>
					<div class="ibox-content">
						<table id="user-table" data-toolbar="#toolbar"
							data-url="<%=basePath%>system/users.moo"
							data-side-pagination="server" data-pagination="true"
							data-page-list="[15, 30]" data-page-size="15" data-height="400"
							data-striped="true" data-click-to-select="true"
							data-pagination-h-align="left"
							data-pagination-detail-h-align="right"
							data-classes="table table-hover"
							data-query-params="SystemUser.seachFlowParams">
							<thead>
								<th data-field="uName">用户姓名</th>
								<th data-field="uAccount">登录账号</th>
								<th data-field="phoneNumber">手机号码</th>
								<th data-field="roleName">所属角色</th>
								<th data-field="regDate" data-formatter="dateTime_formatter">注册时间</th>
								<th data-field="uId"
									data-formatter="SystemUser.operate_formatter">操作</th>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="append-user-modal-div" class="modal fade" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-10">
							<h3 class="m-t-none m-b">新增管理员</h3>
							<div class="hr-line-dashed"></div>
							<form id="form-append-user" class="form-horizontal">
								<div class="form-group">
									<label class="col-lg-3 control-label">用户姓名：</label>
									<div class="col-lg-8">
										<input name="uName" type="text" placeholder="用户姓名"
											class="form-control validate[required]">
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 control-label">登陆账号：</label>
									<div class="col-lg-8">
										<input name="uAccount" type="text" placeholder="登陆账号"
											class="form-control validate[required]">
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 control-label">手机号码：</label>
									<div class="col-lg-8">
										<input name="phoneNumber" type="text" placeholder="手机号码"
											class="form-control validate[required]">
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 control-label">登陆密码：</label>
									<div class="col-lg-8">
										<div class="input-group">
											<input name="uPassword" type="text" placeholder="登陆密码"
												class="form-control validate[required]">
											<div class="input-group-btn">
												<button id="btn-random" tabindex="-1" class="btn btn-white" type="button">随机</button>
											</div>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 control-label">角色：</label>
									<div class="col-lg-8">
										<div class="input-group">
											<select class="form-control m-b validate[required]" name="roleId">
												<option value="">请选择</option>
												<c:forEach items="${roles }" var="item">
													<option value="${item.roleId }">${item.roleName }</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="col-lg-offset-3 col-lg-8">
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
										<button id="btn-append-user" class="btn btn-sm btn-primary" type="submit">新 增</button>
										<button id="btn-reset" class="btn btn-sm btn-warning" type="button">重 置</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="edit-user-modal-div" class="modal fade" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-10">
							<h3 class="m-t-none m-b">编辑管理员</h3>
							<div class="hr-line-dashed"></div>
							<form id="form-edit-user" class="form-horizontal">
								<div class="form-group">
									<label class="col-lg-3 control-label">用户姓名：</label>
									<div class="col-lg-8">
										<input type="hidden" name="uId">
										<input name="uName" type="text" placeholder="用户姓名"
											class="form-control validate[required]">
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 control-label">手机号码：</label>
									<div class="col-lg-8">
										<input name="phoneNumber" type="text" placeholder="手机号码"
											class="form-control validate[required]">
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 control-label">角色：</label>
									<div class="col-lg-8">
										<div class="input-group">
											<select class="form-control m-b validate[required]" name="roleId">
												<option value="">请选择</option>
												<c:forEach items="${roles }" var="item">
													<option value="${item.roleId }">${item.roleName }</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="col-lg-offset-3 col-lg-8">
										<input type="hidden" name="index">
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
										<button class="btn btn-sm btn-primary" type="submit">提　交</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Mainly scripts -->
	<script src="<%=resource%>framework/jquery-2.1.1.min.js"></script>
	<script src="<%=resource%>framework/bootstrap-3.3.4/js/bootstrap.min.js"></script>

	<script src="<%=resource%>plugins/jQuery-slimScroll/jquery.slimscroll.min.js"></script>
	<script src="<%=resource%>plugins/pace/pace.min.js"></script>

	<!-- Bootstrap-Treeview plugin javascript -->
	<script src="<%=resource%>plugins/treeview/bootstrap-treeview.min.js"></script>
	<!-- Bootstrap-table plugin javascript -->
	<script src="<%=resource%>plugins/bootstrap-table/bootstrap-table.js"></script>

	<!-- Toastr script -->
	<script src="<%=resource%>plugins/toastr/toastr.min.js"></script>
	<!-- 弹窗 -->
	<script src="<%=resource%>plugins/sweetalert-master/sweetalert-dev.js"></script>
	<!-- 表单校验 -->
	<script src="<%=resource%>plugins/validate/js/jquery.validationEngine-zh_CN.js"></script>
	<script src="<%=resource%>plugins/validate/js/jquery.validationEngine.js?v=0.1"></script>

	<script src="<%=basePath%>moojs/moo-util-1.js"></script>
	<script src="<%=basePath%>moojs/system/user.js?v=1"></script>

	<script type="text/javascript">
		
	</script>
</body>
</html>