package br.com.fiap.service;

import br.com.fiap.db.dao.ClienteDao;
import br.com.fiap.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteService {

    private ClienteDao clienteDao;

    public ClienteService(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    public void salvarCliente(Cliente cliente) {
        clienteDao.inserir(cliente);
    }

    public void alterarCliente(Cliente cliente) {
        clienteDao.alterar(cliente);
    }

    public void excluirCliente(Cliente cliente) {
        clienteDao.excluir(cliente.getId());
    }

    public List<Cliente> listar() {
        return clienteDao.listar();
    }

}
