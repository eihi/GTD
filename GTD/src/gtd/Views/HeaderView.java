/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gtd.Views;

import gtd.Views.MainView;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author st
 */
public class HeaderView extends JPanel{
    
    private final MainView mainView;
    
    public JButton btnNew;
    public JButton btnDelete;
    public JButton btnEdit;
    
    private static final Color BGCOLOR = new Color(219,219,234);
    
    public HeaderView(MainView mainView) {
        this.mainView = mainView;
        setLayout(new FlowLayout());
        setBackground(BGCOLOR);
        setPreferredSize(new Dimension(550, 64));
        setBorder(new EmptyBorder(5,5,5,5));
        
        JPanel logo = new JPanel();
        logo.setLayout(new FlowLayout());
        logo.setBackground(BGCOLOR);
        logo.setPreferredSize(new Dimension(250, 54));
        add(logo);
        
        add(btnNew = makeButton("New"));
//        add(btnDelete = makeButton("Delete"));
//        btnDelete.setEnabled(false);
//        add(btnEdit = makeButton("Edit"));
//        btnEdit.setEnabled(false);
    }
    
    private JButton makeButton(String commando)
    {
        JButton button = new MenuButton(commando);
        //button.setFont(MENU_FONT);
        return button;
    }
}
