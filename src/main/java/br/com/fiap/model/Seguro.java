package br.com.fiap.model;

public class Seguro {

    private Long id;
    private TipoSeguro tipo;
    private Apolice apolice;
    private Double valorCobertura;
    private Double premio;
    private StatusSeguro status;

    public Seguro(TipoSeguro tipo, Apolice apolice, Double valorCobertura, Double premio, StatusSeguro status) {
        this.tipo = tipo;
        this.apolice = apolice;
        this.valorCobertura = valorCobertura;
        this.status = status;
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

    public StatusSeguro getStatus() {
        return status;
    }

    public void setStatus(StatusSeguro status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Apolice getApolice() {
        return apolice;
    }


    @Override
    public String toString() {
        return "Seguro{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", apolice=" + apolice +
                ", valorCobertura=" + valorCobertura +
                ", premio=" + premio +
                ", status=" + status +
                '}';
    }
}
