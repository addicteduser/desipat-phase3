package finalLoginModule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * CONTROLLER for Login
 */
public class LoginController {
	private loginView loginView;
	private LoginModel loginModel;
	
	/**
	 * Constructor
	 * @param view - GUI
	 * @param model - Functionalities
	 */
	public LoginController(loginView view, LoginModel model) {
		this.loginView = view;
		this.loginModel = model;
		
		this.loginView.addBtnLoginListener(new LoginListener());
	}
	
	/*
	 * LISTENER
	 */
	
	/**
	 * Listener for Login Button
	 */
	public class LoginListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			boolean userOK = false;
			boolean passOK = false;
			loginView.resetFieldsColor();
			loginView.getLblNotMatch().setVisible(false);
			
			if(loginModel.checkUsernameField(loginView.getTxtUsername())) {
				userOK = true;
			} else {
				loginView.emptyField(loginView.getTxtUsername());
			}
			
			if(loginModel.checkPasswordField(loginView.getTxtPass())) {
				passOK = true;
			} else {
				loginView.emptyField(loginView.getTxtPass());
			}
			
			if(userOK && passOK) {
				try {
					loginModel.getUserDAO(loginView.getTxtUsername());
					if(loginModel.checkMatch(loginModel.getUserMdl(), loginView.getTxtUsername(), loginView.getTxtPass())) {
						loginView.setVisible(false);
						System.out.println("LOGIN SUCCESS");
						// navigator n = new navigator(getUser(username.getText()));
						System.out.println("SYSTEM LOG");
						// systemLogDAO.getInstance().saveAccess("Logged in to the system", username.getText().toString());
					} else {
						loginView.getLblNotMatch().setVisible(true);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}			
	}
}
