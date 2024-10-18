package br.com.fiap.dao;

import br.com.fiap.model.Apolice;
import br.com.fiap.model.Cliente;

import java.util.List;

public interface ApoliceDao {

    void inserir(Apolice apolice);
    void alterar(Apolice apolice);
    void excluir(Long id);
    List<Apolice> listar(Long clienteId);
}
