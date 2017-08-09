package edu.securde.beans;

public class LoginActivity {

	public static final String TABLE_NAME ="login_activity";
	public static final String COLUMN_USERID = "userid";
	public static final String COLUMN_LOGINATTEMPTS= "loginattempts";
	
	private int userid;
	private int loginattempts;
	
	public LoginActivity(){
		
	}
	public LoginActivity(int userid, int loginattempts) {
		super();
		this.userid = userid;
		this.loginattempts = loginattempts;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getLoginattempts() {
		return loginattempts;
	}
	public void setLoginattempts(int loginattempts) {
		this.loginattempts = loginattempts;
	}
	
	
}
