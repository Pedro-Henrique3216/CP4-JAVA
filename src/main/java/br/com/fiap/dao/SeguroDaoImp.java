package br.com.fiap.dao;

import br.com.fiap.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeguroDaoImp implements SeguroDao{

    private Connection conn;

    private static SeguroDaoImp seguroDaoImp;

    private SeguroDaoImp(Connection conn){
        this.conn = conn;
    }

    public static synchronized SeguroDaoImp getInstance(Connection conn){
        if(seguroDaoImp == null){
            seguroDaoImp = new SeguroDaoImp(conn);
        }
        return seguroDaoImp;
    }


    @Override
    public void insert(Seguro seguro) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("""
                insert into seguros(tipo_seguro, apolice_id, vl_cobertura, premio) values(?, ?, ?, ?)
            """, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, String.valueOf(seguro.getTipo()));
            ps.setLong(2, seguro.getApolice().getId());
            ps.setDouble(3, seguro.getValorCobertura());
            ps.setDouble(4, seguro.getPremio());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()){
                    Long id = rs.getLong(1);
                    seguro.setId(id);
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
    public void update(Seguro seguro) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("""
                update seguros
                    set vl_cobertura = ?, premio = ?
                where id = ?
            """);
            ps.setDouble(1, seguro.getValorCobertura());
            ps.setDouble(2, seguro.getPremio());
            ps.setLong(3, seguro.getId());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("""
                delete seguros where id = ?
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
    public Seguro findById(Long id) {
        return null;
    }

    @Override
    public List<Seguro> findAll() {
        List<Seguro> seguros = new ArrayList<>();
        Statement s = null;
        try {
            s = conn.createStatement();
            ResultSet rs = s.executeQuery("""
                select s.*, a.*, c.* from seguros s inner join apolices a on (s.apolice_id = a.id) inner join clientes c on(a.cliente_id = c.id)
            """);
            while (rs.next()){
                seguros.add(instanciaSeguro(rs));
            }
            s.close();
            rs.close();
            return seguros;
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private Seguro instanciaSeguro(ResultSet rs) throws SQLException {
        Seguro seguro = new Seguro(TipoSeguro.valueOf(rs.getString("tipo_seguro")), instaciaApolice(rs),
                rs.getDouble("vl_cobertura"), rs.getDouble("premio"));
        seguro.setId(rs.getLong("id"));
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
