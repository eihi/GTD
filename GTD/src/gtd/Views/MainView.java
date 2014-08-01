/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gtd.Views;

import gtd.Views.Modules.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author st
 */
public class MainView extends JFrame {
    private static final Dimension WINDOWSIZE = new Dimension(800, 600);
    private static final String TITLE = "Getting Things Done";
    private static final Color BGCOLOR = new Color(240,240,255);
    //private static HeaderView Header = new HeaderView();
    private static MenuView Menu = new MenuView();
    private static ContentView Content = new ContentView();
    
    /**
     * Constructor
     */
    public MainView() {
        
        super(TITLE);
        initMainView();
    }

    private void initMainView() {
        
        // Initialize mainview
        this.setTitle(TITLE);
        this.setLayout(new BorderLayout());
        this.setSize(WINDOWSIZE);
        this.setResizable(true);
        this.setBackground(BGCOLOR);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Add header
        // this.add(Header, BorderLayout.NORTH);
        
        // Add menu
        this.add(Menu, BorderLayout.WEST);
        
        // Add content
        this.add(Content, BorderLayout.CENTER);
    }    
}
