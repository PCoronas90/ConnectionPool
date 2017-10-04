package it.test.connectionPool;

/**Servlet di esempio per mostrare il funzionamento di un Pool di connessioni**/

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

/**
 * @author PietroCoronas
 *
 */

public class ServletExample extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private DataSource ds;
	private Connection cnn;
	private Statement stm;
	
	/** Inizializzazione del DataSource
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException {
		try {
			// Settaggio iniziale del dataSource
			Context initContext  = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			//Il valore di lookup deve essere lo stesso che abbiamo settato nel context.xml
			ds = (DataSource)envContext.lookup("jdbc/dbTest");

			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/** Semplice metodo get di una servlet. Istanzia la connessione ed effettua una query su db.
	 * Il risultato viene stampato in console.
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ResultSet resultSet = null;
		try {
			//Connessione
			cnn = ds.getConnection();
			//Statement
			stm = cnn.createStatement();
			//Query
			String query = "select name,value from tableExample where name='testConnection'";
			//Risultato
			resultSet = stm.executeQuery(query);
			while (resultSet.next()) {
				//Stampo i risultati a video
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