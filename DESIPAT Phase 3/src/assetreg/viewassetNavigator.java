package assetreg;

import DAO.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Aram
 */
public class viewassetNavigator extends JPanel{

    
      protected JLabel bg;
      public JLabel viewassethistory;
      public JLabel viewassetdetails;
      public JLabel viewsystemlog;

      protected ImageIcon[] viewassethistoryIcon;
      protected ImageIcon[] viewassetdetailsIcon;
      protected ImageIcon[] viewsystemlogIcon;
      
      view assethistory;
      view assetdetails;
      view systemlog;
         
    protected viewassetNavigator.Listener l;
    protected JPanel panel;
    protected JLabel panelbutton;
    public static String username;
    public static String usertype;
    
    protected Connection connect;
    
    public viewassetNavigator(String usertype, String username) {
        this.usertype = usertype;
        this.username = username;
        try {
            initialize();
            setBounds();
            setFrame();
            addToFrame();
           
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    
     private void initialize() throws SQLException, ClassNotFoundException {
        
        viewassethistoryIcon = new ImageIcon[2];  
        viewassethistoryIcon[0] = new ImageIcon(this.getClass().getClassLoader().getResource("pictures/viewassethistory.png"));
        viewassethistoryIcon[1] = new ImageIcon(this.getClass().getClassLoader().getResource("pictures/viewassethistory.png"));
        
        viewassetdetailsIcon = new ImageIcon[2];
        viewassetdetailsIcon[0] = new ImageIcon(this.getClass().getClassLoader().getResource("pictures/viewassetdetails.png"));
        viewassetdetailsIcon[1] = new ImageIcon(this.getClass().getClassLoader().getResource("pictures/viewassetdetails.png"));
       
        viewsystemlogIcon = new ImageIcon[2];
        viewsystemlogIcon[0] = new ImageIcon(this.getClass().getClassLoader().getResource("pictures/viewsystemlog.png"));
        viewsystemlogIcon[1] = new ImageIcon(this.getClass().getClassLoader().getResource("pictures/viewsystemlog.png"));

               
        bg = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("pictures/viewassetnavigator.png")));
         viewassethistory = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("pictures/viewassethistory.png")));
        viewassetdetails = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("pictures/viewassetdetails.png")));
        viewsystemlog = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("pictures/viewsystemlog.png")));
         

        viewassethistory = new JLabel(viewassethistoryIcon[0]);
        viewassetdetails = new JLabel(viewassetdetailsIcon[0]);
       viewsystemlog = new JLabel(viewsystemlogIcon[0]);
        
        viewassethistory.setDisabledIcon(viewassethistoryIcon[1]);
        viewassetdetails.setDisabledIcon(viewassetdetailsIcon[1]);
        viewsystemlog.setDisabledIcon(viewsystemlogIcon[1]);
        

        l = new viewassetNavigator.Listener(this);
         viewassethistory.addMouseListener(l);
         viewassetdetails.addMouseListener(l);
       viewsystemlog.addMouseListener(l);
        
              
         assetdetails = new viewByAssetDetails();
                   assetdetails.View();
                change( assetdetails.getPanel(), viewassetdetails);

        Class.forName("com.mysql.jdbc.Driver");
        connect = login.connect;

    }
     
     public boolean isAdmin(String usertype){
            if("Admin".equals(usertype))
                    return true;
            return false;
    }
     
      private void setBounds()  {
        bg.setBounds(0, 0, bg.getIcon().getIconWidth(), bg.getIcon().getIconHeight());
        panel.setBounds(0, bg.getIcon().getIconHeight(), panel.getWidth(), panel.getHeight());
        viewassethistory.setBounds(475, 23, viewassethistory.getIcon().getIconWidth(), viewassethistory.getIcon().getIconHeight());
        viewassetdetails.setBounds(275, 23, viewassetdetails.getIcon().getIconWidth(), viewassetdetails.getIcon().getIconHeight());
        viewsystemlog.setBounds(690, 23, viewsystemlog.getIcon().getIconWidth(), viewsystemlog.getIcon().getIconHeight());
         
        
    }
         
 private void setFrame() {
        this.setSize(bg.getIcon().getIconWidth(), bg.getIcon().getIconHeight() + panel.getHeight());
        this.setLayout(null);
        this.setVisible(true);
    }

    private void addToFrame() {
        if(!(isAdmin(usertype))){
            viewassethistory.setVisible(false);
            viewsystemlog.setVisible(false);
            
            
        }
 
         this.add(viewassetdetails);
        this.add(viewassethistory);
        
        this.add(viewsystemlog);
      
        this.add(panel);
        this.add(bg);

    }
    
     public void setExitIcons(){
       viewassethistory.setIcon(viewassethistoryIcon[0]);
        viewassetdetails.setIcon(viewassetdetailsIcon[0]);
        viewsystemlog.setIcon(viewsystemlogIcon[0]);
     }
     
     
    public void change(JPanel panel, JLabel panelbutton) {
        if (this.panel != null && this.panelbutton != null) {
            this.remove(this.panel);
            this.panelbutton.addMouseListener(l);
            this.panelbutton.setEnabled(true);
        }
       
        setExitIcons();
        this.panel = panel;
        this.add(panel);
        
        this.panel.setBounds(0, bg.getIcon().getIconHeight(), panel.getWidth(), panel.getHeight());
        this.panelbutton = panelbutton;
        panelbutton.removeMouseListener(l);
        panelbutton.setEnabled(false);
    }
     
      

    public class Listener implements MouseListener {

        viewassetNavigator frame;
        String equipName;
        String equipID;
        String description;
        String status;

        public Listener(viewassetNavigator frame) {
            this.frame = frame;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
           
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getSource() == viewassethistory) {
                  assethistory = new viewByAssetHistory();
                   assethistory.View();
                change( assethistory.getPanel(), viewassethistory);

            } else if (e.getSource() == viewassetdetails) {
                 assetdetails = new viewByAssetDetails();
                   assetdetails.View();
                change( assetdetails.getPanel(), viewassetdetails);
                
                
            } else if (e.getSource() == viewsystemlog) {
                systemlog = new viewBySystemLog();
                systemlog.View();
                change( systemlog.getPanel(), viewsystemlog);
            } 
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == viewassethistory) {
                viewassethistory.setIcon(viewassethistoryIcon[1]);
            } else if (e.getSource() == viewassetdetails) {
                viewassetdetails.setIcon(viewassetdetailsIcon[1]);
            } else if (e.getSource() == viewsystemlog) {
                viewsystemlog.setIcon(viewsystemlogIcon[1]);
            } 
        }
          @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == viewassethistory) {
                viewassethistory.setIcon(viewassethistoryIcon[0]);
            } else if (e.getSource() == viewassetdetails) {
                viewassetdetails.setIcon(viewassetdetailsIcon[0]);
            } else if (e.getSource() == viewsystemlog) {
                viewsystemlog.setIcon(viewsystemlogIcon[0]);
            } 
        }

        
    }
    
    
}

