<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  	
<div class="header">
	<h1 class="page-title">${pageTitle}</h1>
</div>

<ul class="breadcrumb">
	<c:forEach  items="${anchors}" var="anchor" varStatus="status">
		<c:if test="${status.last==false}">
			<li><a href="${anchor.url}">${anchor.desc}</a> <span class="divider">/</span></li>
		</c:if>
		<c:if test="${status.last==true}">
			<li class="active">${anchor.desc}</li>
		</c:if>
	</c:forEach>
</ul>
