package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AtualizarDados {
    public static void main(String[] args) {
        try (Scanner sc1 = new Scanner(System.in);
             Connection conexao = FabricadeConexao.getConexao()) {

            System.out.println("Informe o código do produto: ");
            int codigo = sc1.nextInt();

            String select = "SELECT * FROM produtos WHERE codigo = ?";
            String update = "UPDATE produtos SET nome = ?, preco = ?, quantidade = ? WHERE codigo = ?";

            try (PreparedStatement stmt = conexao.prepareStatement(select)) {
                stmt.setInt(1, codigo);
                ResultSet resultado = stmt.executeQuery();

                if (resultado.next()) {
                    Produto p = new Produto(resultado.getInt("codigo"), resultado.getString("nome"),
                            resultado.getDouble("preco"), resultado.getInt("quantidade"));
                    System.out.println("O nome atual é: " + p.getNome() + "\nPreço: " + p.getPreco() + "\nQuantidade: " + p.getQuantidade());
                    sc1.nextLine();

                    System.out.println("\nQual item deseja mudar (nome, preco, quantidade): ");
                    String item = sc1.nextLine();

                    String novoNome = null;
                    Integer novaQuantidade = null;
                    Double novoPreco = null;

                    switch (item) {
                        case "nome":
                            System.out.println("Informe o novo nome: ");
                            novoNome = sc1.nextLine();
                            break;

                        case "preco":
                            System.out.println("Informe o novo preço: ");
                            novoPreco = sc1.nextDouble();
                            break;

                        case "quantidade":
                            System.out.println("Informe a nova quantidade: ");
                            novaQuantidade = sc1.nextInt();
                            break;

                        case "todos":
                            System.out.println("Informe o novo nome: ");
                            novoNome = sc1.nextLine();

                            System.out.println("Informe o novo preço: ");
                            novoPreco = sc1.nextDouble();

                            System.out.println("Informe a nova quantidade: ");
                            novaQuantidade = sc1.nextInt();
                        default:
                            System.out.println("Item inválido.");
                            return; // Saída antecipada se o item não for válido
                    }

                    try (PreparedStatement updateStmt = conexao.prepareStatement(update)) {
                        updateStmt.setString(1, novoNome != null ? novoNome : resultado.getString("nome"));
                        updateStmt.setDouble(2, novoPreco != null ? novoPreco : resultado.getDouble("preco"));
                        updateStmt.setInt(3, novaQuantidade != null ? novaQuantidade : resultado.getInt("quantidade"));
                        updateStmt.setInt(4, codigo);

                        int linhasAfetadas = updateStmt.executeUpdate();
                        if (linhasAfetadas > 0) {
                            System.out.println("Produto atualizado com sucesso!");
                        } else {
                            System.out.println("Nenhuma alteração foi feita.");
                        }
                    }

                } else {
                    System.out.println("Produto não encontrado!");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao acessar o banco: " + e.getMessage());
        }
    }
}
