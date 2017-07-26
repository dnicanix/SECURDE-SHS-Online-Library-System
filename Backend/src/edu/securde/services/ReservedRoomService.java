package edu.securde.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.securde.beans.*;
import edu.securde.db.DBPool;
public class ReservedRoomService {
	
	public static ArrayList<ReservedRoom> getReservedRoomsByDateAndRoom(String date, int roomid){
		
		ArrayList<ReservedRoom> reservedRooms = new ArrayList<>();
		//SELECT *
		//FROM rooms R, reserved_rooms RR
		//WHERE RR.date LIKE '07-22-2017' and R.roomid = RR.roomid and R.roomname = "123";
		
		String sql = "SELECT * " +
					 "FROM " + ReservedRoom.TABLE_NAME +
					 " WHERE " + ReservedRoom.COLUMN_DATE + " LIKE ? and " + ReservedRoom.COLUMN_ROOMID +
					 			   " = ?";
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setInt(2, roomid);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				ReservedRoom rr = new ReservedRoom();
				rr.setRoomid(rs.getInt(ReservedRoom.COLUMN_ROOMID));
				rr.setUserid(rs.getInt(ReservedRoom.COLUMN_USERID));
				rr.setDate(rs.getString(ReservedRoom.COLUMN_DATE));
				rr.setStarttime(rs.getString(ReservedRoom.COLUMN_STARTTIME));
				rr.setEndtime(rs.getString(ReservedRoom.COLUMN_ENDTIME));
				
				reservedRooms.add(rr);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}finally{
			try {
				pstmt.close();
				conn.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return reservedRooms;
	}
	
	public static boolean reserveMeetingRoom(ReservedRoom reservedRoom){
		//INSERT INTO reserved_rooms(roomid, userid, date, starttime, endtime)
		//VALUES (?, ?, ?, ?, ?);
		boolean isReserveSuccess = false;
		
		String sql = "INSERT INTO " + ReservedRoom.TABLE_NAME + "("
				+ ReservedRoom.COLUMN_ROOMID + ", " + ReservedRoom.COLUMN_USERID + ", "
				+ ReservedRoom.COLUMN_DATE + ", " + ReservedRoom.COLUMN_STARTTIME + ", "
				+ ReservedRoom.COLUMN_ENDTIME + ") VALUES (?, ?, ?, ?, ?)";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reservedRoom.getRoomid());
			pstmt.setInt(2, reservedRoom.getUserid());
			pstmt.setString(3, reservedRoom.getDate());
			pstmt.setString(4, reservedRoom.getStarttime());
			pstmt.setString(5, reservedRoom.getEndtime());
			
			pstmt.executeUpdate();
			System.out.println("Reserved meeting room successfully!!!");
			isReserveSuccess = true;
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
		return isReserveSuccess;
	}

}
