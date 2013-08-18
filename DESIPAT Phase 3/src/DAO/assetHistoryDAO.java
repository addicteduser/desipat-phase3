/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.AssetHistoryModel;
import Model.AssetModel;
import Strategy.AddAssetStrategy;
import Template.AddAssetHistoryTemplate;
import Template.AddAssetTemplate;
import Template.QueryTemplate;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author sgtan_
 */
public class assetHistoryDAO extends DAO{
    
    private static assetHistoryDAO historyDAO;
    
    private assetHistoryDAO(){
        
    }
    
    public static assetHistoryDAO getInstance(){
        if(historyDAO == null)
            historyDAO = new assetHistoryDAO();
        
        return historyDAO;
    }
    
    public void addAssetHistory (AssetHistoryModel ahmdl) throws SQLException{
        QueryTemplate template = new AddAssetHistoryTemplate();
        template.executeQuery(new AddAssetStrategy());
    }
    
    public void saveHistory (String assetName, int identifier, String owner, Date acquired) throws SQLException{
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into assetHistory values(?, ?, ?, ?)");
        preparedStatement.setString(1, assetName);
        preparedStatement.setInt(2, identifier);
        preparedStatement.setString(3, owner);
        preparedStatement.setDate(4, acquired);
        
        update(preparedStatement);
        close(preparedStatement, connection);
    }
    
     public ArrayList<AssetHistoryModel> getAssetHistory(String aname) throws SQLException{
          
         ArrayList<AssetHistoryModel> AssetHistoryModel = new ArrayList<AssetHistoryModel>();
    
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select assetName, assetOwner, dateAcquired from assetHistory  where assetName LIKE '" + aname + "' ");
        ResultSet resultSet = query(preparedStatement);
          while (resultSet.next()) {
            AssetHistoryModel ahmdl = new AssetHistoryModel();
            String Assetname = resultSet.getString("assetName");
            String Assetowner = resultSet.getString("assetOwner");
            Date DateAcquired = resultSet.getDate("dateAcquired");
            
            ahmdl.setAssetName(Assetname);
            ahmdl.setAssetOwner(Assetowner);
            ahmdl.setDateAcquired(DateAcquired);
                        
            AssetHistoryModel.add(ahmdl);
          }
    
        close(preparedStatement, connection);
        return AssetHistoryModel;
        
          
        
      }

    
}
