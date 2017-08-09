package edu.securde.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.owasp.esapi.ESAPI;

import edu.securde.beans.ReadingCategory;
import edu.securde.beans.Readings;
import edu.securde.db.DBPool;

public class ReadingsService {
	
	public static ArrayList<Readings> getAllReadings(){
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
				r.setReadingtitle(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_READINGTITLE)));
				r.setCategoryid(rs.getInt(Readings.COLUMN_CATEGORYID));
				r.setLocation(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_LOCATION)));
				r.setAuthor(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_AUTHOR)));
				r.setPublisher(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_PUBLISHER)));
				r.setYear(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_YEAR)));
				r.setTags(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_TAGS)));
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

	public static ArrayList<Readings> getReadingsByCategory(int category){
		ArrayList<Readings> readingslist = new ArrayList<Readings>();
		
		/***Get the readings list***/
		//SELECT *
		//FROM readings
		//ORDER BY readingtitle ASC;
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM " + Readings.TABLE_NAME + " WHERE " +
					 Readings.COLUMN_CATEGORYID  + " = ?" + " ORDER BY " + 
					 Readings.COLUMN_READINGTITLE + " ASC";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, category);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Readings r = new Readings();
				r.setReadingid(rs.getInt(Readings.COLUMN_READINGID));
				r.setReadingtitle(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_READINGTITLE)));
				r.setCategoryid(rs.getInt(Readings.COLUMN_CATEGORYID));
				r.setLocation(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_LOCATION)));
				r.setAuthor(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_AUTHOR)));
				r.setPublisher(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_PUBLISHER)));
				r.setYear(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_YEAR)));
				r.setTags(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_TAGS)));
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

	public static boolean addReading(Readings reading){
		//INSERT INTO readings(readingtitle, categoryid, location, author, publisher, year, tags, status)
		//VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);
		boolean isAddSuccess = false;
		
		String sql = "INSERT INTO " + Readings.TABLE_NAME + "("
				+ Readings.COLUMN_READINGTITLE + ",  "
				+ Readings.COLUMN_CATEGORYID + ", " + Readings.COLUMN_LOCATION + ", " 
				+ Readings.COLUMN_AUTHOR + ", " + Readings.COLUMN_PUBLISHER + ", "
				+ Readings.COLUMN_YEAR + ", " + Readings.COLUMN_TAGS + ", "
				+ Readings.COLUMN_STATUS + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		
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
			isAddSuccess = true;
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
		return isAddSuccess;
		
	}
	
	public static boolean updateReading(Readings reading){
		boolean isUpdateSuccess = false;
		
		//UPDATE readings SET readingtitle = ?, author = ?, publisher = ?, year = ?, tags = ?
		//WHERE readingid = ?;
		
		int readingid = reading.getReadingid();
		String newReadingTitle = reading.getReadingtitle();
		int newCategoryId = reading.getCategoryid();
		String newLocation = reading.getLocation();
		String newAuthor = reading.getAuthor();
		String newPublisher = reading.getPublisher();
		String newYear = reading.getYear();
		String newTags = reading.getTags();
		String newStatus = reading.getStatus();
		
		String sql = "UPDATE " + Readings.TABLE_NAME + " SET " + Readings.COLUMN_READINGTITLE + "=?, "
				+ Readings.COLUMN_CATEGORYID + "=?," + Readings.COLUMN_LOCATION + "=?, " 
				+ Readings.COLUMN_AUTHOR + "=?, " + Readings.COLUMN_PUBLISHER + "=?, "
				+ Readings.COLUMN_YEAR + "=?, " + Readings.COLUMN_TAGS + "=?, "
				+ Readings.COLUMN_STATUS + "=? WHERE "
				+ Readings.COLUMN_READINGID + "=? ";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newReadingTitle);
			pstmt.setInt(2, newCategoryId);
			pstmt.setString(3, newLocation);
			pstmt.setString(4, newAuthor);
			pstmt.setString(5, newPublisher);
			pstmt.setString(6, newYear);
			pstmt.setString(7, newTags);
			pstmt.setString(8, newStatus);
			pstmt.setInt(9, readingid);
			
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
	
	public static Readings getReadingsByID(int readingid){
		
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
				r.setReadingtitle(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_READINGTITLE)));
				r.setCategoryid(rs.getInt(Readings.COLUMN_CATEGORYID));
				r.setLocation(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_LOCATION)));
				r.setAuthor(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_AUTHOR)));
				r.setPublisher(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_PUBLISHER)));
				r.setYear(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_YEAR)));
				r.setTags(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_TAGS)));
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
	
	public static ArrayList<Readings> getReadingsByCategory(String category){
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
	
	public static ArrayList<Readings> getReadingsByTitle(String title){
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
				r.setReadingtitle(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_READINGTITLE)));
				r.setCategoryid(rs.getInt(Readings.COLUMN_CATEGORYID));
				r.setLocation(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_LOCATION)));
				r.setAuthor(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_AUTHOR)));
				r.setPublisher(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_PUBLISHER)));
				r.setYear(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_YEAR)));
				r.setTags(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_TAGS)));
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
	
	public static ArrayList<Readings> getReadingsByAuthor(String author){
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
				r.setReadingtitle(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_READINGTITLE)));
				r.setCategoryid(rs.getInt(Readings.COLUMN_CATEGORYID));
				r.setLocation(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_LOCATION)));
				r.setAuthor(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_AUTHOR)));
				r.setPublisher(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_PUBLISHER)));
				r.setYear(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_YEAR)));
				r.setTags(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_TAGS)));
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
	
	public static ArrayList<Readings> getReadingsByPublisher(String publisher){
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
				r.setReadingtitle(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_READINGTITLE)));
				r.setCategoryid(rs.getInt(Readings.COLUMN_CATEGORYID));
				r.setLocation(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_LOCATION)));
				r.setAuthor(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_AUTHOR)));
				r.setPublisher(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_PUBLISHER)));
				r.setYear(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_YEAR)));
				r.setTags(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_TAGS)));
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
	
	public static ArrayList<Readings> getReadingsByYear(String year){
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
				r.setReadingtitle(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_READINGTITLE)));
				r.setCategoryid(rs.getInt(Readings.COLUMN_CATEGORYID));
				r.setLocation(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_LOCATION)));
				r.setAuthor(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_AUTHOR)));
				r.setPublisher(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_PUBLISHER)));
				r.setYear(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_YEAR)));
				r.setTags(ESAPI.encoder().encodeForHTML(rs.getString(Readings.COLUMN_TAGS)));
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
	
	public static boolean deleteReadings(int readingid){
		boolean isDeleteSuccess = false;
		String sql = "DELETE FROM " + Readings.TABLE_NAME 
				+ " WHERE " + Readings.COLUMN_READINGID + " = ?";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, readingid);
			
			pstmt.executeUpdate();
			System.out.println("Readings successfully deleted in DB!!!");
			isDeleteSuccess=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isDeleteSuccess=false;
		}finally{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return isDeleteSuccess;
	}
}
