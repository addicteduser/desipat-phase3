package finalDeleteModule;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.AssetDAO;
import DAO.UserDAO;
import Model.UserModel;

public class DeleteModel {
	ArrayList<String> assetName;
	List<UserModel> usermdl;
	AssetDAO adao = AssetDAO.getInstance();
	UserDAO udao = UserDAO.getInstance();
	
	public void getAllAsset() throws SQLException {
			assetName = AssetDAO.getInstance().getAllAssetNames();
	}
	
	public void getAllUserModel() throws SQLException {
		usermdl = UserDAO.getInstance().getAllUsers();
	}
}
