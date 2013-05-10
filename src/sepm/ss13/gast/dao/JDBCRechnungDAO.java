package sepm.ss13.gast.dao;

import java.sql.Connection;

import org.apache.log4j.Logger;

/**
 * 
 */
public class JDBCRechnungDAO {
	
	private static final Logger log = Logger.getLogger (JDBCRechnungDAO.class);

	private Connection c;
	
	public JDBCRechnungDAO(Connection c) {
		this.c=c;
	}
}
