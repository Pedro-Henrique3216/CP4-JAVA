package br.com.fiap.dao;

import br.com.fiap.model.*;
import oracle.jdbc.proxy.annotation.Pre;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PagamentoDaoImp implements PagamentoDao{

    private Connection conn;

    private static PagamentoDaoImp pagamentoDaoImp;

    private PagamentoDaoImp(Connection conn){
        this.conn = conn;
    }


    public static synchronized PagamentoDaoImp getInstance(Connection conn){
        if(pagamentoDaoImp == null){
            pagamentoDaoImp = new PagamentoDaoImp(conn);
        }
        return pagamentoDaoImp;
    }

    @Override
    public void inserir(Pagamento pagamento) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("""
                insert into pagamentos(seguro_id, tipo_pagamento, data_criacao, status, vl_pagamento) values(?, ?, ?, ?, ?)
            """, new String[] {"id"});
            ps.setLong(1, pagamento.getSeguro().getId());
            ps.setString(2, String.valueOf(pagamento.getTipoPagamento()));
            ps.setDate(3, Date.valueOf(pagamento.getDataCriacao()));
            ps.setString(4, String.valueOf(pagamento.getStatusPagamento()));
            ps.setDouble(5, pagamento.getValorTotal());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()){
                    Long id = rs.getLong(1);
                    pagamento.setId(id);
                }
                rs.close();
            } else {
                throw new RuntimeException("Erro ao inserir automovel");
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void alterar(Pagamento pagamento) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("""
                update pagamentos
                    set dt_pagamento = ?, tipo_pagamento = ?, status = ?
                where id = ?
            """);
            ps.setDate(1, Date.valueOf(pagamento.getDataPagamento()));
            ps.setString(2, String.valueOf(pagamento.getTipoPagamento()));
            ps.setString(3, String.valueOf(pagamento.getStatusPagamento()));
            ps.setLong(4, pagamento.getId());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void excluir(Long id) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("""
                delete pagamentos where id = ?
            """);
            ps.setLong(1, id);

            ps.executeUpdate();

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Pagamento> listar(Long idSeguro) {
        List<Pagamento> pagamentos = new ArrayList<>();
        PreparedStatement s = null;
        try {
            s = conn.prepareStatement("""
                select p.*, s.*, a.*, c.* from pagamentos p 
                inner join seguros s on (p.seguro_id = s.id)
                inner join apolices a on (s.apolice_id = a.id) 
                inner join clientes c on(a.cliente_id = c.id)
                where p.seguro_id = ?
            """);
            s.setLong(1, idSeguro);
            ResultSet rs = s.executeQuery();
            while (rs.next()){
                pagamentos.add(instanciaPagamento(rs));
            }
            s.close();
            rs.close();
            return pagamentos;
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Pagamento> buscarPorId(Long id) {
        PreparedStatement s = null;
        Optional<Pagamento> pagamento = Optional.empty();
        try {
            s = conn.prepareStatement("""
                select p.*, s.*, a.*, c.* from pagamentos p 
                inner join seguros s on (p.seguro_id = s.id)
                inner join apolices a on (s.apolice_id = a.id) 
                inner join clientes c on(a.cliente_id = c.id)
                where p.id = ?
            """);
            s.setLong(1, id);
            ResultSet rs = s.executeQuery();
            while (rs.next()){
                pagamento = Optional.of(instanciaPagamento(rs));
            }
            s.close();
            rs.close();
            return pagamento;
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }

    }

    private Pagamento instanciaPagamento(ResultSet rs) throws SQLException {
        Pagamento pagamento = new Pagamento(instanciaSeguro(rs), TipoPagamento.valueOf(rs.getString("tipo_pagamento")),
                rs.getDate("data_criacao").toLocalDate(), rs.getDouble("vl_pagamento"));
        pagamento.setId(rs.getLong("id"));
        pagamento.setStatusPagamento(StatusPagamento.valueOf(rs.getString("status")));
        if(rs.getDate("dt_pagamento") != null){
            pagamento.setDataPagamento(rs.getDate("dt_pagamento").toLocalDate());
        }

        return pagamento;
    }

    private Seguro instanciaSeguro(ResultSet rs) throws SQLException {
        Seguro seguro = new Seguro(TipoSeguro.valueOf(rs.getString("tipo_seguro")), instaciaApolice(rs),
                rs.getDouble("vl_cobertura"), rs.getDouble("premio"), StatusSeguro.valueOf(rs.getString("status_seguro")));
        seguro.setId(rs.getLong("seguro_id"));
        return seguro;
    }

    private Apolice instaciaApolice(ResultSet rs) throws SQLException {
        Apolice apolice = new Apolice(instaciaCliente(rs), rs.getString("coberturas"));
        apolice.setId(rs.getLong("apolice_id"));
        return apolice;
    }

    private Cliente instaciaCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente(rs.getString("nome"), rs.getString("email"),
                rs.getString("cpf"), rs.getString("telefone"),
                rs.getDate("dt_nascimento").toLocalDate());
        cliente.setId(rs.getLong("cliente_id"));
        return cliente;
    }
}
