package finalNavigatorModule;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * VIEW for Navigator
 */
public class NavigatorView extends JFrame {
	protected JLabel bg;
	
	private JLabel btnAddAsset;
    private JLabel btnDeleteAsset;
    private JLabel btnViewAsset;
    private JLabel btnEditAsset;
    private JLabel btnLogout;

	protected ImageIcon[] addAssetIcon;
	protected ImageIcon[] deleteAssetIcon;
	protected ImageIcon[] viewAssetIcon;
	protected ImageIcon[] editAssetIcon;

	private JPanel currentPanel;
	private JLabel currentButton;

	/**
	 * Constructor
	 */
	public NavigatorView() {       
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
		
		// IMPLEMENT THIS
		//changeCurrentPanel(new AddGUI(), btnAddAsset);
	}

	/**
	 * Creates the components
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private void initialize() throws SQLException, ClassNotFoundException {
		bg = new JLabel(new ImageIcon(NavigatorView.class.getResource("/finalNavigatorModule/menu.png")));
		
		addAssetIcon = new ImageIcon[2];  
		addAssetIcon[0] = new ImageIcon(NavigatorView.class.getResource("/finalNavigatorModule/plus.png"));
		addAssetIcon[1] = new ImageIcon(NavigatorView.class.getResource("/finalNavigatorModule/plus_ON.png"));

		deleteAssetIcon = new ImageIcon[2];
		deleteAssetIcon[0] = new ImageIcon(NavigatorView.class.getResource("/finalNavigatorModule/trashcan.png"));
		deleteAssetIcon[1] = new ImageIcon(NavigatorView.class.getResource("/finalNavigatorModule/trashcan_ON.png"));

		editAssetIcon = new ImageIcon[2];
		editAssetIcon[0] = new ImageIcon(NavigatorView.class.getResource("/finalNavigatorModule/wrench.png"));
		editAssetIcon[1] = new ImageIcon(NavigatorView.class.getResource("/finalNavigatorModule/wrench_ON.png"));

		viewAssetIcon = new ImageIcon[2];
		viewAssetIcon[0] = new ImageIcon(NavigatorView.class.getResource("/finalNavigatorModule/list.png"));
		viewAssetIcon[1] = new ImageIcon(NavigatorView.class.getResource("/finalNavigatorModule/list_ON.png"));
		
		btnAddAsset = new JLabel(addAssetIcon[0]);
        btnDeleteAsset = new JLabel(deleteAssetIcon[0]);
        btnEditAsset = new JLabel(editAssetIcon[0]);
        btnViewAsset = new JLabel(viewAssetIcon[0]);
        
        btnAddAsset.setDisabledIcon(addAssetIcon[1]);
        btnDeleteAsset.setDisabledIcon(deleteAssetIcon[1]);
        btnEditAsset.setDisabledIcon(editAssetIcon[1]);
        btnViewAsset.setDisabledIcon(viewAssetIcon[1]);       
        
        btnLogout = new JLabel("Logout");
        btnLogout.setForeground(Color.white);
        btnLogout.setFont(new Font("calibri", Font.PLAIN, 45));
	}

	/**
	 * Places the components in their places
	 */
	private void setBounds() {
		bg.setBounds(0, 0, bg.getIcon().getIconWidth(), bg.getIcon().getIconHeight());
		btnAddAsset.setBounds(283, 8, btnAddAsset.getIcon().getIconWidth(), btnAddAsset.getIcon().getIconHeight());
		btnDeleteAsset.setBounds(355, 4, btnDeleteAsset.getIcon().getIconWidth(), btnDeleteAsset.getIcon().getIconHeight());
		btnEditAsset.setBounds(425, 4, btnEditAsset.getIcon().getIconWidth(), btnEditAsset.getIcon().getIconHeight());
		btnViewAsset.setBounds(505, 4, btnViewAsset.getIcon().getIconWidth(), btnViewAsset.getIcon().getIconHeight());
		btnLogout.setBounds(725, 8, 200, 45);
	}
	
	/**
	 * Frame properties
	 */
	private void setFrame() {
		this.setSize(bg.getIcon().getIconWidth(), bg.getIcon().getIconHeight());
		this.setLayout(null);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Asset Registry");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * Adds the components to the frame
	 */
	private void addToFrame() {
		this.add(btnLogout);
		this.add(btnAddAsset);
		this.add(btnEditAsset);
		this.add(btnDeleteAsset);
		this.add(btnViewAsset);
		this.add(bg);

	}
	
	/*
	 * Other Methods
	 */
	
	public void setExitIcons(){
        btnAddAsset.setIcon(addAssetIcon[0]);
        btnDeleteAsset.setIcon(deleteAssetIcon[0]);
        btnViewAsset.setIcon(viewAssetIcon[0]);
        btnEditAsset.setIcon(editAssetIcon[0]);
     }
     
     
    public void changeCurrentPanel(JPanel panel, JLabel panelbutton) {
        if (this.currentPanel != null && this.currentButton != null) {
            this.remove(this.currentPanel);
            this.currentButton.setEnabled(true);
        }
       
        setExitIcons();
        this.currentPanel = panel;
        this.add(panel);
        this.currentPanel.setBounds(0, bg.getIcon().getIconHeight(), panel.getWidth(), panel.getHeight());
        this.currentButton = panelbutton;
    }
    
    /*
     * Add listener
     */
    
    public void addButtonListeners(MouseListener listener) {
    	btnAddAsset.addMouseListener(listener);
    	btnDeleteAsset.addMouseListener(listener);
    	btnViewAsset.addMouseListener(listener);
    	btnEditAsset.addMouseListener(listener);
    	btnLogout.addMouseListener(listener);
    }
    
    /*
     * Get components
     */
    
    public JLabel getBtnAddAsset() {
		return btnAddAsset;
	}

	public JLabel getBtnDeleteAsset() {
		return btnDeleteAsset;
	}

	public JLabel getBtnViewAsset() {
		return btnViewAsset;
	}

	public JLabel getBtnEditAsset() {
		return btnEditAsset;
	}

	public JLabel getBtnLogout() {
		return btnLogout;
	}

	public ImageIcon[] getAddAssetIcon() {
		return addAssetIcon;
	}

	public ImageIcon[] getDeleteAssetIcon() {
		return deleteAssetIcon;
	}

	public ImageIcon[] getViewAssetIcon() {
		return viewAssetIcon;
	}

	public ImageIcon[] getEditAssetIcon() {
		return editAssetIcon;
	}

	public JPanel getCurrentPanel() {
		return currentPanel;
	}

	public JLabel getCurrentButton() {
		return currentButton;
	}
}
