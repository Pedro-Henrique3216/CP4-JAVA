package br.com.fiap.service;

import br.com.fiap.dao.ApoliceDao;
import br.com.fiap.model.Apolice;

import java.util.List;

public class ApoliceService {

    private ApoliceDao apoliceDao;

    public ApoliceService(ApoliceDao apoliceDao) {
        this.apoliceDao = apoliceDao;
    }

    public void createApolice(Apolice apolice) {
        apoliceDao.inserir(apolice);
    }

    public List<Apolice> getAllSinistroByCliente(Long clienteId) {
        return apoliceDao.listar(clienteId);
    }

    public void alterarCliente(Apolice apolice) {
        apoliceDao.alterar(apolice);
    }

    public void excluirCliente(Long id) {
        apoliceDao.excluir(id);
    }
}
