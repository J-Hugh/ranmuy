
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
</style>
<style type="text/css">
html, body {
  margin: 0;
  padding: 0;
  width: 100%;
  /*height: 100%;*/
  background-color: #eee;
  font-family: 'Raleway';
}

ul, li {
  margin: 0;
  padding: 0;
  list-style: none;
}

.icon {
  position: relative;
  width: 32px;
  height: 32px;
  display: block;
  fill: rgba(51, 51, 51, 0.5);
  margin-right: 20px;
  -webkit-transition: all .2s ease-out;
		  transition: all .2s ease-out;
}

.icon.active {
  fill: #E74C3C;
}

.icon.big {
  width: 64px;
  height: 64px;
  fill: rgba(51, 51, 51, 0.5);
}

#wrapper {
  width: 100%;
  height: 100%;
  margin: auto;
  background-color: #fff;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-align: center;
  -webkit-align-items: center;
	  -ms-flex-align: center;
		  align-items: center;
  -webkit-box-pack: left;
  -webkit-justify-content: left;
	  -ms-flex-pack: left;
		  justify-content: left;
  overflow: hidden;
}

#left-side {
  height: 70%;
  width: 25%;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-align: center;
  -webkit-align-items: center;
	  -ms-flex-align: center;
		  align-items: center;
  -webkit-box-pack: center;
  -webkit-justify-content: center;
	  -ms-flex-pack: center;
		  justify-content: center;
}
#left-side ul li {
  padding-top: 10px;
  padding-bottom: 10px;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  line-height: 34px;
  color: rgba(51, 51, 51, 0.5);
  font-weight: 500;
  cursor: pointer;
  -webkit-transition: all .2s ease-out;
		  transition: all .2s ease-out;
}
#left-side ul li:hover {
  color: #333333;
  -webkit-transition: all .2s ease-out;
		  transition: all .2s ease-out;
}
#left-side ul li:hover > .icon {
  fill: #333;
}
#left-side ul li.active {
  color: #333333;
}
#left-side ul li.active:hover > .icon {
  fill: #E74C3C;
}

#border {
  height: 288px;
  width: 1px;
  background-color: rgba(51, 51, 51, 0.2);
}
#border #line.one {
  width: 5px;
  height: 54px;
  background-color: #E74C3C;
  margin-left: -2px;
  margin-top: 35px;
  -webkit-transition: all .4s ease-in-out;
		  transition: all .4s ease-in-out;
}
#border #line.two {
  width: 5px;
  height: 54px;
  background-color: #E74C3C;
  margin-left: -2px;
  margin-top: 89px;
  -webkit-transition: all .4s ease-in-out;
		  transition: all .4s ease-in-out;
}
#border #line.three {
  width: 5px;
  height: 54px;
  background-color: #E74C3C;
  margin-left: -2px;
  margin-top: 143px;
  -webkit-transition: all .4s ease-in-out;
		  transition: all .4s ease-in-out;
}
#border #line.four {
  width: 5px;
  height: 54px;
  background-color: #E74C3C;
  margin-left: -2px;
  margin-top: 197px;
  -webkit-transition: all .4s ease-in-out;
		  transition: all .4s ease-in-out;
}

#right-side {
  height: 300px;
  width: 75%;
  overflow: hidden;
}
#right-side #first, #right-side #second, #right-side #third, #right-side #fourth {
  position: absolute;
  height: 300px;
  width: 75%;
  -webkit-transition: all .6s ease-in-out;
		  transition: all .6s ease-in-out;
  margin-top: -350px;
  opacity: 0;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-align: center;
  -webkit-align-items: center;
	  -ms-flex-align: center;
		  align-items: center;
  -webkit-box-pack: center;
  -webkit-justify-content: center;
	  -ms-flex-pack: center;
		  justify-content: center;
  -webkit-box-orient: vertical;
  -webkit-box-direction: normal;
  -webkit-flex-direction: column;
	  -ms-flex-direction: column;
		  flex-direction: column;
}
#right-side #first h1, #right-side #second h1, #right-side #third h1, #right-side #fourth h1 {
  font-weight: 800;
  color: #333;
}
#right-side #first p, #right-side #second p, #right-side #third p, #right-side #fourth p {
  color: #333;
  font-weight: 500;
  padding-left: 30px;
  padding-right: 30px;
}
#right-side #first.active, #right-side #second.active, #right-side #third.active, #right-side #fourth.active {
  margin-top: 0px;
  opacity: 1;
  -webkit-transition: all .6s ease-in-out;
		  transition: all .6s ease-in-out;
}
</style>
</head>

<body class="gray-bg">
	<div id="pt-main" class="pt-perspective">
		<div class="pt-page wrapper wrapper-content " >
			
		</div>
		<div class="pt-page pt-page-current wrapper wrapper-content" >
			<div class="row">
				<div class="col-sm-12">
					<div class="ibox float-e-margins">
						<div class="ibox-content" style="padding-bottom: 3px;">
							<div id="wrapper">
  <div id="left-side">
	<ul>
	  <li class="choose active">
		<div class="icon active">
		  <svg viewBox="0 0 32 32">
			<g filter="">
			  <use xlink:href="#shopping-cart"></use>
			</g>
		  </svg>
		</div>
		Choose
	  </li>
	  <li class="pay">
		<div class="icon">
		  <svg viewBox="0 0 32 32">
			<g filter="">
			  <use xlink:href="#credit-card"></use>
			</g>
		  </svg>
		</div>
		Pay
	  </li>
	  <li class="wrap">
		<div class="icon">
		  <svg viewBox="0 0 32 32">
			<g filter="">
			  <use xlink:href="#gift"></use>
			</g>
		  </svg>
		</div>
		Wrap
	  </li>
	  <li class="ship">
		<div class="icon">
		  <svg viewBox="0 0 32 32">
			<g filter="">
			  <use xlink:href="#package"></use>
			</g>
		  </svg>
		</div>
		Ship
	  </li>
	</ul>
  </div>

  <div id="border">
	<div id="line" class="one"></div>
  </div>

  <div id="right-side">
	<div id="first" class="active">
	  <div class="icon big">
		<svg viewBox="0 0 32 32">
		  <g filter="">
			<use xlink:href="#shopping-cart"></use>
		  </g>
		</svg>
	  </div>

	  <h1>Choose your gift</h1>

	  <p>适用浏览器：360、FireFox、Chrome、Safari、Opera、傲游、搜狗、世界之窗.<br>不支持IE8及以下浏览器。</p>
	</div>
	<div id="second">
	  <div class="icon big">
		<svg viewBox="0 0 32 32">
		  <g filter="">
			<use xlink:href="#credit-card"></use>
		  </g>
		</svg>
	  </div>

	  <h1>Pay for it</h1>

	  <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec at viverra est, eu finibus mauris. Quisque tempus vestibulum fringilla. Morbi tortor eros, sollicitudin eu arcu sit amet, aliquet sagittis dolor. </p>
	</div>
	<div id="third">
	  <div class="icon big">
		<svg viewBox="0 0 32 32">
		  <g filter="">
			<use xlink:href="#gift"></use>
		  </g>
		</svg>
	  </div>

	  <h1>We will wrap it</h1>

	  <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec at viverra est, eu finibus mauris. Quisque tempus vestibulum fringilla. Morbi tortor eros, sollicitudin eu arcu sit amet, aliquet sagittis dolor. </p>

	</div>
	<div id="fourth">
	  <div class="icon big">
		<svg viewBox="0 0 32 32">
		  <g filter="">
			<use xlink:href="#package"></use>
		  </g>
		</svg>
	  </div>

	  <h1>Ship it</h1>

	  <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec at viverra est, eu finibus mauris. Quisque tempus vestibulum fringilla. Morbi tortor eros, sollicitudin eu arcu sit amet, aliquet sagittis dolor. </p>

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
    
    <!-- baidu echarts -->
	<script src="<%=resource %>plugins/echarts3/echarts.min.js"></script>
	<!-- 引入 vintage 主题 -->
	<script src="<%=resource %>plugins/echarts3/macarons.js"></script>
	<script src="<%=resource %>plugins/echarts3/china.js"></script>
    
    <script src="<%=basePath %>page/js/modernizr.custom.js"></script>
    
    <script src="<%=basePath%>moojs/moo-util-1.js"></script>
	
	

	<script>
$('.choose').click(function () {
	$('.choose').addClass('active');
	$('.choose > .icon').addClass('active');
	$('.pay').removeClass('active');
	$('.wrap').removeClass('active');
	$('.ship').removeClass('active');
	$('.pay > .icon').removeClass('active');
	$('.wrap > .icon').removeClass('active');
	$('.ship > .icon').removeClass('active');
	$('#line').addClass('one');
	$('#line').removeClass('two');
	$('#line').removeClass('three');
	$('#line').removeClass('four');
});
$('.pay').click(function () {
	$('.pay').addClass('active');
	$('.pay > .icon').addClass('active');
	$('.choose').removeClass('active');
	$('.wrap').removeClass('active');
	$('.ship').removeClass('active');
	$('.choose > .icon').removeClass('active');
	$('.wrap > .icon').removeClass('active');
	$('.ship > .icon').removeClass('active');
	$('#line').addClass('two');
	$('#line').removeClass('one');
	$('#line').removeClass('three');
	$('#line').removeClass('four');
});
$('.wrap').click(function () {
	$('.wrap').addClass('active');
	$('.wrap > .icon').addClass('active');
	$('.pay').removeClass('active');
	$('.choose').removeClass('active');
	$('.ship').removeClass('active');
	$('.pay > .icon').removeClass('active');
	$('.choose > .icon').removeClass('active');
	$('.ship > .icon').removeClass('active');
	$('#line').addClass('three');
	$('#line').removeClass('two');
	$('#line').removeClass('one');
	$('#line').removeClass('four');
});
$('.ship').click(function () {
	$('.ship').addClass('active');
	$('.ship > .icon').addClass('active');
	$('.pay').removeClass('active');
	$('.wrap').removeClass('active');
	$('.choose').removeClass('active');
	$('.pay > .icon').removeClass('active');
	$('.wrap > .icon').removeClass('active');
	$('.choose > .icon').removeClass('active');
	$('#line').addClass('four');
	$('#line').removeClass('two');
	$('#line').removeClass('three');
	$('#line').removeClass('one');
});
$('.choose').click(function () {
	$('#first').addClass('active');
	$('#second').removeClass('active');
	$('#third').removeClass('active');
	$('#fourth').removeClass('active');
});
$('.pay').click(function () {
	$('#first').removeClass('active');
	$('#second').addClass('active');
	$('#third').removeClass('active');
	$('#fourth').removeClass('active');
});
$('.wrap').click(function () {
	$('#first').removeClass('active');
	$('#second').removeClass('active');
	$('#third').addClass('active');
	$('#fourth').removeClass('active');
});
$('.ship').click(function () {
	$('#first').removeClass('active');
	$('#second').removeClass('active');
	$('#third').removeClass('active');
	$('#fourth').addClass('active');
});
</script>
</body>
</html>