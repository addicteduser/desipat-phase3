package view;

import javax.swing.JPanel;

/**
 * This determines which GUI to create
 */
public class GUIFactory {
    protected GUI gui;
    
    /**
     * Creates the Add Asset GUI
     * @return GUI for Add Asset
     */
    public JPanel makeAddGUI(){
        return gui = new AddGUI();       
    }
    
    /**
     * Creates the Edit Asset GUI
     * @return GUI for Edit Asset
     */
    public JPanel makeEditGUI(){
        return gui = new EditGUI();      
    }
    
    /**
     * Creates the Delete Asset GUI
     * @param type - user type of current user
     * @param username - username of current user
     * @return GUI for Delete Asset
     */
    public JPanel makeDeleteGUI(String type, String username){
        return gui = new DeleteGUI(type,username);
    }
}
