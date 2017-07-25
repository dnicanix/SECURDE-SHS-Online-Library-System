package edu.securde.beans;

public class RegisteredUser {

	/**DATABASE INFORMATION**/
	public static final String TABLE_NAME = "users";
	public static final String COLUMN_USERID = "userid";
	public static final String COLUMN_IDNUM = "idnum";
	public static final String COLUMN_ACTIVE = "active";
	public static final String COLUMN_USERTYPE = "usertype";
	public static final String COLUMN_FIRSTNAME = "firstname";
	public static final String COLUMN_MIDDLEINITIAL = "middleinitial";
	public static final String COLUMN_LASTNAME = "lastname";
	public static final String COLUMN_USERNAME = "username";
	public static final String COLUMN_PASSWORD = "password";
	public static final String COLUMN_EMAILADDRESS = "emailaddress";
	public static final String COLUMN_BIRTHDAY = "birthday";
	public static final String COLUMN_SECRETQUESTION = "secretquestion";
	public static final String COLUMN_SECRETANSWER = "secretanswer";
	
	private int userid;
	private String idnum;
	private int active;
	private int usertype;
	private String firstname;
	private String middleinitial;
	private String lastname;
	private String username;
	private String password;
	private String emailaddress;
	private String birthday;
	private String secretquestion;
	private String secretanswer;
	
	public RegisteredUser(){
		
	}
	
	
	public RegisteredUser(String idnum, int active, int usertype, String firstname, String middleinitial,
			String lastname, String username, String password, String emailaddress, String birthday,
			String secretquestion, String secretanswer) {
		super();
		this.idnum = idnum;
		this.active = active;
		this.usertype = usertype;
		this.firstname = firstname;
		this.middleinitial = middleinitial;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.emailaddress = emailaddress;
		this.birthday = birthday;
		this.secretquestion = secretquestion;
		this.secretanswer = secretanswer;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getIdnum() {
		return idnum;
	}

	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getUsertype() {
		return usertype;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddleinitial() {
		return middleinitial;
	}

	public void setMiddleinitial(String middleinitial) {
		this.middleinitial = middleinitial;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSecretquestion() {
		return secretquestion;
	}

	public void setSecretquestion(String secretquestion) {
		this.secretquestion = secretquestion;
	}

	public String getSecretanswer() {
		return secretanswer;
	}

	public void setSecretanswer(String secretanswer) {
		this.secretanswer = secretanswer;
	}
	
}
