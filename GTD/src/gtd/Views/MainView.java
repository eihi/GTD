/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gtd.Views;

import gtd.Controllers.MainController;
import gtd.Models.GTD;
import gtd.Views.Modules.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;

/**
 *
 * @author st
 */
public class MainView extends JFrame implements Observer {
    private static final Dimension WINDOWSIZE = new Dimension(800, 600);
    private static final String TITLE = "Getting Things Done";
    private static final Color BGCOLOR = new Color(240,240,255);
    //private static HeaderView Header = new HeaderView(this);
    private MenuView Menu;
    private ContentView Content;
    
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
        
        // Create menu and add to view
        Menu = new MenuView(this);
        this.add(Menu, BorderLayout.WEST);
        
        // Create content and add to view
        Content = new ContentView(this);
        this.add(Content, BorderLayout.CENTER);
    }    
    	
    public void addController(ActionListener controller){
            System.out.println("View      : adding controller");
            Menu.btnActions.addActionListener(controller);
            Menu.btnContexts.addActionListener(controller);
            Menu.btnProjects.addActionListener(controller);
            Menu.btnThoughts.addActionListener(controller);
    }

    @Override
    public void update(Observable o, Object arg) {
        //who called us and what did they send?
        System.out.println ("View      : Observable is " + o.getClass() + ", object passed is " + o.getClass());
        
        //model Push 
        //parse obj
        //myTextField.setText("" + ((Integer)obj).intValue());	//obj is an Object, need to cast to an Integer
    }
}
