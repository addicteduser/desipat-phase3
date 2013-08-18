/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Template;

import DAO.AssetTypeDAO;
import DAO.ClassificationDAO;
import DAO.MaintenanceSchedDAO;
import DAO.ValueTypeDAO;
import Model.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author sgtan_
 */
public class AddAssetTemplate extends QueryTemplate{

    @Override
     public void queryStatement(PreparedStatement stmt) throws SQLException{
        
        String Type = AssetModel.getInstance().getType();
        String maintenanceSched = AssetModel.getInstance().getSchedule();
        String Classification = AssetModel.getInstance().getClassification();
        String Confidentiality = AssetModel.getInstance().getConfidentiality();
        String Integrity = AssetModel.getInstance().getIntegrity();
        String Availability = AssetModel.getInstance().getAvailability();
   
        
        ArrayList<String> atmdl = AssetTypeDAO.getInstance().getAllAssetTypes();
        ArrayList<String> msmdl = MaintenanceSchedDAO.getInstance().getAllMaintenanceSchedule();
        ArrayList<String> cmdl = ClassificationDAO.getInstance().getAllClassification();
        ArrayList<String> vtmdl = ValueTypeDAO.getInstance().getAllValueType();
        
        
        preparedStatement.setObject(1, AssetModel.getInstance().getAssetname());
        preparedStatement.setObject(2, AssetModel.getInstance().getAssetowner());
        preparedStatement.setObject(3, AssetModel.getInstance().getAssetcustodian());
        
        
        for (int i = 0; i < atmdl.size(); i++) {
            // foreign key to private key interconnect
            if (atmdl.get(i).toString().equals(Type)) {
                preparedStatement.setObject(4, atmdl.get(i).toString());
            }
        }
        
        preparedStatement.setObject(5, AssetModel.getInstance().getDateAcquired());
        preparedStatement.setObject(6, AssetModel.getInstance().getRetentionPeriodFrom());
        preparedStatement.setObject(7, AssetModel.getInstance().getRetentionPeriodTo());
        
        for(int i=0; i < msmdl.size(); i++){
            if(msmdl.get(i).toString().equals(maintenanceSched)){
                preparedStatement.setObject(8, msmdl.get(i).toString());
            }
        }
        
        preparedStatement.setObject(9, AssetModel.getInstance().getFinancial());
        
         for (int i = 0; i < vtmdl.size(); i++) {
            // foreign key to private key interconnect
            if (vtmdl.get(i).toString().equals(Confidentiality)) {
                preparedStatement.setObject(10, vtmdl.get(i).toString());
            }
        } 
           for (int i = 0; i < vtmdl.size(); i++) {
            // foreign key to private key interconnect
            if (vtmdl.get(i).toString().equals(Integrity)) {
                preparedStatement.setObject(11, vtmdl.get(i).toString());
            }
        } 
            for (int i = 0; i < vtmdl.size(); i++) {
            // foreign key to private key interconnect
            if (vtmdl.get(i).toString().equals(Availability)) {
                preparedStatement.setObject(12, vtmdl.get(i).toString());
            }
        } 
            
        for (int i = 0; i < cmdl.size(); i++) {
            // foreign key to private key interconnect
            if (cmdl.get(i).toString().equals(Classification)) {
                preparedStatement.setObject(13, cmdl.get(i).toString());
            }
        }
       
        preparedStatement.setObject(14, AssetModel.getInstance().getStoragelocation());
        
    }
    
    
}
