package br.com.ies.dao.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.ies.bean.Aluno;
import br.com.ies.dao.AlunoDAO;
import static br.com.ies.dao.impl.UsuarioDAOImp.getEntityManager;
import br.com.ies.util.EntityManagerFactoryPool;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AlunoDAOimp implements AlunoDAO,Serializable {

    protected static EntityManager getEntityManager() {
        return EntityManagerFactoryPool.getInstance().getEmf().createEntityManager();
    }

    public Aluno salvar(Aluno aluno) throws Exception {

        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();

            if (aluno.getIdPessoa() == null || aluno.getIdPessoa() == 0) {
                entityManager.persist(aluno);
            } else {
                entityManager.merge(aluno);
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

    public Aluno alterar(Aluno aluno) throws Exception {
        return salvar(aluno);
    }

    public void excluir(Aluno aluno) throws Exception {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.getReference(Aluno.class, aluno.getIdPessoa()));
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

    public Aluno procurar(Integer id) {
        EntityManager em = getEntityManager();
        try {
            Aluno aluno =  em.find(Aluno.class, id);
            if(aluno != null){
                carregaLazy(aluno);
            }
            return aluno;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Aluno> procurar(String nome) throws Exception {
        EntityManager em = getEntityManager();
        try {
            String sql = " SELECT obj FROM " + Aluno.class.getSimpleName() + " obj "
                    + " WHERE obj.nome like '" + nome + "'";

            Query createQuery = em.createQuery(sql);

            List<Aluno> objects = createQuery.getResultList();
            for (Aluno objeto : objects) {
                carregaLazy(objeto);
            }

            return objects;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getSimpleName()).log(Level.WARNING, null, ex);

            return new ArrayList<Aluno>();
        } finally {
            em.close();
        }
    }

    public List<Aluno> listaAlunos() throws Exception {
        EntityManager em = getEntityManager();
        try {
            String sql = " SELECT obj FROM " + Aluno.class.getSimpleName() + " obj "
                    + " WHERE 1=1 ";

            Query createQuery = em.createQuery(sql);

            List<Aluno> objects = createQuery.getResultList();
            for (Aluno objeto : objects) {
                carregaLazy(objeto);
            }

            return objects;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getSimpleName()).log(Level.WARNING, null, ex);

            return new ArrayList<Aluno>();
        } finally {
            em.close();
        }
    }

    

    private Aluno carregaLazy(Aluno user) {
        if (user.getDisciplinas() != null) {
            user.getDisciplinas().size();
        }

        if (user.getFichasAluno() != null) {
            user.getFichasAluno().size();
        }

        return user;
    }
}
