 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar-default navbar-static-side" role="navigation">
   <div class="sidebar-collapse">
       <ul class="nav" id="side-menu">
           <li class="nav-header">

               <div class="dropdown profile-element"> <span>
                   <img alt="image" class="img-circle" src="<%=resource %>img/profile_small.jpg" />
                    </span>
                   <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
                       <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">${user.uName }</strong>
                    </span>  <span class="text-muted text-xs block">超级管理员 <b class="caret"></b></span> </span>
                   </a>
                   <ul class="dropdown-menu animated fadeInRight m-t-xs">
                       <li><a href="form_avatar.html">修改头像</a>
                       </li>
                       <li><a href="profile.html">个人资料</a>
                       </li>
                       <li><a href="contacts.html">联系我们</a>
                       </li>
                       <li><a href="mailbox.html">信箱</a>
                       </li>
                       <li class="divider"></li>
                       <li><a href="<%=basePath %>logout">安全退出</a>
                        </li>
                    </ul>
                </div>
                <div class="logo-element">
                    V+
                </div>
           </li>
           <c:forEach items="${menuTree }" var="item">
           		<li>
	           		<c:if test="${empty item.children }">
	                    <a class="J_menuItem" href="<%=basePath %>${item.resources }" data-index="${item.resourceId }"><i class="fa ${item.icon }"></i> <span class="nav-label">${item.resourceName }</span></a>
	           		</c:if>
	                <c:if test="${!empty item.children }">
	                	<a href="${item.resources }"><i class="fa ${item.icon }"></i> <span class="nav-label">${item.resourceName }</span><span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<c:forEach items="${item.children }" var="item1">
		                	<li><a class="J_menuItem" href="<%=basePath %>${item1.resources}" data-index="${item.resourceId }">${item1.resourceName }</a></li>
		                    </c:forEach>
		                </ul>
	                </c:if>
	            </li>
           </c:forEach>
        </ul>
    </div>
</nav>
