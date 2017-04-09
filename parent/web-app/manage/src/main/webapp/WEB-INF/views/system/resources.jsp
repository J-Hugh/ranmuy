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

    <link href="<%=resource %>css/animate.css" rel="stylesheet">
    <link href="<%=resource %>css/style.min.css" rel="stylesheet">

 	<link href="<%=resource %>plugins/treeview/bootstrap-treeview.min.css" rel="stylesheet">
 	<link href="<%=resource %>plugins/sweetalert-master/sweetalert.css" rel="stylesheet">
 	
 	<link href="<%=resource %>plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet">
 	<link href="<%=resource %>plugins/toastr/toastr.min.css" rel="stylesheet">
 	<link href="<%=resource%>plugins/validate/css/validationEngine.jquery.css?v=0.1" rel="stylesheet">
 	<style type="text/css">
 		.list-group{margin-bottom: 5px;}
 	</style>
</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content">
		<div class="row">
			<div class="col-sm-4">
				<div class=" float-e-margins">
		            <div class="ibox-title">
		                <h5>所有菜单</h5>
		                <div class="ibox-tools">
		                    <a data-toggle="modal" class="btn btn-primary btn-xs" href="#append-menu-modal-div">注册新菜单</a>
		                </div>
		            </div>
		            <div class="ibox-content" style="min-height: calc(100% - 65px);">
						<div id="menu-tree-div" class="test" style="height: auto;" ></div>            
		            </div>
		        </div>
			</div>
			<div class="col-sm-8">
				<div class="row">
					<div class="col-sm-12">
						<div class="ibox float-e-margins">
				            <div class="ibox-title">
				                <h5>基础信息</h5>
				            </div>
				            <div class="ibox-content">
				            	<form id="edit-menu-form" class="form-horizontal">
									<div class="form-group">
										<label class="col-sm-2 control-label">菜单名称</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" name="resourceName">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label">请求路径</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" name="resources">
											<!-- <span class="help-block m-b-none">如果包含子菜单将强行更改为"#"符号</span> -->
										</div>
									</div>
								 	<div class="form-group">
		                                <div class="col-sm-4 col-sm-offset-2">
		                                	<input type="hidden" name="resourceId" >
		                                    <button class="btn btn-primary" type="button" id="edit-menu-btn">保存</button>
		                                    <button class="btn btn-danger" type="button" id="del-menu-btn">删除</button>
		                                </div>
		                            </div>
								</form>
				            </div>
				        </div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="ibox float-e-margins">
				            <div class="ibox-title">
				                <h5>功能列表<small>&nbsp;当前选中菜单下的功能点</small></h5>
				                <div class="ibox-tools">
				                    <a href="projects.html" class="btn btn-primary btn-xs">注册新功能</a>
				                </div>
				            </div>
				            <div class="ibox-content">
								<table id="function-table" data-show-header="false" data-classes="table table-no-bordered table-hover">
									<thead>
										<th data-width="30%" data-field="resourceName">功能名称</th>
										<th data-field="resources">请求路径</th>
										<th data-field="resources" data-formatter="Resource.state_formatter">状态</th>
									</thead>
								</table>
							</div>
				        </div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="append-menu-modal-div" class="modal fade" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-12">
							<h3 class="m-t-none m-b">注册新菜单</h3>
							<form id="append-menu-form" role="form">
								<div class="form-group">
									<label>上级菜单：</label> 
									<select class="form-control m-b" name="pResourceId">
									</select>
								</div>
								<div class="form-group">
									<label>菜单名称：</label> 
									<input type="text" placeholder="菜单名称" class="form-control validate[required]" name="resourceName" />
								</div>
								<div class="form-group">
									<label>请求路径：</label> 
									<input type="text" placeholder="请求路径" class="form-control" name="resources" />
								</div>
								<div>
									<input type="hidden" name="ismenu" value="Y">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									<button id="btn-reg" class="btn btn-sm btn-primary pull-right m-t-n-xs" type="button">
										<strong>注册</strong>
									</button>
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
    
   	<script src="<%=resource%>plugins/sweetalert-master/sweetalert-dev.js"></script>
    
    <script src="<%=resource%>plugins/validate/js/jquery.validationEngine-zh_CN.js"></script>
	<script src="<%=resource%>plugins/validate/js/jquery.validationEngine.js?v=0.1"></script>
    
    <script src="<%=basePath%>moojs/moo-util-1.js"></script>
    <script src="<%=basePath%>moojs/system/resource.js?v=1"></script>
    
    
    <script type="text/javascript">
    $(function(){
    	
    	$(".full-height-scroll").slimScroll({height:"auto",color: '#EFF'});
    	/* width:'100px',//容器宽度,默认无
    	height:'100px',//容器高度,默认250px
    	size:'10px',//滚动条宽度,默认7px
    	position:'left',//滚动条位置,可选值:left,right,默认right
    	color:'#abc',//滚动条颜色,默认#000000
    	alwaysVisible:true,//是否禁用隐藏滚动条,默认false
    	distance:'10px,'//距离边框距离,位置由position参数决定,默认1px
    	start:'.p',//滚动条初始位置,可选值top,bottom,$(selector)--内容元素位置,默认top
    	wheelStep:'12px',滚动条滚动值,默认10px
    	railVisible:true,//滚动条背景轨迹,默认false
    	railColor:'#005612',//滚动条背景轨迹颜色,默认#333333
    	railOpacity:0.8,//滚动条背景轨迹透明度,默认0.2
    	allowPageScroll:true,//滚动条滚动到顶部或底部时是否允许页面滚动,默认false
    	scrollTo:'50px',//跳转到指定的滚动值。可以呼吁任何元素slimScroll已经启用了吗(没试过)
    	scrollBy:'50px'增加/减少当前滚动值由指定的数量(正面或负面)。可以呼吁任何元素slimScroll已经启用(没试过)
    	disableFadeOut:true,//是否禁用鼠标在内容处一定时间不动隐藏滚动条,当设置alwaysVisible为true时该参数无效,默认false
    	touchScrollStep:1000,//可以设置不同的触摸滚动事件的敏感性。负数反转方向滚动,默认200 */
    });
		Resource.init();
	</script>
</body>
</html>