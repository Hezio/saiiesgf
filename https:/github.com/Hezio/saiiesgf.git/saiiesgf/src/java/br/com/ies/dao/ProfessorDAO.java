package br.com.ies.dao;

import java.br.com.ies.bean.Professor;
import java.util.List;

public interface ProfessorDAO {

    Professor salvar(Professor professor) throws Exception;

    void alterar(Professor professor) throws Exception;

    void excluir(Professor professor) throws Exception;

    Professor procurarProfesor(Integer idProfessor) throws Exception;

    List<Professor> procurarProfesor(String nome) throws Exception;

    List<Professor> listaProfessor() throws Exception;
}
