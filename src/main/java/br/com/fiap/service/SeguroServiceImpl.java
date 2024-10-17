package br.com.fiap.service;

import br.com.fiap.dao.SeguroDao;
import br.com.fiap.model.Seguro;

import java.util.List;

final class SeguroServiceImpl implements SeguroService {

    private SeguroDao seguroDao;

    public SeguroServiceImpl(SeguroDao seguroDao) {
        this.seguroDao = seguroDao;
    }

    @Override
    public void insert(Seguro seguro) {
        this.seguroDao.insert(seguro);
    }

    @Override
    public void update(Seguro seguro) {
        this.seguroDao.update(seguro);
    }

    @Override
    public void delete(Long id) {
        this.seguroDao.delete(id);
    }

    @Override
    public Seguro findById(Long id) {
        return this.seguroDao.findById(id);
    }

    @Override
    public List<Seguro> findAll() {
        return this.seguroDao.findAll();
    }
}
