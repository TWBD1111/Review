<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage='true'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改類別資料</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
	<link rel='stylesheet' href="<c:url value='/css/back-end.css' />" />
	<script type="text/javascript" src="/js/back-end.js"></script>
	<style type="text/css">
		span.error {
			color: red;
			display: inline-block;
			font-size: 11pt;
		}	
		.fieldset-auto-width {
			display: inline-block;
		}
		.container {
		    margin-top: 100px; /* 設置上外邊距為 50 像素，你可以根據需要調整 */
		}
	</style>
</head>	
	<c:set var="categoryId"  			value = "${category.categoryId}" />
	<c:set var="previous_categoryId"  	value = "${sessionScope.previous_category_id}" />
	<c:set var="categoryName"  		value = "${category.categoryName}" />	
<body>
	<header>
	   <div class="openButton"></div>
	   <h1>修改類別資料</h1>
	   <ul class="menuBox" style=" padding-left: 0 ;">
	   	   <li>
	       <div class="menu-top">
	           <div class="closeButton"></div>
	       </div>
	       <li>
	           <a class="click1" href="#">第一層</a>
	           <!-- 看自已要不要加 -->
	           <!-- <ul>     
	                   <li><a href="#">第二層</a></li>
	                   <li><a href="#">第二層</a></li>
	                   <li><a href="#">第二層</a></li>
	               </ul> -->
	        <li>
	            <a class="click2" href="#">第一層</a>
	        <li>
	            <a class="click3" href="#">第一層</a>
	    </ul>
	</header>
    <div class='container text-center'>
    <c:url var='updateUrl' value='/category/editCategory/${id}' />
    <form:form method='POST' modelAttribute="category" action="${updateUrl}">
    	
    	<div class='row mb-3'>
            <label class='col-sm-3'>類別編號</label>
            <div class='col-sm-6'>
                <input type='text' class='form-control' name='categoryId' value='${categoryId}' />
                <input type='hidden' name='previous_categoryId' value="${previous_categoryId}" >   
                <input type='hidden' name='_method' value="PUT" >                                 
            </div>
            <div class='col-sm-3'>
            	<form:errors cssClass="error" path='categoryId' />
            </div>
        </div>
    	
        <div class='row mb-3'>
            <label class='col-sm-3'>類別名稱</label>
            <div class='col-sm-6'>
                <input type='text' class='form-control' name='categoryName' value="${categoryName}"/>                                    
            </div>
            <div class='col-sm-3'>
            	<form:errors cssClass="error" path='categoryName' />
            </div>
        </div>
        
        <div class='row mb-3'>
            <div class='offset-sm-3 col-sm-3 d-grid'>
                <button type='submit' class='btn btn-primary'>確認</button>
            </div>
            <div class='col-sm-3 d-grid'>
                <a class='btn btn-outline-primary' href="<c:url value='/category' />" role='button'>離開</a>
            </div>
        </div>
        </form:form>        
    </div>
    <hr>
</body>
</html>
