/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Builder;

/**
 *
 * @author sgtan_
 */
public class SelectQueryDirector {
    private SelectQueryBuilder newBuilder;
    
    public void construct(){
        newBuilder.createNewQuery();
        newBuilder.populateProjections();
        newBuilder.populateTables();
        
    }
    
    public SelectQueryString getQuery(){
        return newBuilder.getQuery();
    }

    public void setNewBuilder(SelectQueryBuilder newBuilder) {
        this.newBuilder = newBuilder;
    }
}
