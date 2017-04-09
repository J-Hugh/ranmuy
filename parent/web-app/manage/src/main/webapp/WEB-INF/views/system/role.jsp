 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%@ include file="../../inc/public.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>角色管理</title>

   	<link href="<%=resource %>framework/bootstrap-3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=resource %>font-awesome/css/font-awesome.min.css" rel="stylesheet">

 	<link href="<%=resource %>plugins/jstree/themes/default/style.min.css" rel="stylesheet">
    <link href="<%=resource %>css/animate.css" rel="stylesheet">
    <link href="<%=resource %>css/style.min.css" rel="stylesheet">

 	<link href="<%=resource %>plugins/sweetalert-master/sweetalert.css" rel="stylesheet">
 	
 	<link href="<%=resource %>plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet">
 	<link href="<%=resource %>plugins/toastr/toastr.min.css" rel="stylesheet">
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
		            <div class="ibox-title">
		               <ol class="breadcrumb">
	                        <li>
	                            <a>主页</a>
	                        </li>
	                        <li>
	                            <a>系统设置</a>
	                        </li>
	                        <li>
	                            <strong>角色管理</strong>
	                        </li>
	                    </ol>
		            </div>
		            <div id="toolbar" class="well">
				       	<form role="form" class="form-inline">
                            <button data-toggle="modal" href="#append-role-modal-div" class="btn btn-sm btn-primary" type="button" style="margin-bottom: 0px;">&nbsp;新增&nbsp;</button>
                        </form>
				    </div>
		            <div class="ibox-content">
						<table id="role-table"
							data-toolbar="#toolbar"
							data-url="<%=basePath %>system/roles.moo"
							data-side-pagination="server"
							data-pagination="true" 
							data-page-list="[15, 30, 45]"
							data-height="400" 
							data-page-size="15" 
							data-striped="true"
							data-click-to-select="true" 
							data-pagination-h-align="left"
							data-pagination-detail-h-align="right"
							data-classes="table table-hover">
							<thead>
								<th data-field="roleName" >角色名称</th>
								<th data-field="roleCode">编码</th>
								<th data-field="number">人数</th>
								<th data-field="creName">创建人</th>
								<th data-field="creDate" data-formatter="dateTime_formatter">创建时间</th>
								<th data-formatter="SystemRole.operate_formatter">操作</th>
							</thead>
						</table>
		            </div>
		        </div>
			</div>
		</div>
	</div>

	<%-- <div id="edit-role-modal-div" class="modal fade" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-12">
							<h3 class="m-t-none m-b">编辑角色信息</h3>
							<form id="edit-role-form" action="<%=basePath %>system/abc.moo" method="post" role="form">
								<div class="form-group">
									<label>角色名称：</label> 
									<input type="text" placeholder="角色名称" class="form-control">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								</div>
								<div class="form-group">
									<label>编码：</label> 
									<input type="text" placeholder="编码：" class="form-control">
								</div>
								<div>
									<button class="btn btn-sm btn-primary pull-right m-t-n-xs" type="submit">
										<strong>保存</strong>
									</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div> --%>
	
	<div id="edit-power-modal-div" class="modal fade" aria-hidden="true" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-12">
							<h3 class="m-t-none m-b">权限设置</h3>
							<div class="full-height-scroll">
							<div id="resource-tree-div"></div>
							</div>
							<div>
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								<button class="btn btn-sm btn-primary pull-right m-t-n-xs" id="edit-role-resource-btn" type="button">
									<strong>保存</strong>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="append-role-modal-div" class="modal fade" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-10">
							<h3 class="m-t-none m-b">新增角色</h3>
							<div class="hr-line-dashed"></div>
							<form id="form-append-role" class="form-horizontal">
								<div class="form-group">
									<label class="col-lg-3 control-label">角色名：</label>
									<div class="col-lg-8">
										<input name="roleName" type="text" placeholder="角色名"
											class="form-control validate[required]">
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 control-label">编码：</label>
									<div class="col-lg-8">
										<div class="input-group">
											<input name="roleCode" type="text" placeholder="编码"
												class="form-control validate[required]">
											<div class="input-group-btn">
												<button id="btn-random" tabindex="-1" class="btn btn-white" type="button">随机</button>
											</div>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="col-lg-offset-3 col-lg-8">
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
										<button class="btn btn-sm btn-primary" type="submit">新 增</button>
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

	<!-- Mainly scripts -->
    <script src="<%=resource%>framework/jquery-2.1.1.min.js"></script>
    <script src="<%=resource%>framework/bootstrap-3.3.4/js/bootstrap.min.js"></script>
  	
  	<script src="<%=resource %>plugins/jQuery-slimScroll/jquery.slimscroll.min.js"></script>
    <script src="<%=resource%>plugins/pace/pace.min.js"></script>
  	
  	<!-- Bootstrap-Treeview plugin javascript -->
    <script src="<%=resource%>plugins/treeview/bootstrap-treeview.min.js"></script>
    <script src="<%=resource%>plugins/bootstrap-table/bootstrap-table.js"></script>
    
    <!-- Toastr script -->
    <script src="<%=resource%>plugins/toastr/toastr.min.js"></script>
    
   	<!-- 表单校验 -->
	<script src="<%=resource%>plugins/validate/js/jquery.validationEngine-zh_CN.js"></script>
	<script src="<%=resource%>plugins/validate/js/jquery.validationEngine.js?v=0.1"></script>
    
    <!-- jsTree plugin javascript -->
    <script src="<%=resource%>plugins/jstree/jstree.js?v=0.3"></script>
    
   	<script src="<%=resource%>plugins/sweetalert-master/sweetalert-dev.js"></script>
    
    <script src="<%=basePath%>moojs/moo-util-1.js"></script>
    
    <script src="<%=basePath%>moojs/system/role.js"></script>
    
</body>
</html>