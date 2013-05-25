package sepm.ss13.gast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import sepm.ss13.gast.domain.Produkt;

/**
 * 
 */
public class JDBCProduktDAO implements ProduktDAO {

	private static final Logger log = Logger.getLogger (JDBCProduktDAO.class);
	
	private Connection c;
	
	public JDBCProduktDAO(Connection c) {
		this.c=c;
	}
	
	public Produkt create(Produkt p) throws DAOException {
		try {
			PreparedStatement ps=c.prepareStatement("INSERT INTO produkt (id,name,typid,preis,deleted) VALUES (NULL,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,p.getName());
			ps.setInt(2, p.getKategorie());
			ps.setInt(3, p.getPreis());
			ps.setBoolean(4, p.getDeleted());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) p.setId(rs.getInt(1));
		} catch (SQLException e) {
			throw new DAOException("ERROR: failed to save product to DB!");
		}
		catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
		
		return p;
	}
	
	public ArrayList<Produkt> search(Produkt p) throws DAOException {
		try {
			PreparedStatement ps=c.prepareStatement("SELECT id,name,typid,preis,deleted FROM produkt WHERE (id=? OR ? IS NULL) AND name LIKE ? AND (typid=? OR ? IS NULL) AND deleted=?");
			ps.setObject(1,p.getId());
			ps.setObject(2,p.getId());
			ps.setString(3,"%"+p.getName()+"%");
			ps.setObject(4,p.getKategorie());
			ps.setObject(5,p.getKategorie());
			ps.setObject(6, p.getDeleted());

			ResultSet rs=ps.executeQuery();
			ArrayList<Produkt> al=new ArrayList<Produkt>();
			while(rs.next()) {
				al.add(new Produkt(rs.getInt("id"),rs.getString("name"),rs.getInt("typid"),rs.getInt("preis"),rs.getBoolean("deleted")));
				
			}
			return al;
		} catch (SQLException e) {
			throw new DAOException("ERROR: failed to search DB for products!");
		}
		catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
	}

	public void update(Produkt p) throws DAOException 
	{	
		try
		{
			PreparedStatement ps = c.prepareStatement("UPDATE produkt SET name=?, typid=?, preis=? WHERE id=?");
			
			ps.setString(1, p.getName());
			ps.setInt(2, p.getKategorie());
			ps.setInt(3, p.getPreis());
			ps.setInt(4, p.getId());
						
			int updatedRows = ps.executeUpdate();
			
			log.info("SQL updated rows: " + updatedRows);
			
			if(updatedRows == 0)
				throw new DAOException("product id not found in DB!");				
		}
		catch(SQLException e) {
			throw new DAOException("ERROR: failed to update product in DB!");
		}
		catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
	}
	

	
	public void delete(Produkt p) throws DAOException{
		try
		{
			PreparedStatement ps = c.prepareStatement("UPDATE produkt SET deleted = true WHERE id=?");
			
			ps.setInt(1, p.getId());
						
			int updatedRows = ps.executeUpdate();
			
			log.info("SQL updated rows: " + updatedRows);
			
			if(updatedRows == 0)
				throw new DAOException("product id not found in DB!");				
		}
		catch(SQLException e) {
			throw new DAOException("ERROR: failed to update product in DB!");
		}
		catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
	}
	
}
