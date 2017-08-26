<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="微闻后台管理平台">
<meta name="author" content="liuhy">
<title>欢迎进入后台管理平台-修改管理员信息</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/bootstrap/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/bootstrap/stylesheets/theme.css">
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/bootstrap/font-awesome/font-awesome.css">
<script src="<%=request.getContextPath() %>/jquery/jquery-1.7.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/jquery/jquery.form.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/main.css">
<script src="<%=request.getContextPath() %>/bootstrap/bootstrap.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/js/main.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/js/admins/edit.js" type="text/javascript"></script>
</head>
<body>
	<div class="navbar">
		<%@include file="/jsps/public/head.jsp"%>
	</div>

	<div class="sidebar-nav">
		<%@include file="/jsps/public/left.jsp"%>
	</div>

	<div class="content">
		<%@include file="/jsps/public/rightHead.jsp"%>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="well">
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
							<form id="tab" method="post">
								<input type="hidden" value="${admins.id}" name="id" id="id" />
								<input type=hidden name="contextPath" id="contextPath" value="<%= request.getContextPath()%>">
								<label>用户名</label> 
								<input type="text" value="${admins.username}" name="username" id="username" class="input-xlarge" ${(empty admins.id)?"":"readonly='readonly'" }/> 
								<label>密码</label> 
								<input type="text" value="${admins.userpwd}" name="userpwd" id="userpwd" class="input-xlarge" ${(empty admins.id)?"":"readonly='readonly'" }"/> 
								<div class="btn-toolbar">
									<input type="button" value="保存" id="btnSubmit" class="btn btn-primary" />
								</div>
							</form>
						</div>
					</div>
				</div>
				<%@include file="/jsps/public/rightBottom.jsp"%>
			</div>
		</div>
	</div>
</body>
</html>