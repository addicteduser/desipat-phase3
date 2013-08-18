/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DB.DBFactory;
import Model.*;
import Strategy.SelectAssetTypeStrategy;
import Template.SelectAssetTypeTemplate;
import Template.SelectQueryTemplate;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Aram
 */
public class AssetTypeDAO extends DAO {
 
    private static AssetTypeDAO assetTypeDAO;
    
    private AssetTypeDAO(){
        
    }
    
    public static AssetTypeDAO getInstance(){
        if(assetTypeDAO == null)
            assetTypeDAO = new AssetTypeDAO();
        
        return assetTypeDAO;
    }
    
     public ArrayList<String> getAllAssetTypes() throws SQLException {
        ArrayList<String> AssetTypeModels = new ArrayList<String>();
        
        SelectQueryTemplate template = new SelectAssetTypeTemplate();
        AssetTypeModels = template.executeQuery(new SelectAssetTypeStrategy());
        
        return AssetTypeModels;
    }

    
}
