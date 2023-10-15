<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 抄憲春+薏仁表格 -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>看版系統</title>
	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
	
    <script type="text/javascript" src="./js/back-end.js"></script>
	<link rel="stylesheet" href="./css/back-end.css">
	<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
   <style>


table {
	table-layout: fixed;
	text-align: center;
	width: 100%;
	border-collapse: collapse;
	
}

th {
	font-size: 36px;
	background-color: #f4f4f4;
	padding: 10px;
	border-bottom: 1px solid #ddd;
	
}

td {
	font-size: 20px;
	padding-top: 2%;
	
	
}



.container {
	max-width: 1300px;
	margin: auto;
	padding: 20px;
	background-color: white;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	border-radius: 5px;
	margin-top: 100px;
	z-index: 0;
}



.form-container {
	display: flex;
	align-items: flex-end;
}


</style>
	
	
	
</head>
<body>
	<header>
        <div class="openButton"></div>
        <h1>廚房看版系統</h1>
        <ul class="menuBox" style=" padding-left: 0 ;">
            <div class="menu-top">
                <div class="closeButton"></div>
            </div>
            <li>
                <a class="click1" href="./chart">報表分析</a>
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
	<div class="container" id='somedivS'>
	</div>
<script>
	window.onload = function() {
	var stompClient = null;
	console.log("WORKING....")
	function connect() {
	    var socket = new SockJS('/websocket');
	    stompClient = Stomp.over(socket);
	    stompClient.connect({}, function(frame) {
	        console.log('Connected TEST1: ' + frame); 
	        stompClient.subscribe('/topic/pendingOrders', function(data) {
	            console.log("成功訂閱");
	        	var orders = JSON.parse(data.body);
	            console.log('Received Data TEST 2:', orders);
	            var content = "<table>";
	            content += "<tr ><th>單號</th><th>內用外帶</th>"
	                    + "<th>品名</th><th>客製化</th><th>產品備註</th><th>數量</th>"
	                    + "<th>接單時間</th></tr>";

	            var previousOrderNo = null;  // 確認上一筆訂單的 OrderNo
	            var backgroundColor = "#12424d"; // 初始色
	            var textColor = "#FFFFFF"

	            for (var i = 0; i < orders.length; i++) {
	                orders[i].labelName = orders[i].labelName || "無";
	                orders[i].foodNote = orders[i].foodNote || "無";

	                // OrderNo 與上一筆訂單不同，更改背景顏色
	                if (orders[i].orderNo !== previousOrderNo) {
	                    backgroundColor = (backgroundColor === "#f4f4f4") ? "#12424d" : "#f4f4f4";
	                    textColor = (textColor === "#000000") ? "#FFFFFF" : "#000000";
	                }

	                content += "<tr style='background-color:" + backgroundColor + "; color:" + textColor + "'>"
	                        + "<td>"
	                        + orders[i].orderNo
	                        + "</td>"
	                        + "<td>"
	                        + orders[i].diningLocation
	                        + "</td>"
	                        + "<td>"
	                        + orders[i].productName
	                        + "</td>"
	                        + "<td>"
	                        + orders[i].labelName
	                        + "</td>"
	                        + "<td>"
	                        + orders[i].foodNote
	                        + "</td>"
	                        + "<td>"
	                        + orders[i].foodQuantity
	                        + "</td>"
	                        + "<td>"
	                        + orders[i].created_at
	                        + "</td>"
	                        + "</tr>";

	                previousOrderNo = orders[i].orderNo;
	            }

	            content += "</table>";
	            var divs = document.getElementById("somedivS");
	            divs.innerHTML = content;
	            console.log("DONE 123");
	            
	        });
	        // 在連線成功後自動觸發訂閱操作
	        stompClient.send("/app/sendMessage", {}, "");
	    });
	}

	connect();
	}	
	</script>
</body>
</html>
