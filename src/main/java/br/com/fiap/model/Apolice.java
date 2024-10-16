package br.com.fiap.model;

public class Apolice {
    private String numero;
    private Seguro seguro;
    private Double valor;

    public Apolice(String numero, Seguro seguro, double valor) {
        this.numero = numero;
        this.seguro = seguro;
        this.valor = valor;
    }

    public String getNumero() {
        return numero;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public double getValor() {
        return valor;
    }
}
