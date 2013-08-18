/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy;

import Builder.AddAssetQueryBuilder;
import Builder.AddQueryBuilder;
import Builder.AddQueryDirector;
import Builder.AddQueryString;

/**
 *
 * @author sgtan_
 */
public class AddAssetStrategy implements ConnectionStrategy{
    
    @Override
    public String buildString() {
        AddQueryDirector myDirector = new AddQueryDirector();
        AddQueryBuilder myBuilder = new AddAssetQueryBuilder();
        myDirector.setNewBuilder(myBuilder);
        myDirector.construct();
        AddQueryString myQuery = myDirector.getQuery();
       
        return myQuery.toString();
    }
    
}
