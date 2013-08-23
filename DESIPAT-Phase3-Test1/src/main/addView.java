/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import main.login;
import src.view.AddGUI;
import src.view.GUI;

/**
 *
 * @author Vilson
 */
public class addView extends GUI 

    protected JLabel bg;
    protected JLabel addasset;
    protected JLabel successful;
    protected ImageIcon[] addassetIcon;
    protected String assetType;
    protected JTextField assetName;
    protected JTextField assetOwner;
    protected JTextField assetCustodian;
    protected numericTextfield financial;
    protected JTextField storageLocation;
    AddGUI.Listener l;
    AddGUI.Listener adl;
    Timer t = new Timer(100, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            populateYear(retentionPeriodToYear, Integer.parseInt(dateAcquiredYear.getSelectedItem().toString()));
            t.stop();
        }
    });
    protected Connection connect;
    JComboBox typelist = new JComboBox();
    JComboBox classificationlist = new JComboBox();
    JComboBox confidentialitylist = new JComboBox();
    JComboBox integritylist = new JComboBox();
    JComboBox availabilitylist = new JComboBox();
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

    public addView() {
        try {
            initialize();
            setBounds();
            setFrame();
            addToFrame();
        } catch (Exception e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }

    @Override
    public void initialize() throws ClassNotFoundException, SQLException {

        financial = new numericTextfield();

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

            }
        });
        dateAcquiredMonth.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                populateDay(dateAcquiredYear, dateAcquiredMonth, dateAcquiredDay);

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


                t.start();
//                populateYear(retentionPeriodToYear, Integer.parseInt(dateAcquiredYear.getSelectedItem().toString()));

            }
        });

        retentionPeriodToYear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                populateDay(retentionPeriodToYear, retentionPeriodToMonth, retentionPeriodToDay);
                parseDate();
            }
        });


        retentionPeriodToMonth.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                populateDay(retentionPeriodToYear, retentionPeriodToMonth, retentionPeriodToDay);
                parseDate();
            }
        });

        retentionPeriodToDay.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                parseDate();

            }
        });

        addassetIcon = new ImageIcon[2];
        addassetIcon[0] = new ImageIcon(this.getClass().getClassLoader().getResource("pictures/addasset.png"));
        addassetIcon[1] = new ImageIcon(this.getClass().getClassLoader().getResource("pictures/addasset.png"));

        bg = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("pictures/addassetbg.png")));

        addasset = new JLabel(addassetIcon[0]);
        successful = new JLabel("The asset has been successfully added!");

        successful.setForeground(Color.WHITE);
        successful.setFont(new Font("calibri", Font.ITALIC, 20));
        successful.setVisible(false);

        assetName = new JTextField();
        assetOwner = new JTextField();
        assetCustodian = new JTextField();
        storageLocation = new JTextField();
        //    financial = new JTextField();

        Class.forName("com.mysql.jdbc.Driver");
        connect = login.connect;

        l = new AddGUI.Listener();
        addasset.addMouseListener(l);
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

    public void setBounds() {
        bg.setBounds(0, 0, bg.getIcon().getIconWidth(), bg.getIcon().getIconHeight());
        addasset.setBounds(720, 477, addasset.getIcon().getIconWidth(), addasset.getIcon().getIconHeight());
        typelist.setBounds(300, 95, 110, 30);
        classificationlist.setBounds(300, 430, 110, 30);
        confidentialitylist.setBounds(300, 300, 110, 30);
        integritylist.setBounds(300, 343, 110, 30);
        availabilitylist.setBounds(300, 383, 110, 30);
        successful.setBounds(555, 450, 500, 30);

        financial.setBounds(300, 258, 110, 30);
        storageLocation.setBounds(300, 471, 110, 30);
        assetName.setBounds(340, 20, 200, 24);
        assetOwner.setBounds(340, 51, 200, 24);
        assetCustodian.setBounds(693, 20, 170, 24);

        dateAcquiredYear.setBounds(300, 135, 110, 30);
        dateAcquiredMonth.setBounds(410, 135, 110, 30);
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
        this.add(addasset);
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
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
