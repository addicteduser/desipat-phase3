/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy;

import Builder.SelectClassificationQueryBuilder;
import Builder.SelectQueryBuilder;
import Builder.SelectQueryDirector;
import Builder.SelectQueryString;

/**
 *
 * @author sgtan_
 */
public class SelectClassificationStrategy implements ConnectionStrategy{

     @Override
    public String buildString() {
        SelectQueryDirector myDirector = new SelectQueryDirector();
        SelectQueryBuilder myBuilder = new SelectClassificationQueryBuilder();
        myDirector.setNewBuilder(myBuilder);
        myDirector.construct();
        SelectQueryString myQuery = myDirector.getQuery();
       
        return myQuery.toString();
    }
    
    
}
