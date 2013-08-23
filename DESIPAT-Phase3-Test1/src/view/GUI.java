package view;

import java.sql.SQLException;
import javax.swing.JPanel;

/**
 * GUI product from the GUI Factory
 */
public abstract class GUI extends JPanel {
	public abstract void initialize() throws ClassNotFoundException,
			SQLException;

	public abstract void setBounds();

	public abstract void setPanelSize();

	public abstract void addToPanel();

	public abstract void makePanel();
}
