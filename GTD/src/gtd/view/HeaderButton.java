/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gtd.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author st
 */
public class HeaderButton extends JButton {
    private static final Color FGCOLOR = new Color(83,83,98);
    
    public HeaderButton(String t){
        this.setText(t);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setFont(getFont().deriveFont(Font.PLAIN, 24));
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        
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
