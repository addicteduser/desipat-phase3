package finalNavigatorModule;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JLabel;

import finalLoginModule.LoginController;
import finalLoginModule.LoginModel;
import finalLoginModule.LoginView;

/**
 * CONTROLLER for Navigator
 */
public class NavigatorController {
	private NavigatorView navView;
	private NavigatorModel navModel;

	public NavigatorController(NavigatorView view, NavigatorModel model) {
		this.navView = view;
		this.navModel = model;
		
		//Implement Add
		//this.navView.changeCurrentPanel(new AddGUI(), this.navView.getBtnAddAsset());
		
		this.navView.addButtonListeners(new Listener());
	}

	/*
	 * LISTENER
	 */

	/**
	 * Listener for the buttons
	 */
	public class Listener implements MouseListener {

		@Override
		public void mousePressed(MouseEvent e) {
			
			//implement changeCurrentPanel here
			if (e.getSource() == navView.getBtnAddAsset()) {
				System.out.println("Add");
			} else if (e.getSource() == navView.getBtnEditAsset()) {
				System.out.println("Edit");
			} else if (e.getSource() == navView.getBtnDeleteAsset()) {
				System.out.println("Delete");
			} else if (e.getSource() == navView.getBtnViewAsset()) {
				System.out.println("View");
			} else if (e.getSource() == navView.getBtnLogout()){
                try {
                	System.out.println("SYSTEM LOG");
                    //systemLogDAO.getInstance().saveAccess("Logged out from the system", login.username.getText().toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                navView.setVisible(false);
                new LoginController(new LoginView(), new LoginModel());
            }
			
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
		public void mouseEntered(MouseEvent e) {
			if (e.getSource() == navView.getBtnAddAsset()) {
				navView.getBtnAddAsset().setIcon(navView.getAddAssetIcon()[1]);
			} else if (e.getSource() == navView.getBtnEditAsset()) {
				navView.getBtnEditAsset().setIcon(navView.getEditAssetIcon()[1]);
			} else if (e.getSource() == navView.getBtnDeleteAsset()) {
				navView.getBtnDeleteAsset().setIcon(navView.getDeleteAssetIcon()[1]);
			} else if (e.getSource() == navView.getBtnViewAsset()) {
				navView.getBtnViewAsset().setIcon(navView.getViewAssetIcon()[1]);
			} 
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if (e.getSource() == navView.getBtnAddAsset()) {
				navView.getBtnAddAsset().setIcon(navView.getAddAssetIcon()[0]);
			} else if (e.getSource() == navView.getBtnEditAsset()) {
				navView.getBtnEditAsset().setIcon(navView.getEditAssetIcon()[0]);
			} else if (e.getSource() == navView.getBtnDeleteAsset()) {
				navView.getBtnDeleteAsset().setIcon(navView.getDeleteAssetIcon()[0]);
			} else if (e.getSource() == navView.getBtnViewAsset()) {
				navView.getBtnViewAsset().setIcon(navView.getViewAssetIcon()[0]);
			} 
		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}
		
		@Override
		public void mouseClicked(MouseEvent e) {

		}
	}
}
