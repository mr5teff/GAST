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
	
	private ApplicationContext ac;
	private DBConnector dbc;
	private JDBCTischDAO test = null;
	
	private Tisch t1 = null; //create
	private Tisch t2 = null; //create
	
	private Tisch t3 = null; //search
	private Tisch t4 = null; //search
	
	private Tisch t5 = null; //update
	private Tisch t6 = null; //update
	
	private Tisch t7 = null; //delete
	private Tisch t8 = null; //delete
	
	private ArrayList<Integer> mykeys = new ArrayList<Integer>();
	private ArrayList<Integer> mykeys_generated = new ArrayList<Integer>();

	@BeforeClass
	public void setUpBeforeClass() throws Exception {
		dbc.getConnection().setAutoCommit(false); 
		
		ac = new ClassPathXmlApplicationContext("spring-config.xml");
		dbc = (DBConnector) ac.getBean("databaseManager");
		dbc.getConnection().setAutoCommit(false); 
		test = new JDBCTischDAO(dbc.getConnection());
		
		t1 = new Tisch(-1, 33, 5, "Stammtisch", "Nichtraucher", false);
		t2 = new Tisch(2, 44, 5, "Neben Stammtisch", "Raucher", false);
		t3 = new Tisch(-1, 33, 5, "Stammtisch", "Nichtraucher", false);
		t4 = new Tisch(2, 44, 5, "Ultrastammtisch", "Raucher", false);
		t5 = new Tisch(-1, 33, 5, "Mitte", "Nichtraucher", false);
		t6 = new Tisch(2, 44, 5, "Neben Stammtisch", "Raucher", false);
		t7 = new Tisch(-1, 33, 5, "Mitte", "Nichtraucher", false);
		t8 = new Tisch(2, 44, 5, "Neben Stammtisch", "Raucher", false);
		
		Tisch t_temp = null;
		t_temp = test.create(t3);
		t3.setId(t_temp.getId());
		mykeys.add(t3.getId());
		t_temp = test.create(t4);
		t4.setId(t_temp.getId());
		mykeys.add(t4.getId());
	}

	@After
	public void tearDown() throws Exception {
		dbc.getConnection().rollback();
	}

	@Test
	public void testCreate_1() throws DAOException {
		Tisch t_test = test.create(t1);
		mykeys_generated.add(t_test.getId());
		
		try {
			PreparedStatement ps = dbc.getConnection().prepareStatement("SELECT id, nummer, plaetze, beschreibung, art, deleted FROM tisch WHERE id=?");
			ps.setInt(1,t_test.getId());
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				assertThat(rs.getInt("id"), equalTo(t_test.getId()));
				assertThat(rs.getInt("nummer"), equalTo(t_test.getNummer()));
				assertThat(rs.getInt("plaetze"), equalTo(t_test.getPlaetze()));
				assertThat(rs.getString("beschreibung") , equalTo(t_test.getBeschreibung()));
				assertThat(rs.getString("art") , equalTo(t_test.getArt()));
				assertThat(rs.getBoolean("deleted") , equalTo(t_test.getDeleted()));
				return;
			} else {
				fail("No data found");
				return;
			}
		} catch (SQLException e) {
			fail("ERROR: failed to search DB for Tisch");
			return;
		} catch (NullPointerException e) {
			fail("NullPointerException");
			return;
		}
	}

	@Test
	public void testCreate_2() throws DAOException {
		Tisch t_test = test.create(t2);
		mykeys_generated.add(t_test.getId());
		
		try {
			PreparedStatement ps = dbc.getConnection().prepareStatement("SELECT id, nummer, plaetze, beschreibung, art, deleted FROM tisch WHERE id=?");
			ps.setInt(1,t_test.getId());
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				assertThat(rs.getInt("id"), equalTo(t_test.getId()));
				assertThat(rs.getInt("nummer"), equalTo(t_test.getNummer()));
				assertThat(rs.getInt("plaetze"), equalTo(t_test.getPlaetze()));
				assertThat(rs.getString("beschreibung") , equalTo(t_test.getBeschreibung()));
				assertThat(rs.getString("art") , equalTo(t_test.getArt()));
				assertThat(rs.getBoolean("deleted") , equalTo(t_test.getDeleted()));
				return;
			} else {
				fail("No data found");
				return;
			}
		} catch (SQLException e) {
			fail("ERROR: failed to search DB for Tisch");
			return;
		} catch (NullPointerException e) {
			fail("NullPointerException");
			return;
		}
	}

	@Test
	public void testSearch_1() throws DAOException {
		Tisch toSearch = new Tisch(t3.getId(), 0, 0, null, null, false);
		ArrayList<Tisch> t_test = test.search(toSearch);
		assertThat(t_test.size(), equalTo(0));
		
		toSearch = t3;
		t_test = test.search(toSearch);
		assertTrue(t_test.size() > 0);
		
		toSearch = new Tisch(t3.getId(), t3.getNummer(), t3.getPlaetze(), t3.getBeschreibung(), t3.getArt(), t3.getDeleted());
		t_test = test.search(toSearch);
		assertTrue(t_test.size() > 0);
		
		for (Tisch t : t_test) {
			assertThat(t.getId(), equalTo(t3.getId()));
			assertThat(t.getNummer(), equalTo(t3.getNummer()));
			assertThat(t.getPlaetze(), equalTo(t3.getPlaetze()));
			assertThat(t.getBeschreibung(), equalTo(t3.getBeschreibung()));
			assertThat(t.getArt(), equalTo(t3.getArt()));
			assertThat(t.getDeleted(), equalTo(t3.getDeleted()));
		}
	}

	@Test
	public void testSearch_2() throws DAOException {
		Tisch toSearch = new Tisch(-1, t4.getNummer(), t4.getPlaetze(), t4.getBeschreibung(), t4.getArt(), t4.getDeleted());
		ArrayList<Tisch> t_test = test.search(toSearch);
		
		assertTrue(t_test.size() == 0);
		
		t_test = test.search(t4);
		
		for (Tisch t : t_test) {
			assertThat(t.getId(), equalTo(t4.getId()));
			assertThat(t.getNummer(), equalTo(t4.getNummer()));
			assertThat(t.getPlaetze(), equalTo(t4.getPlaetze()));
			assertThat(t.getBeschreibung(), equalTo(t4.getBeschreibung()));
			assertThat(t.getArt(), equalTo(t4.getArt()));
			assertThat(t.getDeleted(), equalTo(t4.getDeleted()));
		}
	}

	@Test
	public void testUpdate_1() throws DAOException {
		
		t5 = test.create(t5);
		mykeys_generated.add(t5.getId());
		
		Tisch toUpdate = new Tisch(t5.getId(), 666, 11, "Superplatzerl" , "Raucher", t5.getDeleted());
		
		test.update(toUpdate);
		ArrayList<Tisch> shouldBeUpdated = test.search(toUpdate);
		
		for (Tisch t : shouldBeUpdated) {
			assertThat(t.getId(), equalTo(t5.getId()));
			
			assertFalse(t.getNummer().equals(t5.getNummer()));
			assertFalse(t.getPlaetze().equals(t5.getPlaetze()));
			assertFalse(t.getBeschreibung().equals(t5.getBeschreibung()));
			assertFalse(t.getArt().equals(t5.getArt()));
			
			assertThat(t.getNummer(), equalTo(toUpdate.getNummer()));
			assertThat(t.getPlaetze(), equalTo(toUpdate.getPlaetze()));
			assertThat(t.getBeschreibung(), equalTo(toUpdate.getBeschreibung()));
			assertThat(t.getArt(), equalTo(toUpdate.getArt()));
			assertThat(t.getDeleted(), equalTo(toUpdate.getDeleted()));
		}
	}

	@Test
	public void testUpdate_2() throws DAOException {
		
		t6 = test.create(t6);
		mykeys_generated.add(t6.getId());
		
		Tisch toUpdate = new Tisch(t6.getId(), t6.getNummer(), t6.getPlaetze(), t6.getBeschreibung(), "Nichtraucher", t6.getDeleted());
		
		test.update(toUpdate);
		ArrayList<Tisch> shouldBeUpdated = test.search(toUpdate);
		
		for (Tisch t : shouldBeUpdated) {
			assertThat(t.getId(), equalTo(t6.getId()));
			
			assertFalse(t.getArt().equals(t6.getArt()));
			
			assertThat(t.getNummer(), equalTo(toUpdate.getNummer()));
			assertThat(t.getPlaetze(), equalTo(toUpdate.getPlaetze()));
			assertThat(t.getBeschreibung(), equalTo(toUpdate.getBeschreibung()));
			assertThat(t.getArt(), equalTo(toUpdate.getArt()));
			assertThat(t.getDeleted(), equalTo(toUpdate.getDeleted()));
		}
	}
	
	@Test
	public void testDelete_1() throws DAOException {
		
		t7 = test.create(t7);
		mykeys_generated.add(t7.getId());
		
		ArrayList<Tisch> t_test = test.search(t7);
		assertTrue(t_test.size() != 0);
		test.delete(t7);
		t_test = test.search(t7);
		assertTrue(t_test.size() == 0);
	}

	@Test
	public void testDelete_2() throws DAOException {
		
		t8 = test.create(t8);
		mykeys_generated.add(t8.getId());
		
		ArrayList<Tisch> t_test = test.search(t8);
		assertTrue(t_test.size() != 0);
		test.delete(new Tisch(t8.getId(), 666, 33, "blabla", "Raucher", true));
		t_test = test.search(t8);
		assertTrue(t_test.size() == 0);
	}
}
