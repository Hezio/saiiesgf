package br.com.ies.dao.impl;

import java.br.com.ies.bean.Professor;
import java.br.com.ies.dao.ProfessorDAO;
import java.br.com.ies.util.EntityManagerFactoryPool;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ProfessorDAOimp implements ProfessorDAO,Serializable {

    protected static EntityManager getEntityManager() {
        return EntityManagerFactoryPool.getInstance().getEmf().createEntityManager();
    }

    public Professor salvar(Professor professor) throws Exception {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();

            if (professor.getIdPessoa() == null || professor.getIdPessoa() == 0) {
                entityManager.persist(professor);
            } else {
                entityManager.merge(professor);
            }

            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getSimpleName()).log(Level.SEVERE, null, ex);
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            entityManager.close();
        }

        return null;
    }

    public void alterar(Professor professor) throws Exception {
        salvar(professor);
    }

    public void excluir(Professor professor) throws Exception {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.getReference(Professor.class, professor.getIdPessoa()));
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getSimpleName()).log(Level.SEVERE, null, ex);
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            entityManager.close();
        }
    }

    public Professor procurarProfesor(Integer idPessoa) throws Exception {
        EntityManager em = getEntityManager();
        try {
            Professor aluno = em.find(Professor.class, idPessoa);
            if (aluno != null) {
                carregaLazy(aluno);
            }
            return aluno;
        } finally {
            em.close();
        }
    }

    public List<Professor> procurarProfesor(String nome) throws Exception {
        EntityManager em = getEntityManager();
        try {
            String sql = " SELECT obj FROM " + Professor.class.getSimpleName() + " obj "
                    + " WHERE obj.nome like '" + nome + "'";

            Query createQuery = em.createQuery(sql);

            List<Professor> objects = createQuery.getResultList();
            for (Professor objeto : objects) {
                carregaLazy(objeto);
            }

            return objects;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getSimpleName()).log(Level.WARNING, null, ex);

            return new ArrayList<Professor>();
        } finally {
            em.close();
        }
    
    }

    public List<Professor> listaProfessor() throws Exception {
        EntityManager em = getEntityManager();
        try {
            String sql = " SELECT obj FROM " + Professor.class.getSimpleName() + " obj "
                    + " WHERE 1=1 ";

            Query createQuery = em.createQuery(sql);

            List<Professor> objects = createQuery.getResultList();
            for (Professor objeto : objects) {
                carregaLazy(objeto);
            }

            return objects;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getSimpleName()).log(Level.WARNING, null, ex);

            return new ArrayList<Professor>();
        } finally {
            em.close();
        }
    }

    private Professor carregaLazy(Professor obj) {
        if (obj.getFichasProfessor() != null) {
            obj.getFichasProfessor().size();
        }
        return obj;
    }
}
