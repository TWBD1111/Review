<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List of Labels</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
	<link rel='stylesheet' href="<c:url value='/css/back-end.css' />" />
	<script type="text/javascript" src="/js/back-end.js"></script>
	<style>
		.table{
			width: 90%;
			text-align: center;
			margin: 0 auto;
		}
		.container-fluid{
		    margin-top: 100px; /* 設置上外邊距為 50 像素，你可以根據需要調整 */
		    width: 90%;
		}
		legend {
			font-weight: bold;
			color: black;
			background-color: white;
			border: 1px solid #cccccc;
			padding: 4px 2px;
		}		
		fieldset {
			font-weight: bold;
			color: black;
			background-color: #ffffcc;
			border: 1px solid #cccccc;
			padding: 4px 2px;
	</style>
    <script>
		 function deleteLabel(ids, empno){
			if (confirm('確定要刪除標籤編號為: ' + empno +' 這筆紀錄?')) {
				let url = "<c:url value='/label/LabelDelete' />" + "/" + ids;
				let input1 = document.createElement("input");
				let input2 = document.createElement("input");
				
				let container = document.forms[0];
                container.appendChild(input1);
                container.appendChild(input2);
                
                input1.type = "text";
                input1.name = "empNo";
                input1.value = empno;
                
                input2.type = "text";
                input2.name = "_method";
                input2.value = "DELETE";
                
                document.forms[0].action=url;
				document.forms[0].method="POST";
				document.forms[0].submit();
                return true;
			 } else {
                exit;
             }
		  }
	</script>
</head>
<body>
	<header>
	   <div class="openButton"></div>
	   <h1>商品客製標籤列表</h1>
	   <ul class="menuBox" style=" padding-left: 0 ;">
	   	   <li>
	       <div class="menu-top">
	           <div class="closeButton"></div>
	       </div>
	       <li>
	           <a class="click1" href="#">第一層</a>
	       <li>
	           <a class="click2" href="#">第一層</a>
	       <li>
	           <a class="click3" href="#">第一層</a>
	    </ul>
	</header>
	<div class='container-fluid'>
	    <div class="d-flex justify-content-end">
	    	<a class='btn btn-success' href="<c:url value='/label/insertLabel' />">新增客製標籤</a>
	   </div>
	<h4 align='center'>${message}&nbsp;</h4>
	<table class='table table-striped table-hover align-middle'>
	    <thead>
	        <tr>
	        	<th>標籤編號</th>
	            <th>標籤名稱</th>
	            <th>價格(元)</th>
	            <th>操作</th>
	        </tr>
	    </thead>
	    <tbody>
	        <c:forEach var='label' items='${labelList}' >          
	        <tr>
	        	<td>${label.labelId}</td>
	            <td>${label.labelName}</td>
	            <td>${label.labelPrice}</td>
	            <td>                                 
	              <a class='btn btn-warning btn-sm' href="<c:url value='/label/findById/${label.id}' />">修改</a>&nbsp;
	              <a class='btn btn-danger btn-sm'  onclick="return deleteLabel('${label.id}','${label.labelId}');">刪除</a>                                 
	            </td>
	        </tr>
	      </c:forEach>
	    </tbody>
	  </table>
	  <p>      
	  <div align='center'>
		<a class='btn btn-outline-primary' href="<c:url value='/' />" role='button'>回首頁</a>
	  </div>
	</div>
    <form action="#" method='POST'>
    </form> 
</body>
</html>