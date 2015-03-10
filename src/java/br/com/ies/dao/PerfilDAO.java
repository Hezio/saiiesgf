package br.com.ies.dao;

import java.util.List;

import br.com.ies.bean.Perfil;

public interface PerfilDAO {

    void salvar(Perfil perfil) throws Exception;

    void atualizar(Perfil perfil) throws Exception;

    void excluir(Perfil perfil) throws Exception;

    List<Perfil> todosPerfis() throws Exception;

    List<Perfil> procurar(String nome) throws Exception;

    Perfil procurarPerfil(Integer id) throws Exception;
}
