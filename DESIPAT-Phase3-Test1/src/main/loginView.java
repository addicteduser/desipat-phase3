package main;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;


/**
 * VIEW for Login
 * GUI
 */
public class loginView extends JFrame {

	protected JLabel lblBG;

	protected JButton btnLogin;
	
	protected JLabel lblNotMatch;
	public JLabel lblWarning;

	protected static JTextField txtUsername;
	protected JPasswordField txtPass;
	
	/**
	 * Constructor
	 */
	public loginView() {
		try {
			initialize();
			setBounds();
			setFrame();
			addToFrame();
		} catch (Exception e) {
			System.out.println("Server Error: " + e.getMessage());
		}
	}

	/**
	 * Creates the components
	 * @throws Exception
	 */
	private void initialize() throws Exception {
		lblBG = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("pictures/loginbg.png")));

		txtUsername = new JTextField();
		txtPass = new JPasswordField();
		
		btnLogin = new JButton(new ImageIcon(loginView.class.getResource("/pictures/login_OFF.png")));
		btnLogin.setRolloverIcon(new ImageIcon(loginView.class.getResource("/pictures/login_ON.png")));
		btnLogin.setSelectedIcon(new ImageIcon(loginView.class.getResource("/pictures/login_ON.png")));

		lblWarning = new JLabel();
		lblWarning.setForeground(Color.WHITE);
		lblWarning.setFont(new Font("calibri", Font.PLAIN, 18));
		lblWarning.setBounds(275, 370, 500, 30);
		lblWarning.setVisible(false);
		
		lblNotMatch = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("pictures/notmatch.png")));
		lblNotMatch.setBounds(245, 365, lblNotMatch.getIcon().getIconWidth(), lblNotMatch.getIcon().getIconHeight());
		lblNotMatch.setVisible(false);
	}

	/**
	 * Places the components in their places
	 */
	private void setBounds() {
		lblBG.setBounds(0, 0, lblBG.getIcon().getIconWidth(), lblBG.getIcon().getIconHeight());
		btnLogin.setBounds(356, 403, btnLogin.getIcon().getIconWidth(), btnLogin.getIcon().getIconHeight());
		txtUsername.setBounds(270, 265, 250, 30);
		txtPass.setBounds(270, 345, 250, 30);
	}

	/**
	 * Frame properties
	 */
	private void setFrame() {
		this.setSize(lblBG.getIcon().getIconWidth(), lblBG.getIcon().getIconHeight());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		getContentPane().setLayout(null);
		this.setTitle("Asset Registry - LogIn Page");
		this.setVisible(true);
	}

	/**
	 * Adds the components to the frame
	 */
	private void addToFrame() {
		getContentPane().add(lblWarning);
		getContentPane().add(lblNotMatch);
		getContentPane().add(txtUsername);
		getContentPane().add(txtPass);
		getContentPane().add(btnLogin);
		getContentPane().add(lblBG);
	}	
	
	/*
	 * ADD ACTION LISTENER
	 */
	public void addBtnLoginListener(ActionListener listener) {
		btnLogin.addActionListener(listener);
	}
	
	/*
	 * Other methods
	 */
	public void resetFieldsColor() {
		txtUsername.setBackground(Color.WHITE);
		txtPass.setBackground(Color.WHITE);
	}
	
	public void emptyField(JTextField field) {
		field.setBackground(Color.PINK);
	}
	
	/*
	 * GET COMPONENTS
	 */
	
	public JTextField getTxtUsername() {
		return txtUsername;
	}
	
	public JPasswordField getTxtPass() {
		return txtPass;
	}
	
	public JLabel getLblNotMatch() {
		return lblNotMatch;
	}
	
	public JLabel getLblWarning() {
		return lblWarning;
	}
}