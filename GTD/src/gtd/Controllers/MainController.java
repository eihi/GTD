/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gtd.Controllers;

import gtd.DAL.*;
import gtd.Models.*;
import gtd.Views.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author st
 */
public class MainController implements ActionListener {
    // Properties
    private GTD gtd;
    private MainView view;
    
    /**
     * Constructor
     */
    public MainController() {
        
    }
    public void actionPerformed(java.awt.event.ActionEvent e){
		
        System.out.println ("Controller: The " + e.getActionCommand() 
                + " button is clicked at " + new java.util.Date(e.getWhen())
                + " with e.paramString " + e.paramString() );

        System.out.println("Controller: acting on Model");
        
        switch(e.getActionCommand()){
            case "Thoughts":
                DBConnector dbc = new DBConnector();
                String result = "";
                try {
                    ResultSet rs = dbc.query("SELECT * FROM thoughts");
                    while (rs.next())
                        result = dbc.query("SELECT * FROM thoughts").getString(2);
                }
                catch(Exception excp) {
                    System.out.println(excp);
                }
                System.out.println(result);
                break;
            default:
                break;
        }
    }
    private void loadData() {
        // Load data if there is a dataconnection
    }

    public void addModel(GTD gtd){
        System.out.println("Controller: adding model");
        this.gtd = gtd;
    } 

    public void addView(MainView view) {
        System.out.println("Controller: adding view");
        this.view = view;
        addViewController();
    }
    
    public void addViewController(){
        this.view.addController(this);
    }
}
