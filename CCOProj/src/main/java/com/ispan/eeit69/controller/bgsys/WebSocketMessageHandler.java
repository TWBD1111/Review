package com.ispan.eeit69.controller.bgsys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ispan.eeit69.model.PendingOrder;
import com.ispan.eeit69.service.PendingOrderService;


@Controller
public class WebSocketMessageHandler {
	
	@Autowired
	PendingOrderService pendingOrderService;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	 
	 @GetMapping("/sendPendingOrders")
	 public @ResponseBody List<PendingOrder> sendPendingOrders() {
	     List<PendingOrder> pendingOrders = pendingOrderService.findAll();
	     messagingTemplate.convertAndSend("/topic/pendingOrders", pendingOrders);
	     return pendingOrders;
	 }

}
// 處理建立連線後的資料更新