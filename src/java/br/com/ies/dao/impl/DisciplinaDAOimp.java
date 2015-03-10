/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.dao.impl;

import br.com.ies.bean.Disciplina;
import br.com.ies.dao.DisciplinaDAO;
import static br.com.ies.dao.impl.AlunoDAOimp.getEntityManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class DisciplinaDAOimp implements DisciplinaDAO,Serializable{
    
    @Override
    public List<Disciplina> listaDisciplinas() throws Exception {
        EntityManager em = getEntityManager();
        try {
            String sql = " SELECT obj FROM " + Disciplina.class.getSimpleName() + " obj "
                    + " WHERE 1=1 order by nome_disciplina";

            Query createQuery = em.createQuery(sql);

            List<Disciplina> objects = createQuery.getResultList();
            return objects;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getSimpleName()).log(Level.WARNING, null, ex);

            return new ArrayList<Disciplina>();
        } finally {
            em.close();
        }
    }
    
    public List<Disciplina> listaDisciplinasAluno(Integer idAluno) throws Exception {
        EntityManager em = getEntityManager();
        try {
            String sql = " SELECT obj FROM " + Disciplina.class.getSimpleName() + " obj "+
                         " INNER JOIN obj.alunos a "+
                        " WHERE a.idPessoa = "+idAluno + " order by nome_disciplina";

            Query createQuery = em.createQuery(sql);

            List<Disciplina> objects = createQuery.getResultList();
            return objects;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getSimpleName()).log(Level.WARNING, null, ex);

            return new ArrayList<Disciplina>();
        } finally {
            em.close();
        }
    }

    @Override
    public Disciplina procurarDisciplina(Long id) {
        EntityManager em = getEntityManager();
        try {
            Disciplina disciplina = em.find(Disciplina.class, id);
            if(disciplina != null){
                carregaLazyDisciplina(disciplina);
            }
            return disciplina;
        } finally {
            em.close();
        }
    }
    
       public Disciplina verificaDisciplina(Integer id_disciplina) {
//            String SQL = "select * from disciplina where flg_ativa='N'and id_disciplina=" + id_disciplina;
        return null;
    }

    // trocar de lugar feito s� para testar chart
    public Map<String, Integer> countDisciplinas() throws Exception {
        Map<String, Integer> countDisciplinas = new HashMap<String, Integer>();
//            String SQL = "select count(*) total, nome_disciplina from aluno_disciplina ad join disciplina d on d.id_disciplina = ad.id_disciplina group by ad.id_disciplina";
        return countDisciplinas;
    }

    private Disciplina carregaLazyDisciplina(Disciplina obj) {
        if (obj.getAlunos() != null) {
            obj.getAlunos().size();
        }
        if (obj.getProfessores() != null) {
            obj.getProfessores().size();
        }
        return obj;
    }
    
}
