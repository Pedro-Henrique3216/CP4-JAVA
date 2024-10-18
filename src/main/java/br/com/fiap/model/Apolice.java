package br.com.fiap.model;

import java.util.Arrays;
import java.util.List;

public class Apolice {
    private Long id;
    private Cliente cliente;
    private String coberturas;

    public Apolice(Cliente cliente, String coberturas) {
        this.cliente = cliente;
        this.coberturas = coberturas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoberturas() {
        return coberturas;
    }

    public Long getId() {
        return id;
    }

    public List<String> todasCoberturas() {
        String[] coberturasArray = coberturas.split(",");
        for (int i = 0; i < coberturasArray.length; i++) {
            coberturasArray[i] = coberturasArray[i].trim().toUpperCase();
        }
        return Arrays.asList(coberturasArray);
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public String toString() {
        return "Apolice{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", coberturas='" + coberturas + '\'' +
                '}';
    }
}
