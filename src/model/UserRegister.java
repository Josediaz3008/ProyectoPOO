package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRegister extends DataBaseUtils{
	private PreparedStatement psInsert = null;
	private PreparedStatement psCheckUserExists = null;
	
	public UserRegister() {
		
	}
	
	public boolean signUpUser(User user) {
	    initializeDataBaseConnection();
	    try {
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