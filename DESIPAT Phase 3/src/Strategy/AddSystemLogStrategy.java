/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy;

import Builder.*;

/**
 *
 * @author sgtan_
 */
public class AddSystemLogStrategy implements ConnectionStrategy{

    @Override
    public String buildString() {
        AddQueryDirector myDirector = new AddQueryDirector();
        AddQueryBuilder myBuilder = new AddSystemLogQueryBuilder();
        myDirector.setNewBuilder(myBuilder);
        myDirector.construct();
        AddQueryString myQuery = myDirector.getQuery();
       
        return myQuery.toString();
    }
    
}
