package com.ispan.eeit69.controller.bgsys;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
// 定義 Controller 需要共用的功能
public class AbstractController {
   // 需要處裡前端送來之日期型態的控制器(如處理新增，修改的控制器)都需要下列方法	
   @InitBinder
   public void bindingDate(WebDataBinder binder) {
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	   CustomDateEditor cde = new CustomDateEditor(sdf, true);
	   binder.registerCustomEditor(Date.class, cde);
	   SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   CustomDateEditor cde2 = new CustomDateEditor(sdf2, true);
	   binder.registerCustomEditor(java.util.Date.class, cde2);
   }
}
