/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assetreg;

import DAO.*;
import Model.AssetModel;
import Model.*;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
//import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author JT
 */
public class DeleteGUI extends GUI{    
    protected JLabel bg;
    protected Connection connect;
    protected JLabel lblAsset;
    protected JLabel lblUser;
    private String type;
    private String name;

    JComboBox assetList = new JComboBox();
    JComboBox userList = new JComboBox();

    JButton btnDelAsset = new JButton("Delete Asset");

    JButton btnDelUser = new JButton("Delete User");
    
    
    public DeleteGUI(String type, String username){
        this.type = type;
        this.name = username;
        makePanel();
    } 
    
    public void updateAssetsList(){
     //Add Asset Names in ComboBox
        try{
        ArrayList<String> assetName = AssetDAO.getInstance().getAllAssetNames();
        for (int i = 0; i < assetName.size(); i++) {
            assetList.addItem(assetName.get(i));
        }
         
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
     }
    
    public void updateUsersList() throws SQLException{
     //Add all Usernames in ComboBox
        List<UserModel> usermdl = UserDAO.getInstance().getAllUsers();
        for(int i = 0; i < usermdl.size(); i++)
            userList.addItem(usermdl.get(i).getUsername());
     }
  
    //check if logged in user is an Administrator
    public boolean isAdmin(String usertype){
            if("Admin".equals(usertype))
                    return true;
            return false;
    }
    
    public void delete(int flag) throws SQLException{
      
        //delete assets
        if(flag == 0){
            String selectedItem = "";
            AssetDAO adao = AssetDAO.getInstance();
            selectedItem = assetList.getSelectedItem().toString();
            
            adao.deleteAsset(selectedItem);
            assetList.removeAllItems();
            updateAssetsList();
            systemLogDAO.getInstance().saveAccess("Deleted asset " + selectedItem, login.username.getText());
            
        }
        
        //delete users
        else {
            String selectedItem = "";
            UserDAO udao = UserDAO.getInstance();
            
            selectedItem = userList.getSelectedItem().toString();
           
            if (this.name.equals(selectedItem)){   
                JOptionPane.showMessageDialog(this, "You are not allowed to delete your own account.");
            }
           
            else
                udao.deleteUser(selectedItem);
                userList.removeAllItems();
                updateUsersList();
                systemLogDAO.getInstance().saveAccess("Deleted user " + selectedItem, login.username.getText());
            
        }
    }
    
    @Override
    public void initialize() throws ClassNotFoundException, SQLException {
        bg = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("pictures/deletebg.png")));


        lblAsset = new JLabel("Delete Asset");
        lblUser = new JLabel("Delete User");
        lblAsset.setFont(new Font("Calibri", Font.BOLD, 30));
        lblAsset.setForeground(Color.white);
        lblUser.setFont(new Font("Calibri", Font.BOLD, 30));
        lblUser.setForeground(Color.WHITE);
       
         
        btnDelAsset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               try {
                    delete(0);
                } catch (SQLException ex) {
                    Logger.getLogger(DeleteGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
         
        btnDelUser.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                 try {
                    delete(1);
                } catch (SQLException ex) {
                    Logger.getLogger(DeleteGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
         
     Class.forName("com.mysql.jdbc.Driver");
     connect = login.connect;
     
    }
     
    @Override
    public void setBounds() {
        bg.setBounds(0, 0, bg.getIcon().getIconWidth(), bg.getIcon().getIconHeight());
        assetList.setSelectedIndex(0);
        lblAsset.setBounds(50, 150, 167, 23);
        lblUser.setBounds(500, 150, 167, 23);
       
        assetList.setBounds(50, 200, 220, 25);
        userList.setBounds(500, 200, 220, 25);
       
        btnDelAsset.setBounds(150, 250, 120, 25);
        btnDelUser.setBounds(600, 250, 120, 25);
        
    }
         
    @Override
    public void setPanelSize()  {
        this.setSize(bg.getIcon().getIconWidth(), bg.getIcon().getIconHeight());
        this.setLayout(null);
        this.setVisible(true);
    }
   
    @Override
    public void addToPanel() {
         this.add(lblAsset);
        this.add(lblUser);
        
        this.add(assetList);
        this.add(userList);
     
        this.add(btnDelAsset);
        this.add(btnDelUser);
        this.add(bg);
        
        //if user is not an Admin, disable Delete User 
        if(!(isAdmin(type))){
            lblUser.setVisible(false);
            btnDelUser.setVisible(false);
            userList.setVisible(false);
            
        }
    }

    @Override
    public void makePanel() {
        try {
            initialize();
            updateAssetsList();
            updateUsersList();
            setBounds();
            setPanelSize();
            addToPanel();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    
}
