package com.ispan.eeit69.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ChartService {
	
//	 營業額
	 Integer calDailyTotalRevenue();
	 Integer calWeeklyTotalRevenue();
	 Integer calMonthlyTotalRevenue();
	 Integer calCustomTotalRevenue(Date startDate, Date endDate);
	 
//	 訂單數
	 Integer countDailyOrders();
	 Integer countWeeklyOrders();
	 Integer countMonthlyOrders();
	 Integer countCustomOrders(Date startDate, Date endDate);
	 
//	 外帶內用比例
	 List<Object[]> countDailyDiningLocation();
	 List<Object[]> countWeeklyDiningLocation();
	 List<Object[]> countMonthlyDiningLocation();
	 List<Object[]> countCustomDiningLocation(Date startDate, Date endDate);
	 
//	 產品類別
	 List<Object[]> countDailyFoodCategory();
	 List<Object[]> countWeeklyFoodCategory();
	 List<Object[]> countMonthlyFoodCategory();
	 List<Object[]> countCustomFoodCategory(Date startDate, Date endDate);
	 
//	 熱賣商品排行
	 List<Map<String, Object>> dailyHotProduct();
	 List<Map<String, Object>> weeklyHotProduct();
	 List<Map<String, Object>> monthlyHotProduct();
	 List<Map<String, Object>> customHotProduct(Date startDate, Date endDate);
	 
//	 營業額 & 訂單數分析圖
	 List<List<Object>> findDailyData();
	 List<List<Object>> findWeeklyData();
	 List<List<Object>> findMonthlyData();
	 List<List<Object>> findCustomData(Date startDate, Date endDate);
	 
//	 List<CompleteOrder> findAll();
	 
}
