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

    public Pagamento(Seguro seguro, TipoPagamento tipoPagamento, LocalDate dataCriacao) {
        this.seguro = seguro;
        this.dataCriacao = dataCriacao;
        this.tipoPagamento = tipoPagamento;
        this.statusPagamento = StatusPagamento.PENDENDE;
    }

    public void pagar(){
        if(statusPagamento == StatusPagamento.PENDENDE){
            Period period = Period.between(dataCriacao, LocalDate.now());
            if(period.getDays() > 5){
                statusPagamento = StatusPagamento.CANCELADO;
            } else {
                statusPagamento = StatusPagamento.APROVADO;
                dataPagamento = LocalDate.now();
            }
        }
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
}
