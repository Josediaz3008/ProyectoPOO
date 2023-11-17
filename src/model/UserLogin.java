package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.event.ActionEvent;

public class UserLogin extends DataBaseUtils{
	
	private PreparedStatement preparedStatement = null;
	
	public boolean signInUser(ActionEvent event, User user) {
	    initializeDataBaseConnection();
	    try {
	        if (isValidCredentials(user)) {
	            AppModel.setCurrentUsername(user.getUsername());
	        	return true;
	        }
	        return false;    
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
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
