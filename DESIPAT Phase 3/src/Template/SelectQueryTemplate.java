/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Template;

import DAO.DAO;
import Strategy.ConnectionStrategy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author sgtan_
 */
public abstract class SelectQueryTemplate extends DAO{
    
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet rs;
    ArrayList<String> list = new ArrayList<String>();
    
    public final ArrayList<String> executeQuery(ConnectionStrategy strategy) throws SQLException{
        connection = getConnection();
        
        preparedStatement = connection.prepareStatement(strategy.buildString());
        list = queryStatement(rs);
        
        close(preparedStatement, connection);
        
        return list;
    }
    
   
    public abstract ArrayList<String> queryStatement(ResultSet rs) throws SQLException;
    
}
