/**
 * 
 */
package sepm.ss13.gastTests;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.dao.DBConnector;
import sepm.ss13.gast.dao.JDBCRechnungDAO;
import sepm.ss13.gast.domain.Rechnung;

/**
 * @author Viper
 *
 */
public class Test_JDBCRechnungDAO {

	private static ApplicationContext ac;
	static DBConnector dbc;
	static JDBCRechnungDAO test = null;
	
	static Rechnung r1 = null; //create
	static Rechnung r2 = null; //create
	
	static Rechnung r3 = null; //serach
	static Rechnung r4 = null; //serach
	static Rechnung r5 = null; //serach
	
	
	static ArrayList<Integer> mykeys = new ArrayList<Integer>();
	static ArrayList<Integer> mykeys_generated = new ArrayList<Integer>();
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ac = new ClassPathXmlApplicationContext("spring-config.xml");
		dbc = (DBConnector) ac.getBean("databaseManager");
		test = new JDBCRechnungDAO(dbc.getConnection());
		
		
		r1 = new Rechnung(-1, new Date(),10,null);
		r2 = new Rechnung(-1, null,20,null);
		
		
		r3 = new Rechnung(-1, new Date(), 30,null);
		r4 = new Rechnung(-1, new Date(), 40,null);
		r5 = new Rechnung(-1, new Date(), 40,null);
		
		Rechnung r_temp = null;
		r_temp = test.create(r3);
		r3.setId(r_temp.getId());
		mykeys.add(r_temp.getId());		
		r_temp = test.create(r4);
		r4.setId(r_temp.getId());
		mykeys.add(r_temp.getId());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		for(int i = 0; i < mykeys.size();i++){
			try {
				PreparedStatement ps=dbc.getConnection().prepareStatement("DELETE FROM rechnung WHERE id=?");
				ps.setInt(1,mykeys.get(i));
				ps.executeUpdate();
			} catch (SQLException e) {
				throw new DAOException("ERROR: failed to delete rechnung from DB!");
			}
			catch (NullPointerException e) {
				throw new IllegalArgumentException();
			}
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		for(int i = 0; i < mykeys_generated.size();i++){
			try {
				PreparedStatement ps=dbc.getConnection().prepareStatement("DELETE FROM rechnung WHERE id=?");
				ps.setInt(1,mykeys_generated.get(i));
				ps.executeUpdate();
			} catch (SQLException e) {
				throw new DAOException("ERROR: failed to delete rechnung from DB!");
			}
			catch (NullPointerException e) {
				throw new IllegalArgumentException();
			}
		}
	}

	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCRechnungDAO#create(sepm.ss13.gast.domain.Rechnung)}.
	 * @throws DAOException 
	 */
	@Test
	public void testCreate_1() throws DAOException {
		
		/* INSERT */
		Rechnung r_test = null;
		r_test = test.create(r1);
		
		mykeys_generated.add(r_test.getId());
		
		/* GET INSERTED */
		try {
			PreparedStatement ps = dbc.getConnection().prepareStatement("SELECT id,datum,trinkgeld FROM rechnung WHERE id=?");
			ps.setInt(1,r_test.getId());
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				long t = r1.getDatum().getTime();
				java.sql.Date dt = new java.sql.Date(t);
				
				assertThat(rs.getInt("id"), equalTo(r_test.getId()));
				assertThat(rs.getDate("datum").toString(), equalTo(dt.toString()));
				assertThat(rs.getInt("trinkgeld"), equalTo(r1.getTrinkgeld()));
				return;
			}else{
				fail("No Data found");
				return;
			}
		} catch (SQLException e) {
			fail("ERROR: failed to search DB for bill!");
			return;
		}
		catch (NullPointerException e) {
			fail("NullPointerException");
			return;
		}
	}
	

	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCRechnungDAO#create(sepm.ss13.gast.domain.Rechnung)}.
	 * @throws DAOException 
	 */
	@Test (expected=IllegalArgumentException.class) 
	public void testCreate_2() throws DAOException {
		
		/* INSERT */
		test.create(null);
		
	}	

	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCRechnungDAO#create(sepm.ss13.gast.domain.Rechnung)}.
	 * @throws DAOException 
	 */
	@Test (expected=IllegalArgumentException.class) 
	public void testCreate_3() throws DAOException {
		
		/* INSERT */
		test.create(r2);
		
	}		
	
	
	
	

	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCRechnungDAO#search(sepm.ss13.gast.domain.Rechnung)}.
	 * @throws DAOException 
	 */
	@Test
	public void testSearch_1() throws DAOException {
		
		/* INSERT */
		ArrayList<Rechnung> r_test = new ArrayList<Rechnung>();
		r_test = test.search(r3);
	
		for(int i = 0; i < r_test.size();i++){
			long t = r3.getDatum().getTime();
			java.sql.Date dt = new java.sql.Date(t);
			
			long t2 = r_test.get(i).getDatum().getTime();
			java.sql.Date dt2 = new java.sql.Date(t2);
			
			assertThat(r_test.get(i).getId(), equalTo(r3.getId()));
			assertThat(dt2.toString(), equalTo(dt.toString()));
			assertThat(r_test.get(i).getTrinkgeld(), equalTo(r3.getTrinkgeld()));
		}
		
		r_test = new ArrayList<Rechnung>();
		r_test = test.search(r4);
	
		for(int i = 0; i < r_test.size();i++){
			long t = r4.getDatum().getTime();
			java.sql.Date dt = new java.sql.Date(t);
			
			long t2 = r_test.get(i).getDatum().getTime();
			java.sql.Date dt2 = new java.sql.Date(t2);
			
			assertThat(r_test.get(i).getId(), equalTo(r4.getId()));
			assertThat(dt2.toString(), equalTo(dt.toString()));
			assertThat(r_test.get(i).getTrinkgeld(), equalTo(r4.getTrinkgeld()));
		}
	}
	

	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCRechnungDAO#search(sepm.ss13.gast.domain.Rechnung)}.
	 * @throws DAOException 
	 */
	@Test (expected=IllegalArgumentException.class) 
	public void testSearch_2() throws DAOException {
		
		/* INSERT */
		test.search(null);
	
	}
	
	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCRechnungDAO#search(sepm.ss13.gast.domain.Rechnung)}.
	 * @throws DAOException 
	 */
	@Test
	public void testSearch_3() throws DAOException {
		
		/* INSERT */
		Rechnung r_test = null;
		r_test = test.create(r5);
		
		mykeys_generated.add(r_test.getId());
		
		/* GET INSERTED */
		try {
			PreparedStatement ps = dbc.getConnection().prepareStatement("SELECT id,datum,trinkgeld FROM rechnung WHERE id=?");
			ps.setInt(1,r_test.getId());
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				long t = r5.getDatum().getTime();
				java.sql.Date dt = new java.sql.Date(t);
				
				assertThat(rs.getInt("id"), equalTo(r_test.getId()));
				assertThat(rs.getDate("datum").toString(), equalTo(dt.toString()));
				assertThat(rs.getInt("trinkgeld"), equalTo(r5.getTrinkgeld()));
				return;
			}else{
				fail("No Data found");
				return;
			}
		} catch (SQLException e) {
			fail("ERROR: failed to search DB for bill!");
			return;
		}
		catch (NullPointerException e) {
			fail("NullPointerException");
			return;
		}
	
	}

}
