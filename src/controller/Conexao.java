package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection conectar() {
        try {
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/jogo_cartas", 
                "root",
                "1303"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);          
        }
    }
}