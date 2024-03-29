package sepm.ss13.gast.gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GUITools {
	public static Stage openDialog(String title, Stage ownerStage) {
		if(ownerStage==null) throw new IllegalArgumentException("invalid/no parent stage specified!");
		
		Stage s = new Stage();
		s.initOwner(ownerStage.getScene().getWindow());
		s.initModality(Modality.WINDOW_MODAL);
		s.setTitle(title);
		
		return s;
	}
	
	public static Controller loadFXML(String fxml, Stage stage) {
		FXMLLoader fxmlLoader = new FXMLLoader(GUITools.class.getResource(fxml));
		try {
			stage.setScene(new Scene((Parent) fxmlLoader.load()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	 	Controller c = fxmlLoader.getController();
   	 	c.setStage(stage);
   	 	return c;
	}
	
	public static Controller loadFXML(String fxml, Stage stage, Controller pc) {
		Controller c = loadFXML(fxml, stage);
		c.setParentController(pc);
		return c;
	}
}
