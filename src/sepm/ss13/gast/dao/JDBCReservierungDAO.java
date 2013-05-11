package sepm.ss13.gast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import sepm.ss13.gast.domain.Reservierung;

/**
 * 
 */
public class JDBCReservierungDAO {

	private static final Logger log = Logger.getLogger (JDBCReservierungDAO.class);

	private Connection c;
	
	public JDBCReservierungDAO(Connection c) {
		this.c = c;
	}
		
	public Reservierung create(Reservierung r) throws DAOException 
	{
		try 
		{
			PreparedStatement ps = c.prepareStatement("INSERT INTO reservierung (id, datum, dauer, personen, tischnummer, name, telefonnummer) VALUES (NULL,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1, r.getDatum());
			ps.setInt(2, r.getDauer());
			ps.setInt(3, r.getPersonen());
			ps.setInt(4, r.getTischnummer());
			ps.setString(5, r.getName());
			ps.setString(6, r.getTelefonnummer());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) r.setId(rs.getInt(1));
		} 
		catch (SQLException e) 
		{
			throw new DAOException("ERROR: failed to save Reservierung to DB!");
		}
		
		return r;
	}
	
	public ArrayList<Reservierung> search(Reservierung r) throws DAOException 
	{
		try 
		{
			PreparedStatement ps = c.prepareStatement("SELECT id, datum, dauer, personen, tischnummer, name, telefonnummer FROM reservierung WHERE (id=? OR ?=-1) AND datum LIKE ? AND (dauer=? OR ?=-1) AND (personen=? OR ?=-1) AND (tischnummer=? OR ?=-1) AND name LIKE ? AND telefonnummer LIKE ?");
			ps.setInt(1, r.getId());
			ps.setInt(2, r.getId());
			ps.setDate(3, r.getDatum());
			ps.setInt(4, r.getDauer());
			ps.setInt(5, r.getDauer());
			ps.setInt(6, r.getPersonen());
			ps.setInt(7, r.getPersonen());
			ps.setInt(8, r.getTischnummer());
			ps.setInt(9, r.getTischnummer());
			ps.setString(10, r.getName());
			ps.setString(11, r.getTelefonnummer());
			
			ResultSet rs = ps.executeQuery();
			ArrayList<Reservierung> al= new ArrayList<Reservierung>();
			
			while(rs.next()) 
			{
				al.add(new Reservierung(rs.getInt("id"), rs.getDate("datum"), rs.getInt("dauer"), rs.getInt("personen"), rs.getInt("tischnummer"), rs.getString("name"), rs.getString("telefonnummer")));		
			}
			
			return al;			
		} 
		catch (SQLException e) 
		{
			throw new DAOException("ERROR: failed to search DB for Reservierungen!");
		}
	}
	
	public void update(Reservierung r) throws DAOException 
	{	
		try
		{
			PreparedStatement ps = c.prepareStatement("UPDATE reservierung SET datum=?, dauer=?, personen=?, tischnummer=?, name=?, telefonnummer=? WHERE id=?");
			
			ps.setDate(1, r.getDatum());
			ps.setInt(2, r.getDauer());
			ps.setInt(3, r.getPersonen());
			ps.setInt(4, r.getTischnummer());
			ps.setString(5, r.getName());
			ps.setString(6, r.getTelefonnummer());
			ps.setInt(7, r.getId());
						
			int updatedRows = ps.executeUpdate();
			
			log.info("SQL updated rows: " + updatedRows);
			
			if(updatedRows == 0)
				throw new DAOException("No Reservierung entity could be updated.");				
		}
		catch(SQLException e)
		{
			throw new DAOException("A database error occurred.");
		}		
	}
	
	public void delete(Reservierung r) throws DAOException 
	{
		try 
		{
			PreparedStatement ps = c.prepareStatement("DELETE FROM reservierung WHERE id=?");
			ps.setInt(1, r.getId());
			ps.executeUpdate();
		}
		catch (SQLException e) 
		{
			throw new DAOException("ERROR: failed to delete Reservierung from DB!");
		}
	}
}
