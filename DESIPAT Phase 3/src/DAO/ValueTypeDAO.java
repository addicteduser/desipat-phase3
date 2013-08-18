/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.ValueTypeModel;
import Strategy.SelectValueTypeStrategy;
import Template.SelectQueryTemplate;
import Template.SelectValueTypeTemplate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Aram
 */
public class ValueTypeDAO extends DAO {
    
    private static ValueTypeDAO valueTypeDAO;
    
    private ValueTypeDAO(){
        
    }
    
    public static ValueTypeDAO getInstance(){
        if(valueTypeDAO == null)
            valueTypeDAO = new ValueTypeDAO();
        
        return valueTypeDAO;
    }
    
     public ArrayList<String> getAllValueType() throws SQLException {
       ArrayList<String> type = new ArrayList<String>();
        
        SelectQueryTemplate template = new SelectValueTypeTemplate();
        type = template.executeQuery(new SelectValueTypeStrategy());
        
        return type;
    }
    
}
