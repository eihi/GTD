/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gtd;

import gtd.Controllers.*;
import gtd.Views.MainView;

/**
 *
 * @author st
 */
public class GTD {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Create main view
        MainView mainView = new MainView();
        
        // Show view
        mainView.setVisible(true);
    }
    
}
