/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gtd.Views;

import gtd.Controllers.MainController;
import gtd.Views.MainView;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author st
 */
public class MenuView extends JPanel {
    
    private final MainView mainView;
    
    public JButton btnThoughts;
    public JButton btnActions;
    public JButton btnProjects;
    public JButton btnContexts;
    public JButton btnStatuses;
    
    private static final Color BGCOLOR = new Color(219,219,234);
    
    /**
     * Constructor
     * @param mainView
     */
    public MenuView(MainView mainView) {
        this.mainView = mainView;
        setLayout(new FlowLayout());
        setBackground(BGCOLOR);
        setPreferredSize(new Dimension(250, super.getHeight()));
        setBorder(new EmptyBorder(16,0,0,0));
        
        add(btnActions = makeButton("Actions"));
        add(btnThoughts = makeButton("Thoughts"));
        add(btnProjects = makeButton("Projects"));
        add(btnContexts = makeButton("Contexts"));
        add(btnStatuses = makeButton("Statuses"));
    }
    
    private JButton makeButton(String commando)
    {
        JButton button = new MenuButton(commando);
        return button;
    }
}
