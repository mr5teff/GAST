package sepm.ss13.gastTests;

import static org.junit.Assert.*;

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
import sepm.ss13.gast.dao.JDBCKonfigurationDAO;
import sepm.ss13.gast.domain.Konfiguration;

public class Test_JDBCKonfigurationDAO {
	
	private static ApplicationContext ac;
	static DBConnector dbc;
	static JDBCKonfigurationDAO test = null;
	
	static Konfiguration kOrigin = null;
	static Konfiguration k = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dbc.getConnection().setAutoCommit(false); 

		ac = new ClassPathXmlApplicationContext("spring-config.xml");
		dbc = (DBConnector) ac.getBean("databaseManager");
		test = new JDBCKonfigurationDAO(dbc.getConnection());
		
		kOrigin = test.load();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		dbc.getConnection().setAutoCommit(false);
	}

	@After
	public void tearDown() throws Exception {
		test.save(kOrigin);
		
		dbc.getConnection().rollback();
	}

	@Test
	public void testSaveKonfig() throws DAOException {
		k = new Konfiguration("TestBar", "TestStrasse 2 / 25", "0664 1122334455", kOrigin.getLogo(), 666);
		test.save(k);
		Konfiguration k_test = test.load();
		
		assertThat(k_test.getAdresse(), equalTo(k.getAdresse()));
		assertThat(k_test.getName(), equalTo(k.getName()));
		assertThat(k_test.getTel(), equalTo(k.getTel()));
		assertThat(k_test.getTimerIntervall(), equalTo(k.getTimerIntervall()));
		assertThat(k_test.getLogo(), equalTo(k.getLogo()));
	}

}
