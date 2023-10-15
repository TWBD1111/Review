package com.ispan.eeit69.controller.bgsys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.ispan.eeit69.model.PendingOrder;
import com.ispan.eeit69.service.PendingOrderService;

@Controller
public class WebSocketController {
	
	@Autowired
	PendingOrderService pendingOrderService;
	
	 @MessageMapping("/sendMessage")
	    @SendTo("/topic/pendingOrders")
	    public List<PendingOrder> sendPendingOrders() {
	        // 在這裡生成你的 JSON 資料，並返回它
		 return pendingOrderService.findAll();
	        
	    }
	 
}
// 連線後確認訂閱主題，並返還初始數據