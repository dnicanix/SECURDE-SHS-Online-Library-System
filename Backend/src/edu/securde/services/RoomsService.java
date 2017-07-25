package edu.securde.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.securde.beans.ReservedRoom;
import edu.securde.db.DBPool;

public class RoomsService {
	
	//get available rooms
	
	//get reserved rooms
	public static ArrayList<ReservedRoom> getReservedRooms(String date){
		ArrayList<ReservedRoom> roomList = new ArrayList<ReservedRoom>();
		/*SELECT *
		 *FROM reserved_rooms
		 *WHERE date = ''*/
		
		String sql = "SELECT * FROM " + ReservedRoom.TABLE_NAME + " WHERE " + ReservedRoom.COLUMN_DATE + "= ?";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			
			pstmt.executeQuery();
			while(rs.next()){
				ReservedRoom rr = new ReservedRoom();
				rr.setReservedroomid(rs.getInt(ReservedRoom.COLUMN_RESERVEDROOMID));
				rr.setRoomid(rs.getInt(ReservedRoom.COLUMN_ROOMID));
				rr.setUserid(rs.getInt(ReservedRoom.COLUMN_USERID));
				rr.setDate(rs.getString(ReservedRoom.COLUMN_DATE));
				rr.setTimeslotid(rs.getInt(ReservedRoom.COLUMN_TIMESLOTID));
				roomList.add(rr);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return roomList;
	}
	
	//get rooms
	
	//reserve a room
	public static boolean reserveRoom(ReservedRoom reservedroom){
		boolean isValid = false;
		
		/*INSERT INTO reserved_rooms(roomid, userid, date, timeslotid)
		 *VALUES(?, ?, ?, ?) */
		
		String sql = "INSERT INTO " + ReservedRoom.TABLE_NAME + "(" + ReservedRoom.COLUMN_ROOMID + ", "
				+ ReservedRoom.COLUMN_USERID + ", " + ReservedRoom.COLUMN_DATE + ", " + ReservedRoom.COLUMN_TIMESLOTID
				+ ") VALUES(?, ?, ?, ?)";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reservedroom.getRoomid());
			pstmt.setInt(2, reservedroom.getUserid());
			pstmt.setString(3, reservedroom.getDate());
			pstmt.setInt(4, reservedroom.getTimeslotid());
			
			pstmt.executeUpdate();
			System.out.println("Reserved Room successfully!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return isValid;
	}
	
	//cancel a reservation
	

}
