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
	
	private static ApplicationContext ac;
	static DBConnector dbc;
	static JDBCProduktDAO test = null;
	static JDBCProduktKategorieDAO testKategorie = null;
	
	static ProduktKategorie p1_kategorie = null; //create
	static ProduktKategorie p2_kategorie = null; //create

	static Produkt p1 = null; //create
	static Produkt p2 = null; //create
	static Produkt p3 = null; //create

	static Produkt p4 = null; //update
	static Produkt p5 = null; //update
	
	static Produkt p6 = null; //serach
	static Produkt p7 = null; //serach
	static Produkt p8 = null; //serach
	static Produkt p9 = null; //serach

	static Produkt p10 = null; //update
	static Produkt p11 = null; //update
	
	static ArrayList<Integer> mykeys_Kategorie = new ArrayList<Integer>();
	static ArrayList<Integer> mykeys = new ArrayList<Integer>();
	static ArrayList<Integer> mykeys_generated = new ArrayList<Integer>();
	

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ac = new ClassPathXmlApplicationContext("spring-config.xml");
		dbc = (DBConnector) ac.getBean("databaseManager");
		test = new JDBCProduktDAO(dbc.getConnection());
		testKategorie = new JDBCProduktKategorieDAO(dbc.getConnection());
		
		p1_kategorie = new ProduktKategorie(-1,"Test 1","T 1",false,0);
		p2_kategorie = new ProduktKategorie(-1,"Test 2","T 2",false,0);
		
		ProduktKategorie p_temp_kategorie = null;
		p_temp_kategorie = testKategorie.create(p1_kategorie);
		p1_kategorie.setId(p_temp_kategorie.getId());
		p_temp_kategorie = testKategorie.create(p2_kategorie);
		p2_kategorie.setId(p_temp_kategorie.getId());
		
		
		p1 = new Produkt(-1,"TEST Schnitzel",p1_kategorie.getId(),1000,false);
		p2 = new Produkt(-1,"TEST Schnitzel",-2,0,false);
		p3 = new Produkt(-1,"TEST Schnitzel IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII",p1_kategorie.getId(),1000,false);
		
		p4 = new Produkt(-1,"TEST Update",p2_kategorie.getId(),5000,false);
		p5 = new Produkt(-1,"TEST Update IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII",p2_kategorie.getId(),5000,false);
	
		p6 = new Produkt(-1,"TEST Search 1",p1_kategorie.getId(),100,false);
		p7 = new Produkt(-1,"TEST Search 2",p1_kategorie.getId(),200,false);
		p8 = new Produkt(-1,"TEST Search 3",p2_kategorie.getId(),1000,false);
		p9 = new Produkt(-1,"TEST Search 4",p2_kategorie.getId(),2000,false);

		p10 = new Produkt(-1,"TEST Delete",p2_kategorie.getId(),5000,false);
		p11 = new Produkt(-1,"TEST Delete IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII",p2_kategorie.getId(),5000,false);
	
		
		Produkt p_temp = null;
		p_temp = test.create(p6);
		p6.setId(p_temp.getId());
		mykeys.add(p_temp.getId());		
		p_temp = test.create(p7);
		p7.setId(p_temp.getId());
		mykeys.add(p_temp.getId());
		p_temp = test.create(p8);
		p8.setId(p_temp.getId());
		mykeys.add(p_temp.getId());
		p_temp = test.create(p9);
		p9.setId(p_temp.getId());
		mykeys.add(p_temp.getId());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		for(int i = 0; i < mykeys.size();i++){
			try {
				PreparedStatement ps=dbc.getConnection().prepareStatement("DELETE FROM produkt WHERE id=?");
				ps.setInt(1,mykeys.get(i));
				ps.executeUpdate();
			} catch (SQLException e) {
				throw new DAOException("ERROR: failed to delete product from DB!");
			}
			catch (NullPointerException e) {
				throw new IllegalArgumentException();
			}
		}
		testKategorie.delete(p1_kategorie);
		testKategorie.delete(p2_kategorie);
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		for(int i = 0; i < mykeys_generated.size();i++){
			try {
				PreparedStatement ps=dbc.getConnection().prepareStatement("DELETE FROM produkt WHERE id=?");
				ps.setInt(1,mykeys_generated.get(i));
				ps.executeUpdate();
			} catch (SQLException e) {
				throw new DAOException("ERROR: failed to delete product from DB!");
			}
			catch (NullPointerException e) {
				throw new IllegalArgumentException();
			}
		}
	}

	

	/* 
	 * TESTs for CREATE 
	 */

	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCProduktDAO#create(sepm.ss13.gast.domain.Produkt)}.
	 */
	@Test
	public void testCreate_1() throws DAOException,IllegalArgumentException {
		/* INSERT */
		Produkt p_test = null;
		p_test = test.create(p1);
		
		mykeys_generated.add(p_test.getId());
		
		/* GET INSERTED */
		try {
			PreparedStatement ps=dbc.getConnection().prepareStatement("SELECT id,name,typid,preis,deleted FROM produkt WHERE id=?");
			ps.setInt(1,p_test.getId());

			
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				assertThat(rs.getInt("id"), equalTo(p_test.getId()));
				assertThat(rs.getString("name"), equalTo(p1.getName()));
				assertThat(rs.getInt("typid"), equalTo(p1.getKategorie()));
				assertThat(rs.getInt("preis"), equalTo(p1.getPreis()));
				assertThat(rs.getBoolean("deleted"), equalTo(p1.getDeleted()));
				return;
			}else{
				fail("No Data found");
				return;
			}
		} catch (SQLException e) {
			fail("ERROR: failed to search DB for product!");
			return;
		}
		catch (NullPointerException e) {
			fail("NullPointerException");
			return;
		}
	}
	
	
	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCProduktDAO#create(sepm.ss13.gast.domain.Produkt)}.
	 * @throws DAOException 
	 */
	@Test (expected=IllegalArgumentException.class) 
	public void testCreate_2() throws DAOException,IllegalArgumentException {
		
		/* INSERT */
		test.create(null);
		
	}
	
	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCProduktDAO#create(sepm.ss13.gast.domain.Produkt)}.
	 * @throws DAOException 
	 */
	@Test (expected=DAOException.class) 
	public void testCreate_3() throws DAOException,IllegalArgumentException {
		
		/* INSERT */
		test.create(p2);
		
	}	
	
	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCProduktDAO#create(sepm.ss13.gast.domain.Produkt)}.
	 * @throws DAOException 
	 */
	@Test (expected=DAOException.class) 
	public void testCreate_4() throws DAOException,IllegalArgumentException {
		
		/* INSERT */
		test.create(p3);
		
	}	

	
	
	
	
	
	
	
	/* TESTs for SEARCH */
	
	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCProduktDAO#search(sepm.ss13.gast.domain.Produkt)}.
	 * @throws DAOException 
	 */
	@Test
	public void testSearch_1() throws DAOException {
		//6
		//7
		//8
		//9
		
		Produkt toSearch = new Produkt(p6.getId(),"",-1,-1,false);
		
		/* SEARCH */
		ArrayList<Produkt> p_test = new ArrayList<Produkt>();
		p_test = test.search(toSearch);
	
		for(int i = 0; i < p_test.size();i++){
			assertThat(p_test.get(i).getId(), equalTo(p6.getId()));
			assertThat(p_test.get(i).getName(), equalTo(p6.getName()));
			assertThat(p_test.get(i).getKategorie(), equalTo(p6.getKategorie()));
			assertThat(p_test.get(i).getPreis(), equalTo(p6.getPreis()));
			assertThat(p_test.get(i).getDeleted(), equalTo(p6.getDeleted()));
		}
		
		toSearch = new Produkt(p7.getId(),"",-1,-1,false);
		
		p_test = new ArrayList<Produkt>();
		p_test = test.search(toSearch);
	
		for(int i = 0; i < p_test.size();i++){
			assertThat(p_test.get(i).getId(), equalTo(p7.getId()));
			assertThat(p_test.get(i).getName(), equalTo(p7.getName()));
			assertThat(p_test.get(i).getKategorie(), equalTo(p7.getKategorie()));
			assertThat(p_test.get(i).getPreis(), equalTo(p7.getPreis()));
			assertThat(p_test.get(i).getDeleted(), equalTo(p7.getDeleted()));
		}
	}

	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCProduktDAO#search(sepm.ss13.gast.domain.Produkt)}.
	 * @throws DAOException 
	 */
	@Test
	public void testSearch_2() throws DAOException {
		//6
		//7
		//8
		//9
		
		Produkt toSearch = new Produkt(-1,"TEST Search 4",-1,-1,false);
		
		/* SEARCH */
		ArrayList<Produkt> p_test = new ArrayList<Produkt>();
		p_test = test.search(toSearch);
	
		for(int i = 0; i < p_test.size();i++){
			assertThat(p_test.get(i).getId(), equalTo(p9.getId()));
			assertThat(p_test.get(i).getName(), equalTo(p9.getName()));
			assertThat(p_test.get(i).getKategorie(), equalTo(p9.getKategorie()));
			assertThat(p_test.get(i).getPreis(), equalTo(p9.getPreis()));
			assertThat(p_test.get(i).getDeleted(), equalTo(p9.getDeleted()));
		}

	}	
	
	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCProduktDAO#search(sepm.ss13.gast.domain.Produkt)}.
	 * @throws DAOException 
	 */
	@Test (expected=IllegalArgumentException.class) 
	public void testSearch_3() throws DAOException {
		//6
		//7
		//8
		//9
		
		Produkt toSearch = null;
		
		/* SEARCH */
		test.search(toSearch);

	}	
	
	
	
	
	
	/* TESTs for UPDATE */
	
	
	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCProduktDAO#update(sepm.ss13.gast.domain.Produkt)}.
	 */
	@Test
	public void testUpdate_1() throws DAOException,IllegalArgumentException {
		
		/* INSERT */
		Produkt p_test = null;
		p_test = test.create(p1);
		
		mykeys_generated.add(p_test.getId());
		p4.setId(p_test.getId());
		
		/* UPDATE */
		test.update(p4);
		
		/* GET UPDATED */
		try {
			PreparedStatement ps = dbc.getConnection().prepareStatement("SELECT id,name,typid,preis,deleted FROM produkt WHERE id=?");
			ps.setInt(1,p4.getId());
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				assertThat(rs.getInt("id"), equalTo(p4.getId()));
				assertThat(rs.getString("name"), equalTo(p4.getName()));
				assertThat(rs.getInt("typid"), equalTo(p4.getKategorie()));
				assertThat(rs.getInt("preis"), equalTo(p4.getPreis()));
				assertThat(rs.getBoolean("deleted"), equalTo(p4.getDeleted()));
				return;
			}else{
				fail("No Data found");
				return;
			}
		} catch (SQLException e) {
			fail("ERROR: failed to search DB for product!");
			return;
		}
		catch (NullPointerException e) {
			fail("NullPointerException");
			return;
		}
	}
	
	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCProduktDAO#update(sepm.ss13.gast.domain.Produkt)}.
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
		Produkt p_test = null;
		p_test = test.create(p1);
		
		mykeys_generated.add(p_test.getId());
		p5.setId(p_test.getId());
		
		/* UPDATE */
		test.update(p5);
		
	}	
	
	/**
	 * Test method for {sepm.ss13.gast.dao.JDBCProduktKategorieDAO}.
	 * @throws DAOException 
	 */
	@Test (expected=DAOException.class) 
	public void testUpdate_4() throws DAOException,IllegalArgumentException {
		
		/* UPDATE */
		test.update(p5);
		
	}	
	

	
	
	
	
	/* TESTs for DELETE */
	
	
	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCProduktDAO#delete(sepm.ss13.gast.domain.Produkt)}.
	 */
	@Test
	public void testDelete_1() throws DAOException,IllegalArgumentException {
		
		/* INSERT */
		Produkt p_test = null;
		p_test = test.create(p10);
		
		mykeys_generated.add(p_test.getId());
		p10.setId(p_test.getId());
		
		/* UPDATE */
		test.delete(p10);
		p10.setDeleted(true);
		
		/* TEST DELETED */
		try {
			PreparedStatement ps = dbc.getConnection().prepareStatement("SELECT id,name,typid,preis,deleted FROM produkt WHERE id=?");
			ps.setInt(1,p10.getId());
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				assertThat(rs.getInt("id"), equalTo(p10.getId()));
				assertThat(rs.getString("name"), equalTo(p10.getName()));
				assertThat(rs.getInt("typid"), equalTo(p10.getKategorie()));
				assertThat(rs.getInt("preis"), equalTo(p10.getPreis()));
				assertThat(rs.getBoolean("deleted"), equalTo(p10.getDeleted()));
				return;
			}else{
				fail("No Data found");
				return;
			}
		} catch (SQLException e) {
			fail("ERROR: failed to search DB for product!");
			return;
		}
		catch (NullPointerException e) {
			fail("NullPointerException");
			return;
		}
	}
	
	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCProduktDAO#update(sepm.ss13.gast.domain.Produkt)}.
	 */
	@Test (expected=IllegalArgumentException.class) 
	public void testDelete_2() throws DAOException,IllegalArgumentException {
		
		/* INSERT */
		test.update(null);
		
	}
	
	/**
	 * Test method for {sepm.ss13.gast.dao.JDBCProduktKategorieDAO}.
	 * @throws DAOException 
	 */
	@Test (expected=DAOException.class) 
	public void testDelete_4() throws DAOException,IllegalArgumentException {
		
		
		/* INSERT */
		Produkt p_test = null;
		p_test = test.create(p11);
		
		mykeys_generated.add(p_test.getId());
		p11.setId(p_test.getId());
		
		try {
			PreparedStatement ps=dbc.getConnection().prepareStatement("DELETE FROM produkt WHERE id=?");
			ps.setInt(1,p11.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			fail("ERROR: failed to delete product from DB!");
		}
		catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
		
		test.delete(p11);
		
	}	
	
}
