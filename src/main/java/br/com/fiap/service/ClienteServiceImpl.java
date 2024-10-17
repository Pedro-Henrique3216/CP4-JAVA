package br.com.fiap.service;

import br.com.fiap.dao.ClienteDao;
import br.com.fiap.model.Cliente;

import java.util.List;

final class ClienteServiceImpl implements ClienteService {

    private ClienteDao clienteDao;

    public ClienteServiceImpl(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    @Override
    public void insertCliete(Cliente cliente) {
        clienteDao.inserir(cliente);
    }


    @Override
    public void alterarCliente(Cliente cliente) {
        clienteDao.alterar(cliente);
    }

    @Override
    public void excluirCliente(Long id) {
        clienteDao.excluir(id);
    }

    @Override
    public List<Cliente> listar() {
        return this.clienteDao.listar();
    }
}
