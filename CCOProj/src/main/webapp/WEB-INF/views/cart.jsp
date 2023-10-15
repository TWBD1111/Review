<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    <link rel="stylesheet" href="<c:url value='/css/cart.css' />">
    <script src="<c:url value='/js/cart.js' />"></script>
    <title>CCO</title>
</head>
<body>
	<div id="responseContent"></div>
	<div class="container">
        <div class="row">
            <div class="col-sm-10 col-lg-8 col-xl-7 mx-auto">
            	<div class="nocartdiv">
                    <i class="fa-solid fa-cart-shopping" style="font-size: 100px;"></i>
                    <div class="nocarttext">您的購物車中沒有任何餐點</div>
                    <div class="nocarttext viewmeun">瀏覽餐點</div>
                </div>
                <div id="allcartpage">
	                <nav class="divsticky">
	                    <i class="fa-solid fa-angle-left fa-xl" style="cursor: pointer;"></i>
	                    <div>您的購物車</div>
	                </nav>
	                <div style="text-align: center;">
	                    <h2>CCO早餐資展店</h2>
	                </div>
	                <div class="divflex">
	                    <div>
	                        <h4>訂單內容</h4>
	                    </div>
	                    <div class="neworder">
	                        <span>新增餐點</span>
	                    </div>
	                </div>
	                <div id="cartContainer">
	
	                </div>

				<div style="margin: 20px 0;">
					<h4>訂單備註：</h4>
					<div>
						<input type="text" placeholder="新增備註" class="remarkinput">
					</div>

				</div>
				<hr>
				<div class="takeout" style="margin: 20px 0;">
					<h4>用餐方式*</h4>
					<div>
						<label> <input type="radio" name="diningLocationTakeout"
							value="外帶" required> <span class="round button">外帶</span>
						</label> <label> <input type="radio" name="diningLocationInner"
							value="內用" required> <span class="round button">內用</span>
						</label>
					</div>

				</div>
				<hr>
				<h4>付款方式*</h4>
				<div id="payradio">
							<label class="payradioflex"> <input type="radio"
					name="paymentMethod" value="ecPay" id="ecPayRadioButton">
					<div>
						<span class="payment-method-button" id="ecPayButton">綠界支付</span>
						<p>接受國內信用卡與簽帳金融卡</p>
					</div>
				</label> 
<!-- 				<label class="payradioflex"> <input type="radio" -->
<!-- 							name="paymentMethod" value="googlePay" id="googlePayRadioButton"> -->
<!-- 							<div> -->
<!-- 								<span class="payment-method-button" id="googlePayButton">Google -->
<!-- 									Pay</span> -->
<!-- 								<p>將於送出訂單後才會進行扣款</p> -->
<!-- 							</div> -->
<!-- 						</label>  -->
						<label class="payradioflex"> <input type="radio"
					name="paymentMethod" value="localPayment"
					id="localPaymentRadioButton">
					<div>
						<span class="payment-method-button" id="localPaymentButton">現場付款</span>
						<p>送出訂單後，請先至櫃台付款</p>
					</div>
				</label>

				</div>
<!-- 				<div class="payinfo"> -->
<!-- 					<h4>付款人資訊*</h4> -->
<!-- 					<div>刷卡通知將發送到此信箱</div> -->
<!-- 					<div> -->
<!-- 						<input type="text" placeholder="Email" class="remarkinput"> -->
<!-- 					</div> -->

<!-- 				</div> -->
				<div id="allPrice" class=" divflex">
					<h4>總計金額</h4>
					<h4>
						<span>NT$70</span>
					</h4>
				</div>

				<div class="footer1 footersticky" id="submitOrderButton">
					<div>送出訂單</div>
					<span>NT$100</span>
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
	$(document).ready(function() {
	    // 定義一個變量來記錄當前選中的支付方式，默認為現金支付
	    var selectedPaymentMethod = 'localPayment';

	    $('.remarkinput').on('input', function() {
	        var orderNote = $(this).val(); // 獲取輸入字段的值
	        var orderData = JSON.parse(localStorage.getItem('orderData'));

	        // 遍歷 orderData 並為每個項目更新 orderNote
	        for (var i = 0; i < orderData.length; i++) {
	            orderData[i].orderNote = orderNote;
	        }

	        localStorage.setItem('orderData', JSON.stringify(orderData));
	    });

	    // 更新提交訂單按鈕的行為和路徑
	    $('#submitOrderButton').click(function() {
	        console.log('提交訂單按鈕被點擊');
	        var data = localStorage.getItem('orderData');
	        var parsedData = JSON.parse(data);

	        // 檢查必填欄位是否有填寫
	        var takeoutChecked = $('input[name="diningLocationTakeout"]:checked').length > 0;
	        var innerChecked = $('input[name="diningLocationInner"]:checked').length > 0;
	        var ecPayChecked = $('#ecPayRadioButton').is(':checked');
	        var googlePayChecked = $('#googlePayRadioButton').is(':checked');
	        var localPaymentChecked = $('#localPaymentRadioButton').is(':checked');

	        // 清除之前的錯誤訊息
	        $('.takeouterrmsg').remove();
	        $('.payerrmsg').remove();

	        // 如果有未選擇的必填欄位，顯示錯誤訊息
	        if (!takeoutChecked && !innerChecked) {
	            var messageDiv = document.createElement('div');
	            messageDiv.classList.add('takeouterrmsg');
	            messageDiv.textContent = '請選擇用餐方式';
	            var container = document.querySelector('.takeout');
	            container.appendChild(messageDiv);
	            // 阻止表單提交
	            $('html, body').animate({
               scrollTop: $(container).offset().top
           }, 100); // 1000為滾動的時間，以毫秒為單位
	            return false;
	        }

	        if (!ecPayChecked && !googlePayChecked && !localPaymentChecked) {
	            var messageDiv = document.createElement('div');
	            messageDiv.classList.add('payerrmsg');
	            messageDiv.textContent = '請選擇付款方式';
	            var container = document.querySelector('#payradio');
	            container.appendChild(messageDiv);
	            // 阻止表單提交
	            $('html, body').animate({
               scrollTop: $(container).offset().top
           }, 100); // 1000為滾動的時間，以毫秒為單位
	            return false;
	        }

	        // 如果所有必填欄位都已選擇，根據所選支付方式進行提交
	        if ((takeoutChecked || innerChecked) && (ecPayChecked || googlePayChecked || localPaymentChecked)) {
	        	if (ecPayChecked) {
	                selectedPaymentMethod = 'ecPay';
	            } else if (googlePayChecked) {
	                selectedPaymentMethod = 'googlePay';
	            } else if (localPaymentChecked) {
	                selectedPaymentMethod = 'localPayment';
	            }
	        	if (selectedPaymentMethod === 'ecPay') {
	                // 使用AJAX發送到/ecpayCheckout
	                $.ajax({
	                    url: '/ecpayCheckout',
	                    method: 'POST',
	                    contentType: 'application/json',
	                    data: JSON.stringify(parsedData),
	                    success: function(response) {
	                        console.log('綠界支付成功：', response);
	                        $('#responseContent').html(response);
	                    },
	                    error: function(error) {
	                        console.error('綠界支付錯誤：', error);
	                    }
	                });
	            } else if (selectedPaymentMethod === 'localPayment') {
	                // 使用AJAX發送到/processOrder
	                $.ajax({
	                    url: '/processOrder',
	                    method: 'POST',
	                    contentType: 'application/json',
	                    data: JSON.stringify(parsedData),
	                    success: function(response) {
	                        var updatedOrderNo = response.orderNo; // 直接获取orderNo属性值
	                        console.log('現場付款成功，訂單號：', updatedOrderNo);
	                        // 使用updatedOrderNo跳轉到對應的Final頁面
	                        window.location.href = '/final?updatedOrderNo=' + updatedOrderNo;
	                    },
	                    error: function(error) {
	                        console.error('現場付款錯誤：', error);
	                    }
	                });
	            }
	        }
	    });

	    // 內用選項點擊事件
	    $('input[name="diningLocationInner"]').click(function() {
	        console.log('內用選項被點擊');
	        // 清除用餐方式的錯誤訊息
	        $('.takeouterrmsg').remove();
	        // 將外帶選項取消選擇
	        $('input[name="diningLocationTakeout"]').prop('checked', false);
	        // 更新localStorage中的orderData，將"diningLocation"字段設置為"內用"
	        var orderData = JSON.parse(localStorage.getItem('orderData'));
	        for (var i = 0; i < orderData.length; i++) {
	            orderData[i].diningLocation = '內用';
	        }
	        localStorage.setItem('orderData', JSON.stringify(orderData));
	    });

	    // 外帶選項點擊事件
	    $('input[name="diningLocationTakeout"]').click(function() {
	        console.log('外帶選項被點擊');
	        // 清除用餐方式的錯誤訊息
	        $('.takeouterrmsg').remove();
	        // 將內用選項取消選擇
	        $('input[name="diningLocationInner"]').prop('checked', false);
	        // 更新localStorage中的orderData，將"diningLocation"字段設置為"外帶"
	        var orderData = JSON.parse(localStorage.getItem('orderData'));
	        for (var i = 0; i < orderData.length; i++) {
	            orderData[i].diningLocation = '外帶';
	        }
	        localStorage.setItem('orderData', JSON.stringify(orderData));
	    });
	});


	</script>
</body>
</html>