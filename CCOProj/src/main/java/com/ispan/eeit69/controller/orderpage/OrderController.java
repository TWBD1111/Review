package com.ispan.eeit69.controller.orderpage;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ispan.eeit69.DTO.OrderDto;
import com.ispan.eeit69.service.OrderService;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/processOrder")
    public OrderDto processOrder(@RequestBody List<Map<String, Object>> orderItems) {
        OrderDto orderDto = orderService.updateOrderPriceAndReturnOrderNo(orderItems);
        return orderDto;
    }
}