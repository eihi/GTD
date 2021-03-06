/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gtd.view;

import gtd.Config;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author st
 */
public class MenuButton extends JButton {
    private static final Color FGCOLOR = Config.FGCOLOR;
    
    public MenuButton(String t){
        this.setText(t);
        this.setPreferredSize(new Dimension(250,48));
        this.setHorizontalAlignment(SwingConstants.LEFT);
        this.setBackground(null);
        this.setFont(getFont().deriveFont(Font.PLAIN, 24));
        this.setBorder(new EmptyBorder(5, 20, 5, 5));
        this.setFocusPainted(false);
        
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(Color.LIGHT_GRAY);
                setForeground(Color.WHITE);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(null);
                setForeground(FGCOLOR);
            }
        });
    }
}
