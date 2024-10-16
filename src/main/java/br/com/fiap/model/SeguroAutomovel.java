package br.com.fiap.model;

import java.time.LocalDate;

final class SeguroAutomovel implements Seguro {

    private Long id;
    private Apolice apolice;
    private LocalDate dataInicio;
    private Produto produto;
    private StatusSeguro statusSeguro;

    private Double valorCoberturaMaxima = apolice.getValor() * 5;

    public SeguroAutomovel(Apolice apolice, Produto produto) {
        this.apolice = apolice;
        this.produto = produto;
        this.dataInicio = LocalDate.now();
        statusSeguro = StatusSeguro.ATIVO;
    }

    @Override
    public String tipoSeguro() {
        return "Seguro Automovel";
    }

    @Override
    public Double valorCobertura() {
       double valor = produto.getValorTotal();
       return Math.min(valor, valorCoberturaMaxima);
    }

    @Override
    public Apolice getApolice() {
        return apolice;
    }


    @Override
    public StatusSeguro getStatus() {
        return statusSeguro;
    }

    @Override
    public void setStatus(StatusSeguro status) {
        statusSeguro = status;
    }

    @Override
    public void setDataInivio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }


}
