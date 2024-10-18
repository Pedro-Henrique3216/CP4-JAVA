package br.com.fiap.service;

import br.com.fiap.dao.SeguroDao;

public interface ISeguroServiceFavtory {

    SeguroService createSeguroService(SeguroDao seguroDao, PagamentoService pagamentoService);
}
