package br.com.fiap.model;

public class Sinistro {

    private Long id;
    private String descricao;
    private Double valor;
    private Seguro seguro;

    public Sinistro(String descricao, Double valor, Seguro seguro) {
        verificaSePossivelSeguroCobrir(descricao);
        this.descricao = descricao;
        this.valor = valor;
        this.seguro = seguro;
    }

    public void verificaSePossivelSeguroCobrir(String descricao) {
        if(!seguro.getApolice().getCoberturas().contains(descricao)){
            throw new RuntimeException("Seguro Não cobre esse sinistro");
        }
    }

    public Long getId() {
        return id;
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
