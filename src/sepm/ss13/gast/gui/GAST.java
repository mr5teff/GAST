package sepm.ss13.gast.gui;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sepm.ss13.gast.dao.DBConnector;

import javafx.application.Application;
import javafx.stage.Stage;

public class GAST extends Application {
	
	private static Logger log = Logger.getLogger(Application.class);
	private static ApplicationContext ac;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		log.info("Start Application");
		ac = new ClassPathXmlApplicationContext("spring-config.xml");
		
		DBConnector dbc = (DBConnector) ac.getBean("databaseManager");
		
		Application.launch(GAST.class, args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Gastronomie Assistent für Statistik- und Tischmanagement");
		
		GUIManager gm = (GUIManager) ac.getBean("GUIManager");
		gm.setStage(primaryStage);
		gm.replaceSceneContent("WelcomeScreen.fxml");
		
        primaryStage.show();
	}
}
