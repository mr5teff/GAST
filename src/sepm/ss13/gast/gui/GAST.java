package sepm.ss13.gast.gui;

import java.io.InputStream;
import java.util.logging.Level;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GAST extends Application {
	
	private static Logger log = Logger.getLogger(Application.class);
	
	private Stage stage;

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
		
		gotoWelcome();
		
		/*
		Parent root = FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));
        
        Scene welcomeScene = new Scene(root, 800, 600); 
        welcomeScene.addEventFilter(arg0, arg1)
        primaryStage.setScene(welcomeScene);
        */
		
        primaryStage.show();
		
	}
	
	public void gotoManagement() {
        try {
            ManagementController managementController = (ManagementController) replaceSceneContent("Management.fxml");
            managementController.setApp(this);
        } catch (Exception ex) {
        	log.error("ERROR");
        }
    }
	
	public void gotoKassa() {
        try {
            KassaController kassaController = (KassaController) replaceSceneContent("Kassa.fxml");
            kassaController.setApp(this);
        } catch (Exception ex) {
        	log.error("ERROR");
        }
    }
	
	public void gotoKueche() {
        try {
            KuecheController kuecheController = (KuecheController) replaceSceneContent("Kueche.fxml");
            kuecheController.setApp(this);
        } catch (Exception ex) {
        	log.error("ERROR");
        }
    }
	
	
	public void gotoWelcome() {
        try {
            WelcomeScreenController welcomeScreenController = (WelcomeScreenController) replaceSceneContent("WelcomeScreen.fxml");
            welcomeScreenController.setApp(this);
        } catch (Exception ex) {
        	log.error("ERROR");
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
