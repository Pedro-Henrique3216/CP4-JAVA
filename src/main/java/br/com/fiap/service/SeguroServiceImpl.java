package br.com.fiap.service;

import br.com.fiap.dao.PagamentoDao;
import br.com.fiap.dao.SeguroDao;
import br.com.fiap.model.*;

import java.time.LocalDate;
import java.util.List;

final class SeguroServiceImpl implements SeguroService {

    private SeguroDao seguroDao;

    private PagamentoService pagamentoService;

    public SeguroServiceImpl(SeguroDao seguroDao, PagamentoService pagamentoService) {
        this.seguroDao = seguroDao;
        this.pagamentoService = pagamentoService;
    }

    @Override
    public void insert(Seguro seguro, TipoPagamento tipoPagamento) {
        this.seguroDao.insert(seguro);
        Pagamento pagamento = new Pagamento(seguro, tipoPagamento, LocalDate.now(), seguro.getPremio());
        pagamentoService.createPagamento(pagamento);
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
        Seguro seguro = seguroDao.findById(id).orElseThrow(() -> new RuntimeException("Não existe um seguro com o id " + id));
        if (pagamentoService.getAllPagamentoBySeguro(id).getLast().getStatusPagamento() == StatusPagamento.CANCELADO){
            seguro.setStatus(StatusSeguro.INATIVO);
            update(seguro);
            Pagamento pagamento = new Pagamento(seguro, TipoPagamento.PIX, LocalDate.now(), seguro.getPremio());
            pagamentoService.createPagamento(pagamento);
        }
        if (pagamentoService.getAllPagamentoBySeguro(id).getLast().getStatusPagamento() == StatusPagamento.APROVADO && seguro.getStatus() == StatusSeguro.INATIVO){
            seguro.setStatus(StatusSeguro.ATIVO);
            update(seguro);
        }
        return seguro;
    }

    @Override
    public List<Seguro> findAllByClienteId(Long id) {
        return this.seguroDao.findAllByClienteId(id);
    }
}
