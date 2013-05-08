package sepm.ss13.gast.gui;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GAST extends Application {
	
	private static Logger log = Logger.getLogger(Application.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		log.info("Start Application");
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring-config.xml");
		
		Application.launch(GAST.class, args);

	}

	@Override
	public void start(Stage s) throws Exception {
		// TODO Auto-generated method stub
		
		Parent root = FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));
		
        
        s.setTitle("Gastronomie Assistent für Statistik- und Tischmanagement");  
        s.setScene(new Scene(root, 800, 600));
        s.show();
		
	}

}
