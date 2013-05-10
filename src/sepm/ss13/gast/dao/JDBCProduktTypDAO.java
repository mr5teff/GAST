package sepm.ss13.gast.dao;

import java.sql.Connection;

import org.apache.log4j.Logger;

/**
 * 
 */
public class JDBCProduktTypDAO {

	private static final Logger log = Logger.getLogger (JDBCProduktTypDAO.class);
	
	private Connection c;
	
	public JDBCProduktTypDAO(Connection c) {
		this.c=c;
	}
}
