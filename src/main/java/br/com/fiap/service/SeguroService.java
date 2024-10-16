package br.com.fiap.service;

import br.com.fiap.model.Seguro;

import java.util.List;

public interface SeguroService {

    void insert(Seguro seguro);
    void update(Seguro seguro);
    void delete(Seguro seguro);
    Seguro findById(int id);
    List<Seguro> findAll();

}
