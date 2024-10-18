package br.com.fiap.dao;

import br.com.fiap.model.Seguro;

import java.util.List;
import java.util.Optional;

public interface SeguroDao {

    void insert(Seguro seguro);
    void update(Seguro seguro);
    void delete(Long id);
    Optional<Seguro> findById(Long id);
    List<Seguro> findAllByClienteId(Long id);
}
