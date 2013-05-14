package sepm.ss13.gast.gui;

import javafx.fxml.Initializable;
import javafx.stage.Stage;

public abstract class Controller implements Initializable {
	private Stage s;
	
	protected void setStage(Stage s) {
		this.s=s;
	}
	
	protected Stage getStage() {
		return s;
	}
}
