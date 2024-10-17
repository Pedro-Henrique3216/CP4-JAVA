package br.com.fiap.dao;

import br.com.fiap.model.Sinistro;

import java.sql.Connection;
import java.util.List;

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

    }

    @Override
    public void alterar(Sinistro sinistro) {

    }

    @Override
    public void excluir(Long id) {

    }

    @Override
    public List<Sinistro> listar() {
        return List.of();
    }
}
