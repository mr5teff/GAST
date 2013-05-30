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

	private static JDBCRechnungDAO testDAO;
	private static DBConnector dbc;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring-config.xml");
		dbc = (DBConnector) ac.getBean("databaseManager");
		dbc.getConnection().setAutoCommit(false); 
		testDAO = new JDBCRechnungDAO(dbc.getConnection());		
	}

	@After
	public void tearDown() throws Exception {
		dbc.getConnection().rollback();
	}
	
	@Test
	public void testCreate_1() {
		// Test mit ID=-1 und gültigen Werten

		try {
			Rechnung testRechnung = testDAO.create(new Rechnung(-1, new Date(0), 20, null));
			ArrayList<Rechnung> fromDB = testDAO.search(testRechnung);
			
			for (Rechnung r : fromDB) {
				int i = r.getId();
				int j = testRechnung.getId();
				System.out.println(i == j);
				System.out.println(r.getId() == testRechnung.getId());
				
				assertTrue(r != null);
				assertTrue(r.getId() == testRechnung.getId());
				assertThat(r.getDatum().compareTo(testRechnung.getDatum()), equalTo(0));
				assertTrue(r.getTrinkgeld() == testRechnung.getTrinkgeld());
			}
		} catch (DAOException e) {
			fail(e.getMessage());
		}
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
//	/**
//	 * Test method for {@link sepm.ss13.gast.dao.JDBCRechnungDAO#create(sepm.ss13.gast.domain.Rechnung)}.
//	 * @throws DAOException 
//	 */
//	@Test
//	public void testCreate_1() throws DAOException {
//		
//		/* INSERT */
//		Rechnung r_test = null;
//		r_test = test.create(r1);
//		
//		mykeys_generated.add(r_test.getId());
//		
//		/* GET INSERTED */
//		try {
//			PreparedStatement ps = dbc.getConnection().prepareStatement("SELECT id,datum,trinkgeld FROM rechnung WHERE id=?");
//			ps.setInt(1,r_test.getId());
//			
//			ResultSet rs=ps.executeQuery();
//			if(rs.next()) {
//				long t = r1.getDatum().getTime();
//				java.sql.Date dt = new java.sql.Date(t);
//				
//				assertThat(rs.getInt("id"), equalTo(r_test.getId()));
//				assertThat(rs.getDate("datum").toString(), equalTo(dt.toString()));
//				assertThat(rs.getInt("trinkgeld"), equalTo(r1.getTrinkgeld()));
//				return;
//			}else{
//				fail("No Data found");
//				return;
//			}
//		} catch (SQLException e) {
//			fail("ERROR: failed to search DB for bill!");
//			return;
//		}
//		catch (NullPointerException e) {
//			fail("NullPointerException");
//			return;
//		}
//	}
//	
//
//	/**
//	 * Test method for {@link sepm.ss13.gast.dao.JDBCRechnungDAO#create(sepm.ss13.gast.domain.Rechnung)}.
//	 * @throws DAOException 
//	 */
//	@Test (expected=IllegalArgumentException.class) 
//	public void testCreate_2() throws DAOException {
//		
//		/* INSERT */
//		test.create(null);
//		
//	}	
//
//	/**
//	 * Test method for {@link sepm.ss13.gast.dao.JDBCRechnungDAO#create(sepm.ss13.gast.domain.Rechnung)}.
//	 * @throws DAOException 
//	 */
//	@Test (expected=IllegalArgumentException.class) 
//	public void testCreate_3() throws DAOException {
//		
//		/* INSERT */
//		test.create(r2);
//		
//	}		
//	
//	
//	
//	
//
//	/**
//	 * Test method for {@link sepm.ss13.gast.dao.JDBCRechnungDAO#search(sepm.ss13.gast.domain.Rechnung)}.
//	 * @throws DAOException 
//	 */
//	@Test
//	public void testSearch_1() throws DAOException {
//		
//		/* INSERT */
//		ArrayList<Rechnung> r_test = new ArrayList<Rechnung>();
//		r_test = test.search(r3);
//	
//		for(int i = 0; i < r_test.size();i++){
//			long t = r3.getDatum().getTime();
//			java.sql.Date dt = new java.sql.Date(t);
//			
//			long t2 = r_test.get(i).getDatum().getTime();
//			java.sql.Date dt2 = new java.sql.Date(t2);
//			
//			assertThat(r_test.get(i).getId(), equalTo(r3.getId()));
//			assertThat(dt2.toString(), equalTo(dt.toString()));
//			assertThat(r_test.get(i).getTrinkgeld(), equalTo(r3.getTrinkgeld()));
//		}
//		
//		r_test = new ArrayList<Rechnung>();
//		r_test = test.search(r4);
//	
//		for(int i = 0; i < r_test.size();i++){
//			long t = r4.getDatum().getTime();
//			java.sql.Date dt = new java.sql.Date(t);
//			
//			long t2 = r_test.get(i).getDatum().getTime();
//			java.sql.Date dt2 = new java.sql.Date(t2);
//			
//			assertThat(r_test.get(i).getId(), equalTo(r4.getId()));
//			assertThat(dt2.toString(), equalTo(dt.toString()));
//			assertThat(r_test.get(i).getTrinkgeld(), equalTo(r4.getTrinkgeld()));
//		}
//	}
//	
//
//	/**
//	 * Test method for {@link sepm.ss13.gast.dao.JDBCRechnungDAO#search(sepm.ss13.gast.domain.Rechnung)}.
//	 * @throws DAOException 
//	 */
//	@Test (expected=IllegalArgumentException.class) 
//	public void testSearch_2() throws DAOException {
//		
//		/* INSERT */
//		test.search(null);
//	
//	}
//	
//	/**
//	 * Test method for {@link sepm.ss13.gast.dao.JDBCRechnungDAO#search(sepm.ss13.gast.domain.Rechnung)}.
//	 * @throws DAOException 
//	 */
//	@Test
//	public void testSearch_3() throws DAOException {
//		
//		/* INSERT */
//		Rechnung r_test = null;
//		r_test = test.create(r5);
//		
//		mykeys_generated.add(r_test.getId());
//		
//		/* GET INSERTED */
//		try {
//			PreparedStatement ps = dbc.getConnection().prepareStatement("SELECT id,datum,trinkgeld FROM rechnung WHERE id=?");
//			ps.setInt(1,r_test.getId());
//			
//			ResultSet rs=ps.executeQuery();
//			if(rs.next()) {
//				long t = r5.getDatum().getTime();
//				java.sql.Date dt = new java.sql.Date(t);
//				
//				assertThat(rs.getInt("id"), equalTo(r_test.getId()));
//				assertThat(rs.getDate("datum").toString(), equalTo(dt.toString()));
//				assertThat(rs.getInt("trinkgeld"), equalTo(r5.getTrinkgeld()));
//				return;
//			}else{
//				fail("No Data found");
//				return;
//			}
//		} catch (SQLException e) {
//			fail("ERROR: failed to search DB for bill!");
//			return;
//		}
//		catch (NullPointerException e) {
//			fail("NullPointerException");
//			return;
//		}
//	
//	}

}
