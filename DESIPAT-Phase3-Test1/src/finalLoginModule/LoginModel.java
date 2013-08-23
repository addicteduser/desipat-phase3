package finalLoginModule;

import java.sql.SQLException;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DAO.UserDAO;
import Model.UserModel;

/**
 * MODEL for Login
 */
public class LoginModel {
	private UserDAO udao = UserDAO.getInstance();
	private UserModel umdl;

	/**
	 * Checks if username field is empty
	 * 
	 * @param username
	 * @return TRUE if username field is not empty
	 */
	public boolean checkUsernameField(JTextField username) {
		if (username.getText().isEmpty())
			return false;
		else
			return true;
	}

	/**
	 * Checks if password field is empty
	 * 
	 * @param password
	 * @return TRUE if password field is not empty
	 */
	public boolean checkPasswordField(JPasswordField password) {
		if (password.getText().toString().equals(""))
			return false;
		else
			return true;
	}

	/**
	 * This checks is the username matches the password
	 * 
	 * @param umdl
	 * @param username
	 * @param password
	 * @return TRUE if username matches password, else returns false
	 * @throws SQLException
	 */
	public boolean checkMatch(UserModel umdl, JTextField username,
			JPasswordField password) throws SQLException {
		try {
			if (umdl.getUsername().equals(username.getText())
					&& umdl.getPassword().equals(password.getText())) {
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	public String getUser(String username) throws SQLException {
		return udao.getUser(username).getUsername();
	}

	/*
	 * GET COMPONENTS
	 */
	public UserModel getUserDAO(JTextField username) throws SQLException {
		umdl = udao.getUser(username.getText());
		return umdl;
	}

	public UserModel getUserMdl() {
		return umdl;
	}
}
