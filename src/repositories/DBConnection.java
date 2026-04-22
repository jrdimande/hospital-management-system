package repositories;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


public class DBConnection {
    public static Connection getConnection() {
        try {
        		
        	    String url = "jdbc:mysql://localhost:3306/hospital_db";
        	    String user = "timoteo";
        	    String password = "1234";

        	    Connection conn = DriverManager.getConnection(url, user, password);
        	    System.out.println("Conectado com sucesso!");
        	    return conn;

        } catch (SQLException e) {
            	System.out.println("Erro na conexão: " + e.getMessage());
        }
        
        return null;
    }
}
