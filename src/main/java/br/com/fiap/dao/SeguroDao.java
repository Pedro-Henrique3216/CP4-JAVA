package br.com.fiap.dao;

import br.com.fiap.model.Seguro;

import java.util.List;

public interface SeguroDao {

    void insert(Seguro seguro);
    void update(Seguro seguro);
    void delete(Long id);
    Seguro findById(Long id);
    List<Seguro> findAll();
}
