package sepm.ss13.gast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import sepm.ss13.gast.domain.Tisch;


public class JDBCTischDAO implements TischDAO {

	private static final Logger log = Logger.getLogger (JDBCTischDAO.class);
	private Connection c;
	
	public JDBCTischDAO(Connection c) {
		this.c=c;
	}
	
	public Tisch create(Tisch t) throws DAOException {
		try 
		{
			PreparedStatement ps = c.prepareStatement("INSERT INTO tisch (id, nummer, plaetze, beschreibung, deleted) VALUES (NULL,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, t.getNummer());
			ps.setInt(2, t.getPlaetze());
			ps.setString(3, t.getBeschreibung());
			ps.setBoolean(4, t.getDeleted());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) t.setId(rs.getInt(1));
		} 
		catch (SQLException e) 
		{
			throw new DAOException("ERROR: failed to save Tisch to DB!");
		}
		return t;
	}

	public ArrayList<Tisch> search(Tisch t) throws DAOException {
		try{
			PreparedStatement ps = c.prepareStatement("SELECT id, nummer, plaetze, beschreibung,deleted FROM tisch WHERE (id=? OR ? IS NULL) AND (nummer=? or ?=0) AND (plaetze>=? or ?=0) AND (beschreibung LIKE ? or ? LIKE '') AND deleted=?");
			ps.setObject(1, t.getId());
			ps.setObject(2, t.getId());
			ps.setInt(3, t.getNummer());
			ps.setInt(4, t.getNummer());
			ps.setInt(5, t.getPlaetze());
			ps.setInt(6, t.getPlaetze());
			ps.setString(7, t.getBeschreibung());
			ps.setString(8, t.getBeschreibung());
			ps.setBoolean(9, t.getDeleted());
			ResultSet rs = ps.executeQuery();
			ArrayList<Tisch> tl= new ArrayList<Tisch>();
			
			while(rs.next()) 
			{
				tl.add(new Tisch(rs.getInt("id"), rs.getInt("nummer"), rs.getInt("plaetze"), rs.getString("beschreibung"), rs.getBoolean("deleted")));		
			}
			
			return tl;			
		} 
		catch (SQLException e) 
		{
			throw new DAOException("ERROR: failed to search DB for Tische!");
		}
	}

	public void update(Tisch t) throws DAOException {
		try
		{
			PreparedStatement ps = c.prepareStatement("UPDATE tisch SET nummer=?, plaetze=?, beschreibung=?  WHERE id=?");
			
			ps.setInt(1, t.getNummer());
			ps.setInt(2, t.getPlaetze());
			ps.setString(3, t.getBeschreibung());
			ps.setInt(4, t.getId());
						
			int updatedRows = ps.executeUpdate();
			
			log.info("SQL updated rows: " + updatedRows);
			
			if(updatedRows == 0)
				throw new DAOException("No Tisch entity could be updated.");				
		}
		catch(SQLException e)
		{
			throw new DAOException("A database error occurred.");
		}

	}

	public void delete(Tisch t) throws DAOException {
		try
		{
			PreparedStatement ps = c.prepareStatement("UPDATE tisch SET deleted=true  WHERE id=?");
			
			ps.setInt(1, t.getId());
						
			int updatedRows = ps.executeUpdate();
			
			log.info("SQL updated rows: " + updatedRows);
			
			if(updatedRows == 0)
				throw new DAOException("No Tisch entity could be updated.");				
		}
		catch(SQLException e)
		{
			throw new DAOException("A database error occurred.");
		}
	}

}
