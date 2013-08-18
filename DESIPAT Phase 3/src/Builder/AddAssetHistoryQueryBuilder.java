/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Builder;

import java.util.ArrayList;

/**
 *
 * @author HOLLY
 */
public class AddAssetHistoryQueryBuilder extends AddQueryBuilder{
    protected ArrayList<String> myProjections = new ArrayList<String>();
    protected ArrayList<String> myTables = new ArrayList<String>();
    protected String values = "";
    
    @Override
    public void populateProjections(){
        myProjections.add("assetName");
        myProjections.add("identifier");
        myProjections.add("assetOwner");
        myProjections.add("dateAcquired");
        
        newQuery.setProjections(myProjections);

    }

    @Override
    public void populateTables() {
        myTables.add("assethistory");
        newQuery.setTables(myTables);
    }

    @Override
    public void populateValues() {
        values += "values(?, ?, ?, ?)";
        
        newQuery.setValues(values);
    }
    
    
    
}
