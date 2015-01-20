/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gtd.Views;

import gtd.Config;
import gtd.Views.MainView;
import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

/**
 *
 * @author st
 */
public class ContentView extends JPanel {
    
    private final MainView mainView;
    private final int MARGIN = Config.MARGIN;
    
    public ContentView(MainView mainView) {
        
        this.mainView = mainView;
    }
    
    public void setContent(Component view) {
        this.add(new JScrollPane(view));
    }
}
