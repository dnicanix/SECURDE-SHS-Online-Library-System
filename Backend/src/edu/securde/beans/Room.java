package edu.securde.beans;

public class Room {

	public static String TABLE_NAME = "rooms";
	public static String COLUMN_ROOMID = "roomid";
	public static String COLUMN_ROOMNAME = "roomname";
	
	private int roomid;
	private String roomname;
	
	public Room(){
		
	}

	public Room(int roomid, String roomname) {
		super();
		this.roomid = roomid;
		this.roomname = roomname;
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
}
