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
import sepm.ss13.gast.dao.JDBCWareDAO;
import sepm.ss13.gast.domain.Einkauf;
import sepm.ss13.gast.domain.Ware;

public class Test_JDBCEinkaufDAO {

	private static JDBCEinkaufDAO testDAO;
	private static DBConnector dbc;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring-config.xml");
		dbc = (DBConnector) ac.getBean("databaseManager");
		dbc.getConnection().setAutoCommit(false); 
		testDAO = new JDBCEinkaufDAO(dbc.getConnection());		
	}

	@After
	public void tearDown() throws Exception {
		dbc.getConnection().rollback();
	}
	
	@Test
	public void testCreate_1() {
		// Test mit ID=-1 und gültigen Werten

//		try {
//			Reservierung testRes= testDAO.create(new Reservierung(-1, new Date(0), 3, 5, 1, "Musterfrau", "0664 11 22 33 44"));
//			ArrayList<Reservierung> fromDB = testDAO.search(testRes);
//
//			for (Reservierung r : fromDB) {
//				assertTrue(r != null);
//				assertThat(r.getId(), equalTo(testRes.getId()));
//				assertThat(r.getPersonen(), equalTo(testRes.getPersonen()));
//				assertThat(r.getTischnummer(), equalTo(testRes.getTischnummer()));
//				assertThat(r.getDauer(), equalTo(testRes.getDauer()));
//				assertThat(r.getName(), equalTo(testRes.getName()));
//				assertThat(r.getTelefonnummer(), equalTo(testRes.getTelefonnummer()));
//			}
//		} catch (DAOException e) {
//			fail(e.getMessage());
//		}
	}
	
	@Test
	public void testCreate_2() {
		// Test mit ID=2 und gültigen Werten

//		try {
//			Tisch testTisch = testDAO.create(new Tisch(2, 13, 7, "SuperÜberStammtisch", "Raucher", true));
//			ArrayList<Tisch> fromDB = testDAO.search(testTisch);
//
//			for( Tisch t : fromDB) {
//				assertTrue(t != null);
//				assertThat(t.getId(), equalTo(testTisch.getId()));
//				assertThat(t.getNummer(), equalTo(testTisch.getNummer()));
//				assertThat(t.getPlaetze(), equalTo(testTisch.getPlaetze()));
//				assertThat(t.getBeschreibung(), equalTo(testTisch.getBeschreibung()));
//				assertThat(t.getArt(), equalTo(testTisch.getArt()));
//				assertThat(t.getDeleted(), equalTo(testTisch.getDeleted()));
//			}
//		} catch(DAOException e) {
//			fail(e.getMessage());
//		}
	}
	
	@Test
	public void testCreate_3() {
		// Test mit ungültigen Werten. Sollte nicht erstellt werden und Exception werfen.
//		try {
//			Tisch testTisch = testDAO.create(new Tisch(0, 0, 0, null, "Superraucher", false));
//			fail("Sollte Exception werfen!");
//		} catch (DAOException e) {
//			// Wenn hier, alles gut.
//		}
	}
	

	@Test
	public void testSearch_1() {
		// Einen bestimmten Tisch suchen

//		try {
//			Tisch testTisch = testDAO.create(new Tisch(-1, 33, 5, "Stammtisch", "Nichtraucher", false));
//			ArrayList<Tisch> fromDB = testDAO.search(testTisch);
//
//			for (Tisch t : fromDB) {
//				assertTrue(t != null);
//				assertThat(t.getId(), equalTo(testTisch.getId()));
//				assertThat(t.getNummer(), equalTo(testTisch.getNummer()));
//				assertThat(t.getPlaetze(), equalTo(testTisch.getPlaetze()));
//				assertThat(t.getBeschreibung(), equalTo(testTisch.getBeschreibung()));
//				assertThat(t.getArt(), equalTo(testTisch.getArt()));
//				assertThat(t.getDeleted(), equalTo(testTisch.getDeleted()));
//			}
//		} catch(DAOException e) {
//			fail(e.getMessage());
//		}
	}
	
	@Test
	public void testUpdate_1() {
		// Tisch erzeugen und updaten anhand Tischdaten

//		try {
//			Tisch testTisch = testDAO.create(new Tisch(-1, 33, 5, "Stammtisch", "Nichtraucher", false));
//			Tisch newTestTisch = new Tisch(testTisch.getId(), 666, 11, "Superplatzerl" , "Raucher", testTisch.getDeleted());
//			testDAO.update(newTestTisch);
//
//			ArrayList<Tisch> fromDB = testDAO.search(newTestTisch);
//
//			for (Tisch t : fromDB) {
//				assertThat(t.getId(), equalTo(testTisch.getId()));
//				assertThat(t.getId(), equalTo(newTestTisch.getId()));
//
//				assertFalse(t.getNummer().equals(testTisch.getNummer()));
//				assertFalse(t.getPlaetze().equals(testTisch.getPlaetze()));
//				assertFalse(t.getBeschreibung().equals(testTisch.getBeschreibung()));
//				assertFalse(t.getArt().equals(testTisch.getArt()));
//
//				assertThat(t.getNummer(), equalTo(newTestTisch.getNummer()));
//				assertThat(t.getPlaetze(), equalTo(newTestTisch.getPlaetze()));
//				assertThat(t.getBeschreibung(), equalTo(newTestTisch.getBeschreibung()));
//				assertThat(t.getArt(), equalTo(newTestTisch.getArt()));
//				assertThat(t.getDeleted(), equalTo(newTestTisch.getDeleted()));
//			}
//		} catch(DAOException e) {
//			fail(e.getMessage());
//		}
	}
	
	@Test
	public void testUpdate_2() {
		// Tisch erzeugen und updaten anhand Tischdaten

//		try {
//			Tisch testTisch = testDAO.create(new Tisch(2, 13, 7, "SuperÜberStammtisch", "Raucher", true));
//			Tisch newTestTisch = new Tisch(testTisch.getId(), 666, 11, "Superplatzerl" , "Raucher", testTisch.getDeleted());
//			testDAO.update(newTestTisch);
//
//			ArrayList<Tisch> fromDB = testDAO.search(newTestTisch);
//
//			for (Tisch t : fromDB) {
//				assertThat(t.getId(), equalTo(testTisch.getId()));
//				assertThat(t.getId(), equalTo(newTestTisch.getId()));
//
//				assertFalse(t.getNummer().equals(testTisch.getNummer()));
//				assertFalse(t.getPlaetze().equals(testTisch.getPlaetze()));
//				assertFalse(t.getBeschreibung().equals(testTisch.getBeschreibung()));
//
//				assertThat(t.getNummer(), equalTo(newTestTisch.getNummer()));
//				assertThat(t.getPlaetze(), equalTo(newTestTisch.getPlaetze()));
//				assertThat(t.getBeschreibung(), equalTo(newTestTisch.getBeschreibung()));
//				assertThat(t.getArt(), equalTo(newTestTisch.getArt()));
//				assertThat(t.getDeleted(), equalTo(newTestTisch.getDeleted()));
//			}
//		} catch(DAOException e) {
//			fail(e.getMessage());
//		}
	}
	
	@Test
	public void testDelete_1() {
		// Tisch erzeugen, suchen, löschen, suchen

//		try {
//			Tisch testTisch = testDAO.create(new Tisch(-1, 33, 5, "Stammtisch", "Nichtraucher", false));
//
//			ArrayList<Tisch> fromDB = testDAO.search(testTisch);
//			assertTrue(fromDB.size() > 0);
//
//			testDAO.delete(testTisch);
//
//			fromDB = testDAO.search(testTisch);
//			assertThat(fromDB.size(), equalTo(0));
//		} catch(DAOException e) {
//			fail(e.getMessage());
//		}
	}
	
	@Test
	public void testDelete_2() {
		// Tisch erzeugen, suchen, löschen, suchen

//		try {
//			Tisch testTisch = testDAO.create(new Tisch(2, 13, 7, "SuperÜberStammtisch", "Raucher", false));
//
//			ArrayList<Tisch> fromDB = testDAO.search(testTisch);
//			assertTrue(fromDB.size() > 0);
//
//			testDAO.delete(testTisch);
//
//			fromDB = testDAO.search(testTisch);
//			assertThat(fromDB.size(), equalTo(0));
//		} catch(DAOException e) {
//			fail(e.getMessage());
//		}
	}
	
	
	
	
	
	
//	
//	@Test
//	public void testCreate_1() throws DAOException {
//		Ware w_test = test_Ware.create(w1);
//		mykeys_generated_Ware.add(w_test.getId());
//		e1.setWarenId(w_test.getId());
//		Einkauf e_test = test_Einkauf.create(e1);
//		mykeys_generated_Einkauf.add(e_test.getId());
//		
//		try {
//			PreparedStatement ps = dbc.getConnection().prepareStatement("SELECT id, warenid, menge, datum, preis FROM einkauf WHERE id=?");
//			ps.setInt(1,e_test.getId());
//			
//			ResultSet rs = ps.executeQuery();
//			
//			if(rs.next()) {
//				assertThat(rs.getInt("id"), equalTo(e1.getId()));
//				assertThat(rs.getInt("warenid"), equalTo(e1.getWarenId()));
//				assertThat(rs.getInt("menge"), equalTo(e1.getMenge()));
//				assertThat(rs.getDate("datum").toString(), equalTo(e1.getDatum().toString()));
//				assertThat(rs.getInt("preis"), equalTo(e1.getPreis()));
//				
//				return;
//			} else {
//				fail("No data found");
//				return;
//			}
//			
//			
//		} catch (SQLException e) {
//			fail("ERROR: failed to search DB for Einkauf");
//			return;
//		} catch (NullPointerException e) {
//			fail("NullPointerException");
//			return;
//		}
//	}
//	
//	@Test
//	public void testCreate_2() throws DAOException {
//		Ware w_test = test_Ware.create(w2);
//		mykeys_generated_Ware.add(w_test.getId());
//		e2.setWarenId(w_test.getId());
//		Einkauf e_test = test_Einkauf.create(e2);
//		mykeys_generated_Einkauf.add(e_test.getId());
//		
//		try {
//			PreparedStatement ps = dbc.getConnection().prepareStatement("SELECT id, warenid, menge, datum, preis FROM einkauf WHERE id=?");
//			ps.setInt(1,e_test.getId());
//			
//			ResultSet rs = ps.executeQuery();
//			
//			if(rs.next()) {
//				assertThat(rs.getInt("id"), equalTo(e2.getId()));
//				assertThat(rs.getInt("warenid"), equalTo(e2.getWarenId()));
//				assertThat(rs.getInt("menge"), equalTo(e2.getMenge()));
//				assertThat(rs.getDate("datum").toString(), equalTo(e2.getDatum().toString()));
//				assertThat(rs.getInt("preis"), equalTo(e2.getPreis()));
//				return;
//			} else {
//				fail("No data found");
//				return;
//			}
//			
//		} catch (SQLException e) {
//			fail("ERROR: failed to search DB for Einkauf");
//			return;
//		} catch (NullPointerException e) {
//			fail("NullPointerException");
//			return;
//		}
//	}
//	
//	@Test
//	public void testSearch_1() throws DAOException {
//		ArrayList<Einkauf> e_test = test_Einkauf.search(e3);
//				
//		for (Einkauf e : e_test) {
//			assertThat(e.getId(), equalTo(e3.getId()));
//			assertThat(e.getWarenId(), equalTo(e3.getWarenId()));
//			assertThat(e.getMenge(), equalTo(e3.getMenge()));
//			assertThat(e.getDatum().toString() ,equalTo(e3.getDatum().toString()));
//			assertThat(e.getPreis(), equalTo(e3.getPreis()));
//		}
//	}
//	
//	@Test
//	public void testSearch_2() throws DAOException{
//		Einkauf toSearch = new Einkauf(e4.getId(), e4.getWarenId(), 0, null, 0);
//		ArrayList<Einkauf> e_test = test_Einkauf.search(toSearch);
//		
//		assertThat(e_test.size(), equalTo(0));
//		
//		toSearch = new Einkauf(-1, e4.getWarenId(), e4.getMenge(), e4.getDatum(), e4.getPreis());
//		e_test = test_Einkauf.search(toSearch);
//		
//		assertThat(e_test.size(), equalTo(0));
//		
//		toSearch = new Einkauf(e4.getId(), e4.getWarenId(), e4.getMenge(), e4.getDatum(), e4.getPreis());
//		e_test = test_Einkauf.search(toSearch);
//				
//		for (Einkauf e : e_test) {
//			assertThat(e.getId(), equalTo(e4.getId()));
//			assertThat(e.getWarenId(), equalTo(e4.getWarenId()));
//			assertThat(e.getMenge(), equalTo(e4.getMenge()));
//			assertThat(e.getDatum().toString() ,equalTo(e4.getDatum().toString()));
//			assertThat(e.getPreis(), equalTo(e4.getPreis()));
//		}
//	}
//	
//	@Test
//	public void testUpdate_1() throws DAOException{
//
//		w5 = test_Ware.create(w5);
//		mykeys_generated_Ware.add(w5.getId());
//		e5 = test_Einkauf.create(e5);
//		e5.setWarenId(w5.getId());
//		mykeys_generated_Einkauf.add(e5.getId());
//		
//		Einkauf toUpdate = new Einkauf(e5.getId(), -1, -1, new Date(0), -1);
//		
//		test_Einkauf.update(toUpdate);
//		ArrayList<Einkauf> shouldBeUpdated = test_Einkauf.search(toUpdate);
//		
//		for (Einkauf e : shouldBeUpdated) {
//			assertThat(e.getId(), equalTo(toUpdate.getId()));
//			assertThat(e.getWarenId(), equalTo(toUpdate.getWarenId()));
//			assertThat(e.getMenge(), equalTo(toUpdate.getMenge()));
//			assertThat(e.getDatum().toString() ,equalTo(toUpdate.getDatum().toString()));
//			assertThat(e.getPreis(), equalTo(toUpdate.getPreis()));
//		}
//	}
//	
//	@Test
//	public void testUpdate_2() throws DAOException{
//
//		w6 = test_Ware.create(w6);
//		mykeys_generated_Ware.add(w6.getId());
//		e6.setWarenId(w6.getId());
//		e6 = test_Einkauf.create(e6);
//		mykeys_generated_Einkauf.add(e6.getId());
//		
//		e6.setPreis(666);
//		e6.setMenge(7000);
//		
//		test_Einkauf.update(e6);
//		ArrayList<Einkauf> shouldBeUpdated = test_Einkauf.search(e6);
//		
//		for (Einkauf e : shouldBeUpdated) {
//			assertThat(e.getId(), equalTo(e6.getId()));
//			assertThat(e.getWarenId(), equalTo(e6.getWarenId()));
//			assertThat(e.getMenge(), equalTo(e6.getMenge()));
//			assertThat(e.getDatum().toString() ,equalTo(e6.getDatum().toString()));
//			assertThat(e.getPreis(), equalTo(e6.getPreis()));
//		}
//	}
//	
//	@Test
//	public void testDelete_1() throws DAOException{
//		
//		w7 = test_Ware.create(w7);
//		mykeys_generated_Ware.add(w7.getId());
//		e7.setWarenId(w7.getId());
//		e7 = test_Einkauf.create(e7);
//		mykeys_generated_Einkauf.add(e7.getId());
//		
//		ArrayList<Einkauf> e_test = test_Einkauf.search(e7);
//		assertTrue(e_test.size() != 0);
//		test_Einkauf.delete(e7);
//		e_test = test_Einkauf.search(e7);
//		assertThat(e_test.size(), equalTo(0));
//	}
//	
//	@Test
//	public void testDelete_2() throws DAOException{
//		
//		w8 = test_Ware.create(w8);
//		mykeys_generated_Ware.add(w8.getId());
//		e8.setWarenId(w8.getId());
//		e8 = test_Einkauf.create(e8);
//		mykeys_generated_Einkauf.add(e8.getId());
//		
//		ArrayList<Einkauf> e_test = test_Einkauf.search(e8);
//		assertTrue(e_test.size() != 0);
//		test_Einkauf.delete(new Einkauf(e8.getId(), 0, 0, null, 0));
//		e_test = test_Einkauf.search(e8);
//		assertThat(e_test.size(), equalTo(0));
//	}
}
