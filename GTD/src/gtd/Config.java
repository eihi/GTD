/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gtd;

import java.awt.Color;
import java.awt.Dimension;

/**
 *
 * @author st
 */
public final class Config {
    // Database config
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DATABASE_URL = "jdbc:mysql://localhost/gtd_db";
    public static final String USERNAME = "root";    
    public static final String PASSWORD = "";
    public static final String ACTIONS_QUERY = "SELECT a.id, a.description, a.notes, a.done FROM actions a WHERE a.done = 0";
    public static final String THOUGHTS_QUERY = "SELECT t.id, t.notes FROM thoughts t";
    public static final String PROJECTS_QUERY = "SELECT p.id, p.name, p.notes FROM projects p";
    public static final String STATUSES_QUERY = "SELECT s.id, s.name FROM statuses s";
    public static final String CONTEXTS_QUERY = "SELECT c.id, c.name FROM context c";

    
    // View config
    public static final Dimension WINDOWSIZE = new Dimension(1200, 800);
    public static final String TITLE = "Getting Things Done";
    public static final Color BGCOLOR = new Color(240,240,255);
    public static final int MARGIN = 36;
    
    public static final Color FGCOLOR = new Color(83,83,98);
    
}
