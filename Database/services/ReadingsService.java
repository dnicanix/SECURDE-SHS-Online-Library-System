package securde.edu.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import securde.edu.beans.ReadingCategory;
import securde.edu.beans.Readings;
import securde.edu.db.DBPool;

public class ReadingsService {

	public static void addReading(Readings reading){
		//INSERT INTO readings(readingtitle, categoryid, location, author, publisher, year, tags, status)
		//VALUES (?, ?, ?, ?, ?, ?, ?, ?);
		
		String sql = "INSERT INTO " + Readings.TABLE_NAME + "("
				+ Readings.COLUMN_READINGTITLE + ", "
				+ Readings.COLUMN_CATEGORYID + ", " + Readings.COLUMN_LOCATION + ", " 
				+ Readings.COLUMN_AUTHOR + ", " + Readings.COLUMN_PUBLISHER + ", "
				+ Readings.COLUMN_YEAR + ", " + Readings.COLUMN_TAGS 
				+ Readings.COLUMN_STATUS + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reading.getReadingtitle());
			pstmt.setInt(2, reading.getCategoryid());
			pstmt.setString(3, reading.getLocation());
			pstmt.setString(4, reading.getAuthor());
			pstmt.setString(5, reading.getPublisher());
			pstmt.setString(6, reading.getYear());
			pstmt.setString(7, reading.getTags());
			pstmt.setString(8, reading.getStatus());
			
			pstmt.executeUpdate();
			System.out.println("Readings successfully aded in DB!!!");
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
		
	}
	
	public static boolean updateReading(Readings reading){
		boolean isUpdateSuccess = false;
		
		//UPDATE readings SET readingtitle = ?, author = ?, publisher = ?, year = ?, tags = ?
		//WHERE readingid = ?;
		
		String newReadingTitle = reading.getReadingtitle();
		String newAuthor = reading.getAuthor();
		String newPublisher = reading.getPublisher();
		String newYear = reading.getYear();
		String newTags = reading.getTags();
		String newStatus = reading.getStatus();
		
		String sql = "UPDATE " + Readings.TABLE_NAME + " SET " + Readings.COLUMN_READINGTITLE + "=?, "
				+ Readings.COLUMN_AUTHOR + "=?, " + Readings.COLUMN_PUBLISHER + "=?, "
				+ Readings.COLUMN_YEAR + "=?, " + Readings.COLUMN_TAGS + "=?, "
				+ Readings.COLUMN_STATUS + "=? WHERE "
				+ Readings.COLUMN_READINGID + "=? ";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newReadingTitle);
			pstmt.setString(2, newAuthor);
			pstmt.setString(3, newPublisher);
			pstmt.setString(4, newYear);
			pstmt.setString(5, newTags);
			pstmt.setString(6, newStatus);
			
			pstmt.executeUpdate();
			
			System.out.println("UPDATE IN DB::SUCCESS!");
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
	
	public Readings getReadingsByID(int readingid){
		
		/***Get the readings***/
		//SELECT *
		//FROM readings
		//WHERE readingid = 1;
		
		Readings r = null;
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM " + Readings.TABLE_NAME + " WHERE " 
		+ Readings.COLUMN_READINGID + " = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, readingid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				r = new Readings();
				r.setReadingid(rs.getInt(Readings.COLUMN_READINGID));
				r.setReadingtitle(rs.getString(Readings.COLUMN_READINGTITLE));
				r.setCategoryid(rs.getInt(Readings.COLUMN_CATEGORYID));
				r.setLocation(rs.getString(Readings.COLUMN_LOCATION));
				r.setAuthor(rs.getString(Readings.COLUMN_AUTHOR));
				r.setPublisher(rs.getString(Readings.COLUMN_PUBLISHER));
				r.setYear(rs.getString(Readings.COLUMN_YEAR));
				r.setTags(rs.getString(Readings.COLUMN_TAGS));
				r.setStatus(rs.getString(Readings.COLUMN_STATUS));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		return r;
	}
	
	public ArrayList<Readings> getReadingsByCategory(String category){
		ArrayList<Readings> readingslist = new ArrayList<Readings>();
		
		/***Getting the categoryid***/
		String categorysql = "SELECT " + ReadingCategory.COLUMN_CATEGORYID 
				+ " FROM " + ReadingCategory.TABLE_NAME + " WHERE " 
				+ ReadingCategory.COLUMN_CATEGORY + " = ?";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int categoryid = 0;
		
		try {
			pstmt = conn.prepareStatement(categorysql);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
			while(rs.next()){
				categoryid = rs.getInt(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/***Get the readings list***/
		//SELECT *
		//FROM readings
		//WHERE categoryid = 1
		//ORDER BY readingtitle ASC;
		
		String sql = "SELECT * FROM " + Readings.TABLE_NAME + " WHERE " 
		+ Readings.COLUMN_CATEGORYID + " = ? ORDER BY " + Readings.COLUMN_READINGTITLE + " ASC";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Readings r = new Readings();
				r.setReadingid(rs.getInt(Readings.COLUMN_READINGID));
				r.setReadingtitle(rs.getString(Readings.COLUMN_READINGTITLE));
				r.setCategoryid(rs.getInt(Readings.COLUMN_CATEGORYID));
				r.setLocation(rs.getString(Readings.COLUMN_LOCATION));
				r.setAuthor(rs.getString(Readings.COLUMN_AUTHOR));
				r.setPublisher(rs.getString(Readings.COLUMN_PUBLISHER));
				r.setYear(rs.getString(Readings.COLUMN_YEAR));
				r.setTags(rs.getString(Readings.COLUMN_TAGS));
				r.setStatus(rs.getString(Readings.COLUMN_STATUS));
				readingslist.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		
		return readingslist;
	}
	
	public ArrayList<Readings> getReadingsByTitle(String title){
		ArrayList<Readings> readingslist = new ArrayList<Readings>();
		
		/***Get the readings list***/
		//SELECT *
		//FROM readings
		//WHERE readingtitle LIKE '%title%'
		//ORDER BY readingtitle ASC;
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM " + Readings.TABLE_NAME + " WHERE " 
		+ Readings.COLUMN_READINGTITLE + " LIKE '%" + title + "%' ORDER BY " + Readings.COLUMN_READINGTITLE + " ASC";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Readings r = new Readings();
				r.setReadingid(rs.getInt(Readings.COLUMN_READINGID));
				r.setReadingtitle(rs.getString(Readings.COLUMN_READINGTITLE));
				r.setCategoryid(rs.getInt(Readings.COLUMN_CATEGORYID));
				r.setLocation(rs.getString(Readings.COLUMN_LOCATION));
				r.setAuthor(rs.getString(Readings.COLUMN_AUTHOR));
				r.setPublisher(rs.getString(Readings.COLUMN_PUBLISHER));
				r.setYear(rs.getString(Readings.COLUMN_YEAR));
				r.setTags(rs.getString(Readings.COLUMN_TAGS));
				r.setStatus(rs.getString(Readings.COLUMN_STATUS));
				readingslist.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		return readingslist;
	}
	
	public ArrayList<Readings> getReadingsByAuthor(String author){
		ArrayList<Readings> readingslist = new ArrayList<Readings>();
		
		/***Get the readings list***/
		//SELECT *
		//FROM readings
		//WHERE author LIKE '%author%'
		//ORDER BY readingtitle ASC;
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM " + Readings.TABLE_NAME + " WHERE " 
		+ Readings.COLUMN_AUTHOR + " LIKE '%" + author + "%' ORDER BY " + Readings.COLUMN_READINGTITLE + " ASC";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Readings r = new Readings();
				r.setReadingid(rs.getInt(Readings.COLUMN_READINGID));
				r.setReadingtitle(rs.getString(Readings.COLUMN_READINGTITLE));
				r.setCategoryid(rs.getInt(Readings.COLUMN_CATEGORYID));
				r.setLocation(rs.getString(Readings.COLUMN_LOCATION));
				r.setAuthor(rs.getString(Readings.COLUMN_AUTHOR));
				r.setPublisher(rs.getString(Readings.COLUMN_PUBLISHER));
				r.setYear(rs.getString(Readings.COLUMN_YEAR));
				r.setTags(rs.getString(Readings.COLUMN_TAGS));
				r.setStatus(rs.getString(Readings.COLUMN_STATUS));
				readingslist.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		return readingslist;
	}
	
	public ArrayList<Readings> getReadingsByPublisher(String publisher){
		ArrayList<Readings> readingslist = new ArrayList<Readings>();
		
		/***Get the readings list***/
		//SELECT *
		//FROM readings
		//WHERE publisher LIKE '%publisher%'
		//ORDER BY readingtitle ASC;
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM " + Readings.TABLE_NAME + " WHERE " 
		+ Readings.COLUMN_PUBLISHER + " LIKE '%" + publisher + "%' ORDER BY " + Readings.COLUMN_READINGTITLE + " ASC";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Readings r = new Readings();
				r.setReadingid(rs.getInt(Readings.COLUMN_READINGID));
				r.setReadingtitle(rs.getString(Readings.COLUMN_READINGTITLE));
				r.setCategoryid(rs.getInt(Readings.COLUMN_CATEGORYID));
				r.setLocation(rs.getString(Readings.COLUMN_LOCATION));
				r.setAuthor(rs.getString(Readings.COLUMN_AUTHOR));
				r.setPublisher(rs.getString(Readings.COLUMN_PUBLISHER));
				r.setYear(rs.getString(Readings.COLUMN_YEAR));
				r.setTags(rs.getString(Readings.COLUMN_TAGS));
				r.setStatus(rs.getString(Readings.COLUMN_STATUS));
				readingslist.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		return readingslist;
	}
	
	public ArrayList<Readings> getReadingsByYear(String year){
		ArrayList<Readings> readingslist = new ArrayList<Readings>();
		
		/***Get the readings list***/
		//SELECT *
		//FROM readings
		//WHERE year LIKE '%year%'
		//ORDER BY readingtitle ASC;
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM " + Readings.TABLE_NAME + " WHERE " 
		+ Readings.COLUMN_PUBLISHER + " LIKE '%" + year + "%' ORDER BY " + Readings.COLUMN_READINGTITLE + " ASC";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Readings r = new Readings();
				r.setReadingid(rs.getInt(Readings.COLUMN_READINGID));
				r.setReadingtitle(rs.getString(Readings.COLUMN_READINGTITLE));
				r.setCategoryid(rs.getInt(Readings.COLUMN_CATEGORYID));
				r.setLocation(rs.getString(Readings.COLUMN_LOCATION));
				r.setAuthor(rs.getString(Readings.COLUMN_AUTHOR));
				r.setPublisher(rs.getString(Readings.COLUMN_PUBLISHER));
				r.setYear(rs.getString(Readings.COLUMN_YEAR));
				r.setTags(rs.getString(Readings.COLUMN_TAGS));
				r.setStatus(rs.getString(Readings.COLUMN_STATUS));
				readingslist.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		return readingslist;
	}
	
	public ArrayList<Readings> getAllReadings(){
		ArrayList<Readings> readingslist = new ArrayList<Readings>();
		
		/***Get the readings list***/
		//SELECT *
		//FROM readings
		//ORDER BY readingtitle ASC;
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM " + Readings.TABLE_NAME + " ORDER BY " + Readings.COLUMN_READINGTITLE + " ASC";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Readings r = new Readings();
				r.setReadingid(rs.getInt(Readings.COLUMN_READINGID));
				r.setReadingtitle(rs.getString(Readings.COLUMN_READINGTITLE));
				r.setCategoryid(rs.getInt(Readings.COLUMN_CATEGORYID));
				r.setLocation(rs.getString(Readings.COLUMN_LOCATION));
				r.setAuthor(rs.getString(Readings.COLUMN_AUTHOR));
				r.setPublisher(rs.getString(Readings.COLUMN_PUBLISHER));
				r.setYear(rs.getString(Readings.COLUMN_YEAR));
				r.setTags(rs.getString(Readings.COLUMN_TAGS));
				r.setStatus(rs.getString(Readings.COLUMN_STATUS));
				readingslist.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		return readingslist;
	}
	
	public static void deleteReadings(int readingid){
		String sql = "DELETE FROM " + Readings.TABLE_NAME 
				+ " WHERE " + Readings.COLUMN_READINGID + " = ?";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, readingid);
			
			pstmt.executeUpdate();
			System.out.println("Readings successfully deleted in DB!!!");
			
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
	}
}
