<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>歷史訂單查詢</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f4f4f4;
}

header {
	background-color: #35424a;
	color: white;
	padding: 10px;
	text-align: center;
}

.container {
	max-width: 900px;
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

.webpage2 {
	display: none;
}

th, td {
	padding: 10px;
	text-align: center;
	border-bottom: 1px solid #ddd;
}

th {
	background-color: #a3e9a4;
}

.accept-button {
	text-align: center;
}

.completed {
	background-color: #d1ffd8;
}

.pending {
	background-color: white;
}

/* 調整"待處理訂單"表格欄位大小 */
table.pending tbody td:nth-child(1), table.pending tbody th:nth-child(1)
	{
	width: 10%;
}

table.pending tbody td:nth-child(2), table.pending tbody th:nth-child(2)
	{
	width: 20%; /* 调整總額的比例 */
}

table.pending tbody td:nth-child(3), table.pending tbody th:nth-child(3)
	{
	width: 30%; /* 调整客戶送單時間的比例 */
}

table.pending tbody td:nth-child(4), table.pending tbody th:nth-child(4)
	{
	width: 40%; /* 调整操作的比例 */
}

/* 調整"已完成訂單"表格欄位大小 */
table.completed tbody td:nth-child(1), table.completed tbody th:nth-child(1)
	{
	width: 10%;
}

table.completed tbody td:nth-child(2), table.completed tbody th:nth-child(2)
	{
	width: 20%; /* 调整總額的比例 */
}

table.completed tbody td:nth-child(3), table.completed tbody th:nth-child(3)
	{
	width: 20%; /* 调整完成时间的比例 */
}

table.completed tbody td:nth-child(4), table.completed tbody th:nth-child(4)
	{
	width: 50%; /* 调整操作的比例 */
}

.button-group {
	display: flex;
	justify-content: center;
}

.button {
	background-color: #6dc26d;
	border: none;
	color: white;
	padding: 8px 16px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 14px;
	cursor: pointer;
	border-radius: 4px;
	margin-right: 10px; /* 調整按鈕之間的右邊距 */
}

.complete-button {
	background-color: #3498db;
}

.delete-button {
	background-color: #e74c3c;
}

div {
	text-align: center;
	margin-top: 10px;
}

div label, div input, div button {
	margin: 5px;
}
</style>

<script type="text/javascript"
	src="<c:url value='/webjars/jquery/3.5.1/jquery.js' />"></script>


<script>
	var fetchDataIntervalId;
	function fetchData(page, orderNo, startDate, endDate) {
		// Default values if not provided
		page = page || 1;
		orderNo = orderNo || "";
		startDate = startDate || "";
		endDate = endDate || "";

		var detailsRow = document.getElementById("details_");
		if (detailsRow.style.display === '') {
			detailsRow.style.display = 'none'; // Use 'table-row' for proper display
		} else {
			detailsRow.style.display = 'none';
		}

		var detailsRow1 = document.getElementById('details_1');
		if (detailsRow1.style.display === '') {
			detailsRow1.style.display = 'none'; // Use 'table-row' for proper display
		} else {
			detailsRow1.style.display = 'none';
		}

		var lastPageButton = document.getElementById('lastpage');
		var nextPageButton = document.getElementById('nextpage');

		if (lastPageButton.style.display === 'none'
				&& nextPageButton.style.display === 'none') {
			lastPageButton.style.display = '';
			nextPageButton.style.display = '';
		} else {
			lastPageButton.style.display = '';
			nextPageButton.style.display = '';
		}

		var xhr = new XMLHttpRequest();
		xhr.open("GET", "<c:url value='/findCompletedOrder'/>?page=" + page
				+ "&orderNo=" + orderNo + "&startDate=" + startDate
				+ "&endDate=" + endDate, true);
		xhr.send();

		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var orders = JSON.parse(xhr.responseText);
				var content = "";

				for (var i = 0; i < 11; i++) {
					content += "<tr>";
					content += "<td>" + (orders[i][0] || "") + "</td>";
					content += "<td>" + (orders[i][4] || "") + "</td>";
					content += "<td>" + formatDateTime(orders[i][2] || "")
							+ "</td>";
					content += "<td>" + formatDateTime(orders[i][3] || "")
							+ "</td>";
					content += "<td>";
					content += "<button class='button' onclick='showDetails("
							+ orders[i][0] + ")'>查看品項</button>";
					content += "</td>";
					content += "</tr>";
				}

				var tableBody = document.getElementById("tableBody");
				tableBody.innerHTML = content;
			}
		};
	}

	function showDetails(orderNo) {
		clearInterval(fetchDataIntervalId);
		var xhrr = new XMLHttpRequest();
		xhrr.open("GET",
				"<c:url value='/findCompletedDetailsForAllBasicOrders'/>" + "/"
						+ orderNo, true);

		xhrr.send();

		var tableBody = document.getElementById("tableBody");
		tableBody.innerHTML = "";

		xhrr.onreadystatechange = function() {
			if (xhrr.readyState == 4 && xhrr.status == 200) {
				var orders = JSON.parse(xhrr.responseText);
				var content = "";

				for (var i = 0; i < orders.length; i++) {
					content += "<tr>";
					content += "<td>" + (orders[i][0] || "") + "</td>";
					content += "<td>" + (orders[i][4] || "") + "</td>";
					content += "<td>" + formatDateTime(orders[i][2] || "")
							+ "</td>";
					content += "<td>" + formatDateTime(orders[i][3] || "")
							+ "</td>";
					content += "<td>";
					content += "<button class='button' onclick='fetchData()')'>查看品項</button>";
					content += "</td>";
					content += "</tr>";

				}

				tableBody.innerHTML = content;
			}
		};

		//detailsRow.innerHTML = "";
		var xhr = new XMLHttpRequest();
		xhr.open("GET", "<c:url value='/findCompletedDetailsByOrderNo'/>" + "/"
				+ orderNo, true);
		xhr.send();
		xhr.onreadystatechange = function() {

			if (xhr.readyState == 4 && xhr.status == 200) {
				var details = JSON.parse(xhr.responseText);
				var detailsRow = document.getElementById("details_");

				// Assuming details is an array of details for the given orderNo
				var content = "";

				for (var i = 0; i < details.length; i++) {
					content += "<tr>";
					content += "<td>" + (details[i][10] || "") + "</td>";

					content += "<td>" + (details[i][5] || "") + "</td>";

					content += "<td>" + (details[i][4] || "") + "</td>";
					content += "</tr>";
				}

				// Update the content of detailsRow
				if (detailsRow) {
					detailsRow.innerHTML = content;

					// Toggle the display
					if (detailsRow.style.display === 'none') {
						detailsRow.style.display = ''; // Use 'table-row' for proper display
					} else {
						detailsRow.style.display = 'none';
					}
				}
			}
		};

		// Toggle the display
		var detailsRow1 = document.getElementById('details_1');
		if (detailsRow1.style.display === 'none') {
			detailsRow1.style.display = ''; // Use 'table-row' for proper display
		} else {
			detailsRow1.style.display = 'none';
		}

		var lastPageButton = document.getElementById('lastpage');
		var nextPageButton = document.getElementById('nextpage');

		if (lastPageButton.style.display === ''
				&& nextPageButton.style.display === '') {
			lastPageButton.style.display = 'none';
			nextPageButton.style.display = 'none';
		} else {
			lastPageButton.style.display = '';
			nextPageButton.style.display = '';
		}

	}

	function formatDateTime(dateTimeString) {
		var dateTime = new Date(dateTimeString);
		var year = dateTime.getFullYear();
		var month = ('0' + (dateTime.getMonth() + 1)).slice(-2);
		var day = ('0' + dateTime.getDate()).slice(-2);
		var hours = ('0' + dateTime.getHours()).slice(-2);
		var minutes = ('0' + dateTime.getMinutes()).slice(-2);
		var seconds = ('0' + dateTime.getSeconds()).slice(-2);

		return year + '-' + month + '-' + day + ' ' + hours + ':' + minutes
				+ ':' + seconds;
	}

	// <----------------------------------------------------------------------------------->

	window.onload = function() {
		
		fetchData();
		fetchDataIntervalId = setInterval(fetchData, 80000);

	}
</script>




</head>

<body>
	<header>
		<h1>訂單查詢</h1>
	</header>
	<div>
		<label for="orderNo">訂單號碼:</label> <input type="text" id="orderNo"
			name="orderNo"> <label for="startDate">起始日:</label> <input
			type="date" id="startDate" name="startDate"> <label
			for="endDate">迄日:</label> <input type="date" id="endDate"
			name="endDate">

		<button class="button"
			onclick="fetchData(1, document.getElementById('orderNo').value, document.getElementById('startDate').value, document.getElementById('endDate').value)">查詢</button>
	</div>
	<table class="container">
		<thead>
			<tr>
				<th>單號</th>
				<th>總額</th>
				<th>送單時間</th>
				<th>完成時間</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="tableBody">
			<!-- 動態生成表格行 -->
		</tbody>
	</table>

	<table id="details_1" class="container" style='display: none;'>
		<thead>
			<tr>
				<th>品名</th>
				<th>數量</th>
				<th>備註</th>
			</tr>
		</thead>
		<tbody id="details_" style='display: none;'>
			<!-- 動態生成表格行 -->
		</tbody>
	</table>
	<div class="button-group">
		<button id="lastpage" class="button" onclick="fetchData(1)"
			style='display:;'>上一頁</button>
		<button id="nextpage" class="button" onclick="fetchData(2)"
			style='display:;'>下一頁</button>
	</div>
</body>

</html>
