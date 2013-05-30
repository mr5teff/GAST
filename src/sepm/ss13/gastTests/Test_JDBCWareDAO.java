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
import sepm.ss13.gast.dao.JDBCWareDAO;
import sepm.ss13.gast.domain.Tisch;
import sepm.ss13.gast.domain.Ware;

public class Test_JDBCWareDAO {

	private static JDBCWareDAO testDAO;
	private static DBConnector dbc;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring-config.xml");
		dbc = (DBConnector) ac.getBean("databaseManager");
		dbc.getConnection().setAutoCommit(false); 
		testDAO = new JDBCWareDAO(dbc.getConnection());		
	}

	@After
	public void tearDown() throws Exception {
		dbc.getConnection().rollback();
	}

	@Test
	public void testCreate_1() {
		// Test mit ID=-1 und gültigen Werten
		
		try {
			Ware testWare = testDAO.create(new Ware(-1, "Sauerkraut", "gramm", 10000));
			ArrayList<Ware> fromDB = testDAO.search(testWare);
			
			for(Ware w : fromDB) {
				assertTrue(w != null);
				assertThat(w.getId(), equalTo(testWare.getId()));
				assertThat(w.getBezeichnung(), equalTo(testWare.getBezeichnung()));
				assertThat(w.getEinheit(), equalTo(testWare.getEinheit()));
				assertThat(w.getLagerstand(), equalTo(testWare.getLagerstand()));
			}
		} catch (DAOException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testCreate_2() {
		// Test mit ID=2 und gültigen Werten
		
		try {
			Ware testWare = testDAO.create(new Ware(2, "Cola", "milliliter", 20000));
			ArrayList<Ware> fromDB = testDAO.search(testWare);
			
			for(Ware w : fromDB) {
				assertTrue(w != null);
				assertThat(w.getId(), equalTo(testWare.getId()));
				assertThat(w.getBezeichnung(), equalTo(testWare.getBezeichnung()));
				assertThat(w.getEinheit(), equalTo(testWare.getEinheit()));
				assertThat(w.getLagerstand(), equalTo(testWare.getLagerstand()));
			}
		} catch (DAOException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testCreate_3() {
		// Test mit ungültigen Werten. Sollte nicht erstellt werden und Exception werfen.
		
		try {
			Ware testWare = testDAO.create(new Ware(-1, "Sauerkraut", "Kübal", 10));
			fail("Sollte Exception werfen!");
		} catch (DAOException e) {
			//Wenn hier, alles gut.
		}
	}
	
	@Test
	public void testSearch_1() {
		// Ware erzeugen, suchen

		try {
			Ware testWare = testDAO.create(new Ware(2, "Cola", "milliliter", 20000));
			ArrayList<Ware> fromDB = testDAO.search(testWare);
			
			for(Ware w : fromDB) {
				assertTrue(w != null);
				assertThat(w.getId(), equalTo(testWare.getId()));
				assertThat(w.getBezeichnung(), equalTo(testWare.getBezeichnung()));
				assertThat(w.getEinheit(), equalTo(testWare.getEinheit()));
				assertThat(w.getLagerstand(), equalTo(testWare.getLagerstand()));
			}
		} catch (DAOException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testSearch_2() {
		// Ware erzeugen, suchen

		try {
			Ware testWare = testDAO.create(new Ware(-1, "Sauerkraut", "gramm", 10000));
			ArrayList<Ware> fromDB = testDAO.search(testWare);
			
			for(Ware w : fromDB) {
				assertTrue(w != null);
				assertThat(w.getId(), equalTo(testWare.getId()));
				assertThat(w.getBezeichnung(), equalTo(testWare.getBezeichnung()));
				assertThat(w.getEinheit(), equalTo(testWare.getEinheit()));
				assertThat(w.getLagerstand(), equalTo(testWare.getLagerstand()));
			}
		} catch (DAOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testUpdate_1() throws DAOException {
		// Ware erzeugen und updaten
		
		try {
			Ware testWare = testDAO.create(new Ware(-1, "Sauerkraut", "gramm", 10000));
			Ware newTestWare = new Ware(testWare.getId(), "Supersauerkraut", "gramm", 10000);
			testDAO.update(newTestWare);
			ArrayList<Ware> fromDB = testDAO.search(newTestWare);
			
			for(Ware w : fromDB) {
				assertThat(w.getId(), equalTo(testWare.getId()));
				assertThat(w.getId(), equalTo(newTestWare.getId()));
				
				assertFalse(w.getBezeichnung().equals(testWare.getBezeichnung()));

				assertThat(w.getBezeichnung(), equalTo(newTestWare.getBezeichnung()));
				assertThat(w.getEinheit(), equalTo(newTestWare.getEinheit()));
				assertThat(w.getLagerstand(), equalTo(newTestWare.getLagerstand()));
			}
		} catch (DAOException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testUpdate_2() throws DAOException {
		// Ware erzeugen und updaten
		
		try {
			Ware testWare = testDAO.create(new Ware(2, "Cola", "milliliter", 20000));
			Ware newTestWare = new Ware(testWare.getId(), "Sauerkraut", "gramm", 30000);
			testDAO.update(newTestWare);
			ArrayList<Ware> fromDB = testDAO.search(newTestWare);
			
			for(Ware w : fromDB) {
				assertThat(w.getId(), equalTo(testWare.getId()));
				assertThat(w.getId(), equalTo(newTestWare.getId()));
				
				assertFalse(w.getBezeichnung().equals(testWare.getBezeichnung()));
				assertFalse(w.getEinheit().equals(testWare.getEinheit()));
				assertFalse(w.getLagerstand() == testWare.getLagerstand());

				assertThat(w.getBezeichnung(), equalTo(newTestWare.getBezeichnung()));
				assertThat(w.getEinheit(), equalTo(newTestWare.getEinheit()));
				assertThat(w.getLagerstand(), equalTo(newTestWare.getLagerstand()));
			}
		} catch (DAOException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testDelete_1() throws DAOException {
		// Tisch erzeugen, suchen, löschen, suchen
		
		try {
			Ware testWare = testDAO.create(new Ware(-1, "Sauerkraut", "gramm", 10000));
			
			ArrayList<Ware> fromDB = testDAO.search(testWare);
			assertTrue(fromDB.size() > 0);
			
			testDAO.delete(testWare);
			
			fromDB = testDAO.search(testWare);
			assertThat(fromDB.size(), equalTo(0));
		} catch(DAOException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testDelete_2() throws DAOException {
		// Tisch erzeugen, suchen, löschen, suchen
		
		try {
			Ware testWare = testDAO.create(new Ware(2, "Cola", "milliliter", 20000));
			
			ArrayList<Ware> fromDB = testDAO.search(testWare);
			assertTrue(fromDB.size() > 0);
			
			testDAO.delete(testWare);
			
			fromDB = testDAO.search(testWare);
			assertThat(fromDB.size(), equalTo(0));
		} catch(DAOException e) {
			fail(e.getMessage());
		}
	}
}