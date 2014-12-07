/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gtd.DAL.RequestHandlers;
import java.sql.ResultSet;
import java.sql.SQLException;
import gtd.DAL.DBConnector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author st
 */
public class ThoughtsRequestHandler {
    private final DBConnector _dbConn;
    
    public ThoughtsRequestHandler() {
        _dbConn = new DBConnector();
    }
    
    public ResultSet GetAll() {
        String query = "SELECT * FROM thoughts";
        try {
            return _dbConn.query(query);
        } catch (SQLException ex) {
            System.out.println("Ophalen van alle thoughts is mislukt.");
            Logger.getLogger(ThoughtsRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
