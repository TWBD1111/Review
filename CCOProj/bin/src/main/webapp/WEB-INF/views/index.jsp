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
<%-- <link rel='stylesheet' href="<c:url value='/css/styles.css' /> "type="text/css" /> --%>
<link rel="stylesheet" href="./css/style.css">

<meta charset="UTF-8">
<title>Marlin</title>
</head>
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
        </div>
        <div class="banner wrapper">
            <h2>全台最知名的手機菜單支付系統</h2>
            <a href="#no2">
                <lord-icon class="icon" src="https://cdn.lordicon.com/rxufjlal.json" trigger="hover"
                    colors="primary:#ffffff"></lord-icon>
            </a>
        </div>
    </div>
    <div id="no2" class="containerBackground">
        <article>
            <div class="containerBox">
                <div class="container wrapper">
                    <p>Hello 歡迎來到Marlin</p>
                    <p>數位經營革命</p>
                    <p>拉近顧客零距離</p>
                </div>
                <footer>
                    <div class="footerBox">
                        <a href="#"></a>
                        <p><small>@2023_Starpensive</small></p>
                    </div>

                </footer>
            </div>
        </article>

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
            $("body").css("overflow-y", "scroll");
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