package sepm.ss13.gast.dao;

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
	}

}
