package br.com.fiap.model;

final class Imovel implements Produto {

    private Double valorReconstrucao;
    private Double valorBensInternos;

    public Imovel(Double valorReconstrucao, Double valorBensInternos) {
        this.valorReconstrucao = valorReconstrucao;
        this.valorBensInternos = valorBensInternos;
    }

    @Override
    public Double getValorTotal() {
        return valorReconstrucao + valorBensInternos;
    }
}
