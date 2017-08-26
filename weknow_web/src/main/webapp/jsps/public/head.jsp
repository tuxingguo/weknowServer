<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="navbar-inner">
	<ul class="nav pull-right">
		<!-- <li><a href="#" class="hidden-phone visible-tablet visible-desktop" role="button">Settings</a></li> -->
		<li id="fat-menu" class="dropdown"><a href="#" role="button"
			class="dropdown-toggle" data-toggle="dropdown"> <i
				class="icon-user"></i> XXX<%-- <%=session.getAttribute("userName") %> --%> <i class="icon-caret-down"></i>
		</a>

			<ul class="dropdown-menu">
				<li class="divider visible-phone"></li>
				<li><a tabindex="-1" href="javascript:window.opener=null;window.open('','_self');window.close();">退出</a></li>
			</ul></li>

	</ul>
	<a class="brand" href="#"><span class="first">重庆理工大学</span>
		<span class="second">计算机科学与工程学院</span></a>
</div>
