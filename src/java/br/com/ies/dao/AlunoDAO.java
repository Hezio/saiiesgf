package br.com.ies.dao;

import java.util.List;

import br.com.ies.bean.Aluno;

public interface AlunoDAO {

    Aluno salvar(Aluno aluno) throws Exception;

    Aluno alterar(Aluno aluno) throws Exception;

    void excluir(Aluno aluno) throws Exception;

    List<Aluno> procurar(String nome) throws Exception;

    Aluno procurar(Integer idPessoa) throws Exception;

    List<Aluno> listaAlunos() throws Exception;
    
    
    
}
