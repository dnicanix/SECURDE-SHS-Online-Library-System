package edu.securde.beans;

public class Room {

	public static String TABLE_NAME = "rooms";
	public static String COLUMN_ROOMID = "roomid";
	public static String COLUMN_ROOMNAME = "roomname";
	public static String COLUMN_STATUS = "status";
	
	private int roomid;
	private String roomname;
	private String status;
	
	public Room(){
		
	}

	public Room(int roomid, String roomname, String status) {
		super();
		this.roomid = roomid;
		this.roomname = roomname;
		this.status = status;
	}

	public int getRoomid() {
		return roomid;
	}

	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}

	public String getRoomname() {
		return roomname;
	}

	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
