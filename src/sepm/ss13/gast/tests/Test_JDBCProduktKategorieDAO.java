package sepm.ss13.gast.tests;

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
	
	private static ApplicationContext ac;
	static DBConnector dbc;
	static JDBCProduktKategorieDAO test = null;
	
	static ProduktKategorie p1 = null; //create
	static ProduktKategorie p2 = null; //create
	
	static ProduktKategorie p3 = null; //serach
	static ProduktKategorie p4 = null; //serach

	static ProduktKategorie p5 = null; //update
	static ProduktKategorie p6 = null; //update

	static ProduktKategorie p7 = null; //update
	static ProduktKategorie p8 = null; //update
	
	
	static ArrayList<Integer> mykeys = new ArrayList<Integer>();
	static ArrayList<Integer> mykeys_generated = new ArrayList<Integer>();
	

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ac = new ClassPathXmlApplicationContext("spring-config.xml");
		dbc = (DBConnector) ac.getBean("databaseManager");
		test = new JDBCProduktKategorieDAO(dbc.getConnection());
		
		
		p1 = new ProduktKategorie(-1,"Test ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890ßÄÖÜäöü","T ABCDEFGHÄÜÖ");
		p2 = new ProduktKategorie(-1,"Test ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890ßÄÖÜäöüasdafsdfasdfasdfasdfasdfasdfasdfasdf","T ABCDEFGHasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfsad");
		
		
		p3 = new ProduktKategorie(-1,"Test SEARCH 1","T SE 1");
		p4 = new ProduktKategorie(-1,"Test SEARCH 2","T SE 2");
		
		p5 = new ProduktKategorie(-1,"Test UPDATE 1","T UP 1");
		p6 = new ProduktKategorie(-1,"Test ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890ßÄÖÜäöüasdafsdfasdfasdfasdfasdfasdfasdfasdf","T ABCDEFGHasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfsad");
		
		p7 = new ProduktKategorie(-1,"Test DELETE 1","T DEL 1");
		p8 = new ProduktKategorie(-1,"Test DELETE 2","T DEL 2");
		
		
		ProduktKategorie p_temp = null;
		p_temp = test.create(p3);
		p3.setId(p_temp.getId());
		mykeys.add(p_temp.getId());		
		p_temp = test.create(p4);
		p4.setId(p_temp.getId());
		mykeys.add(p_temp.getId());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		for(int i = 0; i < mykeys.size();i++){
			try {
				PreparedStatement ps=dbc.getConnection().prepareStatement("DELETE FROM produkttyp WHERE id=?");
				ps.setInt(1,mykeys.get(i));
				ps.executeUpdate();
			} catch (SQLException e) {
				throw new DAOException("ERROR: failed to delete product category from DB!");
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
				PreparedStatement ps=dbc.getConnection().prepareStatement("DELETE FROM produkttyp WHERE id=?");
				ps.setInt(1,mykeys_generated.get(i));
				ps.executeUpdate();
			} catch (SQLException e) {
				throw new DAOException("ERROR: failed to delete product category from DB!");
			}
			catch (NullPointerException e) {
				throw new IllegalArgumentException();
			}
		}
	}

	
	

	/*
	 *  TESTs for CREATE
	 */
	
	/**
	 * Test method for {sepm.ss13.gast.dao.JDBCProduktKategorieDAO}.
	 * @throws DAOException 
	 */
	@Test
	public void testCreate_1() throws DAOException,IllegalArgumentException {
		
		/* INSERT */
		ProduktKategorie p_test = null;
		p_test = test.create(p1);
		
		mykeys_generated.add(p_test.getId());
		
		/* GET INSERTED */
		try {
			PreparedStatement ps = dbc.getConnection().prepareStatement("SELECT id,bezeichnung,kurzbezeichnung FROM produkttyp WHERE id=?");
			ps.setInt(1,p_test.getId());
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				assertThat(rs.getInt("id"), equalTo(p_test.getId()));
				assertThat(rs.getString("bezeichnung"), equalTo(p1.getBezeichnung()));
				assertThat(rs.getString("kurzbezeichnung"), equalTo(p1.getKurzbezeichnung()));
				return;
			}else{
				fail("No Data found");
				return;
			}
		} catch (SQLException e) {
			fail("ERROR: failed to search DB for product categories!");
			return;
		}
		catch (NullPointerException e) {
			fail("NullPointerException");
			return;
		}
	}
	
	/**
	 * Test method for {sepm.ss13.gast.dao.JDBCProduktKategorieDAO}.
	 * @throws DAOException 
	 */
	@Test (expected=IllegalArgumentException.class) 
	public void testCreate_2() throws DAOException,IllegalArgumentException {
		
		/* INSERT */
		test.create(null);
		
	}
	
	/**
	 * Test method for {sepm.ss13.gast.dao.JDBCProduktKategorieDAO}.
	 * @throws DAOException 
	 */
	@Test (expected=DAOException.class) 
	public void testCreate_3() throws DAOException,IllegalArgumentException {
		
		/* INSERT */
		test.create(p2);
		
	}	
	
	
	
	/*
	 *  TESTs for SEARCH
	 */
	
	

	/**
	 * Test method for {sepm.ss13.gast.dao.JDBCProduktKategorieDAO}.
	 * @throws DAOException 
	 */
	@Test
	public void testSearch_1() throws DAOException,IllegalArgumentException {
		
		/* INSERT */
		ArrayList<ProduktKategorie> p_test = new ArrayList<ProduktKategorie>();
		p_test = test.search(p3);
	
		for(int i = 0; i < p_test.size();i++){
			assertThat(p_test.get(i).getId(), equalTo(p3.getId()));
			assertThat(p_test.get(i).getBezeichnung(), equalTo(p3.getBezeichnung()));
			assertThat(p_test.get(i).getKurzbezeichnung(), equalTo(p3.getKurzbezeichnung()));
		}
		
		p_test = new ArrayList<ProduktKategorie>();
		p_test = test.search(p4);
	
		for(int i = 0; i < p_test.size();i++){
			assertThat(p_test.get(i).getId(), equalTo(p4.getId()));
			assertThat(p_test.get(i).getBezeichnung(), equalTo(p4.getBezeichnung()));
			assertThat(p_test.get(i).getKurzbezeichnung(), equalTo(p4.getKurzbezeichnung()));
		}
	}
	
	/**
	 * Test method for {sepm.ss13.gast.dao.JDBCProduktKategorieDAO}.
	 * @throws DAOException 
	 */
	@Test (expected=IllegalArgumentException.class) 
	public void testSearch_2() throws DAOException,IllegalArgumentException {
		
		/* INSERT */
		test.search(null);
		
	}
	
	
	/*
	 * TESTs for update
	 */
	
	/**
	 * Test method for {sepm.ss13.gast.dao.JDBCProduktKategorieDAO}.
	 * @throws DAOException 
	 */
	@Test
	public void testUpdate_1() throws DAOException,IllegalArgumentException {
		
		/* INSERT */
		ProduktKategorie p_test = null;
		p_test = test.create(p1);
		
		mykeys_generated.add(p_test.getId());
		p5.setId(p_test.getId());
		
		/* UPDATE */
		test.update(p5);
		
		/* GET UPDATED */
		try {
			PreparedStatement ps = dbc.getConnection().prepareStatement("SELECT id,bezeichnung,kurzbezeichnung FROM produkttyp WHERE id=?");
			ps.setInt(1,p5.getId());
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				assertThat(rs.getInt("id"), equalTo(p5.getId()));
				assertThat(rs.getString("bezeichnung"), equalTo(p5.getBezeichnung()));
				assertThat(rs.getString("kurzbezeichnung"), equalTo(p5.getKurzbezeichnung()));
				return;
			}else{
				fail("No Data found");
				return;
			}
		} catch (SQLException e) {
			fail("ERROR: failed to search DB for product categories!");
			return;
		}
		catch (NullPointerException e) {
			fail("NullPointerException");
			return;
		}
	}
	
	/**
	 * Test method for {sepm.ss13.gast.dao.JDBCProduktKategorieDAO}.
	 * @throws DAOException 
	 */
	@Test (expected=IllegalArgumentException.class) 
	public void testUpdate_2() throws DAOException,IllegalArgumentException {
		
		/* INSERT */
		test.update(null);
		
	}
	
	/**
	 * Test method for {sepm.ss13.gast.dao.JDBCProduktKategorieDAO}.
	 * @throws DAOException 
	 */
	@Test (expected=DAOException.class) 
	public void testUpdate_3() throws DAOException,IllegalArgumentException {
		
		/* INSERT */
		ProduktKategorie p_test = null;
		p_test = test.create(p1);
		
		mykeys_generated.add(p_test.getId());
		p6.setId(p_test.getId());
		
		/* UPDATE */
		test.update(p6);
		
	}	
	
	/**
	 * Test method for {sepm.ss13.gast.dao.JDBCProduktKategorieDAO}.
	 * @throws DAOException 
	 */
	@Test (expected=DAOException.class) 
	public void testUpdate_4() throws DAOException,IllegalArgumentException {
		
		/* UPDATE */
		test.update(p6);
		
	}	
	
	
	

	/*
	 *  TESTs for DELTE
	 */
	
	/**
	 * Test method for {sepm.ss13.gast.dao.JDBCProduktKategorieDAO}.
	 * @throws DAOException 
	 */
	@Test
	public void testDelete_1() throws DAOException,IllegalArgumentException {
		
		/* INSERT */
		ProduktKategorie p_test = null;
		p_test = test.create(p7);
		
		mykeys_generated.add(p_test.getId());
		p7.setId(p_test.getId());
		
		test.delete(p7);
	}
	
	/**
	 * Test method for {sepm.ss13.gast.dao.JDBCProduktKategorieDAO}.
	 * @throws DAOException 
	 */
	@Test (expected=IllegalArgumentException.class) 
	public void testDelete_2() throws DAOException,IllegalArgumentException {
		
		/* INSERT */
		test.delete(null);
		
	}
	
	/**
	 * Test method for {sepm.ss13.gast.dao.JDBCProduktKategorieDAO}.
	 * @throws DAOException 
	 */
	@Test
	public void testDelete_3() throws DAOException,IllegalArgumentException {
		
		
		/* INSERT */
		ProduktKategorie p_test = null;
		p_test = test.create(p8);
		
		mykeys_generated.add(p_test.getId());
		p8.setId(p_test.getId());
		
		try {
			PreparedStatement ps=dbc.getConnection().prepareStatement("DELETE FROM produkttyp WHERE id=?");
			ps.setInt(1,p8.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			fail("ERROR: failed to delete product category from DB!");
		}
		catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
		
		test.delete(p8);
		
	}	
	 
	

}
