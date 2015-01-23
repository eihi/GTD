/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gtd.view;

import gtd.Config;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author st
 */
public class ActionDetailView extends JPanel {
//    private final JPanel buttonPane;

    private final JPanel optionsPane;
    private final JTextField descriptionTextField;
    private JComboBox statusComboBox;
    private JComboBox contextComboBox;
    private JComboBox projectComboBox;
    private JTextField dateLabel;
    private JLabel statusChangeDateLabel;
    private final JCheckBox doneCheckBox;
    private final JTextArea notesTextArea;
    private final JPanel menuPane;
    private String[] statuses;
    private String[] contexts;
    private String[] projects;
    private String project;
    private String context;
    private String status;

    public ActionDetailView() {
        this.setLayout(new BorderLayout());

        // Menu
        menuPane = new JPanel();

        // Options
        optionsPane = new JPanel();
        descriptionTextField = new JTextField("Description");
        descriptionTextField.setBorder(new EmptyBorder(Config.MARGIN, Config.MARGIN, Config.MARGIN, Config.MARGIN));
        descriptionTextField.setFont(getFont().deriveFont(Font.BOLD, 24));
        statusComboBox = new JComboBox();
        statusComboBox.setBorder(new EmptyBorder(0, 4, 0, 4));
        statusComboBox.setPreferredSize(new Dimension(160,36));
        contextComboBox = new JComboBox();
        contextComboBox.setBorder(new EmptyBorder(0, 4, 0, 4));
        contextComboBox.setPreferredSize(new Dimension(160,36));
        projectComboBox = new JComboBox();
        projectComboBox.setBorder(new EmptyBorder(0, 4, 0, 4));
        projectComboBox.setPreferredSize(new Dimension(160,36));
        dateLabel = new JTextField("2015-01-03");
        dateLabel.setBorder(new EmptyBorder(0, 4, 0, 4));
        dateLabel.setPreferredSize(new Dimension(160,36));
        statusChangeDateLabel = new JLabel("2015-11-06");
        statusChangeDateLabel.setBorder(new EmptyBorder(0, 4, 0, 4));
        doneCheckBox = new JCheckBox("Done");
        doneCheckBox.setBorder(new EmptyBorder(0, 4, 0, 4));
        doneCheckBox.setSelected(false);
        
        optionsPane.add(statusComboBox);
        optionsPane.add(contextComboBox);
        optionsPane.add(projectComboBox);
        optionsPane.add(dateLabel);
        optionsPane.add(statusChangeDateLabel);
        optionsPane.add(doneCheckBox);
        optionsPane.revalidate();
        optionsPane.repaint();
        
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(this.descriptionTextField, BorderLayout.NORTH);
        
        // TextArea
        notesTextArea = new JTextArea();
        notesTextArea.setBorder(new EmptyBorder(Config.MARGIN, Config.MARGIN, Config.MARGIN, Config.MARGIN));
        notesTextArea.setLineWrap(true);
        notesTextArea.setFont(getFont().deriveFont(Font.PLAIN, 24));

        // Build gui
        menuPane.add(optionsPane);
        this.add(menuPane, BorderLayout.NORTH);
        contentPane.add(new JScrollPane(notesTextArea), BorderLayout.CENTER);
        this.add(contentPane, BorderLayout.CENTER);
    }

    public ActionDetailView(String[] status, String[] context, String[] project) {
        this();

        this.statuses = status;
        this.contexts = context;
        this.projects = project;

        this.setStatuses(statuses);
        this.setContexts(contexts);
        this.setProjects(projects);
    }

    public ActionDetailView(String description, String[] status, String[] context, String[] project, String date, String statusChangeDate, boolean done, String notes) {
        this();

        this.statuses = status;
        this.contexts = context;
        this.projects = project;

        this.descriptionTextField.setText(description);
        this.setStatuses(statuses);
        this.setContexts(contexts);
        this.setProjects(projects);

        this.dateLabel.setText(date);
        this.statusChangeDateLabel.setText(statusChangeDate);
        this.doneCheckBox.setSelected(done);
        this.notesTextArea.setText(notes);
    }

    public String getDescription() {
        return this.descriptionTextField.getText();
    }

    public void setStatuses(String[] statuses) {
        this.statuses = statuses;
        statusComboBox.removeAll();
        for (String s : statuses) {
            statusComboBox.addItem(s);
        }
        this.statusComboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String entity = (String) statusComboBox.getSelectedItem();
                status = entity;
            }
        });
        this.revalidate();
        this.repaint();
    }

    public void setSelectedStatus(String status) {
        if (statuses.length > 0) {
            for (int i = 0; i < statuses.length; i++) {
                if (statuses[i].equals(status)) {
                    this.status = status;
                    statusComboBox.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    public void setContexts(String[] contexts) {
        this.contexts = contexts;
        contextComboBox.removeAllItems();
        for (String s : contexts) {
            contextComboBox.addItem(s);
        }
        this.contextComboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String entity = (String) contextComboBox.getSelectedItem();
                context = entity;
            }
        });
        this.revalidate();
        this.repaint();
    }

    public void setSelectedContext(String context) {
        if (contexts.length > 0) {
            for (int i = 0; i < statuses.length; i++) {
                if (contexts[i].equals(context)) {
                    this.context = context;
                    contextComboBox.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    public void setProjects(String[] projects) {
        this.projects = projects;
        projectComboBox.removeAll();
        for (String s : projects) {
            projectComboBox.addItem(s);
        }
        projectComboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String entity = (String) projectComboBox.getSelectedItem();
                project = entity;
            }
        });
        this.revalidate();
        this.repaint();
    }

    public void setSelectedProject(String project) {
        if (projects.length > 0) {
            for (int i = 0; i < statuses.length; i++) {
                if (projects[i].equals(project)) {
                    this.project = project;
                    projectComboBox.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    public String getDate() {
        return this.dateLabel.getText();
    }

    public String getStatusChangeDate() {

        return this.statusChangeDateLabel.getText();
    }

    public boolean getDone() {
        return this.doneCheckBox.isSelected();
    }

    public String getNotes() {
        return this.notesTextArea.getText();
    }

    public String getProject() {
        return project;
    }

    public String getContext() {
        return context;
    }

    public String getStatus() {
        return status;
    }
}
