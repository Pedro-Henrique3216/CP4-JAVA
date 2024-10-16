package br.com.fiap.db.dao;

import br.com.fiap.model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoImp implements ClienteDao {

    private Connection conn;

    public ClienteDaoImp(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void inserir(Cliente cliente) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("""
                insert into cliente(nome, cpf, telefone, dt_nascimento, email) values(?, ?, ?, ?, ?, ?)
            """, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getTelefone());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()){
                    Long id = rs.getLong(1);
                    cliente.setId(id);
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
    public void alterar(Cliente cliente) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("""
                update cliente
                    set nome = ?, dt_nascimento = ?, telefone = ?, email = ?
                where id = ?
            """);
            ps.setString(1, cliente.getNome());
            ps.setDate(2, Date.valueOf(cliente.getDataNascimento()));
            ps.setString(3, cliente.getTelefone());
            ps.setLong(4, cliente.getId());

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
                delete cliente where id = ?
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
    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();
        Statement s = null;
        try {
            s = conn.createStatement();
            ResultSet rs = s.executeQuery("""
                select * from cliente
            """);
            while (rs.next()){
                clientes.add(instaciaCliente(rs));
            }
            s.close();
            rs.close();
            return clientes;
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private Cliente instaciaCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente(rs.getString("nome"), rs.getString("email"),
                rs.getString("cpf"), rs.getString("telefone"),
                rs.getDate("dt_nascimento").toLocalDate());
        cliente.setId(rs.getLong("id"));
        return cliente;
    }
}
