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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author st
 */
public class ProjectDetailView extends JPanel {
    private final JTextField nameTextField;
    private JPanel contentPane;
    private JTextArea notesTextArea;
    
    public ProjectDetailView() {
        this.setLayout(new BorderLayout());
        
        nameTextField = new JTextField();
        nameTextField.setBorder(new EmptyBorder(Config.MARGIN,Config.MARGIN,Config.MARGIN,Config.MARGIN));
        nameTextField.setFont(getFont().deriveFont(Font.PLAIN, 48));
        
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(nameTextField, BorderLayout.NORTH);
        
        notesTextArea = new JTextArea();
        notesTextArea.setBorder(new EmptyBorder(Config.MARGIN,Config.MARGIN,Config.MARGIN,Config.MARGIN));
        notesTextArea.setLineWrap(true);
        notesTextArea.setFont(getFont().deriveFont(Font.PLAIN, 24));
        contentPane.add(new JScrollPane(notesTextArea), BorderLayout.CENTER);
                
        this.add(contentPane, BorderLayout.CENTER);
    }
    
    public ProjectDetailView(String name, String notes) {
        this();
        nameTextField.setText(name);
        notesTextArea.setText(notes);
    }
    
    public String getName() {
        return nameTextField.getText();
    }
    
    public String getNotes() {
        return notesTextArea.getText();
    }
    
}
