package sepm.ss13.gast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sepm.ss13.gast.domain.Rechnung;

/**
 * 
 */
public class JDBCRechnungDAO implements RechnungDAO {
	private Connection c;
	
	public JDBCRechnungDAO(Connection c) {
		this.c=c;
	}
	
	public Rechnung create(Rechnung r) throws DAOException {
		try {
			long t = r.getDatum().getTime();
			java.sql.Date dt = new java.sql.Date(t);
			PreparedStatement ps=c.prepareStatement("INSERT INTO rechnung (id,datum,trinkgeld,pdf) VALUES (NULL,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1,dt);
			ps.setInt(2, r.getTrinkgeld());
			ps.setBytes(3,r.getPdf());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) r.setId(rs.getInt(1));
		} catch (SQLException e) {
			throw new DAOException("ERROR: failed to save bill to DB!");
		}
		catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
		
		return r;
	}
	
	public ArrayList<Rechnung> search(Rechnung r) throws DAOException {
		try {
			java.sql.Date dt = null;
			if(r.getDatum() != null){
				long t = r.getDatum().getTime();
				dt = new java.sql.Date(t);
			}
			/*
			PreparedStatement ps=c.prepareStatement("SELECT id,datum,trinkgeld FROM rechnung WHERE (id=? OR ?=-1) AND (datum=? OR ? IS NULL)");
			ps.setInt(1,r.getId());
			ps.setInt(2,r.getId());
			ps.setDate(3,dt);
			ps.setDate(4,dt);
			*/
			PreparedStatement ps=c.prepareStatement("SELECT id,datum,trinkgeld,pdf FROM rechnung WHERE (id=? OR ? IS NULL)");
			ps.setObject(1,r.getId());
			ps.setObject(2,r.getId());
			
			ResultSet rs=ps.executeQuery();
			ArrayList<Rechnung> al=new ArrayList<Rechnung>();
			while(rs.next()) {
				al.add(new Rechnung(rs.getInt("id"),rs.getDate("datum"),rs.getInt("trinkgeld"),rs.getBytes("pdf")));
				
			}
			return al;
		} catch (SQLException e) {
			throw new DAOException("ERROR: failed to search DB for bills!");
		}
		catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
	}
}
