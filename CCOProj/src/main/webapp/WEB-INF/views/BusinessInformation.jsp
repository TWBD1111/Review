<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://cdn.lordicon.com/bhenfmcm.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>

<script type="text/javascript" src="./js/back-end.js"></script>
<link rel='stylesheet'
	href="<c:url value='/bootstrap-3.4.1-dist/css/bootstrap.min.css' /> "
	type="text/css" />
<link rel='stylesheet' href="<c:url value='/css/styles.css' /> "
	type="text/css" />
<link rel='stylesheet' href="<c:url value='/css/back-end.css' /> "
	type="text/css" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>店家基本資料</title>
<style>
body {
	/* font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f4f4f4; */
}

.container {
	max-width: 800px;
	margin: auto;
	padding: 20px;
	background-color: white;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	border-radius: 5px;
	margin-top: 100px;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	padding: 10px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

th {
	background-color: #f4f4f4;
	width: 30%;
}

input[type="text"], input[type="password"] {
	width: 100%;
	padding: 8px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

table span {
	color: red;
}

.save-button, .cancel-button {
	background-color: #35424a;
	color: white;
	border: none;
	padding: 5px 10px;
	font-size: 12px;
	cursor: pointer;
	border-radius: 4px;
	margin-right: 5px;
}

.change-password-button {
	background-color: #6dc26d;
	color: white;
	border: none;
	padding: 5px 10px;
	font-size: 12px;
	cursor: pointer;
	border-radius: 4px;
}

.password-change-box {
	display: none;
	margin-top: 10px;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
}
</style>
</head>
<body>
	 <header>
        <div class="back-end-header" style="margin-top: 0;">
            <div class="back-end-openButton"></div>
            <h1 style=" margin: 0;">店家基本資料</h1>
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
	<div class="container">
		<form id="StoreInfoPasswordForm" action="StoreInfoPassword"
			method="POST">
			<table>
				<tr>
					<th>帳號</th>
					<td><p id="userName" style="margin: 0; font-size: 20px; color: black"></p></td>
				</tr>
				<tr>
					<th>變更密碼</th>
					<td><input type="password" id="newPassword" placeholder="新密碼"></td>
				</tr>
				<tr>
					<th>確認新密碼</th>
					<td><input type="password" id="confirmNewPassword"
						placeholder="確認新密碼"></td>
				</tr>
				<tr>
					<td colspan="2">
						<button type="submit" class="save-button" style="background-color: #6dc26d ">儲存新密碼</button>
						<span style="color: black" id="passwordErrorMessage"></span>
					</td>
				</tr>
			</table>
		</form>
		<form id="BusinessInformationForm" action="StoreInfoServlet"
			method="POST">
			<table>
				<tr>
					<th>店家名稱</th>
					<td colspan="2"><input type="text" id="storeName" value="示例店家"></td>
				</tr>
				<tr>
					<th>地址</th>
					<td colspan="2"><input type="text" id="storeAddress"
						value="台灣示例市示例區示例路123號"></td>
				</tr>
				<tr>
					<th>聯絡人</th>
					<td colspan="2"><input type="text" id="contactPerson"
						value="聯絡人姓名"></td>
				</tr>
				<tr>
					<th>電話號碼</th>
					<td colspan="2"><input type="text" id="phoneNum"
						value="123-456-789"></td>
				</tr>
				<tr>
					<td colspan="3">
					<button type="submit" class="save-button">儲存</button>
					<span style="color: black" id="errorMessage"></span></td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
	/* $("#passwordErrorMessage").text("密码必须包含至少一个小写字母、一个大写字母、一个数字，并且长度至少为8个字符。"); */
		$(document).ready(function() {
			$.ajax({
					type : "GET",
					url : "/getBusinessInfo",
					contentType : "application/json",
					success : function(response) {
						// 更新輸入框的值
						$("#userName").text(response.userName);
						$("#storeName").val(response.storeName);
						$("#storeAddress").val(response.storeAddress);
						$("#contactPerson").val(response.contactPerson);
						$("#phoneNum").val(response.phoneNum);
						$("#errorMessage").text(response.errorMessage);
					},
					error : function() {							
						console.log("Error fetching business information");		
					}
			});
		$("#StoreInfoPasswordForm").submit(function(event) {
			event.preventDefault(); // 阻止表单的默认提交行为
						// 获取表单数据
		var formData = {
			newPassword : $("#newPassword").val(),
			confirmNewPassword : $("#confirmNewPassword").val()
		};
		var passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;
		if (formData.newPassword === formData.confirmNewPassword){
			if (!passwordPattern.test(formData.newPassword)) {
				$("#passwordErrorMessage").text("密码必须包含至少一个小写字母、一个大写字母、一个数字，并且长度至少为8个字符。");
				return; // 停止继续执行表单提交  
			}else{
				// 发送AJAX请求
				$.ajax({
					type : "POST",
					url : "/UpdateByPassword",
					data : JSON.stringify(formData), // 将数据转换为JSON字符串
					contentType : "application/json", // 设置请求的内容类型为JSON
					success : function(passwordResponse) {	
						$("#passwordErrorMessage").text(passwordResponse.passwordErrorMessage);
						},error : function() {
							console.log("Error fo password");
						}
					});
			}
		}else{
			$("#passwordErrorMessage").text("密码不一致");
			return; // 停止继续执行表单提交  
		}
		});
						
		$("#BusinessInformationForm").submit(function(event) {
			event.preventDefault(); // 阻止表单的默认提交行为
			// 获取表单数据
			var formData = {
				storeName : $("#storeName").val(),
				storeAddress : $("#storeAddress").val(),
				contactPerson : $("#contactPerson").val(),
				phoneNum : $("#phoneNum").val()
			};
			// 发送AJAX请求
			$.ajax({
				type : "POST",
				url : "/StoreInfoServlet",
				data : JSON.stringify(formData), // 将数据转换为JSON字符串
				contentType : "application/json", // 设置请求的内容类型为JSON
				success : function(StoreInfoServletResponse) {
					$("#errorMessage").text(StoreInfoServletResponse.errorMessage);
					/* window.location.href = StoreInfoServletResponse.redirect; // 跳轉到指定的頁面 */
				},error : function() {
					console.log("Error fo information");
				}
				});
			});
});
	</script>
	<!-- <script>
       window.addEventListener('DOMContentLoaded', () => {
            populateInputsFromLocalStorage();
            /* populateInputsFromLocalStorage(): 此函數用於從本地存儲中獲取資料，並將其填充到對應的輸入欄位 */
        });

        function populateInputsFromLocalStorage() {
            const storeNameInput = document.getElementById('storeName');
            const storeAddressInput = document.getElementById('storeAddress');
            const storePhoneInput = document.getElementById('storePhone');
            const storeHoursInput = document.getElementById('storeHours');
            const storeUrlInput = document.getElementById('storeUrl');

            storeNameInput.value = localStorage.getItem('storeName') || '';
            storeAddressInput.value = localStorage.getItem('storeAddress') || '';
            storePhoneInput.value = localStorage.getItem('storePhone') || '';
            storeHoursInput.value = localStorage.getItem('storeHours') || '';
            storeUrlInput.value = localStorage.getItem('storeUrl') || '';
        }
       /*  saveData(): 儲存所有的商店信息到本地存儲。 */
        function saveData() {
            const storeName = document.getElementById('storeName').value;
            const storeAddress = document.getElementById('storeAddress').value;
            const storePhone = document.getElementById('storePhone').value;
            const storeHours = document.getElementById('storeHours').value;
            const storeUrl = document.getElementById('storeUrl').value;

           
            localStorage.setItem('storeName', storeName);
            localStorage.setItem('storeAddress', storeAddress);
            localStorage.setItem('storePhone', storePhone);
            localStorage.setItem('storeHours', storeHours);
            localStorage.setItem('storeUrl', storeUrl);

            
            populateInputsFromLocalStorage();

            
            console.log('資料已儲存');
        }
        /* updateDisplayedData(): 從本地存儲更新顯示的商店信息。 */
        function updateDisplayedData() {
           
            const storeNameInput = document.getElementById('storeName');
            const storeAddressInput = document.getElementById('storeAddress');
            const storePhoneInput = document.getElementById('storePhone');
            const storeHoursInput = document.getElementById('storeHours');
            const storeUrlInput = document.getElementById('storeUrl');

            const storeName = localStorage.getItem('storeName') || '';
            const storeAddress = localStorage.getItem('storeAddress') || '';
            const storePhone = localStorage.getItem('storePhone') || '';
            const storeHours = localStorage.getItem('storeHours') || '';
            const storeUrl = localStorage.getItem('storeUrl') || '';

            storeNameInput.value = storeName;
            storeAddressInput.value = storeAddress;
            storePhoneInput.value = storePhone;
            storeHoursInput.value = storeHours;
            storeUrlInput.value = storeUrl;
        }
        /* showChangePasswordBox(): 顯示用於變更密碼的輸入欄位。 */
        function showChangePasswordBox() {
            const newPasswordRow = document.getElementById('newPasswordRow');
            const confirmNewPasswordRow = document.getElementById('confirmNewPasswordRow');
            const passwordChangeButtonRow = document.getElementById('passwordChangeButtonRow');

            newPasswordRow.style.display = 'table-row';
            confirmNewPasswordRow.style.display = 'table-row';
            passwordChangeButtonRow.style.display = 'table-row';
        }
       /*  cancelPasswordChange(): 隱藏變更密碼的輸入欄位。 */
        function cancelPasswordChange() {
            const newPasswordRow = document.getElementById('newPasswordRow');
            const confirmNewPasswordRow = document.getElementById('confirmNewPasswordRow');
            const passwordChangeButtonRow = document.getElementById('passwordChangeButtonRow');

            newPasswordRow.style.display = 'none';
            confirmNewPasswordRow.style.display = 'none';
            passwordChangeButtonRow.style.display = 'none';
        }
       
        /* togglePasswordVisibilityOnMouseDown(inputId) 和 togglePasswordVisibilityOnMouseUp(): 
        這兩個函數用於在按下和釋放鼠標按鈕時切換密碼輸入欄位的可見性。 */
        function togglePasswordVisibilityOnMouseDown(inputId) {
            const input = document.getElementById(inputId);
            input.type = 'text';
        }
        function togglePasswordVisibilityOnMouseUp() {
            const newPasswordInput = document.getElementById('newPassword');
            const confirmNewPasswordInput = document.getElementById('confirmNewPassword');
            if (!newPasswordInput.contains(document.activeElement) && !confirmNewPasswordInput.contains(document.activeElement)) {
                newPasswordInput.type = 'password';
                confirmNewPasswordInput.type = 'password';
            }
        }
        /* changePassword(): 檢查新密碼和確認新密碼是否相符，如果是則進行密碼變更。 */
        function changePassword() {
            const newPassword = document.getElementById('newPassword').value;
            const confirmNewPassword = document.getElementById('confirmNewPassword').value;
            if (newPassword !== confirmNewPassword) {
                alert('新密碼與確認新密碼不相符');
                return;
            }
            console.log('密碼已變更');
            const newPasswordRow = document.getElementById('newPasswordRow');
            const confirmNewPasswordRow = document.getElementById('confirmNewPasswordRow');
            const passwordChangeButtonRow = document.getElementById('passwordChangeButtonRow');
            newPasswordRow.style.display = 'none';
            confirmNewPasswordRow.style.display = 'none';
            passwordChangeButtonRow.style.display = 'none';
        }
    </script> -->
</body>
</html>