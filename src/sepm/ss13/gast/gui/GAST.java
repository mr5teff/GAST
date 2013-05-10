package sepm.ss13.gast.gui;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.dao.DBConnector;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GAST extends Application {
	
	private static Logger log = Logger.getLogger(Application.class);
	
	private static String kuecheFxml = "Kueche.fxml";
	private static String welcomeFxml = "WelcomeScreen.fxml";
	private static String managementFxml = "Management.fxml";
	private static String kassaFxml = "Kassa.fxml";
	
	private Stage stage;
	private ManagementController managementController;
	private KassaController kassaController;
	private KuecheController kuecheController;
	private WelcomeScreenController welcomeScreenController;
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
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		stage = primaryStage;
		stage.setTitle("Gastronomie Assistent für Statistik- und Tischmanagement");
		
		initService();
		
		gotoWelcome();
		
        primaryStage.show();
	}
	
	private void initService()
	{
		DBConnector dbcon = DBConnector.instance();
		
		try {
			dbcon.openConnection("www.bachl.tk", "", "SA", "lalalalalala");
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void gotoManagement() {
        try {
            managementController = (ManagementController) replaceSceneContent(managementFxml);
            managementController.setApp(this);
        } catch (Exception ex) {
        	log.error("Management Scene could not be loaded");
        }
    }
	
	public void gotoKassa() {
        try {
            kassaController = (KassaController) replaceSceneContent(kassaFxml);
            kassaController.setApp(this);
        } catch (Exception ex) {
        	log.error("Kassa Scene could not be loaded");
        }
    }
	
	public void gotoKueche() {
        try {
            kuecheController = (KuecheController) replaceSceneContent(kuecheFxml);
            kuecheController.setApp(this);
        } catch (Exception ex) {
        	log.error("Kueche Scene could not be loaded");
        }
    }
	
	
	public void gotoWelcome() {
        try {
            welcomeScreenController = (WelcomeScreenController) replaceSceneContent(welcomeFxml);
            welcomeScreenController.setApp(this);
        } catch (Exception ex) {
        	log.error("Welcome Scene could not be loaded");
        }
    }
	
	
	private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = GAST.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(GAST.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        
        Scene scene = new Scene(page);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }

}
