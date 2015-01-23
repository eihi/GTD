/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gtd.view;

import gtd.Config;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author st
 */
public class ContentView extends JPanel {
        
    public ContentView() {
        this.setLayout(new GridLayout());
        this.setBackground(Config.BGCOLOR);
    }
    
    public void setContent(Component view) {
        this.removeAll();
        this.add(view);
        this.revalidate();
        this.repaint();
    }
}
