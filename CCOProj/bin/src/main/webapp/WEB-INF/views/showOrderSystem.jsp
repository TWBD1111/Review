<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>接單系統</title>
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
	margin-top: 20px;
}

th, td {
	padding: 10px;
	text-align: center;
	border-bottom: 1px solid #ddd;
}

th {
	background-color: #a3e9a4;
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
	margin-right: 10px;
}

.complete-button {
	background-color: #3498db;
}

.delete-button {
	background-color: #e74c3c;
}

/* 調整"待處理訂單"表格欄位大小 */
table.pending tbody td:nth-child(1), table.pending tbody th:nth-child(1)
	{
	width: 10%;
}

table.pending tbody td:nth-child(2), table.pending tbody th:nth-child(2)
	{
	width: 20%;
}

table.pending tbody td:nth-child(3), table.pending tbody th:nth-child(3)
	{
	width: 30%;
}

table.pending tbody td:nth-child(4), table.pending tbody th:nth-child(4)
	{
	width: 40%;
}

/* 調整"已完成訂單"表格欄位大小 */
table.completed tbody td:nth-child(1), table.completed tbody th:nth-child(1)
	{
	width: 10%;
}

table.completed tbody td:nth-child(2), table.completed tbody th:nth-child(2)
	{
	width: 20%;
}

table.completed tbody td:nth-child(3), table.completed tbody th:nth-child(3)
	{
	width: 20%;
}

table.completed tbody td:nth-child(4), table.completed tbody th:nth-child(4)
	{
	width: 50%;
}

.details-row td {
	background-color: #f4f4f4;
	padding: 10px;
	text-align: left;
}
</style>

<script type="text/javascript"
	src="<c:url value='/webjars/jquery/3.5.1/jquery.js' />"></script>

<script>
	function fetchData() {

		/*  var detailsRow = document.getElementById("details_");
		  detailsRow.style.display === 'none';
		  var detailsRow1 = document.getElementById('details_1');
		  detailsRow1.style.display === 'none'; */

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

		var tableBody = document.getElementById("tableBody");
		tableBody.innerHTML = "";
		var xhr = new XMLHttpRequest();
		xhr.open("GET", "<c:url value='/findorder'/>", true);
		xhr.send();

		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var orders = JSON.parse(xhr.responseText);
				var content = "";

				for (var i = 0; i < 11; i++) {
					content += "<tr>";
					content += "<td>" + (orders[i][0] || "") + "</td>";
					content += "<td>" + (orders[i][3] || "") + "</td>";
					content += "<td>" + (formatDateTime(orders[i][2]) || "")
							+ "</td>";
					content += "<td>";
					content += "<button class='button' onclick='showDetails("
							+ orders[i][0] + ")'>查看品項</button>";
					content += "<button class='button complete-button' onclick='completed("
							+ orders[i][0] + ")'>完成</button>";
					content += "<button class='button delete-button' onclick='deleteOrder("
							+ orders[i][0] + ")'>刪除</button>";
					content += "</td>";
					content += "</tr>";

					// Add an empty row for details, you can toggle its display later
					///   content += "<tr id='details_" + orders[i][0] + "' class='details-row' style='display: none;'>";
					//  content += "<td colspan='4'></td>"; // Adjusted colspan to 4
					//  content += "</tr>";
				}

				tableBody.innerHTML = content;

			}
		};

		/*     var detailsRow = document.getElementById("details_");
		     if (detailsRow.style.display === '') {
		         detailsRow.style.display = 'none'; // Use 'table-row' for proper display
		     } else {
		         detailsRow.style.display = '';
		     }
		     
		     var detailsRow1 = document.getElementById('details_1');
		     if (detailsRow1.style.display === '') {
		         detailsRow1.style.display = 'none'; // Use 'table-row' for proper display
		     } else {
		         detailsRow1.style.display = '';
		     }  */

	}

	function showDetails(orderNo) {
		var xhrr = new XMLHttpRequest();
		xhrr.open("GET", "<c:url value='/findOrderDetailsForAllBasicOrders'/>"
				+ "/" + orderNo, true);

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
					content += "<td>" + (orders[i][3] || "") + "</td>";
					content += "<td>" + (formatDateTime(orders[i][2]) || "")
							+ "</td>";
					content += "<td>";
					content += "<button class='button' onclick='fetchData()'>查看品項</button>";
					content += "<button class='button complete-button' onclick='completed("
							+ orders[i][0] + ")'>完成</button>";
					content += "<button class='button delete-button' onclick='deleteOrder("
							+ orders[i][0] + ")'>刪除</button>";
					content += "</td>";
					content += "</tr>";

				}

				tableBody.innerHTML = content;
			}
		};

		//detailsRow.innerHTML = "";
		var xhr = new XMLHttpRequest();
		xhr
				.open("GET", "<c:url value='/findorderbyID'/>" + "/" + orderNo,
						true);
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

	}

	function completed(orderNo) {
		var confirmComplete = confirm("此訂單是否完成");

		if (confirmComplete) {
			var xhr = new XMLHttpRequest();
			xhr.open("POST", "<c:url value='/copy-from-pending'/>" + "/"
					+ orderNo, true);
			xhr.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");

			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						console.log("Order completed successfully");
						// You might want to update the UI or take additional actions here
						fetchData();
					} else {
						console.error("Failed to complete order");
						// Regardless of success or failure, you might want to refresh the data
						fetchData();
					}
				}
			};

			xhr.send();
		} else {
			fetchData();
		}
	}

	function deleteOrder(orderNo) {
		if (confirm('確定要刪除訂單: ' + orderNo + ' 這筆紀錄?')) {
			var url = "<c:url value='/orderIDdelete' />" + "/" + orderNo;

			var xhr = new XMLHttpRequest();
			xhr.open("POST", url, true);
			xhr.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");

			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						console.log("Order deleted successfully");
						// You might want to update the UI or take additional actions here

						// Perform a synchronous redirect after the deletion is successful
						window.location.href = "/showOrderSystem";
					} else {
						console.error("Failed to delete order");
						// Regardless of success or failure, you might want to refresh the data
						fetchData();
					}
				}
			};

			xhr.send("orderNo=" + orderNo + "&_method=DELETE");
		} else {
			fetchData();
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
		// setInterval(fetchData, 30000); // 每 8 秒更新一次資料
	}
</script>



</head>

<body>
	<header>
		<h1>接單系統</h1>
	</header>

	<table class="container">
		<thead>
			<tr>
				<th>單號</th>
				<th>總額</th>
				<th>客戶送單時間</th>
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
</body>

</html>
