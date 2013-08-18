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
public class SelectValueTypeQueryBuilder extends SelectQueryBuilder{
    
    protected ArrayList<String> myProjections = new ArrayList<String>();
    protected ArrayList<String> myTables = new ArrayList<String>();
    
    @Override
    public void populateProjections() {
        myProjections.add("valueType");
        newQuery.setProjections(myProjections);
    }

    @Override
    public void populateTables() {
        myTables.add("valueType");
        newQuery.setTables(myTables);
    }
    
}
