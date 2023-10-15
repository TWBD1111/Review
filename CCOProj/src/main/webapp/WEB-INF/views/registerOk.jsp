<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
 <script src="https://cdn.lordicon.com/bhenfmcm.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>

<link rel='stylesheet' href="<c:url value='/bootstrap-3.4.1-dist/css/bootstrap.min.css' /> "type="text/css" />
<link rel='stylesheet' href="<c:url value='/css/page.css' /> "type="text/css" />
<link rel='stylesheet' href="<c:url value='/css/style.css' /> "type="text/css" />

<meta charset="UTF-8">
<title>login</title>
</head>
<style>
    body {
        overflow-x: hidden;
        overflow-y: hidden;
    }

    .loginWrapper {
        width: 450px;
        margin: 0 auto;
    }

    .loginBox {
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.6);
    }

    .loginContainer h2 {
        text-align: center;
        color: aliceblue;
        margin-top: 100px;
    }

    details {
        margin: 0 auto 10px;
        width: 400px;
    }

    summary {
        height: 50px;
        display: flex;
        /*讓題目與加號水平並列，以及重設預設值的三角箭頭*/
        justify-content: space-between;
        /*配置於左右兩側*/
        align-items: center;
        /*配置在垂直居中處*/
        padding: 20px 30px;
        font-size: 15px;
        background-color: #d6d6d6;
        border-radius: 10px;
        cursor: pointer;
        /*於滑鼠游標移入時顯示pointer*/
    }

    summary::-webkit-details-marker {
        /*重設Webkit網頁瀏覽器的三角箭頭*/
        display: none;
    }

    summary:hover,
    details[open] summary {
        /*滑鼠游標移入題目與選單展開之後的顯示方式*/
        background-color: #bbb;
    }

    summary::after {
        /*利用偽元素指定加號*/
        content: '+';
        margin-left: 10px;
        color: #5b8f8f;
        font-size: 25px;
        transition: transform .5s;
        /*指定選單展開時的特效*/
    }

    details[open] summary::after {
        /*指定加號展開之後的顯示方式*/
        transform: rotate(45deg);
        /*旋轉45度*/
    }

    .answer {
        padding: 20px;
        line-height: 1.6;
    }

    .answer p{
        color: aliceblue;
    }

    details[open] .answer {
        animation: fadein .5s ease;
        /*指定展開後的特效*/
    }

    @keyframes fadein {
        0% {
            opacity: 0;
        }

        100% {
            opacity: 1;
        }
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
            <div class="loginContainer loginWrapper">
                <h2>註冊成功</h2>
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
    $(document).ready(function(){
     $("#contain").ipsum();
});
  
</script>

</html>