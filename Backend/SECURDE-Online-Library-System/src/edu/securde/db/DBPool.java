package edu.securde.db;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class DBPool {
	
	public static final String schema = "securdedb";
	public static final String username = "root";
	public static final String password = "root";
	public static final String url = "jdbc:mysql://localhost:3306/";
	public static final String driver = "com.mysql.jdbc.Driver";
	
	// an instance of itself
	private static DBPool singleton;
	private static BasicDataSource basicDataSource;
	
	//private constructor
	private DBPool(){
		basicDataSource = new BasicDataSource();
		basicDataSource.setUsername(username);
		basicDataSource.setPassword(password);
		basicDataSource.setUrl(url + schema);
		basicDataSource.setDriverClassName(driver);
	}
	
	//get instance
	public static DBPool getInstance(){
		if(singleton == null){
			singleton = new DBPool();
				}
				
		return singleton;

	}
	
	public Connection getConnection(){
		try{
			return basicDataSource.getConnection();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
}
