package com.ispan.eeit69.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ispan.eeit69.model.PendingOrder;
import com.ispan.eeit69.repository.CompleteOrderRepository;
import com.ispan.eeit69.repository.PendingOrderRepository;
import com.ispan.eeit69.service.PendingOrderService;

@Service
public class PendingOrderServiceImpl implements PendingOrderService {

	@Autowired
	PendingOrderRepository pendingOrder;
	
	@Autowired
    CompleteOrderRepository completeOrderRepository;
	
	
	
	@Override
	public List<PendingOrder> findAll() {
		
		return pendingOrder.findAll();
	}
	
	@Override
	public String findDiningLocation(Integer updatedOrderNo) {
        return pendingOrder.findDiningLocation(updatedOrderNo);
    }

    @Override
	public List<PendingOrder> findOrderNo(Integer updatedOrderNo) {
        return pendingOrder.findByOrderNo(updatedOrderNo);
    }

    @Override
	public Integer findOrderPrice(Integer updatedOrderNo) {
        return pendingOrder.findOrderPrice(updatedOrderNo);
    }
    
    @Override
    public void deleteById(Integer orderNo) {
    	pendingOrder.deleteById(orderNo);
    }

	@Override
	public List<Object[]> findOrderDetailsForAllOrders() {
		return  pendingOrder.findOrderDetailsForAllOrders();		
	}

	public void copyPendingOrderToCompleteOrder(Integer orderNo) {
		pendingOrder.copyFromPendingOrderToCompleteOrder(orderNo);
    }
	
	@Override
	public List<Object[]> findCompletedOrdeeForAllOrders() {
		return  completeOrderRepository.findCompletedOrdeeForAllOrders();		
	}
	
	
	@Override
	public  List<Object[]> findOrderDetailsByOrderNo(Integer orderNo) {
		return  pendingOrder.findOrderDetailsByOrderNo(orderNo);		
	}

	
	//----------------------------------------------------------------------------Ray 2023/10/11
	
		
		@Override
		public List<Object[]> findOrderDetailsForAllBasicOrders(Integer orderNo){
			return  pendingOrder.findOrderDetailsForAllBasicOrders(orderNo);
		}

		@Override
		public List<Object[]> findCompletedDetailsForAllBasicOrders(Integer orderNo){
			return  completeOrderRepository.findCompletedDetailsForAllBasicOrders(orderNo);
		}
		
		
		@Override
		public List<Object[]> findCompletedDetailsByOrderNo(Integer orderNo){
			return  completeOrderRepository.findCompletedDetailsByOrderNo(orderNo);
		}
	
	
}
