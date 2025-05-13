package com.curd.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class connection_DB
{
	private static String	url			=	"jdbc:mysql://localhost:3306/library_management";
	private static String	userName	=	"root";
	private static String 	password	=	"P@ssw0rd@123";
	private static Connection conn=null;
	
		public static Connection getConnection()  {
			try {
				
				if(conn==null) {
					conn = DriverManager.getConnection(url, userName, password);
				}
				
				return conn;
				
			}catch(SQLException e) {
				System.out.println(e);
			}
			return conn;
			
		}
		
		public static void close() throws SQLException
		{
			conn.close();
		}
		
		
//		public static PreparedStatement getPreparedStatementObj(String query)
//		{
//			PreparedStatement pstmt = null;
//			System.out.println(query);
//			try
//			{
//				pstmt = conn.prepareStatement(query);
//			}
//			catch(SQLException e)
//			{
//				e.printStackTrace();
//			}
//			return pstmt;
//
//		}

}
