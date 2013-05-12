/**
 * 
 */
package sepm.ss13.gast.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sepm.ss13.gast.dao.DBConnector;
import sepm.ss13.gast.dao.JDBCProduktDAO;
import sepm.ss13.gast.domain.Produkt;

/**
 * @author Admin
 *
 */
public class Test_JDBCProduktDAO {
	
	private static ApplicationContext ac;
	
	static JDBCProduktDAO test = null;
	

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ac = new ClassPathXmlApplicationContext("spring-config.xml");
		DBConnector dbc = (DBConnector) ac.getBean("databaseManager");
		test = new JDBCProduktDAO(dbc.getConnection());
		
		Produkt p1 = new Produkt(-1,"Test Schnitzel",0,1);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
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
	}

	
	
	
	
	
	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCProduktDAO#JDBCProduktDAO(java.sql.Connection)}.
	 */
	@Test
	public void testJDBCProduktDAO() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCProduktDAO#create(sepm.ss13.gast.domain.Produkt)}.
	 */
	@Test
	public void testCreate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCProduktDAO#search(sepm.ss13.gast.domain.Produkt)}.
	 */
	@Test
	public void testSearch() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link sepm.ss13.gast.dao.JDBCProduktDAO#update(sepm.ss13.gast.domain.Produkt)}.
	 */
	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

}
