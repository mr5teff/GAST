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
import sepm.ss13.gast.domain.Reservierung;
import sepm.ss13.gast.domain.Tisch;

public class Test_JDBCReservierungDAO {
	
	private static ApplicationContext ac;
	static DBConnector dbc;
	static JDBCReservierungDAO test = null;
	
	static Reservierung r1 = null; //create
	static Reservierung r2 = null; //create
	
	static Reservierung r3 = null; //search
	static Reservierung r4 = null; //search
	
	static Reservierung r5 = null; //update
	static Reservierung r6 = null; //update
	
	static Reservierung r7 = null; //delete
	static Reservierung r8 = null; //delete
	
	static ArrayList<Integer> mykeys = new ArrayList<Integer>();
	static ArrayList<Integer> mykeys_generated = new ArrayList<Integer>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ac = new ClassPathXmlApplicationContext("spring-config.xml");
		dbc = (DBConnector) ac.getBean("databaseManager");
		test = new JDBCReservierungDAO(dbc.getConnection());
		
		r1 = new Reservierung(-1, new Date(0), 3, 7, 5, "Musterfrau", "0650 41 53 994");
		r2 = new Reservierung(50, new Date(100000), 15, 2, 10, "Mustermann", "0650 12 34 56 78");
		r3 = new Reservierung(-1, new Date(0), 3, 7, 5, "Musterfrau", "0650 41 53 994");
		r4 = new Reservierung(50, new Date(100000), 15, 2, 10, "Mustermann", "0650 12 34 56 78");
		r5 = new Reservierung(-1, new Date(0), 3, 7, 5, "Musterfrau", "0650 41 53 994");
		r6 = new Reservierung(50, new Date(100000), 15, 2, 10, "Mustermann", "0650 12 34 56 78");
		r7 = new Reservierung(-1, new Date(0), 3, 7, 5, "Musterfrau", "0650 41 53 994");
		r8 = new Reservierung(50, new Date(100000), 15, 2, 10, "Mustermann", "0650 12 34 56 78");
		
		Reservierung r_test = null;
		r_test = test.create(r3);
		r3.setId(r_test.getId());
		mykeys.add(r3.getId());
		r_test = test.create(r4);
		r4.setId(r_test.getId());
		mykeys.add(r4.getId());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		for(int i = 0; i < mykeys.size();i++){
			try {
				PreparedStatement ps=dbc.getConnection().prepareStatement("DELETE FROM reservierung WHERE id=?");
				ps.setInt(1,mykeys.get(i));
				ps.executeUpdate();
			} catch (SQLException e) {
				throw new DAOException("ERROR: failed to delete Reservierung from DB!");
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
				PreparedStatement ps=dbc.getConnection().prepareStatement("DELETE FROM reservierung WHERE id=?");
				ps.setInt(1,mykeys_generated.get(i));
				ps.executeUpdate();
			} catch (SQLException e) {
				throw new DAOException("ERROR: failed to delete Reservierung from DB!");
			}
			catch (NullPointerException e) {
				throw new IllegalArgumentException();
			}
		}
	}

	@Test
	public void testCreate_1() throws DAOException {
		Reservierung r_test = test.create(r1);
		mykeys_generated.add(r_test.getId());
		
		try {
			PreparedStatement ps = dbc.getConnection().prepareStatement("SELECT id, datum, dauer, personen, tischnummer, name, telefonnummer FROM reservierung WHERE id=?");
			ps.setInt(1,r_test.getId());
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				assertThat(rs.getInt("id"), equalTo(r_test.getId()));
				assertThat(rs.getDate("datum").toString(), equalTo(r_test.getDatum().toString()));
				assertThat(rs.getInt("dauer"), equalTo(r_test.getDauer()));
				assertThat(rs.getInt("personen"), equalTo(r_test.getPersonen()));
				assertThat(rs.getInt("tischnummer"), equalTo(r_test.getTischnummer()));
				assertThat(rs.getString("name"), equalTo(r_test.getName()));
				assertThat(rs.getString("telefonnummer"), equalTo(r_test.getTelefonnummer()));
				return;
			} else {
				fail("No data found");
				return;
			}
		} catch (SQLException e) {
			fail("ERROR: failed to search DB for Reservierung");
			return;
		} catch (NullPointerException e) {
			fail("NullPointerException");
			return;
		}
	}

	@Test
	public void testCreate_2() throws DAOException {
		Reservierung r_test = test.create(r2);
		mykeys_generated.add(r_test.getId());
		
		try {
			PreparedStatement ps = dbc.getConnection().prepareStatement("SELECT id, datum, dauer, personen, tischnummer, name, telefonnummer FROM reservierung WHERE id=?");
			ps.setInt(1,r_test.getId());
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				assertThat(rs.getInt("id"), equalTo(r_test.getId()));
				assertThat(rs.getDate("datum").toString(), equalTo(r_test.getDatum().toString()));
				assertThat(rs.getInt("dauer"), equalTo(r_test.getDauer()));
				assertThat(rs.getInt("personen"), equalTo(r_test.getPersonen()));
				assertThat(rs.getInt("tischnummer"), equalTo(r_test.getTischnummer()));
				assertThat(rs.getString("name"), equalTo(r_test.getName()));
				assertThat(rs.getString("telefonnummer"), equalTo(r_test.getTelefonnummer()));
				return;
			} else {
				fail("No data found");
				return;
			}
		} catch (SQLException e) {
			fail("ERROR: failed to search DB for Reservierung");
			return;
		} catch (NullPointerException e) {
			fail("NullPointerException");
			return;
		}
	}

	@Test
	public void testSearch_1() throws DAOException {
		Reservierung toSearch = new Reservierung(r3.getId(), r3.getDatum(), r3.getDauer(), r3.getPersonen(), r3.getTischnummer(), r3.getName(), r3.getTelefonnummer());
		ArrayList<Reservierung> r_test = test.search(toSearch);
		
		for (Reservierung r : r_test) {
			assertThat(r.getId(), equalTo(r3.getId()));
			assertThat(r.getDatum().toString(), equalTo(r3.getDatum().toString()));
			assertThat(r.getDauer(), equalTo(r3.getDauer()));
			assertThat(r.getPersonen(), equalTo(r3.getPersonen()));
			assertThat(r.getTischnummer(), equalTo(r3.getTischnummer()));
			assertThat(r.getName(), equalTo(r3.getName()));
			assertThat(r.getTelefonnummer(), equalTo(r3.getTelefonnummer()));
		}
	}

	@Test
	public void testSearch_2() throws DAOException {
		Reservierung toSearch = new Reservierung(-1, r4.getDatum(), r4.getDauer(), r4.getPersonen(), r4.getTischnummer(), r4.getName(), r4.getTelefonnummer());
		ArrayList<Reservierung> r_test = test.search(toSearch);
		
		assertTrue(r_test.size() == 0);
		
		r_test = test.search(r4);
		
		for (Reservierung r : r_test) {
			assertThat(r.getId(), equalTo(r4.getId()));
			assertThat(r.getDatum().toString(), equalTo(r4.getDatum().toString()));
			assertThat(r.getDauer(), equalTo(r4.getDauer()));
			assertThat(r.getPersonen(), equalTo(r4.getPersonen()));
			assertThat(r.getTischnummer(), equalTo(r4.getTischnummer()));
			assertThat(r.getName(), equalTo(r4.getName()));
			assertThat(r.getTelefonnummer(), equalTo(r4.getTelefonnummer()));
		}
	}

	@Test
	public void testUpdate_1() throws DAOException {
		
		r5 = test.create(r5);
		mykeys_generated.add(r5.getId());
		
		Reservierung toUpdate = new Reservierung(r5.getId(), new Date(100), 3, 5, 10, "Maxmuster", "0699 1122334455");
		
		test.update(toUpdate);
		ArrayList<Reservierung> shouldBeUpdated = test.search(toUpdate);
		
		for (Reservierung r : shouldBeUpdated) {
			assertThat(r.getId(), equalTo(r5.getId()));
			
			assertThat(r.getDatum().toString(), equalTo(toUpdate.getDatum().toString()));
			assertThat(r.getDauer(), equalTo(toUpdate.getDauer()));
			assertThat(r.getPersonen(), equalTo(toUpdate.getPersonen()));
			assertThat(r.getTischnummer(), equalTo(toUpdate.getTischnummer()));
			assertThat(r.getName(), equalTo(toUpdate.getName()));
			assertThat(r.getTelefonnummer(), equalTo(toUpdate.getTelefonnummer()));
		}
	}

	@Test
	public void testUpdate_2() throws DAOException {
		
		r6 = test.create(r6);
		mykeys_generated.add(r6.getId());
		
		Reservierung toUpdate = new Reservierung(r6.getId(), null, 0, 0, 0, null, null);
		test.update(toUpdate);
		ArrayList<Reservierung> shouldBeUpdated = test.search(toUpdate);
		
		assertThat(shouldBeUpdated.size(), equalTo(0));
		
		toUpdate = new Reservierung(r6.getId(), r6.getDatum(), r6.getDauer(), r6.getPersonen(), r6.getTischnummer(), "Musterfrau", r6.getTelefonnummer());
		test.update(toUpdate);
		shouldBeUpdated = test.search(toUpdate);
		
		for (Reservierung r : shouldBeUpdated) {
			assertThat(r.getId(), equalTo(r6.getId()));
			
			assertThat(r.getDatum().toString(), equalTo(toUpdate.getDatum().toString()));
			assertThat(r.getDauer(), equalTo(toUpdate.getDauer()));
			assertThat(r.getPersonen(), equalTo(toUpdate.getPersonen()));
			assertThat(r.getTischnummer(), equalTo(toUpdate.getTischnummer()));
			assertThat(r.getName(), equalTo(toUpdate.getName()));
			assertThat(r.getTelefonnummer(), equalTo(toUpdate.getTelefonnummer()));
		}
	}

	@Test
	public void testDelete_1() throws DAOException {
		
		r7 = test.create(r7);
		mykeys_generated.add(r7.getId());
		
		ArrayList<Reservierung> r_test = test.search(r7);
		assertTrue(r_test.size() != 0);
		test.delete(r7);
		r_test = test.search(r7);
		assertTrue(r_test.size() == 0);
	}

	@Test
	public void testDelete_2() throws DAOException {
		
		r8 = test.create(r8);
		mykeys_generated.add(r8.getId());
		
		ArrayList<Reservierung> r_test = test.search(r8);
		assertTrue(r_test.size() != 0);
		test.delete(r8);
		r_test = test.search(r8);
		assertTrue(r_test.size() == 0);
	}
}
