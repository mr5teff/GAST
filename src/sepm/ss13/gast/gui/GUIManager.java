package sepm.ss13.gast.gui;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GUIManager {
	private static Stage stage;
	
	public void setStage(Stage s) {
		stage=s;
	}
	
	public Stage getStage()
	{
		return stage;
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
	
	public static Stage openDialog(String title) {
		Stage s = new Stage();
		s.initOwner(stage.getScene().getWindow());
		s.initModality(Modality.WINDOW_MODAL);
		s.setTitle(title);
		
		return s;
	}
	
	public static Scene loadFXML(String fxml, Object controller) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(GUIManager.class.getResource(fxml));
		fxmlLoader.setController(controller);
   	 	return new Scene((Parent) fxmlLoader.load());
	}
}
