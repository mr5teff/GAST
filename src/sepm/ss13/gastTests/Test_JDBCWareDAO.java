package sepm.ss13.gastTests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

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
import sepm.ss13.gast.dao.JDBCTischDAO;
import sepm.ss13.gast.dao.JDBCWareDAO;
import sepm.ss13.gast.domain.Tisch;
import sepm.ss13.gast.domain.Ware;

public class Test_JDBCWareDAO {

	private static ApplicationContext ac;
	static DBConnector dbc;
	static JDBCWareDAO test;
	
	static Ware w1 = null; //create
	static Ware w2 = null; //create
	
	static Ware w3 = null; //search
	static Ware w4 = null; //search
	
	static Ware w5 = null; //update
	static Ware w6 = null; //update
	
	static Ware w7 = null; //delete
	static Ware w8 = null; //delete
	
	static ArrayList<Integer> mykeys = new ArrayList<Integer>();
	static ArrayList<Integer> mykeys_generated = new ArrayList<Integer>();
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ac = new ClassPathXmlApplicationContext("spring-config.xml");
		dbc = (DBConnector) ac.getBean("databaseManager");
		test = new JDBCWareDAO(dbc.getConnection());
		
		w1 = new Ware(-1, "Sauerkraut", "Kübel", 30);
		w2 = new Ware(-1, "Mais", "Dosen", 15);
		w3 = new Ware(-1, "Sauerkraut", "Kübel", 30);
		w4 = new Ware(-1, "Mais", "Dosen", 15);
		w5 = new Ware(-1, "Sauerkraut", "Kübel", 30);
		w6 = new Ware(-1, "Mais", "Dosen", 15);
		w7 = new Ware(-1, "Sauerkraut", "Kübel", 30);
		w8 = new Ware(-1, "Mais", "Dosen", 15);
		
		Ware w_temp = null;
		w_temp = test.create(w3);
		w3.setId(w_temp.getId());
		mykeys.add(w3.getId());
		w_temp = test.create(w4);
		w4.setId(w_temp.getId());
		mykeys.add(w4.getId());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		for(int i = 0; i < mykeys.size();i++){
			try {
				PreparedStatement ps=dbc.getConnection().prepareStatement("DELETE FROM tisch WHERE id=?");
				ps.setInt(1,mykeys.get(i));
				ps.executeUpdate();
			} catch (SQLException e) {
				throw new DAOException("ERROR: failed to delete tisch from DB!");
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
		for(int i = 0; i < mykeys_generated.size();i++){
			try {
				PreparedStatement ps=dbc.getConnection().prepareStatement("DELETE FROM tisch WHERE id=?");
				ps.setInt(1,mykeys_generated.get(i));
				ps.executeUpdate();
			} catch (SQLException e) {
				throw new DAOException("ERROR: failed to delete tisch category from DB!");
			}
			catch (NullPointerException e) {
				throw new IllegalArgumentException();
			}
		}
	}

	@Test
	public void testCreate_1() throws DAOException {
		Ware w_test = test.create(w1);
		mykeys_generated.add(w_test.getId());
		
		try {
			PreparedStatement ps = dbc.getConnection().prepareStatement("SELECT id, bezeichnung, lagerstand, einheit FROM ware WHERE id=?");
			ps.setInt(1,w_test.getId());
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				assertTrue(rs.getInt("id") == w_test.getId());
				assertTrue(rs.getString("bezeichnung").equals(w_test.getBezeichnung()));
				assertTrue(rs.getInt("lagerstand") == w_test.getLagerstand());
				assertTrue(rs.getString("einheit").equals(w_test.getEinheit()));
			} else {
				fail("No data found");
			}
			
		} catch (SQLException e) {
			fail("ERROR: failed to search DB for Ware");
			return;
		} catch (NullPointerException e) {
			fail("NullPointerException");
			return;
		}
	}
	
	@Test
	public void testCreate_2() throws DAOException {
		Ware w_test = test.create(w2);
		mykeys_generated.add(w_test.getId());
		
		try {
			PreparedStatement ps = dbc.getConnection().prepareStatement("SELECT id, bezeichnung, lagerstand, einheit FROM ware WHERE id=?");
			ps.setInt(1,w_test.getId());
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				assertTrue(rs.getInt("id") == w_test.getId());
				assertTrue(rs.getString("bezeichnung").equals(w_test.getBezeichnung()));
				assertTrue(rs.getInt("lagerstand") == w_test.getLagerstand());
				assertTrue(rs.getString("einheit").equals(w_test.getEinheit()));
			} else {
				fail("No data found");
			}
			
		} catch (SQLException e) {
			fail("ERROR: failed to search DB for Ware");
			return;
		} catch (NullPointerException e) {
			fail("NullPointerException");
			return;
		}
	}
	
	@Test
	public void testSearch_1() throws DAOException {
		Ware toSearch = new Ware(w3.getId(), w3.getBezeichnung(), w3.getEinheit(), w3.getLagerstand());
		ArrayList<Ware> t_test = test.search(toSearch);
		
		for (Ware w : t_test) {
			assertThat(w.getId(), equalTo(w3.getId()));
			assertThat(w.getBezeichnung(), equalTo(w3.getBezeichnung()));
			assertThat(w.getEinheit(), equalTo(w3.getEinheit()));
			assertThat(w.getLagerstand(), equalTo(w3.getLagerstand()));
		}
	}
	
	@Test
	public void testSearch_2() throws DAOException {
		Ware toSearch = new Ware(-1, w4.getBezeichnung(), w4.getEinheit(), w4.getLagerstand());
		ArrayList<Ware> t_test = test.search(toSearch);
		
		assertTrue(t_test.size() == 0);
		
		toSearch = new Ware(w4.getId(), w4.getBezeichnung(), w4.getEinheit(), w4.getLagerstand());
		t_test = test.search(toSearch);
		
		for (Ware w : t_test) {
			assertThat(w.getId(), equalTo(w4.getId()));
			assertThat(w.getBezeichnung(), equalTo(w4.getBezeichnung()));
			assertThat(w.getEinheit(), equalTo(w4.getEinheit()));
			assertThat(w.getLagerstand(), equalTo(w4.getLagerstand()));
		}
	}
	
	@Test
	public void testUpdate_1() throws DAOException {
		
		w5 = test.create(w5);
		mykeys_generated.add(w5.getId());
		
		Ware toUpdate = new Ware(w5.getId(), "SuperSauerkraut", "Faß, groß", 60);
		
		test.update(toUpdate);
		ArrayList<Ware> shouldBeUpdated = test.search(w5);
		
		for (Ware w : shouldBeUpdated) {
			assertTrue(w.getId() == w5.getId());
			
			assertFalse(w.getBezeichnung().equals(w5.getBezeichnung()));
			assertFalse(w.getEinheit().equals(w5.getEinheit()));
			assertFalse(w.getLagerstand() == (w5.getLagerstand()));
			
			assertTrue(w.getBezeichnung().equals(w5.getBezeichnung()));
			assertTrue(w.getEinheit().equals(w5.getEinheit()));
			assertTrue(w.getLagerstand() == (w5.getLagerstand()));
		}
	}
	
	@Test
	public void testUpdate_2() throws DAOException {
		
		w6 = test.create(w6);
		mykeys_generated.add(w6.getId());
		
		Ware toUpdate = new Ware(w6.getId(), "MexicoMais", "Dose, klein", 80);
		
		test.update(toUpdate);
		ArrayList<Ware> shouldBeUpdated = test.search(w6);
		
		for (Ware w : shouldBeUpdated) {
			assertTrue(w.getId() == w6.getId());
			
			assertFalse(w.getBezeichnung().equals(w6.getBezeichnung()));
			assertFalse(w.getEinheit().equals(w6.getEinheit()));
			assertFalse(w.getLagerstand() == (w6.getLagerstand()));
			
			assertTrue(w.getBezeichnung().equals(w6.getBezeichnung()));
			assertTrue(w.getEinheit().equals(w6.getEinheit()));
			assertTrue(w.getLagerstand() == (w6.getLagerstand()));
		}
	}
	
	@Test
	public void testDelete_1() throws DAOException {
		
		w7 = test.create(w7);
		mykeys_generated.add(w7.getId());
		
		ArrayList<Ware> t_test = test.search(w7);
		assertTrue(t_test.size() != 0);
		test.delete(w7);
		t_test = test.search(w7);
		assertTrue(t_test.size() == 0);
	}
	
	@Test
	public void testDelete_2() throws DAOException {
		
		w8 = test.create(w8);
		mykeys_generated.add(w8.getId());
		
		ArrayList<Ware> t_test = test.search(w8);
		assertTrue(t_test.size() != 0);
		test.delete(new Ware(w8.getId(), "blabla", "SuperMais", 30));
		t_test = test.search(w8);
		assertTrue(t_test.size() == 0);
	}

}
