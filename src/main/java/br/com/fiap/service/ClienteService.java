package br.com.fiap.service;

import br.com.fiap.model.Cliente;
import br.com.fiap.model.Seguro;

import java.util.List;

public interface ClienteService {

    void insertCliete(Cliente cliente);

    void alterarCliente(Cliente cliente);

    void excluirCliente(Long id);

    List<Cliente> listar();

}
