package com.ispan.eeit69.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.eeit69.model.BusinessInformationForm;

@Repository
public interface BusinessUsersRepository extends JpaRepository<BusinessInformationForm, Long> {
	BusinessInformationForm findByUsername(String username);
	
	@Query("SELECT new map(b.storeName as storeName, b.storeAddress as storeAddress, b.contactPerson as contactPerson, b.phoneNum as phoneNum) FROM BusinessInformationForm b WHERE b.username = :username")
	Map<String, Object> findBusinessInfoByUsername(@Param("username") String username);

	
//	@Modifying // 表明這是一個修改數據庫的操作
//    @Query("UPDATE BusinessInformationForm SET storeName = :storeName, storeAddress = :storeAddress, contactPerson = :contactPerson, phoneNum = :phoneNum WHERE username = :username")
//    void updateBusinessInfoByUsername(
//            @Param("username") String username,
//            @Param("storeName") String storeName,
//            @Param("storeAddress") String storeAddress,
//            @Param("contactPerson") String contactPerson,
//            @Param("phoneNum") String phoneNum);

    default void updateBusinessInfo(String username, String newStoreName, String newStoreAddress, String newContactPerson, String newPhoneNum) {
    }

	// 新增擴展方法來尋找特定使用者名稱的密碼
	@Query("SELECT password FROM BusinessInformationForm WHERE username = :username")
	String findPasswordByUsername(@Param("username") String username);

	default void addUsernameAndPassword(String username, String password) {
	}

	// 新增擴展方法來新增特定使用者名稱的 level
	@Modifying
	@Query("UPDATE BusinessInformationForm SET level = :level WHERE username = :username")
	void addLevelByUsername(@Param("username") String username, @Param("level") String level);
	
	//尋找特定使用者名稱的 level
	@Query("SELECT level FROM BusinessInformationForm WHERE username = :username")
	String findLevelByUsername(@Param("username") String username);

	default void updateLevelByUsername(String username, String newLevel) {
		BusinessInformationForm user = findByUsername(username);
		if (user != null) {
			user.setLevel(newLevel);
			save(user);
			System.out.println("更新方案帳號" + username + "更新方案為" + newLevel);
		}
	}

}
