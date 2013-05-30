package sepm.ss13.gastTests;

import static org.junit.Assert.*;

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
	
	private static JDBCKonfigurationDAO testDAO;
	private static DBConnector dbc;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring-config.xml");
		dbc = (DBConnector) ac.getBean("databaseManager");
		dbc.getConnection().setAutoCommit(false); 
		testDAO = new JDBCKonfigurationDAO(dbc.getConnection());		
	}

	@After
	public void tearDown() throws Exception {
		dbc.getConnection().rollback();
	}

	@Test
	public void testSaveKonfig() {
		// Original sichern, neue Konfiguration erstellen, speichern, vergleichen
		
		try {
			Konfiguration original = testDAO.load();
			
			Konfiguration k = new Konfiguration("Testbar", "Teststrasse 6", "0664 11223344", original.getLogo(), 666);
			testDAO.save(k);
			Konfiguration kTest = testDAO.load();
			
			assertThat(k.getName(), equalTo(kTest.getName()));
			assertThat(k.getAdresse(), equalTo(kTest.getAdresse()));
			assertThat(k.getTel(), equalTo(kTest.getTel()));
			assertThat(k.getTimerIntervall(), equalTo(kTest.getTimerIntervall()));
			assertThat(k.getLogo(), equalTo(kTest.getLogo()));
			
			testDAO.save(original);
			
		} catch(DAOException e) {
			fail(e.getMessage());
		}
	}
}
