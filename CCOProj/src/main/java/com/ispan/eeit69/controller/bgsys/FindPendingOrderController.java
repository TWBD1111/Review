package com.ispan.eeit69.controller.bgsys;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ispan.eeit69.model.PendingOrder;
import com.ispan.eeit69.repository.PendingOrderRepository;
import com.ispan.eeit69.service.PendingOrderService;

@Controller
public class FindPendingOrderController {

    @Autowired
    PendingOrderService pendingOrderService;

    @Autowired
    PendingOrderRepository pendingOrderRepository;
    
    @Autowired
    WebSocketMessageHandler wsmh;

   
    @GetMapping("/pendingorder")
    public @ResponseBody List<PendingOrder> findAll() {
    	
        return pendingOrderService.findAll();
        
    }

    
    @GetMapping("/findorder")
    public @ResponseBody List<Object[]> findAllpendingOrder() {
    	
        return pendingOrderService.findOrderDetailsForAllOrders();
        
    }
   
    @GetMapping("/findorderbyID/{orderNo}")
    public @ResponseBody List<Object[]> findOrderDetailsByOrderNo(@PathVariable Integer orderNo) {
        return pendingOrderService.findOrderDetailsByOrderNo(orderNo);
    }
    
    
    @GetMapping("/findCompletedOrder")
    public @ResponseBody List<Object[]> findCompletedOrder() {
    	
        return pendingOrderService.findCompletedOrdeeForAllOrders();
        
    }
    
    
     @GetMapping("/showOrderSystem")
     public String pendingOrders() {
         return "showOrderSystem";
     }
    
     @GetMapping("/showcompletedsystem")
     public String completedOrders() {
         return "showcompletedsystem";
     }
     
     @PostMapping("/copy-from-pending/{orderNo}")
     @Transactional  // Add this annotation
     public void copyPendingOrderToCompleteOrder(@PathVariable Integer orderNo) {
    	 pendingOrderService.copyPendingOrderToCompleteOrder(orderNo);
    	 pendingOrderService.deleteById(orderNo);
    	 wsmh.sendPendingOrders();
     }
     
     
    // Delete order by orderNo
     @DeleteMapping("/orderIDdelete/{orderNo}")
     public String deleteProduct(
             RedirectAttributes ra,
             @PathVariable Integer orderNo
     ) {
         try {
             pendingOrderService.deleteById(orderNo);
             ra.addFlashAttribute("message", "<font color='green'>已刪除OrderID: " + orderNo + " 之紀錄</font>");
         } catch (Exception e) {
             ra.addFlashAttribute("message", "<font color='red'>刪除商品編號: " + orderNo + " 之紀錄失敗:" + e.getMessage() + "</font>");
             e.printStackTrace();
         }
         wsmh.sendPendingOrders();
         return "redirect:/showOrderSystem";
     }

     
     
//---------------------------------------------------------------------------------------Ray 2023/10/11
     

     
     @GetMapping("/findOrderDetailsForAllBasicOrders/{orderNo}")
     public @ResponseBody List<Object[]> findOrderDetailsForAllBasicOrders(@PathVariable Integer orderNo) {
         return pendingOrderService.findOrderDetailsForAllBasicOrders(orderNo);       
     }
     
     
     
     
     @GetMapping("/findCompletedDetailsByOrderNo/{orderNo}")
     public @ResponseBody List<Object[]>findCompletedDetailsByOrderNo(@PathVariable Integer orderNo) {
         return pendingOrderService.findCompletedDetailsByOrderNo(orderNo);
     }
     
     
     @GetMapping("/findCompletedDetailsForAllBasicOrders/{orderNo}")
     public @ResponseBody List<Object[]>findCompletedDetailsForAllBasicOrders(@PathVariable Integer orderNo) {
         return pendingOrderService.findCompletedDetailsForAllBasicOrders(orderNo);
         
     }
             
     
     
}





