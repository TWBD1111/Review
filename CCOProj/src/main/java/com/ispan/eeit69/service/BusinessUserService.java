package com.ispan.eeit69.service;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.ispan.eeit69.model.InvitationCodeForm;

public interface BusinessUserService {

	InvitationCodeForm findByInvitationCode(String invitationCode);

	String findLevelByInvitationCode(String invitationCode);

	void deleteByInvitationCodeAndLevel(String invitationCode, String level);

	String findByUsername(String username);

	Map<String, Object> getBusinessInformation(String username);

	void updateBusinessInfo(String username, String newStoreName, String newStoreAddress, String newContactPerson,
			String newPhoneNum);

	void updatePasswordByUsername(String username, String newPassword);

	String findPasswordByUsername(String username);

	void addUsernameAndPassword(String username, String password);

	void updateLevelByUsername(String username, String newLevel);

	String findLevelByUsername(String username);

}