package org.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class NovoProduto {

    public static void main(String[] args) {
        try ( Scanner scanner = new Scanner(System.in);
              Connection conexao = FabricadeConexao.getConexao()) {

            System.out.println("Informe o nome:  ");
            String nome = scanner.next();
            System.out.println("Preço: ");
            double preco = scanner.nextDouble();
            System.out.println("Quantidade: ");
            int quantidade = scanner.nextInt();

            String sql = "INSERT INTO produtos (nome, preco, quantidade) VALUES (?,?,?)";

            try (PreparedStatement stmt = conexao.prepareStatement(sql)){

                stmt.setString(1, nome);
                stmt.setDouble(2,preco);
                stmt.setInt(3,quantidade);

                int inserted = stmt.executeUpdate();

                if (inserted > 0) {
                    System.out.println("Insercao bem sucedida");
                    scanner.close();
                }
            }
        } catch (SQLException e) {

            System.err.println("Erro ao inserir no banco:  " + e.getMessage());
        }
    }
}