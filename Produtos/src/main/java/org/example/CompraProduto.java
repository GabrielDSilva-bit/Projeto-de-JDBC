package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CompraProduto {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);
             Connection conexao = FabricadeConexao.getConexao()) {

            System.out.println("Informe o código do produto: ");
            int codigo = scanner.nextInt();

            // Buscar produto no banco
            String select = "SELECT nome, preco, quantidade FROM produtos WHERE codigo = ?";
            PreparedStatement stmt = conexao.prepareStatement(select);
            stmt.setInt(1, codigo);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                String nomeProduto = resultado.getString("nome");
                double preco = resultado.getDouble("preco");
                int quantidadeAtual = resultado.getInt("quantidade");

                // Imprimindo informações do produto
                System.out.println("Produto encontrado: " + nomeProduto + ", Preço: " + preco);
                System.out.println("Quantidade atual disponível: " + quantidadeAtual);
                System.out.println("Informe a quantidade a comprar: ");
                int quantidadeCompra = scanner.nextInt();

                // Verificar se a quantidade solicitada é válida
                if (quantidadeCompra > quantidadeAtual) {
                    System.out.println("Quantidade solicitada maior do que a disponível. Tente novamente.");
                } else {
                    // Comando SQL para atualizar a quantidade do produto
                    String updateSql = "UPDATE produtos SET quantidade = quantidade - ? WHERE codigo = ?";
                    try (PreparedStatement updateStmt = conexao.prepareStatement(updateSql)) {
                        updateStmt.setInt(1, quantidadeCompra);
                        updateStmt.setInt(2, codigo);

                        int updated = updateStmt.executeUpdate();

                        if (updated > 0) {
                            // Criar a compra
                            Compra compra = new Compra(codigo, nomeProduto, preco, quantidadeCompra);
                            registrarCompra(compra);
                            System.out.println("Compra registrada com sucesso!");
                        } else {
                            System.out.println("Nenhum produto encontrado com o código: " + codigo);
                        }
                    }
                }
            } else {
                System.out.println("Produto não encontrado!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao acessar o banco: " + e.getMessage());
        }
    }

    private static void registrarCompra(Compra compra) {
        String arquivoRelatorio = "relatorio_compras.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoRelatorio, true))) {
            writer.write(compra.toString() + ", Total: " + compra.calcularTotal());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao gravar no arquivo: " + e.getMessage());
        }
    }
}
