package br.com.fiap.model;

final class Automovel implements Produto{

    private Double valorVeiculo;
    private Double valorAcessorios;

    public Automovel(Double valorVeiculo, Double valorAcessorios) {
        this.valorVeiculo = valorVeiculo;
        this.valorAcessorios = valorAcessorios;
    }

    @Override
    public Double getValorTotal() {
        return valorVeiculo + valorAcessorios;
    }
}
