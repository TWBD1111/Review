package com.ispan.eeit69.controller.website;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {

	@Autowired
	private JavaMailSender emailSender;

	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}
	
	
	@PostMapping("/contactForm")
	public String sendContactForm(@RequestParam("brandName") String brandName,
			@RequestParam("companyName") String companyName, @RequestParam("city") String city,
			@RequestParam("services") String services, @RequestParam("contactName") String contactName,
			@RequestParam("contactPhone") String contactPhone, @RequestParam("contactEmail") String contactEmail,
			@RequestParam("comments") String comments) {

		// 創建一個簡單的郵件消息
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo("chopchoporder69@gmail.com"); // 收件人的郵箱地址
		mailMessage.setSubject(brandName + "諮詢表單 From Website"); // 郵件主題
		mailMessage.setText(
				"品牌名稱： " + brandName + 
				"\n公司名稱： " + companyName +
				"\n所在縣市： " + city +
				"\n想了解服務： " + services +
				"\n聯絡人： " + contactName +
				"\n聯絡電話： " + contactPhone +
				"\nEmail： " + contactEmail + 
				"\n備註： " + comments

		); // 郵件內容

		// 使用郵件發送器發送郵件
		emailSender.send(mailMessage);

		// 重定向到感謝頁面或其他頁面
		return "redirect:/thanks";
	}
	
	
	@GetMapping("/thanks")
	public String thanks() {
		return "thanks";
	}
	
}
