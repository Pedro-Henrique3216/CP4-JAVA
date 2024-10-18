package br.com.fiap.service;

import br.com.fiap.dao.SinistroDao;
import br.com.fiap.model.Sinistro;

import java.util.List;

public class SinistroService {

    private SinistroDao sinistroDao;

    public SinistroService(SinistroDao sinistroDao) {
        this.sinistroDao = sinistroDao;
    }

    public void createSinistro(Sinistro sinistro) {
        sinistroDao.inserir(sinistro);
    }

    public List<Sinistro> getAllSinistroBySeguro(Long seguroId) {
        return sinistroDao.listar(seguroId);
    }

    public void alterarCliente(Sinistro sinistro) {
        sinistroDao.alterar(sinistro);
    }

    public void excluirCliente(Long id) {
        sinistroDao.excluir(id);
    }


}
