package br.com.fiap.model;

public interface ProdutoFactory {

    Produto createProduto(Double valorProduto, Double valorComplementarProduto);
}
