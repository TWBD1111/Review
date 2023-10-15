package com.ispan.eeit69.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static Date startOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        setToBeginningOfDay(calendar);
        return calendar.getTime();
    }

    // 將今天設為最後一天
    public static Date endOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        setToEndOfDay(calendar);
        return calendar.getTime();
    }
    
    //近 7 七天的起始日
    public static Date getStartDateForLastSevenDays(Date currentDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_YEAR, -6); // 減去6天，得到最近7天的開始日期
        setToBeginningOfDay(calendar);
        return calendar.getTime();
    }
 

    //近 30 天的起始日
    public static Date getStartDateForLastThirtyDays(Date currentDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_YEAR, -30); // 減去6天，得到最近7天的開始日期
        setToBeginningOfDay(calendar);
        return calendar.getTime();
    }
    
    
    public static Date plusDate(Date currentDate) {
    	Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
    }
    
    

    private static void setToBeginningOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    private static void setToEndOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
    }
    
//  //此法抓的第一天為星期日
//    public static Date getFirstDayOfWeek(Date date) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
//        setToBeginningOfDay(calendar);
//        return calendar.getTime();
//    }
//    
//  //此法抓的最後一天為星期六
//    public static Date getLastDayOfWeek(Date date) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6);
//        setToEndOfDay(calendar);
//        return calendar.getTime();
//    }
    
//    public static Date getFirstDayOfMonth(Date date) {
//      Calendar calendar = Calendar.getInstance();
//      calendar.setTime(date);
//      calendar.set(Calendar.DAY_OF_MONTH, 1);
//      setToBeginningOfDay(calendar);
//      return calendar.getTime();
//  }
//
//  public static Date getLastDayOfMonth(Date date) {
//      Calendar calendar = Calendar.getInstance();
//      calendar.setTime(date);
//      calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//      setToEndOfDay(calendar);
//      return calendar.getTime();
//  }
    
}