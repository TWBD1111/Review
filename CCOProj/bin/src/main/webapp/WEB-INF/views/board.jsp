<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>系統公告</title>
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

body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f4f4f4;
}

.announcement {
	background-color: #ffffff;
	border: 1px solid #ddd;
	padding: 20px;
	margin-bottom: 20px;
}

.announcement-category {
	font-size: 18px;
	font-weight: bold;
}

.announcement-content {
	margin-top: 10px;
}

.announcement-date {
	color: #888;
	margin-top: 10px;
}

.pagination {
    text-align: center;
    margin-top: 20px;
    justify-content: space-between
}

.pagination a {
    margin: 0 5px; /* 为分页链接添加左右间距 */
    text-decoration: none; /* 去除链接的下划线 */
    color: #007bff; /* 链接的颜色，你可以根据需要自定义 */
}

/* 可以选择性地添加样式来突出当前页和总页数 */
#currentPage, #totalPages {
    font-weight: bold;
}

option {
	text-align: center;
}

</style>
<script>
$(() => {
    const announcementsPerPage = 5;
    const totalAnnouncements = ${boardData.size()} || 1;
    const totalPages = Math.ceil(totalAnnouncements / announcementsPerPage);
    const pageSelector = document.getElementById('pageSelector');
    
    let currentPage = 1;
	
    //限制頁面資料顯示筆數
    function updateAnnouncements() {
        const startIndex = (currentPage - 1) * announcementsPerPage;
        const endIndex = startIndex + announcementsPerPage;

        $(".announcement").hide();
        $(".announcement").slice(startIndex, endIndex).show();

        $("#currentPage").text("目前頁數: " + currentPage);
        $("#totalPages").text("總頁數: " + totalPages);
    }

    // 處理選擇器的事件
    $("#pageSelector").change(function() {
        const selectedPage = parseInt($(this).val());
        if (!isNaN(selectedPage) && selectedPage >= 1 && selectedPage <= totalPages) {
            currentPage = selectedPage;
            updateAnnouncements();
        }else{console.log("PAGE ERROR")}
    });

    //選擇器
    for (let i = 1; i <= totalPages; i++) {
        const option = document.createElement('option');
        option.value = i;
        option.text = "第 " + i + " 頁";
        pageSelector.appendChild(option);
    }

    //上一頁點擊事件
    $("#prevPage").click(() => {
        if (currentPage > 1) {
            currentPage--;
            updateAnnouncements();
        }
    });

  	//下一頁點擊事件
    $("#nextPage").click(() => {
        if (currentPage < totalPages) {
            currentPage++;
            updateAnnouncements();
        }
    });
    
    updateAnnouncements();
});
    </script>
	
	
</head>
<body>
	<header>
        <div class="openButton"></div>
        <h1>系統公告</h1>
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
	<div class="container">
	<c:forEach var="board" items="${boardData}">
            <div class="announcement">
            <div class="announcement-category">${board.boardTitle}</div>
            <div class="announcement-content">${board.boardContent}</div>
            <div class="announcement-date">發布日期：<fmt:formatDate value="${board.publicDate}" pattern="yyyy-MM-dd HH:mm" /></div>
            </div>
        </c:forEach>
	
        <div class="pagination">
        <a href="#" id="prevPage">上一頁</a>
        <span id="currentPage">目前頁數: </span>
        <select class="form-select" id="pageSelector" aria-label="Default select example">
			  <option selected>請選擇頁數</option>
		</select>
        <span id="totalPages">總頁數: </span>
        <a href="#" id="nextPage">下一頁</a>
    </div>
    </div>

    
</body>
</html>
