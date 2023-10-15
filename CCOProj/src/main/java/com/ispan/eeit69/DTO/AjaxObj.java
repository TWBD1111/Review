package com.ispan.eeit69.DTO;

public class AjaxObj {
	private String invitationCode;
	private String username;
    private String password;
    private String password2;
    
	    public AjaxObj() {}

		public AjaxObj(String invitationCode, String username, String password, String password2) {
			super();
			this.invitationCode = invitationCode;
			this.username = username;
			this.password = password;
			this.password2 = password2;
		}

		public String getInvitationCode() {
			return invitationCode;
		}

		public void setInvitationCode(String invitationCode) {
			this.invitationCode = invitationCode;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getPassword2() {
			return password2;
		}

		public void setPassword2(String password2) {
			this.password2 = password2;
		}

		
}