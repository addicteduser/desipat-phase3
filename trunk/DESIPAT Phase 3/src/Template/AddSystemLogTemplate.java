/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Template;

import DAO.systemLogDAO;
import assetreg.login;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author sgtan_
 */
public class AddSystemLogTemplate extends QueryTemplate{

    private String getDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
        java.util.Date date = new java.util.Date();
                
	return dateFormat.format(date);
     }	
    
    @Override
    public void queryStatement(PreparedStatement stmt) throws SQLException {
        
        preparedStatement.setString(1, systemLogDAO.getInstance().access);
        preparedStatement.setString(2, systemLogDAO.getInstance().username);
        preparedStatement.setString(3, getDate());
        
    }
    
}
