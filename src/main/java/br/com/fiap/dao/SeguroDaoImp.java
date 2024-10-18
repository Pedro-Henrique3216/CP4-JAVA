package br.com.fiap.dao;

import br.com.fiap.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.fiap.model.InstanciaObjetosDeOutrasTabelas.instaciaApolice;

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
                insert into seguros(tipo_seguro, apolice_id, vl_cobertura, premio, status_seguro) values(?, ?, ?, ?, ?)
            """, new String[] {"id"});
            ps.setString(1, String.valueOf(seguro.getTipo()));
            ps.setLong(2, seguro.getApolice().getId());
            ps.setDouble(3, seguro.getValorCobertura());
            ps.setDouble(4, seguro.getPremio());
            ps.setString(5, String.valueOf(seguro.getStatus()));
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
                    set vl_cobertura = ?, premio = ?, status_seguro = ?
                where id = ?
            """);
            ps.setDouble(1, seguro.getValorCobertura());
            ps.setDouble(2, seguro.getPremio());
            ps.setString(3, String.valueOf(seguro.getStatus()));
            ps.setLong(4, seguro.getId());

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
    public List<Seguro> findAllByClienteId(Long id) {
        List<Seguro> seguros = new ArrayList<>();
        PreparedStatement s = null;
        try {
            s = conn.prepareStatement("""
                select s.*, a.*, c.* from seguros s inner join apolices a on (s.apolice_id = a.id) inner join clientes c on(a.cliente_id = c.id)
                where s.cliente_id = ?
            """);
            s.setLong(1, id);
            ResultSet rs = s.executeQuery();
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

    @Override
    public Optional<Seguro> findById(Long id) {
        Optional<Seguro> seguro = Optional.empty();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("""
                select s.*, a.*, c.* from seguros s inner join apolices a on (s.apolice_id = a.id) inner join clientes c on(a.cliente_id = c.id)
                where s.id = ?
            """);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
               seguro = Optional.of(instanciaSeguro(rs));
            }
            rs.close();
            ps.close();
            return seguro;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Seguro instanciaSeguro(ResultSet rs) throws SQLException {
        Seguro seguro = new Seguro(TipoSeguro.valueOf(rs.getString("tipo_seguro")), instaciaApolice(rs),
                rs.getDouble("vl_cobertura"), rs.getDouble("premio"), StatusSeguro.valueOf(rs.getString("status_seguro")));
        seguro.setId(rs.getLong("id"));
        return seguro;
    }


}
