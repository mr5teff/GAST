package sepm.ss13.gast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import sepm.ss13.gast.domain.Bestellung;

/**
 * 
 */
public class JDBCBestellungDAO implements BestellungDAO {

	private static final Logger log = Logger.getLogger (JDBCBestellungDAO.class);
	
	private Connection c;
	
	public JDBCBestellungDAO(Connection c) {
		this.c=c;
	}
	
	public Bestellung create(Bestellung b) throws DAOException {
		try {
			PreparedStatement ps=c.prepareStatement("INSERT INTO bestellung (id,tischnummer,produktid,preis,rechnungid,status) VALUES (NULL,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1,b.getTisch());
			ps.setInt(2, b.getProdukt());
			ps.setInt(3,b.getPreis());
			ps.setInt(4, b.getRechnung());
			ps.setString(5, b.getStatus());
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
			PreparedStatement ps=c.prepareStatement("SELECT id,tischnummer,produktid,preis,rechnungid,status FROM bestellung WHERE (id=? OR ?=-1) AND status LIKE ? AND (tischnummer=? OR ?=-1)");
			ps.setInt(1,b.getId());
			ps.setInt(2,b.getId());
			ps.setString(3,b.getStatus());
			ps.setInt(4,b.getTisch());
			ps.setInt(5,b.getTisch());
			
			ResultSet rs=ps.executeQuery();
			ArrayList<Bestellung> al=new ArrayList<Bestellung>();
			while(rs.next()) {
				al.add(new Bestellung(rs.getInt("id"),rs.getInt("tischnummer"),rs.getInt("produktid"),rs.getInt("preis"),rs.getInt("rechnungid"),rs.getString("status")));
				
			}
			return al;
		} catch (SQLException e) {
			throw new DAOException("ERROR: failed to search DB for orders!");
		}
		catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
	}
	
	public void delete(Bestellung b) throws DAOException {
		try {
			PreparedStatement ps=c.prepareStatement("DELETE FROM bestellung WHERE id=?");
			ps.setInt(1,b.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("ERROR: failed to delete order from DB!");
		}
		catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
	}
}
