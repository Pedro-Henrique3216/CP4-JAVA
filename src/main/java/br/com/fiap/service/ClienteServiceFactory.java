package br.com.fiap.service;

import br.com.fiap.dao.ClienteDao;

public class ClienteServiceFactory implements IClienteServiceFactory{

    @Override
    public ClienteService createClienteService(ClienteDao clienteDao) {
        return new ClienteServiceImpl(clienteDao);
    }
}
