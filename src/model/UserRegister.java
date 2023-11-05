package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.control.Alert.AlertType;
import util.SceneManager;

public class UserRegister extends DataBaseUtils{
	private PreparedStatement psInsert = null;
	private PreparedStatement psCheckUserExists = null;
	
	public UserRegister() {
		
	}
	
	public void signUpUser(User user) {
	    initializeDataBaseConnection();
	    try {
	        if (userExists(user)) {
	            SceneManager.createAlert(AlertType.ERROR, "Error", "User already exists");
	        } else {
	            createUser(user);
	            SceneManager.createAlert(AlertType.INFORMATION, "User Confirmation", "User successfully registered.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        SceneManager.createAlert(AlertType.ERROR, "Error", "Internal Database Error");
	    } finally {
	        closeResources();
	    }
	}

	private boolean userExists(User user) throws SQLException {
	    this.psCheckUserExists = this.connection.prepareStatement("SELECT * FROM users WHERE username = ?");
	    this.psCheckUserExists.setString(1, user.getUsername());
	    this.resultSet = psCheckUserExists.executeQuery();
	    return this.resultSet.isBeforeFirst();
	}

	private void createUser(User user) throws SQLException {
		this.psInsert = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
		this.psInsert.setString(1, user.getUsername());
		this.psInsert.setString(2, user.getPasswordHash());
		this.psInsert.executeUpdate();
	}

	private void closeResources() {
	    try {
	        if (this.resultSet != null) {
	        	this.resultSet.close();
	        }
	        if (this.psCheckUserExists != null) {
	            psCheckUserExists.close();
	        }
	        if (this.psInsert != null) {
	        	this.psInsert.close();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    closeDataBaseConnection();
	}

}