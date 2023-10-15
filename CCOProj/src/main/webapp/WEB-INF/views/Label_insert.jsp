<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新增客製標籤</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
	<link rel='stylesheet' href="<c:url value='/css/back-end.css' />" />
	<script type="text/javascript" src="/js/back-end.js"></script>
	<style>
		span.error {
			color: red;
			display: inline-block;
			font-size: 11pt;
		}	
		.fieldset-auto-width {
			display: inline-block;
		}
		.container{
		    margin-top: 100px; /* 設置上外邊距為 50 像素，你可以根據需要調整 */
		}
	</style>
</head>
<body>
	<header>
        <div class="back-end-header" style="margin-top: 0;">
            <h1 style=" margin: 0;">新增客製標籤</h1>
            <ul class="back-end-menuBox" style=" padding-left: 0 ; display: none;">
                <div  class="back-end-menu-top"  style="margin-top: 0;" >
                    <div class="back-end-closeButton"></div>
                </div>
                <li>
                    <a href="/BusinessInformation">基本資料</a>
                </li>
                <li>
                    <a class="click1" href="#">商品管理
                        <img id="click1-buutton" class="click-buutton0" src="./img/icon/angle-double-small-down2.png" alt="" >
                    </a>
                    <ul style="padding: 0;">
                        <li><a href="/product">商品總覽</a></li>
                        <li><a href="/category">商品類別管理</a></li>
                        <li><a href="/label">商品客製標籤管理</a></li>
                    </ul>
                </li>
                <li>
                    <a class="click2" href="#">接單系統
                        <img id="click2-buutton" class="click-buutton0" src="./img/icon/angle-double-small-down2.png" alt="">
                    </a>
                    <ul style="padding: 0;">
                        <li><a href="/showOrderSystem">待完成訂單</a></li>
                        <li><a href="/kitchenDisplaySystem">廚房刊版系統</a></li>
                        <li><a href="/showcompletedsystem">歷史訂單</a></li>
                    </ul>
                </li>
                <li>
                    <a class="click3" href="/chart">報表分析</a>
                </li>
                <li>
                    <a class="click3" href="/board">系統公告</a>
                </li>
            </ul>
        </div>
    </header>
    <div class='container text-center'>
    <c:url var='insertUrl' value='/label/insertLabel' />
    <form:form method='POST' modelAttribute="label" action="${insertUrl}">    	
    	<div class='row mb-3'>
            <label class='col-sm-3'>標籤編號</label>
            <div class='col-sm-6'>
                <form:input type='text' class='form-control' path='labelId'  
                       placeholder="請輸入標籤編號" />                                    
            </div>
            <div class='col-sm-3'>
            	<form:errors cssClass="error" path='labelId' />
            </div>
        </div>
    	
        <div class='row mb-3'>
            <label class='col-sm-3'>標籤名稱</label>
            <div class='col-sm-6'>
                <form:input type='text' class='form-control' path='labelName'  
                       placeholder="請輸入標籤名稱  EX:加蛋、加辣、固定冰量、少鹽..." />                                    
            </div>
            <div class='col-sm-3'>
            	<form:errors cssClass="error" path='labelName' />
            </div>
        </div>
        
     	<div class='row mb-3'>
			<label class='col-sm-3'>標籤價格</label>
			<div class='col-sm-6'>
				<form:input type='number' min="0" class='form-control' path='labelPrice'
					placeholder="請輸入標籤價格(若無須加價，可寫 0)" />
			</div>
			<div class='col-sm-3'>
				<form:errors cssClass="error" path='labelPrice' />
			</div>
		</div>
        <c:if test="${not empty exception}">${exception}</c:if>       
        <div class='row mb-3'>
            <div class='offset-sm-3 col-sm-3 d-grid'>
                <button type='submit' class='btn btn-primary'>確認</button>
            </div>
            <div class='col-sm-3 d-grid'>
                <a class='btn btn-outline-primary' href="<c:url value='/label' />" role='button'>離開</a>
            </div>
        </div>
        </form:form>
        <hr>       
    </div>
</body>
</html>
