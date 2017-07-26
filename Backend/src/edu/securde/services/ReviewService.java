package edu.securde.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.securde.beans.Review;
import edu.securde.db.DBPool;
import edu.securde.beans.Readings;
import edu.securde.beans.RegisteredUser;
import edu.securde.beans.ReviewInfo;
import edu.securde.beans.UserType;

public class ReviewService {

	public static void addReview(Review review){
		//INSERT INTO reviews(readingid, userid, review, reviewdate)
		//VALUES (?, ?, ?, ?);
		
		String sql = "INSERT INTO " + Review.TABLE_NAME + "(" + Review.COLUMN_READINGID + ", "
				+ Review.COLUMN_USERID + ", " + Review.COLUMN_REVIEW + ", " 
				+ Review.COLUMN_REVIEWDATE + ") VALUES (?, ?, ?, NOW())";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, review.getReadingid());
			pstmt.setInt(2, review.getUserid());
			pstmt.setString(3, review.getReview());
			
			pstmt.executeUpdate();
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
	/*public static ArrayList<Review> getReviewsOfBook(int readingid){
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
	}*/
	
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
	
	public static ArrayList<ReviewInfo> getReviewsOfBook(int readingid){
		ArrayList<ReviewInfo> reviewInfoList = new ArrayList<ReviewInfo>();
		
		/*SELECT R.readingid, RE.readingtitle, R.userid, U.username, UT.nametype, CONCAT(U.firstname, ' ', U.lastname) AS name, R.review, R.reviewdate
		FROM ((reviews R INNER JOIN users U ON R.userid = U.userid) 
			INNER JOIN readings RE ON R.readingid = RE.readingid) 
			INNER JOIN users_type UT ON U.usertype = UT.usertypeid
		WHERE R.readingid = 1
		ORDER BY R.reviewdate DESC;*/
		
		String sql = "SELECT R." + Review.COLUMN_READINGID + ", RE." + Readings.COLUMN_READINGTITLE 
				+ ", R." + Review.COLUMN_USERID + ", U." + RegisteredUser.COLUMN_USERNAME
				+ ", UT." + UserType.COLUMN_NAMETYPE + ", CONCAT(U." + RegisteredUser.COLUMN_FIRSTNAME 
				+ ", ' ', U." + RegisteredUser.COLUMN_LASTNAME + ") AS name, R." + Review.COLUMN_REVIEW + ", R." + Review.COLUMN_REVIEWDATE
				+ " FROM ((" + Review.TABLE_NAME + " R INNER JOIN " + RegisteredUser.TABLE_NAME + " U ON R." 
				+ Review.COLUMN_USERID + " = U." + RegisteredUser.COLUMN_USERID + ") INNER JOIN " + Readings.TABLE_NAME 
				+ " RE ON R." + Review.COLUMN_READINGID + " = RE." + Readings.COLUMN_READINGID 
				+ ") INNER JOIN " + UserType.TABLE_NAME + " UT ON U." + RegisteredUser.COLUMN_USERTYPE 
				+ " = UT." + UserType.COLUMN_USERTYPEID + " WHERE R." + Review.COLUMN_READINGID 
				+ " = ? ORDER BY R." + Review.COLUMN_REVIEWDATE + " DESC";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, readingid);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				ReviewInfo ri = new ReviewInfo();
				ri.setReadingid(rs.getInt(1));
				ri.setReadingtitle(rs.getString(2));
				ri.setUserid(rs.getInt(3));
				ri.setUsername(rs.getString(4));
				ri.setUsertype(rs.getString(5));
				ri.setUserfullname(rs.getString(6));
				ri.setReview(rs.getString(7));
				ri.setReviewdate(rs.getString(8));
				reviewInfoList.add(ri);
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
		
		return reviewInfoList;
	}

}
