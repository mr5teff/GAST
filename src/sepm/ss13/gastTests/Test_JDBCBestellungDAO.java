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
import sepm.ss13.gast.domain.Bestellung;
import sepm.ss13.gast.domain.Produkt;
import sepm.ss13.gast.domain.ProduktKategorie;
import sepm.ss13.gast.domain.Rechnung;

/**
 * @author Viper
 *
 */
public class Test_JDBCBestellungDAO {
	
	private static ApplicationContext ac;
	static DBConnector dbc;
	static JDBCBestellungDAO test = null;
	static JDBCProduktDAO testProdukt = null;
	static JDBCProduktKategorieDAO testKategorie = null;
	static JDBCRechnungDAO testRechnung = null;
	
	static ProduktKategorie p1_kategorie = null; //create
	static ProduktKategorie p2_kategorie = null; //create
	
	static Produkt p1_produkt = null; //create
	static Produkt p2_produkt = null; //create
	static Produkt p3_produkt = null; //create
	
	static Rechnung p1_rechnung = null; //create
	static Rechnung p2_rechnung = null; //create

	static Bestellung p1 = null; //create
	static Bestellung p2 = null; //create
	static Bestellung p3 = null; //create
	
	static Bestellung p4 = null; //serach
	static Bestellung p5 = null; //serach
	static Bestellung p6 = null; //serach
	static Bestellung p7 = null; //serach

	static Bestellung p8 = null; //delete
	static Bestellung p9 = null; //delete
	
	static ArrayList<Integer> mykeys_Rechnung = new ArrayList<Integer>();
	static ArrayList<Integer> mykeys_Produkt = new ArrayList<Integer>();
	
	static ArrayList<Integer> mykeys = new ArrayList<Integer>();
	static ArrayList<Integer> mykeys_generated = new ArrayList<Integer>();
	

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ac = new ClassPathXmlApplicationContext("spring-config.xml");
		dbc = (DBConnector) ac.getBean("databaseManager");
		test = new JDBCBestellungDAO(dbc.getConnection());
		testProdukt = new JDBCProduktDAO(dbc.getConnection());
		testKategorie = new JDBCProduktKategorieDAO(dbc.getConnection());
		testRechnung = new JDBCRechnungDAO(dbc.getConnection());
		
		p1_kategorie = new ProduktKategorie(-1,"Test 1","T 1");
		p2_kategorie = new ProduktKategorie(-1,"Test 2","T 2");
		
		
		ProduktKategorie p_temp_kategorie = null;
		p_temp_kategorie = testKategorie.create(p1_kategorie);
		p1_kategorie.setId(p_temp_kategorie.getId());
		p_temp_kategorie = testKategorie.create(p2_kategorie);
		p2_kategorie.setId(p_temp_kategorie.getId());
		
		p1_produkt = new Produkt(-1,"TEST 1",p1_kategorie.getId(),1000,false);
		p2_produkt = new Produkt(-1,"TEST 2",p2_kategorie.getId(),2000,false);
		p3_produkt = new Produkt(-1,"TEST 3",p1_kategorie.getId(),2000,true);
		
		Produkt p_temp_produkt = null;
		p_temp_produkt = testProdukt.create(p1_produkt);
		p1_produkt.setId(p_temp_produkt.getId());
		mykeys_Produkt.add(p_temp_produkt.getId());	
		p_temp_produkt = testProdukt.create(p2_produkt);
		p2_produkt.setId(p_temp_produkt.getId());
		mykeys_Produkt.add(p_temp_produkt.getId());	
		p_temp_produkt = testProdukt.create(p3_produkt);
		p3_produkt.setId(p_temp_produkt.getId());
		mykeys_Produkt.add(p_temp_produkt.getId());	
		
		
		p1_rechnung = new Rechnung(-1, new Date(),10,null);
		p2_rechnung = new Rechnung(-1, new Date(),20,null);
		
		Rechnung p_temp_rechnung = null;
		p_temp_rechnung = testRechnung.create(p1_rechnung);
		p1_rechnung.setId(p_temp_rechnung.getId());
		mykeys_Rechnung.add(p_temp_rechnung.getId());		
		p_temp_rechnung = testRechnung.create(p2_rechnung);
		p2_rechnung.setId(p_temp_rechnung.getId());
		mykeys_Rechnung.add(p_temp_rechnung.getId());
		

		//Bestellung(int id, int tisch, int produkt, int preis, int rechnung, String status)
		p1 = new Bestellung(-1,1, p1_produkt.getId(), p1_produkt.getName(), p1_produkt.getPreis(), null, "bestellt");
		p2 = new Bestellung(-1,1, p1_produkt.getId(), p1_produkt.getName(),  p1_produkt.getPreis(), p1_rechnung.getId(), "bezahlt");
		p3 = new Bestellung(-1,1, p2_produkt.getId(), p2_produkt.getName(),  p2_produkt.getPreis(), 0, "bezahlt");
		
		p4 = new Bestellung(-1,-200, p1_produkt.getId(), p1_produkt.getName(),  p1_produkt.getPreis(), null, "bestellt");
		p5 = new Bestellung(-1,-300, p1_produkt.getId(), p1_produkt.getName(),  p1_produkt.getPreis(), null, "wirdGekocht");
		p6 = new Bestellung(-1,-400, p2_produkt.getId(), p2_produkt.getName(),  p2_produkt.getPreis(), null, "fertigGekocht");
		p7 = new Bestellung(-1,-500, p2_produkt.getId(), p2_produkt.getName(),  p2_produkt.getPreis(), null, "geliefert");
		
		Bestellung p_temp = null;
		p_temp = test.create(p4);
		p4.setId(p_temp.getId());
		mykeys.add(p_temp.getId());	
		p_temp = test.create(p5);
		p5.setId(p_temp.getId());
		mykeys.add(p_temp.getId());	
		p_temp = test.create(p6);
		p6.setId(p_temp.getId());
		mykeys.add(p_temp.getId());	
		p_temp = test.create(p7);
		p7.setId(p_temp.getId());
		mykeys.add(p_temp.getId());	

		p8 = new Bestellung(-1,1, p2_produkt.getId(), p2_produkt.getName() ,  p2_produkt.getPreis(), null, "bestellt");
		p9 = new Bestellung(-1,1, p2_produkt.getId(), p2_produkt.getName(),  p2_produkt.getPreis(), p2_rechnung.getId(), "bezahlt");
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		for(int i = 0; i < mykeys.size();i++){
			try {
				PreparedStatement ps=dbc.getConnection().prepareStatement("DELETE FROM bestellung WHERE id=?");
				ps.setInt(1,mykeys.get(i));
				ps.executeUpdate();
			} catch (SQLException e) {
				throw new DAOException("ERROR: failed to delete bestellung from DB!");
			}
			catch (NullPointerException e) {
				throw new IllegalArgumentException();
			}
		}
		for(int i = 0; i < mykeys_Produkt.size();i++){
			try {
				PreparedStatement ps=dbc.getConnection().prepareStatement("DELETE FROM produkt WHERE id=?");
				ps.setInt(1,mykeys_Produkt.get(i));
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
		
		for(int i = 0; i < mykeys_Rechnung.size();i++){
			try {
				PreparedStatement ps=dbc.getConnection().prepareStatement("DELETE FROM rechnung WHERE id=?");
				ps.setInt(1,mykeys_Rechnung.get(i));
				ps.executeUpdate();
			} catch (SQLException e) {
				throw new DAOException("ERROR: failed to delete rechnung from DB!");
			}
			catch (NullPointerException e) {
				throw new IllegalArgumentException();
			}
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		for(int i = 0; i < mykeys_generated.size();i++){
			try {
				PreparedStatement ps=dbc.getConnection().prepareStatement("DELETE FROM bestellung WHERE id=?");
				ps.setInt(1,mykeys_generated.get(i));
				ps.executeUpdate();
			} catch (SQLException e) {
				throw new DAOException("ERROR: failed to delete bestellung from DB!");
			}
			catch (NullPointerException e) {
				throw new IllegalArgumentException();
			}
		}
	}

	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCBestellungDAO#create(sepm.ss13.gast.domain.Bestellung)}.
	 * @throws DAOException 
	 */
	@Test
	public void testCreate_1() throws DAOException {
		
		/* INSERT */
		Bestellung p_test = null;
		p_test = test.create(p1);
		
		mykeys_generated.add(p_test.getId());
		
		/* GET INSERTED */
		try {
			PreparedStatement ps = dbc.getConnection().prepareStatement("SELECT id,tischnummer,produktid,preis,rechnungid,status,deleted FROM bestellung WHERE id = ?");
			ps.setInt(1,p_test.getId());
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				assertThat(rs.getInt("id"), equalTo(p_test.getId()));
				assertThat(rs.getInt("tischnummer"), equalTo(p1.getTisch()));
				assertThat(rs.getInt("produktid"), equalTo(p1.getProdukt()));
				assertThat(rs.getInt("preis"), equalTo(p1.getPreis()));
				assertThat(rs.getInt("rechnungid"), equalTo(0));
				assertThat(rs.getString("status"), equalTo(p1.getStatus()));
				assertThat(rs.getBoolean("deleted"), equalTo(p1.getDeleted()));
				return;
			}else{
				fail("No Data found");
				return;
			}
		} catch (SQLException e) {
			fail("ERROR: failed to search DB for bestellung!");
			return;
		}
		catch (NullPointerException e) {
			fail("NullPointerException");
			return;
		}
	}

	

	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCBestellungDAO#create(sepm.ss13.gast.domain.Bestellung)}.
	 * @throws DAOException 
	 */
	@Test
	public void testCreate_2() throws DAOException {
		
		/* INSERT */
		Bestellung p_test = null;
		p_test = test.create(p2);
		
		mykeys_generated.add(p_test.getId());
		
		/* GET INSERTED */
		try {
			PreparedStatement ps = dbc.getConnection().prepareStatement("SELECT id,tischnummer,produktid,preis,rechnungid,status,deleted FROM bestellung WHERE id = ?");
			ps.setInt(1,p_test.getId());
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				assertThat(rs.getInt("id"), equalTo(p_test.getId()));
				assertThat(rs.getInt("tischnummer"), equalTo(p2.getTisch()));
				assertThat(rs.getInt("produktid"), equalTo(p2.getProdukt()));
				assertThat(rs.getInt("preis"), equalTo(p2.getPreis()));
				assertThat(rs.getInt("rechnungid"), equalTo(p2.getRechnung()));
				assertThat(rs.getString("status"), equalTo(p2.getStatus()));
				assertThat(rs.getBoolean("deleted"), equalTo(p2.getDeleted()));
				return;
			}else{
				fail("No Data found");
				return;
			}
		} catch (SQLException e) {
			fail("ERROR: failed to search DB for bestellung!");
			return;
		}
		catch (NullPointerException e) {
			fail("NullPointerException");
			return;
		}
	}
	

	

	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCBestellungDAO#create(sepm.ss13.gast.domain.Bestellung)}.
	 * @throws DAOException 
	 */
	@Test (expected=DAOException.class) 
	public void testCreate_3() throws DAOException {
		
		/* INSERT */
		Bestellung p_test = null;
		p_test = test.create(p3);
		
		mykeys_generated.add(p_test.getId());
		
	}
	

	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCBestellungDAO#create(sepm.ss13.gast.domain.Bestellung)}.
	 * @throws DAOException 
	 */
	@Test (expected=IllegalArgumentException.class) 
	public void testCreate_4() throws DAOException {
		
		/* INSERT */
		test.create(null);
		
	}
	
	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCBestellungDAO#search(sepm.ss13.gast.domain.Bestellung)}.
	 */
	@Test
	public void testSearch_1() throws DAOException,IllegalArgumentException {
		
		//Bestellung(int id, int tisch, int produkt, int preis, int rechnung, String status)
		Bestellung toSearch = new Bestellung(-1,-200,-1,"",-1,null,"-1");
		ArrayList<Bestellung> p_test = new ArrayList<Bestellung>();
		p_test = test.search(toSearch);
	
		for(int i = 0; i < p_test.size();i++){
			assertThat(p_test.get(i).getId(), equalTo(p4.getId()));
			assertThat(p_test.get(i).getTisch(), equalTo(p4.getTisch()));
			assertThat(p_test.get(i).getProdukt(), equalTo(p4.getProdukt()));
			assertThat(p_test.get(i).getPreis(), equalTo(p4.getPreis()));
			assertThat(p_test.get(i).getRechnung(), equalTo(null));
			assertThat(p_test.get(i).getStatus(), equalTo(p4.getStatus()));
			assertThat(p_test.get(i).getDeleted(), equalTo(p4.getDeleted()));
		}

		toSearch = new Bestellung(-1,-300,-1,"",-1,null,"wirdGekocht");
		p_test = new ArrayList<Bestellung>();
		p_test = test.search(toSearch);
	
		for(int i = 0; i < p_test.size();i++){
			assertThat(p_test.get(i).getId(), equalTo(p5.getId()));
			assertThat(p_test.get(i).getTisch(), equalTo(p5.getTisch()));
			assertThat(p_test.get(i).getProdukt(), equalTo(p5.getProdukt()));
			assertThat(p_test.get(i).getPreis(), equalTo(p5.getPreis()));
			assertThat(p_test.get(i).getRechnung(), equalTo(null));
			assertThat(p_test.get(i).getStatus(), equalTo(p5.getStatus()));
			assertThat(p_test.get(i).getDeleted(), equalTo(p5.getDeleted()));
		}
		
		toSearch = new Bestellung(p6.getId(),-1,-1,"",-1,null,"-1");
		p_test = new ArrayList<Bestellung>();
		p_test = test.search(toSearch);
	
		for(int i = 0; i < p_test.size();i++){
			assertThat(p_test.get(i).getId(), equalTo(p6.getId()));
			assertThat(p_test.get(i).getTisch(), equalTo(p6.getTisch()));
			assertThat(p_test.get(i).getProdukt(), equalTo(p6.getProdukt()));
			assertThat(p_test.get(i).getPreis(), equalTo(p6.getPreis()));
			assertThat(p_test.get(i).getRechnung(), equalTo(null));
			assertThat(p_test.get(i).getStatus(), equalTo(p6.getStatus()));
			assertThat(p_test.get(i).getDeleted(), equalTo(p6.getDeleted()));
		}
		
		toSearch = new Bestellung(p7.getId(),-500,-1,"",-1,null,"geliefert");
		p_test = new ArrayList<Bestellung>();
		p_test = test.search(toSearch);
	
		for(int i = 0; i < p_test.size();i++){
			assertThat(p_test.get(i).getId(), equalTo(p7.getId()));
			assertThat(p_test.get(i).getTisch(), equalTo(p7.getTisch()));
			assertThat(p_test.get(i).getProdukt(), equalTo(p7.getProdukt()));
			assertThat(p_test.get(i).getPreis(), equalTo(p7.getPreis()));
			assertThat(p_test.get(i).getRechnung(), equalTo(null));
			assertThat(p_test.get(i).getStatus(), equalTo(p7.getStatus()));
			assertThat(p_test.get(i).getDeleted(), equalTo(p7.getDeleted()));
		}
	}
	
	
	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCBestellungDAO#search(sepm.ss13.gast.domain.Bestellung)}.
	 */
	@Test (expected=IllegalArgumentException.class) 
	public void testSearch_2() throws DAOException,IllegalArgumentException {
		
		test.search(null);
	
	}
	
	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCBestellungDAO#search(sepm.ss13.gast.domain.Bestellung)}.
	 */
	@Test
	public void testSearch_3() throws DAOException,IllegalArgumentException {
		
		Bestellung toSearch = new Bestellung(-1,-500,-1,"",-1,null,null);
		ArrayList<Bestellung> p_test = new ArrayList<Bestellung>();
		p_test = test.search(toSearch);

		if(p_test.size() != 0){
			fail("Wrong Bestellung count -- " + p_test.size());
		}
	
	}
	

	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCBestellungDAO#delete(sepm.ss13.gast.domain.Bestellung)}.
	 */
	@Test
	public void testDelete_1() throws DAOException,IllegalArgumentException {
		
		/* INSERT */
		Bestellung p_test = null;
		p_test = test.create(p8);
		
		mykeys_generated.add(p_test.getId());
		p8.setId(p_test.getId());
		
		test.delete(p8);
	}
	
	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCBestellungDAO#delete(sepm.ss13.gast.domain.Bestellung)}.
	 */
	@Test
	public void testDelete_2() throws DAOException,IllegalArgumentException {
		
		/* INSERT */
		Bestellung p_test = null;
		p_test = test.create(p9);
		
		mykeys_generated.add(p_test.getId());
		p9.setId(p_test.getId());
		p9.setDeleted(true);
		
		test.delete(p9);
		
		try {
			PreparedStatement ps = dbc.getConnection().prepareStatement("SELECT id,tischnummer,produktid,preis,rechnungid,status,deleted FROM bestellung WHERE id = ?");
			ps.setInt(1,p_test.getId());
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				assertThat(rs.getInt("id"), equalTo(p_test.getId()));
				assertThat(rs.getInt("tischnummer"), equalTo(p9.getTisch()));
				assertThat(rs.getInt("produktid"), equalTo(p9.getProdukt()));
				assertThat(rs.getInt("preis"), equalTo(p9.getPreis()));
				assertThat(rs.getInt("rechnungid"), equalTo(p9.getRechnung()));
				assertThat(rs.getString("status"), equalTo(p9.getStatus()));
				assertThat(rs.getBoolean("deleted"), equalTo(p9.getDeleted()));
				return;
			}else{
				fail("No Data found");
				return;
			}
		} catch (SQLException e) {
			fail("ERROR: failed to search DB for bestellung!");
			return;
		}
		catch (NullPointerException e) {
			fail("NullPointerException");
			return;
		}
	}

	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCBestellungDAO#delete(sepm.ss13.gast.domain.Bestellung)}.
	 */
	@Test (expected=IllegalArgumentException.class) 
	public void testDelete_3() throws DAOException,IllegalArgumentException {
		
		/* INSERT */
		test.delete(null);

	}
	
}
