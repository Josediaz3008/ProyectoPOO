package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.scene.control.Alert.AlertType;
import util.SceneManager;

public class UserLogin extends DataBaseUtils{
	
	private PreparedStatement preparedStatement = null;
	
	public void signInUser(String username, String password) {
	    initializeDataBaseConnection();

	    try {
	        if (isValidCredentials(username, password)) {
	            // Usuario autenticado con éxito, cambia a la escena de inicio de sesión
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

	private boolean isValidCredentials(String username, String password) throws SQLException {
	    PreparedStatement preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
	    preparedStatement.setString(1, username);
	    this.resultSet = preparedStatement.executeQuery();

	    if (!this.resultSet.isBeforeFirst()) {
	        return false;
	    }

	    while (this.resultSet.next()) {
	        String retrievedPassword = this.resultSet.getString("password");
	        if (retrievedPassword.equals(password)) {
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
