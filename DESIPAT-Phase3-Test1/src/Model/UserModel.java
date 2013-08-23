/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * 
 * @author Aram
 */
public class UserModel {
	private String username;
	private String pass;
	private String firstName;
	private String lastName;
	private String userType;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return pass;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.pass = password;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstName;
	}

	/**
	 * @param firstname
	 *            the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstName = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastName;
	}

	/**
	 * @param lastname
	 *            the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastName = lastname;
	}

	/**
	 * @return the IDnumber
	 */
	public String getUsertype() {
		return userType;
	}

	/**
	 * @param IDnumber
	 *            the IDnumber to set
	 */
	public void setUsertype(String usertype) {
		this.userType = usertype;
	}

}
