package sepm.ss13.gast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import sepm.ss13.gast.domain.Rezept;
import sepm.ss13.gast.domain.Ware;

/**
 * 
 */
public class JDBCRezeptDAO implements RezeptDAO{

	private static final Logger log = Logger.getLogger (JDBCRezeptDAO.class);

	private Connection c;
	
	public JDBCRezeptDAO(Connection c) {
		this.c=c;
	}

	public Rezept create(Rezept toCreate) throws DAOException {
		try 
		{
			PreparedStatement ps = c.prepareStatement("INSERT INTO rezept (produktid,warenid,menge) VALUES (?,?,?)");
			ps.setInt(1, toCreate.getProduktId());
			ps.setInt(2, toCreate.getWareId());
			ps.setInt(3, toCreate.getMenge());
			ps.executeUpdate();
			
			//ResultSet rs = ps.getGeneratedKeys();
			//if(rs.next()) toCreate.setId(rs.getInt(1));
		} 
		catch (SQLException e) 
		{
			throw new DAOException("ERROR: failed to save Rezept to DB!");
		}
		
		return toCreate;
	}

	public ArrayList<Rezept> search(Rezept toSearch) throws DAOException {
		try 
		{
			PreparedStatement ps = c.prepareStatement("SELECT produktid,warenid,menge,bezeichnung,einheit FROM rezept,ware WHERE (produktid=? OR ? IS NULL) AND (warenid=? OR ? IS NULL) AND (menge = ? or ? = -1) and id = warenid");
			
			//System.out.println("SELECT produktid,warenid,menge,bezeichnung FROM rezept,ware WHERE (produktid=" + toSearch.getProduktId() + " OR " + toSearch.getProduktId() + " IS NULL) AND (warenid=? OR ? IS NULL) AND (menge = ? or ? = -1) and id = warenid");

			ps.setObject(1, toSearch.getProduktId());
			ps.setObject(2, toSearch.getProduktId());
			ps.setObject(3, toSearch.getWareId());
			ps.setObject(4, toSearch.getWareId());
			
			ps.setInt(5, toSearch.getMenge());
			ps.setInt(6, toSearch.getMenge());
			
			ResultSet rs = ps.executeQuery();
			ArrayList<Rezept> al= new ArrayList<Rezept>();
			
			while(rs.next()) 
			{
				//System.out.println(rs.getInt("produktid") + " , " + rs.getInt("warenid") + " , " + rs.getInt("menge") + " , " + rs.getString("bezeichnung"));
				al.add(new Rezept(rs.getInt("produktid"),rs.getInt("warenid"),rs.getInt("menge"),rs.getString("bezeichnung") + " (" + rs.getString("einheit") + ")"));		
			}
			
			return al;			
		} 
		catch (SQLException e) 
		{
			throw new DAOException("ERROR: failed to search DB for Rezepte!");
		}
	}

	public void update(Rezept toUpdate) throws DAOException {
		try
		{
			PreparedStatement ps = c.prepareStatement("UPDATE rezept SET menge=? WHERE produktid=? and warenid=?");
			
			ps.setInt(1, toUpdate.getMenge());
			ps.setInt(2, toUpdate.getProduktId());
			ps.setInt(3, toUpdate.getWareId());
						
			int updatedRows = ps.executeUpdate();
			
			log.info("SQL updated rows: " + updatedRows);
			
			if(updatedRows == 0)
				throw new DAOException("No Rezept entity could be updated.");				
		}
		catch(SQLException e)
		{
			throw new DAOException("A database error occurred.");
		}	
	}

	public void delete(Rezept toDelete) throws DAOException {
		try 
		{
			PreparedStatement ps = c.prepareStatement("DELETE FROM rezept WHERE produktid=?");
			ps.setInt(1, toDelete.getProduktId());
			
			ps.executeUpdate();
		}
		catch (SQLException e) 
		{
			throw new DAOException("ERROR: failed to delete Ware from DB!");
		}
	}
}
