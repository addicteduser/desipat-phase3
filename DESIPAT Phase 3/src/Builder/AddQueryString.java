/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Builder;

import java.util.ArrayList;

/**
 *
 * @author sgtan_
 */
public class AddQueryString{

    private ArrayList<String> projections;
    private ArrayList<String> tables;
    private String values;

    public void setProjections(ArrayList<String> projections) {
        this.projections = projections;
    }

    public void setTables(ArrayList<String> tables) {
        this.tables = tables;
    }

    public void setValues(String values) {
        this.values = values;
    }
    
    public String toString(){
        String string ="";
        
        string = "insert into ";
        
        string += tables.get(0);
        
        string +=" (";
        
        for (int i = 0; i < projections.size(); i++){
            string += projections.get(i) +((i<projections.size()-1)?",":") ");
        }
        
        string += values;
        
        return string;
    }
}
