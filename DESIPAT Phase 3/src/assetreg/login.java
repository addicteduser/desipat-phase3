package assetreg;

import DB.DBFactory;
import DAO.*;
import Model.UserModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Aram
 */
public class login extends JFrame {
    
    protected JLabel bg;
    protected JLabel login;
    protected static JTextField username;
    protected JPasswordField pass;
    protected Listener l;
    protected ImageIcon loginEnter;
    protected ImageIcon loginExit;
    
    public JLabel warning;
        

    // Heeeeelllooooo Triaaaaal
    //For JDBC
    protected static Connection connect;
    private UserDAO udao = UserDAO.getInstance();
    //public static String serverUserPass;
    protected String userNameString;
    
    public login() {
        try {
             initialize();
            setBounds();
            setFrame();
            addToFrame();
        } catch (Exception e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
    
     public login(String userName) {
        userNameString = userName;
        try {
            initialize();
            setBounds();
            setFrame();
            addToFrame();
           
            this.username.setText(userName);
        } catch (Exception e) {
            System.out.println("Server Error: " + e.getMessage());
        }
        
    }
    
    private void initialize() throws Exception {
        loginExit = new ImageIcon(this.getClass().getClassLoader().getResource("pictures/login_OFF.png"));
        loginEnter = new ImageIcon(this.getClass().getClassLoader().getResource("pictures/login_OFF.png"));
        bg = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("pictures/loginbg.png")));
        
        
        login = new JLabel(loginExit);
        username = new JTextField();
        pass = new JPasswordField();
        
        warning = new JLabel();
        warning.setForeground(Color.WHITE);
        warning.setFont(new Font("calibri", Font.PLAIN, 18));
        warning.setVisible(false);

        l = new Listener(this);
       login.addMouseListener(l); 
        
//        Connection con = DBFactory.getInstance(SQLConnection.username, SQLConnection.password).getConnection();
//        connect = con; 
    }
    
    public static void main(String[] args) {

        try {
            DBFactory myFactory = DBFactory.getInstance();
            Connection myConnection = myFactory.getConnection();
            myConnection.close();
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        login l = new login();

    }
    
    private void setBounds() {
        bg.setBounds(0, 0, bg.getIcon().getIconWidth(), bg.getIcon().getIconHeight());
        login.setBounds(355, 387, login.getIcon().getIconWidth(), login.getIcon().getIconHeight());
        username.setBounds(270, 265, 250, 30);
        pass.setBounds(270, 345, 250, 30);
    }

    private void setFrame() {
        this.setSize(bg.getIcon().getIconWidth(), bg.getIcon().getIconHeight());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("Asset Registry - LogIn Page");
        this.setVisible(true);
    }

    private void addToFrame() {
        this.add(warning);
        this.add(username);
        this.add(pass);
        this.add(login);
       this.add(bg);
    }
  
      public void setText(String userName, String passWord) {
        this.username.setText(userName);
        this.pass.setText(passWord);
    }
      
      
  
     
       public class Listener implements MouseListener {

        
        protected login frame;
        protected JLabel notMatch;

        public Listener(login f) {
            frame = f;
            notMatch = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("pictures/notmatch.png")));

            notMatch.setBounds(245, 365, notMatch.getIcon().getIconWidth(), notMatch.getIcon().getIconHeight());
            frame.add(notMatch);
            notMatch.setVisible(false);
        }

        public boolean checkMatch(UserModel umdl) throws SQLException {
                if (umdl.getUsername().equals(username.getText()) && umdl.getPassword().equals(pass.getText())) {
                    return true;
                }
            return false;
        }
        
          public String getUser(String username) throws SQLException {
            return UserDAO.getInstance().getUser(username).getUsername();
        }
          
          public boolean checkFields(){
              
              
              if(username.getText().toString().equals("")){
                  warning.setText("Please enter username.");
                  warning.setBounds(275, 370, 500, 30);
                  warning.setVisible(true);
                  return false;
              }
              
              else if(pass.getText().toString().equals("")){
                  warning.setText("Please enter password.");
                  warning.setBounds(275, 370, 500, 30);
                  warning.setVisible(true);
                  return false;
              }
              
              return true;
          }
          
      @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            try {
                if (e.getSource() == login) {
                    UserModel umdl = udao.getUser(username.getText());
                    
                    if(checkFields()){
                        if(umdl.getUsername() != null || umdl.getPassword() != null ){
                            if (checkMatch(umdl)) {
                                frame.setVisible(false);
                                navigator n = new navigator(getUser(username.getText()));
                                systemLogDAO.getInstance().saveAccess("Logged in to the system", username.getText().toString());
                            }
                            else {
                                notMatch.setVisible(true);
                            }    
                            
                        }
                        else {
                                notMatch.setVisible(true);
                        }  
                    }
                }
            } catch (SQLException ex) {
                //System.out.println("ERROR: " + ex.toString());
                ex.printStackTrace();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == login) {
                login.setIcon(loginEnter);
            } 
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == login) {
                login.setIcon(loginExit);
                notMatch.setVisible(false);
                warning.setVisible(false);
            } 

        } 
    }

    
}
