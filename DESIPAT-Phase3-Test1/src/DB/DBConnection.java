package DB;

/**
 * Concrete Factory for creating a database connection
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection extends DBFactory {

	@Override
	public Connection getConnection() {
		try {
			Class.forName(getDbDriver());
			Connection con = DriverManager.getConnection(getDbUrl() + getDbName(), getUsername(), getPassword());
			return con;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
