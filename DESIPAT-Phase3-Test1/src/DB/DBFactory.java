/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * 
 * @author sgtan_
 */
public abstract class DBFactory {
	private static String driverName = "";
	private static String url = "";
	private static String username = "";
	private static String password = "";

	public static DBFactory getInstance() {
		ResourceBundle rb = ResourceBundle.getBundle("database");
		Enumeration<String> settings = rb.getKeys();
		driverName = rb.getString("driverName");
		url = rb.getString("url");
		username = rb.getString("username");
		password = rb.getString("password");

		return new SQLConnection();
	}

	public abstract Connection getConnection();

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (Exception e) {
		}
	}

	public void closeResultSet(ResultSet resultSet) {
		try {
			resultSet.close();
		} catch (Exception e) {
		}
	}

	public void closeStatement(Statement statement) {
		try {
			statement.close();
		} catch (Exception e) {
		}
	}
}
