package edu.securde.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import edu.securde.beans.BorrowedReading;
import edu.securde.beans.Readings;
import edu.securde.db.DBPool;

public class BorrowedReadingService {

	public static void reserveReading(BorrowedReading borrowedreading){
		//INSERT INTO	borrowed_readings(readingid, userid, datereserved)
		//VALUES (?, ?, CURDATE());
		
		String sql = "INSERT INTO " + BorrowedReading.TABLE_NAME + "("
				+ BorrowedReading.COLUMN_READINGID + ", " + BorrowedReading.COLUMN_USERID + ", "
				+ BorrowedReading.COLUMN_DATERESERVED + ") VALUES (?, ?, CURDATE())";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, borrowedreading.getReadingid());
			pstmt.setInt(2, borrowedreading.getUserid());
			
			pstmt.executeUpdate();
			System.out.println("Borrowed Reading successfully added in DB!!!");
			
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
		
		int readingid = borrowedreading.getReadingid();
		String status = "Reserved";
		updateReadingStatus(readingid, status);
		
	}
	
	public static boolean borrowReading(int readingid, int userid){
		boolean isUpdateSuccess = false;
		
		//check the borrowedreadings with the readingid and userid
		//SELECT borrowedreadingid
		//FROM borrowed_readings
		//WHERE readingid = ? AND userid = ? AND dateborrowed LIKE '0000-00-00';\
		
		String checksql = "SELECT " + BorrowedReading.COLUMN_BORROWEDREADINGID 
					+ " FROM " + BorrowedReading.TABLE_NAME + " WHERE "
					+ BorrowedReading.COLUMN_READINGID + " = ? AND " + BorrowedReading.COLUMN_USERID + " = ? AND "
					+ BorrowedReading.COLUMN_DATEBORROWED + " LIKE '0000-00-00'";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int borrowedreadingid = 0;
		
		try {
			pstmt = conn.prepareStatement(checksql);
			pstmt.setInt(1, readingid);
			pstmt.setInt(2, userid);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				borrowedreadingid = rs.getInt(0);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//format due date
		Date tempdate = new Date();
		DateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");
		dateformat.format(tempdate);
		
		Calendar c = Calendar.getInstance();
		c.setTime(tempdate);
		
		c.add(Calendar.DATE, 7);
		Date duedate = c.getTime();
		String duedateString = dateformat.format(duedate);
		
		//UPDATE borrowed_readings SET dateborrowed = ?, duedate = ?
		//WHERE borrowedreadingid = ? AND readingid = ? AND userid = ?;
		
		String sql = "UPDATE " + BorrowedReading.TABLE_NAME + " SET " + BorrowedReading.COLUMN_DATEBORROWED + "= CURDATE(), "
				+ BorrowedReading.COLUMN_DUEDATE + "= ? WHERE " + BorrowedReading.COLUMN_BORROWEDREADINGID + "= ? AND "
				+ BorrowedReading.COLUMN_READINGID + " = ? AND " + BorrowedReading.COLUMN_USERID + "= ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, duedateString);
			pstmt.setInt(2, readingid);
			pstmt.setInt(3, userid);
			
			pstmt.executeQuery();
			System.out.println("Reading successfully borrowed:: Update in DB!!");
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
		
		String status = "OUT";
		isUpdateSuccess =updateReadingStatus(readingid, status);
		
		return isUpdateSuccess;		
	}
	
	public static boolean returnReading(BorrowedReading borrowedreading){
		boolean isUpdateSuccess = false;
		//UPDATE borrowed_readings SET datereturned = ?
		//WHERE readingid = ? AND userid = ?;
		
		String sql = "UPDATE " + BorrowedReading.TABLE_NAME + " SET " + BorrowedReading.COLUMN_DATERETURNED 
				+ "= ? WHERE " + BorrowedReading.COLUMN_READINGID + " = ? AND " + BorrowedReading.COLUMN_USERID + "= ?";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, borrowedreading.getDatereturned());
			pstmt.setInt(3, borrowedreading.getReadingid());
			pstmt.setInt(4, borrowedreading.getUserid());
			
			pstmt.executeQuery();
			System.out.println("Reading successfully returned:: Update in DB!!");
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
		
		int readingid = borrowedreading.getReadingid();
		String status = "Available";
		
		isUpdateSuccess =updateReadingStatus(readingid, status);
		
		return isUpdateSuccess;	
	}
	
	public static boolean updateReadingStatus(int readingid, String status){
		boolean isUpdateSuccess = false;

		//UPDATE readings SET status = 'Reserve' WHERE readingid = ?
		String sql = "UPDATE " + Readings.TABLE_NAME + " SET " + Readings.COLUMN_STATUS 
						+ " = ? WHERE " + Readings.COLUMN_READINGID + " = ?";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
				
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, status);
			pstmt.setInt(2, readingid);
					
			pstmt.executeUpdate();
			System.out.println("Status Update in DB!!");
			isUpdateSuccess = true;
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
		
		
		return isUpdateSuccess;
	}
	
	//get all CURRENTLY BORROWED readings of a user
	public static ArrayList<BorrowedReading> getBorrowedReadingsOfUser(int userid){
		ArrayList<BorrowedReading> borrowedreadingList = new ArrayList<BorrowedReading>();
		
		//SELECT *
		//FROM borrowed_readings BR INNER JOIN readings R ON BR.readingid = R.readingid
		//WHERE BR.userid = ?
		//ORDER BY BR.dateborrowed DESC;

		String sql = "SELECT * FROM " + BorrowedReading.TABLE_NAME + " BR INNER JOIN " 
				+ Readings.TABLE_NAME + "  R ON " + "BR." + BorrowedReading.COLUMN_READINGID + "= R." + Readings.COLUMN_READINGID
				+ " WHERE BR." + BorrowedReading.COLUMN_USERID + " = ? ORDER BY BR." + BorrowedReading.COLUMN_DATEBORROWED + " DESC";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userid);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return borrowedreadingList;
	}
	
	//get all CURRENTLY RESERVED readings of a user
	
	//get borrowed reading history of a book
}
