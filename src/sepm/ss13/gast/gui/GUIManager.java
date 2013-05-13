package sepm.ss13.gast.gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GUIManager {
	private static Stage stage;
	
	public void setStage(Stage s) {
		stage=s;
	}
	
	public void replaceSceneContent(String fxml) {
		AnchorPane page = null;
		try {
			page = (AnchorPane) FXMLLoader.load(getClass().getResource(fxml));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        Scene scene = new Scene(page);
        
        if(stage==null) System.out.println("waaaaaaaaaa");
        
        stage.setScene(scene);
        stage.sizeToScene();
    }

}
