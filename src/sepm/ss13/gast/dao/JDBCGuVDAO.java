package sepm.ss13.gast.dao;

import java.sql.Connection;

import org.apache.log4j.Logger;

/**
 * 
 */
public class JDBCGuVDAO {
	private Connection c;
	
	public JDBCGuVDAO(Connection c) {
		this.c=c;
	}
	
}
