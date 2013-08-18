/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Builder;

/**
 *
 * @author sgtan_
 */
public abstract class SelectQueryBuilder {
    
    protected SelectQueryString newQuery;
    
    public abstract void populateProjections();
    public abstract void populateTables();
    
    public void createNewQuery(){
        newQuery = new SelectQueryString();
    }
    
    public SelectQueryString getQuery(){
        return newQuery;
    }
}
