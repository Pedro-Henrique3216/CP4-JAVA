package br.com.fiap.model;

public class Sinistro {

    private Long id;
    private String descricao;
    private Double valor;
    private Seguro seguro;

    public Sinistro(String descricao, Double valor, Seguro seguro) {
        this.seguro = seguro;
        descricao = descricao.toUpperCase();
        verificaSePossivelSeguroCobrir(descricao, valor);
        this.descricao = descricao;
        this.valor = valor;
    }

    public void verificaSePossivelSeguroCobrir(String descricao, Double valor) {
        if(!seguro.getApolice().todasCoberturas().contains(descricao)){
            throw new RuntimeException("Seguro Não cobre esse tipo de sinistro");
        }

        if(seguro.getValorCobertura() < valor){
            throw new RuntimeException("valor maior que a cobertura do seguro");
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

    public Seguro getSeguro() {
        return seguro;
    }

    @Override
    public String toString() {
        return "Sinistro{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", seguro=" + seguro.getId() +
                '}';
    }
}
