/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.dao;

import br.com.ies.bean.Disciplina;
import java.util.List;

public interface DisciplinaDAO {

    List<Disciplina> listaDisciplinas() throws Exception;

    Disciplina procurarDisciplina(Long id);

    List<Disciplina> listaDisciplinasAluno(Integer idAluno) throws Exception;
}
