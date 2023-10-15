package com.ispan.eeit69.controller.orderpage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ispan.eeit69.service.PendingOrderService;

@Controller
public class OrderResultController {

	@Autowired
	private PendingOrderService pendingOrderService;

	@GetMapping("/final")
	public String showFinalPage(@RequestParam(name = "updatedOrderNo", required = false) Integer updatedOrderNo,
			Model model) {
		if (updatedOrderNo != null) {

			// 使用PendingOrderService中的方法來獲取數據
			String diningLocation = pendingOrderService.findDiningLocation(updatedOrderNo);
			Integer orderPrice = pendingOrderService.findOrderPrice(updatedOrderNo);

			// 將訂單詳細資料添加到模型中，以便在 JSP 頁面中使用
		    model.addAttribute("updatedOrderNo", updatedOrderNo);
			model.addAttribute("diningLocation", diningLocation);
			model.addAttribute("orderPrice", orderPrice);
		}

		return "final";
	}
}
