package br.com.fiap.service;

import br.com.fiap.model.Seguro;

import java.util.List;

public interface SeguroService {

    void insert(Seguro seguro);
    void update(Seguro seguro);
    void delete(Long id);
    Seguro findById(Long id);
    List<Seguro> findAll();

}
