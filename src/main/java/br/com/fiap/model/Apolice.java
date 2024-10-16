package br.com.fiap.model;

import java.util.Arrays;
import java.util.List;

public class Apolice {
    private Long id;
    private Cliente cliente;
    private String coberturas;

    public Apolice(Long id, Cliente cliente, String coberturas) {
        this.id = id;
        this.cliente = cliente;
        this.coberturas = coberturas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getCoberturas() {
        String[] coberturasArray = coberturas.split(",");
        for (int i = 0; i < coberturasArray.length; i++) {
            coberturasArray[i] = coberturasArray[i].trim();
        }
        return Arrays.asList(coberturasArray);
    }

    public Cliente getCliente() {
        return cliente;
    }

}
