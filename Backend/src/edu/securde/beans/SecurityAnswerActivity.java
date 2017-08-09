package edu.securde.beans;

public class SecurityAnswerActivity {
	
	public static final String TABLE_NAME ="securityanswer_activity";
	public static final String COLUMN_USERID = "userid";
	public static final String COLUMN_ANSWERATTEMPTS= "answer_attempts";
	
	private int userid;
	private int answer_attempts;
	
	public SecurityAnswerActivity(){
		
	}
	
	public SecurityAnswerActivity(int userid, int answer_attempts) {
		super();
		this.userid = userid;
		this.answer_attempts = answer_attempts;
	}
	
	public int getUserid() {
		return userid;
	}
	
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public int getAnswer_attempts() {
		return answer_attempts;
	}
	
	public void setAnswer_attempts(int answer_attempts) {
		this.answer_attempts = answer_attempts;
	}
}
