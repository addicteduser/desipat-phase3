package DB;

import java.sql.Connection;
import java.util.Enumeration;
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

	/**
	 * @return the dbDriver
	 */
	public String getDbDriver() {
		return dbDriver;
	}

	/**
	 * @param dbDriver the dbDriver to set
	 */
	public void setDbDriver(String dbDriver) {
		this.dbDriver = dbDriver;
	}

	/**
	 * @return the dbUrl
	 */
	public String getDbUrl() {
		return dbUrl;
	}

	/**
	 * @param dbUrl the dbUrl to set
	 */
	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	/**
	 * @return the dbName
	 */
	public String getDbName() {
		return dbName;
	}

	/**
	 * @param dbName the dbName to set
	 */
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
