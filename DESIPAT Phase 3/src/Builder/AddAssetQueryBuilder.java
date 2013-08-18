/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Builder;

import java.util.ArrayList;

/**
 *
 * @author sgtan_
 */
public class AddAssetQueryBuilder extends AddQueryBuilder {
    
    
    protected ArrayList<String> myProjections = new ArrayList<String>();
    protected ArrayList<String> myTables = new ArrayList<String>();
    protected String values = "";
    
    @Override
    public void populateProjections() {
        
        myProjections.add("assetName");
        myProjections.add("assetOwner");
        myProjections.add("assetCustodian");
        myProjections.add("assetType");
        myProjections.add("dateAcquired");
        myProjections.add("retentionPeriodFrom");
        myProjections.add("retentionPeriodTo");
        myProjections.add("maintenanceSched");
        myProjections.add("financial");
        myProjections.add("confidentiality");
        myProjections.add("integrity");
        myProjections.add("availability");
        myProjections.add("classification");
        myProjections.add("storageLocation");
        
        newQuery.setProjections(myProjections);
        
    }

    @Override
    public void populateTables() {
        
        myTables.add("asset");
        newQuery.setTables(myTables);
        
    }

    @Override
    public void populateValues() {
        values+= "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        newQuery.setValues(values);
    }
    
}
