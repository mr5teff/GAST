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
import sepm.ss13.gast.dao.JDBCProduktKategorieDAO;
import sepm.ss13.gast.domain.Produkt;
import sepm.ss13.gast.domain.ProduktKategorie;

/**
 * @author Admin
 *
 */
public class Test_JDBCProduktDAO {
	
	private static JDBCProduktDAO testDAO;
	private static DBConnector dbc;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring-config.xml");
		dbc = (DBConnector) ac.getBean("databaseManager");
		dbc.getConnection().setAutoCommit(false); 
		testDAO = new JDBCProduktDAO(dbc.getConnection());		
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
//	public void testCreate_1() throws DAOException,IllegalArgumentException {
//		/* INSERT */
//		Produkt p_test = null;
//		p_test = test.create(p1);
//		
//		mykeys_generated.add(p_test.getId());
//		
//		/* GET INSERTED */
//		try {
//			PreparedStatement ps=dbc.getConnection().prepareStatement("SELECT id,name,typid,preis,deleted FROM produkt WHERE id=?");
//			ps.setInt(1,p_test.getId());
//
//			
//			ResultSet rs=ps.executeQuery();
//			if(rs.next()) {
//				assertThat(rs.getInt("id"), equalTo(p_test.getId()));
//				assertThat(rs.getString("name"), equalTo(p1.getName()));
//				assertThat(rs.getInt("typid"), equalTo(p1.getKategorie()));
//				assertThat(rs.getInt("preis"), equalTo(p1.getPreis()));
//				assertThat(rs.getBoolean("deleted"), equalTo(p1.getDeleted()));
//				return;
//			}else{
//				fail("No Data found");
//				return;
//			}
//		} catch (SQLException e) {
//			fail("ERROR: failed to search DB for product!");
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
//	 * Test method for {@link sepm.ss13.gast.dao.JDBCProduktDAO#create(sepm.ss13.gast.domain.Produkt)}.
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
//	 * Test method for {@link sepm.ss13.gast.dao.JDBCProduktDAO#create(sepm.ss13.gast.domain.Produkt)}.
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
//	/**
//	 * Test method for {@link sepm.ss13.gast.dao.JDBCProduktDAO#create(sepm.ss13.gast.domain.Produkt)}.
//	 * @throws DAOException 
//	 */
//	@Test (expected=DAOException.class) 
//	public void testCreate_4() throws DAOException,IllegalArgumentException {
//		
//		/* INSERT */
//		test.create(p3);
//		
//	}	
//
//	
//	
//	
//	
//	
//	
//	
//	/* TESTs for SEARCH */
//	
//	/**
//	 * Test method for {@link sepm.ss13.gast.dao.JDBCProduktDAO#search(sepm.ss13.gast.domain.Produkt)}.
//	 * @throws DAOException 
//	 */
//	@Test
//	public void testSearch_1() throws DAOException {
//		//6
//		//7
//		//8
//		//9
//		
//		Produkt toSearch = new Produkt(p6.getId(),"",-1,-1,false);
//		
//		/* SEARCH */
//		ArrayList<Produkt> p_test = new ArrayList<Produkt>();
//		p_test = test.search(toSearch);
//	
//		for(int i = 0; i < p_test.size();i++){
//			assertThat(p_test.get(i).getId(), equalTo(p6.getId()));
//			assertThat(p_test.get(i).getName(), equalTo(p6.getName()));
//			assertThat(p_test.get(i).getKategorie(), equalTo(p6.getKategorie()));
//			assertThat(p_test.get(i).getPreis(), equalTo(p6.getPreis()));
//			assertThat(p_test.get(i).getDeleted(), equalTo(p6.getDeleted()));
//		}
//		
//		toSearch = new Produkt(p7.getId(),"",-1,-1,false);
//		
//		p_test = new ArrayList<Produkt>();
//		p_test = test.search(toSearch);
//	
//		for(int i = 0; i < p_test.size();i++){
//			assertThat(p_test.get(i).getId(), equalTo(p7.getId()));
//			assertThat(p_test.get(i).getName(), equalTo(p7.getName()));
//			assertThat(p_test.get(i).getKategorie(), equalTo(p7.getKategorie()));
//			assertThat(p_test.get(i).getPreis(), equalTo(p7.getPreis()));
//			assertThat(p_test.get(i).getDeleted(), equalTo(p7.getDeleted()));
//		}
//	}
//
//	/**
//	 * Test method for {@link sepm.ss13.gast.dao.JDBCProduktDAO#search(sepm.ss13.gast.domain.Produkt)}.
//	 * @throws DAOException 
//	 */
//	@Test
//	public void testSearch_2() throws DAOException {
//		//6
//		//7
//		//8
//		//9
//		
//		Produkt toSearch = new Produkt(-1,"TEST Search 4",-1,-1,false);
//		
//		/* SEARCH */
//		ArrayList<Produkt> p_test = new ArrayList<Produkt>();
//		p_test = test.search(toSearch);
//	
//		for(int i = 0; i < p_test.size();i++){
//			assertThat(p_test.get(i).getId(), equalTo(p9.getId()));
//			assertThat(p_test.get(i).getName(), equalTo(p9.getName()));
//			assertThat(p_test.get(i).getKategorie(), equalTo(p9.getKategorie()));
//			assertThat(p_test.get(i).getPreis(), equalTo(p9.getPreis()));
//			assertThat(p_test.get(i).getDeleted(), equalTo(p9.getDeleted()));
//		}
//
//	}	
//	
//	/**
//	 * Test method for {@link sepm.ss13.gast.dao.JDBCProduktDAO#search(sepm.ss13.gast.domain.Produkt)}.
//	 * @throws DAOException 
//	 */
//	@Test (expected=IllegalArgumentException.class) 
//	public void testSearch_3() throws DAOException {
//		//6
//		//7
//		//8
//		//9
//		
//		Produkt toSearch = null;
//		
//		/* SEARCH */
//		test.search(toSearch);
//
//	}	
//	
//	
//	
//	
//	
//	/* TESTs for UPDATE */
//	
//	
//	/**
//	 * Test method for {@link sepm.ss13.gast.dao.JDBCProduktDAO#update(sepm.ss13.gast.domain.Produkt)}.
//	 */
//	@Test
//	public void testUpdate_1() throws DAOException,IllegalArgumentException {
//		
//		/* INSERT */
//		Produkt p_test = null;
//		p_test = test.create(p1);
//		
//		mykeys_generated.add(p_test.getId());
//		p4.setId(p_test.getId());
//		
//		/* UPDATE */
//		test.update(p4);
//		
//		/* GET UPDATED */
//		try {
//			PreparedStatement ps = dbc.getConnection().prepareStatement("SELECT id,name,typid,preis,deleted FROM produkt WHERE id=?");
//			ps.setInt(1,p4.getId());
//			
//			ResultSet rs=ps.executeQuery();
//			if(rs.next()) {
//				assertThat(rs.getInt("id"), equalTo(p4.getId()));
//				assertThat(rs.getString("name"), equalTo(p4.getName()));
//				assertThat(rs.getInt("typid"), equalTo(p4.getKategorie()));
//				assertThat(rs.getInt("preis"), equalTo(p4.getPreis()));
//				assertThat(rs.getBoolean("deleted"), equalTo(p4.getDeleted()));
//				return;
//			}else{
//				fail("No Data found");
//				return;
//			}
//		} catch (SQLException e) {
//			fail("ERROR: failed to search DB for product!");
//			return;
//		}
//		catch (NullPointerException e) {
//			fail("NullPointerException");
//			return;
//		}
//	}
//	
//	/**
//	 * Test method for {@link sepm.ss13.gast.dao.JDBCProduktDAO#update(sepm.ss13.gast.domain.Produkt)}.
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
//		Produkt p_test = null;
//		p_test = test.create(p1);
//		
//		mykeys_generated.add(p_test.getId());
//		p5.setId(p_test.getId());
//		
//		/* UPDATE */
//		test.update(p5);
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
//		test.update(p5);
//		
//	}	
//	
//
//	
//	
//	
//	
//	/* TESTs for DELETE */
//	
//	
//	/**
//	 * Test method for {@link sepm.ss13.gast.dao.JDBCProduktDAO#delete(sepm.ss13.gast.domain.Produkt)}.
//	 */
//	@Test
//	public void testDelete_1() throws DAOException,IllegalArgumentException {
//		
//		/* INSERT */
//		Produkt p_test = null;
//		p_test = test.create(p10);
//		
//		mykeys_generated.add(p_test.getId());
//		p10.setId(p_test.getId());
//		
//		/* UPDATE */
//		test.delete(p10);
//		p10.setDeleted(true);
//		
//		/* TEST DELETED */
//		try {
//			PreparedStatement ps = dbc.getConnection().prepareStatement("SELECT id,name,typid,preis,deleted FROM produkt WHERE id=?");
//			ps.setInt(1,p10.getId());
//			
//			ResultSet rs=ps.executeQuery();
//			if(rs.next()) {
//				assertThat(rs.getInt("id"), equalTo(p10.getId()));
//				assertThat(rs.getString("name"), equalTo(p10.getName()));
//				assertThat(rs.getInt("typid"), equalTo(p10.getKategorie()));
//				assertThat(rs.getInt("preis"), equalTo(p10.getPreis()));
//				assertThat(rs.getBoolean("deleted"), equalTo(p10.getDeleted()));
//				return;
//			}else{
//				fail("No Data found");
//				return;
//			}
//		} catch (SQLException e) {
//			fail("ERROR: failed to search DB for product!");
//			return;
//		}
//		catch (NullPointerException e) {
//			fail("NullPointerException");
//			return;
//		}
//	}
//	
//	/**
//	 * Test method for {@link sepm.ss13.gast.dao.JDBCProduktDAO#update(sepm.ss13.gast.domain.Produkt)}.
//	 */
//	@Test (expected=IllegalArgumentException.class) 
//	public void testDelete_2() throws DAOException,IllegalArgumentException {
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
//	public void testDelete_4() throws DAOException,IllegalArgumentException {
//		
//		
//		/* INSERT */
//		Produkt p_test = null;
//		p_test = test.create(p11);
//		
//		mykeys_generated.add(p_test.getId());
//		p11.setId(p_test.getId());
//		
//		try {
//			PreparedStatement ps=dbc.getConnection().prepareStatement("DELETE FROM produkt WHERE id=?");
//			ps.setInt(1,p11.getId());
//			ps.executeUpdate();
//		} catch (SQLException e) {
//			fail("ERROR: failed to delete product from DB!");
//		}
//		catch (NullPointerException e) {
//			throw new IllegalArgumentException();
//		}
//		
//		test.delete(p11);
//		
//	}	
	
}
