package com.github.melpis.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

	private  JDBCConnection jdbcConnection = null;
	private static String dbURI 	= "";
	private static String dbUserId = "";
	private static String dbUserPw = "";
	private static String dbDriver = "";
	
	
	private JDBCConnection(){
	
		if(jdbcConnection == null){
			new JDBCConnection();
		}
	}
	
	
	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(dbURI,dbUserId,dbUserPw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	
	
	
	
	
	
	

	
}
