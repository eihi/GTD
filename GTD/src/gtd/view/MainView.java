/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gtd.view;

import gtd.Config;
import gtd.controller.DatabaseController;
import gtd.controller.MainController;
import gtd.model.Action;
import gtd.model.Context;
import gtd.model.Project;
import gtd.model.Status;
import gtd.model.Thought;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showConfirmDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author st
 */
public class MainView extends JFrame implements Observer {

    private static final Dimension WINDOWSIZE = Config.WINDOWSIZE;
    private static final String TITLE = Config.TITLE;
    private static final Color BGCOLOR = Config.BGCOLOR;
    public HeaderView Header;
    public MenuView Menu;
    private ContentView Content;
    private JTable resultTable;
    private MainController controller;
    public ActionDetailView actionDetailView;
    public ThoughtDetailView thoughtDetailView;
    public ProjectDetailView projectDetailView;
    public ContextDetailView contextDetailView;
    public StatusDetailView statusDetailView;

    /**
     * Constructor
     */
    public MainView() {

        // Initialize mainview
        this.setTitle(TITLE);
        this.setLayout(new BorderLayout());
        this.setSize(WINDOWSIZE);
        this.setResizable(true);
        this.setBackground(BGCOLOR);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add header
        Header = new HeaderView(this);
        Header.setHeaderText("GTD");
        this.add(Header, BorderLayout.NORTH);

        // Create menu and add to view
        Menu = new MenuView(this);
        this.add(Menu, BorderLayout.WEST);

        // Create content and add to view
        Content = new ContentView();
        this.add(Content, BorderLayout.CENTER);

        this.setVisible(true);
    }

    public void addActionListener(ActionListener controller) {
        // Header
        Header.newButton.addActionListener(controller);
        Header.saveButton.addActionListener(controller);
        Header.deleteButton.addActionListener(controller);

        // Menu
        Menu.actionsButton.addActionListener(controller);
        Menu.contextsButton.addActionListener(controller);
        Menu.projectsButton.addActionListener(controller);
        Menu.thoughtsButton.addActionListener(controller);
        Menu.statusesButton.addActionListener(controller);

        // Content
    }

    public void addController(MainController controller) {
        this.setController(controller);
    }

    public void addTable(ResultSetTableModel tableModel) {
        setResultTable(new JTable(tableModel));
        getResultTable().setAutoCreateRowSorter(true);
        getResultTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent ev) {
                if (getResultTable() != null) {
                    int selectedRow = getResultTable().getSelectedRow();
                    if (selectedRow < getResultTable().getRowCount()) {
                        int id = (int) getResultTable().getValueAt(selectedRow, 0);
                        if (ev.getValueIsAdjusting()) {
                            controller.setCurrentId(id);
                            loadDetailView(id);
                        }
                    }
                }
            }
        });

        setContent(new JScrollPane(getResultTable()));
        revalidate();
        repaint();
    }

    public void setContent(Component c) {
        Content.setContent(c);
    }

    public void loadDetailView(int id) {
        switch (getController().getCurrentTable()) {
            case "actions":
                
                Action a = getController().getAction(id);
                actionDetailView = new ActionDetailView(
                        a.getDescription(),
                        getStatusStrings(),
                        getContextStrings(),
                        getProjectStrings(),
                        a.getActionDateString(),
                        a.getStatusChangeDateString(),
                        a.isDone(),
                        a.getNotes()
                );
                actionDetailView.setSelectedStatus(controller.getNameById("statuses", a.getStatus_id()));
                actionDetailView.setSelectedContext(controller.getNameById("context", a.getContext_id()));
                actionDetailView.setSelectedProject(controller.getNameById("projects", a.getProjectId()));
                setContent(actionDetailView);
                enableButtons(true);
                break;
            case "thoughts":
                thoughtDetailView = new ThoughtDetailView(getController().getThought(id).getNotes());
                setContent(thoughtDetailView);
                enableButtons(true);
                break;
            case "projects":
                projectDetailView = new ProjectDetailView(getController().getProject(id).getName(), getController().getProject(id).getNotes());
                setContent(projectDetailView);
                enableButtons(true);
                break;
            case "context":
                contextDetailView = new ContextDetailView(getController().getContext(id).getName());
                setContent(contextDetailView);
                enableButtons(true);
                break;
            case "statuses":
                statusDetailView = new StatusDetailView(getController().getStatus(id).getName());
                setContent(statusDetailView);
                enableButtons(true);
                break;
            default:
                break;
        }
    }

    public int deleteMessage() {
        return showConfirmDialog(this, "Are you sure you want to delete this?", "Delete", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
    }

    public void saveMessage() {
        JOptionPane.showMessageDialog(this, "Saved " + controller.getCurrentTable());
    }

    public void loadDetailView() {
        switch (getController().getCurrentTable()) {
            case "actions":
                controller.loadStatusData();
                controller.loadContextData();
                controller.loadProjectData();
                controller.loadActionData();
                
                actionDetailView = new ActionDetailView(getStatusStrings(), getContextStrings(), getProjectStrings());
//                actionDetailView.setStatuses(getStatusStrings());
//                actionDetailView.setContexts(getContextStrings());
//                actionDetailView.setProjects(getProjectStrings());
                
                setContent(actionDetailView);
                enableButtons(true);
                break;
            case "thoughts":
                thoughtDetailView = new ThoughtDetailView();
                setContent(thoughtDetailView);
                enableButtons(true);
                break;
            case "projects":
                projectDetailView = new ProjectDetailView();
                setContent(projectDetailView);
                enableButtons(true);
                break;
            case "context":
                contextDetailView = new ContextDetailView();
                setContent(contextDetailView);
                enableButtons(true);
                break;
            case "statuses":
                statusDetailView = new StatusDetailView();
                setContent(statusDetailView);
                enableButtons(true);
                break;
            default:
                break;
        }
    }

    public MainController getController() {
        return controller;
    }

    public void setController(MainController controller) {
        this.controller = controller;
    }

    public JTable getResultTable() {
        return resultTable;
    }

    public void setResultTable(JTable resultTable) {
        this.resultTable = resultTable;
    }

    @Override
    public void update(Observable o, Object arg) {
        
    }

    public void enableButtons(boolean b) {
        Header.saveButton.setEnabled(b);
        Header.deleteButton.setEnabled(b);
        Header.saveButton.setVisible(b);
        Header.deleteButton.setVisible(b);
    }

    public String[] getStatusStrings() {
        controller.loadStatusData();
        String[] statuses = new String[getController().getStatuses().size()];
        for (int i = 0; i < getController().getStatuses().size(); i++) {
            statuses[i] = getController().getStatuses().get(i).getName();
        }
        return statuses;
    }

    public String[] getContextStrings() {
        controller.loadContextData();
        String[] contexts = new String[getController().getContexts().size()];
        for (int i = 0; i < getController().getContexts().size(); i++) {
            contexts[i] = getController().getContexts().get(i).getName();
        }
        return contexts;
    }

    public String[] getProjectStrings() {
        controller.loadProjectData();
        String[] projects = new String[getController().getProjects().size()];
        for (int i = 0; i < getController().getProjects().size(); i++) {
            projects[i] = getController().getProjects().get(i).getName();
        }
        return projects;
    }
}
