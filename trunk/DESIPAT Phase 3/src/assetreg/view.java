/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assetreg;

import java.sql.SQLException;
import javax.swing.JPanel;

/**
 *
 * @author Aram
 */
abstract class view extends JPanel{
    
  
     abstract void initialize();
     abstract void setBounds();
     abstract void setFrame();
     abstract void addToFrame();
     
     public final void View(){
        
            initialize();
            setBounds();
            setFrame();
            addToFrame();
            
           
    
        }
     public abstract JPanel getPanel();
       
        
     

    
}
