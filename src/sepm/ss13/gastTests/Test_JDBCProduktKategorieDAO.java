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
import sepm.ss13.gast.dao.JDBCProduktKategorieDAO;
import sepm.ss13.gast.domain.ProduktKategorie;

public class Test_JDBCProduktKategorieDAO {
	
	private static JDBCProduktKategorieDAO testDAO;
	private static DBConnector dbc;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring-config.xml");
		dbc = (DBConnector) ac.getBean("databaseManager");
		dbc.getConnection().setAutoCommit(false); 
		testDAO = new JDBCProduktKategorieDAO(dbc.getConnection());		
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
	
	

//	/*
//	 *  TESTs for CREATE
//	 */
//	
//	/**
//	 * Test method for {sepm.ss13.gast.dao.JDBCProduktKategorieDAO}.
//	 * @throws DAOException 
//	 */
//	@Test
//	public void testCreate_1() throws DAOException,IllegalArgumentException {
//		
//		/* INSERT */
//		ProduktKategorie p_test = null;
//		p_test = test.create(p1);
//		
//		mykeys_generated.add(p_test.getId());
//		
//		/* GET INSERTED */
//		try {
//			PreparedStatement ps = dbc.getConnection().prepareStatement("SELECT id,bezeichnung,kurzbezeichnung FROM produkttyp WHERE id=?");
//			ps.setInt(1,p_test.getId());
//			
//			ResultSet rs=ps.executeQuery();
//			if(rs.next()) {
//				assertThat(rs.getInt("id"), equalTo(p_test.getId()));
//				assertThat(rs.getString("bezeichnung"), equalTo(p1.getBezeichnung()));
//				assertThat(rs.getString("kurzbezeichnung"), equalTo(p1.getKurzbezeichnung()));
//				return;
//			}else{
//				fail("No Data found");
//				return;
//			}
//		} catch (SQLException e) {
//			fail("ERROR: failed to search DB for product categories!");
//			return;
//		}
//		catch (NullPointerException e) {
//			fail("NullPointerException");
//			return;
//		}
//	}
//	
//	/**
//	 * Test method for {sepm.ss13.gast.dao.JDBCProduktKategorieDAO}.
//	 * @throws DAOException 
//	 */
//	@Test (expected=IllegalArgumentException.class) 
//	public void testCreate_2() throws DAOException,IllegalArgumentException {
//		
//		/* INSERT */
//		test.create(null);
//		
//	}
//	
//	/**
//	 * Test method for {sepm.ss13.gast.dao.JDBCProduktKategorieDAO}.
//	 * @throws DAOException 
//	 */
//	@Test (expected=DAOException.class) 
//	public void testCreate_3() throws DAOException,IllegalArgumentException {
//		
//		/* INSERT */
//		test.create(p2);
//		
//	}	
//	
//	
//	
//	/*
//	 *  TESTs for SEARCH
//	 */
//	
//	
//
//	/**
//	 * Test method for {sepm.ss13.gast.dao.JDBCProduktKategorieDAO}.
//	 * @throws DAOException 
//	 */
//	@Test
//	public void testSearch_1() throws DAOException,IllegalArgumentException {
//		
//		/* INSERT */
//		ArrayList<ProduktKategorie> p_test = new ArrayList<ProduktKategorie>();
//		p_test = test.search(p3);
//	
//		for(int i = 0; i < p_test.size();i++){
//			assertThat(p_test.get(i).getId(), equalTo(p3.getId()));
//			assertThat(p_test.get(i).getBezeichnung(), equalTo(p3.getBezeichnung()));
//			assertThat(p_test.get(i).getKurzbezeichnung(), equalTo(p3.getKurzbezeichnung()));
//		}
//		
//		p_test = new ArrayList<ProduktKategorie>();
//		p_test = test.search(p4);
//	
//		for(int i = 0; i < p_test.size();i++){
//			assertThat(p_test.get(i).getId(), equalTo(p4.getId()));
//			assertThat(p_test.get(i).getBezeichnung(), equalTo(p4.getBezeichnung()));
//			assertThat(p_test.get(i).getKurzbezeichnung(), equalTo(p4.getKurzbezeichnung()));
//		}
//	}
//	
//	/**
//	 * Test method for {sepm.ss13.gast.dao.JDBCProduktKategorieDAO}.
//	 * @throws DAOException 
//	 */
//	@Test (expected=IllegalArgumentException.class) 
//	public void testSearch_2() throws DAOException,IllegalArgumentException {
//		
//		/* INSERT */
//		test.search(null);
//		
//	}
//	
//	
//	/*
//	 * TESTs for update
//	 */
//	
//	/**
//	 * Test method for {sepm.ss13.gast.dao.JDBCProduktKategorieDAO}.
//	 * @throws DAOException 
//	 */
//	@Test
//	public void testUpdate_1() throws DAOException,IllegalArgumentException {
//		
//		/* INSERT */
//		ProduktKategorie p_test = null;
//		p_test = test.create(p1);
//		
//		mykeys_generated.add(p_test.getId());
//		p5.setId(p_test.getId());
//		
//		/* UPDATE */
//		test.update(p5);
//		
//		/* GET UPDATED */
//		try {
//			PreparedStatement ps = dbc.getConnection().prepareStatement("SELECT id,bezeichnung,kurzbezeichnung FROM produkttyp WHERE id=?");
//			ps.setInt(1,p5.getId());
//			
//			ResultSet rs=ps.executeQuery();
//			if(rs.next()) {
//				assertThat(rs.getInt("id"), equalTo(p5.getId()));
//				assertThat(rs.getString("bezeichnung"), equalTo(p5.getBezeichnung()));
//				assertThat(rs.getString("kurzbezeichnung"), equalTo(p5.getKurzbezeichnung()));
//				return;
//			}else{
//				fail("No Data found");
//				return;
//			}
//		} catch (SQLException e) {
//			fail("ERROR: failed to search DB for product categories!");
//			return;
//		}
//		catch (NullPointerException e) {
//			fail("NullPointerException");
//			return;
//		}
//	}
//	
//	/**
//	 * Test method for {sepm.ss13.gast.dao.JDBCProduktKategorieDAO}.
//	 * @throws DAOException 
//	 */
//	@Test (expected=IllegalArgumentException.class) 
//	public void testUpdate_2() throws DAOException,IllegalArgumentException {
//		
//		/* INSERT */
//		test.update(null);
//		
//	}
//	
//	/**
//	 * Test method for {sepm.ss13.gast.dao.JDBCProduktKategorieDAO}.
//	 * @throws DAOException 
//	 */
//	@Test (expected=DAOException.class) 
//	public void testUpdate_3() throws DAOException,IllegalArgumentException {
//		
//		/* INSERT */
//		ProduktKategorie p_test = null;
//		p_test = test.create(p1);
//		
//		mykeys_generated.add(p_test.getId());
//		p6.setId(p_test.getId());
//		
//		/* UPDATE */
//		test.update(p6);
//		
//	}	
//	
//	/**
//	 * Test method for {sepm.ss13.gast.dao.JDBCProduktKategorieDAO}.
//	 * @throws DAOException 
//	 */
//	@Test (expected=DAOException.class) 
//	public void testUpdate_4() throws DAOException,IllegalArgumentException {
//		
//		/* UPDATE */
//		test.update(p6);
//		
//	}	
//	
//	
//	
//
//	/*
//	 *  TESTs for DELTE
//	 */
//	
//	/**
//	 * Test method for {sepm.ss13.gast.dao.JDBCProduktKategorieDAO}.
//	 * @throws DAOException 
//	 */
//	@Test
//	public void testDelete_1() throws DAOException,IllegalArgumentException {
//		
//		/* INSERT */
//		ProduktKategorie p_test = null;
//		p_test = test.create(p7);
//		
//		mykeys_generated.add(p_test.getId());
//		p7.setId(p_test.getId());
//		
//		test.delete(p7);
//	}
//	
//	/**
//	 * Test method for {sepm.ss13.gast.dao.JDBCProduktKategorieDAO}.
//	 * @throws DAOException 
//	 */
//	@Test (expected=IllegalArgumentException.class) 
//	public void testDelete_2() throws DAOException,IllegalArgumentException {
//		
//		/* INSERT */
//		test.delete(null);
//		
//	}
//	
//	/**
//	 * Test method for {sepm.ss13.gast.dao.JDBCProduktKategorieDAO}.
//	 * @throws DAOException 
//	 */
//	@Test
//	public void testDelete_3() throws DAOException,IllegalArgumentException {
//		
//		
//		/* INSERT */
//		ProduktKategorie p_test = null;
//		p_test = test.create(p8);
//		
//		mykeys_generated.add(p_test.getId());
//		p8.setId(p_test.getId());
//		
//		try {
//			PreparedStatement ps=dbc.getConnection().prepareStatement("DELETE FROM produkttyp WHERE id=?");
//			ps.setInt(1,p8.getId());
//			ps.executeUpdate();
//		} catch (SQLException e) {
//			fail("ERROR: failed to delete product category from DB!");
//		}
//		catch (NullPointerException e) {
//			throw new IllegalArgumentException();
//		}
//		
//		test.delete(p8);
//		
//	}	
	 
	

}
