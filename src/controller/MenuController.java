package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.AppModel;

public class MenuController extends BaseMenuController implements Initializable{
	
	// Label
	@FXML
	private Label labelWelcome;
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		
		initializeMenu();
		
		this.labelWelcome.setText("Welcome Back " + AppModel.getCurrentUsername() + "!");
	}

}
