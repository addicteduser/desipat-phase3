/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assetreg;




import DAO.*;
import Model.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 *
 * @author JT
 */
public class EditGUI extends GUI implements ActionListener{
    protected JLabel bg;
    protected JLabel editasset;
    protected JLabel successful;
    protected ImageIcon[] editassetIcon;
    protected String assetType;
    protected JTextField assetOwner;
    protected JTextField assetCustodian;
    protected numericTextfield financial;
    protected JTextField storageLocation;
    
    protected AssetModel details = AssetModel.getInstance();  
    Listener l;
    Listener adl;
 
    protected Connection connect;
    
    JComboBox assetName = new JComboBox();
    JComboBox typelist = new JComboBox();
    JComboBox classificationlist = new JComboBox();
    JComboBox confidentialitylist = new JComboBox();
    JComboBox integritylist = new JComboBox();
    JComboBox availabilitylist = new JComboBox();

    public String[] month = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    JComboBox dateAcquiredYear = new JComboBox();
    JComboBox dateAcquiredMonth = new JComboBox();
    JComboBox dateAcquiredDay = new JComboBox();
    JComboBox retentionPeriodFromYear = new JComboBox();
    JComboBox retentionPeriodFromMonth = new JComboBox();
    JComboBox retentionPeriodFromDay = new JComboBox();
    JComboBox retentionPeriodToYear = new JComboBox();
    JComboBox retentionPeriodToMonth = new JComboBox();
    JComboBox retentionPeriodToDay = new JComboBox();
    JComboBox maintenanceSched = new JComboBox();

    Date dateAcquired = null;
    Date retentionDate = null;

    java.sql.Date dateAcquiredSQL;
    java.sql.Date retentionPeriodFromSQL;
    java.sql.Date retentionPeriodToSQL;

    Calendar calendar = Calendar.getInstance();
     
     
    public EditGUI(){           
        makePanel();
    }
    
    public void populateYear(JComboBox year, int startYear){
        year.setModel(new DefaultComboBoxModel());
        for(int i = startYear; i <= 2030; i++){
            year.addItem(i);
        }
    }

     public void populateMonth(){
         ResourceBundle rb = ResourceBundle.getBundle("months");
         Enumeration<String> settings = rb.getKeys();
         for(int i=1; i<=12; i++){
             dateAcquiredMonth.addItem(rb.getString(Integer.toString(i)));
             retentionPeriodFromMonth.addItem(rb.getString(Integer.toString(i)));
             retentionPeriodToMonth.addItem(rb.getString(Integer.toString(i)));
         }
     }


    public void populateDay(JComboBox year, JComboBox month, JComboBox day){
        calendar.set(Integer.parseInt(year.getSelectedItem().toString()), month.getSelectedIndex(), 1);

        day.setModel(new DefaultComboBoxModel());

        for(int i = 1; i <= calendar.getActualMaximum(calendar.DAY_OF_MONTH); i++){           
            day.addItem(i);
        }

    }

    public void checkDate(Date dateAcquired, Date retentionDate){
        if(dateAcquired.after(retentionDate)){

                   retentionPeriodToYear.setBackground(Color.getHSBColor(353, 0.58f, 0.92f));
                   retentionPeriodToMonth.setBackground(Color.getHSBColor(353, 0.58f, 0.92f));
                   retentionPeriodToDay.setBackground(Color.getHSBColor(353, 0.58f, 0.92f));
               }
               else{
                   retentionPeriodToYear.setBackground(Color.white);
                   retentionPeriodToMonth.setBackground(Color.white);
                   retentionPeriodToDay.setBackground(Color.white);
               }
    }

    public void parseDate(){
         String stringDate1;
         String stringDate2;
        
         //stringDate1 = dateAcquiredMonth.getSelectedItem().toString() + " " +dateAcquiredDay.getSelectedItem().toString() + ", " + dateAcquiredYear.getSelectedItem().toString();
         stringDate1 = dateAcquiredDay.getSelectedItem().toString() + "-" + Integer.toString(dateAcquiredMonth.getSelectedIndex()+1) + "-" + dateAcquiredYear.getSelectedItem().toString();
         //stringDate2 = retentionPeriodToMonth.getSelectedItem().toString() + " " +retentionPeriodToDay.getSelectedItem().toString() + ", " + retentionPeriodToYear.getSelectedItem().toString();
         stringDate2 = retentionPeriodToDay.getSelectedItem().toString() +"-"+ Integer.toString(retentionPeriodToMonth.getSelectedIndex()+1) + "-" + retentionPeriodToYear.getSelectedItem().toString();
         
         try {
                    
            dateAcquired = new SimpleDateFormat("dd-MM-yyyy").parse(stringDate1);
            retentionDate = new SimpleDateFormat("dd-MM-yyyy").parse(stringDate2);
                    
                    
         } catch (ParseException ex) {
           Logger.getLogger(EditGUI.class.getName()).log(Level.SEVERE, null, ex);
               
         }
         checkDate(dateAcquired, retentionDate); 
            
     }
    
    public void populateDetails(AssetModel det){
        int type, dAY,  rAY, mS,conf,integ,avail,classif;
        type = dAY = rAY = mS = conf = integ = avail = classif = 0;
        assetOwner.setText(det.getAssetowner());
        assetCustodian.setText(det.getAssetcustodian());
        
        for(int i = 0;i<typelist.getItemCount();i++){
            typelist.setSelectedIndex(i);
            if(typelist.getSelectedItem().toString().equals(det.getType())){  
                type = i;
            }
        }
        typelist.setSelectedIndex(type);
        
        //Date acquired
        String[] dateAcquiredString;
        String[] retentionDateString;

        dateAcquiredString = det.getDateAcquired().toString().split("-");
        retentionDateString = det.getRetentionPeriodTo().toString().split("-");

        for(int i = 0;i<dateAcquiredYear.getItemCount();i++){
            dateAcquiredYear.setSelectedIndex(i);
            if(dateAcquiredYear.getSelectedItem().toString().equals(dateAcquiredString[0])){ 
                dAY = i;
            }
        }
        dateAcquiredYear.setSelectedIndex(dAY);        
        dateAcquiredMonth.setSelectedIndex(Integer.parseInt(dateAcquiredString[1])-1);
        dateAcquiredDay.setSelectedIndex(Integer.parseInt(dateAcquiredString[2])-1);

        for(int i = 0;i<retentionPeriodToYear.getItemCount();i++){
            retentionPeriodToYear.setSelectedIndex(i);
            if(retentionPeriodToYear.getSelectedItem().toString().equals(retentionDateString[0])){ 
                rAY = i;
            }
        }
        retentionPeriodToYear.setSelectedIndex(rAY);        
        retentionPeriodToMonth.setSelectedIndex(Integer.parseInt(retentionDateString[1])-1);
        retentionPeriodToDay.setSelectedIndex(Integer.parseInt(retentionDateString[2])-1);
        
        //Maintenance Schedule
        for(int i = 0;i<maintenanceSched.getItemCount();i++){
            maintenanceSched.setSelectedIndex(i);
            if(maintenanceSched.getSelectedItem().toString().equals(det.getSchedule())){ 
                mS = i;
            }
        }
        maintenanceSched.setSelectedIndex(mS);
        
        //Confidentiality
        for(int i = 0;i<confidentialitylist.getItemCount();i++){
           confidentialitylist.setSelectedIndex(i);
            if(confidentialitylist.getSelectedItem().toString().equals(det.getConfidentiality())){ 
                conf = i;
            }
        }
        confidentialitylist.setSelectedIndex(conf);
        
        //Integrity
        for(int i = 0;i<integritylist.getItemCount();i++){
           integritylist.setSelectedIndex(i);
            if(integritylist.getSelectedItem().toString().equals(det.getIntegrity())){ 
                integ = i;
            }
        }
        integritylist.setSelectedIndex(integ);
        
        //Availability
        for(int i = 0;i<availabilitylist.getItemCount();i++){
           availabilitylist.setSelectedIndex(i);
            if(availabilitylist.getSelectedItem().toString().equals(det.getAvailability())){ 
                avail = i;
            }
        }
        availabilitylist.setSelectedIndex(avail);
        
        //Classification
        for(int i = 0;i<classificationlist.getItemCount();i++){
           classificationlist.setSelectedIndex(i);
            if(classificationlist.getSelectedItem().toString().equals(det.getClassification())){ 
                classif = i;
            }
        }
        classificationlist.setSelectedIndex(classif);
        
        financial.setText(Integer.toString(det.getFinancial()));
        storageLocation.setText(det.getStoragelocation());   
    }
    
    
    
    @Override    
    public void initialize() throws ClassNotFoundException, SQLException {
        financial = new numericTextfield();
          
        // ADD ASSETNAMES IN COMBOBOX
        try {
            ArrayList<String> name = AssetDAO.getInstance().getAllAssetNames();
           
            for (int i = 0; i < name.size(); i++) {
                assetName.addItem(name.get(i));
            }
            try{
                AssetDAO.getInstance().getAssetDetails(assetName.getSelectedItem().toString());
            }catch (Exception e) {
               
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        // ADD ASSET TYPES IN COMBOBOX
        try {
            ArrayList<String> atmdl = AssetTypeDAO.getInstance().getAllAssetTypes();
            for (int i = 0; i < atmdl.size(); i++) {
                typelist.addItem(atmdl.get(i).toString());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // ADD CLASSIFICATION IN COMBOBOX
        try {
            
            ArrayList<String> cmdl = ClassificationDAO.getInstance().getAllClassification();
            for (int i = 0; i < cmdl.size(); i++) {
                classificationlist.addItem(cmdl.get(i).toString());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
         // ADD VALUE LIST IN COMBOBOX
        try {
            ArrayList<String> vtmdl = ValueTypeDAO.getInstance().getAllValueType();
            for (int i = 0; i < vtmdl.size(); i++) {
                confidentialitylist.addItem(vtmdl.get(i).toString());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            ArrayList<String> vtmdl = ValueTypeDAO.getInstance().getAllValueType();
            for (int i = 0; i < vtmdl.size(); i++) {
                availabilitylist.addItem(vtmdl.get(i).toString());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            ArrayList<String> vtmdl = ValueTypeDAO.getInstance().getAllValueType();
            for (int i = 0; i < vtmdl.size(); i++) {
                integritylist.addItem(vtmdl.get(i).toString());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        try {
            ArrayList<String> msmdl = MaintenanceSchedDAO.getInstance().getAllMaintenanceSchedule();
            for (int i = 0; i < msmdl.size(); i++) {
                maintenanceSched.addItem(msmdl.get(i).toString());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        populateYear(dateAcquiredYear, Calendar.getInstance().get(Calendar.YEAR));
        //populateYear(retentionPeriodToYear, calendar.getInstance().get(Calendar.YEAR));
        populateMonth();
        typelist.setSelectedIndex(0);
        typelist.addActionListener(this);
        classificationlist.setSelectedIndex(0);
        classificationlist.addActionListener(this);
        confidentialitylist.setSelectedIndex(0);
        confidentialitylist.addActionListener(this);
        integritylist.setSelectedIndex(0);
        integritylist.addActionListener(this);
        availabilitylist.setSelectedIndex(0);
        availabilitylist.addActionListener(this);
         
        retentionPeriodFromDay.setEnabled(false);
        retentionPeriodFromMonth.setEnabled(false);
        retentionPeriodFromYear.setEnabled(false);
        retentionPeriodToYear.setEnabled(false);
        retentionPeriodToMonth.setEnabled(false);
        retentionPeriodToDay.setEnabled(false);
              
        dateAcquiredYear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                populateDay(dateAcquiredYear, dateAcquiredMonth, dateAcquiredDay);
                populateYear(retentionPeriodToYear, Integer.parseInt(dateAcquiredYear.getSelectedItem().toString()));
                
            }
        });
         dateAcquiredMonth.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                populateDay(dateAcquiredYear, dateAcquiredMonth, dateAcquiredDay);
                parseDate();
               
            }
        });
         
        dateAcquiredDay.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                populateDay(dateAcquiredYear, dateAcquiredMonth, retentionPeriodFromDay);
                populateYear(retentionPeriodFromYear, Integer.parseInt(dateAcquiredYear.getSelectedItem().toString()));
                retentionPeriodFromYear.setSelectedItem(dateAcquiredYear.getSelectedItem());
                retentionPeriodFromMonth.setSelectedItem(dateAcquiredMonth.getSelectedItem());
                retentionPeriodFromDay.setSelectedItem(dateAcquiredDay.getSelectedItem());
                
                retentionPeriodToYear.setEnabled(true);
                retentionPeriodToMonth.setEnabled(true);
                retentionPeriodToDay.setEnabled(true);
                
                
                 //t.start();
//                populateYear(retentionPeriodToYear, Integer.parseInt(dateAcquiredYear.getSelectedItem().toString()));
              
            }
        });
        
        retentionPeriodToYear.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e) {
               
                populateDay(retentionPeriodToYear, retentionPeriodToMonth, retentionPeriodToDay);
                
            }

        });        
             
        
        retentionPeriodToMonth.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                populateDay(retentionPeriodToYear, retentionPeriodToMonth, retentionPeriodToDay);
                parseDate();
            }   
            
        });
         
        retentionPeriodToDay.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                parseDate();
               
            }

        });    
        
         assetName.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                 try {
                     AssetDAO.getInstance().getAssetDetails(assetName.getSelectedItem().toString());
                     populateDetails(details);
                      
                 } catch (SQLException ex) {
                     ex.printStackTrace();
                 }
               
            }
        });    
                
        editassetIcon = new ImageIcon[2];
        editassetIcon[0] = new ImageIcon(this.getClass().getClassLoader().getResource("pictures/editasset.png"));
        editassetIcon[1] = new ImageIcon(this.getClass().getClassLoader().getResource("pictures/editasset.png"));
        
        bg = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("pictures/addassetbg.png")));
         
        editasset = new JLabel(editassetIcon[0]);
        successful = new JLabel("The asset has been successfully added!");

        successful.setForeground(Color.WHITE);
        successful.setFont(new Font("calibri", Font.ITALIC, 20));
        successful.setVisible(false);
        
        
        assetOwner = new JTextField();
        assetCustodian = new JTextField();
        storageLocation = new JTextField();
    //    financial = new JTextField();
        bg = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("pictures/editassetbg.png")));
        Class.forName("com.mysql.jdbc.Driver");
        connect = login.connect;
        
        l = new Listener();
        editasset.addMouseListener(l);
        typelist.addMouseListener(l);
        classificationlist.addMouseListener(l);
        confidentialitylist.addMouseListener(l);
        availabilitylist.addMouseListener(l);
        integritylist.addMouseListener(l);
        storageLocation.addMouseListener(l);
        assetName.addMouseListener(l);
        assetOwner.addMouseListener(l);
        financial.addMouseListener(l);
        assetCustodian.addMouseListener(l);
         
    }
    
    @Override   
    public void setBounds() {
        bg.setBounds(0, 0, bg.getIcon().getIconWidth(), bg.getIcon().getIconHeight());
        editasset.setBounds(720, 477, editasset.getIcon().getIconWidth(), editasset.getIcon().getIconHeight());
        typelist.setBounds(300, 95, 110, 30);
        classificationlist.setBounds(300,430,110,30);
        confidentialitylist.setBounds(300,300,110,30);
        integritylist.setBounds(300,343,110,30);
        availabilitylist.setBounds(300,383,110,30);
        successful.setBounds(500, 450, 500, 30);
        
        financial.setBounds(300, 258, 110, 30);
        storageLocation.setBounds(300,471,110,30);
        assetName.setBounds(340,20,200,24);
        assetOwner.setBounds(340,51,200,24);
        assetCustodian.setBounds(693,20,170,24);
        
        dateAcquiredYear.setBounds(300, 135, 110, 30);
        dateAcquiredMonth.setBounds(410, 135, 110,30);
        dateAcquiredDay.setBounds(520, 135, 110, 30);
        retentionPeriodFromYear.setBounds(300, 175, 90, 30);
        retentionPeriodFromMonth.setBounds(390, 175, 90, 30);
        retentionPeriodFromDay.setBounds(480, 175, 90, 30);
        retentionPeriodToYear.setBounds(600, 175, 90, 30);
        retentionPeriodToMonth.setBounds(690, 175, 90, 30);
        retentionPeriodToDay.setBounds(780, 175, 90, 30);
        maintenanceSched.setBounds(300, 215, 110, 30);
      
        
    }
        
    @Override
    public void setPanelSize() {
        this.setSize(bg.getIcon().getIconWidth(), bg.getIcon().getIconHeight());
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void addToPanel() {
        this.add(editasset);
        this.add(typelist);
        this.add(dateAcquiredYear);
        this.add(dateAcquiredMonth);
        this.add(dateAcquiredDay);
        this.add(retentionPeriodFromYear);
        this.add(retentionPeriodFromMonth);
        this.add(retentionPeriodFromDay);
        this.add(retentionPeriodToYear);
        this.add(retentionPeriodToMonth);
        this.add(retentionPeriodToDay);
        this.add(maintenanceSched);
        this.add(classificationlist);
        this.add(confidentialitylist);
        this.add(integritylist);
        this.add(availabilitylist);
        this.add(successful);
        this.add(assetName);
        this.add(assetCustodian);
        this.add(financial);
        this.add(storageLocation);
        this.add(assetOwner);
        
        this.add(bg);
    }
    
    @Override
    public void makePanel() {
        try {
            initialize();
            setBounds();
            setPanelSize();
            addToPanel();
            populateDay(dateAcquiredYear, dateAcquiredMonth, dateAcquiredDay);
            populateYear(retentionPeriodToYear, Integer.parseInt(dateAcquiredYear.getSelectedItem().toString()));
            populateDay(retentionPeriodToYear, retentionPeriodToMonth, retentionPeriodToDay);
            try{
            populateDetails(details);
            }catch (Exception ex) {            
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    
    private boolean blankCheck() {
        
        int i = 0;

        if (assetCustodian.getText().equals("")) {
            assetCustodian.setBackground(Color.getHSBColor(353, 0.58f, 0.92f));
             i++;
        } else {
            assetCustodian.setBackground(Color.white);
        }

        if (financial.getText().equals("")) {
            financial.setBackground(Color.getHSBColor(353, 0.58f, 0.92f));
             i++;
        } else {
            financial.setBackground(Color.white);
        }
        
        if (storageLocation.getText().equals("")) {
            storageLocation.setBackground(Color.getHSBColor(353, 0.58f, 0.92f));
            i++;
        } else {
            storageLocation.setBackground(Color.white);
        }

        if (assetOwner.getText().equals("")) {
            assetOwner.setBackground(Color.getHSBColor(353, 0.58f, 0.92f));
             i++;
        } else {
            assetOwner.setBackground(Color.white);
        }
        if(dateAcquired == null && retentionDate == null){
            i++;
            successful.setText("Please enter date acquired and retention period.");
            successful.setBounds(460, 450, 500, 30);
            successful.setVisible(true);
        }
                   
        if(retentionPeriodToDay.getBackground().equals(Color.getHSBColor(353, 0.58f, 0.92f)) &&
           retentionPeriodToMonth.getBackground().equals(Color.getHSBColor(353, 0.58f, 0.92f)) &&
           retentionPeriodToYear.getBackground().equals(Color.getHSBColor(353, 0.58f, 0.92f))  ){
            i++;
            successful.setText("Invalid Retention Period.");
            successful.setBounds(660, 450, 500, 30);
            successful.setVisible(true);
        }
      
        if(i==0)
        return true;
        
        return false;
    }
 
    private void compareDetails() throws SQLException{
        if(!AssetModel.getInstance().getAssetowner().equals(assetOwner.getText())){
            systemLogDAO.getInstance().saveAccess("Edited " + assetName.getSelectedItem().toString() + "'s owner", login.username.getText().toString());
            assetHistoryDAO.getInstance().saveHistory(assetName.getSelectedItem().toString(), AssetDAO.getInstance().getAssetIdentifier(assetName.getSelectedItem().toString()), assetOwner.getText(), dateAcquiredSQL);
            //OwnerDAO.getInstance().saveOwner(assetOwner.getText(), AssetDAO.getInstance().getAssetIdentifier(assetName.getSelectedItem().toString()), assetName.getSelectedItem().toString());
        }    
        if(!AssetModel.getInstance().getAssetcustodian().equals(assetCustodian.getText()))
            systemLogDAO.getInstance().saveAccess("Edited " + assetName.getSelectedItem().toString() + "'s custodian", login.username.getText().toString());
        if(!AssetModel.getInstance().getType().equals(typelist.getSelectedItem().toString()))
            systemLogDAO.getInstance().saveAccess("Edited " + assetName.getSelectedItem().toString() + "'s type", login.username.getText().toString());
        if(!AssetModel.getInstance().getDateAcquired().equals(dateAcquiredSQL))
            systemLogDAO.getInstance().saveAccess("Edited " + assetName.getSelectedItem().toString() + "'s acquire date", login.username.getText().toString());
        if(!AssetModel.getInstance().getRetentionPeriodFrom().equals(dateAcquiredSQL))
            systemLogDAO.getInstance().saveAccess("Edited " + assetName.getSelectedItem().toString() + "'s retention start date", login.username.getText().toString());
        if(!AssetModel.getInstance().getRetentionPeriodTo().equals(retentionPeriodToSQL))
            systemLogDAO.getInstance().saveAccess("Edited " + assetName.getSelectedItem().toString() + "'s retention end date", login.username.getText().toString());
        if(!AssetModel.getInstance().getSchedule().equals(maintenanceSched.getSelectedItem().toString()))
            systemLogDAO.getInstance().saveAccess("Edited " + assetName.getSelectedItem().toString() + "'s maintenance schedule", login.username.getText().toString());
        if(!(AssetModel.getInstance().getFinancial()== Integer.parseInt(financial.getText())))
            systemLogDAO.getInstance().saveAccess("Edited " + assetName.getSelectedItem().toString() + "'s financial", login.username.getText().toString());
        if(!AssetModel.getInstance().getConfidentiality().equals(confidentialitylist.getSelectedItem().toString()))
            systemLogDAO.getInstance().saveAccess("Edited " + assetName.getSelectedItem().toString() + "'s confidentiality", login.username.getText().toString());
        if(!AssetModel.getInstance().getIntegrity().equals(integritylist.getSelectedItem().toString()))
            systemLogDAO.getInstance().saveAccess("Edited " + assetName.getSelectedItem().toString() + "'s integrity", login.username.getText().toString());
        if(!AssetModel.getInstance().getAvailability().equals(availabilitylist.getSelectedItem().toString()))
            systemLogDAO.getInstance().saveAccess("Edited " + assetName.getSelectedItem().toString() + "'s availability", login.username.getText().toString());
        if(!AssetModel.getInstance().getClassification().equals(classificationlist.getSelectedItem().toString()))
            systemLogDAO.getInstance().saveAccess("Edited " + assetName.getSelectedItem().toString() + "'s classification", login.username.getText().toString());
        if(!AssetModel.getInstance().getStoragelocation().equals(storageLocation.getText()))
            systemLogDAO.getInstance().saveAccess("Edited " + assetName.getSelectedItem().toString() + "'s storage location", login.username.getText().toString());
    
    }
  
    
    public class Listener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
                
                if (e.getSource() == editasset  && blankCheck()) {
                          
                    try {
                        dateAcquiredSQL = DateUtil.utilDateToSqlDate(dateAcquired);
                        retentionPeriodFromSQL = dateAcquiredSQL;
                        retentionPeriodToSQL = DateUtil.utilDateToSqlDate(retentionDate);

                    } catch (ParseException ex) {
                        Logger.getLogger(EditGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
              
                    try {
                        compareDetails();
                        AssetModel.getInstance().setAssetname(assetName.getSelectedItem().toString());
                        AssetModel.getInstance().setAssetowner(assetOwner.getText());
                        AssetModel.getInstance().setAssetcustodian(assetCustodian.getText());
                        AssetModel.getInstance().setType(typelist.getSelectedItem().toString());
                        AssetModel.getInstance().setDateAcquired(dateAcquiredSQL);
                        AssetModel.getInstance().setRetentionPeriodFrom(retentionPeriodFromSQL);
                        AssetModel.getInstance().setRetentionPeriodTo(retentionPeriodToSQL);
                        AssetModel.getInstance().setSchedule(maintenanceSched.getSelectedItem().toString());
                        AssetModel.getInstance().setFinancial(Integer.parseInt(financial.getText()));
                        AssetModel.getInstance().setConfidentiality(confidentialitylist.getSelectedItem().toString());
                        AssetModel.getInstance().setIntegrity(integritylist.getSelectedItem().toString());
                        AssetModel.getInstance().setAvailability(availabilitylist.getSelectedItem().toString());
                        AssetModel.getInstance().setClassification(classificationlist.getSelectedItem().toString());
                        AssetModel.getInstance().setStoragelocation(storageLocation.getText());
                        AssetDAO.getInstance().editAsset(AssetModel.getInstance());
                        
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(EditGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 
                    successful.setText("The asset has been successfully updated");
                    successful.setBounds(535, 450, 500, 30);
                    successful.setVisible(true);

                }  else if (e.getSource() == assetOwner) {
                    assetOwner.setText("");
                } else if (e.getSource() == assetCustodian) {
                    assetCustodian.setText("");
                } else if (e.getSource() == financial) {
                    financial.setText("");
                } else if (e.getSource() == storageLocation) {
                    storageLocation.setText("");
     
       
            }
            
                //System.out.println(ex.getMessage());
       
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == editasset) {
                editasset.setIcon(editassetIcon[1]);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == editasset) {
                editasset.setIcon(editassetIcon[0]);
                successful.setVisible(false);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
    }
    

    
}
