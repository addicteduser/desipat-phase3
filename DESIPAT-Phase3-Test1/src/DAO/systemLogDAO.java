/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Strategy.AddSystemLogStrategy;
import Template.AddSystemLogTemplate;
import Template.QueryTemplate;
import DB.DBFactory;
import Model.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author sgtan_
 */
public class systemLogDAO extends DAO{
    
     private static systemLogDAO logDAO;
     public static String access;
     public static String username;
     
    private systemLogDAO(){
        
    }
    
    public static systemLogDAO getInstance(){
        if(logDAO == null)
            logDAO = new systemLogDAO();
        
        return logDAO;
    }
    
     public void saveAccess (String access, String user) throws SQLException{
         systemLogDAO.access = access;
         systemLogDAO.username = user;
         
        QueryTemplate template = new AddSystemLogTemplate();
        template.executeQuery(new AddSystemLogStrategy());
    }
     
       public ArrayList<SystemLogModel> getAllDates() throws SQLException {
            
           ArrayList<SystemLogModel> SystemLogModels = new ArrayList<SystemLogModel>();
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from systemLog");
            ResultSet resultSet = query(preparedStatement);
            while (resultSet.next()) {
                SystemLogModel slmdl = new SystemLogModel();
                slmdl.setAccess(resultSet.getString("access"));
                slmdl.setUsername(resultSet.getString("username"));
                slmdl.setDate(resultSet.getString("DATE"));
                SystemLogModels.add(slmdl);
            }
            return SystemLogModels;
    
        }
        public ArrayList<SystemLogModel> getDateContent(String aname) throws SQLException {
            
           ArrayList<SystemLogModel> SystemLogModels = new ArrayList<SystemLogModel>();
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from systemLog where (DATE LIKE '%" + aname + "%') ");
            ResultSet resultSet = query(preparedStatement);
            while (resultSet.next()) {
                SystemLogModel slmdl = new SystemLogModel();
                slmdl.setAccess(resultSet.getString("access"));
                slmdl.setUsername(resultSet.getString("username"));
                slmdl.setDate(resultSet.getString("DATE"));
                SystemLogModels.add(slmdl);
            }
            return SystemLogModels;
    
        }
    
    
}
