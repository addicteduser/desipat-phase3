/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Template.QueryTemplate;
import Model.*;
import Strategy.AddAssetStrategy;
import Template.AddAssetTemplate;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Aram
 */
public class AssetDAO extends DAO {
    
    private static AssetDAO assetDAO;
    
    private AssetDAO(){
        
    }
    
    public static AssetDAO getInstance(){
        if(assetDAO == null)
            assetDAO = new AssetDAO();
        
        return assetDAO;
    }
    
     public void addAsset (AssetModel amdl) throws SQLException{
        QueryTemplate template = new AddAssetTemplate();
        template.executeQuery(new AddAssetStrategy());
    }
          
     public void deleteAsset(String assetname) throws SQLException{
       
       int confirmation = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this asset?", "Delete Asset", JOptionPane.YES_NO_OPTION);
        
       if(confirmation == JOptionPane.YES_OPTION){ 
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM asset WHERE assetName = '" + assetname + "'");
            ps.executeUpdate();
       
            JOptionPane.showMessageDialog(null, "Asset has been successfully deleted!");
            close(ps, connection); 
       }
       
       else
            JOptionPane.showMessageDialog(null, "Asset deletion cancelled.");    
    }
    
    public void editAsset (AssetModel amdl) throws SQLException{
        String Type = amdl.getType();
        String maintenanceSched = amdl.getSchedule();
        String Classification = amdl.getClassification();
        String Confidentiality = amdl.getConfidentiality();
        String Integrity = amdl.getIntegrity();
        String Availability = amdl.getAvailability();
   
        ArrayList<String> atmdl = AssetTypeDAO.getInstance().getAllAssetTypes();
        ArrayList<String> msmdl = MaintenanceSchedDAO.getInstance().getAllMaintenanceSchedule();
        ArrayList<String> cmdl = ClassificationDAO.getInstance().getAllClassification();
        ArrayList<String> vtmdl = ValueTypeDAO.getInstance().getAllValueType();
        
        
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("update asset set assetOwner = ?, assetCustodian = ?, assetType = ?, dateAcquired = ?, retentionPeriodFrom = ?, retentionPeriodTo = ?, maintenanceSched = ?, financial = ?, confidentiality = ?, integrity = ?, availability = ?, classification = ?, storageLocation = ? where assetName ='"+amdl.getAssetname()+"'");
        
        preparedStatement.setObject(1, amdl.getAssetowner());
        preparedStatement.setObject(2, amdl.getAssetcustodian());
        
        
        for (int i = 0; i < atmdl.size(); i++) {
            // foreign key to private key interconnect
            if (atmdl.get(i).toString().equals(Type)) {
                preparedStatement.setObject(3, atmdl.get(i).toString());
            }
        }
        
        preparedStatement.setObject(4, amdl.getDateAcquired());
        preparedStatement.setObject(5, amdl.getRetentionPeriodFrom());
        preparedStatement.setObject(6, amdl.getRetentionPeriodTo());
        
        for(int i=0; i < msmdl.size(); i++){
            if(msmdl.get(i).toString().equals(maintenanceSched)){
                preparedStatement.setObject(7, msmdl.get(i).toString());
            }
        }
        
        preparedStatement.setObject(8, amdl.getFinancial());
        
         for (int i = 0; i < vtmdl.size(); i++) {
            // foreign key to private key interconnect
            if (vtmdl.get(i).toString().equals(Confidentiality)) {
                preparedStatement.setObject(9, vtmdl.get(i).toString());
            }
        } 
           for (int i = 0; i < vtmdl.size(); i++) {
            // foreign key to private key interconnect
            if (vtmdl.get(i).toString().equals(Integrity)) {
                preparedStatement.setObject(10, vtmdl.get(i).toString());
            }
        } 
            for (int i = 0; i < vtmdl.size(); i++) {
            // foreign key to private key interconnect
            if (vtmdl.get(i).toString().equals(Availability)) {
                preparedStatement.setObject(11, vtmdl.get(i).toString());
            }
        } 
            
        for (int i = 0; i < cmdl.size(); i++) {
            // foreign key to private key interconnect
            if (cmdl.get(i).toString().equals(Classification)) {
                preparedStatement.setObject(12, cmdl.get(i).toString());
            }
        }
       
        preparedStatement.setObject(13, amdl.getStoragelocation());
        
        update(preparedStatement);
        close(preparedStatement, connection);
    }
    
    public void getAssetDetails(String aname) throws SQLException{
       
       
       AssetModel amdl = AssetModel.getInstance();
    
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from asset WHERE (assetName LIKE '" + aname + "') ");
        ResultSet resultSet = query(preparedStatement);
          while (resultSet.next()) {
            
            String Assetname = resultSet.getString("assetName");
            String Assetowner = resultSet.getString("assetOwner");
            String Assetcustodian = resultSet.getString("assetCustodian");
            String Assettype = resultSet.getString("assetType");
            Date DateAcquired = resultSet.getDate("dateAcquired");
            Date RetentionPeriodFrom = resultSet.getDate("retentionPeriodFrom");
            Date RetentionPeriodTo = resultSet.getDate("retentionPeriodTo");
            String Schedule = resultSet.getString("maintenanceSched");
            int Financial = resultSet.getInt("financial");
            String Confidentiality = resultSet.getString("confidentiality");
            String Integrity = resultSet.getString("integrity");
            String Availability = resultSet.getString("availability");
            String Classification = resultSet.getString("classification");
            String StorageLocation = resultSet.getString("storageLocation");
            
            amdl.setAssetname(Assetname);
            amdl.setAssetowner(Assetowner);
            amdl.setAssetcustodian(Assetcustodian);
            amdl.setType(Assettype);
            amdl.setDateAcquired(DateAcquired);
            amdl.setRetentionPeriodFrom(RetentionPeriodFrom);
            amdl.setRetentionPeriodTo(RetentionPeriodTo);
            amdl.setSchedule(Schedule);
            amdl.setFinancial(Financial);
            amdl.setConfidentiality(Confidentiality);
            amdl.setIntegrity(Integrity);
            amdl.setAvailability(Availability);
            amdl.setClassification(Classification);
            amdl.setStoragelocation(StorageLocation);
        
            
          }
    
        close(preparedStatement, connection);
  
      }
    
    public int getAssetIdentifier(String aname) throws SQLException{
       
        int identifier = 0;
    
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select identifier from asset WHERE (assetName LIKE '" + aname + "') ");
        ResultSet resultSet = query(preparedStatement); 
       
        while(resultSet.next())
            identifier = resultSet.getInt("identifier");
        
        close(preparedStatement, connection);
        return identifier;
  
      }
    
    public ArrayList<String> getAllAssetNames() throws SQLException {
        ArrayList<String> assetName = new ArrayList<String>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select assetName from asset order by assetName");
        ResultSet resultSet = query(preparedStatement);
        while (resultSet.next()) {
            
            assetName.add(resultSet.getString("assetName"));
            
      }
        return assetName;
    
    }

    
}
