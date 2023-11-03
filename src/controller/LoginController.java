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


public class LoginController implements Initializable{
	
	// Buttons
	@FXML
	private Button buttonLogin;
	
	@FXML
	private Button buttonRegister;
	
	// Text Fields
	@FXML
	private TextField tfUsername;
	
	@FXML
	private TextField tfPassword;

	// Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		buttonRegister.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManagment.ChangeScene(event, "register.fxml", "Register");
			}
		});
		
	}
	
	

}