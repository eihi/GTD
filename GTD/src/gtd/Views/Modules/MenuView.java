/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gtd.Views.Modules;

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
public class MenuView extends JPanel implements ActionListener {
    
    public static JButton btnThoughts;
    public static JButton btnActions;
    public static JButton btnProjects;
    public static JButton btnContexts;
    
    private static final Color BGCOLOR = new Color(219,219,234);
    
    /**
     * Constructor
     */
    public MenuView() {
        setLayout(new FlowLayout());
        setBackground(BGCOLOR);
        setPreferredSize(new Dimension(250, super.getHeight()));
        setBorder(new EmptyBorder(16,0,0,0));
        
         add(btnThoughts = makeButton("Thoughts"));
        add(btnActions = makeButton("Actions"));
        add(btnProjects = makeButton("Projects"));
        add(btnContexts = makeButton("Contexts"));
    }
    
    private JButton makeButton(String commando)
    {
        JButton button = new MenuButton(commando);
        button.addActionListener(this);
        //button.setFont(MENU_FONT);
        
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        keyPressed(e.getActionCommand());
    }

    private void keyPressed(String actionCommand) {
        switch(actionCommand){
            case "Thoughts":
                //controller.loadThoughts();
                break;
            case "Actions":
                //controller.loadActions();
                break;
            case "Projects":
//                controller.loadProjects();
                break;
            case "Contexts":
//                controller.loadContexts();
                break;
            default:
                break;
        }
    }   
}
