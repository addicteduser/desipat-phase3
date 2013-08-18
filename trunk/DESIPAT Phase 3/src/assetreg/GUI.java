/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assetreg;
import java.sql.SQLException;
import javax.swing.JPanel;

/**
 *
 * @author JT
 */
public abstract class GUI extends JPanel{
    public abstract void initialize()throws ClassNotFoundException, SQLException;
    public abstract void setBounds();
    public abstract void setPanelSize();
    public abstract void addToPanel();  
    public abstract void makePanel();
}
