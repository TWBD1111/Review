package com.ispan.eeit69.service;

import java.util.List;

import com.ispan.eeit69.model.PendingOrder;

public interface PendingOrderService {

	List<PendingOrder> findAll();

	String findDiningLocation(Integer updatedOrderNo);

	List<PendingOrder> findOrderNo(Integer updatedOrderNo);

	Integer findOrderPrice(Integer updatedOrderNo);

	List<Object[]> findOrderDetailsByOrderNo(Integer orderNo);

	void deleteById(Integer orderNo);

	void copyPendingOrderToCompleteOrder(Integer orderNo);
//--------------------------------------------------------------------------Ray 2023/10/11
	List<Object[]> findOrderDetailsForAllOrders();

	List<Object[]> findCompletedOrdeeForAllOrders();

	List<Object[]> findOrderDetailsForAllBasicOrders(Integer orderNo);

	List<Object[]> findCompletedDetailsForAllBasicOrders(Integer orderNo);

	List<Object[]> findCompletedDetailsByOrderNo(Integer orderNo);

}
