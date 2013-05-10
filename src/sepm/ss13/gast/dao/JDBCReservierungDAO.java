package sepm.ss13.gast.dao;

import java.sql.Connection;

import org.apache.log4j.Logger;

/**
 * 
 */
public class JDBCReservierungDAO {

	private static final Logger log = Logger.getLogger (JDBCReservierungDAO.class);

	private Connection c;
	
	public JDBCReservierungDAO(Connection c) {
		this.c=c;
	}
}
