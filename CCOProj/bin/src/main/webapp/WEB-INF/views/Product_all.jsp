<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List of Products</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
	<link rel='stylesheet' href="<c:url value='/css/back-end.css' />" />
	<script type="text/javascript" src="/js/back-end.js"></script>
	<style>
		.table{
			width: 90%;
			text-align: center;
			margin: 0 auto;
		}
		.container{
		    margin-top: 100px; /* 設置上外邊距為 50 像素，你可以根據需要調整 */
		    width: 91%;
		}
		legend {
			font-weight: bold;
			color: black;
			background-color: white;
			border: 1px solid #cccccc;
			padding: 4px 2px;
		}		
		fieldset {
			font-weight: bold;
/* 			color: black; */
/* 			background-color: #ffffcc; */
			border: 1px solid #cccccc;
			padding: 4px 2px;
	</style>
    <script>
		 function deleteProduct(ids, empno){
			 
			if (confirm('確定要刪除商品編號為: ' + empno +' 這筆紀錄?')) {
				let url = "<c:url value='/product/ProductDelete' />" + "/" + ids;
				let input1 = document.createElement("input");
				let input2 = document.createElement("input");
				
				let container = document.forms[0];
                container.appendChild(input1);
                container.appendChild(input2);
                
                input1.type = "text";
                input1.name = "empNo";
                input1.value = empno;
                
                input2.type = "text";
                input2.name = "_method";
                input2.value = "DELETE";
                
                document.forms[0].action=url;
				document.forms[0].method="POST";
				document.forms[0].submit();
                return true;
			 } else {
                exit;
             }
		  }
	</script>
	<script>
		function searchProducts(data) {			
			console.log("searchProducts function called."); // 檢查函式是否被呼叫
			var keywordInput = document.getElementById("searchKeyword");	
		    if (!keywordInput) {
		        console.error("Element with id 'searchKeyword' not found.");
		        return;
		    }	
		    var keyword = keywordInput.value;	
		    console.log("Keyword: " + keyword);  // 檢查keyword是否有值
		    
		    // 使用 Ajax 向後端發送搜尋請求
		    $.ajax({
		        type: "GET",
		        url: "<c:url value='/searchProducts' />",
		        data: { keyword: keyword },
		        success: function(responseHtml) {
		            console.log('Received HTML data:', responseHtml);
		            
		            // Assuming the responseHtml is a table structure or contains the table you want to display.
		            // Insert the HTML into the searchResults element.
		            document.getElementById("searchResults").style.display = "block";
		            document.getElementById("searchResults").innerHTML = responseHtml;
		        },
		        error: function(jqXHR, textStatus, errorThrown) {
		            console.error("搜尋商品時發生錯誤:");
		            console.error("狀態碼：" + jqXHR.status);
		            console.error("錯誤訊息：" + textStatus);
		            console.error("錯誤拋出：" + errorThrown);
		        }
		    });
		}
	</script>
</head>
<body>
	<header>
	   <div class="openButton"></div>
	   <h1>所有商品列表</h1>
	   <ul class="menuBox" style=" padding-left: 0 ;">
	   	   <li>
	       <div class="menu-top">
	           <div class="closeButton"></div>
	       </div>
	       <li>
	           <a class="click1" href="#">第一層</a>
	       <li>
	           <a class="click2" href="#">第一層</a>
	       <li>
	           <a class="click3" href="#">第一層</a>
	    </ul>
	</header>
	<div class='container'>
		<div class="row align-items-end">
		    <div class="col-lg-4">
		        <label for="searchKeyword" class="form-label">搜尋商品：</label>
		        <form>
		        	<input type="text" class="form-control" id="searchKeyword" placeholder="輸入商品編號、商品名稱、類別名稱">
		        </form>		        
		    </div>
		    <div class="col-lg-4">
		        <button class="btn btn-primary" onclick="searchProducts()">搜尋</button>
		    </div>
		    <div class="col-lg-4 text-end">
		        <a class='btn btn-success' href="<c:url value='/product/insertProduct' />">新增商品</a>
		    </div>
		</div>
	</div>
	<p>
	<div id="searchResults" style="display: none;">

	</div>
	<p>
	<h4 align='center'>${message}&nbsp;</h4>
	<p>
	<table class='table table-striped table-hover align-middle'>
        <thead>
            <tr>
            	<th>商品編號</th>
                <th>商品名稱</th>
                <th>圖片</th>
                <th>類別</th>
                <th>商品介紹</th>
                <th>份量</th>
                <th>價格(元)</th>
                <th>庫存</th>
                <th>上架時間</th>
                <th>客製標籤</th>
                <th>操作</th>
            </tr>
        </thead>
		<tbody>
	    <c:forEach var="product" items="${productPage.content}">
	        <c:set var="found" value="false" />
	        <c:forEach var="pageProduct" items="${productList}">
	            <c:if test="${product.productId eq pageProduct.productId}">
	                <tr>
	                    <td>${product.productId}</td>
	                    <td>${product.productName}</td>
	                    <td><img width='150' height='100' src='${product.dataUri}'></td>
	                    <td>${product.category.categoryName}</td>
	                    <td style="width: 200px;">${product.productDescription}</td>
	                    <td>${product.productPortion}</td>
	                    <td>${product.productPrice}</td>
	                    <td>${product.productStock}</td>
	                    <td style="width: 150px;">${product.created_at}</td>
	                    <td>
	                        <c:choose>
	                            <c:when test="${empty product.labels}">無</c:when>
	                            <c:otherwise>
	                                <c:forEach var="label" items="${product.labels}">${label.labelName} (+${label.labelPrice}元)<br></c:forEach>
	                            </c:otherwise>
	                        </c:choose>
	                    </td>
	                    <td>
	                        <a class='btn btn-warning btn-sm' href="<c:url value='product/findById/${pageProduct.id}' />">修改</a>&nbsp;
	                        <a class='btn btn-danger btn-sm'  onclick="return deleteProduct('${pageProduct.id}','${pageProduct.productId}');">刪除</a>
	                    </td>
	                </tr>
	                <c:set var="found" value="true" />
	            </c:if>
	        </c:forEach>
	        <!-- Handle the case where a product from productPage.content does not exist in productList -->
	        <c:if test="${not found}">
	            <tr>
	                <td>${product.productId}</td>
	                <td>${product.productName}</td>
	                <td><img width='150' height='100' src='${product.dataUri}'></td>
	                <td>${product.category.categoryName}</td>
	                <td>${product.productDescription}</td>
	                <td>${product.productPortion}</td>
	                <td>${product.productPrice}</td>
	                <td>${product.productStock}</td>
	                <td>${product.created_at}</td>
	                <td>
	                    <c:choose>
	                        <c:when test="${empty product.labels}">無</c:when>
	                        <c:otherwise>
	                            <c:forEach var="label" items="${product.labels}">${label.labelName} (+${label.labelPrice}元)<br></c:forEach>
	                        </c:otherwise>
	                    </c:choose>
	                </td>
	                <td>
	                    <a class='btn btn-warning btn-sm' href="<c:url value='product/findById/${product.id}' />">修改</a>&nbsp;
	                    <a class='btn btn-danger btn-sm'  onclick="return deleteProduct('${product.id}','${product.productId}');">刪除</a>
	                </td>
	            </tr>
	        </c:if>
	    </c:forEach>
		</tbody>	
    </table>
    <p>
	<div class="pagination d-flex justify-content-center">
	    <c:if test="${productPage.hasPrevious()}">
	        <a href="<c:url value='/product?page=${productPage.previousPageable().pageNumber}' />" class="btn btn-secondary">上一頁</a>
	    </c:if>
	    <span class="align-self-center mx-2">第 ${productPage.number + 1} 頁，共 ${productPage.totalPages} 頁</span>
	    <c:if test="${productPage.hasNext()}">
	        <a class="btn btn-secondary" href="<c:url value='/product?page=${productPage.nextPageable().pageNumber}' />">下一頁</a>
	    </c:if>
	</div>
	<p>    
    <div align='center'>
  			<a class='btn btn-outline-primary' href="<c:url value='/' />" role='button'>回首頁</a>
    </div>
    <form action="#" method='POST'></form>
</body>
</html>