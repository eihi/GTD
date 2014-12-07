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
        // Create model and view
        GTD gtd = new GTD();
        MainView view = new MainView();
        
        // Tell model about view
        gtd.addObserver(view);
        
        // Create controller, tell it about model and view, initialize model
        MainController controller = new MainController();
        controller.addModel(gtd);
        controller.addView(view);
        //controller.initModel(start_value);
        
        // Show view
        view.setVisible(true);
    }
}
