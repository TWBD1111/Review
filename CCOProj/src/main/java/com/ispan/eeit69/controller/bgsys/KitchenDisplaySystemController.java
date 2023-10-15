package com.ispan.eeit69.controller.bgsys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ispan.eeit69.service.PendingOrderService;

@Controller
public class KitchenDisplaySystemController {

	@Autowired
	PendingOrderService pendingOrderService;

	//WebSocket Version
	@GetMapping("/kitchenDisplaySystem")
	public String pendingOrdersWS() {
		return "kitchenDisplaySystem";
	}
	
//	// 查詢所有暫存訂單
//	@GetMapping("/pendingOrder")
//	public @ResponseBody List<PendingOrder> findAllpendingOrder() {
//		return pendingOrderService.findAll();
//	}
//
//	//AJAX Version
//	@GetMapping("/showPendingOrders")
//	public String pendingOrders() {
//		return "showPendingOrders";
//	}
}