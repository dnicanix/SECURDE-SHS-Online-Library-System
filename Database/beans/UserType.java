package edu.securde.beans;

public class UserType {

	public static final String TABLE_NAME = "users_type";
	public static final String COLUMN_USERTYPEID = "usertypeid";
	public static final String COLUMN_NAMETYPE = "nametype";
	
	private int usertypeid;
	private String nametype;
	
	public UserType(){
		
	}

	public UserType(int usertypeid, String nametype) {
		super();
		this.usertypeid = usertypeid;
		this.nametype = nametype;
	}

	public int getUsertypeid() {
		return usertypeid;
	}

	public void setUsertypeid(int usertypeid) {
		this.usertypeid = usertypeid;
	}

	public String getNametype() {
		return nametype;
	}

	public void setNametype(String nametype) {
		this.nametype = nametype;
	}
	
}
