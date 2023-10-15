package com.ispan.eeit69.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ispan.eeit69.model.CompleteOrder;

public interface CompleteOrderRepository extends JpaRepository<CompleteOrder, Integer> {

	// 查詢營業額
	@Query("SELECT SUM(Complete_Order.orderPrice) FROM CompleteOrder Complete_Order WHERE Complete_Order.complete_at BETWEEN :startDate AND :endDate")
	Integer calRevenue(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	// 查詢訂單量
	@Query("SELECT COUNT(DISTINCT Complete_Order.orderNo) FROM CompleteOrder Complete_Order WHERE Complete_Order.complete_at BETWEEN :startDate AND :endDate")
	Integer countOrders(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	// 統計內用外帶比例
	@Query("SELECT Complete_Order.diningLocation, COUNT(Complete_Order) FROM CompleteOrder Complete_Order WHERE Complete_Order.complete_at BETWEEN :startDate AND :endDate GROUP BY Complete_Order.diningLocation")
	List<Object[]> countDiningLocation(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	// 統計產品類別比例
//	@Query("SELECT Complete_Order.categoryName, COUNT(Complete_Order) FROM CompleteOrder Complete_Order WHERE Complete_Order.complete_at BETWEEN :startDate AND :endDate GROUP BY Complete_Order.categoryName")
	@Query("SELECT co.product.category.categoryName, COUNT(co) FROM CompleteOrder co WHERE co.complete_at BETWEEN :startDate AND :endDate GROUP BY co.product.category.categoryName")
	List<Object[]> countFoodCategory(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	// 熱門商品排行
	@Query("SELECT Complete_Order.product.productName, SUM(Complete_Order.foodQuantity), SUM(Complete_Order.orderPrice) FROM CompleteOrder Complete_Order WHERE Complete_Order.complete_at BETWEEN :startDate AND :endDate GROUP BY Complete_Order.product ORDER BY SUM(Complete_Order.foodQuantity) DESC")
	List<Object[]> hotProduct(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	// 查詢每日營業額及訂單量
	@Query("SELECT DATE(c.complete_at) AS orderDate, SUM(c.orderPrice) AS Revenue, COUNT(DISTINCT c.orderNo) AS orderQuan FROM CompleteOrder c WHERE c.complete_at BETWEEN :startDate AND :endDate GROUP BY orderDate ORDER BY orderDate")
	List<Object[]> findDailyData(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	//日期格式化 - 顯示星期幾的查詢語句，用在上面方法
//	@Query("SELECT FUNCTION('DATE_FORMAT', c.complete_at, '%Y/%m/%d %W'), SUM(c.orderPrice) AS Revenue, COUNT(DISTINCT c.orderNo) AS orderQuan FROM Chart c WHERE c.complete_at BETWEEN :startDate AND :endDate GROUP BY FUNCTION('DATE_FORMAT', c.complete_at, '%Y/%m/%d %W') ORDER BY FUNCTION('DATE_FORMAT', c.complete_at, '%Y/%m/%d %W')")

	// 查詢當日每小時營業額及訂單量
	@Query("SELECT DATE_FORMAT(c.complete_at, '%Y/%m/%d %H:00') AS formattedDate, SUM(c.orderPrice) AS revenue, COUNT(DISTINCT c.orderNo) AS orderQuan FROM CompleteOrder c WHERE c.complete_at BETWEEN :startDate AND :endDate GROUP BY formattedDate ORDER BY formattedDate")
	List<Object[]> findHourData(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	
	//------------------------------------------------------------------------------Ray 2023/10/11
	
	@Query(value = "SELECT " +
	        "  po.orderNo as orderNo, " +
	        "  po.diningLocation as diningLocation, " +
	        "  MIN(po.created_at) as createdAt, " +
	        "  MIN(po.complete_at) as completeAt, " +
	        "  SUM(CAST(po.orderPrice AS DECIMAL(10, 2))) as totalOrderPrice " +
	        "FROM Complete_Order po " +
	        "GROUP BY po.orderNo, po.diningLocation " +
	        "ORDER BY MIN(po.complete_at) DESC", nativeQuery = true)
	List<Object[]> findCompletedOrdeeForAllOrders();

	@Query(value = "SELECT " +
	        "  po.orderNo as orderNo, " +
	        "  po.diningLocation as diningLocation, " +
	        "  MIN(po.created_at) as createdAt, " +
	        "  MIN(po.complete_at) as completeAt, " +
	        "  SUM(CAST(po.orderPrice AS DECIMAL(10, 2))) as totalOrderPrice " +
	        "FROM Complete_Order po WHERE po.orderNo = :orderNo " +  // 添加這個空格
	        "GROUP BY po.orderNo, po.diningLocation,po.diningLocation", nativeQuery = true)
	List<Object[]> findCompletedDetailsForAllBasicOrders(@Param("orderNo") Integer orderNo);
	
	@Query(value = "SELECT * FROM Complete_Order po WHERE po.orderNo = :orderNo", nativeQuery = true)
	List<Object[]> findCompletedDetailsByOrderNo(@Param("orderNo") Integer orderNo);
	
	
	
}
