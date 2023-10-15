package com.ispan.eeit69.controller.website;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;  // 引入 HttpSession

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ispan.eeit69.DTO.AjaxObj;
import com.ispan.eeit69.service.BusinessUserService;
import com.ispan.eeit69.service.Impl.BusinessUserServiceImpl;
import com.ispan.eeit69.utils.BCrypt;

@RestController
public class LoginController {

//    @Autowired
//    private BusinessUsersRepository businessUsersRepository;
	@Autowired
	private BusinessUserServiceImpl businessUserService;

    @PostMapping(value = "/checkUsernameAndPassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> checkUsernameAndpassword(@RequestBody AjaxObj loginForm, HttpSession session) { // 注入HttpSession
    	
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        String getPassword = businessUserService.findPasswordByUsername(username);
        
        // 返回响应
        Map<String, Object> loginResponse = new HashMap<>();

        String businessInformationForm = businessUserService.findByUsername(username);
        if (businessInformationForm != null) {
            if (BCrypt.checkpw(password, getPassword)) {
            	 String level = businessUserService.findLevelByUsername(username);
                // 增加Session
                session.setAttribute("username", username);  // 將用戶名存儲在Session中
                session.setAttribute("level", level);  // 將用戶名存儲在Session中
                loginResponse.put("exists", false);
                loginResponse.put("redirect", "BusinessInformation");  // 指定跳轉頁面的路徑
                return ResponseEntity.ok(loginResponse);
            } else {
                String errorMessage = "密碼錯誤";// "帳號或密碼錯誤"
                loginResponse.put("exists", true);
                loginResponse.put("message", errorMessage);
                return ResponseEntity.ok(loginResponse);
            }
        } else {
            String errorMessage = "帳號錯誤";
            loginResponse.put("exists", true);
            loginResponse.put("message", errorMessage);
            return ResponseEntity.ok(loginResponse);
        }
    }
}
