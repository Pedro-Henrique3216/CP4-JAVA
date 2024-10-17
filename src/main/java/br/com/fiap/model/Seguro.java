package br.com.fiap.model;

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

    public Long getId() {
        return id;
    }

    public TipoSeguro getTipo() {
        return tipo;
    }

    public Double getValorCobertura() {
        return valorCobertura;
    }

    public Double getPremio() {
        return premio;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Apolice getApolice() {
        return apolice;
    }
}
