/**
 * 
 */
package sepm.ss13.gastTests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.dao.DBConnector;
import sepm.ss13.gast.dao.JDBCBestellungDAO;
import sepm.ss13.gast.dao.JDBCProduktDAO;
import sepm.ss13.gast.dao.JDBCProduktKategorieDAO;
import sepm.ss13.gast.dao.JDBCRechnungDAO;
import sepm.ss13.gast.dao.JDBCReservierungDAO;
import sepm.ss13.gast.domain.Bestellung;
import sepm.ss13.gast.domain.Produkt;
import sepm.ss13.gast.domain.ProduktKategorie;
import sepm.ss13.gast.domain.Rechnung;
import sepm.ss13.gast.domain.Reservierung;


public class Test_JDBCBestellungDAO {
	
	private static JDBCBestellungDAO testDAO;
	private static DBConnector dbc;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring-config.xml");
		dbc = (DBConnector) ac.getBean("databaseManager");
		dbc.getConnection().setAutoCommit(false); 
		testDAO = new JDBCBestellungDAO(dbc.getConnection());		
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
	
	
//	/**
//	 * Test method for {@link sepm.ss13.gast.dao.JDBCBestellungDAO#create(sepm.ss13.gast.domain.Bestellung)}.
//	 * @throws DAOException 
//	 */
//	@Test
//	public void testCreate_1() throws DAOException {
//		
//		/* INSERT */
//		Bestellung p_test = null;
//		p_test = test.create(p1);
//		
//		mykeys_generated.add(p_test.getId());
//		
//		/* GET INSERTED */
//		try {
//			PreparedStatement ps = dbc.getConnection().prepareStatement("SELECT id,tischnummer,produktid,preis,rechnungid,status,deleted FROM bestellung WHERE id = ?");
//			ps.setInt(1,p_test.getId());
//			
//			ResultSet rs=ps.executeQuery();
//			if(rs.next()) {
//				assertThat(rs.getInt("id"), equalTo(p_test.getId()));
//				assertThat(rs.getInt("tischnummer"), equalTo(p1.getTisch()));
//				assertThat(rs.getInt("produktid"), equalTo(p1.getProdukt()));
//				assertThat(rs.getInt("preis"), equalTo(p1.getPreis()));
//				assertThat(rs.getInt("rechnungid"), equalTo(0));
//				assertThat(rs.getString("status"), equalTo(p1.getStatus()));
//				assertThat(rs.getBoolean("deleted"), equalTo(p1.getDeleted()));
//				return;
//			}else{
//				fail("No Data found");
//				return;
//			}
//		} catch (SQLException e) {
//			fail("ERROR: failed to search DB for bestellung!");
//			return;
//		}
//		catch (NullPointerException e) {
//			fail("NullPointerException");
//			return;
//		}
//	}
//
//	
//
//	/**
//	 * Test method for {@link sepm.ss13.gast.dao.JDBCBestellungDAO#create(sepm.ss13.gast.domain.Bestellung)}.
//	 * @throws DAOException 
//	 */
//	@Test
//	public void testCreate_2() throws DAOException {
//		
//		/* INSERT */
//		Bestellung p_test = null;
//		p_test = test.create(p2);
//		
//		mykeys_generated.add(p_test.getId());
//		
//		/* GET INSERTED */
//		try {
//			PreparedStatement ps = dbc.getConnection().prepareStatement("SELECT id,tischnummer,produktid,preis,rechnungid,status,deleted FROM bestellung WHERE id = ?");
//			ps.setInt(1,p_test.getId());
//			
//			ResultSet rs=ps.executeQuery();
//			if(rs.next()) {
//				assertThat(rs.getInt("id"), equalTo(p_test.getId()));
//				assertThat(rs.getInt("tischnummer"), equalTo(p2.getTisch()));
//				assertThat(rs.getInt("produktid"), equalTo(p2.getProdukt()));
//				assertThat(rs.getInt("preis"), equalTo(p2.getPreis()));
//				assertThat(rs.getInt("rechnungid"), equalTo(p2.getRechnung()));
//				assertThat(rs.getString("status"), equalTo(p2.getStatus()));
//				assertThat(rs.getBoolean("deleted"), equalTo(p2.getDeleted()));
//				return;
//			}else{
//				fail("No Data found");
//				return;
//			}
//		} catch (SQLException e) {
//			fail("ERROR: failed to search DB for bestellung!");
//			return;
//		}
//		catch (NullPointerException e) {
//			fail("NullPointerException");
//			return;
//		}
//	}
//	
//
//	
//
//	/**
//	 * Test method for {@link sepm.ss13.gast.dao.JDBCBestellungDAO#create(sepm.ss13.gast.domain.Bestellung)}.
//	 * @throws DAOException 
//	 */
//	@Test (expected=DAOException.class) 
//	public void testCreate_3() throws DAOException {
//		
//		/* INSERT */
//		Bestellung p_test = null;
//		p_test = test.create(p3);
//		
//		mykeys_generated.add(p_test.getId());
//		
//	}
//	
//
//	/**
//	 * Test method for {@link sepm.ss13.gast.dao.JDBCBestellungDAO#create(sepm.ss13.gast.domain.Bestellung)}.
//	 * @throws DAOException 
//	 */
//	@Test (expected=IllegalArgumentException.class) 
//	public void testCreate_4() throws DAOException {
//		
//		/* INSERT */
//		test.create(null);
//		
//	}
//	
//	/**
//	 * Test method for {@link sepm.ss13.gast.dao.JDBCBestellungDAO#search(sepm.ss13.gast.domain.Bestellung)}.
//	 */
//	@Test
//	public void testSearch_1() throws DAOException,IllegalArgumentException {
//		
//		//Bestellung(int id, int tisch, int produkt, int preis, int rechnung, String status)
//		Bestellung toSearch = new Bestellung(-1,-200,-1,"",-1,null,"-1", false,new Date(), null,0);
//		ArrayList<Bestellung> p_test = new ArrayList<Bestellung>();
//		p_test = test.search(toSearch);
//	
//		for(int i = 0; i < p_test.size();i++){
//			assertThat(p_test.get(i).getId(), equalTo(p4.getId()));
//			assertThat(p_test.get(i).getTisch(), equalTo(p4.getTisch()));
//			assertThat(p_test.get(i).getProdukt(), equalTo(p4.getProdukt()));
//			assertThat(p_test.get(i).getPreis(), equalTo(p4.getPreis()));
//			assertThat(p_test.get(i).getRechnung(), equalTo(null));
//			assertThat(p_test.get(i).getStatus(), equalTo(p4.getStatus()));
//			assertThat(p_test.get(i).getDeleted(), equalTo(p4.getDeleted()));
//		}
//
//		toSearch = new Bestellung(-1,-300,-1,"",-1,null,"wirdGekocht", false,new Date(), null,0);
//		p_test = new ArrayList<Bestellung>();
//		p_test = test.search(toSearch);
//	
//		for(int i = 0; i < p_test.size();i++){
//			assertThat(p_test.get(i).getId(), equalTo(p5.getId()));
//			assertThat(p_test.get(i).getTisch(), equalTo(p5.getTisch()));
//			assertThat(p_test.get(i).getProdukt(), equalTo(p5.getProdukt()));
//			assertThat(p_test.get(i).getPreis(), equalTo(p5.getPreis()));
//			assertThat(p_test.get(i).getRechnung(), equalTo(null));
//			assertThat(p_test.get(i).getStatus(), equalTo(p5.getStatus()));
//			assertThat(p_test.get(i).getDeleted(), equalTo(p5.getDeleted()));
//		}
//		
//		toSearch = new Bestellung(p6.getId(),-1,-1,"",-1,null,"-1",false, new Date(), null,0);
//		p_test = new ArrayList<Bestellung>();
//		p_test = test.search(toSearch);
//	
//		for(int i = 0; i < p_test.size();i++){
//			assertThat(p_test.get(i).getId(), equalTo(p6.getId()));
//			assertThat(p_test.get(i).getTisch(), equalTo(p6.getTisch()));
//			assertThat(p_test.get(i).getProdukt(), equalTo(p6.getProdukt()));
//			assertThat(p_test.get(i).getPreis(), equalTo(p6.getPreis()));
//			assertThat(p_test.get(i).getRechnung(), equalTo(null));
//			assertThat(p_test.get(i).getStatus(), equalTo(p6.getStatus()));
//			assertThat(p_test.get(i).getDeleted(), equalTo(p6.getDeleted()));
//		}
//		
//		toSearch = new Bestellung(p7.getId(),-500,-1,"",-1,null,"geliefert",false, new Date(), null,0);
//		p_test = new ArrayList<Bestellung>();
//		p_test = test.search(toSearch);
//	
//		for(int i = 0; i < p_test.size();i++){
//			assertThat(p_test.get(i).getId(), equalTo(p7.getId()));
//			assertThat(p_test.get(i).getTisch(), equalTo(p7.getTisch()));
//			assertThat(p_test.get(i).getProdukt(), equalTo(p7.getProdukt()));
//			assertThat(p_test.get(i).getPreis(), equalTo(p7.getPreis()));
//			assertThat(p_test.get(i).getRechnung(), equalTo(null));
//			assertThat(p_test.get(i).getStatus(), equalTo(p7.getStatus()));
//			assertThat(p_test.get(i).getDeleted(), equalTo(p7.getDeleted()));
//		}
//	}
//	
//	
//	/**
//	 * Test method for {@link sepm.ss13.gast.dao.JDBCBestellungDAO#search(sepm.ss13.gast.domain.Bestellung)}.
//	 */
//	@Test (expected=IllegalArgumentException.class) 
//	public void testSearch_2() throws DAOException,IllegalArgumentException {
//		
//		test.search(null);
//	
//	}
//	
//	/**
//	 * Test method for {@link sepm.ss13.gast.dao.JDBCBestellungDAO#search(sepm.ss13.gast.domain.Bestellung)}.
//	 */
//	@Test
//	public void testSearch_3() throws DAOException,IllegalArgumentException {
//		
//		Bestellung toSearch = new Bestellung(-1,-500,-1,"",-1,null,null, false,new Date(), null,0);
//		ArrayList<Bestellung> p_test = new ArrayList<Bestellung>();
//		p_test = test.search(toSearch);
//
//		if(p_test.size() != 0){
//			fail("Wrong Bestellung count -- " + p_test.size());
//		}
//	
//	}
//	
//
//	/**
//	 * Test method for {@link sepm.ss13.gast.dao.JDBCBestellungDAO#delete(sepm.ss13.gast.domain.Bestellung)}.
//	 */
//	@Test
//	public void testDelete_1() throws DAOException,IllegalArgumentException {
//		
//		/* INSERT */
//		Bestellung p_test = null;
//		p_test = test.create(p8);
//		
//		mykeys_generated.add(p_test.getId());
//		p8.setId(p_test.getId());
//		
//		test.delete(p8);
//	}
//	
//	/**
//	 * Test method for {@link sepm.ss13.gast.dao.JDBCBestellungDAO#delete(sepm.ss13.gast.domain.Bestellung)}.
//	 */
//	@Test
//	public void testDelete_2() throws DAOException,IllegalArgumentException {
//		
//		/* INSERT */
//		Bestellung p_test = null;
//		p_test = test.create(p9);
//		
//		mykeys_generated.add(p_test.getId());
//		p9.setId(p_test.getId());
//		p9.setDeleted(true);
//		
//		test.delete(p9);
//		
//		try {
//			PreparedStatement ps = dbc.getConnection().prepareStatement("SELECT id,tischnummer,produktid,preis,rechnungid,status,deleted FROM bestellung WHERE id = ?");
//			ps.setInt(1,p_test.getId());
//			
//			ResultSet rs=ps.executeQuery();
//			if(rs.next()) {
//				assertThat(rs.getInt("id"), equalTo(p_test.getId()));
//				assertThat(rs.getInt("tischnummer"), equalTo(p9.getTisch()));
//				assertThat(rs.getInt("produktid"), equalTo(p9.getProdukt()));
//				assertThat(rs.getInt("preis"), equalTo(p9.getPreis()));
//				assertThat(rs.getInt("rechnungid"), equalTo(p9.getRechnung()));
//				assertThat(rs.getString("status"), equalTo(p9.getStatus()));
//				assertThat(rs.getBoolean("deleted"), equalTo(p9.getDeleted()));
//				return;
//			}else{
//				fail("No Data found");
//				return;
//			}
//		} catch (SQLException e) {
//			fail("ERROR: failed to search DB for bestellung!");
//			return;
//		}
//		catch (NullPointerException e) {
//			fail("NullPointerException");
//			return;
//		}
//	}
//
//	/**
//	 * Test method for {@link sepm.ss13.gast.dao.JDBCBestellungDAO#delete(sepm.ss13.gast.domain.Bestellung)}.
//	 */
//	@Test (expected=IllegalArgumentException.class) 
//	public void testDelete_3() throws DAOException,IllegalArgumentException {
//		
//		/* INSERT */
//		test.delete(null);
//
//	}
	
}
