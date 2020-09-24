package com.dbservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.properties.PropertiesContext;

public class ConnectTionManager{
	private static Connection connection;
	private static ConnectTionManager cm;
	
	public static Connection getConnection() throws Exception {
		getCM();
		return connection;
	}

	
	private ConnectTionManager() throws Exception{
		PropertiesContext prop = new PropertiesContext();
		String url = prop.getProp().getProperty("url");
		String username = prop.getProp().getProperty("username");
		String password = prop.getProp().getProperty("password");
		String driver = prop.getProp().getProperty("driver");		
		Class.forName(driver);
		connection = DriverManager.getConnection(url, username, password);
	}
	
	public static synchronized ConnectTionManager getCM() throws Exception{
		if(cm==null){
			return new ConnectTionManager();
		}
		else{
			return cm;
		}
	}
	
	public static void close() throws SQLException{
		connection.close();
	}
	
	
}
