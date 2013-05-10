package sepm.ss13.gast.dao;

import java.sql.Connection;

import org.apache.log4j.Logger;

/**
 * 
 */
public class JDBCRezeptDAO {

	private static final Logger log = Logger.getLogger (JDBCRezeptDAO.class);

	private Connection c;
	
	public JDBCRezeptDAO(Connection c) {
		this.c=c;
	}
}
