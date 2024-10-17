package br.com.fiap.service;

import br.com.fiap.dao.ClienteDao;

public interface IClienteServiceFactory {

    ClienteService createClienteService(ClienteDao clienteDao);
}
