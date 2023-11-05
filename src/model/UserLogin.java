package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.scene.control.Alert.AlertType;
import util.SceneManager;

public class UserLogin extends DataBaseUtils{
	
	private PreparedStatement preparedStatement = null;
	
	public void signInUser(User user) {
	    initializeDataBaseConnection();

	    try {
	        if (isValidCredentials(user)) {
	            // Change Scene to Login
	            SceneManager.createAlert(AlertType.INFORMATION, "Succesfully logged in", "Logged In");
	        } else {
	            SceneManager.createAlert(AlertType.ERROR, "Error", "The provided credentials are incorrect");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        closeResources();
	    }
	}

	private boolean isValidCredentials(User user) throws SQLException {
	    PreparedStatement preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
	    preparedStatement.setString(1, user.getUsername());
	    this.resultSet = preparedStatement.executeQuery();

	    if (!this.resultSet.isBeforeFirst()) {
	        return false;
	    }

	    while (this.resultSet.next()) {
	        String retrievedPassword = this.resultSet.getString("password");
	        if (retrievedPassword.equals(user.getPasswordHash())) {
	            return true;
	        }
	    }

	    return false;
	}
	
	private void closeResources() {
	    try {
	        if (this.resultSet != null) {
	        	this.resultSet.close();
	        }
	        if (this.preparedStatement != null) {
	            this.preparedStatement.close();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    closeDataBaseConnection();
	}
}
