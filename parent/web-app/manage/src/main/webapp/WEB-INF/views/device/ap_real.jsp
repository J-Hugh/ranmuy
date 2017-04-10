
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

<link href="<%=resource%>css/style.min.css" rel="stylesheet">

<link href="<%=resource%>plugins/datepicker/css/bootstrap-datepicker3.css" rel="stylesheet">

<link rel="stylesheet" href="<%=resource%>plugins/bootstrap-table/export/bootstrap-table.css">
<link rel="stylesheet" href="<%=resource%>plugins/bootstrap-table/export/examples.css">

<link rel="stylesheet" type="text/css" href="<%=basePath %>page/css/multilevelmenu.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>page/css/component.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>page/css/animations.css" />

<style type="text/css">
.ibox {
	margin-bottom: 0px;
}
.wrapper-content {
	padding-bottom: 0px;
}
.panel-body {
    padding: 0px 0px;
}

.fa-exclamation-triangle {
    position: relative;
    font-size: 20px;
    animation: ease-in-out breath 2500ms infinite alternate;
    -webkit-animation: ease-in-out breath 2500ms infinite alternate;
    cursor: pointer;
    text-shadow: 0 0 5px #fff,  
               0 0 10px #fff,  
               0 0 10px #FF1177;
}

@keyframes breath {
    20% {
		opacity: .2;
    }
    50% {
        opacity: 1;
    }
    70% {
        opacity: .2;
    }
    100% {
        opacity: 1;
    }
}


</style>
</head>

<body class="gray-bg">
	<div id="pt-main" class="pt-perspective">
		<div class="pt-page pt-page-current wrapper wrapper-content " >
			<div class="row">
				<div class="col-sm-12">
					<div class="ibox">
						<div class="ibox-content" style="padding-bottom: 3px;">
							<div class="well" style="margin-bottom: 3px;">
								<form id="form-num-seach" role="form" class="form-inline">
									<div class="form-group">
										<label for="exampleInputEmail2" class="sr-only">城市</label> 
										<select class="form-control" name="par[city]">
										 	<option value="">全部</option>
                                            <option value="1001001">北京</option>
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
										<label for="exampleInputEmail2" class="sr-only">MAC</label> 
										<input name="par[apMac]" type="text" placeholder="设备MAC" class="form-control">
									</div>
									<div class="form-group">
										<label for="exampleInputEmail2" class="sr-only">线路</label> 
										<input name="par[lineName]" type="text" placeholder="线路" class="form-control">
									</div>
									<div class="form-group">
										<label for="exampleInputEmail2" class="sr-only">MAC</label> 
										<input id="data_1" name="par[recordDate]" value="2017-03-09" type="text" placeholder="日期，默认为查询当日" class="form-control">
									</div>
									<button id="btn-search-num" class="btn btn-sm btn-primary"
										type="button" style="margin-bottom: 0px;">&nbsp;查询&nbsp;</button>
									<button id="btn-chart" class="btn btn-sm btn-primary"
										type="button" style="margin-bottom: 0px;">&nbsp;详情&nbsp;</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row" style="margin-left:0px;margin-right:0px; background-color: #fff">
				<div class="col-sm-12">
					<div class="ibox">
						<div class="ibox-content" style="padding: 0px;border-top: 0px;">
							<table id="num-table"
									data-url="<%=basePath%>device_monitor/basic.moo"
									data-side-pagination="server" 
									data-pagination="true"
									data-page-list="[15, 30]" 
									data-page-size="15"
									data-striped="true" data-click-to-select="true"
									data-pagination-h-align="left"
									data-pagination-detail-h-align="right"
									data-classes="table table-hover"
					               	data-query-params="ApReal.seachFlowParams">
								<thead>
									<th data-field="apMac" data-formatter="ApReal.apmac_formatter">设备MAC</th>
									<th data-field="city">城市</th>
									<th data-field="lineName">线路</th>
									<th data-field="onlineTimeLength">开机时长(分)</th>
									<th data-field="moveDistance">位移(米)</th>
									<th data-field="heartbeatTimes">心跳(次)</th>
									<th data-field="gpsTimes">GPS(次)</th>
									<th data-field="connTimes">连网(次)</th>
									<th data-field="id" data-formatter="ApReal.operate_formatter">操作</th>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="pt-page wrapper wrapper-content">
			<div class="row">
				<div class="col-sm-12">
					<div class="ibox">
						<div class="ibox-content"  style="padding-bottom: 3px;">
							<div class="well" style="margin-bottom: 3px;">
								<form id="form-num-chart-seach" role="form" class="form-inline">
									<div class="form-group">
										<label for="exampleInputEmail2" class="sr-only">MAC</label> 
										<input name="par[apmac]" type="text" placeholder="设备MAC" class="form-control">
									</div>
									<div class="form-group">
										<label for="exampleInputEmail2" class="sr-only">MAC</label> 
										<input id="data_1" name="par[date]" value="2016-12-24" type="text" placeholder="日期，默认为查询当日" class="form-control">
									</div>
	                                <button id="btn-search-chart" class="btn btn-sm btn-primary"
										type="button" style="margin-bottom: 0px;">&nbsp;查询&nbsp;</button>
									<button id="btn-back" class="btn btn-sm btn-primary"
										type="button" style="margin-bottom: 0px;">&nbsp;返回&nbsp;</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row" style="margin-left:0px;margin-right:0px; background-color: #fff;height: calc(100% - 60px);">
				<div class="col-lg-3">
					<div class="ibox float-e-margins">
						<div class="ibox-content" style="border: 1px solid #e3e3e3;border-radius: 4px;">
                        	<h4>城市：北京</h4>
                        	<h4>厂商：锐捷</h4>
                        	<h4>线路：110路</h4>
                        	<h4>车内编号：9-32532</h4>
                        	<h4>车牌号：京A4H315</h4>
                        	<hr>
                        	<h4>安装时间：2015-10-21</h4>
                        	<h4>上线时间：2016-10-21</h4>
                        	<h4>设备序列号：MPZBH01925</h4>
                        	<h4>设备型号：BMT2000-30</h4>
                        	
                        	<hr>
                        	<h4>SIM归属地：杭州</h4>
                        	<h4>SIM卡号：15382304145</h4>
                        	
                       	</div>
                   	</div>
				</div>
				<div class="col-sm-9">
					<div class="row">
						<div class="col-sm-12">
							<div class="ibox">
								<div class="ibox-content" style="border: 1px solid #e3e3e3;border-radius: 4px; height: 40%;">
									<div id="day-echart" style="width: 100%; height: 100%;"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="row" style="margin-top: 5px;">
						<div class="col-sm-12">
							<div class="ibox">
								<div class="ibox-content" style="border: 1px solid #e3e3e3;border-radius: 4px; height: calc(60% - 10px);">
									<div id="line-echart" style="width: 100%; height: 100%;"></div>
								</div>
							</div>
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

	<!-- Bootstrap-table plugin javascript -->
	<script src="<%=resource%>plugins/bootstrap-table/bootstrap-table.js"></script>

    
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
    
    
    <script src="<%=basePath %>page/js/modernizr.custom.js"></script>
    
    <script src="<%=basePath%>moojs/moo-util-1.js"></script>
	
	<!-- baidu echarts -->
	<script src="<%=resource%>plugins/echarts3/echarts.min.js"></script>
	<!-- 引入 vintage 主题 -->
	<script src="<%=resource%>plugins/echarts3/macarons.js"></script>
	<script src="<%=resource%>plugins/echarts3/china.js"></script>
	
	<script src="<%=basePath%>moojs/device/apreal.js?v=1"></script>

	<script type="text/javascript">
	</script>
</body>
</html>