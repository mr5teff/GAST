package sepm.ss13.gast.gui;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Stage;

public abstract class Controller implements Initializable {
	private Stage s;
	private Controller pc;
	private boolean active=true;
	
	@FXML protected Parent root;
	
	protected void setStage(Stage s) {
		this.s=s;
	}
	
	protected Stage getStage() {
		if(s!=null) return s;
		else if(root!=null) return (Stage) root.getScene().getWindow();
		else throw new NullPointerException("failed to get stage! missing root in fxml?");
	}
	
	protected void setParentController(Controller c) {
		this.pc=c;
	}
	
	protected Controller getParentController() {
		return pc;
	}
	
	protected ApplicationContext getApplicationContext() {
		return GAST.getApplicationContext();
	}
	
	protected Logger getLogger() {
		return Logger.getLogger(this.getClass());
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
