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

	private ApplicationContext ac;
	private DBConnector dbc;
	private JDBCWareDAO test;
	
	private Ware w1 = null; //create
	private Ware w2 = null; //create
	
	private Ware w3 = null; //search
	private Ware w4 = null; //search
	
	private Ware w5 = null; //update
	private Ware w6 = null; //update
	
	private Ware w7 = null; //delete
	private Ware w8 = null; //delete
	
	private ArrayList<Integer> mykeys = new ArrayList<Integer>();
	private ArrayList<Integer> mykeys_generated = new ArrayList<Integer>();
	
	@BeforeClass
	public void setUpBeforeClass() throws Exception {
		ac = new ClassPathXmlApplicationContext("spring-config.xml");
		dbc = (DBConnector) ac.getBean("databaseManager");
		dbc.getConnection().setAutoCommit(false);
		test = new JDBCWareDAO(dbc.getConnection());
		
		w1 = new Ware(-1, "Sauerkraut", "gramm", 30);
		w2 = new Ware(-1, "Bier", "milliliter", 15);
		w3 = new Ware(-1, "Sauerkraut", "gramm", 30);
		w4 = new Ware(-1, "Bier", "milliliter", 15);
		w5 = new Ware(-1, "Sauerkraut", "gramm", 30);
		w6 = new Ware(-1, "Bier", "milliliter", 15);
		w7 = new Ware(-1, "Sauerkraut", "gramm", 30);
		w8 = new Ware(-1, "Bier", "milliliter", 15);
		
		Ware w_temp = null;
		w_temp = test.create(w3);
		w3.setId(w_temp.getId());
		mykeys.add(w3.getId());
		w_temp = test.create(w4);
		w4.setId(w_temp.getId());
		mykeys.add(w4.getId());
	}

	@After
	public void tearDown() throws Exception {
		dbc.getConnection().rollback();
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
		Ware toSearch = new Ware(w3.getId(), null, null, 0);
		ArrayList<Ware> t_test = test.search(toSearch);
		assertThat(t_test.size(), equalTo(0));
		
		t_test = test.search(w3);
		
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
		
		Ware toUpdate = new Ware(w5.getId(), "Fanta", "milliliter", 60);
		
		test.update(toUpdate);
		ArrayList<Ware> shouldBeUpdated = test.search(toUpdate);
		
		for (Ware w : shouldBeUpdated) {
			assertThat(w.getId(), equalTo(w5.getId()));
			assertThat(w.getId(), equalTo(toUpdate.getId()));
			assertThat(w.getBezeichnung(), equalTo(toUpdate.getBezeichnung()));
			assertThat(w.getEinheit(), equalTo(toUpdate.getEinheit()));
			assertThat(w.getLagerstand(), equalTo(toUpdate.getLagerstand()));
		}
	}
	
	@Test
	public void testUpdate_2() throws DAOException {
		
		w6 = test.create(w6);
		mykeys_generated.add(w6.getId());
		
		Ware toUpdate = new Ware(w6.getId(), "Weißbier", "milliliter", 80);
		
		test.update(toUpdate);
		ArrayList<Ware> shouldBeUpdated = test.search(toUpdate);
		
		for (Ware w : shouldBeUpdated) {
			assertThat(w.getId(), equalTo(w6.getId()));
			
			assertFalse(w.getBezeichnung().equals(w6.getBezeichnung()));
			assertFalse(w.getLagerstand() == (w6.getLagerstand()));
			
			assertTrue(w.getBezeichnung().equals(toUpdate.getBezeichnung()));
			assertTrue(w.getEinheit().equals(toUpdate.getEinheit()));
			assertTrue(w.getLagerstand() == (toUpdate.getLagerstand()));
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
		test.delete(new Ware(w8.getId(), "Cola", "milliliter", 30));
		t_test = test.search(w8);
		assertTrue(t_test.size() == 0);
	}

}
