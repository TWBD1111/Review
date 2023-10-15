package com.ispan.eeit69.service;

public interface InvitationEmailService {

	String generateSecureRandomString(int length);

	void sendSimpleEmail(String toEmail, String body, String subject);

}