<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../inc/public.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>登录</title>

    <link href="<%=resource %>framework/bootstrap-3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=resource %>font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=resource %>css/animate.css" rel="stylesheet">
    <link href="<%=resource %>css/style.min.css" rel="stylesheet">

</head>

<body class="gray-bg">

    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div>
                <!-- <h1 class="logo-name">&nbsp;</h1> -->
            </div>
            <h3>欢迎使用 MINImoo</h3>
            <h4>
            	<div class="panel-heading">
                	<h3 class="panel-title">
                   		<c:if test="${not empty error}">
						<div class="error">${error}</div>
						</c:if>
						<c:if test="${not empty msg}">
						<div class="msg">${msg}</div>
						</c:if>
					</h3>
                </div>
            </h4>
            <form role="form" name='loginForm' action="<c:url value='/checklogin' />" method="post" >
                <div class="form-group">
                    <input type="text" name="username" class="form-control" placeholder="用户名" required="">
                </div>
                <div class="form-group">
                    <input type="password" name="password" class="form-control" placeholder="密码" required="">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <input type="hidden" name="questionId" value="${questionId}" />
                </div>
                <div class="form-group">
					<div class="input-group">
						<input name="randKey" type="text" placeholder="验证码"
							class="form-control validate[required]">
						<div class="input-group-btn">
							 <img src="<c:url value="/servlet/random"/>" id="verificationCodeImg" alt="验证码" />
						</div>
					</div>
				</div>
                <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>
				<p class="text-muted text-center"> 账号密码均为"【名字全拼】111" </p>
                <!-- <p class="text-muted text-center"> <a href="login.html#"><small>忘记密码了？</small></a> | <a href="register.html">注册一个新账号</a> </p>-->
            </form>
        </div>
    </div>

    <!-- Mainly scripts -->
    <script src="<%=resource %>framework/jquery-2.1.1.min.js"></script>
    <script src="<%=resource %>framework/bootstrap-3.3.4/js/bootstrap.min.js"></script>
</body>

</html>
