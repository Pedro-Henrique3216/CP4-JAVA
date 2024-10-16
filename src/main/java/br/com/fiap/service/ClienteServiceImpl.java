package br.com.fiap.service;

import br.com.fiap.db.dao.ClienteDao;
import br.com.fiap.model.Cliente;
import br.com.fiap.model.Seguro;

import java.util.List;

public class ClienteServiceImpl implements ClienteService {

    private ClienteService clienteService;


    @Override
    public void insertSeguro(Seguro seguro) {
        this.insertSeguro(seguro);
    }

    @Override
    public void alterarCliente(Cliente cliente) {
        this.alterarCliente(cliente);
    }

    @Override
    public void salvarCliente(Cliente cliente) {
        this.salvarCliente(cliente);
    }

    @Override
    public void excluirCliente(Cliente cliente) {
        this.excluirCliente(cliente);
    }

    @Override
    public List<Cliente> listar() {

        return this.clienteService.listar();
    }
}
