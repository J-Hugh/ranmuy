 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%@ include file="../../inc/public.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="${_csrf.parameterName}" content="${_csrf.token}"/>
    <title>公告管理</title>

   	<link href="<%=resource %>framework/bootstrap-3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=resource %>font-awesome/css/font-awesome.min.css" rel="stylesheet">
 	<link href="<%=resource %>plugins/iCheck/skins/custom.css" rel="stylesheet">
    <link href="<%=resource %>css/animate.css" rel="stylesheet">
    <link href="<%=resource %>css/style.min.css" rel="stylesheet">

 	<link href="<%=resource %>plugins/sweetalert-master/sweetalert.css" rel="stylesheet">
 	
 	<link href="<%=resource %>plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet">
 	<link href="<%=resource %>plugins/toastr/toastr.min.css" rel="stylesheet">
 	<style type="text/css">
 		.ibox{
 			margin-bottom: 0px;
 		}
 		.wrapper-content{
 			padding-bottom: 0px;
 		}
 	</style>
</head>

<body class="gray-bg">

	<div id="append-notice-div" class="wrapper wrapper-content animated" style="display:none; position: absolute; top: 0px;width: 100%;">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
		            <div class="ibox-title">
		                <h5>发布公告</h5>
		                <div class="ibox-tools">
		                	<a data-toggle="modal" class="btn btn-primary btn-xs" href="#append-menu-modal-div">确认发布</a>
		                    <a id="back-btn" class="btn btn-primary btn-xs" >返回</a>
		                </div>
		            </div>
		             
		            <div class="ibox-content">
						<form method="get" class="form-horizontal">
	                        <div class="form-group">
	                            <label class="col-sm-2 control-label">公告标题</label>
	                            <div class="col-sm-10">
	                                <input type="text" class="form-control">
	                            </div>
	                        </div>
	                        <div class="form-group">
	                            <label class="col-sm-2 control-label">发布范围</label>
								 <div class="col-sm-10">
                                     <label class="radio-inline i-checks">
                                         <input name="range" type="radio" value="option1"> <i></i> 全站</label>
                                     <label class="radio-inline i-checks">
                                         <input name="range" type="radio" value="option2"> <i></i> 部门</label>
                                     <label class="radio-inline i-checks">
                                         <input name="range" type="radio" value="option3"> <i></i> 角色</label>
                                     <label class="radio-inline i-checks">
                                         <input name="range" type="radio" value="option3"> <i></i> 个人</label>
                                 </div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
	                            <label class="col-sm-2 control-label">编辑内容</label>
								 <div class="col-sm-10">
                                 	<textarea id="editor" name="noticeCotent" class="input-xlarge"  style="width:100%; ;height:150px;" rows="3"></textarea>
                                 </div>
							</div>
                        </form>
		            </div>
		        </div>
			</div>
		</div>
	</div>


	<div id="notice-list-div" class="wrapper wrapper-content animated" style="z-index:9999; position: absolute; top: 0px;width: 100%;">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
		            <div class="ibox-title">
		                <h5>所有公告</h5>
		                <div class="ibox-tools">
		                    <a id="append-notice-btn" class="btn btn-primary btn-xs" >发布公告</a>
		                </div>
		            </div>
		             
		            <div class="ibox-content">
						<table id="user-table"
							data-toolbar="#toolbar"
							data-url="<%=basePath %>system/users.moo"
							data-side-pagination="server" 
							data-pagination="true"
							data-page-list="[5, 10, 31]" 
							data-height="400"
							data-show-header="false"
							data-classes="table table-no-bordered table-hover">
							<thead>
								<th data-field="uState" data-formatter="SystemNotice.state_formatter"> 状态</th>
								<th data-field="uName" >用户名称</th>
								<th data-field="uAccount">账号</th>
								<th data-field="qqNumber">QQ</th>
								<th data-field="phoneNumber">手机号码</th>
								<th data-field="regDate" data-formatter="dateTime_formatter">注册时间</th>
								<th data-field="uId" data-align="right" data-formatter="SystemNotice.operate_formatter">操作</th>
							</thead>
						</table>
		            </div>
		        </div>
			</div>
		</div>
	</div>
	

	<!-- Mainly scripts -->
    <script src="<%=basePath%>ueditor/third-party/jquery-1.10.2.min.js"></script>
    <script src="<%=resource%>framework/bootstrap-3.3.4/js/bootstrap.min.js"></script>
  	
  	<script src="<%=resource %>plugins/jQuery-slimScroll/jquery.slimscroll.min.js"></script>
    <script src="<%=resource%>plugins/pace/pace.min.js"></script>
  	
    <script src="<%=resource%>plugins/bootstrap-table/bootstrap-table.js"></script>

    <!-- Toastr script -->
    <script src="<%=resource%>plugins/toastr/toastr.min.js"></script>
    
     <!-- iCheck -->
    <script src="<%=resource%>plugins/iCheck/icheck.min.js"></script>
    
    <script src="<%=basePath%>moojs/moo-util-1.js"></script>
   	<script src="<%=basePath%>/ueditor/ueditor.config.js" type="text/javascript"></script>
	<script src="<%=basePath%>/ueditor/ueditor.all.js" type="text/javascript" ></script>
	
	<script src="<%=basePath%>moojs/system/notice.js?v=1"></script>
	
    <script type="text/javascript">
    $(document).ready(function () {
	    
	    var ue = UE.getEditor('editor');
    });
	</script>
</body>
</html>