package br.com.fiap.dao;

import br.com.fiap.model.Apolice;
import br.com.fiap.model.Cliente;
import oracle.jdbc.proxy.annotation.Pre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApoliceDaoImp implements ApoliceDao{

    private Connection conn;

    private static ApoliceDaoImp apoliceDaoImp;

    private ApoliceDaoImp(Connection conn){
        this.conn = conn;
    }

    public static synchronized ApoliceDaoImp getInstance(Connection conn){
       if(apoliceDaoImp == null){
           apoliceDaoImp = new ApoliceDaoImp(conn);
       }
       return apoliceDaoImp;
    }


    @Override
    public void inserir(Apolice apolice) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("""
                insert into apolices(cliente_id, coberturas) values(?, ?)
            """, new String[] {"id"});
            ps.setLong(1, apolice.getCliente().getId());
            ps.setString(2, apolice.getCoberturas());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()){
                    Long id = rs.getLong(1);
                    apolice.setId(id);
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
    public void alterar(Apolice apolice) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("""
                update apolices
                    set coberturas = ?
                where id = ?
            """);
            ps.setString(1, apolice.getCoberturas());
            ps.setLong(2, apolice.getId());

            ps.executeUpdate();

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
                delete apolices where id = ?
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
    public List<Apolice> listar(Long clienteId) {
        List<Apolice> apolices = new ArrayList<>();
        PreparedStatement s = null;
        try {
            s = conn.prepareStatement("""
                select a.*, c.* from apolices a inner join clientes c on (a.cliente_id = c.id) where a.cliente_id = ?
            """);
            s.setLong(1, clienteId);
            ResultSet rs = s.executeQuery();
            while (rs.next()){
                apolices.add(instaciaApolice(rs));
            }
            s.close();
            rs.close();
            return apolices;
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private Apolice instaciaApolice(ResultSet rs) throws SQLException {
        Apolice apolice = new Apolice(instaciaCliente(rs), rs.getString("coberturas"));
        apolice.setId(rs.getLong("id"));
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
