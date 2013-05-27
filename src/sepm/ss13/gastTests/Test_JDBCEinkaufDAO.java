package sepm.ss13.gastTests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import sepm.ss13.gast.dao.JDBCEinkaufDAO;
import sepm.ss13.gast.dao.JDBCProduktDAO;
import sepm.ss13.gast.dao.JDBCWareDAO;
import sepm.ss13.gast.domain.Einkauf;
import sepm.ss13.gast.domain.Tisch;
import sepm.ss13.gast.domain.Ware;

public class Test_JDBCEinkaufDAO {

	private static ApplicationContext ac;
	static DBConnector dbc;
	static JDBCWareDAO test_Ware = null;
	static JDBCEinkaufDAO test_Einkauf = null;

	static Ware w1 = null; //create
	static Ware w2 = null; //create
	static Einkauf e1 = null; // create
	static Einkauf e2 = null; // create

	static Ware w3 = null; // search
	static Ware w4 = null; // search
	static Einkauf e3 = null; // search
	static Einkauf e4 = null; // search

	static Ware w5 = null; // update
	static Ware w6 = null; // update
	static Einkauf e5 = null; // update
	static Einkauf e6 = null; // update

	static Ware w7 = null; // delete
	static Ware w8 = null; // delete
	static Einkauf e7 = null; // delete
	static Einkauf e8 = null; // delete
	
	
	static ArrayList<Integer> mykeys_Einkauf = new ArrayList<Integer>();
	static ArrayList<Integer> mykeys_generated_Einkauf = new ArrayList<Integer>();
	static ArrayList<Integer> mykeys_Ware = new ArrayList<Integer>();
	static ArrayList<Integer> mykeys_generated_Ware = new ArrayList<Integer>();
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ac = new ClassPathXmlApplicationContext("spring-config.xml");
		dbc = (DBConnector) ac.getBean("databaseManager");
		test_Einkauf = new JDBCEinkaufDAO(dbc.getConnection());
		test_Ware = new JDBCWareDAO(dbc.getConnection());
		
		w1 = new Ware(-1, "Blutwurst", "gramm", 10000);
		w2 = new Ware(-1, "Weißbier", "milliliter", 50000);
		e1 = new Einkauf(-1, -1, 30000, new Date(0), 30);
		e2 = new Einkauf(-1, -1, 60000, new Date(0), 40);

		w3 = new Ware(-1, "Blutwurst", "gramm", 10000);
		w4 = new Ware(-1, "Weißbier", "milliliter", 50000);
		e3 = new Einkauf(-1, -1, 30000, new Date(0), 30);
		e4 = new Einkauf(-1, -1, 60000, new Date(0), 40);

		w5 = new Ware(-1, "Blutwurst", "gramm", 10000);
		w6 = new Ware(-1, "Weißbier", "milliliter", 50000);
		e5 = new Einkauf(-1, -1, 30000, new Date(0), 30);
		e6 = new Einkauf(-1, -1, 60000, new Date(0), 40);

		w7 = new Ware(-1, "Blutwurst", "gramm", 10000);
		w8 = new Ware(-1, "Weißbier", "milliliter", 50000);
		e7 = new Einkauf(-1, -1, 30000, new Date(0), 30);
		e8 = new Einkauf(-1, -1, 60000, new Date(0), 40);
		
		Ware w_temp = null;
		Einkauf e_temp = null;
		w_temp = test_Ware.create(w3);
		e_temp = test_Einkauf.create(e3);
		w3.setId(w_temp.getId());
		e3.setId(e_temp.getId());
		mykeys_Ware.add(w3.getId());
		mykeys_Einkauf.add(e3.getId());
		w_temp = test_Ware.create(w4);
		e_temp = test_Einkauf.create(e4);
		w4.setId(w_temp.getId());
		e4.setId(e_temp.getId());
		mykeys_Ware.add(w4.getId());
		mykeys_Einkauf.add(e4.getId());
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		for(int i = 0; i < mykeys_Einkauf.size();i++){
			try {
				PreparedStatement ps=dbc.getConnection().prepareStatement("DELETE FROM einkauf WHERE id=?");
				ps.setInt(1,mykeys_Einkauf.get(i));
				ps.executeUpdate();
			} catch (SQLException e) {
				throw new DAOException("ERROR: failed to delete Einkauf from DB!");
			}
			catch (NullPointerException e) {
				throw new IllegalArgumentException();
			}
		}
		
		for(int i = 0; i < mykeys_Ware.size();i++){
			try {
				PreparedStatement ps=dbc.getConnection().prepareStatement("DELETE FROM ware WHERE id=?");
				ps.setInt(1,mykeys_Ware.get(i));
				ps.executeUpdate();
			} catch (SQLException e) {
				throw new DAOException("ERROR: failed to delete ware from DB!");
			}
			catch (NullPointerException e) {
				throw new IllegalArgumentException();
			}
		}
	}
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		for(int i = 0; i < mykeys_generated_Einkauf.size();i++){
			try {
				PreparedStatement ps=dbc.getConnection().prepareStatement("DELETE FROM einkauf WHERE id=?");
				ps.setInt(1,mykeys_generated_Einkauf.get(i));
				ps.executeUpdate();
			} catch (SQLException e) {
				throw new DAOException("ERROR: failed to delete Einkauf from DB!");
			}
			catch (NullPointerException e) {
				throw new IllegalArgumentException();
			}
		}
		

		for(int i = 0; i < mykeys_generated_Ware.size();i++){
			try {
				PreparedStatement ps=dbc.getConnection().prepareStatement("DELETE FROM ware WHERE id=?");
				ps.setInt(1,mykeys_generated_Ware.get(i));
				ps.executeUpdate();
			} catch (SQLException e) {
				throw new DAOException("ERROR: failed to delete ware from DB!");
			}
			catch (NullPointerException e) {
				throw new IllegalArgumentException();
			}
		}
	}

	@Test
	public void testCreate_1() throws DAOException {
		Ware w_test = test_Ware.create(w1);
		mykeys_generated_Ware.add(w_test.getId());
		e1.setWarenId(w_test.getId());
		Einkauf e_test = test_Einkauf.create(e1);
		mykeys_generated_Einkauf.add(e_test.getId());
		
		try {
			PreparedStatement ps = dbc.getConnection().prepareStatement("SELECT id, warenid, menge, datum, preis FROM einkauf WHERE id=?");
			ps.setInt(1,e_test.getId());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				assertThat(rs.getInt("id"), equalTo(e1.getId()));
				assertThat(rs.getInt("warenid"), equalTo(e1.getWarenId()));
				assertThat(rs.getInt("menge"), equalTo(e1.getMenge()));
				assertThat(rs.getDate("datum").toString(), equalTo(e1.getDatum().toString()));
				assertThat(rs.getInt("preis"), equalTo(e1.getPreis()));
				
				return;
			} else {
				fail("No data found");
				return;
			}
			
			
		} catch (SQLException e) {
			fail("ERROR: failed to search DB for Einkauf");
			return;
		} catch (NullPointerException e) {
			fail("NullPointerException");
			return;
		}
	}
	
	@Test
	public void testCreate_2() throws DAOException {
		Ware w_test = test_Ware.create(w2);
		mykeys_generated_Ware.add(w_test.getId());
		e1.setWarenId(w_test.getId());
		Einkauf e_test = test_Einkauf.create(e2);
		mykeys_generated_Einkauf.add(e_test.getId());
		
		try {
			PreparedStatement ps = dbc.getConnection().prepareStatement("SELECT id, warenid, menge, datum, preis FROM einkauf WHERE id=?");
			ps.setInt(1,e_test.getId());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				assertThat(rs.getInt("id"), equalTo(e2.getId()));
				assertThat(rs.getInt("warenid"), equalTo(e2.getWarenId()));
				assertThat(rs.getInt("menge"), equalTo(e2.getMenge()));
				assertThat(rs.getDate("datum").toString(), equalTo(e2.getDatum().toString()));
				assertThat(rs.getInt("preis"), equalTo(e2.getPreis()));
				
				return;
			} else {
				fail("No data found");
				return;
			}
			
			
		} catch (SQLException e) {
			fail("ERROR: failed to search DB for Einkauf");
			return;
		} catch (NullPointerException e) {
			fail("NullPointerException");
			return;
		}
	}
	
	@Test
	public void testSearch_1() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testSearch_2(){
		fail("Not yet implemented");
	}
	
	@Test
	public void testUpdate_1(){
		fail("Not yet implemented");
	}
	
	@Test
	public void testUpdate_2(){
		fail("Not yet implemented");
	}
	
	@Test
	public void testDelete_1(){
		fail("Not yet implemented"); 
	}
	
	@Test
	public void testDelete_2(){
		fail("Not yet implemented");
	}
}
