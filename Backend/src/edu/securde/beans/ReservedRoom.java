package edu.securde.beans;

public class ReservedRoom {

	public static final String TABLE_NAME = "reserved_rooms";
	public static final String COLUMN_RESERVEDROOMID = "reservedroomid";
	public static final String COLUMN_ROOMID = "roomid";
	public static final String COLUMN_USERID = "userid";
	public static final String COLUMN_DATE = "date";
	public static final String COLUMN_TIMESLOTID = "timeslotid";
	
	private int reservedroomid;
	private int roomid;
	private int userid;
	private String date;
	private int timeslotid;
	
	public ReservedRoom(){
		
	}

	public ReservedRoom(int roomid, int userid, String date, int timeslotid) {
		super();
		this.roomid = roomid;
		this.userid = userid;
		this.date = date;
		this.timeslotid = timeslotid;
	}

	public int getReservedroomid() {
		return reservedroomid;
	}

	public void setReservedroomid(int reservedroomid) {
		this.reservedroomid = reservedroomid;
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

	public int getTimeslotid() {
		return timeslotid;
	}

	public void setTimeslotid(int timeslotid) {
		this.timeslotid = timeslotid;
	}

	
}
