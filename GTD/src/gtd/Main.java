/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gtd;

import gtd.Controllers.*;
import gtd.Models.GTD;
import gtd.Views.MainView;

/**
 *
 * @author st
 */
public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Create model
        GTD gtd = new GTD();
        
        // Create view
        MainView view = new MainView(gtd);
        
        // Create controller
        MainController controller = new MainController(gtd);
        
        // Show view
        view.setVisible(true);
    }
    
}
