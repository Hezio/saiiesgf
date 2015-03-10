package br.com.ies.dao;

import java.util.List;

import br.com.ies.bean.Usuario;

public interface UsuarioDAO {

    void salvar(Usuario usuario) throws Exception;

    void atualizar(Usuario usuario) throws Exception;

    void excluir(Usuario usuario) throws Exception;

    Usuario procurarUsuario(Integer id) throws Exception;

    List<Usuario> todosUsuarios() throws Exception;

    public boolean loga(Usuario usuario) throws Exception;

    public Usuario pesquisaUsuario(String login) throws Exception;

    public Usuario buscaUsuario(Integer id)throws Exception;
}
