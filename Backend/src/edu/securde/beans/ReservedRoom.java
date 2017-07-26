package edu.securde.beans;

public class ReservedRoom {

	public static final String TABLE_NAME = "reserved_rooms";
	public static final String COLUMN_ROOMID = "roomid";
	public static final String COLUMN_USERID = "userid";
	public static final String COLUMN_DATE = "date";
	public static final String COLUMN_STARTTIME = "starttime";
	public static final String COLUMN_ENDTIME = "endtime";
	
	private int roomid;
	private int userid;
	private String date;
	private String starttime;
	private String endtime;
	
	public ReservedRoom(){
		
	}

	public ReservedRoom(int roomid, int userid, String date, String starttime, String endtime) {
		super();
		this.roomid = roomid;
		this.userid = userid;
		this.date = date;
		this.starttime = starttime;
		this.endtime = endtime;
	}

	public int getRoomid() {
		return roomid;
	}

	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
}
