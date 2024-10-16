package br.com.fiap;

import br.com.fiap.model.Pagamento;
import br.com.fiap.model.Seguro;
import br.com.fiap.model.SeguroImovelFactory;
import br.com.fiap.model.TipoPagamento;

import java.time.LocalDate;

public class
Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SeguroImovelFactory seguroImovelFactory = new SeguroImovelFactory();
        Seguro seguro = seguroImovelFactory.createSeguro(null, null);
        Pagamento pagamento = new Pagamento(seguro, TipoPagamento.DEBITO, LocalDate.now().minusDays(10));
        pagamento.pagar();
        System.out.println(pagamento.getStatusPagamento());

    }
}