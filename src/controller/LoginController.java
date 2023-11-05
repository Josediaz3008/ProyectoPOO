package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.User;
import model.UserLogin;
import javafx.scene.control.Alert.AlertType;
import util.SceneManager;


public class LoginController implements Initializable{
	
	// Buttons
	@FXML
	private Button buttonLogin;
	
	@FXML
	private Button buttonRegister;
	
	// Text Fields
	@FXML
	private TextField tfUserName;
	
	@FXML
	private TextField tfPassword;

	// Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		buttonRegister.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "register.fxml", "Register", 500, 600);
			}
		});
		
		buttonLogin.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String username = tfUserName.getText().trim();
				String password = tfPassword.getText().trim();
				
				if(userLoginValidation(username, password)) {
					User user = new User(username, password);
					UserLogin userLogin = new UserLogin();
					userLogin.signInUser(user);
					resetTextFields();
				}
			
			}
		});
	}
	
	private boolean userLoginValidation(String username, String password) {
	    if (username.isEmpty()) {
	        SceneManager.createAlert(AlertType.ERROR, "Error", "Username cannot be empty.");
	        return false;
	    }

	    if (password.isEmpty()) {
	        SceneManager.createAlert(AlertType.ERROR, "Error", "Password cannot be empty.");
	        return false;
	    }
	    return true;
	}

	private void resetTextFields() {
		this.tfUserName.setText("");
		this.tfPassword.setText("");
	}
}