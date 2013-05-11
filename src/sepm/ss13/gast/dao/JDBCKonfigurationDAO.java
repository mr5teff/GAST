package sepm.ss13.gast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import sepm.ss13.gast.domain.Konfiguration;

/**
 * 
 */
public class JDBCKonfigurationDAO {

	private static final Logger log = Logger.getLogger (JDBCKonfigurationDAO.class);
	
	private Connection c;
	
	public JDBCKonfigurationDAO(Connection c) {
		this.c=c;
	}
	
	public Konfiguration load() throws DAOException {
		try {
			PreparedStatement ps=c.prepareStatement("SELECT name,adresse,tel,logo,tischanzahl FROM konfiguration");
			
			Konfiguration k = null;
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				k = new Konfiguration(rs.getString("name"), rs.getString("adresse"), rs.getString("tel"), rs.getBytes("logo"), rs.getInt("tischanzahl"));
			}
			
			return k;
		} catch (SQLException e) {
			throw new DAOException("ERROR: failed to load configuration from DB!");
		}
	}
	
	public void save(Konfiguration k) throws DAOException {
		try {
			PreparedStatement ps=c.prepareStatement("UPDATE konfiguration SET name=?,adresse=?,tel=?,logo=?,tischanzahl=?");
			ps.setString(1,k.getName());
			ps.setString(2,k.getAdresse());
			ps.setString(3,k.getTel());
			ps.setBytes(4,k.getLogo());
			ps.setInt(5,k.getTischanzahl());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("ERROR: failed to save configuration to DB!");
		}
		catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
	}
}