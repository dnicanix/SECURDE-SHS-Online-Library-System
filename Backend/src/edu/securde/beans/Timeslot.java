package edu.securde.beans;

public class Timeslot {

	public static String TABLE_NAME = "timeslot";
	public static String COLUMN_TIMESLOTID = "timeslotid";
	public static String COLUMN_ROOMID = "roomid";
	public static String COLUMN_STARTTIME = "starttime";
	public static String COLUMN_ENDTIME = "starttime";
	
	public int timeslotid;
	public int roomid;
	public String starttime;
	public String endttime;
	
	public Timeslot(){
		
	}

	public Timeslot(int roomid, String starttime, String endttime) {
		super();
		this.roomid = roomid;
		this.starttime = starttime;
		this.endttime = endttime;
	}
	
	public int getTimeslotid() {
		return timeslotid;
	}

	public void setTimeslotid(int timeslotid) {
		this.timeslotid = timeslotid;
	}

	public int getRoomid() {
		return roomid;
	}

	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndttime() {
		return endttime;
	}

	public void setEndttime(String endttime) {
		this.endttime = endttime;
	}
	
	
}
