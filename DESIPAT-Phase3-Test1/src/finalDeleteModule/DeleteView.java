/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package finalDeleteModule;

import DAO.*;
import Model.*;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
//import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

import finalLoginModule.loginView;
import view.GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author KYLE DELA CRUZ
 */
public class DeleteView extends GUI{    
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
    
    
    public DeleteView(String type, String username){
        this.type = type;
        this.name = username;
        makePanel();
    }
    
    //check if logged in user is an Administrator
    public boolean isAdmin(String usertype){
            if("Admin".equals(usertype))
                    return true;
            return false;
    }
    
    // ADDS THE LISTENERSSSSS
    public void addBtnDelAssetListener(ActionListener listener) {
		btnDelAsset.addActionListener(listener);
	}
    
    public void addBtnDelUserListener(ActionListener listener) {
		btnDelUser.addActionListener(listener);
	}
    
    // UI INITIALIZATIONS
    @Override
    public void initialize() throws ClassNotFoundException, SQLException {
        bg = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("pictures/deletebg.png")));


        lblAsset = new JLabel("Delete Asset");
        lblUser = new JLabel("Delete User");
        lblAsset.setFont(new Font("Calibri", Font.BOLD, 30));
        lblAsset.setForeground(Color.white);
        lblUser.setFont(new Font("Calibri", Font.BOLD, 30));
        lblUser.setForeground(Color.WHITE);
         
     Class.forName("com.mysql.jdbc.Driver");
     //connect = login.connect;
     
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
    
    // GETTERS AND SETTERS
    public JComboBox getAssetList() {
		return assetList;
	}

	public void setAssetList(JComboBox assetList) {
		this.assetList = assetList;
	}

	public JComboBox getUserList() {
		return userList;
	}

	public void setUserList(JComboBox userList) {
		this.userList = userList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
