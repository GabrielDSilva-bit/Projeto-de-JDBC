package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CriarTabelaProduto {
    public static void main(String[] args) {
        try (Connection conexao = FabricadeConexao.getConexao();
             Statement stmt = conexao.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS produtos (" +
                    "codigo INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nome VARCHAR(80) NOT NULL, " +
                    "preco DOUBLE NOT NULL, " +  // Tipo de dado correto para preço
                    "quantidade INT NOT NULL" +  // Tipo de dado correto para quantidade
                    ")";

            stmt.execute(sql);
            System.out.println("Tabela criada com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela: " + e.getMessage());
        }
    }
}
