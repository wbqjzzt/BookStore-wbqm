package com.tiny.util;

import java.io.InputStream;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	
	private static String url = null;
	
	private static String user = null;
	
	private static String password = null;
	
	static {
		
		try {
			
			ClassLoader loader = DBUtil.class.getClassLoader();
			InputStream in = loader.getResourceAsStream("com/tiny/properties/Config.properties");
			
			Properties prop = new Properties();
			prop.load(in);
			
			url  = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
			
			Class.forName(prop.getProperty("driver"));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
	
	public static void close(ResultSet result, Connection connection, Statement statement) {
		try {
			if (result != null) result.close();

			if (statement != null) statement.close();
			
			if (connection != null) connection.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(Connection connection, Statement statement) {
		try {
			if (null != connection) connection.close();

			if (null != statement) statement.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	

}
