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
public abstract class AddQueryBuilder {
    
    protected AddQueryString newQuery;
    
    public abstract void populateProjections();
    public abstract void populateTables();
    public abstract void populateValues();
    
    public void createNewQuery(){
        newQuery = new AddQueryString();
    }
    
    public AddQueryString getQuery(){
        return newQuery;
    }
    
}
