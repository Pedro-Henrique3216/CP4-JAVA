package br.com.fiap.model;

public class SeguroImovelFactory implements SeguroFactory {
    @Override
    public Seguro createSeguro(Apolice apolice, Produto produto) {
        return new SeguroImovel(apolice, produto);
    }
}
