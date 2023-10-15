package com.ispan.eeit69.service.Impl;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ispan.eeit69.service.InvitationEmailService;

@Service
public class InvitationEmailServiceImpl implements InvitationEmailService {
	  @Autowired
	    private JavaMailSender mailSender;
	  
		@Override
		public String generateSecureRandomString(int length) {
		        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		        StringBuilder stringBuilder = new StringBuilder(length);
		        SecureRandom secureRandom = new SecureRandom();
		        
		        for (int i = 0; i < length; i++) {
		            stringBuilder.append(characters.charAt(secureRandom.nextInt(characters.length())));
		        }
		        System.out.println("Generated Secure Random String: " + stringBuilder.toString());
		        return stringBuilder.toString();
		    }
		     
	    @Override
		public void sendSimpleEmail(String toEmail, String body, String subject) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setFrom("starpensive@gmail.com");
	        message.setTo(toEmail);
	        message.setText(body);
	        message.setSubject(subject);

	        mailSender.send(message);
	        System.out.println("Mail Send...");
	    }
	    
}
