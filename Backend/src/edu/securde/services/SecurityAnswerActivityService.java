package edu.securde.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.securde.beans.LoginActivity;
import edu.securde.beans.SecurityAnswerActivity;
import edu.securde.db.DBPool;

public class SecurityAnswerActivityService {
	
	public static boolean addSecurityAnswerActivity(SecurityAnswerActivity securityAnswerActivity){
		//INSERT INTO login_activity(userid, loginattempts)
		//VALUES(1,4);
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO " + SecurityAnswerActivity.TABLE_NAME + "(" + SecurityAnswerActivity.COLUMN_USERID + ", "
								    + SecurityAnswerActivity.COLUMN_ANSWERATTEMPTS + ") " +
					 "VALUES(?,?);";
		
		boolean isAddSuccess = false;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, securityAnswerActivity.getUserid());
			pstmt.setInt(2, securityAnswerActivity.getAnswer_attempts());
			
			pstmt.executeUpdate();
			System.out.println("Security Answer Activity successfully added in DB!!!");
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
	
	public static SecurityAnswerActivity searchSecurityAnswerActivity(int userid){
		//SELECT *
		//FROM login_activity
		//WHERE userid = ?;

		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM " + SecurityAnswerActivity.TABLE_NAME +
					 " WHERE " + SecurityAnswerActivity.COLUMN_USERID + " =?";
		
		SecurityAnswerActivity securityAnswerActivity = null;
		
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				securityAnswerActivity = new SecurityAnswerActivity();
				securityAnswerActivity.setUserid(rs.getInt(SecurityAnswerActivity.COLUMN_USERID));
				securityAnswerActivity.setAnswer_attempts(rs.getInt(SecurityAnswerActivity.COLUMN_ANSWERATTEMPTS));
			}
		}catch (SQLException e) {
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
		
		return securityAnswerActivity;
	}
	
	public static boolean updateAnswerAttempts(SecurityAnswerActivity securityAnswerActivity){
		//UPDATE login_activity
		//SET loginattempts = 4
		//WHERE userid=1;
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE " + SecurityAnswerActivity.TABLE_NAME + 
					 " SET " + SecurityAnswerActivity.COLUMN_ANSWERATTEMPTS + "=? " +
					 "WHERE " + SecurityAnswerActivity.COLUMN_USERID + "=? ";
		
		boolean isUpdateSuccess = false;
		int userid = securityAnswerActivity.getUserid();
		int loginattempts = securityAnswerActivity.getAnswer_attempts();
		
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, loginattempts);
			pstmt.setInt(2, userid);
			pstmt.executeUpdate();
			System.out.println("UPDATE IN DB::SUCCESS!");
			isUpdateSuccess = true;
		}catch(SQLException e){
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
	
	public static boolean deleteSecurityAnswerActivity(int userid){
		//DELETE FROM login_activity
		//WHERE userid = 1;
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM " + SecurityAnswerActivity.TABLE_NAME + 
					 " WHERE " + SecurityAnswerActivity.COLUMN_USERID + "=? ";
		
		boolean isDeleteSuccess = false;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userid);
			
			pstmt.executeUpdate();
			System.out.println("SecurityAnswerActivity successfully deleted in DB!!!");
			isDeleteSuccess=true;
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
		return isDeleteSuccess;
				
	}
}
