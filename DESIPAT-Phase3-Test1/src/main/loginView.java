package main;

import java.awt.*;
import javax.swing.*;


/**
 * VIEW for Login
 */
public class loginView extends JFrame {

	protected JLabel bg;
	protected JLabel login;
	protected static JTextField username;
	protected JPasswordField pass;

	public JLabel warning;


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
		bg = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("pictures/loginbg.png")));

		username = new JTextField();
		pass = new JPasswordField();
		login = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("pictures/login_OFF.png")));

		warning = new JLabel();
		warning.setForeground(Color.WHITE);
		warning.setFont(new Font("calibri", Font.PLAIN, 18));
		warning.setVisible(false);
	}

	/**
	 * Places the components in their places
	 */
	private void setBounds() {
		bg.setBounds(0, 0, bg.getIcon().getIconWidth(), bg.getIcon().getIconHeight());
		login.setBounds(355, 387, login.getIcon().getIconWidth(), login.getIcon().getIconHeight());
		username.setBounds(270, 265, 250, 30);
		pass.setBounds(270, 345, 250, 30);
	}

	/**
	 * Frame properties
	 */
	private void setFrame() {
		this.setSize(bg.getIcon().getIconWidth(), bg.getIcon().getIconHeight());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		this.setTitle("Asset Registry - LogIn Page");
		this.setVisible(true);
	}

	/**
	 * Adds the components to the frame
	 */
	private void addToFrame() {
		this.add(warning);
		this.add(username);
		this.add(pass);
		this.add(login);
		this.add(bg);
	}
}