package br.com.fiap.service;

import br.com.fiap.db.dao.SeguroDao;
import br.com.fiap.model.Seguro;

import java.util.List;

public class SeguroServiceImpl implements SeguroService {

    private SeguroService seguroService;


    @Override
    public void insert(Seguro seguro) {
        this.seguroService.insert(seguro);
    }

    @Override
    public void update(Seguro seguro) {
        this.seguroService.update(seguro);
    }

    @Override
    public void delete(Seguro seguro) {
        this.seguroService.delete(seguro);
    }

    @Override
    public Seguro findById(int id) {
        return this.seguroService.findById(id);
    }

    @Override
    public List<Seguro> findAll() {
        return this.seguroService.findAll();
    }
}
