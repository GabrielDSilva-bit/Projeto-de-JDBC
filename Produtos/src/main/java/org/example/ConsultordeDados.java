package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConsultordeDados {
    public static <produtos> void main(String[] args) throws SQLException {
        Connection conexao = FabricadeConexao.getConexao();
        String sql = "SELECT * FROM produtos";
        Statement stmt = conexao.createStatement();
        ResultSet resultado = stmt.executeQuery(sql);

        List<Produto> produtos = new ArrayList<>();

        while (resultado.next()){
            int codigo = resultado.getInt("codigo");
            String nome = resultado.getString("nome");
            double preco = resultado.getDouble("preco");
            int quantidade = resultado.getInt("quantidade");
            produtos.add(new Produto(codigo,nome,preco,quantidade));
        }

        for (Produto p: produtos){
            System.out.println(p.getCodigo()+"-->" + p.getNome()+" "+p.getPreco()+" "+p.getQuantidade());
        }
        stmt.close();
    }
}
