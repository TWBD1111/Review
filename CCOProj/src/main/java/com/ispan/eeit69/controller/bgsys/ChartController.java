package com.ispan.eeit69.controller.bgsys;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ispan.eeit69.DTO.ChartData;
import com.ispan.eeit69.service.ChartService;

@Controller
public class ChartController {

	@Autowired
	ChartService chartService;


	@GetMapping("/chart")
	public String chart() {
		return "chart";
	}
	
//	@GetMapping("/charttest")
//	public @ResponseBody List<CompleteOrder>charttest() {
//		return chartService.findAll();
//	}
	
	// 整合日級別數據
    @GetMapping("/todayData")
    public @ResponseBody ChartData getTodayData() {
        ChartData todayData = new ChartData();

        todayData.setTotalRevenue(chartService.calDailyTotalRevenue());
        todayData.setTotalOrders(chartService.countDailyOrders());
        todayData.setDiningLocation(chartService.countDailyDiningLocation());
        todayData.setFoodCategory(chartService.countDailyFoodCategory());
        todayData.setHotProducts(chartService.dailyHotProduct());
        todayData.setChartData(chartService.findDailyData());

        return todayData;
    }
// 整合近 7 日數據
    @GetMapping("/weeklyData")
    public @ResponseBody ChartData getWeeklyData() {
        ChartData weeklyData = new ChartData();

        weeklyData.setTotalRevenue(chartService.calWeeklyTotalRevenue());
        weeklyData.setTotalOrders(chartService.countWeeklyOrders());
        weeklyData.setDiningLocation(chartService.countWeeklyDiningLocation());
        weeklyData.setFoodCategory(chartService.countWeeklyFoodCategory());
        weeklyData.setHotProducts(chartService.weeklyHotProduct());
        weeklyData.setChartData(chartService.findWeeklyData());

        return weeklyData;
    }
// 整合近 30 日數據
    @GetMapping("/monthlyData")
    public @ResponseBody ChartData getMonthlyData() {
        ChartData monthlyData = new ChartData();

        monthlyData.setTotalRevenue(chartService.calMonthlyTotalRevenue());
        monthlyData.setTotalOrders(chartService.countMonthlyOrders());
        monthlyData.setDiningLocation(chartService.countMonthlyDiningLocation());
        monthlyData.setFoodCategory(chartService.countMonthlyFoodCategory());
        monthlyData.setHotProducts(chartService.monthlyHotProduct());
        monthlyData.setChartData(chartService.findMonthlyData());

        return monthlyData;
    }
// 整合用戶指定範圍數據
    @GetMapping("/customData")
    public @ResponseBody ChartData getCustomData(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        ChartData customData = new ChartData();

        customData.setTotalRevenue(chartService.calCustomTotalRevenue(startDate, endDate));
        customData.setTotalOrders(chartService.countCustomOrders(startDate, endDate));
        customData.setDiningLocation(chartService.countCustomDiningLocation(startDate, endDate));
        customData.setFoodCategory(chartService.countCustomFoodCategory(startDate, endDate));
        customData.setHotProducts(chartService.customHotProduct(startDate, endDate));
        customData.setChartData(chartService.findCustomData(startDate, endDate));

        return customData;
    }
	
	
//	-----------------------------------------------------------------
	
	// 測試用
	
	// 日營業總額
	@GetMapping("/dailyTotalRevenue")
	public @ResponseBody Integer calDailyTotalRevenue() {

		return chartService.calDailyTotalRevenue();
	}

	// 日總訂單數
	@GetMapping("/dailyOrders")
	public @ResponseBody Integer countDailyOrders() {

		return chartService.countDailyOrders();
	}

	// 日外帶內用比例
	@GetMapping("/dailyDingingLocation")
	public @ResponseBody List<Object[]> countDailyDiningLocation() {

		return chartService.countDailyDiningLocation();
	}

	// 日產品類別比例
	@GetMapping("/dailyFoodCategory")
	public @ResponseBody List<Object[]> countDailyFoodCategory() {

		return chartService.countDailyFoodCategory();
	}

	// 日熱賣產品排行
	@GetMapping("/dailyHotProduct")
	public @ResponseBody List<Map<String, Object>> dailyHotProduct() {
		List<Map<String, Object>> dailyHotProduct = chartService.dailyHotProduct();
		
		return dailyHotProduct;
	}
	
	// 日營業額訂單量每小時數據
	@GetMapping("/dailyChart")
	public @ResponseBody List<List<Object>> findDailyData() {
		return chartService.findDailyData();
	}

//	-----------------------------------------------------------------
	// 近 7 天營業總額
	@GetMapping("/weeklyTotalRevenue")
	public @ResponseBody Integer calWeeklyTotalRevenue() {

		return chartService.calWeeklyTotalRevenue();
	}

	// 近 7 天總訂單數
	@GetMapping("/weeklyOrders")
	public @ResponseBody Integer countWeeklyOrders() {

		return chartService.countWeeklyOrders();
	}

	// 近 7 天外帶內用比例
	@GetMapping("/weeklyDingingLocation")
	public @ResponseBody List<Object[]> countWeeklyDiningLocation() {

		return chartService.countWeeklyDiningLocation();
	}

	// 近 7 天產品類別比例
	@GetMapping("/weeklyFoodCategory")
	public @ResponseBody List<Object[]> countWeeklyFoodCategory() {

		return chartService.countWeeklyFoodCategory();
	}

	// 近 7 天熱賣產品排行
	@GetMapping("/weeklyHotProduct")
	public @ResponseBody List<Map<String, Object>> weeklyHotProduct() {
		List<Map<String, Object>> weeklyHotProduct = chartService.weeklyHotProduct();
		
		return weeklyHotProduct;
	}

	// 近 7 天營業額訂單量每日數據
	@GetMapping("/weeklyChart")
	public @ResponseBody List<List<Object>> findWeeklyData() {
		
		return chartService.findWeeklyData();
	}

//	-----------------------------------------------------------------
	// 近 30 天營業總額
	@GetMapping("/monthlyTotalRevenue")
	public @ResponseBody Integer calMonthlyTotalRevenue() {

		return chartService.calMonthlyTotalRevenue();
	}

	// 近 30 天總訂單數
	@GetMapping("/monthlyOrders")
	public @ResponseBody Integer countMonthlyOrders() {

		return chartService.countMonthlyOrders();
	}

	// 近 30 天外帶內用比例
	@GetMapping("/monthlyDingingLocation")
	public @ResponseBody List<Object[]> countMonthlyDiningLocation() {

		return chartService.countMonthlyDiningLocation();
	}

	// 近 30 天產品類別比例
	@GetMapping("/monthlyFoodCategory")
	public @ResponseBody List<Object[]> countMonthlyFoodCategory() {

		return chartService.countMonthlyFoodCategory();
	}

	// 近 30 天熱賣產品排行
	@GetMapping("/monthlyHotProduct")
	public @ResponseBody List<Map<String, Object>> monthlyHotProduct() {
		List<Map<String, Object>> monthlyHotProduct = chartService.monthlyHotProduct();
		
		return monthlyHotProduct;
	}

	// 近 30 天營業額訂單量每日數據
	@GetMapping("/monthlyChart")
	public @ResponseBody List<List<Object>> findMonthlyData() {
		
		return chartService.findMonthlyData();
	}

//	-----------------------------------------------------------------
	// 用戶指定範圍營業總額
	@GetMapping("/customTotalRevenue")
	public @ResponseBody Integer calCustomTotalRevenue(
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {

		return chartService.calCustomTotalRevenue(startDate, endDate);
	}

	// 用戶指定日期範圍訂單數
	@GetMapping("/customOrders")
	public @ResponseBody Integer countCustomOrders(
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {

		return chartService.countCustomOrders(startDate, endDate);
	}

	// 用戶指定日期範圍外帶內用比例
	@GetMapping("/customDingingLocation")
	public @ResponseBody List<Object[]> countCustomDiningLocation(
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {

		return chartService.countCustomDiningLocation(startDate, endDate);
	}

	// 用戶指定日期範圍產品類別比例
	@GetMapping("/customFoodCategory")
	public @ResponseBody List<Object[]> countCustomFoodCategory(
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {

		return chartService.countCustomFoodCategory(startDate, endDate);
	}

	// 用戶指定日期範圍熱賣產品排行
	@GetMapping("/customHotProduct")
	public @ResponseBody List<Map<String, Object>> customHotProduct(
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
		List<Map<String, Object>> customHotProduct = chartService.customHotProduct(startDate, endDate);

		return customHotProduct;
	}

	// 用戶自訂範圍營業額訂單量每日數據
	@GetMapping("/customChart")
	public @ResponseBody List<List<Object>> findCustomData(
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {

		return chartService.findCustomData(startDate, endDate);
	}
//	-----------------------------------------------------------------

//	物件陣列
//	//日熱賣產品排行
//	@GetMapping("/dailyHotProduct")
//	public @ResponseBody List<Object[]> dailyHotProduct() {
//		return chartService.dailyHotProduct();
//	}
//	
//	//週熱賣產品排行
//	@GetMapping("/weeklyHotProduct")
//	public @ResponseBody List<Object[]> weeklyHotProduct() {
//		return chartService.weeklyHotProduct();
//	}
//		
//	//月熱賣產品排行
//	@GetMapping("/monthlyHotProduct")
//	public @ResponseBody List<Object[]> monthlyHotProduct() {
//		return chartService.monthlyHotProduct();
//	}
	// 測試查詢原始資料
//	@GetMapping("/testOriginData")
//	public @ResponseBody List<Object[]> findOriginData(){
//		return chartService.findDailyData();	
//	}

}
