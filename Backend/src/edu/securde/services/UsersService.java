package edu.securde.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.owasp.esapi.ESAPI;

import edu.securde.beans.Readings;
import edu.securde.beans.RegisteredUser;
import edu.securde.beans.UserType;
import edu.securde.db.DBPool;

public class UsersService {
	
	public static ArrayList<RegisteredUser> getAllUsers(){
		ArrayList<RegisteredUser> registeredUsers = new ArrayList<RegisteredUser>();
		
		/***Get the readings list***/
		//SELECT *
		//FROM users
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM " + RegisteredUser.TABLE_NAME;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				RegisteredUser user = new RegisteredUser();
				user = new RegisteredUser();
				user.setUserid(rs.getInt(RegisteredUser.COLUMN_USERID));
				user.setIdnum(rs.getString(RegisteredUser.COLUMN_IDNUM));
				user.setUsertype(rs.getInt(RegisteredUser.COLUMN_USERTYPE));
				user.setFirstname(ESAPI.encoder().encodeForHTML(rs.getString(RegisteredUser.COLUMN_FIRSTNAME)));
				user.setMiddleinitial(ESAPI.encoder().encodeForHTML(rs.getString(RegisteredUser.COLUMN_MIDDLEINITIAL)));
				user.setLastname(ESAPI.encoder().encodeForHTML(rs.getString(RegisteredUser.COLUMN_LASTNAME)));
				user.setUsername(ESAPI.encoder().encodeForHTML(rs.getString(RegisteredUser.COLUMN_USERNAME)));
				user.setPassword(rs.getString(RegisteredUser.COLUMN_PASSWORD));
				user.setEmailaddress(rs.getString(RegisteredUser.COLUMN_EMAILADDRESS));
				user.setBirthday(rs.getString(RegisteredUser.COLUMN_BIRTHDAY));
				user.setSecretquestion(rs.getString(RegisteredUser.COLUMN_SECRETQUESTION));
				user.setSecretanswer(rs.getString(RegisteredUser.COLUMN_SECRETANSWER));
				user.setStatus(rs.getString(RegisteredUser.COLUMN_STATUS));
				user.setRole(UsersService.getRoleByUsername(user.getUsername()));
				registeredUsers.add(user);
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
		
		return registeredUsers;
	}

	public static void addUser(RegisteredUser user){
		//INSERT INTO users(idnum, usertype, firstname, middleinitial, lastname, username, 
		// password, emailaddress, birthday, secretquestion, secretanswer)
		//VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
		
		String sql = "INSERT INTO " + RegisteredUser.TABLE_NAME + "("
				+ RegisteredUser.COLUMN_IDNUM + ","
				+ RegisteredUser.COLUMN_USERTYPE + "," + RegisteredUser.COLUMN_FIRSTNAME + ","
				+ RegisteredUser.COLUMN_MIDDLEINITIAL + ","
				+ RegisteredUser.COLUMN_LASTNAME + "," + RegisteredUser.COLUMN_USERNAME + ","
				+ RegisteredUser.COLUMN_PASSWORD + "," + RegisteredUser.COLUMN_EMAILADDRESS + ","
				+ RegisteredUser.COLUMN_BIRTHDAY + "," + RegisteredUser.COLUMN_SECRETQUESTION + ","
				+ RegisteredUser.COLUMN_SECRETANSWER + "," + RegisteredUser.COLUMN_STATUS +
				") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getIdnum());
			pstmt.setInt(2, user.getUsertype());
			pstmt.setString(3, user.getFirstname());
			pstmt.setString(4, user.getMiddleinitial());
			pstmt.setString(5, user.getLastname());
			pstmt.setString(6, user.getUsername());
			pstmt.setString(7, user.getPassword());
			pstmt.setString(8, user.getEmailaddress());
			pstmt.setString(9, user.getBirthday());
			pstmt.setString(10, user.getSecretquestion());
			pstmt.setString(11, user.getSecretanswer());
			pstmt.setString(12, user.getStatus());
			
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
	
	public static String validateUserbyUsername(String username){
		String password = "";
		
		String sql = "SELECT password FROM users where username = ?";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			while(rs.next()){
				password = rs.getString(RegisteredUser.COLUMN_PASSWORD);
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
		return password;
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
				user.setIdnum(rs.getString(RegisteredUser.COLUMN_IDNUM));
				user.setUsertype(rs.getInt(RegisteredUser.COLUMN_USERTYPE));
				user.setFirstname(rs.getString(RegisteredUser.COLUMN_FIRSTNAME));
				user.setMiddleinitial(rs.getString(RegisteredUser.COLUMN_MIDDLEINITIAL));
				user.setLastname(rs.getString(RegisteredUser.COLUMN_LASTNAME));
				user.setUsername(rs.getString(RegisteredUser.COLUMN_USERNAME));
				user.setPassword(rs.getString(RegisteredUser.COLUMN_PASSWORD));
				user.setEmailaddress(rs.getString(RegisteredUser.COLUMN_EMAILADDRESS));
				user.setBirthday(rs.getString(RegisteredUser.COLUMN_BIRTHDAY));
				user.setSecretquestion(rs.getString(RegisteredUser.COLUMN_SECRETQUESTION));
				user.setSecretanswer(rs.getString(RegisteredUser.COLUMN_SECRETANSWER));
				user.setStatus(rs.getString(RegisteredUser.COLUMN_STATUS));
				
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
	
	public static RegisteredUser getUser(int userid){
		RegisteredUser user = null;
		
		String sql = "SELECT * FROM " + RegisteredUser.TABLE_NAME + " WHERE " + RegisteredUser.COLUMN_USERID + " = ?";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userid);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				user = new RegisteredUser();
				user.setUserid(rs.getInt(RegisteredUser.COLUMN_USERID));
				user.setIdnum(rs.getString(RegisteredUser.COLUMN_IDNUM));
				user.setUsertype(rs.getInt(RegisteredUser.COLUMN_USERTYPE));
				user.setFirstname(rs.getString(RegisteredUser.COLUMN_FIRSTNAME));
				user.setFirstname(rs.getString(RegisteredUser.COLUMN_MIDDLEINITIAL));
				user.setLastname(rs.getString(RegisteredUser.COLUMN_LASTNAME));
				user.setUsername(rs.getString(RegisteredUser.COLUMN_USERNAME));
				user.setPassword(rs.getString(RegisteredUser.COLUMN_PASSWORD));
				user.setEmailaddress(rs.getString(RegisteredUser.COLUMN_EMAILADDRESS));
				user.setBirthday(rs.getString(RegisteredUser.COLUMN_BIRTHDAY));
				user.setSecretquestion(rs.getString(RegisteredUser.COLUMN_SECRETQUESTION));
				user.setSecretanswer(rs.getString(RegisteredUser.COLUMN_SECRETANSWER));
				user.setStatus(rs.getString(RegisteredUser.COLUMN_STATUS));
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
	
	public static RegisteredUser getUserByUsernameID(String username_idnumber){
		RegisteredUser user = null;
		
		String sql = "SELECT * FROM " + RegisteredUser.TABLE_NAME + " WHERE " 
				+ RegisteredUser.COLUMN_USERNAME + " = ? OR " + RegisteredUser.COLUMN_IDNUM + " = ?";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username_idnumber);
			pstmt.setString(2, username_idnumber);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				user = new RegisteredUser();
				user.setUserid(rs.getInt(RegisteredUser.COLUMN_USERID));
				user.setIdnum(rs.getString(RegisteredUser.COLUMN_IDNUM));
				user.setUsertype(rs.getInt(RegisteredUser.COLUMN_USERTYPE));
				user.setFirstname(rs.getString(RegisteredUser.COLUMN_FIRSTNAME));
				user.setFirstname(rs.getString(RegisteredUser.COLUMN_MIDDLEINITIAL));
				user.setLastname(rs.getString(RegisteredUser.COLUMN_LASTNAME));
				user.setUsername(rs.getString(RegisteredUser.COLUMN_USERNAME));
				user.setPassword(rs.getString(RegisteredUser.COLUMN_PASSWORD));
				user.setEmailaddress(rs.getString(RegisteredUser.COLUMN_EMAILADDRESS));
				user.setBirthday(rs.getString(RegisteredUser.COLUMN_BIRTHDAY));
				user.setSecretquestion(rs.getString(RegisteredUser.COLUMN_SECRETQUESTION));
				user.setSecretanswer(rs.getString(RegisteredUser.COLUMN_SECRETANSWER));
				user.setStatus(rs.getString(RegisteredUser.COLUMN_STATUS));
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
		String newMiddleInitial = user.getMiddleinitial();
		String newFirstName = user.getFirstname();
		String newEmail = user.getEmailaddress();
		String newUsername = user.getUsername();
		String newPassword = user.getPassword();
		
		String sql = "UPDATE "+ RegisteredUser.TABLE_NAME + " SET " + RegisteredUser.COLUMN_LASTNAME + "=?, " +
					 RegisteredUser.COLUMN_FIRSTNAME + "=?, " +
					 RegisteredUser.COLUMN_MIDDLEINITIAL + "=?, " +
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
			pstat.setString(3, newMiddleInitial);
			pstat.setString(4, newEmail);
			pstat.setString(5, newUsername);
			pstat.setString(6, newPassword);
			pstat.setInt(7, user.getUserid());
			
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
				user.setIdnum(rs.getString(RegisteredUser.COLUMN_IDNUM));
				user.setUsertype(rs.getInt(RegisteredUser.COLUMN_USERTYPE));
				user.setFirstname(rs.getString(RegisteredUser.COLUMN_FIRSTNAME));
				user.setFirstname(rs.getString(RegisteredUser.COLUMN_MIDDLEINITIAL));
				user.setLastname(rs.getString(RegisteredUser.COLUMN_LASTNAME));
				user.setUsername(rs.getString(RegisteredUser.COLUMN_USERNAME));
				user.setPassword(rs.getString(RegisteredUser.COLUMN_PASSWORD));
				user.setEmailaddress(rs.getString(RegisteredUser.COLUMN_EMAILADDRESS));
				user.setBirthday(rs.getString(RegisteredUser.COLUMN_BIRTHDAY));
				user.setSecretquestion(rs.getString(RegisteredUser.COLUMN_SECRETQUESTION));
				user.setSecretanswer(rs.getString(RegisteredUser.COLUMN_SECRETANSWER));
				user.setStatus(rs.getString(RegisteredUser.COLUMN_STATUS));
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
	
	public static boolean validateUsernameIDnum(String username_idnum){
		boolean isValid = false;
		int userid = 0;
		
		/*SELECT *
		 * FROM users
		 * WHERE username = ? OR idnum = ?
		 */
		
		String sql = "SELECT " + RegisteredUser.COLUMN_USERID + " FROM " + RegisteredUser.TABLE_NAME + " WHERE " 
				+ RegisteredUser.COLUMN_USERNAME + " = ? OR " + RegisteredUser.COLUMN_IDNUM + " = ?";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username_idnum);
			pstmt.setString(2, username_idnum);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				userid = rs.getInt(RegisteredUser.COLUMN_USERID);
			}
			
			if(userid != 0){
				isValid = true;
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
		
		return isValid;
	}
	
	public static RegisteredUser getUserByUsernameIDnum(String username_idnum){
		
		/*SELECT *
		 * FROM users
		 * WHERE username = ? OR idnum = ?
		 */
		
		RegisteredUser user = null;
		
		String sql = "SELECT * FROM " + RegisteredUser.TABLE_NAME + " WHERE " 
				+ RegisteredUser.COLUMN_USERNAME + " = ? OR " + RegisteredUser.COLUMN_IDNUM + " = ?";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username_idnum);
			pstmt.setString(2, username_idnum);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				user = new RegisteredUser();
				user.setUserid(rs.getInt(RegisteredUser.COLUMN_USERID));
				user.setIdnum(rs.getString(RegisteredUser.COLUMN_IDNUM));
				user.setUsertype(rs.getInt(RegisteredUser.COLUMN_USERTYPE));
				user.setFirstname(rs.getString(RegisteredUser.COLUMN_FIRSTNAME));
				user.setFirstname(rs.getString(RegisteredUser.COLUMN_MIDDLEINITIAL));
				user.setLastname(rs.getString(RegisteredUser.COLUMN_LASTNAME));
				user.setUsername(rs.getString(RegisteredUser.COLUMN_USERNAME));
				user.setPassword(rs.getString(RegisteredUser.COLUMN_PASSWORD));
				user.setEmailaddress(rs.getString(RegisteredUser.COLUMN_EMAILADDRESS));
				user.setBirthday(rs.getString(RegisteredUser.COLUMN_BIRTHDAY));
				user.setSecretquestion(rs.getString(RegisteredUser.COLUMN_SECRETQUESTION));
				user.setSecretanswer(rs.getString(RegisteredUser.COLUMN_SECRETANSWER));
				user.setStatus(rs.getString(RegisteredUser.COLUMN_STATUS));
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
	
	public static boolean updateStatus(int userid, String status){
		
		boolean isUpdateSuccess = false;
		
		String sql = "UPDATE "+ RegisteredUser.TABLE_NAME + " SET " + RegisteredUser.COLUMN_STATUS 
					+ "=? WHERE " + RegisteredUser.COLUMN_USERID + "=? ";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstat = null;
		
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, status);
			pstat.setInt(2, userid);
			
			
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
	
	public static boolean checkPasswordIfUnique(String password){
		boolean isUnique = false;
		//SELECT userid
		//FROM users
		//WHERE password = ?
		
		String sql = "SELECT " + RegisteredUser.COLUMN_USERID + " FROM " + RegisteredUser.TABLE_NAME 
				+ " WHERE " + RegisteredUser.COLUMN_PASSWORD + " = ?";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstat = null;
		ResultSet rs = null;
		
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, password);
			
			rs = pstat.executeQuery();
			
			if(rs.next() == false){
				isUnique = true;
			}
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
		
		return isUnique;
	}
	
	public static boolean changePassword(int userid, String password){
		boolean isUpdateSuccess = false;
		
		String sql = "UPDATE "+ RegisteredUser.TABLE_NAME + " SET " + RegisteredUser.COLUMN_PASSWORD 
					+ "=? WHERE " + RegisteredUser.COLUMN_USERID + "= ?";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstat = null;
		
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, password);
			pstat.setInt(2, userid);
			
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
	
	public static String getRoleByUsername(String username){
		String role = "";
		
		/*SELECT UT.nametype
		FROM users U INNER JOIN users_type UT ON U.usertype = UT.usertypeid
		WHERE U.username = ?;*/
		
		String sql = "SELECT UT." + UserType.COLUMN_NAMETYPE + " FROM " + RegisteredUser.TABLE_NAME 
				+ " U INNER JOIN " + UserType.TABLE_NAME + " UT ON U." + RegisteredUser.COLUMN_USERTYPE
				+ " = UT." + UserType.COLUMN_USERTYPEID + " WHERE U." + RegisteredUser.COLUMN_USERNAME + " = ?";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstat = null;
		ResultSet rs = null;
		
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, username);
			rs = pstat.executeQuery();
			
			while(rs.next()){
				role = rs.getString(1);
			}
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
		
		
		return role;
	}
	
	public static boolean checkSecurityAnswer(int userid, String answer){
		boolean ifSame = false;
		String username = "";
		
		String sql = "SELECT " + RegisteredUser.COLUMN_USERNAME + " FROM " + RegisteredUser.TABLE_NAME 
				+ " WHERE " + RegisteredUser.COLUMN_USERID + " = ? AND " + RegisteredUser.COLUMN_SECRETANSWER + " = ?";
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userid);
			pstmt.setString(2, answer);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				username = rs.getString(1);
				ifSame = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ifSame;
	}
	/*
	public static void lockUser(int userid){
		//UPDATE users
		//SET locked = 1
		//WHERE userid = 9;
		
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE " + RegisteredUser.TABLE_NAME +
					 " SET " + RegisteredUser.COLUMN_LOCKED + "= ?" +
					 " WHERE " + RegisteredUser.COLUMN_USERID + "= ?";
		
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, userid);
			pstmt.executeUpdate();
			System.out.println("LOCKED USER::SUCCESS!");
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
	}
	*/
}
