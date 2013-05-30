package sepm.ss13.gastTests;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.dao.DBConnector;
import sepm.ss13.gast.dao.JDBCProduktDAO;
import sepm.ss13.gast.dao.JDBCTischDAO;
import sepm.ss13.gast.domain.Tisch;

public class Test_JDBCTischDAO {

	private static JDBCTischDAO testDAO;
	private static DBConnector dbc;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring-config.xml");
		dbc = (DBConnector) ac.getBean("databaseManager");
		dbc.getConnection().setAutoCommit(false); 
		testDAO = new JDBCTischDAO(dbc.getConnection());		
	}

	@After
	public void tearDown() throws Exception {
		dbc.getConnection().rollback();
	}

	@Test
	public void testCreate_1(){
		// Test mit ID=-1 und gültigen Werten

		try {
			Tisch testTisch = testDAO.create(new Tisch(-1, 33, 5, "Stammtisch", "Nichtraucher", false));
			ArrayList<Tisch> fromDB = testDAO.search(testTisch);

			for( Tisch t : fromDB) {
				assertTrue(t != null);
				assertThat(t.getId(), equalTo(testTisch.getId()));
				assertThat(t.getNummer(), equalTo(testTisch.getNummer()));
				assertThat(t.getPlaetze(), equalTo(testTisch.getPlaetze()));
				assertThat(t.getBeschreibung(), equalTo(testTisch.getBeschreibung()));
				assertThat(t.getArt(), equalTo(testTisch.getArt()));
				assertThat(t.getDeleted(), equalTo(testTisch.getDeleted()));
			}
		} catch(DAOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testCreate_2(){
		// Test mit ID=2 und gültigen Werten

		try {
			Tisch testTisch = testDAO.create(new Tisch(2, 13, 7, "SuperÜberStammtisch", "Raucher", true));
			ArrayList<Tisch> fromDB = testDAO.search(testTisch);

			for( Tisch t : fromDB) {
				assertTrue(t != null);
				assertThat(t.getId(), equalTo(testTisch.getId()));
				assertThat(t.getNummer(), equalTo(testTisch.getNummer()));
				assertThat(t.getPlaetze(), equalTo(testTisch.getPlaetze()));
				assertThat(t.getBeschreibung(), equalTo(testTisch.getBeschreibung()));
				assertThat(t.getArt(), equalTo(testTisch.getArt()));
				assertThat(t.getDeleted(), equalTo(testTisch.getDeleted()));
			}
		} catch(DAOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testCreate_3() {
		// Test mit ungültigen Werten. Sollte nicht erstellt werden und Exception werfen.
		try {
			Tisch testTisch = testDAO.create(new Tisch(0, 0, 0, null, "Superraucher", false));
			fail("Sollte Exception werfen!");
		} catch (DAOException e) {
			// Wenn hier, alles gut.
		}
	}


	@Test
	public void testSearch_1() {
		// Einen bestimmten Tisch suchen

		try {
			Tisch testTisch = testDAO.create(new Tisch(-1, 33, 5, "Stammtisch", "Nichtraucher", false));
			ArrayList<Tisch> fromDB = testDAO.search(testTisch);

			for (Tisch t : fromDB) {
				assertTrue(t != null);
				assertThat(t.getId(), equalTo(testTisch.getId()));
				assertThat(t.getNummer(), equalTo(testTisch.getNummer()));
				assertThat(t.getPlaetze(), equalTo(testTisch.getPlaetze()));
				assertThat(t.getBeschreibung(), equalTo(testTisch.getBeschreibung()));
				assertThat(t.getArt(), equalTo(testTisch.getArt()));
				assertThat(t.getDeleted(), equalTo(testTisch.getDeleted()));
			}
		} catch(DAOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testSearch_2() {
		// Einen bestimmten Tisch suchen

		try {
			Tisch testTisch = testDAO.create(new Tisch(2, 13, 7, "SuperÜberStammtisch", "Raucher", true));
			ArrayList<Tisch> fromDB = testDAO.search(testTisch);

			for (Tisch t : fromDB) {
				assertTrue(t != null);
				assertThat(t.getId(), equalTo(testTisch.getId()));
				assertThat(t.getNummer(), equalTo(testTisch.getNummer()));
				assertThat(t.getPlaetze(), equalTo(testTisch.getPlaetze()));
				assertThat(t.getBeschreibung(), equalTo(testTisch.getBeschreibung()));
				assertThat(t.getArt(), equalTo(testTisch.getArt()));
				assertThat(t.getDeleted(), equalTo(testTisch.getDeleted()));
			}
		} catch(DAOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testUpdate_1() {
		// Tisch erzeugen und updaten anhand Tischdaten

		try {
			Tisch testTisch = testDAO.create(new Tisch(-1, 33, 5, "Stammtisch", "Nichtraucher", false));
			Tisch newTestTisch = new Tisch(testTisch.getId(), 666, 11, "Superplatzerl" , "Raucher", testTisch.getDeleted());
			testDAO.update(newTestTisch);

			ArrayList<Tisch> fromDB = testDAO.search(newTestTisch);

			for (Tisch t : fromDB) {
				assertThat(t.getId(), equalTo(testTisch.getId()));
				assertThat(t.getId(), equalTo(newTestTisch.getId()));

				assertFalse(t.getNummer().equals(testTisch.getNummer()));
				assertFalse(t.getPlaetze().equals(testTisch.getPlaetze()));
				assertFalse(t.getBeschreibung().equals(testTisch.getBeschreibung()));
				assertFalse(t.getArt().equals(testTisch.getArt()));

				assertThat(t.getNummer(), equalTo(newTestTisch.getNummer()));
				assertThat(t.getPlaetze(), equalTo(newTestTisch.getPlaetze()));
				assertThat(t.getBeschreibung(), equalTo(newTestTisch.getBeschreibung()));
				assertThat(t.getArt(), equalTo(newTestTisch.getArt()));
				assertThat(t.getDeleted(), equalTo(newTestTisch.getDeleted()));
			}
		} catch(DAOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testUpdate_2() {
		// Tisch erzeugen und updaten anhand Tischdaten

		try {
			Tisch testTisch = testDAO.create(new Tisch(2, 13, 7, "SuperÜberStammtisch", "Raucher", true));
			Tisch newTestTisch = new Tisch(testTisch.getId(), 666, 11, "Superplatzerl" , "Raucher", testTisch.getDeleted());
			testDAO.update(newTestTisch);

			ArrayList<Tisch> fromDB = testDAO.search(newTestTisch);

			for (Tisch t : fromDB) {
				assertThat(t.getId(), equalTo(testTisch.getId()));
				assertThat(t.getId(), equalTo(newTestTisch.getId()));

				assertFalse(t.getNummer().equals(testTisch.getNummer()));
				assertFalse(t.getPlaetze().equals(testTisch.getPlaetze()));
				assertFalse(t.getBeschreibung().equals(testTisch.getBeschreibung()));

				assertThat(t.getNummer(), equalTo(newTestTisch.getNummer()));
				assertThat(t.getPlaetze(), equalTo(newTestTisch.getPlaetze()));
				assertThat(t.getBeschreibung(), equalTo(newTestTisch.getBeschreibung()));
				assertThat(t.getArt(), equalTo(newTestTisch.getArt()));
				assertThat(t.getDeleted(), equalTo(newTestTisch.getDeleted()));
			}
		} catch(DAOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testDelete_1() {
		// Tisch erzeugen, suchen, löschen, suchen

		try {
			Tisch testTisch = testDAO.create(new Tisch(-1, 33, 5, "Stammtisch", "Nichtraucher", false));

			ArrayList<Tisch> fromDB = testDAO.search(testTisch);
			assertTrue(fromDB.size() > 0);

			testDAO.delete(testTisch);

			fromDB = testDAO.search(testTisch);
			assertThat(fromDB.size(), equalTo(0));
		} catch(DAOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testDelete_2() {
		// Tisch erzeugen, suchen, löschen, suchen

		try {
			Tisch testTisch = testDAO.create(new Tisch(2, 13, 7, "SuperÜberStammtisch", "Raucher", false));

			ArrayList<Tisch> fromDB = testDAO.search(testTisch);
			assertTrue(fromDB.size() > 0);

			testDAO.delete(testTisch);

			fromDB = testDAO.search(testTisch);
			assertThat(fromDB.size(), equalTo(0));
		} catch(DAOException e) {
			fail(e.getMessage());
		}
	}
}
