package sepm.ss13.gast.dao;

import org.apache.log4j.Logger;

public class DAOException extends Exception{
	
	/**
	 * Represents an Exception in the DAO Layer
	 */

	private static final long serialVersionUID = 3115668383314976651L;

	
	public DAOException()
	{
		super();
	}
	
	public DAOException(String message)
	{
		super(message);
		Logger.getLogger (DAOException.class).fatal(message);
	}
	
	public DAOException(String message, Exception e) {
		super(message,e);
		Logger.getLogger (DAOException.class).fatal(message);
	}

}
