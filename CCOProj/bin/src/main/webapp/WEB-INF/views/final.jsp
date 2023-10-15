<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<title>CCO</title>
<style>
html, body {
	height: 100%;
}

.cardheader {
	border-bottom: 2px solid #39c0ed;
}

.productitem {
	display: flex;
	align-items: center;
	justify-content: space-between;
	cursor: pointer;
}

.row img {
	max-height: 100%;
	max-width: 100%;
}

.footer1 {
	left: 0;
	bottom: 0;
	width: 100%;
	height: 50px;
	margin: 5px 0;
	padding: 10px;
	color: white;
	border: 0;
	background-color: #1d1e20;
	color: #fff;
	border-radius: 10px;
	cursor: pointer;
	display: flex;
	justify-content: center;
	align-items: center;
}

.footer1 div {
	flex: 1;
	text-align: center;
}

.footer1 span {
	margin-left: auto;
}

.divflex {
	display: flex;
}

.footersticky {
	/* position: -webkit-sticky; */
	/* position: sticky; */
	bottom: 0;
	z-index: 1000;
}

.ordercmp {
	color: #10da68;
	font-size: 50px;
	margin: 40px;
	text-align: center;
}

.orderinfo, h2 {
	text-align: center;
	font-weight: bold;
}
</style>
</head>
<script>
	$(document).ready(function() {
		// 获取更新后的订单号
		var updatedOrderNo = "${updatedOrderNo}";

		// 如果存在更新后的订单号
		if (updatedOrderNo) {
			// 从 localStorage 中获取 OrderItem 数据
			var orderItem = localStorage.getItem("orderData");

			// 如果订单数据存在
			if (orderItem) {
				// 将 OrderItem 重新命名为 HistoryOrderItem
				localStorage.setItem("historyOrderData", orderItem);

				// 删除原始的 OrderItem
				localStorage.removeItem("orderData");
			}
		}

		// 绑定点击事件，跳转回首页
		$('.footer1').click(function() {
			window.location.href = '/main';
		});
	});
</script>

<body>
	<h2 id="orderNoPlaceholder"></h2>

	<div class="container">
		<div class="row">
			<div class="col-sm-10 col-lg-8 col-xl-7 mx-auto">

				<div style="margin: 10px;">
					<h2>CCO早餐資展店</h2>
				</div>
				<!-- 店頭圖片 -->
				<img src="<c:url value='/img/點餐頁面.jpg' />">
				<div class="ordercmp">
					<i class="fa-regular fa-circle-check fa-bounce">訂單完成</i>
				</div>
				<div class="orderinfo">
					<div>感謝您的訂購，以下是您的單號</div>
					<div style="margin: 20px; font-size: 36px;">
						<span>${diningLocation}</span> &nbsp; <span>${updatedOrderNo}</span>
						<br> <span>總金額:${orderPrice}</span>
						<div>祝您用餐愉快！</div>
					</div>
				</div>
				<div class="footer1 footersticky">
					<div class="footer1 footersticky">
						<div>返回首頁</div>
					</div>
				</div>
			</div>

		</div>

		<script
			src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
			integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
			crossorigin="anonymous"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"
			integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa"
			crossorigin="anonymous"></script>
		<script src="https://kit.fontawesome.com/ed6fd4cc97.js"
			crossorigin="anonymous"></script>
		<script>
			
		</script>
</body>

</html>