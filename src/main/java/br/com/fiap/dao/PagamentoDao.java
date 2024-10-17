package br.com.fiap.dao;

import br.com.fiap.model.Pagamento;

import java.util.List;

public interface PagamentoDao {

    void inserir(Pagamento pagamento);
    void alterar(Pagamento pagamento);
    void excluir(Long id);
    List<Pagamento> listar();
}
