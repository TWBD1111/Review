$(document).ready(function () {
	localStorage.removeItem('cartdata');
    $('.productitem').click(function () {
	  var productID = $(this).data('productid');
      window.location.href = 'opProduct/'+productID;
    });
    $('.footer1').click(function () {
      window.location.href = 'cart';
    });
    // 获取本地存储的订单数据
    const orderData = JSON.parse(localStorage.getItem('orderData')) || [];

    // 计算总金额
    let totalAmount = 0;
    for (const order of orderData) {
      totalAmount += order.finaltotalPrice;
    }
    const footerElement = document.querySelector('.footer1');
    const totalAmountElement = document.querySelector('.footer1 span');

    if (totalAmount > 0) {
      totalAmountElement.textContent = `NT$${totalAmount}`;
    } else {
      $('.footersticky span').hide();
    }
    if ($(".footersticky span").text().trim() == '') {
      $(".footersticky").hide();
    }
  });