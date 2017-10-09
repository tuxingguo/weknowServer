<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="微闻后台管理平台">
<meta name="author" content="stu">
<title>欢迎进入后台管理平台-浏览新闻</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/bootstrap/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/bootstrap/stylesheets/theme.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/bootstrap/font-awesome/font-awesome.css">
<script src="<%=request.getContextPath() %>/jquery/jquery-1.7.2.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/main.css">
<script src="<%=request.getContextPath() %>/bootstrap/bootstrap.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/js/main.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/js/news/main.js" type="text/javascript"></script>
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
				<div class="btn-toolbar">
					<button class="btn btn-primary" onclick="onAdd();">
						<i class="icon-plus"></i> 添加新闻
					</button>
					<div class="btn-group"></div>
				</div>
				<div class="well">
					<input type=hidden name="contextPath" id="contextPath" value="<%= request.getContextPath()%>">
					<table class="table">
						<thead>
							<tr>
								<th>序号</th>
								<th>标题</th>
								<th>内容</th>
								<th>类型</th>
								<th>图片</th>
								<th>音频</th>
								<th>视频</th>
								<th>时间</th>
								<th>IP</th>
								<th>作者id</th>
								<th>位置</th>
								<th>位置编码</th>
								<th style="width: 40px;">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pageResult.dataList}" var="data"
								varStatus="count">
								<tr>
									<td><input type="hidden" value="${data.id}" />${(pageResult.pageNo - 1) * pageResult.pageSize + count.index + 1}</td>
									<td>${data.title}</td>
									<td>${data.content}</td>
									<td>${data.type}</td>
									<td>
									<!-- 图片 -->
									<c:if test="${data.imageurl!=null&&data.imageurl!=''}">
										<img src="${data.imageurl }" width=100 height=100 />
									</c:if>
									</td>
									<td>
									<!-- 音频 -->
									<c:if test="${data.audiourl!=null&&data.audiourl!=''}">
										<audio width="100" height="100" controls="controls" src="${data.audiourl }">
										</audio>
									</c:if>
									</td>
									<td>
									<!-- 视频 -->
									<c:if test="${data.videourl!=null&&data.videourl!=''}">
										<video width="100" height="100" controls="controls">
										   <source src="${data.videourl }" type="video/mp4" />
										   <source src="${data.videourl }" type="video/ogg" />
										</video> 
									</c:if>
									</td>
									<td>${data.uploadtime}</td>
									<td>${data.uploadip}</td>
									<td>${data.authorid}</td>
									<td>${data.position}</td>
									<td>${data.addresscode}</td>
									<td><a href="<%=request.getContextPath()%>/news/edit.action?id=${data.id}"><i
											class="icon-pencil"></i></a> <a href="#myModal"
										onclick="onSetId(${data.id});" role="button"
										data-toggle="modal"><i class="icon-remove"></i></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="pagination">
					<input type="hidden" id="pageNo" value="${pageResult.pageNo}" /> <input
						type="hidden" id="pages" value="${pageResult.pages}" /> <input
						type="hidden" id="pageSize" value="${pageResult.pageSize}" />
					<ul>
						<li><a
							${pageResult.pageNo<=1?"":"href='"}<%=request.getContextPath()%>${"/news/main.action?pageNo="}
							${pageResult.pageNo-1} ${"'"}>上一页</a></li>
						<li><a href="#" id="pageInfo">${pageResult.pageNo}/${pageResult.pages}</a></li>
						<li><a
							${pageResult.pageNo>=pageResult.pages?"":"href='"}<%=request.getContextPath()%>${"/news/main.action?pageNo="}
							${pageResult.pageNo+1} ${"'"}>下一页</a></li>
					</ul>
				</div>
				<div class="modal small hide fade" id="myModal" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h3 id="myModalLabel">删除确认</h3>
					</div>
					<div class="modal-body">
						<p class="error-text">
							<i class="icon-warning-sign modal-icon"></i>你确定要删除?
						</p>
					</div>
					<div class="modal-footer">
						<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
						<button class="btn btn-danger" data-dismiss="modal"
							onclick="onDelete();">确定</button>
					</div>
				</div>
				<%@include file="/jsps/public/rightBottom.jsp"%>
			</div>
		</div>
	</div>
</body>
</html>