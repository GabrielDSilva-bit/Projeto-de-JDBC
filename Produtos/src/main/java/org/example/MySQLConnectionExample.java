package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/produtos?useSSL=false";
        String user = "root";
        String password = "senaigama";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conexão estabelecida com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
        }
    }
}