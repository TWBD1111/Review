<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
	<link rel='stylesheet' href="<c:url value='/css/back-end.css' />" />
	<script type="text/javascript" src="/js/back-end.js"></script>
</head>
<body>
	待統整登祥網站
	<div align='center'>
		<h1>商品管理總覽</h1>
		<h3>使用Spring Data JPA技術</h3>
	    <hr>
	    <p><a class='btn  btn-primary btn-lg' href="<c:url value='/product' />">所有商品列表</a></p>
	    <p><a class='btn btn-primary btn-lg' href="<c:url value='/category' />">商品類別列表</a></p>
	    <p><a class='btn btn-primary btn-lg' href="<c:url value='/label' />">商品客製標籤列表</a></p>
	</div>
</body>
</html>