package sepm.ss13.gast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import sepm.ss13.gast.domain.ProduktKategorie;

/**
 * 
 */
public class JDBCProduktKategorieDAO implements ProduktKategorieDAO {

	private static final Logger log = Logger.getLogger (JDBCProduktKategorieDAO.class);
	
	private Connection c;
	
	public JDBCProduktKategorieDAO(Connection c) {
		this.c=c;
	}
	
	public ProduktKategorie create(ProduktKategorie p) throws DAOException {
		try {
			PreparedStatement ps=c.prepareStatement("INSERT INTO produkttyp (id,bezeichnung,kurzbezeichnung,steuer) VALUES (NULL,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,p.getBezeichnung());
			ps.setString(2,p.getKurzbezeichnung());
			ps.setInt(3,p.getSteuer());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) p.setId(rs.getInt(1));
		} catch (SQLException e) {
			throw new DAOException("ERROR: failed to save product category to DB!");
		}
		catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
		
		return p;
	}
	
	public ArrayList<ProduktKategorie> search(ProduktKategorie p) throws DAOException {
		try {
			PreparedStatement ps=c.prepareStatement("SELECT id,bezeichnung,kurzbezeichnung,deleted,steuer FROM produkttyp WHERE (id=? OR ? IS NULL) AND deleted=?");
			ps.setObject(1,p.getId());
			ps.setObject(2,p.getId());
			ps.setObject(3, p.getDeleted());
			
			ResultSet rs=ps.executeQuery();
			ArrayList<ProduktKategorie> al=new ArrayList<ProduktKategorie>();
			while(rs.next()) {
				al.add(new ProduktKategorie(rs.getInt("id"),rs.getString("bezeichnung"),rs.getString("kurzbezeichnung"),rs.getBoolean("deleted"),rs.getInt("steuer")));
				
			}
			return al;
		} catch (SQLException e) {
			throw new DAOException("ERROR: failed to search DB for product categories!");
		}
		catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
	}

	public void update(ProduktKategorie p) throws DAOException 
	{	
		try
		{
			PreparedStatement ps = c.prepareStatement("UPDATE produkttyp SET bezeichnung=?,kurzbezeichnung=?,steuer=? WHERE id=?");
			
			ps.setString(1, p.getBezeichnung());
			ps.setString(2, p.getKurzbezeichnung());
			ps.setInt(3,p.getSteuer());
			ps.setInt(4, p.getId());
						
			int updatedRows = ps.executeUpdate();
			
			log.info("SQL updated rows: " + updatedRows);
			
			if(updatedRows == 0)
				throw new DAOException("product category id not found in DB!");				
		}
		catch(SQLException e) {
			throw new DAOException("ERROR: failed to update product category in DB!");
		}
		catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
	}
	
	public void delete(ProduktKategorie p) throws DAOException {
		try {		
			PreparedStatement ps = c.prepareStatement("UPDATE produkttyp SET deleted = true WHERE id=?");
			
			ps.setInt(1, p.getId());
						
			int updatedRows = ps.executeUpdate();
			log.info("SQL updated rows: " + updatedRows);
			
			if(updatedRows == 0)
				throw new DAOException("producttype id not found in DB!");	
			
		} catch (SQLException e) {
			throw new DAOException("ERROR: failed to delete product category from DB!");
		}
		catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
	}
}
