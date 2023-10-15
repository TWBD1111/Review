package com.ispan.eeit69.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ispan.eeit69.model.PendingOrder;

public interface PendingOrderRepository extends JpaRepository<PendingOrder, Integer> {
	@Query("SELECT MAX(p.orderNo) FROM PendingOrder p")
	Integer findLastOrderNo();
	
	@Query("SELECT p FROM PendingOrder p WHERE p.orderNo = :updatedOrderNo")
	List<PendingOrder> findByOrderNo(@Param("updatedOrderNo")Integer updatedOrderNo);
	
	
	@Query("SELECT MAX(p.diningLocation) FROM PendingOrder p WHERE p.orderNo = :updatedOrderNo")
    String findDiningLocation(@Param("updatedOrderNo")Integer updatedOrderNo);
    
	@Query("SELECT SUM(p.orderPrice) FROM PendingOrder p WHERE p.orderNo = :updatedOrderNo")
    Integer findOrderPrice(@Param("updatedOrderNo")Integer updatedOrderNo);
	
	// 添加判斷訂單是否存在的方法
	boolean existsByOrderNo(Integer orderNo);
	
	@Modifying
    @Query("UPDATE PendingOrder p SET p.orderPrice = :orderPrice WHERE p.orderNo = :orderNo")
    void updateOrderPrice(@Param("orderNo") Integer orderNo, @Param("orderPrice") Integer orderPrice);

	// ---------------------------------------------------------------------------------Ray 2023/10/11
	
	@Query(value = "SELECT " + "  po.orderNo as orderNo, " + "  po.diningLocation as diningLocation, "
			+ "  MIN(po.created_at) as createdAt, " + "  SUM(CAST(po.orderPrice AS DECIMAL(10, 2))) as totalOrderPrice "
			+ "FROM PendingOrder po " + "GROUP BY po.orderNo, po.diningLocation,po.diningLocation", nativeQuery = true)
	List<Object[]> findOrderDetailsForAllOrders();

	@Query(value = "SELECT * FROM PendingOrder po WHERE po.orderNo = :orderNo", nativeQuery = true)
	List<Object[]> findOrderDetailsByOrderNo(@Param("orderNo") Integer orderNo);

	@Query(value = "SELECT " + "  po.orderNo as orderNo, " + "  po.diningLocation as diningLocation, "
			+ "  MIN(po.created_at) as createdAt, " + "  SUM(CAST(po.orderPrice AS DECIMAL(10, 2))) as totalOrderPrice "
			+ "FROM PendingOrder po WHERE po.orderNo = :orderNo " + // 添加這個空格
			"GROUP BY po.orderNo, po.diningLocation,po.diningLocation", nativeQuery = true)
	List<Object[]> findOrderDetailsForAllBasicOrders(@Param("orderNo") Integer orderNo);

	@Modifying
	@Query(nativeQuery = true, value = "INSERT INTO Complete_Order (\n"
			+ "    orderNo, diningLocation, productName, categoryName, foodQuantity, \n"
			+ "    orderPrice, created_at, complete_at, labelName, foodNote, orderNote\n" + ") " + "SELECT \n"
			+ "    p.orderNo, p.diningLocation, p.productName, p.categoryName, p.foodQuantity, \n"
			+ "    p.orderPrice, p.created_at, CONVERT_TZ(NOW(), 'UTC', 'Asia/Taipei'), \n"
			+ "    p.labelName, p.foodNote, p.orderNote  " + "FROM PendingOrder p " + "WHERE p.orderNo = :orderNo")
	void copyFromPendingOrderToCompleteOrder(@Param("orderNo") Integer orderNo);

	@Modifying
	@Query("DELETE FROM PendingOrder p WHERE p.orderNo = :orderNo")
	void deleteById(@Param("orderNo") Integer orderNo);



}
