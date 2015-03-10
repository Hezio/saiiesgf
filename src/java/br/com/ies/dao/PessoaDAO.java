package br.com.ies.dao;

import java.util.List;

import br.com.ies.bean.Pessoa;

public interface PessoaDAO {

    Pessoa salvar(Pessoa pessoa) throws Exception;

    void alterar(Pessoa pessoa) throws Exception;

    void excluir(Pessoa pessoa) throws Exception;

    List procurar(String nome) throws Exception;

    Pessoa procurar(Integer idPessoa) throws Exception;

    List listaPessoa() throws Exception;
}
