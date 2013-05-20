package sepm.ss13.gast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sepm.ss13.gast.domain.Bestellung;
import sepm.ss13.gast.domain.Produkt;

/**
 * 
 */
public class JDBCBestellungDAO implements BestellungDAO {
	private Connection c;
	
	public JDBCBestellungDAO(Connection c) {
		this.c=c;
	}
	
	public Bestellung create(Bestellung b) throws DAOException {
		try {
			PreparedStatement ps = null;
			
			if(b.getRechnung() == -1 && !("bezahlt".equalsIgnoreCase(b.getStatus()))){
				ps=c.prepareStatement("INSERT INTO bestellung (id,tischnummer,produktid,produktname,preis,rechnungid,status,deleted) VALUES (NULL,?,?,?,?,NULL,?,?)",Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1,b.getTisch());
				ps.setInt(2, b.getProdukt());
				ps.setString(3, b.getPname());
				ps.setInt(4,b.getPreis());
				//ps.setInt(5, b.getRechnung());
				ps.setString(5, b.getStatus());
				ps.setBoolean(6,b.getDeleted());
			}else{
				ps=c.prepareStatement("INSERT INTO bestellung (id,tischnummer,produktid,produktname,preis,rechnungid,status,deleted) VALUES (NULL,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, b.getTisch());
				ps.setInt(2, b.getProdukt());
				ps.setString(3, b.getPname());
				ps.setInt(4, b.getPreis());
				ps.setInt(5, b.getRechnung());
				ps.setString(6, b.getStatus());
				ps.setBoolean(7,b.getDeleted());
			}
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) b.setId(rs.getInt(1));
		} catch (SQLException e) {
			throw new DAOException("ERROR: failed to save order to DB!");
		}
		catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
		
		return b;
	}
	
	public ArrayList<Bestellung> search(Bestellung b) throws DAOException {
		try {
			PreparedStatement ps=c.prepareStatement("SELECT b.id,b.tischnummer,b.produktid,b.produktname,b.preis,b.rechnungid,b.status,b.deleted FROM bestellung b WHERE (id=? OR ?=-1)  AND (tischnummer=? OR ?=-1) AND (status like ? or  ?='-1')");
			ps.setInt(1,b.getId());
			ps.setInt(2,b.getId());
			ps.setInt(3,b.getTisch());
			ps.setInt(4,b.getTisch());
			ps.setString(5,b.getStatus());
			ps.setString(6,b.getStatus());
			
			ResultSet rs=ps.executeQuery();
			ArrayList<Bestellung> al=new ArrayList<Bestellung>();
			while(rs.next()) {
				al.add(new Bestellung(rs.getInt("id"),rs.getInt("tischnummer"),rs.getInt("produktid"),rs.getString("produktname"),rs.getInt("preis"),rs.getInt("rechnungid"),rs.getString("status"), rs.getBoolean("deleted")));
				
			}
			return al;
		} catch (SQLException e) {
			throw new DAOException("ERROR: failed to search DB for orders!");
		}
		catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
	}
	
	public void update(Bestellung b) throws DAOException 
	{	
		try
		{
			PreparedStatement ps = c.prepareStatement("UPDATE bestellung SET tischnummer=?, produktid=?, produktname=?, preis=?, rechnungid=?, status=?, deleted=? WHERE id=?");
			
			ps.setInt(1,b.getTisch());
			ps.setInt(2,b.getProdukt());
			ps.setString(3, b.getPname());
			ps.setInt(4,b.getPreis());
			ps.setInt(5,b.getRechnung());
			ps.setString(6,b.getStatus());
			ps.setBoolean(7,b.getDeleted());
			ps.setInt(8,b.getId());
						
			int updatedRows = ps.executeUpdate();
			
			if(updatedRows == 0)
				throw new DAOException("Bestellung id not found in DB!");				
		}
		catch(SQLException e) {
			throw new DAOException("ERROR: failed to update Bestellung in DB!");
		}
		catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
	}
	
	public void delete(Bestellung b) throws DAOException {
		try {
			PreparedStatement ps=c.prepareStatement("update bestellung set deleted = true where id=?");
			ps.setInt(1,b.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("ERROR: failed to update order from DB!");
		}
		catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
	}
}
