package br.com.fiap.dao;

import br.com.fiap.model.Pagamento;

import java.util.List;
import java.util.Optional;

public interface PagamentoDao {

    void inserir(Pagamento pagamento);
    void alterar(Pagamento pagamento);
    void excluir(Long id);
    List<Pagamento> listar(Long seguroId);
    Optional<Pagamento> buscarPorId(Long id);
}
