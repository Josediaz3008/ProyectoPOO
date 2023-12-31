package controller;


import java.net.URL;
import java.util.ResourceBundle;

import DAO.UserRegisterDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import util.SceneManager;

public class RegisterController implements Initializable {
	
	// Attributes

	// Text Fields
	@FXML
	private TextField tfUserName;
	
	@FXML
	private PasswordField tfPassword;
	
	@FXML
	private PasswordField tfConfirmPassword;
	
	// Buttons
	@FXML
	private Button buttonRegister;
	
	@FXML
	private Button buttonLogin;
	
	// Constructor
	public RegisterController() {
		
	}
	
	// Methods

	@Override
	public void initialize(URL location, ResourceBundle resource) {
		
		initializeLoginButton();
		
		
		initializeRegisterButton();
		
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
	
	private void initializeLoginButton() {
		buttonLogin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "Login.fxml", "Login", 500, 600);
			}
		});
	}
	
	private void initializeRegisterButton() {
		buttonRegister.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String username = tfUserName.getText().trim();
				String password = tfPassword.getText().trim();
				String confirmPassword = tfConfirmPassword.getText().trim();
				
				if(isValidCredentials(username, password, confirmPassword)) {
					User user = new User(username, password);
					UserRegisterDAO userRegisterDAO = new UserRegisterDAO();
					if(userRegisterDAO.signUpUser(user)) {
						SceneManager.createAlert(AlertType.INFORMATION, "User Register", "User Register Succesfully");
					} else {
						SceneManager.createAlert(AlertType.ERROR, "User Register", "Error, User registration failed. Please try again later");
					}
					resetTextFields();
				}
			}
		});	
	}

}
