/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy;

import Builder.SelectQueryBuilder;
import Builder.SelectQueryDirector;
import Builder.SelectQueryString;
import Builder.SelectValueTypeQueryBuilder;

/**
 *
 * @author sgtan_
 */
public class SelectValueTypeStrategy implements ConnectionStrategy{

     @Override
    public String buildString() {
        SelectQueryDirector myDirector = new SelectQueryDirector();
        SelectQueryBuilder myBuilder = new SelectValueTypeQueryBuilder();
        myDirector.setNewBuilder(myBuilder);
        myDirector.construct();
        SelectQueryString myQuery = myDirector.getQuery();
       
        return myQuery.toString();
    }
    
}
