package DB;

import java.sql.Connection;
import java.util.ResourceBundle;

/**
 * Abstract Factory for creating a database connection
 */
public abstract class DBFactory {
	private static String dbDriver = "";
	private static String dbUrl = "";
	private static String dbName = "";
	private static String username = "";
	private static String password = "";

	/**
	 * Creates the instance for connection based on the URL
	 * @return instance for connection
	 */
	public static DBConnection getInstance(){
		ResourceBundle rb = ResourceBundle.getBundle("database");
		
		dbDriver = rb.getString("dbDriver");
		dbUrl = rb.getString("dbUrl");
		dbName = rb.getString("dbName");
		username = rb.getString("username");
		password = rb.getString("password");

		return new DBConnection();
	}
	
	/**
	 * Gets the database connection based on the instance
	 * @return connection to the database
	 */
	public abstract Connection getConnection();

	/*
	 * GETTERS 
	 */
	
	/**
	 * @return the dbDriver
	 */
	public String getDbDriver() {
		return dbDriver;
	}

	/**
	 * @return the dbUrl
	 */
	public String getDbUrl() {
		return dbUrl;
	}

	/**
	 * @return the dbName
	 */
	public String getDbName() {
		return dbName;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
}
