/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gtd.view;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author st
 */
public class HeaderView extends JPanel{    
    public JButton newButton;
    public JButton deleteButton;
    public JButton saveButton;
    
    private static final Color BGCOLOR = new Color(219,219,234);
    private final JLabel headerLabel;
    private final MainView view;
    
    public HeaderView(MainView view) {
        this.view = view;
        this.setLayout(new GridLayout(0,4));
        this.setBackground(BGCOLOR);
        this.setBorder(new EmptyBorder(5,10,5,5));
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        
        // Header label
        headerLabel = new JLabel();
        headerLabel.setHorizontalAlignment(SwingConstants.LEFT);
        headerLabel.setVerticalAlignment(SwingConstants.CENTER);
        headerLabel.setFont(getFont().deriveFont(Font.BOLD, 24));
        headerLabel.setBorder(new EmptyBorder(8,8,8,8));
        headerLabel.setPreferredSize(new Dimension(200,54));
        this.add(headerLabel);
        
        // New button
        newButton = new MenuButton("New");
        this.add(newButton);
        
        // Save button
        saveButton = new MenuButton("Save");
        saveButton.setEnabled(false);
        saveButton.setVisible(false);
        this.add(saveButton);
        
        // Delete button
        deleteButton = new MenuButton("Delete");
        deleteButton.setEnabled(false);
        deleteButton.setVisible(false);   
        this.add(deleteButton);
    }
    
    public void setHeaderText(String text) {
        headerLabel.setText(text);
    }
}
