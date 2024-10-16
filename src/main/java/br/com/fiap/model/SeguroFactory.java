package br.com.fiap.model;

public interface SeguroFactory {

    Seguro createSeguro(Apolice apolice, Produto produto);
}
