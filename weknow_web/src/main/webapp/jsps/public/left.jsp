<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<a href="#dashboard-menu" class="nav-header" data-toggle="collapse"><i
	class="icon-dashboard"></i>操作菜单</a>
<ul id="dashboard-menu" class="nav nav-list collapse in">
	<li><a href="<%=request.getContextPath() %>/admins/main.action">登录管理</a></li>
	<li><a href="<%=request.getContextPath() %>/users/main.action">用户管理</a></li>
	<li><a href="<%=request.getContextPath() %>/news/main.action">新闻管理</a></li>
</ul>

<a href="#accounts-menu" class="nav-header" data-toggle="collapse"><i
	class="icon-briefcase"></i>系统设置</a>
<ul id="accounts-menu" class="nav nav-list collapse">
	<li><a href="<%=request.getContextPath() %>/domains/main.action">区域设置</a></li>
	<li><a href="<%=request.getContextPath() %>/feedbacks/main.action">投诉建议管理</a></li>
	<li><a href="<%=request.getContextPath() %>/account/resetPassword.action">修改密码</a></li>
</ul>
