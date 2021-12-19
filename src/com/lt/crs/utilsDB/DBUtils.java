package com.lt.crs.utilsDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtils {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/test";
	    static final String USER = "root";
	   static final String PASSWORD = "root";
	   
	   public static Connection getConnection() {
		   Connection con=null;
		   try {
			   Class.forName(JDBC_DRIVER);
			   System.out.println("Conneting to the DataBase test");
			   con=DriverManager.getConnection(DB_URL,USER,PASSWORD);
	
			      }
		   catch(ClassNotFoundException e) {;e.printStackTrace();}
		   catch(SQLException e){e.printStackTrace();}
		   catch(Exception e){e.printStackTrace();}
		   
		  return con;
		   
		   
	   }
	   
	   
	   
	   
}
