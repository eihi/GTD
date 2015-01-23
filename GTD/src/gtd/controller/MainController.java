/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gtd.controller;

import gtd.Config;
import gtd.model.Action;
import gtd.model.Context;
import gtd.model.Project;
import gtd.model.Status;
import gtd.model.Thought;
import gtd.view.MainView;
import gtd.view.ResultSetTableModel;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private String currentTable = "actions";
    private int currentId = 0;

    public MainController() {
        // Initialize fields
        this.actions = new ArrayList<>();
        this.projects = new ArrayList<>();
        this.thoughts = new ArrayList<>();
        this.contexts = new ArrayList<>();
        this.statuses = new ArrayList<>();

    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        try {
            switch (e.getActionCommand()) {
                case "New":
                    System.out.println("New " + getCurrentTable() + " " + currentId);
                    view.loadDetailView();
                    currentId = 0;
                    break;
                case "Save":
                    System.out.println("Save " + getCurrentTable() + " " + currentId);
                    save();
                    view.saveMessage();
                    break;
                case "Delete":
                    System.out.println();
                    if (view.deleteMessage() == 0) {
                        System.out.println("Delete " + getCurrentTable() + " " + currentId);
                        delete();
                        currentId = 0;
                        loadTable(getCurrentQuery());
                        view.enableButtons(false);
                    }
                    break;
                case "Actions":
                    loadActions();
                    break;
                case "Thoughts":
                    loadThoughts();
                    break;
                case "Projects":
                    loadProjects();
                    break;
                case "Statuses":
                    loadStatuses();
                    break;
                case "Contexts":
                    loadContexts();
                    break;
                default:
                    break;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String getCurrentQuery() {

        switch (getCurrentTable()) {
            case "actions":
                return Config.ACTIONS_QUERY;
            case "thoughts":
                return Config.THOUGHTS_QUERY;
            case "projects":
                return Config.PROJECTS_QUERY;
            case "context":
                return Config.CONTEXTS_QUERY;
            case "statuses":
                return Config.STATUSES_QUERY;
            default:
                return Config.ACTIONS_QUERY;
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

    public void addEntity(Project project) {
        this.projects.add(project);
    }

    public Thought getThought(int id) {
        for (Thought entity : thoughts) {
            if (id == entity.getId()) {
                return entity;
            }
        }
        return null;
    }

    public Action getAction(int id) {
        for (Action entity : actions) {
            if (id == entity.getId()) {
                return entity;
            }
        }
        return null;
    }

    public Context getContext(int id) {
        for (Context entity : contexts) {
            if (id == entity.getId()) {
                return entity;
            }
        }
        return null;
    }

    public Project getProject(int id) {
        for (Project entity : projects) {
            if (id == entity.getId()) {
                return entity;
            }
        }
        return null;
    }

    public Status getStatus(int id) {
        for (Status entity : statuses) {
            if (id == entity.getId()) {
                return entity;
            }
        }
        return null;
    }

    public ArrayList<Thought> getThoughts() {
        return this.thoughts;
    }

    public void addEntity(Thought thought) {
        this.thoughts.add(thought);
    }

    public ArrayList<Context> getContexts() {
        return this.contexts;
    }

    public void addEntity(Context context) {
        this.contexts.add(context);
    }

    public ArrayList<Status> getStatuses() {
        return this.statuses;
    }

    public void addEntity(Status status) {
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

    public void loadTable(String query) {
        if (DatabaseController.getInstance().connect()) {
            try {
                tableModel = new ResultSetTableModel(Config.JDBC_DRIVER, Config.DATABASE_URL, Config.USERNAME, Config.PASSWORD, query);
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

    public void loadActions() throws SQLException {
        setCurrentTable("actions");
        
        loadActionData();

        loadTable(Config.ACTIONS_QUERY);
    }

    private void loadThoughts() throws SQLException {
        this.setCurrentTable("thoughts");

        loadThoughtData();

        loadTable(Config.THOUGHTS_QUERY);
    }

    private void loadProjects() throws SQLException {
        this.setCurrentTable("projects");

        loadProjectData();

        loadTable(Config.PROJECTS_QUERY);
    }

    private void loadStatuses() throws SQLException {
        this.setCurrentTable("statuses");

        loadStatusData();

        loadTable(Config.STATUSES_QUERY);
    }

    public void loadContexts() throws SQLException {
        this.setCurrentTable("context");

        loadContextData();

        loadTable(Config.CONTEXTS_QUERY);
    }

    public void setCurrentTable(String currentTable) {
        this.currentTable = currentTable;
        view.Header.setHeaderText(currentTable.toUpperCase());
        view.enableButtons(false);
    }

    public void save() {
        System.out.println("Saving: " + currentTable);

        try {
            switch (getCurrentTable()) {
                case "actions":
                    if (currentId == 0) {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = new Date();
                        Date statusChangeDate = new Date();
                        try {
                            date = formatter.parse(view.actionDetailView.getDate());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        try {
                            statusChangeDate = formatter.parse(view.actionDetailView.getStatusChangeDate());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Action action = new Action(
                                view.actionDetailView.getDescription(),
                                view.actionDetailView.getNotes(),
                                DatabaseController.getInstance().getIdByName("statuses", view.actionDetailView.getStatus()),
                                DatabaseController.getInstance().getIdByName("context", view.actionDetailView.getContext()),
                                DatabaseController.getInstance().getIdByName("projects", view.actionDetailView.getProject()),
                                date,
                                statusChangeDate,
                                view.actionDetailView.getDone()
                        );
                        DatabaseController.getInstance().create(action);
                    } else {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = new Date();
                        Date statusChangeDate = new Date();
                        try {
                            date = formatter.parse(view.actionDetailView.getDate());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        try {
                            statusChangeDate = formatter.parse(view.actionDetailView.getStatusChangeDate());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Action a = getAction(currentId);
                        a.setDescription(view.actionDetailView.getDescription());
                        a.setNotes(view.actionDetailView.getNotes());
                        a.setDone(view.actionDetailView.getDone());
                        a.setActionDate(date);
                        a.setStatuschangeDate(statusChangeDate);
                        a.setContextId(DatabaseController.getInstance().getIdByName("context", view.actionDetailView.getContext()));
                        a.setProjectId(DatabaseController.getInstance().getIdByName("projects", view.actionDetailView.getProject()));
                        a.setStatus_id(DatabaseController.getInstance().getIdByName("statuses", view.actionDetailView.getStatus()));
                        DatabaseController.getInstance().update(a);
                    }
                    break;
                case "thoughts":
                    if (currentId == 0) {
                        Thought thought = new Thought(view.thoughtDetailView.getNotes());
                        DatabaseController.getInstance().create(thought);
                    } else {
                        DatabaseController.getInstance().update(getThought(currentId));
                    }
                    break;
                case "projects":
                    if (currentId == 0) {
                        Project project = new Project(view.projectDetailView.getName(), view.projectDetailView.getNotes());
                        DatabaseController.getInstance().create(project);
                    } else {
                        DatabaseController.getInstance().update(getProject(currentId));
                    }
                    break;
                case "context":
                    if (currentId == 0) {
                        Context context = new Context(view.contextDetailView.getName());
                        DatabaseController.getInstance().create(context);
                    } else {
                        DatabaseController.getInstance().update(getContext(currentId));
                    }
                    break;
                case "statuses":
                    if (currentId == 0) {
                        Status status = new Status(view.statusDetailView.getName());
                        DatabaseController.getInstance().create(status);
                    } else {
                        DatabaseController.getInstance().update(getStatus(currentId));
                    }
                    break;
                default:
                    break;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String getNameById(String table, int id) {
        return DatabaseController.getInstance().getNameById(table, id);
    }

    public void delete() {
        if (currentId != 0) {
            try {
                switch (getCurrentTable()) {
                    case "actions":
                        DatabaseController.getInstance().delete(getAction(currentId));
                        break;
                    case "thoughts":
                        DatabaseController.getInstance().delete(getThought(currentId));
                        break;
                    case "projects":
                        DatabaseController.getInstance().delete(getProject(currentId));
                        break;
                    case "context":
                        DatabaseController.getInstance().delete(getContext(currentId));
                        break;
                    case "statuses":
                        DatabaseController.getInstance().delete(getStatus(currentId));
                        break;
                    default:
                        break;
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void setCurrentId(int currentId) {
        this.currentId = currentId;
    }

    public void loadContextData() {

        try {
            ResultSet rs = DatabaseController.getInstance().getTable("context");
            if (rs != null) {
                this.contexts.clear();
                while (rs.next()) {
                    Context entity = new Context(
                            rs.getInt("id"),
                            rs.getString("name")
                    );
                    entity.addObserver(this.view);
                    addEntity(entity);
                }
            }
            System.out.println("Loaded " + getContexts().size() + " entitie(s)");
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadStatusData() {

        try {
            ResultSet rs = DatabaseController.getInstance().getTable("statuses");

            if (rs != null) {
                this.statuses.clear();
                while (rs.next()) {
                    Status entity = new Status(
                            rs.getInt("id"),
                            rs.getString("name")
                    );
                    entity.addObserver(this.view);
                    addEntity(entity);
                }
            }
            System.out.println("Loaded " + getStatuses().size() + " entitie(s)");
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadProjectData() {
        try {
            ResultSet rs = DatabaseController.getInstance().getTable("projects");

            if (rs != null) {
                this.projects.clear();
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
            System.out.println("Loaded " + getProjects().size() + " entitie(s)");
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadThoughtData() {
        try {
            ResultSet rs = DatabaseController.getInstance().getTable("thoughts");

            if (rs != null) {
                this.thoughts.clear();
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
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadActionData() {

        try {
            ResultSet rs = DatabaseController.getInstance().getTable("actions");

            if (rs != null) {
                this.actions.clear();
                while (rs.next()) {
                    Action entity = new Action(
                            rs.getInt("id"),
                            rs.getString("description"),
                            rs.getString("notes"),
                            rs.getInt("statuses_id"),
                            rs.getInt("context_id"),
                            rs.getInt("projects_id"),
                            rs.getDate("action_date"),
                            rs.getDate("statuschange_date"),
                            rs.getBoolean("done")
                    );
                    entity.addObserver(this.view);
                    addEntity(entity);
                }
                System.out.println("Loaded " + getActions().size() + " entitie(s)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
