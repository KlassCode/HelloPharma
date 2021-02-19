package com.klasscode.helloPharma.mode.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
	private static Connection conn;
	private String url;
	private String user;
	private String password;

	private DbConnection() {
		Properties props= new Properties();
		
		try(FileInputStream fis= new FileInputStream("../HelloPharma/src/com/klasscode/helloPharma/app/config/conf.properties")){
			
			props.load(fis);
			
			Class.forName(props.getProperty("jdbc.driver.class"));
			
			 url= props.getProperty("jdbc.url");
			 user= props.getProperty("jdbc.user");
			 password= props.getProperty("jdbc.password");
			 
			 //System.out.println(url+user+password);
			conn= DriverManager.getConnection(url,user,password);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getInstance() {

		synchronized (DbConnection.class) {

			if (conn == null) {
				new DbConnection();
			}
			return conn;
		}
	}
}
