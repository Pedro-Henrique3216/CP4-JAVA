package br.com.fiap.service;

import br.com.fiap.dao.PagamentoDao;
import br.com.fiap.model.Pagamento;
import br.com.fiap.model.StatusPagamento;

import java.util.List;

public class PagamentoService {

    private PagamentoDao pagamentoDao;

    public PagamentoService(PagamentoDao pagamentoDao) {
        this.pagamentoDao = pagamentoDao;
    }

    public void createPagamento(Pagamento pagamento) {
        pagamentoDao.inserir(pagamento);
    }

    public List<Pagamento> getAllPagamentoBySeguro(Long seguroId) {
        return pagamentoDao.listar(seguroId);
    }

    public List<Pagamento> getAllPagamentoPago(Long seguroId) {
        return pagamentoDao.listar(seguroId).stream()
                .filter(pagamento -> pagamento.getStatusPagamento() == StatusPagamento.APROVADO)
                .toList();
    }

    public void pagarSeguro(Long id) {
        Pagamento pagamento = pagamentoDao.buscarPorId(id).orElseThrow(() -> new RuntimeException("Não existe um pagamento com o id " + id));
        pagamento.pagar();
        pagamentoDao.alterar(pagamento);
    }
}
