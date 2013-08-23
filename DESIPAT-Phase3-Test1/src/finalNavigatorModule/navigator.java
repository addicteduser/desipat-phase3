package finalNavigatorModule;

import DAO.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class navigator extends JFrame{
    
    protected JLabel bg;
    public JLabel mainpage;
    public JLabel addAsset;
    public JLabel deleteAsset;
    public JLabel viewAsset;
    public JLabel editAsset;
    
    JPanel Panel = new JPanel();
 
    protected ImageIcon[] addAssetIcon;
    protected ImageIcon[] mainpageIcon;
    protected ImageIcon[] deleteAssetIcon;
    protected ImageIcon[] viewAssetIcon;
    protected ImageIcon[] editAssetIcon;
 
    protected navigator.Listener l;
    protected JPanel panel;
    protected JLabel panelbutton;
    public static String username;
    public static String usertype;
    
    private JLabel logout;
    

    protected Connection connect;
    
    public navigator(String username) throws SQLException {
        this.username = username;
        
        usertype = UserDAO.getInstance().getUser(username).getUsertype();
        
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
        
        addAssetIcon = new ImageIcon[2];  
        addAssetIcon[0] = new ImageIcon(this.getClass().getClassLoader().getResource("pictures/plus.png"));
        addAssetIcon[1] = new ImageIcon(this.getClass().getClassLoader().getResource("pictures/plus_ON.png"));
        
        deleteAssetIcon = new ImageIcon[2];
        deleteAssetIcon[0] = new ImageIcon(this.getClass().getClassLoader().getResource("pictures/trashcan.png"));
        deleteAssetIcon[1] = new ImageIcon(this.getClass().getClassLoader().getResource("pictures/trashcan_ON.png"));
       
        editAssetIcon = new ImageIcon[2];
        editAssetIcon[0] = new ImageIcon(this.getClass().getClassLoader().getResource("pictures/wrench.png"));
        editAssetIcon[1] = new ImageIcon(this.getClass().getClassLoader().getResource("pictures/wrench_ON.png"));
        
        viewAssetIcon = new ImageIcon[2];
        viewAssetIcon[0] = new ImageIcon(this.getClass().getClassLoader().getResource("pictures/list.png"));
        viewAssetIcon[1] = new ImageIcon(this.getClass().getClassLoader().getResource("pictures/list_ON.png"));
        
        
        bg = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("pictures/menu.png")));

        addAsset = new JLabel(addAssetIcon[0]);
        deleteAsset = new JLabel(deleteAssetIcon[0]);
        editAsset = new JLabel(editAssetIcon[0]);
        viewAsset = new JLabel(viewAssetIcon[0]);
        
        addAsset.setDisabledIcon(addAssetIcon[1]);
        deleteAsset.setDisabledIcon(deleteAssetIcon[1]);
        editAsset.setDisabledIcon(editAssetIcon[1]);
        viewAsset.setDisabledIcon(viewAssetIcon[1]);
        

        l = new navigator.Listener(this);
        addAsset.addMouseListener(l);
        deleteAsset.addMouseListener(l);
        editAsset.addMouseListener(l);
        viewAsset.addMouseListener(l);
        
        
        logout = new JLabel("Logout");
        logout.setForeground(Color.white);
        logout.setFont(new Font("calibri", Font.PLAIN, 45));
        logout.addMouseListener(l);
        
       //change(new AddGUI(), addAsset);

        Class.forName("com.mysql.jdbc.Driver");
        //connect = login.connect;

    }
    
    private void setBounds() {
        bg.setBounds(0, 0, bg.getIcon().getIconWidth(), bg.getIcon().getIconHeight());
        panel.setBounds(0, bg.getIcon().getIconHeight(), panel.getWidth(), panel.getHeight());
        addAsset.setBounds(283, 8, addAsset.getIcon().getIconWidth(), addAsset.getIcon().getIconHeight());
        deleteAsset.setBounds(355, 4, deleteAsset.getIcon().getIconWidth(), deleteAsset.getIcon().getIconHeight());
        editAsset.setBounds(425, 4, editAsset.getIcon().getIconWidth(), editAsset.getIcon().getIconHeight());
        viewAsset.setBounds(505, 4, viewAsset.getIcon().getIconWidth(), viewAsset.getIcon().getIconHeight());
        logout.setBounds(725, 8, 200, 45);
        
    }
     private void setFrame() {
        this.setSize(bg.getIcon().getIconWidth(), bg.getIcon().getIconHeight() + panel.getHeight());
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Asset Registry");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void addToFrame() {
       this.add(logout);
       this.add(addAsset);
        this.add(editAsset);
        this.add(deleteAsset);
        this.add(viewAsset);
      this.add(Panel);
        this.add(panel);
        this.add(bg);

    }
    
     public void setExitIcons(){
        addAsset.setIcon(addAssetIcon[0]);
        deleteAsset.setIcon(deleteAssetIcon[0]);
        viewAsset.setIcon(viewAssetIcon[0]);
        editAsset.setIcon(editAssetIcon[0]);
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
         this.add(Panel);
        this.panel.setBounds(0, bg.getIcon().getIconHeight(), panel.getWidth(), panel.getHeight());
        this.panelbutton = panelbutton;
        panelbutton.removeMouseListener(l);
        panelbutton.setEnabled(false);
    }
    
    private String getUsername() throws SQLException {
        return UserDAO.getInstance().getUser(username).getUsername();
    }

    public class Listener implements MouseListener {

        navigator frame;
        String equipName;
        String equipID;
        String description;
        String status;

        public Listener(navigator frame) {
            this.frame = frame;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
           
        }

        @Override
        public void mousePressed(MouseEvent e) {
            /*if (e.getSource() == addAsset) {
               change(new AddGUI(), addAsset);
            } else if (e.getSource() == deleteAsset) {
                change(new DeleteGUI(usertype, username), deleteAsset);
            } else if (e.getSource() == viewAsset) {
               change(new viewassetNavigator(usertype,username), viewAsset);
            } else if (e.getSource() == editAsset) {
                 change(new EditGUI(), editAsset);
            } else if (e.getSource() == logout){
                try {
                    systemLogDAO.getInstance().saveAccess("Logged out from the system", login.username.getText().toString());
                } catch (SQLException ex) {
                    Logger.getLogger(navigator.class.getName()).log(Level.SEVERE, null, ex);
                }
                frame.setVisible(false);
                login l = new login();
            }*/
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == addAsset) {
                addAsset.setIcon(addAssetIcon[1]);
            } else if (e.getSource() == editAsset) {
                editAsset.setIcon(editAssetIcon[1]);
            } else if (e.getSource() == deleteAsset) {
                deleteAsset.setIcon(deleteAssetIcon[1]);
            } else if (e.getSource() == viewAsset) {
                viewAsset.setIcon(viewAssetIcon[1]);
            } 
        }
          @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == addAsset) {
                addAsset.setIcon(addAssetIcon[0]);
            } else if (e.getSource() == editAsset) {
                editAsset.setIcon(editAssetIcon[0]);
            } else if (e.getSource() == deleteAsset) {
                deleteAsset.setIcon(deleteAssetIcon[0]);
            } else if (e.getSource() == viewAsset) {
                viewAsset.setIcon(viewAssetIcon[0]);
            } 
        }

        
    }
}
