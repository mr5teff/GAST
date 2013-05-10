package sepm.ss13.gast.dao;

import java.sql.Connection;

import org.apache.log4j.Logger;

/**
 * 
 */
public class JDBCWareDAO {

	private static final Logger log = Logger.getLogger (JDBCWareDAO.class);

	private Connection c;
	
	public JDBCWareDAO(Connection c) {
		this.c=c;
	}
}
