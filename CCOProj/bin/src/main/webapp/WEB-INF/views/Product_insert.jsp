<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>新增產品資料</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
	<link rel='stylesheet' href="<c:url value='/css/back-end.css' />" />
	<script type="text/javascript" src="/js/back-end.js"></script>	
	<style>
		span.error {
			color: red;
			display: inline-block;
			font-size: 11pt;
		}		
		.fieldset-auto-width {
			display: inline-block;	
		}
		.container{
		    margin-top: 100px; /* 設置上外邊距為 50 像素，你可以根據需要調整 */
		}
	</style>
	<script>
		let inputFileToLoad = null;
		let image = null;
		let img = null;
		window.addEventListener('load', function() {
			inputFileToLoad = document.getElementById("inputFileToLoad");
			image = document.getElementById("image");
			img = document.getElementById("img");
			inputFileToLoad.addEventListener('change', loadImageFileAsURL);
		});
		function loadImageFileAsURL() {
			let filesSelected = inputFileToLoad.files;
			if (filesSelected.length > 0) {
				let fileReader = new FileReader();
				let fileToLoad = filesSelected[0];
				fileReader.onload = function(fileLoadedEvent) {
					image.value = fileLoadedEvent.target.result;
					console.log("image.value=" + image.value);
					img.src = fileLoadedEvent.target.result;
				};
				fileReader.readAsDataURL(fileToLoad);
			}
		}
	</script>
</head>
<body>
	<header>
	   <div class="openButton"></div>
	   <h1>新增產品資料</h1>
	   <ul class="menuBox" style=" padding-left: 0 ;">
	   	   <li>
	       <div class="menu-top">
	           <div class="closeButton"></div>
	       </div>
	       <li>
	           <a class="click1" href="#">第一層</a>
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
	<div class="container text-center">
		<c:url var='insertUrl' value='/product/insertProduct' />
		<form:form method='POST' modelAttribute="product" action="${insertUrl}">
			<div class='row mb-3'>
				<label class='col-sm-3'>商品編號</label>
				<div class='col-sm-6'>
					<form:input type='text' class='form-control' path='productId'
						placeholder="請輸入商品編號" /> 
				</div>
				<div class='col-sm-3'>
					<form:errors cssClass="error" path='productId' />
				</div>
			</div>

			<div class='row mb-3'>
				<label class='col-sm-3'>商品名稱</label>
				<div class='col-sm-6'>
					<form:input type='text' class='form-control' path='productName'
						placeholder="請輸入商品名稱" />
				</div>
				<div class='col-sm-3'>
					<form:errors cssClass="error" path='productName' />
				</div>
			</div>

			<div class='row mb-3'>
				<label class='col-sm-3'>圖片</label>
				<div class='col-sm-3'>
					<form:input type='file' id='inputFileToLoad' class='form-control'
						path='fileName' placeholder="請挑選圖片" value='${product.fileName}' />
				</div>
				<div class='col-sm-4'>
					<c:choose>
						<c:when test='${empty image}'>
							<img id='img' width='200' height='150'>
							<input type='hidden' id='image' name='image'>
						</c:when>
						<c:otherwise>
							<img id='img' width='200' height='150' src='${image}'>
							<input type='hidden' name='image' value='${image}'>
						</c:otherwise>
					</c:choose>
				</div>
				<div class='col-sm-2'>
					<form:errors cssClass="error" path='fileName' />
				</div>
			</div>

			<div class='row mb-3'>
				<label class='col-sm-3'>類別名稱</label>
				<div class='col-sm-6'>
					<form:select class="form-select"  path="category">
						<form:option value=""  label="Select a category" />
						<form:options items="${categoryList}" itemValue="id" itemLabel="categoryName" />
					</form:select>
				</div>
				<div class='col-sm-3'>
					<form:errors cssClass="error" path='category' />
				</div>
			</div>

			<div class='row mb-3'>
				<label class='col-sm-3'>商品介紹</label>
				<div class='col-sm-6'>
					<form:textarea class='form-control' path='productDescription'
						placeholder="請輸入商品介紹" />
				</div>
				<div class='col-sm-3'>
					<form:errors cssClass="error" path='productDescription' />
				</div>
			</div>

			<div class='row mb-3'>
				<label class='col-sm-3'>份量</label>
				<div class='col-sm-6'>
					<form:input type='text' class='form-control' path='productPortion'
						placeholder="請輸入份量 (EX中杯、小杯...)" />
				</div>
				<div class='col-sm-3'>
					<form:errors cssClass="error" path='productPortion' />
				</div>
			</div>

			<div class='row mb-3'>
				<label class='col-sm-3'>價格</label>
				<div class='col-sm-6'>
					<form:input type='number' min="0" class='form-control' path='productPrice'
						placeholder="請輸入商品價格" />
				</div>
				<div class='col-sm-3'>
					<form:errors cssClass="error" path='productPrice' />
				</div>
			</div>

			<div class='row mb-3'>
				<label class='col-sm-3'>庫存</label>
				<div class='col-sm-6'>
					<form:input type='number' min="0" class='form-control'
						path='productStock' placeholder="請輸入商品庫存" />
				</div>
				<div class='col-sm-3'>
					<form:errors cssClass="error" path='productStock' />
				</div>
			</div>
			<div class='row mb-3'>
			    <label class='col-sm-3'>客製標籤</label>
			    <div class='col-sm-6'>
			    	<div class="row">
				        <c:forEach var="label" items="${labelList}">
				        	<div class="col-sm-3">
					            <label>
					                <input type="checkbox" name="labels" value="${label.id}" 
					                <c:if test="${selectedLabelIds.contains(label.id)}">checked</c:if>> ${label.labelName}
					            </label><br>
					        </div> 
				        </c:forEach>
			            <div class="col-sm-3">
			                <label>
			                    <input type="checkbox" name="labels" value="0" 
			                    <c:if test="${selectedLabelIds.isEmpty() || !selectedLabelIds.contains('0')}">checked</c:if>> 無
			                </label>
			            </div>
			        </div>  
			    </div>	  
			    <div class='col-sm-3'>
			        <form:errors cssClass="error" path="labels"  />
			    </div>
			</div>		    
			<c:if test="${not empty exception}">${exception}</c:if>
			<div class='row mb-3'>
				<div class='offset-sm-3 col-sm-3 d-grid'>
					<button type='submit' class='btn btn-primary'>確認</button>
				</div>
				<div class='col-sm-3 d-grid'>
					<a class='btn btn-outline-primary' href="<c:url value='/product' />"
						role='button'>離開</a>
				</div>
			</div>
		</form:form>
		<hr>
	</div>
</body>
</html>
