package finalNavigatorModule;

import java.sql.SQLException;

import DAO.UserDAO;

/**
 * MODEL for Navigator
 */
public class NavigatorModel {
	 public static String username;
	 public static String usertype;
	 
	 public NavigatorModel() {
		 //delete this
	 }
	 
	 public NavigatorModel(String username) throws SQLException {
		 this.username = username;
	     usertype = UserDAO.getInstance().getUser(username).getUsertype();
	 }
}
