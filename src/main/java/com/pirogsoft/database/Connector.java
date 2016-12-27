/**
 * 
 */
package com.pirogsoft.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


/**
 * @author Andrey
 *
 */

public class Connector {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/mafia_leading_helper";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "";

	static Connector instance;
	public static Connector getInstance()
	{
		if(instance == null)
		{
			instance = new Connector();
		}
		return instance; 
	}
	
	private Connector()
	{
		
	}
	
	public <T> List<T>  getResult(IListGetter<T> lg) {
		List<T> result = null;
		Connection conn = null;
		Statement stmt = null;
		try {
			// регистрируем драйвер
			Class.forName(JDBC_DRIVER);

			// открываем соединение

			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Выполняем запрос
			stmt = conn.createStatement();
			String sql = lg.getQuery();
			ResultSet rs = stmt.executeQuery(sql);
			result = lg.getResultList(rs);

			// закрываем соединение
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {

			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}
		return result;
	}

	public int insertQueryReturnInt(String query) {
		ResultSet generatedKeys = null;
		int generatedKey = -1;



		// открываем соединение

		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			Statement statement = conn.createStatement();
			statement.execute(query);
		} catch (Exception e) {

			return -1;
		}

		try {
			//generatedKey = Integer.parseInt(readOneValue("SELECT @@IDENTITY"));
		} catch (Exception e) {

			return -1;
		}

		return generatedKey;
	}
}
