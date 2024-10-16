package br.com.fiap.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

final class SeguroImovel implements Seguro {

    private Long id;
    private Apolice apolice;
    private Produto produto;
    private LocalDate dataInicio;
    private StatusSeguro statusSeguro;

    public SeguroImovel(Apolice apolice, Produto produto) {
        this.apolice = apolice;
        this.produto = produto;
        statusSeguro = StatusSeguro.ATIVO;
    }

    @Override
    public String tipoSeguro() {
        return "Seguro Imovel";
    }

    @Override
    public Double valorCobertura() {
        return produto.getValorTotal();
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
