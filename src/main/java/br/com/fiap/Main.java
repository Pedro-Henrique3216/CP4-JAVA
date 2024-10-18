package br.com.fiap;

import br.com.fiap.config.DbConfig;
import br.com.fiap.dao.*;
import br.com.fiap.model.*;
import br.com.fiap.service.*;

import java.sql.Connection;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Inicializa a configuração do banco de dados com os detalhes de conexão
        DbConfig dbConfig = new DbConfig("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl", "rm559064", "221005");
        Connection connection = dbConfig.getConnection();

        // Instancia os DAOs (Data Access Objects) com a conexão do banco de dados
        ClienteDao clienteDao = ClienteDaoImp.getInstance(connection);
        ApoliceDao apoliceDao = ApoliceDaoImp.getInstance(connection);
        SeguroDao seguroDao = SeguroDaoImp.getInstance(connection);
        SinistroDao sinistroDao = SinistroDaoImp.getInstance(connection);
        PagamentoDao pagamentoDao = PagamentoDaoImp.getInstance(connection);

        // Instancia as fábricas de serviços para clientes e seguros
        IClienteServiceFactory iClienteServiceFactory = new ClienteServiceFactory();
        ISeguroServiceFavtory iSeguroServiceFavtory = new SeguroServiceFactory();

        // Cria os serviços utilizando os DAOs e as fábricas de serviços
        ClienteService clienteService = iClienteServiceFactory.createClienteService(clienteDao);
        PagamentoService pagamentoService = new PagamentoService(pagamentoDao);
        SeguroService seguroService = iSeguroServiceFavtory.createSeguroService(seguroDao, pagamentoService);
        ApoliceService apoliceService = new ApoliceService(apoliceDao);
        SinistroService sinistroService = new SinistroService(sinistroDao);

        // Cria um novo cliente e insere no banco de dados, em seguida lista todos os clientes
        Cliente cliente = new Cliente("Pedro", "ph@fiap.com", "12345678909", "11912345678", LocalDate.parse("2005-10-22"));
        clienteService.insertCliete(cliente);
        clienteService.listar().forEach(System.out::println);
        System.out.println();

        // Obtém o último cliente adicionado e cria uma apólice para ele, em seguida lista todas as apólices do cliente
        Cliente newCliente = clienteService.listar().getLast();
        Apolice apolice = new Apolice(cliente, "Roubo,Furto");
        apoliceService.createApolice(apolice);
        apoliceService.getAllSinistroByCliente(1L).forEach(System.out::println);
        System.out.println();

        // Cria um seguro associado à apólice e um pagamento inicial, depois lista todos os pagamentos desse seguro
        Seguro seguro = new Seguro(TipoSeguro.SEGURO_AUTOMOVEL, apolice, 200.00, 100.00, StatusSeguro.ATIVO);
        seguroService.insert(seguro, TipoPagamento.PIX);
        pagamentoService.getAllPagamentoBySeguro(1L).forEach(System.out::println);
        pagamentoService.pagarSeguro(1L);
        System.out.println();

        // Lista todos os pagamentos que foram realizados para o seguro específico
        pagamentoService.getAllPagamentoPago(1L).forEach(System.out::println);
        System.out.println();

        // Busca um seguro pelo ID e imprime suas informações
        Seguro buscaSeguro = seguroService.findById(1L);
        System.out.println(buscaSeguro);
        System.out.println();

        // Cria um sinistro associado ao seguro, verificando se o evento está coberto pela apólice
        Sinistro sinistro = new Sinistro("Roubo", 150.0, buscaSeguro);
        sinistroService.createSinistro(sinistro);

        // Cria um pagamento retroativo em 6 dias e tenta realizar o pagamento, depois lista todos os pagamentos do seguro
        Pagamento pagamento = new Pagamento(seguro, TipoPagamento.PIX, LocalDate.now().minusDays(6), seguro.getPremio());
        pagamentoService.createPagamento(pagamento);
        pagamentoService.pagarSeguro(2L);
        pagamentoService.getAllPagamentoBySeguro(1L).forEach(System.out::println);
        System.out.println();

        // Verifica novamente o seguro para ver se a lógica de inatividade foi aplicada corretamente
        System.out.println(seguroService.findById(1L));
        System.out.println();

        pagamentoService.getAllPagamentoBySeguro(1L).forEach(System.out::println);
        System.out.println();



        // Testa a criação de um sinistro que não está coberto pela apólice, para verificar se o sistema gera um erro
        try {
            Sinistro sinistroErro = new Sinistro("Arranhão", 250.0, buscaSeguro);
            sinistroService.createSinistro(sinistroErro);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        // Lista todos os sinistros associados ao seguro para verificar os resultados das operações anteriores
        sinistroService.getAllSinistroBySeguro(buscaSeguro.getId()).forEach(System.out::println);








    }
}