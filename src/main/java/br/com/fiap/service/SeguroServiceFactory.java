package br.com.fiap.service;

import br.com.fiap.dao.SeguroDao;

public class SeguroServiceFactory implements ISeguroServiceFavtory{
    @Override
    public SeguroService createSeguroService(SeguroDao seguroDao) {
        return new SeguroServiceImpl(seguroDao);
    }
}
