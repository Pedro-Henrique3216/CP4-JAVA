package br.com.fiap.db.dao;

import br.com.fiap.model.Cliente;

import java.util.List;

public interface ClienteDao {

    void inserir(Cliente cliente);
    void alterar(Cliente cliente);
    void excluir(Long id);
    List<Cliente> listar();
}
