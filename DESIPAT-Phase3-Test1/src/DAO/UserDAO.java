package DAO;

import Model.UserModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * 
 * @author Aram
 */
public class UserDAO extends DAO {

	private static UserDAO userDAO;

	private UserDAO() {

	}

	public static UserDAO getInstance() {
		if (userDAO == null)
			userDAO = new UserDAO();

		return userDAO;
	}

	public UserModel getUser(String username) throws SQLException {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("select * from users where username = ?");
		preparedStatement.setString(1, username);
		ResultSet resultSet = query(preparedStatement);
		UserModel umdl = new UserModel();
		while (resultSet.next()) {
			String Username = resultSet.getString("username");
			String Password = resultSet.getString("pass");
			String Firstname = resultSet.getString("firstName");
			String Lastname = resultSet.getString("lastName");
			String Usertype = resultSet.getString("userType");
			umdl.setUsertype(Usertype);
			umdl.setUsername(Username);
			umdl.setPassword(Password);
			umdl.setFirstname(Firstname);
			umdl.setLastname(Lastname);
		}
		close(preparedStatement, connection);
		return umdl;
	}

	public List<UserModel> getAllUsers() throws SQLException {
		List<UserModel> allusers = new ArrayList<UserModel>();
		Connection connection = getConnection();
		PreparedStatement ps = connection
				.prepareStatement("SELECT * FROM users ORDER BY username");
		ResultSet rs = query(ps);
		while (rs.next()) {
			UserModel usermodel = new UserModel();
			usermodel.setUsername(rs.getString("username"));
			usermodel.setPassword(rs.getString("pass"));
			usermodel.setFirstname(rs.getString("firstName"));
			usermodel.setLastname(rs.getString("lastName"));
			usermodel.setUsertype(rs.getString("userType"));

			allusers.add(usermodel);
			// System.out.println(allusers.get(0).getUsername());
		}
		close(ps, connection);
		return allusers;

	}

	/*
	 * public String getUserType(String username) throws SQLException{ String
	 * usertype = ""; Connection connection = getConnection(); PreparedStatement
	 * ps =
	 * connection.prepareStatement("SELECT userType FROM users WHERE username = '"
	 * + username + "'"); ResultSet rs = query(ps); while(rs.next()) usertype =
	 * rs.getString("userType");
	 * 
	 * // System.out.println(usertype); return usertype;
	 * 
	 * }
	 */

	public void deleteUser(String username) throws SQLException {
		// System.out.println(username);
		int confirmation = JOptionPane.showConfirmDialog(null,
				"Are you sure you want to delete this user?", "Delete User",
				JOptionPane.YES_NO_OPTION);

		if (confirmation == JOptionPane.YES_OPTION) {
			Connection connection = getConnection();
			PreparedStatement ps = connection
					.prepareStatement("DELETE FROM users WHERE userName = '"
							+ username + "'");
			ps.executeUpdate();

			JOptionPane.showMessageDialog(null,
					"User has been successfully deleted!");
			close(ps, connection);
		}

		else
			JOptionPane.showMessageDialog(null, "User deletion cancelled.");
	}

}
