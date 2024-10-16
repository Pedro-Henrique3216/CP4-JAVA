package br.com.fiap.service;

import br.com.fiap.model.Cliente;
import br.com.fiap.model.Seguro;

import java.util.List;

public interface ClienteService {

    void insertSeguro(Seguro seguro);

    void alterarCliente(Cliente cliente);

    void salvarCliente(Cliente cliente);

    void excluirCliente(Cliente cliente);

    List<Cliente> listar();

}
