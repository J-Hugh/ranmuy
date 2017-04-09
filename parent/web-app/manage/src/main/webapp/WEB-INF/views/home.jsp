
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../inc/public.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>LBS后台</title>
	
	<link rel="icon" href="<%=basePath %>/favicon.ico" type="image/x-icon" /> 
	<link rel="shortcut icon" href="<%=basePath %>/favicon.ico" type="image/x-icon" />  
	
	
	<link href="<%=resource %>framework/bootstrap-3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=resource %>font-awesome/css/font-awesome.min.css" rel="stylesheet">

    <!-- Morris -->
    <link href="<%=resource %>plugins/morris/morris.css" rel="stylesheet">

    <!-- Gritter -->
    <link href="<%=resource %>plugins/Gritter/css/jquery.gritter.css" rel="stylesheet">

    <link href="<%=resource %>css/animate.css" rel="stylesheet">
	<link href="<%=resource %>css/style.min.css" rel="stylesheet">
</head>

<!-- <body class="fixed-sidebar full-height-layout gray-bg  pace-done"> -->
<body class="fixed-sidebar full-height-layout gray-bg  pace-done mini-navbar">
    <div id="wrapper">
        <%@ include file="../inc/munu.jsp"%>
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header">
                        <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
                       <!-- <form role="search" class="navbar-form-custom" method="post" action="search_results.html">
                            <div class="form-group">
                                <input type="text" placeholder="请输入您需要查找的内容 …" class="form-control" name="top-search" id="top-search">
                            </div>
                        </form>  -->
                    </div>
                    
                    <ul class="nav navbar-top-links navbar-right" style="margin-top: 7px; margin-left: 20px; font-size: 15px;">
                        <li>
                            <span class="m-r-sm text-muted welcome-message"><a class="count-info" href="index.html" title="返回首页"><i class="fa fa-home"></i></a>&nbsp;上网审计平台</span>
                        </li>
                        <!-- <li class="dropdown">
                            <a class="dropdown-toggle count-info" data-toggle="dropdown" href="index.html#">
                                <i class="glyphicon glyphicon-user"></i> 
                            </a>
                            <ul class="dropdown-menu dropdown-alerts">
                                <li>
                                    <a href="mailbox.html">
                                        <div>
                                            <i class="fa fa-envelope fa-fw"></i> 您有16条未读消息
                                            <span class="pull-right text-muted small">4分钟前</span>
                                        </div>
                                    </a>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <a href="profile.html">
                                        <div>
                                            <i class="fa fa-qq fa-fw"></i> 3条新回复
                                            <span class="pull-right text-muted small">12分钟钱</span>
                                        </div>
                                    </a>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <div class="text-center link-block">
                                        <a href="notifications.html">
                                            <strong>查看所有 </strong>
                                            <i class="fa fa-angle-right"></i>
                                        </a>
                                    </div>
                                </li>
                            </ul>
                        </li> -->
                        
                    </ul>

                </nav>
            </div>
            
            <div class="row content-tabs">
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content" style="margin-left: 0px;">
                        <a href="javascript:;" class="J_menuTab active" data-id="/index.moo">首页</a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                    </button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabShowActive"><a>定位当前选项卡</a>
                        </li>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                        </li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </div>
                <a href="<%=basePath %>logout" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="<%=basePath %>index.moo" frameborder="0" data-id="/index.moo" seamless="" style="display: inline;"></iframe>
            </div>
            <!-- <div class="footer">
                 <div class="pull-right">
                    By：<a href="" target="_blank">neo</a>
                </div>
                <div>
                    <strong>Copyright</strong> mini &copy; 2014
                </div> 
            </div> -->
        </div>
    </div>

    <!-- Mainly scripts -->
    <script src="<%=resource %>framework/jquery-2.1.1.min.js"></script>
    <script src="<%=resource %>framework/bootstrap-3.3.4/js/bootstrap.min.js"></script>
    <script src="<%=resource %>plugins/metisMenu/metisMenu.min.js"></script>
    <script src="<%=resource %>plugins/jQuery-slimScroll/jquery.slimscroll.min.js"></script>
    
     <!-- Custom and plugin javascript -->
    <script src="<%=resource %>js/plus.js"></script>
    <script src="<%=basePath %>H/contabs.min.js"></script>
    
    
    
</body>
</html>