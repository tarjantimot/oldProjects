/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.training.library.db;

import hu.evo.training.library.model.DbTable;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author pappmico
 */
public class DBUtil {
    //jndi name, standalone.xml-ben definiált
    private static final String name="java:jboss/jdbc/KisDS";
    
    private static DataSource ds;
    private static Connection conn;
    
    public static void createConnection() {
        try {
            final InitialContext context = new InitialContext();
            //ds felszedése jndi alapján
            ds = (DataSource) context.lookup(name);
            conn=ds.getConnection();
        } catch (NamingException x) {
            throw new IllegalStateException(x.getLocalizedMessage(), x);
        } catch (SQLException ex) {
            throw new IllegalStateException(ex.getLocalizedMessage(), ex);
        } 
        
    }
    
    public static Connection getConnection(){
        return conn;
    }
    
    public static ResultSet selectAll(DbTable table) throws SQLException{
            String query="SELECT * FROM "+table.getTableName();
            return conn.createStatement().executeQuery(query);
    }
    

    
}
