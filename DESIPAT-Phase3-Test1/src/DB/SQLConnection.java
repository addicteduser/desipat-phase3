/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author sgtan_
 */
public class SQLConnection extends DBFactory {

	@Override
	public Connection getConnection() {
		try {
			// TODO code application logic here
			Class.forName(getDriverName());
			Connection conn = DriverManager.getConnection(getUrl(),
					getUsername(), getPassword());
			// System.out.println("success");
			return conn;
		} catch (SQLException ex) {
			Logger.getLogger(SQLConnection.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(SQLConnection.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return null;
	}

}
