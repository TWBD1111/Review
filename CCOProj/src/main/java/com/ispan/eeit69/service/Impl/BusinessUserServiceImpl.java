package com.ispan.eeit69.service.Impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.eeit69.model.BusinessInformationForm;
import com.ispan.eeit69.model.InvitationCodeForm;
import com.ispan.eeit69.repository.BusinessUsersRepository;
import com.ispan.eeit69.repository.InvitationCodeRepository;
import com.ispan.eeit69.service.BusinessUserService;

@Service
public class BusinessUserServiceImpl implements BusinessUserService {
    @Autowired
    private BusinessUsersRepository businessUsersRepository;
    @Autowired
    private InvitationCodeRepository invitationCodeRepository;
    
    @Override
	public InvitationCodeForm findByInvitationCode(String invitationCode) {
    InvitationCodeForm invitationCodeForm = invitationCodeRepository.findByInvitationCode(invitationCode);
    return invitationCodeForm;
    }

    @Override
	public String findLevelByInvitationCode(String invitationCode) {
    	String invitationCodeForm = invitationCodeRepository.findLevelByInvitationCode(invitationCode);
    	 if (invitationCodeForm != null) {
         	System.out.println("找到的邀請碼為: " + invitationCode);
             return invitationCodeForm;
         } else {
             return null;  // 或者返回"没有找到"，根据你的需求
         }
    }
    
    @Override
	public void deleteByInvitationCodeAndLevel(String invitationCode, String level) {
    	invitationCodeRepository.deleteByInvitationCodeAndLevel(invitationCode,level);
    }
    

    @Override
	public String findByUsername(String username) {
        BusinessInformationForm user = businessUsersRepository.findByUsername(username);
        if (user != null) {
        	System.out.println("使用者為: " + username);
            return user.getUsername();
        } else {
            return null;  // 或者返回"没有找到"，根据你的需求
        }
    }
    
    @Override
	public Map<String, Object> getBusinessInformation(String username) {
        return businessUsersRepository.findBusinessInfoByUsername(username);
    }
    
    @Override
	@Transactional
    public void updateBusinessInfo(String username, String newStoreName, String newStoreAddress, String newContactPerson, String newPhoneNum) {
    	  BusinessInformationForm user = businessUsersRepository.findByUsername(username);
          if (user != null) {
              user.setStoreName(newStoreName);
              user.setStoreAddress(newStoreAddress);
              user.setContactPerson(newContactPerson);
              user.setPhoneNum(newPhoneNum);
              businessUsersRepository.save(user);
              System.out.println("更新商戶信息：用戶名稱 " + username);
          }else{
          	System.out.println("商戶信息不存在");
          }
    }
    
    @Override
	@Transactional
    public void updatePasswordByUsername(String username, String newPassword) {
        BusinessInformationForm user = businessUsersRepository.findByUsername(username);
        if (user != null) {
            user.setPassword(newPassword);
            businessUsersRepository.save(user);
        }
    }
    
    @Override
	public String findPasswordByUsername(String username) {
        String password = businessUsersRepository.findPasswordByUsername(username);
        if (password != null) {
            System.out.println("找到的密碼是: " + password);
            return password;
        } else {
            System.out.println("沒有找到與該 username 相關的密碼");
            return null;
        }
 	}
    
    @Override
	@Transactional
    public void addUsernameAndPassword(String username, String password) {
    	BusinessInformationForm businessInformationForm = new BusinessInformationForm();
		businessInformationForm.setUsername(username);
		businessInformationForm.setPassword(password);
		businessUsersRepository.save(businessInformationForm);
		System.out.println("增帳號" + username + "新增密碼" + password);
    }
    
    @Override
	@Transactional
    public void updateLevelByUsername(String username, String newLevel) {
    	businessUsersRepository.addLevelByUsername(username, newLevel);
    }
 	
    @Override
	public String findLevelByUsername(String username) {
        String level = businessUsersRepository.findLevelByUsername(username);
        if (level != null) {
            System.out.println("找到的 level 是: " + level);
            return level;
        } else {
            System.out.println("沒有找到與該 username 相關的 level");
            return null;
        }
    }

    
    
}