package sepm.ss13.gast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import sepm.ss13.gast.domain.Einkauf;

/**
 * 
 */
public class JDBCEinkaufDAO implements EinkaufDAO 
{

	private static final Logger log = Logger.getLogger (JDBCEinkaufDAO.class);

	private Connection c;
	
	public JDBCEinkaufDAO(Connection c) {
		
		this.c = c;
	}
	
	public Einkauf create(Einkauf ek) throws DAOException 
	{
		try 
		{
			PreparedStatement ps = c.prepareStatement("INSERT INTO einkauf (id, warenid, menge, datum, preis) VALUES (NULL,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, ek.getWarenId());
			ps.setInt(2, ek.getMenge());
			ps.setDate(3, ek.getDatum());
			ps.setDouble(4, ek.getPreis());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) ek.setId(rs.getInt(1));
		} 
		catch (SQLException e) 
		{
			throw new DAOException("ERROR: failed to save Einkauf to DB!");
		}
		
		return ek;
	}
	
	public ArrayList<Einkauf> search(Einkauf ek) throws DAOException 
	{
		try 
		{
			PreparedStatement ps = c.prepareStatement("SELECT id, warenid, menge, datum, preis FROM einkauf WHERE (id=? OR ? IS NULL) AND (warenid=? OR ? IS NULL) AND (menge=? OR ? IS NULL) AND datum LIKE ? AND (preis=? OR ? IS NULL)");
			ps.setObject(1, ek.getId());
			ps.setObject(2, ek.getId());
			ps.setObject(3, ek.getWarenId());
			ps.setObject(4, ek.getWarenId());
			ps.setInt(5, ek.getMenge());
			ps.setInt(6, ek.getMenge());
			ps.setDate(7, ek.getDatum());
			ps.setInt(8, ek.getPreis());
			ps.setInt(9, ek.getPreis());
			
			ResultSet rs = ps.executeQuery();
			ArrayList<Einkauf> al= new ArrayList<Einkauf>();
			
			while(rs.next()) 
			{
				al.add(new Einkauf(rs.getInt("id"), rs.getInt("warenid"), rs.getInt("menge"), rs.getDate("datum"), rs.getInt("preis")));		
			}
			
			return al;			
		} 
		catch (SQLException e) 
		{
			throw new DAOException("ERROR: failed to search DB for orders!");
		}
	}
	
	public void update(Einkauf ek) throws DAOException 
	{	
		try
		{
			PreparedStatement ps = c.prepareStatement("UPDATE einkauf SET warenid=?, menge=?, datum=?, preis=? WHERE id=?");
			
			ps.setInt(1, ek.getWarenId());
			ps.setInt(2, ek.getMenge());
			ps.setDate(3, ek.getDatum());
			ps.setDouble(4, ek.getPreis());
			ps.setInt(5, ek.getId());
						
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
	
	public void delete(Einkauf ek) throws DAOException 
	{
		try 
		{
			PreparedStatement ps = c.prepareStatement("DELETE FROM einkauf WHERE id=?");
			ps.setInt(1, ek.getId());
			ps.executeUpdate();
		}
		catch (SQLException e) 
		{
			throw new DAOException("ERROR: failed to delete Einkauf from DB!");
		}
	}
}
