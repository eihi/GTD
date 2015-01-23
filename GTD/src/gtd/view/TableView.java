/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gtd.view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author st
 */
public class TableView extends AbstractTableModel {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData metaData;
    private int numberOfRows;
    private boolean isConnected = false;
    
    public TableView() {
        
    }
    
    @Override
    public int getRowCount() {
        if (isConnected) 
            return numberOfRows;
        return 0;
    }

    @Override
    public int getColumnCount() {
        if (isConnected) {
            try {
                return metaData.getColumnCount();
            }
            catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (isConnected) {
            try {
            resultSet.absolute(rowIndex + 1);
            return resultSet.getObject(columnIndex + 1);
            }
            catch(SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
        return null;
    }
    
}
