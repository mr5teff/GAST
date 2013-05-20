package sepm.ss13.gast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import sepm.ss13.gast.domain.Ware;

/**
 * 
 */
public class JDBCWareDAO implements WareDAO
{
	private static final Logger log = Logger.getLogger (JDBCWareDAO.class);

	private Connection c;
	
	public JDBCWareDAO(Connection c) 
	{
		this.c = c;
	}
	
	public Ware create(Ware w) throws DAOException 
	{
		try 
		{
			PreparedStatement ps = c.prepareStatement("INSERT INTO ware (id, bezeichnung, lagerstand) VALUES (NULL,?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, w.getBezeichnung());
			ps.setInt(2, w.getLagerstand());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) w.setId(rs.getInt(1));
		} 
		catch (SQLException e) 
		{
			throw new DAOException("ERROR: failed to save Ware to DB!");
		}
		
		return w;
	}
	
	public ArrayList<Ware> search(Ware w) throws DAOException 
	{
		try 
		{
			PreparedStatement ps = c.prepareStatement("SELECT id, bezeichnung, lagerstand FROM ware WHERE (id=? OR ? IS NULL) AND bezeichnung LIKE ? AND (lagerstand=? OR ? IS NULL)");
			ps.setObject(1, w.getId());
			ps.setObject(2, w.getId());
			ps.setString(3, w.getBezeichnung());
			ps.setInt(4, w.getLagerstand());
			ps.setInt(5, w.getLagerstand());
			
			ResultSet rs = ps.executeQuery();
			ArrayList<Ware> al= new ArrayList<Ware>();
			
			while(rs.next()) 
			{
				al.add(new Ware(rs.getInt("id"), rs.getString("bezeichnung"), rs.getInt("lagerstand")));		
			}
			
			return al;			
		} 
		catch (SQLException e) 
		{
			throw new DAOException("ERROR: failed to search DB for Waren!");
		}
	}
	
	public void update(Ware w) throws DAOException 
	{	
		try
		{
			PreparedStatement ps = c.prepareStatement("UPDATE ware SET bezeichnung=?, lagerstand=? WHERE id=?");
			
			ps.setString(1, w.getBezeichnung());
			ps.setInt(2, w.getLagerstand());
			ps.setInt(3, w.getId());
						
			int updatedRows = ps.executeUpdate();
			
			log.info("SQL updated rows: " + updatedRows);
			
			if(updatedRows == 0)
				throw new DAOException("No Einkauf entity could be updated.");				
		}
		catch(SQLException e)
		{
			throw new DAOException("A database error occurred.");
		}		
	}
	
	public void delete(Ware w) throws DAOException 
	{
		try 
		{
			PreparedStatement ps = c.prepareStatement("DELETE FROM ware WHERE id=?");
			ps.setInt(1, w.getId());
			ps.executeUpdate();
		}
		catch (SQLException e) 
		{
			throw new DAOException("ERROR: failed to delete Ware from DB!");
		}
	}
}
