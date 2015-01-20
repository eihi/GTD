/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gtd.Views;

import gtd.Config;
import gtd.Controllers.MainController;
import gtd.Models.Action;
import gtd.Models.Context;
import gtd.Models.Project;
import gtd.Models.Status;
import gtd.Models.Thought;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author st
 */
public class DetailView extends JFrame {

    // Properties
    private int id, status_id, context_id, project_id;
    private SimpleDateFormat date_format;
    private Date action_date, statuschange_date;
    private JRadioButton done;
    private final GridBagConstraints constraints;
    private final MainController controller;

    public DetailView(MainController controller) {
        this.controller = controller;
        this.setMinimumSize(new Dimension(400, 600));
        this.setLayout(new GridBagLayout());
        this.constraints = new GridBagConstraints();

    }

    private void addButtons() {
        // Add save button
        JButton saveButton = makeButton("Save");
        saveButton.addActionListener(controller);
        constraints.ipady = 0;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.weightx = 0.5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(saveButton, constraints);

        // Add delete button
        JButton deleteButton = makeButton("Delete");
        deleteButton.addActionListener(controller);
        constraints.ipady = 0;
        constraints.weightx = 0.5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 0;
        this.add(deleteButton, constraints);
    }

    public void load(Action entity) {
        this.setTitle(entity.getClass().getName().replace("gtd.Models.Entities.", ""));
        this.setVisible(true);

        // Add buttons
        this.addButtons();

        // Add description field
        JTextField descriptionText = new JTextField();
        descriptionText.setText(entity.getDescription());
        constraints.ipady = 0;
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;
        constraints.insets = new Insets(10,10,10,10);
        constraints.fill = GridBagConstraints.VERTICAL;
        //constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(descriptionText, constraints);

        // Add notes field
        JTextArea notesText = new JTextArea();
        notesText.setText(entity.getNotes());
        this.add(notesText);
    }

    public void load(Thought entity) {
        this.setTitle(entity.getClass().getName().replace("gtd.Models.Entities.", ""));

        // Add buttons
        this.addButtons();

        // Add notes field
        JTextArea notesText = new JTextArea();
        notesText.setText(entity.getNotes());
        constraints.ipady = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(notesText, constraints);

        this.setVisible(true);
    }

    public void load(Project entity) {
        this.setTitle(entity.getClass().getName().replace("gtd.Models.Entities.", ""));
        this.setVisible(true);
    }

    public void load(Status entity) {
        this.setTitle(entity.getClass().getName().replace("gtd.Models.Entities.", ""));
        this.setVisible(true);
    }

    public void load(Context entity) {
        this.setTitle(entity.getClass().getName().replace("gtd.Models.Entities.", ""));
        this.setVisible(true);
    }

    private JButton makeButton(String commando) {
        JButton button = new JButton(commando);
        button.setBorder(new EmptyBorder(8,8,8,8));
        button.setBackground(new Color(219,219,234));
        button.setFont(getFont().deriveFont(Font.PLAIN, 24));
        button.setFocusPainted(false);
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(Color.LIGHT_GRAY);
                setForeground(Color.WHITE);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(null);
                setForeground(Config.FGCOLOR);
            }
        });
        return button;
    }
}
