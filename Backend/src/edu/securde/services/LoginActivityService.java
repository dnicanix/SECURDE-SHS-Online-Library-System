package edu.securde.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.securde.beans.LoginActivity;
import edu.securde.beans.Readings;
import edu.securde.beans.RegisteredUser;
import edu.securde.db.DBPool;

public class LoginActivityService {
	
	public static boolean addLoginActivity(LoginActivity loginActivity){
		//INSERT INTO login_activity(userid, loginattempts)
		//VALUES(1,4);
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO " + LoginActivity.TABLE_NAME + "(" + LoginActivity.COLUMN_USERID + ", "
								    + LoginActivity.COLUMN_LOGINATTEMPTS + ") " +
					 "VALUES(?,?);";
		
		boolean isAddSuccess = false;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, loginActivity.getUserid());
			pstmt.setInt(2, loginActivity.getLoginattempts());
			
			pstmt.executeUpdate();
			System.out.println("Login Activity successfully added in DB!!!");
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
	
	public static LoginActivity searchLoginActivity(int userid){
		//SELECT *
		//FROM login_activity
		//WHERE userid = ?;

		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM " + LoginActivity.TABLE_NAME +
					 " WHERE " + LoginActivity.COLUMN_USERID + " =?";
		
		LoginActivity loginActivity = null;
		
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				loginActivity = new LoginActivity();
				loginActivity.setUserid(rs.getInt(LoginActivity.COLUMN_USERID));
				loginActivity.setLoginattempts(rs.getInt(LoginActivity.COLUMN_LOGINATTEMPTS));
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
		
		return loginActivity;
	}
	
	public static boolean updateLoginAttempts(LoginActivity loginActivity){
		//UPDATE login_activity
		//SET loginattempts = 4
		//WHERE userid=1;
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE " + LoginActivity.TABLE_NAME + 
					 " SET " + LoginActivity.COLUMN_LOGINATTEMPTS + "=? " +
					 "WHERE " + LoginActivity.COLUMN_USERID + "=? ";
		
		boolean isUpdateSuccess = false;
		int userid = loginActivity.getUserid();
		int loginattempts = loginActivity.getLoginattempts();
		
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
	
	public static boolean deleteLoginActivity(int userid){
		//DELETE FROM login_activity
		//WHERE userid = 1;
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM " + LoginActivity.TABLE_NAME + 
					 " WHERE " + LoginActivity.COLUMN_USERID + "=? ";
		
		boolean isDeleteSuccess = false;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userid);
			
			pstmt.executeUpdate();
			System.out.println("LoginActivity successfully deleted in DB!!!");
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
