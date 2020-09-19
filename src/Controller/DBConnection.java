/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ishanka
 */
public class DBConnection {
    
    private static DBConnection dbConnection;
    private Connection connection;
    
    private DBConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunrise", "root", "7278");
    }
    
    public Connection getConnection(){
        return connection;
    }
    
    public static DBConnection getInstance() throws SQLException, ClassNotFoundException{
        if(dbConnection==null){
            dbConnection=new DBConnection();   
        }
        return dbConnection;
    }
}
