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
import sepm.ss13.gast.dao.JDBCReservierungDAO;
import sepm.ss13.gast.dao.JDBCTischDAO;
import sepm.ss13.gast.domain.Reservierung;
import sepm.ss13.gast.domain.Tisch;
import sepm.ss13.gast.domain.Ware;

public class Test_JDBCReservierungDAO {

	private static JDBCReservierungDAO testDAO;
	private static DBConnector dbc;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring-config.xml");
		dbc = (DBConnector) ac.getBean("databaseManager");
		dbc.getConnection().setAutoCommit(false); 
		testDAO = new JDBCReservierungDAO(dbc.getConnection());		
	}

	@After
	public void tearDown() throws Exception {
		dbc.getConnection().rollback();
	}

	@Test
	public void testCreate_1() throws DAOException {
		// Test mit ID=-1 und gültigen Werten

		try {
			Reservierung testRes= testDAO.create(new Reservierung(-1, new Date(0), 3, 5, 1, "Musterfrau", "0664 11 22 33 44"));
			ArrayList<Reservierung> fromDB = testDAO.search(testRes);

			for (Reservierung r : fromDB) {
				assertTrue(r != null);
				assertThat(r.getId(), equalTo(testRes.getId()));
				assertThat(r.getPersonen(), equalTo(testRes.getPersonen()));
				assertThat(r.getTischnummer(), equalTo(testRes.getTischnummer()));
				assertThat(r.getDauer(), equalTo(testRes.getDauer()));
				assertThat(r.getName(), equalTo(testRes.getName()));
				assertThat(r.getTelefonnummer(), equalTo(testRes.getTelefonnummer()));
			}
		} catch (DAOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testCreate_2() throws DAOException {
		// Test mit ID=2 und gültigen Werten

		try {
			Reservierung testRes= testDAO.create(new Reservierung(2, new Date(100), 6, 2, 3, "Mustermann", "0664 11 22 33 44"));
			ArrayList<Reservierung> fromDB = testDAO.search(testRes);

			for (Reservierung r : fromDB) {
				assertTrue(r != null);
				assertThat(r.getId(), equalTo(testRes.getId()));
				assertThat(r.getPersonen(), equalTo(testRes.getPersonen()));
				assertThat(r.getTischnummer(), equalTo(testRes.getTischnummer()));
				assertThat(r.getDauer(), equalTo(testRes.getDauer()));
				assertThat(r.getName(), equalTo(testRes.getName()));
				assertThat(r.getTelefonnummer(), equalTo(testRes.getTelefonnummer()));
			}
		} catch (DAOException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testCreate_3() throws DAOException {
		// Test mit ungültigen Werten

		try {
			Reservierung testRes= testDAO.create(new Reservierung(2, null, 6, 2, 3, 
					"Super langer string, der die definierten Grenzen der DB überschreiten soll. " +
					"alsjdalsjdaslkjd alksjdaslkjdaslkjdalsköjslöjaslödjasldjaslkasjdlkasjdlkasjd" +
					"lasjlasdjalsödjaslöjdaslöjdaslöjsalöajsdlöjaslköd", "0664 11 22 33 44"));
			
			fail("Sollte Exception werfen!");
		} catch (DAOException e) {
			// Wenn hier, alles gut.
		}
	}

	@Test
	public void testSearch_1() throws DAOException {
		// Test mit ID=-1 und gültigen Werten

		try {
			Reservierung testRes= testDAO.create(new Reservierung(-1, new Date(0), 3, 5, 1, "Musterfrau", "0664 11 22 33 44"));
			ArrayList<Reservierung> fromDB = testDAO.search(testRes);

			for (Reservierung r : fromDB) {
				assertTrue(r != null);
				assertThat(r.getId(), equalTo(testRes.getId()));
				assertThat(r.getPersonen(), equalTo(testRes.getPersonen()));
				assertThat(r.getTischnummer(), equalTo(testRes.getTischnummer()));
				assertThat(r.getDauer(), equalTo(testRes.getDauer()));
				assertThat(r.getName(), equalTo(testRes.getName()));
				assertThat(r.getTelefonnummer(), equalTo(testRes.getTelefonnummer()));
			}
		} catch (DAOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testUpdate_1() throws DAOException {
		// Reservierung erzeugen und updaten
		
		try {
			Reservierung testRes = testDAO.create(new Reservierung(-1, new Date(0), 3, 5, 1, "Musterfrau", "0664 11 22 33 44"));
			Reservierung newTestRes = new Reservierung(testRes.getId(), new Date(0), 3, 5, 1, "Mustermann", "0664 11 22 33 44");
			testDAO.update(newTestRes);
			
			ArrayList<Reservierung> fromDB = testDAO.search(newTestRes);
			
			for (Reservierung r : fromDB) {
				assertThat(r.getId(), equalTo(testRes.getId()));
				assertThat(r.getId(), equalTo(newTestRes.getId()));
				
				assertFalse(r.getName().equals(testRes.getName()));
				
				assertThat(r.getDatum().toString(), equalTo(newTestRes.getDatum().toString()));
				assertThat(r.getTischnummer(), equalTo(newTestRes.getTischnummer()));
				assertThat(r.getPersonen(), equalTo(newTestRes.getPersonen()));
				assertThat(r.getDauer(), equalTo(newTestRes.getDauer()));
				assertThat(r.getName(), equalTo(newTestRes.getName()));
			}
		} catch(DAOException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testUpdate_2() throws DAOException {
		// Reservierung erzeugen und updaten
		
		try {
			Reservierung testRes = testDAO.create(new Reservierung(-1, new Date(10), 3, 5, 1, "Musterfrau", "0664 11 22 33 44"));
			Reservierung newTestRes = new Reservierung(testRes.getId(), new Date(5000), 4, 6, 2, "Mustermann", "0664 22 22 33 44");
			testDAO.update(newTestRes);
			
			ArrayList<Reservierung> fromDB = testDAO.search(newTestRes);
			
			for (Reservierung r : fromDB) {
				assertThat(r.getId(), equalTo(testRes.getId()));
				assertThat(r.getId(), equalTo(newTestRes.getId()));

				assertFalse(r.getDatum().compareTo(testRes.getDatum()) == 0);
				assertFalse(r.getTischnummer() == testRes.getTischnummer());
				assertFalse(r.getPersonen() == testRes.getPersonen());
				assertFalse(r.getDauer() == testRes.getDauer());
				assertFalse(r.getName().toString().equals(testRes.getName()));
				assertFalse(r.getTelefonnummer().equals(testRes.getTelefonnummer()));
				
				assertThat(r.getDatum().compareTo(newTestRes.getDatum()), equalTo(0));
				assertThat(r.getTischnummer(), equalTo(newTestRes.getTischnummer()));
				assertThat(r.getPersonen(), equalTo(newTestRes.getPersonen()));
				assertThat(r.getDauer(), equalTo(newTestRes.getDauer()));
				assertThat(r.getName(), equalTo(newTestRes.getName()));
			}
		} catch(DAOException e) {
			fail(e.getMessage());
		}
	}
	
//	public void testUpdate_1() {
//		// Tisch erzeugen und updaten anhand Tischdaten
//
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
//	}
	
	
	
	
	
//
//	@Test
//	public void testUpdate_2() throws DAOException {
//
//		r6 = test.create(r6);
//		mykeys_generated.add(r6.getId());
//
//		Reservierung toUpdate = new Reservierung(r6.getId(), null, 0, 0, 0, null, null);
//		test.update(toUpdate);
//		ArrayList<Reservierung> shouldBeUpdated = test.search(toUpdate);
//
//		assertThat(shouldBeUpdated.size(), equalTo(0));
//
//		toUpdate = new Reservierung(r6.getId(), r6.getDatum(), r6.getDauer(), r6.getPersonen(), r6.getTischnummer(), "Musterfrau", r6.getTelefonnummer());
//		test.update(toUpdate);
//		shouldBeUpdated = test.search(toUpdate);
//
//		for (Reservierung r : shouldBeUpdated) {
//			assertThat(r.getId(), equalTo(r6.getId()));
//
//			assertThat(r.getDatum().toString(), equalTo(toUpdate.getDatum().toString()));
//			assertThat(r.getDauer(), equalTo(toUpdate.getDauer()));
//			assertThat(r.getPersonen(), equalTo(toUpdate.getPersonen()));
//			assertThat(r.getTischnummer(), equalTo(toUpdate.getTischnummer()));
//			assertThat(r.getName(), equalTo(toUpdate.getName()));
//			assertThat(r.getTelefonnummer(), equalTo(toUpdate.getTelefonnummer()));
//		}
//	}
//
//	@Test
//	public void testDelete_1() throws DAOException {
//
//		r7 = test.create(r7);
//		mykeys_generated.add(r7.getId());
//
//		ArrayList<Reservierung> r_test = test.search(r7);
//		assertTrue(r_test.size() != 0);
//		test.delete(r7);
//		r_test = test.search(r7);
//		assertTrue(r_test.size() == 0);
//	}
//
//	@Test
//	public void testDelete_2() throws DAOException {
//
//		r8 = test.create(r8);
//		mykeys_generated.add(r8.getId());
//
//		ArrayList<Reservierung> r_test = test.search(r8);
//		assertTrue(r_test.size() != 0);
//		test.delete(r8);
//		r_test = test.search(r8);
//		assertTrue(r_test.size() == 0);
//	}
}
