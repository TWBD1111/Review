package com.ispan.eeit69.controller.website;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ispan.eeit69.model.InvitationCodeForm;
import com.ispan.eeit69.repository.InvitationCodeRepository;
import com.ispan.eeit69.service.InvitationEmailService;

@RestController
public class EnterInvitationCodeController {

	@Autowired
	private InvitationCodeRepository invitationCodeRepository;
	@Autowired
	private InvitationEmailService invitationEmailService;

	@PostMapping(value = "/enterInvitationCode", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> enterInvitationCode(@RequestBody InvitationModel invitationCodeForm) {
		
		String invitationCode = invitationEmailService.generateSecureRandomString(20);
		String invitationGmail = invitationCodeForm.getInvitationGmail();
		String level = invitationCodeForm.getLevel();
		
		// 返回响应
		Map<String, Object> invitationCodeResponse = new HashMap<>();
		InvitationCodeForm findinvitationCode = invitationCodeRepository.findByInvitationCode(invitationCode);
		if (findinvitationCode != null) {
			System.out.println("邀請碼重複 " + invitationCode);
			String errorMessage = "邀請碼重複";
			invitationCodeResponse.put("message", errorMessage);
			return ResponseEntity.ok(invitationCodeResponse);
		} else {
			String errorMessage = "驗證碼註冊成功，郵件已發送";
			String subject ="歡迎加入ChopChop大家庭";
			String body ="您的邀請碼為"+ invitationCode;
			invitationEmailService.sendSimpleEmail(invitationGmail, body, subject);
			invitationCodeRepository.addInvitationCodeAndLevelAndUsed(invitationCode, level);
			invitationCodeResponse.put("message", errorMessage);
			return ResponseEntity.ok(invitationCodeResponse);
		}
	}

	public static class InvitationModel {
		private String invitationCode;
		private String invitationGmail;
		private String level;
		
		 public InvitationModel() {}

		public InvitationModel(String invitationCode, String invitationGmail, String level) {
			super();
			this.invitationCode = invitationCode;
			this.invitationGmail = invitationGmail;
			this.level = level;
		}

		public String getInvitationCode() {
			return invitationCode;
		}

		public void setInvitationCode(String invitationCode) {
			this.invitationCode = invitationCode;
		}

		public String getInvitationGmail() {
			return invitationGmail;
		}

		public void setInvitationGmail(String invitationGmail) {
			this.invitationGmail = invitationGmail;
		}

		public String getLevel() {
			return level;
		}

		public void setLevel(String level) {
			this.level = level;
		}
		 
		 
	}
}