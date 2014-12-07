/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gtd.DAL;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ST
 */

public class DBConnector {
    private Connection con;
    
    public DBConnector() {
        try {
            this.con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/gtd_db", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isConnected(){
        return this.con != null;
    }
    
    public ResultSet query(String query) throws SQLException{
        Statement stmt = (Statement) this.con.createStatement();
        try {
            return stmt.executeQuery(query);
        } catch(SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

