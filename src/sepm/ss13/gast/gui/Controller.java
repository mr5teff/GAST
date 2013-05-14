package sepm.ss13.gast.gui;

import javafx.fxml.Initializable;
import javafx.stage.Stage;

public abstract class Controller implements Initializable {
	private Stage s;
	private Controller pc;
	
	protected void setStage(Stage s) {
		this.s=s;
	}
	
	protected Stage getStage() {
		if(s!=null) return s;
		else if(pc!=null) return pc.getStage();
		else return null;
	}
	
	protected void setParentController(Controller c) {
		this.pc=c;
	}
	
	protected Controller getParentController() {
		return pc;
	}
}
