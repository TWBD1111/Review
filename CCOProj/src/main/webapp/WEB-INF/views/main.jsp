<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.HashSet" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
  <link rel="stylesheet" href="<c:url value='/css/main.css' />">
  <script src="<c:url value='/js/main.js' />"></script>
  <title>CCO</title>

</head>

<body>

  <div class="container">
    <div class="row">
      <div class="col-sm-10 col-lg-8 col-xl-7 mx-auto">
        <div>
          <div style="margin: 10px;">
            <h1>CCO早餐店</h1>
          </div>

          <img src="<c:url value='/img/點餐頁面.jpg' />" style="width: 100%;">
          <div class="cardheader">

            <details open>
              <summary style="margin: 20px 0px;">公告</summary>
              <div class="answer">
                <p>結帳方式請選擇『現場支付』、點選其他方式結帳將會取消訂單、送出後請至櫃檯結帳才會出餐、感謝您！
                  用餐時間一小時、每週一公休、謝謝！</p>
              </div>
            </details>

          </div>
          <div class="divsticky">

            
			<c:forEach items="${categoryList}" var="category">
					<a class="nav-link button-like"
						href="#scrollspyHeading${category.categoryId}">
						${category.categoryName} </a>
			</c:forEach>

          </div>
          
          <div class="cardbody">
			    <c:forEach var="category" items="${categoryList}">
			        <h1 id="scrollspyHeading${category.categoryId}">${category.categoryName}</h1>
			        <c:forEach var="product" items="${productList}">
			        <c:if test="${product.category == category}">
			            <div id="productitem" class="productitem" data-productid="${product.productId}">
			                <div style="display: flex; align-items: center;">
			                    <img style="height: 60px;width: 60px;" src="${product.dataUri}">
			                    <div style="margin-left: 10px;">
			                        <h6>${product.productName}</h6>
			                        <c:set var="price" value="${fn:split(product.productPrice, '.')[0]}" />
			                        <div>NT$<span>${price}</span></div>
			                    </div>
			                </div>
			                <span></span>
			            </div>
			            <hr>
			            </c:if>
			        </c:forEach>
			    </c:forEach>
          </div>
          <div class="footer1 footersticky">
            <div>查看購物車</div>
            <span></span>
          </div>
        </div>

      </div>

    </div>

  </div>

  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
    integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
    crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"
    integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa"
    crossorigin="anonymous"></script>
</body>

</html>