package it.test.connectionPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class ServletExample extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataSource ds;
	private Connection cnn;
	private Statement stm;
	
	public void init() throws ServletException {
		try {
			// Get DataSource
			Context initContext  = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/dbTest");

			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ResultSet resultSet = null;
		try {
			// Get Connection and Statement
			cnn = ds.getConnection();
			stm = cnn.createStatement();
			String query = "select name,value from tableExample where name='testConnection'";
			
			resultSet = stm.executeQuery(query);
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1) + resultSet.getString(2) + resultSet.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try { if(null!=resultSet)resultSet.close();} catch (SQLException e) 
			{e.printStackTrace();}
			try { if(null!=stm)stm.close();} catch (SQLException e) 
			{e.printStackTrace();}
			try { if(null!=cnn)cnn.close();} catch (SQLException e) 
			{e.printStackTrace();}
		}
	}
}