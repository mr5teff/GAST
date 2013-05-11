package sepm.ss13.gast.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * This class creates a connection to a database and is designed as Singleton
 * (only one instance is possibly)
 * 
 * 
 */
public class DBConnector {

	private static DBConnector dbConnector = null;
	
	private Connection conn = null;
	
	private String host;
	private String database;
	private String userName;
	private String password;
	
	/**
	 * Private constructor
	 * @throws DAOException 
	 */
	private DBConnector(String host, String database, String userName, String password) throws DAOException {
		this.host=host;
		this.database=database;
		this.userName=userName;
		this.password=password;
	}
	
	/**
	 * Returns a single instance of a DBConnector.
	 * @return An instance of a DBConnector.
	 * @throws DAOException 
	 */
	public static DBConnector instance(String host, String database, String userName, String password) throws DAOException {
		if(dbConnector == null)
			dbConnector = new DBConnector(host,database,userName,password);
		
		return dbConnector;
	}
	
	
	/**
	 * Opens a Connection to a given jdbc hsqldb database.
	 * @param host The URL hostname where the database is located.
	 * @param database The name of the database.
	 * @param userName The name of the database user.
	 * @param password The password to get access to the database.
	 * @throws DAOException if connection could not be opened.
	 */
	public void openConnection() throws DAOException
	{
		
		String connectionString = "jdbc:hsqldb:hsql://" + host + "/" + database;
		try 
		{
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
		} 
		catch (ClassNotFoundException e) {
			throw new DAOException("ERROR: failed to load HSQLDB JDBC driver.");
		}
		
		try
		{
			conn = DriverManager.getConnection(connectionString, userName, password);
		}
		catch (SQLException e) {
			throw new DAOException("ERROR: failed to create connection.");
		}

	}
	
	/**
	 * @return A connection to a hsqldb database. NULL if connection not opened.
	 */
	public Connection getConnection()
	{				
		return conn;
	}
	
	/**
	 * Closes an opened hsqldb database connection.
	 * @throws DAOException if a database error occurred. 
	 */
	public void closeConnection() throws DAOException
	{
		if(conn != null)
		{
			try
			{
				conn.close();
			}
			catch (SQLException e)
			{
				throw new DAOException("ERROR: database access error occurs");
			}
			finally
			{
				conn = null;
			}
			
		}
	}
}
