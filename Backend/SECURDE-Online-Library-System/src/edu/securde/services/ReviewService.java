package edu.securde.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.securde.beans.Review;
import edu.securde.db.DBPool;

public class ReviewService {

	public static void addReview(Review review){
		//INSERT INTO reviews(readingid, userid, review, reviewdate)
		//VALUES (?, ?, ?, ?);
		
		String sql = "INSERT INTO " + Review.TABLE_NAME + "(" + Review.COLUMN_READINGID + ", "
				+ Review.COLUMN_USERID + ", " + Review.COLUMN_REVIEW + ", " 
				+ Review.COLUMN_REVIEWDATE + ") VALUES (?, ?, ?, CURDATE())";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, review.getReadingid());
			pstmt.setInt(2, review.getUserid());
			pstmt.setString(3, review.getReview());
			
			pstmt.executeQuery();
			System.out.println("Review is added successfully in DB!!!");
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
	
	//get all reviews of a book
	public static ArrayList<Review> getReviewsOfBook(int readingid){
		ArrayList<Review> reviewList = new ArrayList<Review>();
		
		//SELECT *
		//FROM reviews
		//WHERE readingid = ?
		//ORDER BY reviewdate DESC;
		
		String sql = "SELECT * FROM " + Review.TABLE_NAME + " WHERE " + Review.COLUMN_READINGID 
				+ "= ? ORDER BY " + Review.COLUMN_REVIEWDATE + " DESC";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, readingid);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				Review r = new Review();
				r.setReadingid(rs.getInt(Review.COLUMN_READINGID));
				r.setUserid(rs.getInt(Review.COLUMN_USERID));
				r.setReview(rs.getString(Review.COLUMN_REVIEW));
				r.setReviewdate(rs.getString(Review.COLUMN_REVIEWDATE));
				reviewList.add(r);
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
		
		return reviewList;
	}
	
	//get all reviews of a user
	public static ArrayList<Review> getReviewsOfUser(int userid){
		ArrayList<Review> reviewList = new ArrayList<Review>();
		
		//SELECT *
		//FROM reviews
		//WHERE userid = ?
		//ORDER BY reviewdate DESC;
		
		String sql = "SELECT * FROM " + Review.TABLE_NAME + " WHERE " + Review.COLUMN_USERID 
				+ "= ? ORDER BY " + Review.COLUMN_REVIEWDATE + " DESC";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userid);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				Review r = new Review();
				r.setReadingid(rs.getInt(Review.COLUMN_READINGID));
				r.setUserid(rs.getInt(Review.COLUMN_USERID));
				r.setReview(rs.getString(Review.COLUMN_REVIEW));
				r.setReview(rs.getString(Review.COLUMN_REVIEWDATE));
				reviewList.add(r);
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
		
		return reviewList;
	}
	
}
