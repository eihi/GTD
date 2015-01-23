/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gtd.controller;

import gtd.model.Project;
import gtd.model.Thought;
import gtd.model.Context;
import gtd.model.Action;
import gtd.model.Status;
import gtd.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author st
 */
public class DatabaseController {

    // Properties
    private static DatabaseController databaseController;
    private Connection connection;

    public DatabaseController() {

    }

    public static DatabaseController getInstance() {
        if (databaseController == null) {
            databaseController = new DatabaseController();
        }
        return databaseController;
    }

    public int getIdByName(String table, String name) {
        String query = "SELECT t.id FROM " + table + " t WHERE t.name = '" + name + "' LIMIT 1";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);

            if (rs != null) {
                while (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
    public String getNameById(String table, int id) {
        String query = "SELECT t.name FROM " + table + " t WHERE t.id = " + id + " LIMIT 1";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);

            if (rs != null) {
                while (rs.next()) {
                    return rs.getString("name");
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return "";
    }

    public boolean connect() {
        try {
            connection = DriverManager.getConnection(Config.DATABASE_URL, Config.USERNAME, Config.PASSWORD);
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean isConnected() {
        return connection != null;
    }

    public boolean executeUpdate(String query) throws SQLException {
        if (isConnected()) // Execute query if connected
        {
            connection.createStatement().executeUpdate(query);
        }

        return true;
    }

    public ResultSet getTable(String name) throws SQLException {
        String query = "SELECT * FROM " + name; // Build query

        if (connect() && isConnected()) // Execute query if connected
        {
            return connection.createStatement().executeQuery(query);
        }

        return null;
    }

    public boolean create(Action entity) throws SQLException {
        String query = "CALL CreateAction('" + entity.getDescription() + "', '" + entity.getNotes() + "', '" + entity.getActionDateString() + "', '" + entity.getStatusChangeDateString() + "', " + (entity.isDone() ? "1" : "0") + ", " + entity.getProjectId() + ", " + entity.getContext_id() + ", " + entity.getStatus_id() + ")";

        return executeUpdate(query);
    }

    public boolean read(int id, String table, String column) throws SQLException {
        String query = "SELECT t." + column + " FROM " + table + " t WHERE t.id = " + id;

        return executeUpdate(query);
    }

    public boolean update(Action entity) throws SQLException {
        String query = "CALL UpdateAction(" + entity.getId() + ", '" + entity.getDescription() + "', '" + entity.getNotes() + "', '" + entity.getActionDateString() + "', '" + entity.getStatusChangeDateString() + "', " + (entity.isDone() ? "1" : "0") + ", " + entity.getProjectId() + ", " + entity.getContext_id() + ", " + entity.getStatus_id() + ")";

        return executeUpdate(query);
    }

    public boolean delete(Action entity) throws SQLException {
        String query = "CALL DeleteAction('" + entity.getId() + "')";

        return executeUpdate(query);
    }

    public boolean create(Thought entity) throws SQLException {
        String query = "CALL CreateThought('" + entity.getNotes() + "')";

        return executeUpdate(query);
    }

    public boolean update(Thought entity) throws SQLException {
        String query = "CALL UpdateThought('" + entity.getId() + "', '" + entity.getNotes() + "')";

        return executeUpdate(query);
    }

    public boolean delete(Thought entity) throws SQLException {
        String query = "CALL DeleteThought('" + entity.getId() + "')";

        return executeUpdate(query);
    }

    public boolean create(Context entity) throws SQLException {
        String query = "CALL CreateContext('" + entity.getName() + "')";

        return executeUpdate(query);
    }

    public boolean update(Context entity) throws SQLException {
        String query = "CALL UpdateContext('" + entity.getId() + "', '" + entity.getName() + "')";

        return executeUpdate(query);
    }

    public boolean delete(Context entity) throws SQLException {
        String query = "CALL DeleteContext('" + entity.getId() + "')";

        return executeUpdate(query);
    }

    public boolean create(Project entity) throws SQLException {
        String query = "CALL CreateProject('" + entity.getName() + "', '" + entity.getNotes() + "')";

        return executeUpdate(query);
    }

    public boolean update(Project entity) throws SQLException {
        String query = "CALL UpdateProject('" + entity.getId() + "', '" + entity.getName() + "', '" + entity.getNotes() + "')";

        return executeUpdate(query);
    }

    public boolean delete(Project entity) throws SQLException {
        String query = "CALL DeleteProject('" + entity.getId() + "')";

        return executeUpdate(query);
    }

    public boolean create(Status entity) throws SQLException {
        String query = "CALL CreateStatus('" + entity.getName() + "')";

        return executeUpdate(query);
    }

    public boolean update(Status entity) throws SQLException {
        String query = "CALL UpdateStatus('" + entity.getId() + "', '" + entity.getName() + "')";

        return executeUpdate(query);
    }

    public boolean delete(Status entity) throws SQLException {
        String query = "CALL DeleteStatus('" + entity.getId() + "')";

        return executeUpdate(query);
    }
}
