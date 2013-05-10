package sepm.ss13.gast.dao;

import java.sql.Connection;

import org.apache.log4j.Logger;

/**
 * 
 */
public class JDBCBestellungDAO {

	private static final Logger log = Logger.getLogger (JDBCBestellungDAO.class);
	
	private Connection c;
	
	public JDBCBestellungDAO(Connection c) {
		this.c=c;
	}
}
