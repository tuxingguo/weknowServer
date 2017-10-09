<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="微闻后台管理平台">
<meta name="author" content="liuhy">
<title>欢迎进入后台管理平台-修改新闻信息</title>
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
<script src="<%=request.getContextPath() %>/js/news/edit.js" type="text/javascript"></script>

</head>
<body>
	<jsp:useBean id="date" class="java.util.Date" />
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
							<form id="tab" method="post" enctype="multipart/form-data">
								<input type="hidden" value="${news.id}" name="id" id="id" />
								<input type=hidden name="contextPath" id="contextPath" value="<%= request.getContextPath()%>">
								<label>标题</label> 
								<input type="text" value="${(empty news.title)?"":news.title}" name="title" id="title" class="input-xlarge" /> 
								<label>内容</label> 
								<input type="text" value="${(empty news.content)?"":news.content}" name="content" id="content" class="input-xlarge" />
								<label>类型</label>
								<input type="text" value="${(empty news.type)?"":news.type}" name="type" id="type" class="input-xlarge" />
								<label>作者id</label>
								<input type="text" value="${(empty news.authorid)?"":news.authorid}" name="authorid" id="authorid" class="input-xlarge" />
								<label>图片</label> 
								<input type="hidden" name="imageurl" value="${news.imageurl } " id="imageurl" class="input-xlarge">
								<c:if test="${news.imageurl!=null&&news.imageurl!=''}">
								
									<img src="${news.imageurl }" width=100 height=100 id="img1"/>
									<br/>
								</c:if>
								<input type="file" name="news_image" id="d1" >
								<input type="button" onclick="fn_image()" value="删除">
								
								<label>音频</label> 
								<input type="hidden" name="audiourl" value="${news.audiourl } " id="audiourl" class="input-xlarge">
								<c:if test="${news.audiourl!=null&&news.audiourl!=''}">
									<audio width="100" height="100" controls="controls" src="${news.audiourl }" ></audio>
									<br/>
								</c:if>
								<input type="file" name="news_audio" />
								<input type="button" onclick="fn_audio()" value="删除">
									
								<label>视频</label> 
								<input type="hidden" name="videourl" value="${news.videourl } " id="videourl" class="input-xlarge">
								<c:if test="${news.videourl!=null&&news.videourl!=''}">
									<video width="100" height="100" controls="controls">
										   <source src="${news.videourl }" type="video/mp4" />
										   <source src="${news.videourl }" type="video/ogg" />
									</video>
									<br/>
								</c:if>
								<input type="file" name="news_video" />
								<input type="button" onclick="fn_video()" value="删除">
								
								<!-- 时间 -->
								<input type="hidden" value="<fmt:formatDate value="${date}" type="both" dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss" />" 
										name="uploadtime" id="uploadtime"/>
								<!-- IP -->
								<input type="hidden" value="<%=request.getLocalAddr() %> " name="uploadip" id="uploadip">
								
								<!-- 位置 -->
								<input type="hidden" value="上海" name="position" id="position">
								<!-- 位置编码-->
								<input type="hidden" value="1" name="addresscode" id="addresscode">
								
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