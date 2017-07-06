package edu.securde.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.securde.beans.RegisteredUser;
import edu.securde.db.DBPool;

public class UsersService {

	public static void addUser(RegisteredUser user){
		//INSERT INTO users(idnum, usertype, firstname, lastname, username, 
		// password, emailaddress, birthday, secretquestion, secretanswer)
		//VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
		
		String sql = "INSERT INTO " + RegisteredUser.TABLE_NAME + "("
				+ RegisteredUser.COLUMN_IDNUM + ","
				+ RegisteredUser.COLUMN_USERTYPE + "," + RegisteredUser.COLUMN_FIRSTNAME + ","
				+ RegisteredUser.COLUMN_LASTNAME + "," + RegisteredUser.COLUMN_USERNAME + ","
				+ RegisteredUser.COLUMN_PASSWORD + "," + RegisteredUser.COLUMN_EMAILADDRESS + ","
				+ RegisteredUser.COLUMN_BIRTHDAY + "," + RegisteredUser.COLUMN_SECRETQUESTION + ","
				+ RegisteredUser.COLUMN_SECRETANSWER + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getIdnum());
			pstmt.setInt(2, user.getUsertype());
			pstmt.setString(3, user.getFirstname());
			pstmt.setString(4, user.getLastname());
			pstmt.setString(5, user.getUsername());
			pstmt.setString(6, user.getPassword());
			pstmt.setString(7, user.getEmailaddress());
			pstmt.setString(8, user.getBirthday());
			pstmt.setString(9, user.getSecretquestion());
			pstmt.setString(10, user.getSecretanswer());
			
			pstmt.executeUpdate();
			System.out.println("User is added successfully in DB!!!");
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
	
	public static boolean validateUser(String username, String password){
		boolean isValid = false;
		
		String sql = "SELECT * FROM users where username = ? and password = ?";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			isValid = rs.next();
			
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
		return isValid;
	}
	
	public static boolean isExisting(String email, String username){
		
		String sql = "SELECT * FROM " + RegisteredUser.TABLE_NAME + " WHERE " + 
						RegisteredUser.COLUMN_EMAILADDRESS + " = '" + email + "'"
						+ " OR " + RegisteredUser.COLUMN_USERNAME + " = '" + username + "'";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int idMember = 0;
		try{
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				idMember = Integer.parseInt(rs.getString(RegisteredUser.COLUMN_USERID));
			}
		} catch (SQLException e){
			e.printStackTrace();
		} finally{
			try{
				pstmt.close();
				conn.close();
				rs.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		if(idMember==0)
			return false;
		else
			return true;	
	}
	
	public static RegisteredUser getUser(String username){
		RegisteredUser user = null;
		
		String sql = "SELECT * FROM " + RegisteredUser.TABLE_NAME + " WHERE " + RegisteredUser.COLUMN_USERNAME
					 + " = '" + username + "'";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				user = new RegisteredUser();
				user.setUserid(rs.getInt(RegisteredUser.COLUMN_USERID));
				user.setUsername(rs.getString(RegisteredUser.COLUMN_USERNAME));
				user.setIdnum(rs.getString(RegisteredUser.COLUMN_IDNUM));
				user.setUsertype(rs.getInt(RegisteredUser.COLUMN_USERTYPE));
				user.setFirstname(rs.getString(RegisteredUser.COLUMN_FIRSTNAME));
				user.setLastname(rs.getString(RegisteredUser.COLUMN_LASTNAME));
				user.setUsername(rs.getString(RegisteredUser.COLUMN_USERNAME));
				user.setPassword(rs.getString(RegisteredUser.COLUMN_PASSWORD));
				user.setEmailaddress(rs.getString(RegisteredUser.COLUMN_EMAILADDRESS));
				user.setBirthday(rs.getString(RegisteredUser.COLUMN_BIRTHDAY));
				user.setSecretquestion(rs.getString(RegisteredUser.COLUMN_SECRETQUESTION));
				user.setSecretquestion(rs.getString(RegisteredUser.COLUMN_SECRETANSWER));
				
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

		return user;
	}
	
	public static boolean updateUser(RegisteredUser user){
		boolean isUpdateSuccess = false;
		
		/** Get all new info of the user**/
		String newLastName = user.getLastname();
		String newFirstName = user.getFirstname();
		String newEmail = user.getEmailaddress();
		String newUsername = user.getUsername();
		String newPassword = user.getPassword();
		
		String sql = "UPDATE "+ RegisteredUser.TABLE_NAME + " SET " + RegisteredUser.COLUMN_LASTNAME + "=?, " +
					 RegisteredUser.COLUMN_FIRSTNAME + "=?, " +
					 RegisteredUser.COLUMN_EMAILADDRESS + "=?, " + 
					 RegisteredUser.COLUMN_USERNAME + "=?, " + 
					 RegisteredUser.COLUMN_PASSWORD + "=?" + " WHERE " +
					 RegisteredUser.COLUMN_USERID + "=? ";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstat = null;
		
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, newLastName);
			pstat.setString(2, newFirstName);
			pstat.setString(3, newEmail);
			pstat.setString(4, newUsername);
			pstat.setString(5, newPassword);
			pstat.setInt(9, user.getUserid());
			
			pstat.executeUpdate();
			
			System.out.println("UPDATE IN DB::SUCCESS!");
			isUpdateSuccess = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				pstat.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return isUpdateSuccess;
		
	}
	
	public static RegisteredUser getUserByID(String userID){
		RegisteredUser user = null;
		
		String sql = "SELECT * FROM " + RegisteredUser.TABLE_NAME + " WHERE " + RegisteredUser.COLUMN_USERID
					 + " = ?";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstat = null;
		ResultSet rs = null;
		
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, userID);
			rs = pstat.executeQuery();
			
			while(rs.next()){
				user = new RegisteredUser();
				user.setUserid(rs.getInt(RegisteredUser.COLUMN_USERID));
				user.setUsername(rs.getString(RegisteredUser.COLUMN_USERNAME));
				user.setIdnum(rs.getString(RegisteredUser.COLUMN_IDNUM));
				user.setUsertype(rs.getInt(RegisteredUser.COLUMN_USERTYPE));
				user.setFirstname(rs.getString(RegisteredUser.COLUMN_FIRSTNAME));
				user.setLastname(rs.getString(RegisteredUser.COLUMN_LASTNAME));
				user.setUsername(rs.getString(RegisteredUser.COLUMN_USERNAME));
				user.setPassword(rs.getString(RegisteredUser.COLUMN_PASSWORD));
				user.setEmailaddress(rs.getString(RegisteredUser.COLUMN_EMAILADDRESS));
				user.setBirthday(rs.getString(RegisteredUser.COLUMN_BIRTHDAY));
				user.setSecretquestion(rs.getString(RegisteredUser.COLUMN_SECRETQUESTION));
				user.setSecretquestion(rs.getString(RegisteredUser.COLUMN_SECRETANSWER));
			}
			
			System.out.println("GOT A USER::SUCCESS");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				pstat.close();
				conn.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return user;
	}
}
