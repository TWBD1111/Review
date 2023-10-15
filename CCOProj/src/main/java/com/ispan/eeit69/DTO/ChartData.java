package com.ispan.eeit69.DTO;

import java.util.List;
import java.util.Map;

public class ChartData {

	private Integer totalRevenue;
	private Integer totalOrders;
	private List<Object[]> diningLocation;
	private List<Object[]> foodCategory;
	private List<Map<String, Object>> hotProducts;
	private List<List<Object>> chartData;

	public ChartData() {
		super();
	}

	public ChartData(Integer totalRevenue, Integer totalOrders, List<Object[]> diningLocation,
			List<Object[]> foodCategory, List<Map<String, Object>> hotProducts, List<List<Object>> chartData) {
		super();
		this.totalRevenue = totalRevenue;
		this.totalOrders = totalOrders;
		this.diningLocation = diningLocation;
		this.foodCategory = foodCategory;
		this.hotProducts = hotProducts;
		this.chartData = chartData;
	}

	public Integer getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(Integer totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public Integer getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(Integer totalOrders) {
		this.totalOrders = totalOrders;
	}

	public List<Object[]> getDiningLocation() {
		return diningLocation;
	}

	public void setDiningLocation(List<Object[]> diningLocation) {
		this.diningLocation = diningLocation;
	}

	public List<Object[]> getFoodCategory() {
		return foodCategory;
	}

	public void setFoodCategory(List<Object[]> foodCategory) {
		this.foodCategory = foodCategory;
	}

	public List<Map<String, Object>> getHotProducts() {
		return hotProducts;
	}

	public void setHotProducts(List<Map<String, Object>> hotProducts) {
		this.hotProducts = hotProducts;
	}

	public List<List<Object>> getChartData() {
		return chartData;
	}

	public void setChartData(List<List<Object>> chartData) {
		this.chartData = chartData;
	}

	
	
}