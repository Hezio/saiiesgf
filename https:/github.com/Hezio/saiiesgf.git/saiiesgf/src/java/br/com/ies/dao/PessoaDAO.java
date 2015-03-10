package br.com.ies.dao;

import java.br.com.ies.bean.Pessoa;
import java.util.List;

public interface PessoaDAO {

    Pessoa salvar(Pessoa pessoa) throws Exception;

    void alterar(Pessoa pessoa) throws Exception;

    void excluir(Pessoa pessoa) throws Exception;

    List procurar(String nome) throws Exception;

    Pessoa procurar(Integer idPessoa) throws Exception;

    List listaPessoa() throws Exception;
}
