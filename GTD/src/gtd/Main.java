/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gtd;

import gtd.controller.MainController;
import gtd.view.MainView;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author st
 */
public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                
        // Create view
        MainView view = new MainView();
        
        // Create controller, tell it about view
        MainController controller = new MainController();
        controller.addView(view);
        try {
            controller.loadActions();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Show view
        view.setVisible(true);
    }
}
