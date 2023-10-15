package com.ispan.eeit69.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ispan.eeit69.model.InvitationCodeForm;

@Repository
public interface InvitationCodeRepository extends JpaRepository<InvitationCodeForm, Long> {
	InvitationCodeForm findByInvitationCode(String invitationCode);
    
	default void addInvitationCodeAndLevelAndUsed(String invitationCode, String level) {
		InvitationCodeForm form = new InvitationCodeForm();
		form.setInvitationCode(invitationCode);
		form.setLevel(level);
		save(form);
	}
	
	 // 新增擴展方法來尋找特定 invitationCode 的 level
    @Query("SELECT level FROM InvitationCodeForm WHERE invitationCode = :invitationCode")
    String findLevelByInvitationCode(@Param("invitationCode") String invitationCode);
	
	 default void deleteByInvitationCodeAndLevel(String invitationCode, String level) {
	        InvitationCodeForm form = findByInvitationCode(invitationCode);
	        if (form != null && form.getLevel().equals(level)) {
	            delete(form);
	        }
	    }
}