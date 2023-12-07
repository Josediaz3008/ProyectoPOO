package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DataBaseUtils {
	
	// Attributes
	protected Connection connection = null;
	protected ResultSet resultSet = null;
	protected PreparedStatement preparedStatement = null;
	
	private String username = "root";
	private String password = "root";
	private String url = "jdbc:mysql://localhost:3306/prpoo";
	
	public DataBaseUtils() {
	}
	
	protected void initializeDataBaseConnection() throws SQLException {
		this.connection = DriverManager.getConnection(this.url, this.username, this.password);
	}
	
	protected void closeResources() {
	    try (ResultSet rs = this.resultSet; PreparedStatement ps = this.preparedStatement; Connection cn = this.connection) {
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
