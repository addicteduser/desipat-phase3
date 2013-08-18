/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Template;

import DAO.DAO;
import Strategy.ConnectionStrategy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sgtan_
 */
public abstract class QueryTemplate extends DAO{
    
    Connection connection;
    PreparedStatement preparedStatement;
    
    public final void executeQuery(ConnectionStrategy strategy) throws SQLException{
        connection = getConnection();
        
        preparedStatement = connection.prepareStatement(strategy.buildString());
        queryStatement(preparedStatement);
        
        update(preparedStatement);
        close(preparedStatement, connection);
    }
    
   
    public abstract void queryStatement(PreparedStatement stmt) throws SQLException;
}
