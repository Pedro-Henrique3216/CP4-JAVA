package br.com.fiap.service;

import br.com.fiap.model.Seguro;
import br.com.fiap.model.TipoPagamento;

import java.util.List;
import java.util.Optional;

public interface SeguroService {

    void insert(Seguro seguro, TipoPagamento tipoPagamento);
    void update(Seguro seguro);
    void delete(Long id);
    Seguro findById(Long id);
    List<Seguro> findAllByClienteId(Long id);

}
