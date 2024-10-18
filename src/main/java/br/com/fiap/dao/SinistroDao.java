package br.com.fiap.dao;

import br.com.fiap.model.Sinistro;

import java.util.List;

public interface SinistroDao {

    void inserir(Sinistro sinistro);
    void alterar(Sinistro sinistro);
    void excluir(Long id);
    List<Sinistro> listar(Long seguroId);
}
