package br.com.fiap.model;

public class ImovelFactory implements ProdutoFactory{
    @Override
    public Produto createProduto(Double valorProduto, Double valorComplementarProduto) {
        return new Imovel(valorProduto, valorComplementarProduto);
    }
}
