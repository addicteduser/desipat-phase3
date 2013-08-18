/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy;

import Builder.SelectQueryBuilder;
import Builder.SelectQueryDirector;
import Builder.SelectQueryString;
import Builder.SelectAssetTypeQueryBuilder;

/**
 *
 * @author sgtan_
 */
public class SelectAssetTypeStrategy implements ConnectionStrategy{

    @Override
    public String buildString() {
        SelectQueryDirector myDirector = new SelectQueryDirector();
        SelectQueryBuilder myBuilder = new SelectAssetTypeQueryBuilder();
        myDirector.setNewBuilder(myBuilder);
        myDirector.construct();
        SelectQueryString myQuery = myDirector.getQuery();
       
        return myQuery.toString();
    }
    
}
