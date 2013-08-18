/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy;

import Builder.SelectMaintenanceQueryBuilder;
import Builder.SelectQueryBuilder;
import Builder.SelectQueryDirector;
import Builder.SelectQueryString;

/**
 *
 * @author sgtan_
 */
public class SelectMaintenanceStrategy implements ConnectionStrategy{

     @Override
    public String buildString() {
        SelectQueryDirector myDirector = new SelectQueryDirector();
        SelectQueryBuilder myBuilder = new SelectMaintenanceQueryBuilder();
        myDirector.setNewBuilder(myBuilder);
        myDirector.construct();
        SelectQueryString myQuery = myDirector.getQuery();
       
        return myQuery.toString();
    }
}
