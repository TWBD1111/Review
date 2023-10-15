package com.ispan.eeit69.service;

import com.ispan.eeit69.DTO.OrderDto;

public interface EcpayOrderService {

	String ecpayCheckout(OrderDto orderDto);

}