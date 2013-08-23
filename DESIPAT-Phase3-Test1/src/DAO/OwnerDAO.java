/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 
 * @author sgtan_
 */
public class OwnerDAO extends DAO {

	private static OwnerDAO ownerDAO;

	private OwnerDAO() {

	}

	public static OwnerDAO getInstance() {
		if (ownerDAO == null)
			ownerDAO = new OwnerDAO();

		return ownerDAO;
	}

	public void saveOwner(String owner, int identifier, String assetOwned)
			throws SQLException {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("insert into owners values(?, ?, ?)");
		preparedStatement.setString(1, owner);
		preparedStatement.setInt(2, identifier);
		preparedStatement.setString(3, assetOwned);

	}

}
