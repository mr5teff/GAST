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
		log.info("Start Application");
		ac = new ClassPathXmlApplicationContext("spring-config.xml");
		
		Application.launch(GAST.class, args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Gastronomie Assistent für Statistik- und Tischmanagement");
		
		GUITools.loadFXML("WelcomeScreen.fxml",primaryStage);
        primaryStage.show();
	}
}
