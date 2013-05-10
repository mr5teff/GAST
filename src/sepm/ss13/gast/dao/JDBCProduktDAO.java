package sepm.ss13.gast.dao;

import java.sql.Connection;

import org.apache.log4j.Logger;

/**
 * 
 */
public class JDBCProduktDAO {

	private static final Logger log = Logger.getLogger (JDBCProduktDAO.class);
	
	private Connection c;
	
	public JDBCProduktDAO(Connection c) {
		this.c=c;
	}
}
