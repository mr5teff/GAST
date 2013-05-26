package sepm.ss13.gastTests;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
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
import sepm.ss13.gast.dao.JDBCEinkaufDAO;
import sepm.ss13.gast.dao.JDBCProduktDAO;
import sepm.ss13.gast.domain.Einkauf;

public class Test_JDBCEinkaufDAO {

	private static ApplicationContext ac;
	static DBConnector dbc;
	static JDBCEinkaufDAO test = null;
	
	static Einkauf e1 = null; // create
	static Einkauf e2 = null; // create
	
	static Einkauf e3 = null; // search
	static Einkauf e4 = null; // search
	
	static Einkauf e5 = null; // update
	static Einkauf e6 = null; // update
	
	static Einkauf e7 = null; // delete
	static Einkauf e8 = null; // delete
	
	
	static ArrayList<Integer> mykeys = new ArrayList<Integer>();
	static ArrayList<Integer> mykeys_generated = new ArrayList<Integer>();
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ac = new ClassPathXmlApplicationContext("spring-config.xml");
		dbc = (DBConnector) ac.getBean("databaseManager");
		test = new JDBCEinkaufDAO(dbc.getConnection());
		
//		e1 = new Einkauf();
//		e2 = new Einkauf();
//		
//		e3 = new Einkauf();
//		e4 = new Einkauf();
//		
//		e5 = new Einkauf();
//		e6 = new Einkauf();
//		
//		e7 = new Einkauf();
//		e8 = new Einkauf();
//		
//		Einkauf e_temp = null;
//		e_temp = test.create(e1);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		for(int i = 0; i < mykeys.size();i++){
			try {
				PreparedStatement ps=dbc.getConnection().prepareStatement("DELETE FROM einkauf WHERE id=?");
				ps.setInt(1,mykeys.get(i));
				ps.executeUpdate();
			} catch (SQLException e) {
				throw new DAOException("ERROR: failed to delete Einkauf from DB!");
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
				PreparedStatement ps=dbc.getConnection().prepareStatement("DELETE FROM einkauf WHERE id=?");
				ps.setInt(1,mykeys_generated.get(i));
				ps.executeUpdate();
			} catch (SQLException e) {
				throw new DAOException("ERROR: failed to delete Einkauf from DB!");
			}
			catch (NullPointerException e) {
				throw new IllegalArgumentException();
			}
		}
	}

	@Test
	public void testCreate_1() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testCreate_2() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testSearch_1() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testSearch_2(){
		fail("Not yet implemented");
	}
	
	@Test
	public void testUpdate_1(){
		fail("Not yet implemented");
	}
	
	@Test
	public void testUpdate_2(){
		fail("Not yet implemented");
	}
	
	@Test
	public void testDelete_1(){
		fail("Not yet implemented"); 
	}
	
	@Test
	public void testDelete_2(){
		fail("Not yet implemented");
	}
}
