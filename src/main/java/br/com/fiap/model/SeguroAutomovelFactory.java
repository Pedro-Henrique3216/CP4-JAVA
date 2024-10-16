package br.com.fiap.model;

public class SeguroAutomovelFactory implements SeguroFactory{
    @Override
    public Seguro createSeguro(Apolice apolice, Produto produto) {
        return new SeguroAutomovel(apolice, produto);
    }
}
