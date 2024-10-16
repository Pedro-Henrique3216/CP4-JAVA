package br.com.fiap.model;

public class Sinistro {

    private Long id;
    private Cliente cliente;
    private String descricao;
    private Double valor;

    public Sinistro(Cliente cliente, String descricao, Double valor) {
        this.cliente = cliente;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
