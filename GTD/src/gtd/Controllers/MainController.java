/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gtd.Controllers;

import gtd.Config;
import gtd.Models.Action;
import gtd.Models.Context;
import gtd.Models.Project;
import gtd.Models.Status;
import gtd.Models.Thought;
import gtd.Views.ResultSetTableModel;
import gtd.Views.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author st
 */
public class MainController implements ActionListener {

    // Properties
    private MainView view;
    private ResultSetTableModel tableModel;
    private ArrayList<Action> actions;
    private ArrayList<Project> projects;
    private ArrayList<Thought> thoughts;
    private ArrayList<Context> contexts;
    private ArrayList<Status> statuses;
    private String currentTable;

    /**
     * Constructor
     */
    public MainController() {
        // Initialize fields
        
        this.actions = new ArrayList<>();
        this.projects = new ArrayList<>();
        this.thoughts = new ArrayList<>();
        this.contexts = new ArrayList<>();
        this.statuses = new ArrayList<>();
        
        this.currentTable = "actions";
    }
    
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        try {
            switch (e.getActionCommand()) {
                case "New":
                    System.out.println("hoi");
                    DatabaseController.getInstance().create(new Action());
                    break;
                case "Save":
                    System.out.println("Save");
                    break;
                case "Delete":
                    System.out.println("Delete");
                    break;
                case "Actions":
                    loadActions();
                    tableModel.setQuery(Config.ACTIONS_QUERY);
                    currentTable = "actions";
                    break;
                case "Thoughts":
                    loadThoughts();
                    tableModel.setQuery(Config.THOUGHTS_QUERY);
                    currentTable = "thoughts";
                    break;
                case "Projects":
                    loadProjects();
                    tableModel.setQuery(Config.PROJECTS_QUERY);
                    currentTable = "projects";
                    break;
                case "Statuses":
                    loadStatuses();
                    tableModel.setQuery(Config.STATUSES_QUERY);
                    currentTable = "statuses";
                    break;
                case "Contexts":
                    loadContexts();
                    tableModel.setQuery(Config.CONTEXTS_QUERY);
                    currentTable = "context";
                    break;
                default:
                    break;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void addView(MainView view) {
        this.view = view;
        this.view.addController(this);
        this.view.addActionListener(this);
    }
    
    public String getCurrentTable() {
        return this.currentTable;
    }
    
    public ArrayList<Action> getActions() {
        return this.actions;
    }
    
    public ArrayList<Project> getProjects() {
        return this.projects;
    }
    
    public void addEntity(Action action) {
        this.actions.add(action);
    } 
    
    public void clearProjects() {
        this.projects.clear();
    }
    
    public void clearActions() {
        this.actions.clear();
    }
    
    public void clearThoughts() {
        this.thoughts.clear();
    }
    
    public void clearContexts() {
        this.contexts.clear();
    }
    
    public void clearStatuses() {
        this.statuses.clear();
    }
    
    public void addEntity(Project project) {
        this.projects.add(project);
    } 
    
    public Thought getThought(int id) {
        for (Thought entity : thoughts)
            if (id == entity.getId())
                return entity;
        return null;
    }
    
    public Action getAction(int id) {
        for (Action entity : actions) 
            if (id == entity.getId())
                return entity;
        return null;
    }
    
    public Context getContext(int id) {
        for (Context entity : contexts) 
            if (id == entity.getId())
                return entity;
        return null;
    }
    
    public Project getProject(int id) {
        for (Project entity : projects)
            if (id == entity.getId())
                return entity;
        return null;
    }
    
    public Status getStatus(int id) {
        for (Status entity : statuses) 
            if (id == entity.getId())
                return entity;
        return null;
    }
    
    public ArrayList<Thought> getThoughts() {
        return this.thoughts;
    }
    public void addEntity(Thought thought){
        this.thoughts.add(thought);
    } 
    
    public ArrayList<Context> getContexts() {
        return this.contexts;
    }
    
    public void addEntity(Context context){
        this.contexts.add(context);
    } 
    
    public ArrayList<Status> getStatuses() {
        return this.statuses;
    } 
    
    public void addEntity(Status status){
        this.statuses.add(status);
    }  

    public void loadTable() {
        if (DatabaseController.getInstance().connect()) {
            try {
                tableModel = new ResultSetTableModel(Config.JDBC_DRIVER, Config.DATABASE_URL, Config.USERNAME, Config.PASSWORD, Config.ACTIONS_QUERY);
                view.addTable(tableModel);

            } catch (ClassNotFoundException classNotFound) {
                JOptionPane.showMessageDialog(null,
                        "MySQL driver not found", "Driver not found",
                        JOptionPane.ERROR_MESSAGE);

                System.exit(1); // terminate application
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void loadActions() throws SQLException {
        ResultSet rs = DatabaseController.getInstance().getTable("actions");

        if (rs != null) {
            clearActions();
            while (rs.next()) {
                Action entity = new Action(
                        rs.getInt("id"),
                        rs.getString("description"),
                        rs.getString("Notes"),
                        rs.getInt("statuses_id"),
                        rs.getInt("context_id"),
                        rs.getInt("project_id"),
                        rs.getDate("action_date"),
                        rs.getDate("statuschange_date"),
                        rs.getBoolean("done")
                );
                entity.addObserver(this.view);
                addEntity(entity);
            }
            System.out.println("Loaded " + getActions().size() + " entitie(s)");
        }
    }

    private void loadThoughts() throws SQLException {
        ResultSet rs = DatabaseController.getInstance().getTable("thoughts");

        if (rs != null) {
            clearThoughts();
            while (rs.next()) {
                Thought entity = new Thought(
                        rs.getInt("id"),
                        rs.getString("notes")
                );
                entity.addObserver(this.view);
                addEntity(entity);
            }
        }
        System.out.println("Loaded " + getThoughts().size() + " entitie(s)");
    }

    private void loadProjects() throws SQLException {
        ResultSet rs = DatabaseController.getInstance().getTable("projects");

        if (rs != null) {
            clearProjects();
            while (rs.next()) {
                Project entity = new Project(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("notes")
                );
                entity.addObserver(this.view);
                addEntity(entity);
            }
        }
        System.out.println("Loaded " + getThoughts().size() + " entitie(s)");
    }

    private void loadStatuses() throws SQLException {
        ResultSet rs = DatabaseController.getInstance().getTable("statuses");

        if (rs != null) {
            clearStatuses();
            while (rs.next()) {
                Status entity = new Status(
                        rs.getInt("id"),
                        rs.getString("name")
                );
                entity.addObserver(this.view);
                addEntity(entity);
            }
        }
        System.out.println("Loaded " + getThoughts().size() + " entitie(s)");
    }

    private void loadContexts() throws SQLException {
        ResultSet rs = DatabaseController.getInstance().getTable("context");

        if (rs != null) {
            clearContexts();
            while (rs.next()) {
                Context entity = new Context(
                        rs.getInt("id"),
                        rs.getString("name")
                );
                entity.addObserver(this.view);
                addEntity(entity);
            }
        }
        System.out.println("Loaded " + getThoughts().size() + " entitie(s)");
    }
}
