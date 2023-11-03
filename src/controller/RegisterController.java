package controller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import util.SceneManagment;

public class RegisterController implements Initializable {

	// Text Fields
	@FXML
	private TextField tfUserName;
	
	@FXML
	private TextField tfPassword;
	
	@FXML
	private TextField tfConfirmPassword;
	
	// Buttons
	@FXML
	private Button buttonRegister;
	
	@FXML
	private Button buttonSignIn;
	
	// Methods

	@Override
	public void initialize(URL location, ResourceBundle resource) {
		
		buttonSignIn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				SceneManagment.ChangeScene(event, "login.fxml", "Register");
				
			}
		});
		
	}
}
