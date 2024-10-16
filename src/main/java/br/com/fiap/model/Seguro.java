package br.com.fiap.model;

import java.time.LocalDate;

public interface Seguro {

    String tipoSeguro();

    Double valorCobertura();

    Apolice getApolice();

    StatusSeguro getStatus();

    void setStatus(StatusSeguro status);

    void setDataInivio(LocalDate dataInicio);
}
