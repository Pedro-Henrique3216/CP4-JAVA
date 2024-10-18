package br.com.fiap.model;

import java.time.LocalDate;
import java.time.Period;

public class Pagamento {

    private Long id;
    private Seguro seguro;
    private LocalDate dataCriacao;
    private LocalDate dataPagamento;
    private TipoPagamento tipoPagamento;
    private StatusPagamento statusPagamento;
    private Double valorTotal;

    public Pagamento(Seguro seguro, TipoPagamento tipoPagamento, LocalDate dataCriacao, Double valorTotal) {
        this.seguro = seguro;
        this.dataCriacao = dataCriacao;
        this.tipoPagamento = tipoPagamento;
        this.statusPagamento = StatusPagamento.PENDENDE;
        this.valorTotal = valorTotal;

    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void pagar(){
        if(statusPagamento == StatusPagamento.PENDENDE){
            Period period = Period.between(dataCriacao, LocalDate.now());
            if(period.getDays() > 5){
                statusPagamento = StatusPagamento.CANCELADO;
                System.out.println("Cancelado");
            } else {
                statusPagamento = StatusPagamento.APROVADO;
                dataPagamento = LocalDate.now();
            }
        }
    }


    public Long getId() {
        return id;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "id=" + id +
                ", seguro=" + seguro.getId() +
                ", dataCriacao=" + dataCriacao +
                ", dataPagamento=" + dataPagamento +
                ", tipoPagamento=" + tipoPagamento +
                ", statusPagamento=" + statusPagamento +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
