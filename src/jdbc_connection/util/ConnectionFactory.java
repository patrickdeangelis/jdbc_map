/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc_connection.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author patrick
 */
public class ConnectionFactory {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/projeto_jdbc_map";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "1234";
   
    
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch(SQLException err) { 
            System.out.println("Error" + err.getMessage());
            return null;
        }
    }
    
}
