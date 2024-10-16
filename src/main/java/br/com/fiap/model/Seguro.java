package br.com.fiap.model;

import java.time.LocalDate;

public class Seguro {

    private Long id;
    private TipoSeguro tipo;
    private Apolice apolice;
    private Double valorCobertura;
    private Double premio;

    public Seguro(TipoSeguro tipo, Apolice apolice, Double valorCobertura, Double premio) {
        this.tipo = tipo;
        this.apolice = apolice;
        this.valorCobertura = valorCobertura;
        this.premio = premio;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Apolice getApolice() {
        return apolice;
    }
}
