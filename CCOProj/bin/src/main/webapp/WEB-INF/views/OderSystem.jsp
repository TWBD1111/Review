 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
 <script src="https://cdn.lordicon.com/bhenfmcm.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>

<link rel='stylesheet' href="<c:url value='/bootstrap-3.4.1-dist/css/bootstrap.min.css' /> "type="text/css" />
<link rel='stylesheet' href="<c:url value='/css/styles.css' /> "type="text/css" />
<link rel='stylesheet' href="<c:url value='/css/page.css' /> "type="text/css" />

<meta charset="UTF-8">
<title>OderSystem</title>
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
        margin-top: 80px;
    }

    .loginContainer p {
        font-size: 20px;
        color: white;
        margin: 20px 0 5px 0;
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
    <div class="headerBackground headerBackgroundCenter">
        <div class="pageBox">
            <div class="header">
                <div class="menuButton"></div>
                <div class="menuBox">
                    <ul>
                        <h1><a href="./index.html">marlinLogo</a></h1>
                        <div class="user">
                            <div class="menuButtonClose"></div>
                        </div>
                        <li>
                            <a class="click1" href="#">服務介紹
                                <div class="downIcon"></div>
                            </a>
                            <ul>
                                <li><a href="./OderSystem.html">接單系統</a></li>
                                <li><a href="./Report.html">報表分析</a></li>
                            </ul>
                        </li>
                        <li><a href="./plan.html">價格方案</a></li>
                        <li><a class="click2" href="/contact">立刻諮詢</a></li>
                        <li><a class="click3" href="./login.html">登入</a></li>
                    </ul>
                </div>
                <div class="logo">
                    <h1><a href="./index.html">MarlinLogo</a></h1>
                </div>
                <div class="nav">
                    <ul>
                        <li><a href="./login.html">登入</a></li>
                        <li><a href="#">服務介紹</a>
                            <ul>
                                <li><a href="./OderSystem.html">接單系統</a></li>
                                <li><a href="./Report.html">營業報表分析</a></li>
                            </ul>
                        </li>
                        <li><a href="./plan.html">價格方案</a></li>
                        <li><a href="/contact">立刻諮詢</a></li>
                    </ul>
                </div>
            </div>
            <div class="loginContainer pageWrapper">
                <h2>接單管理</h2>
                <p>透過接單機輕鬆一鍵接單／拒單，或是上／下架商品，讓你不再掉單、漏單</p>
                <p>「預約訂單」功能可幫助店家事前備料、準備訂單，避免單量過大而影響出單</p>
                <p>同時支援 Android／iOS 系統，一台平板就能接單、出單、上下架商品，完成訂單大小事</p>
            </div>
        </div>
    </div>
</body>
<script>
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
</script>

</html>