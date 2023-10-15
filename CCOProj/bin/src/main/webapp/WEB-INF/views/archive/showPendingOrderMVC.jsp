<%@ page language="java" contentType="text/html; charset= UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- KDS JSTL + EL Version  -->
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>暫存訂單頁</title>
	<style>
 		body { 
 			font-family: Arial, sans-serif; 
 			margin: 0; 
 			padding: 0; 
			background-color: #f4f4f4; 
 		} 
		
 		.container { 
 			max-width: 800px; 
 			margin: auto; 
			padding: 20px; 
			background-color: white; 
 			box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); 
 			border-radius: 5px; 
 			margin-top: 20px; 
 		} 
		
		table { 
			width: 100%; 
 			border-collapse: collapse; 
		} 
		
 		th, td { 
 			padding: 10px; 
 			text-align: center; 
 			border-bottom: 1px solid #ddd; 
 		} 
		
 		th { 
			background-color: #f4f4f4; 
 		} 
		
 		.complete-button { 
 			background-color: #6dc26d; 
			border: none; 
			color: white; 
 			padding: 8px 16px; 
			text-align: center; 
			text-decoration: none; 
 			display: inline-block; 
 			font-size: 14px; 
 			margin: auto; 
 			cursor: pointer; 
			border-radius: 4px; 
 		} 
		
 		.completed { 
 			background-color: #d3d3d3; 
 		} 
	</style>
</head>
<body>
	<div class="container">
		<table>
    <thead>
        <tr>
            <th>單號</th>
            <th>內用外帶</th>
            <th>品名</th>
            <th>數量</th>
            <th>接單時間</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="Orders" items="${pendingOrdersMVC}">
            <tr>
                <td>${Orders.orderNo}</td>
                <td>${Orders.diningLocation}</td>
                <td>${Orders.ProductName}</td>
                <td>${Orders.foodQuantity}</td>
                <td>${Orders.created_at}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

	</div>
</body>
</html>