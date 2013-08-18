package assetreg;
import DAO.AssetDAO;
import DAO.AssetTypeDAO;
import Model.*;
import DAO.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 *
 * @author Aram
 */
public class viewByAssetHistory extends view implements ActionListener {
      protected JLabel bg;
        protected JLabel assethistoryBG;
        protected JLabel viewhistory;
         
        protected JLabel[] assetName1;
        protected JLabel[] assetOwner1;
        protected JLabel[] dateAcquired1;
        protected JLabel viewassethistory;
        protected JLabel next;
        protected JLabel back;

        protected ImageIcon nextIcon;
        protected ImageIcon backIcon;
        
        protected int count;
        protected int count2;
        protected int current;
        protected int track; 
        
        protected Font itemsFont;
        protected String nameList;
       
       protected Connection connect;
       
       protected ImageIcon[] viewAssetIcon;
       protected ImageIcon[] assetDetailsIcon;
       
       protected viewassetNavigator n;
       protected viewByAssetHistory.Listener l;
       protected JPanel panel;
       protected JLabel panelbutton;
       
       JPanel Panel = new JPanel();
    
    ArrayList<AssetModel> amdls;
    ArrayList<AssetModel> amdl;
    ArrayList<AssetHistoryModel> ahmdl;
    ArrayList<SystemLogModel> slmdl;
    UserModel umdl;
    
    JComboBox namelist = new JComboBox();
    
     public JPanel getPanel(){
        return this.Panel;
    }
    
    public void setPanel(JPanel Panel){
        this.Panel = Panel;
    }
     

    @Override
    void initialize() {
       
        
         nextIcon = new ImageIcon(this.getClass().getClassLoader().getResource("pictures/next.png"));
         backIcon = new ImageIcon(this.getClass().getClassLoader().getResource("pictures/back.png"));
         
         // ADD ASSET names IN COMBOBOX
        try {
            ArrayList<String> name = AssetDAO.getInstance().getAllAssetNames();
            for (int i = 0; i < name.size(); i++) {
                namelist.addItem(name.get(i));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
       
    
         bg = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("pictures/assethistorybg.jpg")));
         viewhistory = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("pictures/view.png")));
         viewassethistory = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("pictures/viewassethistory.png")));
         assethistoryBG = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("pictures/assethistorybg.jpg")));
        try {
            count = getItemCount();
        } catch (SQLException ex) {
            Logger.getLogger(viewByAssetHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
         current = 0;
         
         //System.out.println(count);
         
         next = new JLabel(nextIcon);
         back = new JLabel(backIcon);
         
         next.setVisible(false);
         back.setVisible(false);
         
         assethistoryBG.setVisible(true);
         namelist.setVisible(true);
         viewhistory.setVisible(true);

        
       namelist.setSelectedIndex(0);
       namelist.addActionListener(this);
      // namelist.setVisible(false);
      // viewhistory.setVisible(false);
       
       itemsFont = new Font("Calibri", Font.BOLD, 20);
        
        l = new viewByAssetHistory.Listener();
        viewassethistory.addMouseListener(l);
        namelist.addMouseListener(l);
        next.addMouseListener(l);
        back.addMouseListener(l);
        viewhistory.addMouseListener(l);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(viewByAssetHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
        connect = login.connect;
        
     //   throw new UnsupportedOperationException("Not supported yet.");
       
    }

    @Override
    void setBounds() {
            bg.setBounds(0, 0, bg.getIcon().getIconWidth(), bg.getIcon().getIconHeight());
            next.setBounds(700, 350, next.getIcon().getIconWidth(), next.getIcon().getIconHeight());
            back.setBounds(650, 350, back.getIcon().getIconWidth(), back.getIcon().getIconHeight());
            viewassethistory.setBounds(475, 23, viewassethistory.getIcon().getIconWidth(), viewassethistory.getIcon().getIconHeight());
            namelist.setBounds(400, 24, 150, 30);
            viewhistory.setBounds(570, 30,viewhistory.getIcon().getIconWidth(), viewhistory.getIcon().getIconHeight());
        
      //  throw new UnsupportedOperationException("Not supported yet.");
           
    }

    @Override
    void setFrame() {
            Panel.setSize(bg.getIcon().getIconWidth(), bg.getIcon().getIconHeight());
            Panel.setLayout(null);
            Panel.setVisible(true);
        
        //throw new UnsupportedOperationException("Not supported yet.");
            
    }

    @Override
    void addToFrame() {
     //    Panel.add(bg);
      //  Panel.add(assethistoryBG);
    
        Panel.add(back);
        Panel.add(next);
        
        Panel.add(namelist);
        Panel.add(viewhistory);
        Panel.add(assethistoryBG);
        Panel.add(bg);
      
      //  throw new UnsupportedOperationException("Not supported yet.");
       
    }

   
   public void viewResults() {
         viewhistory.setVisible(false);
        try {
            viewAssetHistoryItems();
        } catch (SQLException ex) {
            Logger.getLogger(viewByAssetHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            systemLogDAO.getInstance().saveAccess("Viewed " + namelist.getSelectedItem().toString() + "'s history", login.username.getText().toString());
        } catch (SQLException ex) {
            Logger.getLogger(viewByAssetHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
            
                 back.setVisible(true);
                 next.setVisible(true);
        try {
            count = getItemCount();
        } catch (SQLException ex) {
            Logger.getLogger(viewByAssetHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
        
     //   throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      //  throw new UnsupportedOperationException("Not supported yet.");
    }
    
     private int getItemCount() throws SQLException {
  
        
        return assetHistoryDAO.getInstance().getAssetHistory(namelist.getSelectedItem().toString()).size();
    }
   
    
    private void viewAssetHistoryItems() throws SQLException {
        
        
        ahmdl = assetHistoryDAO.getInstance().getAssetHistory(namelist.getSelectedItem().toString());
        count = 0;
        count = getItemCount();
        
         assetName1 = new JLabel[count];
         assetOwner1 = new JLabel[count];
         dateAcquired1 = new JLabel[count];
        
       // ArrayList<AssetModel> amdl = adao.getAssetHistory(namelist.getSelectedItem().toString());
        for (int i = 0; i < count; i++) {
            
            assetName1[i] = new JLabel(ahmdl.get(i).getAssetName());
            assetOwner1[i] = new JLabel(ahmdl.get(i).getAssetOwner());
            dateAcquired1[i] = new JLabel(ahmdl.get(i).getDateAcquired().toString());
          
       
            assetName1[i].setFont(itemsFont);
            assetOwner1[i].setFont(itemsFont);
            dateAcquired1[i].setFont(itemsFont);
            assetName1[i].setForeground(Color.WHITE);
            assetOwner1[i].setForeground(Color.WHITE);
            dateAcquired1[i].setForeground(Color.WHITE);
            assetName1[i].setVisible(false);
            assetOwner1[i].setVisible(false);
            dateAcquired1[i].setVisible(false);
            Panel.add(assetName1[i]);
            Panel.add(assetOwner1[i]);
            Panel.add(dateAcquired1[i]);
            Panel.add(next);
            Panel.add(back);
            Panel.add(assethistoryBG);
            Panel.add(bg);
            setAssetHistoryBounds(assetName1[i], assetOwner1[i], dateAcquired1[i], i);
      }
           
            setAssetHistoryVisible();
          //  track++;
        
    }
    
   
     public void change(JPanel panel, JLabel panelbutton) {
        if (this.panel != null && this.panelbutton != null) {
            Panel.remove(this.panel);
            this.panelbutton.addMouseListener(l);
            this.panelbutton.setEnabled(true);
        }
       
        
        this.panel = panel;
        this.Panel = panel;
        Panel.add(panel);
       // this.add(Panel);
        this.panel.setBounds(0, bg.getIcon().getIconHeight(), panel.getWidth(), panel.getHeight());
        this.panelbutton = panelbutton;
        panelbutton.removeMouseListener(l);
        panelbutton.setEnabled(false);
    }
     
    
      private void setAssetHistoryBounds(JLabel assetName1, JLabel assetOwner1, JLabel dateAcquired1, int i){


           switch (i % 5) {
            case 0:
               assetName1.setBounds(97,100,500,30);
                assetOwner1.setBounds(367,100,500,30);
                dateAcquired1.setBounds(620, 100, 500, 30);
                break;
            case 1:
                assetName1.setBounds(97,130,500,30);
                assetOwner1.setBounds(367,130,500,30);
                dateAcquired1.setBounds(620, 130, 500, 30);
                break;
            case 2:
                assetName1.setBounds(97,160,500,30);
                assetOwner1.setBounds(367,160,500,30);
                dateAcquired1.setBounds(620, 160, 500, 30);
                break;
            case 3:
                assetName1.setBounds(97,190,500,30);
                assetOwner1.setBounds(367,190,500,30);
                dateAcquired1.setBounds(620, 190, 500, 30);
                break;
            case 4:
                assetName1.setBounds(97,220,500,30);
                assetOwner1.setBounds(367,220,500,30);
                dateAcquired1.setBounds(620, 220, 500, 30);
                break;
        }
          
        
        assethistoryBG.setBounds(0, 0, assethistoryBG.getIcon().getIconWidth(), assethistoryBG.getIcon().getIconHeight());
        
        
    }
      
      
    private void setAssetHistoryVisible() {
        
        for (int i = 0; i < count; i++) {
            if (i < current || i >= current + 5) {
                assetName1[i].setVisible(false);
                assetOwner1[i].setVisible(false);
                dateAcquired1[i].setVisible(false);
            } else {
                assetName1[i].setVisible(true);
                assetOwner1[i].setVisible(true);
                dateAcquired1[i].setVisible(true);
            }

        }
      
    }
    
     public class Listener implements MouseListener {


       
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) throws NullPointerException {
            
             if(e.getSource() == viewhistory){
                
                 viewResults();
               
            }
             
             if (e.getSource() == next) {
                if (current + 5 < count) {
                    current += 5;
                    setAssetHistoryVisible();
                }
            } else if (e.getSource() == back) {
                if (current - 5 >= 0) {
                    current -= 5;
                    setAssetHistoryVisible();
                }
            } 
               
    }
 
        @Override
        public void mouseReleased(MouseEvent e) {
      
        }

        @Override
        public void mouseEntered(MouseEvent e) {
          
        }
          @Override
        public void mouseExited(MouseEvent e) {
            
        }
          
      

        
    }
    
 
    
}
