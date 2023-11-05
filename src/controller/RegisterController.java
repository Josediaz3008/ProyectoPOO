package controller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.User;
import model.UserRegister;
import util.SceneManager;

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
	private Button buttonLogin;
	
	// Methods

	@Override
	public void initialize(URL location, ResourceBundle resource) {
		
		// Return to Login
		buttonLogin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "login.fxml", "Login", 500, 600);
			}
		});
		
		// User Register
		buttonRegister.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String username = tfUserName.getText().trim();
				String password = tfPassword.getText().trim();
				String confirmPassword = tfConfirmPassword.getText().trim();
				
				if(isValidCredentials(username, password, confirmPassword)) {
					User user = new User(username, password);
					UserRegister userRegister = new UserRegister();
					userRegister.signUpUser(user);
					resetTextFields();
				}
				
			}
		});
		
	}
	
	private boolean isValidCredentials(String username, String password, String confirmPassword) {
	    if (username.isEmpty()) {
	        SceneManager.createAlert(AlertType.ERROR, "Error", "Username cannot be empty.");
	        return false;
	    }

	    if (password.isEmpty()) {
	        SceneManager.createAlert(AlertType.ERROR, "Error", "Password cannot be empty.");
	        return false;
	    }

	    if (confirmPassword.isEmpty()) {
	        SceneManager.createAlert(AlertType.ERROR, "Error", "Confirmation cannot be empty.");
	        return false;
	    }

	    if (!password.equals(confirmPassword)) {
	        SceneManager.createAlert(AlertType.ERROR, "Error", "Passwords do not match. Please make sure you enter the same password in both fields.");
	        return false;
	    }

	    return true;
	}
	
	private void resetTextFields() {
		this.tfUserName.setText("");
		this.tfPassword.setText("");
		this.tfConfirmPassword.setText("");
	}

}
