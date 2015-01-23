/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gtd.view;

import gtd.Config;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author st
 */
public class ThoughtDetailView extends JPanel {
    private final JTextArea notesTextArea;
    
    public ThoughtDetailView() {
        this.setLayout(new BorderLayout());
        notesTextArea = new JTextArea();
        notesTextArea.setBorder(new EmptyBorder(Config.MARGIN,Config.MARGIN,Config.MARGIN,Config.MARGIN));
        notesTextArea.setLineWrap(true);
        notesTextArea.setFont(getFont().deriveFont(Font.PLAIN, 24));
        this.add(new JScrollPane(notesTextArea), BorderLayout.CENTER);
    }
    
    public ThoughtDetailView(String text) {
        this();
        notesTextArea.setText(text);
    }
    
    public void setNotes(String text) {
        this.notesTextArea.setText(text);
    }
    
    public String getNotes() {
        return this.notesTextArea.getText();
    }
    
}
