/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gtd.Controllers;

import gtd.Models.Project;
import gtd.Models.Thought;
import gtd.Models.Context;
import gtd.Models.Action;
import gtd.Models.Status;
import gtd.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            connection.createStatement().executeUpdate(query);

        return true;
    }

    public ResultSet getTable(String name) throws SQLException {
        String query = "SELECT * FROM " + name; // Build query

        if (connect() && isConnected()) // Execute query if connected
            return connection.createStatement().executeQuery(query);

        return null;
    }

    public boolean create(Action entity) throws SQLException {
        String query = "CALL CreateAction('" + entity.getDescription() + "', '" + entity.getNotes() + "', '" + entity.getActionDate() + "', '" + entity.getStatuschangeDate() + "', '" + (entity.isDone() ? "1" : "0") + "', '" + entity.getProjectId() + "', '" + entity.getContext_id() + "', '" + entity.getStatus_id() + "')";

        return executeUpdate(query);
    }
    
    public boolean read(int id, String table, String column) throws SQLException {
        String query = "SELECT t." + column + " FROM " + table + " t WHERE t.id = " + id;
        
        return executeUpdate(query);
    }

    public boolean update(Action entity) throws SQLException {
        String query = "CALL UpdateAction('" + entity.getId() + "', " + entity.getDescription() + "', '" + entity.getNotes() + "', '" + entity.getActionDate() + "', '" + entity.getStatuschangeDate() + "', '" + (entity.isDone() ? "1" : "0") + "', '" + entity.getProjectId() + "', '" + entity.getContext_id() + "', '" + entity.getStatus_id() + "')";

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
