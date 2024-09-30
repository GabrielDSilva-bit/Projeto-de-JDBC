package org.example;

public class Compra {
    private int codigoProduto;
    private String nomeProduto;
    private double preco;
    private int quantidade;

    public Compra(int codigoProduto, String nomeProduto, double preco, int quantidade) {
        this.codigoProduto = Integer.parseInt(String.valueOf(codigoProduto));
        this.nomeProduto = nomeProduto;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Código: " + codigoProduto + ", Nome: " + nomeProduto + ", Preço: " + preco + ", Quantidade: " + quantidade;
    }

    public double calcularTotal() {
        return preco * quantidade;
    }
}
