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
public class SelectQueryString{

    private ArrayList<String> projections;
    private ArrayList<String> tables;


    public void setProjections(ArrayList<String> projections) {
        this.projections = projections;
    }

    public void setTables(ArrayList<String> tables) {
        this.tables = tables;
    }

    public String toString(){
        
        String string = "";
        
        string = "SELECT ";
        
        for (int i = 0; i < projections.size(); i++){
            string += projections.get(i) +((i<projections.size()-1)?",":" ");
        }
        
        string += "FROM ";
        
        for (int i = 0; i < tables.size(); i++){
            string += tables.get(i) +((i<tables.size()-1)?",":" ");
        }
        
        return string;
    }
}
