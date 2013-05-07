package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class GASTController implements Initializable {
    @FXML private TextField textField;
	
	public void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        System.out.println(textField.getText());
        
    }

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
