package br.com.fiap.service;

import br.com.fiap.db.dao.SeguroDao;
import br.com.fiap.model.Seguro;

import java.util.List;

public class SeguroService {

    SeguroDao seguroDao;

    public SeguroService(SeguroDao seguroDao) {
        this.seguroDao = seguroDao;
    }

    public void insertSeguro(Seguro seguro) {
        seguroDao.insert(seguro);
    }

    public void updateSeguro(Seguro seguro) {
        seguroDao.update(seguro);
    }

    public void deleteSeguro(Long id) {
        seguroDao.delete(id);
    }

    public Seguro findSeguro(Long id) {
        return seguroDao.findById(id);
    }

    public List<Seguro> findAllSeguro() {
        return seguroDao.findAll();
    }
}
