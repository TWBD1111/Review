// 計算總金額
function updatefooterPrice() {
    let totalAmount = 0;
    const orderData = JSON.parse(localStorage.getItem('orderData')) || [];
    for (const order of orderData) {
        totalAmount += order.finaltotalPrice;
    }
    const footerElement = document.querySelector('.footer1');
    const totalAmountElement = document.querySelector('.footer1 span');
    const allPriceElement = $('#allPrice h4 span');

    if (totalAmount > 0) {
        totalAmountElement.textContent = `NT$${totalAmount}`;
        allPriceElement.text(`NT$${totalAmount}`);
    } else {
        $('.footersticky span').hide();
    }
}
function deleteProduct(button) {
    var productContainer = button.parentElement.parentElement.parentElement; 
    var productId = parseInt(productContainer.dataset.productId); 

    // 從 local storage 中刪除對應的產品
    var orderData = JSON.parse(localStorage.getItem('orderData')) || [];
    orderData = orderData.filter(product => !(product.cartId === productId));

    // 更新 local storage
    localStorage.setItem('orderData', JSON.stringify(orderData));
    // 從 DOM 中移除相應的 HTML 元素
    productContainer.remove();
    updatefooterPrice();
    if ($("#cartContainer").text().trim() == '') {
        $('#allcartpage').hide();
        $(".nocartdiv").show();
    }
    
}

$(document).ready(function () {
    $('.fa-angle-left').click(function () {
        window.location.href = '/main';
    });
    $('.neworder ,.viewmeun').click(function () {
        window.location.href = '/main';
    });
    $(".nocartdiv").hide();
//    $('.footer1').click(function () {
//        var errorMessages = [];
//        // 檢查必填欄位是否有填寫
//        var takeoutChecked = $('input[name="takeout"]:checked').length > 0;
//        var payChecked = $('input[name="label"]:checked').length > 0;
//
//        // 如果有未選擇的必填欄位
//        if (!takeoutChecked) {
//            
//            var messageDiv = document.createElement('div');
//            messageDiv.classList.add('takeouterrmsg');
//            messageDiv.textContent = '請選擇用餐方式';
//            var container = document.querySelector('.takeout');
//            $('.takeouterrmsg').remove();
//            container.appendChild(messageDiv);
//            // 將畫面滾動至錯誤訊息位置
//            $('html, body').animate({
//                scrollTop: $(container).offset().top
//            }, 100); // 1000為滾動的時間，以毫秒為單位
//        }
//        if (!payChecked) {
//            var messageDiv = document.createElement('div');
//            messageDiv.classList.add('payerrmsg');
//            messageDiv.textContent = '請選擇付款方式';
//            var container = document.querySelector('#payradio');
//            $('.payerrmsg').remove();
//            container.appendChild(messageDiv);
//            $('html, body').animate({
//                scrollTop: $(container).offset().top
//            }, 100);
//        }
//        if (takeoutChecked && payChecked) {
//            window.location.href = 'finish';
//        }
//    });
//    $('input[name="takeout"]').on('input', function () {
//        $('.takeouterrmsg').remove();
//    });
//
//    $('input[name="label"]').on('input', function () {
//        $('.payerrmsg').remove();
//    });
//
//    $('.payinfo').hide();
//    
//    $('input[name="label"]').change(function () {
//        if (this.value === '線上刷卡') {
//
//            $('.payinfo').show();
//        } else if (this.value === 'GooglePay') {
//            $('.payinfo').show();
//
//        } else {
//            $('.payinfo').hide();
//        }
//    });


    const orderData = JSON.parse(localStorage.getItem('orderData')) || [];
    const cartContainer = document.getElementById('cartContainer');

    orderData.forEach(data => {
        const div = document.createElement('div');
        div.className = 'orderflex';
        const customizedOptions = data.customized.split(',');
        const customizedHTML = customizedOptions.map(option => {
            return `<div class="customedinfo">${option}</div>`;
        }).join('');
        const toppingsOptions = Object.values(data.toppings).map(option => {
            return `<div class="toppingsinfo">${option}</div>`;
        }).join('');

        const remarkValue = data.remark;
        const productId = data.cartId;
        div.setAttribute('data-product-id', productId); 
        div.innerHTML = `
                        <div class="pdtcount">
                            <input type="button" value="-" class="uuu">
                            <span>${data.itemCount}</span>
                            <input type="button" value="+">
                        </div>
                        <div class="cartitemspace" style="width: 60%;"  data-cartID="${data.cartId}">
                            <div class="ordername" data-productID="${data.productID}">${data.name}</div>
                            ${customizedHTML}
                            ${toppingsOptions}
                            <div class="orderrename">餐點備註</div>
                            <div class="pdtrename">${remarkValue}</div>
                            <div class="cartitemRevise">修改</div>
                        </div>
                        <div class="cartitemspace">
                            <div class="pdtPrice">NT$80</div>
                            <div><i class="fa-solid fa-trash-can" style="cursor: pointer;float: right;" onclick="deleteProduct(this)"></i></div>
                        </div>
                        
                    `;

        cartContainer.appendChild(div);

    });
    // 從localStorage中獲取產品數據
    var products = JSON.parse(localStorage.getItem('orderData'));
    products.forEach(function (product, index) {
        // 獲取產品的價格和數量
        var productPrice = parseInt(product.price);
        var toppingPrice = 0;

        // 計算toppingPrice
        if (product.toppings) {
            product.toppings.forEach(function (topping) {
                toppingPrice += parseInt(product.toppingsPrices[topping]);
            });
        }

        var quantity = parseInt(product.itemCount);
        var totalPrice = (productPrice + toppingPrice) * quantity;

        // 將總價格更新到相應的元素中
        var pdtPriceElement = document.querySelectorAll('.pdtPrice')[index];
        pdtPriceElement.textContent = 'NT$' + totalPrice;
    });

    var interval;

    $(".pdtcount input[value='+']").on('mousedown touchstart', function () {

        interval = setInterval(function () {
            var spanElement = $(this).siblings("span");
            var currentValue = parseInt(spanElement.text(), 10);
            if (currentValue < 99) {
                spanElement.text(currentValue + 1);
                updateTotalPrice(this);
                updatefooterPrice();

            }
        }.bind(this), 100);
        $(this).on('mouseup touchend', function () {
            clearInterval(interval);
        });
    });

    $(".pdtcount input[value='-']").on('mousedown touchstart', function () {
        interval = setInterval(function () {
            var spanElement = $(this).siblings("span");
            var currentValue = parseInt(spanElement.text(), 10);
            if (currentValue > 1) {
                spanElement.text(currentValue - 1);
                updateTotalPrice(this);
                updatefooterPrice();

            }
        }.bind(this), 100);
        $(this).on('mouseup touchend', function () {
            clearInterval(interval);
        });
    });

    $(".pdtcount input[value='+']").click(function () {
        var spanElement = $(this).siblings("span");
        var currentValue = parseInt(spanElement.text(), 10);
        if (currentValue < 99) {
            spanElement.text(currentValue + 1);
            updateTotalPrice(this);
            updatefooterPrice();
        }
    });
    $(".pdtcount input[value='-']").click(function () {
        var spanElement = $(this).siblings("span");
        var currentValue = parseInt(spanElement.text(), 10);
        if (currentValue > 1) {
            spanElement.text(currentValue - 1);
            updateTotalPrice(this);
            updatefooterPrice();
        }
    });
    function updateTotalPrice(button) {
        var pdtPriceElement = button.parentElement.parentElement.querySelector('.pdtPrice');
        let total = 0;
        var priceText = pdtPriceElement.textContent.replace('NT$', '');
        var sumprice = parseInt(priceText);
        var quantity = parseInt(button.parentElement.querySelector('span').textContent);
        var currentProduct = button.parentElement.parentElement;
        var productName = currentProduct.querySelector('.ordername').textContent;
        var customizedOption = currentProduct.querySelector('.customedinfo:nth-child(2)').textContent;
        var toppingsOptions = Array.from(currentProduct.querySelectorAll('.toppingsinfo')).map(option => option.textContent).join(', ');
        const orderData = JSON.parse(localStorage.getItem('orderData')) || [];

        orderData.forEach(function (product, index) {
            var productToppings = product.toppings ? product.toppings.join(', ') : '';
            if (product.name === productName && product.customized === customizedOption && productToppings === toppingsOptions) {
                var singlePrice = product.singlePrice;
                total = quantity * singlePrice;
                orderData[index].itemCount = quantity;
                orderData[index].finaltotalPrice = total;
            }
        });

        localStorage.setItem('orderData', JSON.stringify(orderData));

        pdtPriceElement.textContent = `NT$${total}`;
    }
    $('.orderflex').each(function () {
        var orderRename = $(this).find('.orderrename');
        var pdtRename = $(this).find('.pdtrename');

        if (pdtRename.text().trim() !== '') {
            orderRename.removeClass('renamehidden');
            pdtRename.removeClass('renamehidden');
        } else {
            orderRename.addClass('renamehidden');
            pdtRename.addClass('renamehidden');
        }
    });
	 $('.cartitemRevise').click(function () {
		   
	       let cartID = $(this).closest('.cartitemspace').data('cartid');
	       let productID = $(this).closest('.cartitemspace').find('.ordername').data('productid');
	       
		　　let itemCount = $(this).parent().parent().find('span').text();
		　　let customizedHTML = $(this).closest('.cartitemspace').find('.customedinfo').html();
		　　let toppingsHTML = $(this).closest('.cartitemspace').find('.toppingsinfo').map(function() {
			    return $(this).text();
				}).get();
		   let remark = $(this).closest('.cartitemspace').find('.pdtrename').text();
		
		　　let cartdata = {
			  　cartID: cartID,
			  　productID: productID,
			  　itemCount: itemCount,
			  　customized: customizedHTML, 
			  　toppings: toppingsHTML,
		        remark:remark
		　　};
			
		　　localStorage.setItem('cartdata', JSON.stringify(cartdata));
	       window.location.href = '/opProduct/' + productID +'?cartID=' + cartID;
	    });
    updatefooterPrice();
    
});