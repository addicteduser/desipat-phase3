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
public class AddSystemLogQueryBuilder extends AddQueryBuilder{

    
    protected ArrayList<String> myProjections = new ArrayList<String>();
    protected ArrayList<String> myTables = new ArrayList<String>();
    protected String values = "";
    @Override
    public void populateProjections() {
        myProjections.add("access");
        myProjections.add("username");
        myProjections.add("date");
        
        newQuery.setProjections(myProjections);
    }

    @Override
    public void populateTables() {
        myTables.add("systemLog");
        
        newQuery.setTables(myTables);
    }

    @Override
    public void populateValues() {
        
        values+="values(?, ?, ?)";
        
        newQuery.setValues(values);
    }
    
}
