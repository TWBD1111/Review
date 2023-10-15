package com.ispan.eeit69.controller.website;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ispan.eeit69.service.BusinessUserService;
import com.ispan.eeit69.service.Impl.BusinessUserServiceImpl;
import com.ispan.eeit69.utils.BCrypt;

@RestController
public class BusinessInformationController {

	@Autowired
	private BusinessUserServiceImpl businessUserService;

	@GetMapping("/getBusinessInfo")
	public ResponseEntity<?> getBusinessInfo(HttpSession httpSession) {
		  Object usernameObject = httpSession.getAttribute("username");
		  String userName = usernameObject.toString(); //轉換為字符串，這個步驟可能會根據你存儲的數據類型而變。
		 Map<String, Object> businessInfo = businessUserService.getBusinessInformation(userName);
	    System.out.println("Username from HttpSession: " + userName);
		if(businessInfo != null && !businessInfo.isEmpty()) {
		    String storeName = (String) businessInfo.get("storeName");
		    String storeAddress = (String) businessInfo.get("storeAddress");
		    String contactPerson = (String) businessInfo.get("contactPerson");
		    String phoneNum = (String) businessInfo.get("phoneNum");
		    System.out.println("Store Name: " + storeName);
		    System.out.println("Store Address: " + storeAddress);
		    System.out.println("Contact Person: " + contactPerson);
		    System.out.println("Phone Number: " + phoneNum);
		    businessInfo.put("userName", userName);
		    return ResponseEntity.ok(businessInfo);
		} else {
			String errorMessage = "No business info found for the given username.";// "帳號或密碼錯誤"
		    System.out.println("No business info found for the given username.");
		    businessInfo.put("errorMessage", errorMessage);
		    return ResponseEntity.ok(businessInfo);
		}
	}
	
	@PostMapping(value = "/UpdateByPassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> UpdateByPassword(@RequestBody businessInfoModel BusinessInfoForm,HttpSession httpSession) {
		Object usernameObject = httpSession.getAttribute("username");
		 String username = usernameObject.toString();
		Map<String, Object> businessInfoResponse = new HashMap<>();
		businessInfoResponse.put("userName", username);
	    System.out.println("更改密碼的帳號名稱為 " + username);
		String NewPassword = BusinessInfoForm.getNewPassword();
		String bcryptHashPassword = BCrypt.hashpw( NewPassword, BCrypt.gensalt());
		businessUserService.updatePasswordByUsername(username, bcryptHashPassword);
		businessInfoResponse.put("passwordErrorMessage", "密碼更改成功");
		businessInfoResponse.put("redirect", "BusinessInformation");
		return ResponseEntity.ok(businessInfoResponse);
	}

	@PostMapping(value = "/StoreInfoServlet", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> readBusinessInfo(@RequestBody businessInfoModel BusinessInfoForm,HttpSession httpSession) {
		Object usernameObject = httpSession.getAttribute("username");
		String userName = usernameObject.toString(); //轉換為字符串，這個步驟可能會根據你存儲的數據類型而變。
		String StoreName = BusinessInfoForm.getStoreName();
		String StoreAddress = BusinessInfoForm.getStoreAddress();
		String ContactPerson = BusinessInfoForm.getContactPerson();
		String PhoneNum = BusinessInfoForm.getPhoneNum();
		System.out.println("StoreName=" + StoreName + "  ;  " + "StoreName=" + StoreName + "  ;  " + "StoreAddress="
				+ StoreAddress + "  ;  " + "ContactPerson=" + ContactPerson + "  ;  " + "PhoneNum=" + PhoneNum);
		businessUserService.updateBusinessInfo(userName, StoreName, StoreAddress, ContactPerson, PhoneNum);
		Map<String, Object> businessInfoResponse = new HashMap<>();
		String errorMessage = "更新成功";// "帳號或密碼錯誤"
	    businessInfoResponse.put("errorMessage", errorMessage);
		businessInfoResponse.put("redirect", "BusinessInformation");
		return ResponseEntity.ok(businessInfoResponse);
	}

	public static class businessInfoModel {
		private String NewPassword;
		private String StoreName;
		private String ContactPerson;
		private String StoreAddress;
		private String PhoneNum;

		public businessInfoModel() {
		}

		public businessInfoModel(String newPassword, String storeName, String contactPerson, String storeAddress,
				String phoneNum) {
			super();
			NewPassword = newPassword;
			StoreName = storeName;
			ContactPerson = contactPerson;
			StoreAddress = storeAddress;
			PhoneNum = phoneNum;
		}

		public String getNewPassword() {
			return NewPassword;
		}

		public void setNewPassword(String newPassword) {
			NewPassword = newPassword;
		}

		public String getStoreName() {
			return StoreName;
		}

		public void setStoreName(String storeName) {
			StoreName = storeName;
		}

		public String getContactPerson() {
			return ContactPerson;
		}

		public void setContactPerson(String contactPerson) {
			ContactPerson = contactPerson;
		}

		public String getStoreAddress() {
			return StoreAddress;
		}

		public void setStoreAddress(String storeAddress) {
			StoreAddress = storeAddress;
		}

		public String getPhoneNum() {
			return PhoneNum;
		}

		public void setPhoneNum(String phoneNum) {
			PhoneNum = phoneNum;
		}

	}
}
