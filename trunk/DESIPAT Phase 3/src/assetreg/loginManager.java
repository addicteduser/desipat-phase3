package assetreg;

import DAO.*;
import Model.UserModel;
import java.awt.event.*;
import java.sql.*;

/**
 *
 * @author JT
 */
public class loginManager implements MouseListener{
    login l;
    //For JDBC
    protected static Connection connect;
    public static String serverName;
    public static String serverPass;
    public static String serverUserPass;
    protected String userNameString;
    public loginManager(){
        l = new login(); 
        addListeners();    
    }
    
    private void addListeners(){
        l.login.addMouseListener(this);
    }
   
     public boolean checkMatch(UserModel umdl) throws SQLException {
                if (umdl.getUsername().equals(l.username.getText()) && umdl.getPassword().equals(l.pass.getText())) {
                    return true;
                }
            return false;
        }
        
        public String getUser(String username) throws SQLException {
            return UserDAO.getInstance().getUser(username).getUsername();
        }

    @Override
    public void mouseClicked(MouseEvent me) {
        
        
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
//            try {
//                if (me.getSource() == l.login) {
//                    UserModel umdl = udao.getUser(l.username.getText());
//                    if(umdl.getUsername() != null || 
//                            umdl.getPassword() != null || 
//                            umdl.getFirstname() != null || 
//                            umdl.getLastname() != null){
//                        if (checkMatch(umdl)) {
//                            l.setVisible(false);
//                            navigator n = new navigator(getUser(l.username.getText()));
//                        }       
//                    }
//                    else {
//                        l.notMatch.setVisible(true);
                        System.out.println("Testing");
//                    }
//                }
//            } catch (SQLException ex) {
//                //System.out.println("ERROR: " + ex.toString());
//                ex.printStackTrace();
//            }
     }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }
    
}
