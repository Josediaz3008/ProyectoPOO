package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DataBaseUtils {
	
	// Attributes
	protected Connection connection = null;
	protected ResultSet resultSet = null;
	
	private String username = "root";
	private String password = "RYPFvel9hVL_njT";
	private String url = "jdbc:mysql://localhost:3306/prpoo";
	
	public DataBaseUtils() {
		
	}
	
	protected void initializeDataBaseConnection() {
		try {
			this.connection = DriverManager.getConnection(this.url, this.username, this.password);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error Data Base Utils");
		}
	}
	
	protected void closeDataBaseConnection() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	
	public Connection getConnection() {
		return this.connection;
	}
}
