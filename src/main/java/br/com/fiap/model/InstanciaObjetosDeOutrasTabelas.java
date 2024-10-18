package br.com.fiap.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InstanciaObjetosDeOutrasTabelas {

    public static Seguro instanciaSeguro(ResultSet rs) throws SQLException {
        Seguro seguro = new Seguro(TipoSeguro.valueOf(rs.getString("tipo_seguro")), instaciaApolice(rs),
                rs.getDouble("vl_cobertura"), rs.getDouble("premio"), StatusSeguro.valueOf(rs.getString("status_seguro")));
        seguro.setId(rs.getLong("seguro_id"));
        return seguro;
    }

    public static Apolice instaciaApolice(ResultSet rs) throws SQLException {
        Apolice apolice = new Apolice(instaciaCliente(rs), rs.getString("coberturas"));
        apolice.setId(rs.getLong("apolice_id"));
        return apolice;
    }

    public static Cliente instaciaCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente(rs.getString("nome"), rs.getString("email"),
                rs.getString("cpf"), rs.getString("telefone"),
                rs.getDate("dt_nascimento").toLocalDate());
        cliente.setId(rs.getLong("cliente_id"));
        return cliente;
    }
}
