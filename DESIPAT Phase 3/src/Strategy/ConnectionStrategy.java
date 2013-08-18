/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy;

import DB.DBFactory;
import java.sql.Connection;

/**
 *
 * @author sgtan_
 */
public interface ConnectionStrategy{
    
    DBFactory dbfactory = DBFactory.getInstance();
    Connection connection = dbfactory.getConnection();
    
    public String buildString();
}
