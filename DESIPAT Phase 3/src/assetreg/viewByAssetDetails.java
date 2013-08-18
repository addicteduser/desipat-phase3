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
public class viewByAssetDetails extends view implements ActionListener{
    
     protected JLabel bg;
        protected JLabel assetdetailsBG;
        protected JLabel view;
        protected JLabel assetName;
        protected JLabel assetOwner;
        protected JLabel assetCustodian;
        protected JLabel assetType;
        protected JLabel dateAcquired;
        protected JLabel retentionPeriodFrom;
        protected JLabel retentionPeriodTo;
        protected JLabel maintenanceSched;
        protected JLabel financial;
        protected JLabel confidentiality;
        protected JLabel integrity;
        protected JLabel availability; 
        protected JLabel classification;
        protected JLabel storageLocation;
        protected JLabel viewassetdetails;
        protected JLabel next;
        protected JLabel back;

        protected ImageIcon nextIcon;
        protected ImageIcon backIcon;
        
        protected ImageIcon nextIcon1;
        protected ImageIcon backIcon1;
        
        protected int count;
        protected int count2;
        protected int current;
        
        protected Font itemsFont;
        protected String nameList;
       
       protected Connection connect;
       
       protected ImageIcon[] viewAssetIcon;
       protected ImageIcon[] assetDetailsIcon;
       
       protected viewassetNavigator n;
       protected viewByAssetDetails.Listener l;
       protected JPanel panel;
       protected JLabel panelbutton;
    
    ArrayList<AssetHistoryModel> ahmdl;
    ArrayList<SystemLogModel> slmdl;
    UserModel umdl;
    
    
    JComboBox namelist = new JComboBox();
    JComboBox datelist = new JComboBox();
    
    JPanel Panel = new JPanel();

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
     
    
         bg = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("pictures/assetdetailsbg.jpg")));
         assetdetailsBG = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("pictures/assetdetailsbg.jpg")));
        view = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("pictures/view.png")));
        viewassetdetails = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("pictures/viewassetdetails.png")));

      
         current = 0;
         
         next = new JLabel(nextIcon);
         back = new JLabel(backIcon);
         
         next.setVisible(false);
         back.setVisible(false);
         
        
         
       assetName = new JLabel();
       assetOwner = new JLabel();
       assetCustodian = new JLabel();
       assetType = new JLabel();
       dateAcquired = new JLabel();
       retentionPeriodFrom = new JLabel();
       retentionPeriodTo = new JLabel();
       maintenanceSched = new JLabel();
       financial = new JLabel();
       confidentiality = new JLabel();
       integrity = new JLabel();
       availability = new JLabel();
       classification = new JLabel();
       storageLocation = new JLabel();
       
 
        
       namelist.setSelectedIndex(0);
       namelist.addActionListener(this);
       namelist.setVisible(false);
       view.setVisible(false);
       
       itemsFont = new Font("Calibri", Font.BOLD, 20);
        
        l = new viewByAssetDetails.Listener();
        viewassetdetails.addMouseListener(l);
        namelist.addMouseListener(l);
        next.addMouseListener(l);
        back.addMouseListener(l);
        view.addMouseListener(l);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(viewByAssetDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        connect = login.connect;
        try {
            getAssetDetails();
        } catch (SQLException ex) {
            Logger.getLogger(viewByAssetDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    //    throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    void setBounds() {
            bg.setBounds(0, 0, bg.getIcon().getIconWidth(), bg.getIcon().getIconHeight());
            next.setBounds(700, 350, next.getIcon().getIconWidth(), next.getIcon().getIconHeight());
            back.setBounds(650, 350, back.getIcon().getIconWidth(), back.getIcon().getIconHeight());
            namelist.setBounds(400, 24, 150, 30);
            view.setBounds(570, 30,view.getIcon().getIconWidth(), view.getIcon().getIconHeight());
        
        
        
      //  throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    void setFrame() {
            Panel.setSize(bg.getIcon().getIconWidth(), bg.getIcon().getIconHeight());
            Panel.setLayout(null);
            Panel.setVisible(true);
        
        //throw new UnsupportedOperationException("Not supported yet.");
    }
     public JPanel getPanel(){
        return this.Panel;
    }
    
    public void setPanel(JPanel Panel){
        this.Panel = Panel;
    }
    @Override
    void addToFrame() {
            Panel.add(back);
            Panel.add(next);
            Panel.add(viewassetdetails);
            Panel.add(namelist);
            Panel.add(view);
            Panel.add(assetType);
            Panel.add(classification);
            Panel.add(confidentiality);
            Panel.add(integrity);
            Panel.add(availability);
            Panel.add(financial);
            Panel.add(storageLocation);
            Panel.add(assetName);
            Panel.add(assetOwner);
            Panel.add(assetCustodian);
            Panel.add(dateAcquired);
            Panel.add(retentionPeriodFrom);
            Panel.add(retentionPeriodTo);
            Panel.add(maintenanceSched);
            Panel.add(bg);
        
         
          assetdetailsBG.setVisible(true);
          namelist.setVisible(true);
          view.setVisible(true);
        //throw new UnsupportedOperationException("Not supported yet.");
    }

   
   public void viewResults() {
                 setAssetDetailsBounds();
              
                    try {
                         AssetDAO.getInstance().getAssetDetails(namelist.getSelectedItem().toString());
                    } catch (SQLException ex) {
                        Logger.getLogger(viewByAssetDetails.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                 assetType.setText(AssetModel.getInstance().getType());
                 classification.setText(AssetModel.getInstance().getClassification());
                 confidentiality.setText(AssetModel.getInstance().getConfidentiality());
                 integrity.setText(AssetModel.getInstance().getIntegrity());
                 availability.setText(AssetModel.getInstance().getAvailability());
                 financial.setText(Integer.toString(AssetModel.getInstance().getFinancial()));
                 storageLocation.setText(AssetModel.getInstance().getStoragelocation());
                 assetName.setText(AssetModel.getInstance().getAssetname());
                 assetOwner.setText(AssetModel.getInstance().getAssetowner());
                 assetCustodian.setText(AssetModel.getInstance().getAssetcustodian());
                 dateAcquired.setText(AssetModel.getInstance().getDateAcquired().toString());
                 retentionPeriodFrom.setText(AssetModel.getInstance().getRetentionPeriodFrom().toString());
                 retentionPeriodTo.setText(AssetModel.getInstance().getRetentionPeriodTo().toString());
                 maintenanceSched.setText(AssetModel.getInstance().getSchedule());
              
               
                  try {
                    systemLogDAO.getInstance().saveAccess("Viewed " + assetName.getText() + "'s details", login.username.getText().toString());
                } catch (SQLException ex) {
                    Logger.getLogger(viewByAssetDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    
     public void change(JPanel panel, JLabel panelbutton) {
        if (this.panel != null && this.panelbutton != null) {
            Panel.remove(this.panel);
            this.panelbutton.addMouseListener(l);
            this.panelbutton.setEnabled(true);
        }
       
        
        this.panel = panel;
        Panel.add(panel);
        this.panel.setBounds(0, bg.getIcon().getIconHeight(), panel.getWidth(), panel.getHeight());
        this.panelbutton = panelbutton;
        panelbutton.removeMouseListener(l);
        panelbutton.setEnabled(false);
    }
     
      private void setAssetDetailsBounds(){

        assetType.setBounds(300, 153, 500, 30); //225
        classification.setBounds(300,360,500,30); //432
        confidentiality.setBounds(300,278,500,30); //350
        integrity.setBounds(300,308,500,30); //380
        availability.setBounds(300,333,500,30); //405
        financial.setBounds(300, 253, 500, 30); //325
        storageLocation.setBounds(300,388,500,30); //460
        assetName.setBounds(300,74,500,30); //146
        assetOwner.setBounds(300,98,500,30); //170
        assetCustodian.setBounds(300,123,500,30); //195
        dateAcquired.setBounds(300, 178, 500, 30); //250
        retentionPeriodFrom.setBounds(300, 204, 500, 30); //276
        retentionPeriodTo.setBounds(430, 204, 500, 30); //276
        maintenanceSched.setBounds(300, 228, 500, 30); //300
        assetdetailsBG.setBounds(0, 0, assetdetailsBG.getIcon().getIconWidth(), assetdetailsBG.getIcon().getIconHeight());
        
        
    }
 
    
     private void getAssetDetails() throws SQLException {
        
        assetType.setFont(itemsFont);
        classification.setFont(itemsFont);
        confidentiality.setFont(itemsFont);
        integrity.setFont(itemsFont);
        availability.setFont(itemsFont);
        financial.setFont(itemsFont);
        storageLocation.setFont(itemsFont);
        assetName.setFont(itemsFont);
        assetOwner.setFont(itemsFont);
        assetCustodian.setFont(itemsFont);
        dateAcquired.setFont(itemsFont);
        retentionPeriodFrom.setFont(itemsFont);
        retentionPeriodTo.setFont(itemsFont);
        maintenanceSched.setFont(itemsFont);
        
        assetType.setForeground(Color.WHITE);
        classification.setForeground(Color.WHITE);
        confidentiality.setForeground(Color.WHITE);
        integrity.setForeground(Color.WHITE);
        availability.setForeground(Color.WHITE);
        financial.setForeground(Color.WHITE);
        storageLocation.setForeground(Color.WHITE);
        assetName.setForeground(Color.WHITE);
        assetOwner.setForeground(Color.WHITE);
        assetCustodian.setForeground(Color.WHITE);
        dateAcquired.setForeground(Color.WHITE);
        retentionPeriodFrom.setForeground(Color.WHITE);
        retentionPeriodTo.setForeground(Color.WHITE);
        maintenanceSched.setForeground(Color.WHITE);
        
    }
     
      public class Listener implements MouseListener {


       
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) throws NullPointerException {
         

             if(e.getSource() == view){
                 viewResults();
             }
    
        }

        @Override
        public void mouseReleased(MouseEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }
        
       
      }
      
  
     
     
}
