<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://cdn.lordicon.com/bhenfmcm.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>

<link rel='stylesheet'
	href="<c:url value='/bootstrap-3.4.1-dist/css/bootstrap.min.css' /> "
	type="text/css" />
<link rel='stylesheet' href="<c:url value='/css/page.css' /> "
	type="text/css" />
<link rel='stylesheet' href="<c:url value='/css/styles.css' /> "
	type="text/css" />

<meta charset="UTF-8">
<title>帳號註冊</title>
</head>
<style>
body {
	overflow-x: hidden;
	overflow-y: hidden;
}

.loginBox {
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.6);
}

.loginContainer h2 {
	text-align: center;
	color: aliceblue;
	margin-top: 180px;
}

.loginContainer p {
	color: white;
	margin: 0 0 5px 0;
}

.loginContainer input {
	width: 100%;
	height: 50px;
	border-radius: 5px;
	display: block;
	font-size: 20px;
	text-indent: 10px;
	color: aliceblue;
	background-color: rgba(240, 248, 255, 0.2);
	border: 0;
	margin-bottom: 20px;
}

.loginContainer .loginButton {
	margin-top: 40px;
}
</style>

<body>
	<div class="headerBackground">
		<div class="loginBox">
			<div class="header">
				<div class="menuButton"></div>
				<div class="menuBox">
					<ul>
						<h1>
							<a href="./index.html">marlinLogo</a>
						</h1>
						<div class="user">
							<div class="menuButtonClose"></div>
						</div>
						<li><a class="click1" href="#">服務介紹
								<div class="downIcon"></div>
						</a>
							<ul>
								<li><a href="./OderSystem.html">接單系統</a></li>
								<li><a href="./Report.html">報表分析</a></li>
							</ul>
						<li><a href="./plan.html">價格方案</a></li>
						<li><a class="click2" href="/contact">立刻諮詢</a></li>
					</ul>
				</div>
				<div class="logo">
					<h1>
						<a href="./index.html">MarlinLogo</a>
					</h1>
				</div>
				<div class="nav">
					<ul>
						<li><a href="#">服務介紹</a>
							<ul>
								<li><a href="./OderSystem.html">接單系統</a></li>
								<li><a href="./Report.html">報表分析</a></li>
							</ul></li>
						<li><a href="./plan.html">價格方案</a></li>
						<li><a href="./contact">立刻諮詢</a></li>
					</ul>
				</div>
			</div>
			<div class="loginContainer pageWrapper">
				<h2>帳號註冊</h2>
				<form id="registrationForm" method="POST">
				    <p>輸入註冊碼</p>
					<input type="text" name="invitationCode" id="invitationCode">
					<p>輸入帳號：</p>
					<input type="text" name="username" id="username">
					<p>輸入密碼：</p>
					<input type="password" name="password" id="password">
					<p>再次輸入密碼：</p>
					<input type="password" name="password" id="password2"> <input
						class="loginButton" type="submit" value="註冊"> <a href="#"></a>
				</form>
				<p id="errorMessage"></p>
				<p id="passwordErrorMessage"></p>
			</div>
		</div>
	</div>
</body>
<script>	
$(document).ready(function() {
	 $("form").submit(function(event) {
		    event.preventDefault(); // 阻止表单的默认提交行为
		    // 获取表单数据
		    var formData = {
		      invitationCode: $("#invitationCode").val(),
		      username: $("#username").val(),
		      password: $("#password").val(),
		      password2: $("#password2").val()
		    };
		    var passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;
		    if (!passwordPattern.test(formData.password)) {
		        $("#errorMessage").text("密码必须包含至少一个小写字母、一个大写字母、一个数字，并且长度至少为8个字符。");
		        return; // 停止继续执行表单提交
		    }
		    // 发送AJAX请求
		    $.ajax({
		        type: "POST",
		        url: "/checkUsername",
		        data: JSON.stringify(formData), // 将数据转换为JSON字符串
		        contentType: "application/json", // 设置请求的内容类型为JSON
		        success: function(response) {
		            if (response.exists) {
		                // 用户名已存在的处理逻辑
		                console.log("Username already exists: " + formData.username);
		                $("#errorMessage").text(response.message);
		            } else {
		                // 用户名可用，继续注册流程
		                console.log("Registration successful for username: " + formData.username);
		                // 提交表单
		                $("form").unbind("submit").submit();
		                window.location.href = response.redirect; // 跳轉到指定的頁面
		            }
		        },
		        error: function() {
		            console.log("Error occurred during username check.");
		        }
		    });
		});
	$(() => {
		console.log("ok")
		// 鼠标经过
		$(".nav>ul>li").mouseover(function () {
			// $(this) jQuery 当前元素  this不要加引号
			// show() 显示元素  hide() 隐藏元素
			$(this).find("ul").stop().show(300);
			console.log("ok")
		});
		// 鼠标离开
		$(".nav>ul").mouseout(function () {
			$(this).find("ul").stop().hide(300);
			console.log("close")
		});

		// 鼠标经过
		$(".menuButton").mouseover(function () {
			if (($(".menuBox").mouseover) != 0) {
				$("body").css("overflow-y", "hidden");
				$("lord-icon").stop().fadeOut(200);
				$(".menuBox").stop().slideDown(300);
			}
		})
		//按鈕點擊
		$(".menuButtonClose").click(function () {
			$("lord-icon").stop().fadeIn(300);
			$(".menuBox").stop().slideUp(300);
		})
		// $(".menuBox").click(function () {
		//     $(".menuBox").stop().hide(300);
		// })

		let click1 = false
		let click2 = false
		let click3 = false
		$(".click1").click(function () {
			// $(this) jQuery 当前元素  this不要加引号
			// show() 显示元素  hide() 隐藏元素
			if (click1) {
				$(this).siblings("ul").stop().hide(300);
				click1 = false
				console.log("click1=false")
			} else {
				$(this).siblings("ul").stop().show(300);
				console.log("click1=true")
				click1 = true
			}
		});

		$(".click2").click(function () {
			// $(this) jQuery 当前元素  this不要加引号
			// show() 显示元素  hide() 隐藏元素
			if (click2) {
				$(this).siblings("ul").stop().hide(300);
				click2 = false
				console.log("click2=false")
			} else {
				$(this).siblings("ul").stop().show(300);
				console.log("click2=true")
				click2 = true
			}
		});

		$(".click3").click(function () {
			// $(this) jQuery 当前元素  this不要加引号
			// show() 显示元素  hide() 隐藏元素
			if (click3) {
				$(this).siblings("ul").stop().hide(300);
				click3 = false
				console.log("click3=false")
			} else {
				$(this).siblings("ul").stop().show(300);
				console.log("click3=true")
				click3 = true
			}
		});
	})
		});	
	/* 
	function validateForm() {
    var password1 = document.getElementById("password1").value;
    var password2 = document.getElementById("password2").value;
    var message = document.getElementById("message");
    if (password1 === password2) {
        message.textContent = "密碼相符";
        return true; // 允許表單提交
    } else {
        message.textContent = "密碼不相符";
        return false; // 禁止表單提交
    }
}
	var registerForm = document.getElementById("registerForm");

	registerForm.addEventListener("submit", function(event) {
	  event.preventDefault();

	  var formData = new FormData(registerForm);
	  var username = formData.get("username");

	  fetch("/checkUsername?username=" + encodeURIComponent(username))
	    .then(function(response) {
	      if (response.ok) {
	        // 帳號可用，繼續進行註冊流程
	        register();
	      } else {
	        // 帳號已存在，顯示錯誤訊息或其他處理邏輯
	        console.log("使用者名稱已存在，請選擇其他使用者名稱");
	      }
	    })
	    .catch(function(error) {
	      console.log("錯誤：" + error);
	    });
	}); */
	
</script>

</html>