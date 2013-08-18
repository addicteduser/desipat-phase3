/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Template;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author sgtan_
 */
public class SelectMaintenanceTemplate extends SelectQueryTemplate{
     ArrayList<String> sched = new ArrayList<String>();
    
    @Override
    public ArrayList<String> queryStatement(ResultSet rs) throws SQLException {
        ResultSet resultSet = query(preparedStatement);
        while (resultSet.next()) {
            
            sched.add(resultSet.getString("sched"));
        }
        return sched;
    }
    
}
