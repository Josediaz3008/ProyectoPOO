package DAO;

import java.sql.SQLException;
import model.AppModel;
import model.User;
import javafx.event.ActionEvent;

public class UserLoginDAO extends DataBaseUtils{
	
	// Constructor
	public UserLoginDAO() {
		super();
	}
	
	// Methods
	public boolean signInUser(ActionEvent event, User user) {
	    try {
	    	initializeDataBaseConnection();
	        if (isValidCredentials(user)) {
	            AppModel.setCurrentUsername(user.getUsername());
	            setUserId(user);
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
	    this.preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
	    this.preparedStatement.setString(1, user.getUsername());
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
	
	private void setUserId(User user) throws SQLException {
		this.preparedStatement = connection.prepareStatement("Select user_id FROM users WHERE username = ?");
		this.preparedStatement.setString(1, user.getUsername());
		this.resultSet = preparedStatement.executeQuery();
		
		while(this.resultSet.next()) {
			int retrivedUserId = this.resultSet.getInt("user_id");
			AppModel.setCurrentUserId(retrivedUserId);
		}
	}

}
