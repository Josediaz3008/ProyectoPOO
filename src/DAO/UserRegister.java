package DAO;

import java.sql.SQLException;
import model.User;

public class UserRegister extends DataBaseUtils{
	
	// Constructor
	public UserRegister() {
		super();
	}
	
	// Methods
	public boolean signUpUser(User user) {
	    try {
	    	initializeDataBaseConnection();
	        if (!userExists(user)) {
	        	createUser(user);
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

	private boolean userExists(User user) throws SQLException {
	    this.preparedStatement = this.connection.prepareStatement("SELECT * FROM users WHERE username = ?");
	    this.preparedStatement.setString(1, user.getUsername());
	    this.resultSet = preparedStatement.executeQuery();
	    return this.resultSet.isBeforeFirst();
	}

	private void createUser(User user) throws SQLException {
		this.preparedStatement = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
		this.preparedStatement.setString(1, user.getUsername());
		this.preparedStatement.setString(2, user.getPasswordHash());
		this.preparedStatement.executeUpdate();
	}

}