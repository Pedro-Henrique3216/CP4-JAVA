package br.com.fiap.dao;

import br.com.fiap.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static br.com.fiap.model.InstanciaObjetosDeOutrasTabelas.instanciaSeguro;

public class SinistroDaoImp implements SinistroDao{

    private Connection conn;

    private static SinistroDaoImp sinistroDaoImp;

    private SinistroDaoImp(Connection conn){
        this.conn = conn;
    }

    public static synchronized SinistroDaoImp getInstance(Connection conn){
        if(sinistroDaoImp == null){
            sinistroDaoImp = new SinistroDaoImp(conn);
        }
        return sinistroDaoImp;
    }

    @Override
    public void inserir(Sinistro sinistro) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("""
                insert into sinistros(descricao, valor, seguro_id) values(?, ?, ?)
            """, new String[] {"id"});
            ps.setString(1, sinistro.getDescricao());
            ps.setDouble(2, sinistro.getValor());
            ps.setLong(3, sinistro.getSeguro().getId());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()){
                    Long id = rs.getLong(1);
                    sinistro.setId(id);
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
    public void alterar(Sinistro sinistro) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("""
                update sinistros
                    set descricao = ?, valor = ?
                where id = ?
            """);
           ps.setString(1, sinistro.getDescricao());
           ps.setDouble(2, sinistro.getValor());
           ps.setLong(3, sinistro.getId());

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
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Sinistro> listar(Long seguroId) {
        List<Sinistro> sinistros = new ArrayList<>();
        PreparedStatement s = null;
        try {
            s = conn.prepareStatement("""
                select si.*, s.*, a.*, c.* from sinistros si 
                inner join seguros s on(s.id = si.seguro_id)
                inner join apolices a on (s.apolice_id = a.id) 
                inner join clientes c on(a.cliente_id = c.id)
                where si.seguro_id = ?
            """ );
            s.setLong(1, seguroId);
            ResultSet rs = s.executeQuery();
            while (rs.next()){
                sinistros.add(instanciaSinistro(rs));
            }
            s.close();
            rs.close();
            return sinistros;
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private Sinistro instanciaSinistro(ResultSet rs) throws SQLException{
        Sinistro sinistro = new Sinistro(rs.getString("descricao"), rs.getDouble("valor"), instanciaSeguro(rs));
        sinistro.setId(rs.getLong("id"));
        return sinistro;
    }


}
