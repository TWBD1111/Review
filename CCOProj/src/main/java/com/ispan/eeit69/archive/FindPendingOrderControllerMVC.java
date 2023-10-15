//package com.ispan.eeit69.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import com.ispan.eeit69.model.PendingOrder;
//import com.ispan.eeit69.service.PendingOrderService;
//
//@Controller
//public class FindPendingOrderControllerMVC {
//
//	@Autowired
//	PendingOrderService pendingOrderService;
//
//
//
//	// 查詢所有暫存訂單 - 傳統 JSP 視圖
//	@GetMapping("/pendingOrdersMVC")
//	public String findAllpendingOrder(Model model) {
//		List<PendingOrder> pendingOrders = pendingOrderService.findAll();
//		model.addAttribute("pendingOrdersMVC", pendingOrders);
//
//		return "showPendingOrderMVC";
//	}
//
//	// 測試用
////	@GetMapping("/")
////	public String index() {
////		return "index";
////	}
//
//}



