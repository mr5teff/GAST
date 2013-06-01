package sepm.ss13.gast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sepm.ss13.gast.domain.Konsumstatistik;

public class JDBCKonsumstatistikDAO implements KonsumstatistikDAO
{
	private Connection c;
	
	public JDBCKonsumstatistikDAO(Connection c) 
	{
		this.c = c;
	}
	
	public ArrayList<Konsumstatistik> searchPopularProducts() throws DAOException 
	{
		try
		{
			PreparedStatement ps = c.prepareStatement("select produktname, count(produktname) as absatzmenge from bestellung group by produktname order by count(produktname) desc");
	
			ResultSet rs = ps.executeQuery();
			
			ArrayList<Konsumstatistik> al = new ArrayList<Konsumstatistik>();
		
			while(rs.next()) 
			{
				al.add(new Konsumstatistik(rs.getString("produktname"),rs.getInt("absatzmenge")));	
			}
			
			return al;
		} 
		catch(SQLException e) 
		{
			throw new DAOException("ERROR: failed to search DB for products!");
		}
		catch(NullPointerException e) 
		{
			throw new IllegalArgumentException();
		}
	}
}
