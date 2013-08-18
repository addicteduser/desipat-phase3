/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assetreg;

import javax.swing.JPanel;

/**
 *
 * @author JT
 */
public class GUIFactory {
    protected GUI gui;
    public JPanel makeAddGUI(){
        return   gui = new AddGUI();       
    }
    public JPanel makeEditGUI(){
        return gui = new EditGUI();      
    }
    public JPanel makeDeleteGUI(String type, String username){
        return gui = new DeleteGUI(type,username);
    }
}
