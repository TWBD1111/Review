package com.ispan.eeit69.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ispan.eeit69.repository.CompleteOrderRepository;
import com.ispan.eeit69.service.ChartService;
import com.ispan.eeit69.utils.DateUtils;

@Service
public class ChartServiceImpl implements ChartService {

	@Autowired
	CompleteOrderRepository chartRp;

	Date currentDate = new Date();

//	@Override
//	public List<CompleteOrder> findAll() {
//		
//		return chartRp.findAll();
//	}
	
	
	// 日營業總額
	@Override
	public Integer calDailyTotalRevenue() {
		Integer dailyRevenue = chartRp.calRevenue(DateUtils.startOfDay(currentDate), DateUtils.endOfDay(currentDate));

		return dailyRevenue;
	}

	// 日總訂單數
	@Override
	public Integer countDailyOrders() {
		Integer dailyOrders = chartRp.countOrders(DateUtils.startOfDay(currentDate), DateUtils.endOfDay(currentDate));

		return dailyOrders;
	}

	// 日外帶內用比例
	@Override
	public List<Object[]> countDailyDiningLocation() {
		List<Object[]> dailyDiningLocation = chartRp.countDiningLocation(DateUtils.startOfDay(currentDate),
				DateUtils.endOfDay(currentDate));

		return dailyDiningLocation;
	}

	// 日產品類別比例
	@Override
	public List<Object[]> countDailyFoodCategory() {
		List<Object[]> dailyFoodCategory = chartRp.countFoodCategory(DateUtils.startOfDay(currentDate),
				DateUtils.endOfDay(currentDate));

		return dailyFoodCategory;
	}
	
	// 日熱賣產品排行
	@Override
	public List<Map<String, Object>> dailyHotProduct() {
        List<Object[]> dailyData = chartRp.hotProduct(DateUtils.startOfDay(currentDate),
                DateUtils.endOfDay(currentDate));
        List<Map<String, Object>> dailyHotProduct = new ArrayList<>();
        int ranking = 1;

        for (Object[] item : dailyData) {
            Map<String, Object> data = new HashMap<>();
            data.put("productName", item[0]);
            data.put("productQuantity", item[1]);
            data.put("productPrice", item[2]);
            data.put("ranking", ranking);
            dailyHotProduct.add(data);
            ranking++;
        }
        return dailyHotProduct;
    }
	
	
	// 日營業額訂單量數據
	public List<List<Object>> findDailyData() {
		List<Object[]> originDatas = chartRp.findHourData(DateUtils.startOfDay(currentDate),
				DateUtils.endOfDay(currentDate));

		List<Object> dates = new ArrayList<>();
		List<Object> orders = new ArrayList<>();
		List<Object> revenues = new ArrayList<>();

		for (Object[] data : originDatas) {

			String date = (String) data[0];
			Long revenue = (Long) data[1];
			Long order = (Long) data[2];

			String formattedDate = date.replace(" ", " <br> ");

			dates.add(formattedDate);
			revenues.add(revenue);
			orders.add(order);
		}

		List<List<Object>> result = new ArrayList<>();
		result.add(dates);
		result.add(revenues);
		result.add(orders);

		return result;

	}

//	-----------------------------------------------------------------	

	// 近 7 天營業總額
	@Override
	public Integer calWeeklyTotalRevenue() {
		Integer weekRevenue = chartRp.calRevenue(DateUtils.getStartDateForLastSevenDays(currentDate),
				DateUtils.endOfDay(currentDate));

		return weekRevenue;
	}

	// 近 7 天總訂單數
	@Override
	public Integer countWeeklyOrders() {
		Integer weekOrders = chartRp.countOrders(DateUtils.getStartDateForLastSevenDays(currentDate),
				DateUtils.endOfDay(currentDate));

		return weekOrders;
	}

	// 近 7 天外帶內用比例
	@Override
	public List<Object[]> countWeeklyDiningLocation() {
		List<Object[]> weekDiningLocation = chartRp.countDiningLocation(
				DateUtils.getStartDateForLastSevenDays(currentDate), DateUtils.endOfDay(currentDate));

		return weekDiningLocation;
	}

	// 近 7 天產品類別比例
	@Override
	public List<Object[]> countWeeklyFoodCategory() {
		List<Object[]> weeklyFoodCategory = chartRp.countFoodCategory(
				DateUtils.getStartDateForLastSevenDays(currentDate), DateUtils.endOfDay(currentDate));

		return weeklyFoodCategory;
	}

	// 近 7 天熱賣產品排行
	@Override
	public List<Map<String, Object>> weeklyHotProduct() {
        List<Object[]> weeklyData = chartRp.hotProduct(DateUtils.getStartDateForLastSevenDays(currentDate),
            DateUtils.endOfDay(currentDate));

        List<Map<String, Object>> weeklyHotProduct = new ArrayList<>();
        int ranking = 1;

        for (Object[] item : weeklyData) {
            Map<String, Object> data = new HashMap<>();
            data.put("productName", item[0]);
            data.put("productQuantity", item[1]);
            data.put("productPrice", item[2]);
            data.put("ranking", ranking); // 添加排名變數
            weeklyHotProduct.add(data);
            ranking++; // 增加排名
        }

        return weeklyHotProduct;
    }

	// 近 7 天營業額訂單量每日數據
	@Override
	public List<List<Object>> findWeeklyData() {
		List<Object[]> originDatas = chartRp.findDailyData(DateUtils.getStartDateForLastSevenDays(currentDate),
				DateUtils.endOfDay(currentDate));

		List<Object> dates = new ArrayList<>();
		List<Object> orders = new ArrayList<>();
		List<Object> revenues = new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd EEEE");

		for (Object[] data : originDatas) {

			Date date = (Date) data[0];
			Long revenue = (Long) data[1];
			Long order = (Long) data[2];

			String formattedDate = dateFormat.format(date).replace(" ", " <br> ");

			dates.add(formattedDate);
			revenues.add(revenue);
			orders.add(order);
		}

		List<List<Object>> result = new ArrayList<>();
		result.add(dates);
		result.add(revenues);
		result.add(orders);

		return result;
	}

//	-----------------------------------------------------------------

	// 近 30 天營業總額
	@Override
	public Integer calMonthlyTotalRevenue() {
		Integer monthRevenue = chartRp.calRevenue(DateUtils.getStartDateForLastThirtyDays(currentDate),
				DateUtils.endOfDay(currentDate));

		return monthRevenue;
	}

	// 近 30 天總訂單數
	@Override
	public Integer countMonthlyOrders() {
		Integer monthOrders = chartRp.countOrders(DateUtils.getStartDateForLastThirtyDays(currentDate),
				DateUtils.endOfDay(currentDate));

		return monthOrders;
	}

	// 近 30 天外帶內用比例
	@Override
	public List<Object[]> countMonthlyDiningLocation() {
		List<Object[]> monthDiningLocation = chartRp.countDiningLocation(
				DateUtils.getStartDateForLastThirtyDays(currentDate), DateUtils.endOfDay(currentDate));

		return monthDiningLocation;
	}

	// 近 30 天產品類別比例
	@Override
	public List<Object[]> countMonthlyFoodCategory() {
		List<Object[]> monthlyFoodCategory = chartRp.countFoodCategory(
				DateUtils.getStartDateForLastThirtyDays(currentDate), DateUtils.endOfDay(currentDate));

		return monthlyFoodCategory;
	}

	// 近 30 天熱賣產品排行
	@Override
	public List<Map<String, Object>> monthlyHotProduct() {
        List<Object[]> monthlyData = chartRp.hotProduct(DateUtils.getStartDateForLastThirtyDays(currentDate),
                DateUtils.endOfDay(currentDate));

        List<Map<String, Object>> monthlyHotProduct = new ArrayList<>();
        int ranking = 1;

        for (Object[] item : monthlyData) {
            Map<String, Object> data = new HashMap<>();
            data.put("productName", item[0]);
            data.put("productQuantity", item[1]);
            data.put("productPrice", item[2]);
            data.put("ranking", ranking);
            monthlyHotProduct.add(data);
            ranking++;
        }

        return monthlyHotProduct;

    }

	// 近 30 天營業額訂單量每日數據
	@Override
	public List<List<Object>> findMonthlyData() {
		List<Object[]> originDatas = chartRp.findDailyData(DateUtils.getStartDateForLastThirtyDays(currentDate),
				DateUtils.endOfDay(currentDate));

		List<Object> dates = new ArrayList<>();
		List<Object> orders = new ArrayList<>();
		List<Object> revenues = new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd EEEE");

		for (Object[] data : originDatas) {

			Date date = (Date) data[0];
			Long revenue = (Long) data[1];
			Long order = (Long) data[2];

			String formattedDate = dateFormat.format(date).replace(" ", " <br> ");

			dates.add(formattedDate);
			revenues.add(revenue);
			orders.add(order);
		}

		List<List<Object>> result = new ArrayList<>();
		result.add(dates);
		result.add(revenues);
		result.add(orders);

		return result;
	}

//		-----------------------------------------------------------------
	// 用戶指定範圍營業總額
	@Override
	public Integer calCustomTotalRevenue(Date startDate, Date endDate) {
		Integer customRevenue = chartRp.calRevenue(startDate, DateUtils.plusDate(endDate));

		return customRevenue;
	}

	// 用戶指定日期範圍訂單數
	@Override
	public Integer countCustomOrders(Date startDate, Date endDate) {
		Integer customOrders = chartRp.countOrders(startDate, DateUtils.plusDate(endDate));

		return customOrders;
	}

	// 用戶指定日期範圍外帶內用比例
	@Override
	public List<Object[]> countCustomDiningLocation(Date startDate, Date endDate) {
		List<Object[]> customDiningLocation = chartRp.countDiningLocation(startDate, DateUtils.plusDate(endDate));

		return customDiningLocation;
	}

	// 用戶指定日期範圍產品類別比例
	@Override
	public List<Object[]> countCustomFoodCategory(Date startDate, Date endDate) {
		List<Object[]> customFoodCategory = chartRp.countFoodCategory(startDate, DateUtils.plusDate(endDate));

		return customFoodCategory;
	}

	public List<Map<String, Object>> customHotProduct(Date startDate, Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date nextDay = calendar.getTime();

        List<Object[]> customData = chartRp.hotProduct(startDate, nextDay);

        List<Map<String, Object>> customHotProduct = new ArrayList<>();
        int ranking = 1;

        for (Object[] item : customData) {
            Map<String, Object> data = new HashMap<>();
            data.put("productName", item[0]);
            data.put("productQuantity", item[1]);
            data.put("productPrice", item[2]);
            data.put("ranking", ranking);
            customHotProduct.add(data);
            ranking++;
        }

        return customHotProduct;
    }

	// 用戶指定日期範圍營業額及訂單量
	@Override
	public List<List<Object>> findCustomData(Date startDate, Date endDate) {
		List<Object[]> originDatas = chartRp.findDailyData(startDate, DateUtils.plusDate(endDate));

		List<Object> dates = new ArrayList<>();
		List<Object> orders = new ArrayList<>();
		List<Object> revenues = new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd EEEE");

		for (Object[] data : originDatas) {

			Date date = (Date) data[0];
			Long revenue = (Long) data[1];
			Long order = (Long) data[2];

			String formattedDate = dateFormat.format(date).replace(" ", " <br> ");

			dates.add(formattedDate);
			revenues.add(revenue);
			orders.add(order);
		}

		List<List<Object>> result = new ArrayList<>();
		result.add(dates);
		result.add(revenues);
		result.add(orders);

		return result;
	}
	
	// -----------------------------------------------------------------

		// 營業額&訂單量分析圖

		// 測試查詢分析圖原始資料
//		public List<Object[]> findDailyData() {
//			List<Object[]> originData = chartRp.findDailyData(DateUtils.getStartDateForLastSevenDays(currentDate),
//					DateUtils.endOfDay(currentDate));
//			return originData;
//		}

}
