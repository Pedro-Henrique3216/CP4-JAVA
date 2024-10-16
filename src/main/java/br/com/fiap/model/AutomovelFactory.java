package br.com.fiap.model;

public class AutomovelFactory implements ProdutoFactory{

    @Override
    public Produto createProduto(Double valorProduto, Double valorComplementarProduto) {
        return new Automovel(valorProduto, valorComplementarProduto);
    }
}
