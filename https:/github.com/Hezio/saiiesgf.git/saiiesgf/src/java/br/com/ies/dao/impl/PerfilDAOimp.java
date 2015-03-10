package br.com.ies.dao.impl;

import java.br.com.ies.bean.Perfil;
import java.br.com.ies.dao.PerfilDAO;
import java.br.com.ies.util.EntityManagerFactoryPool;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PerfilDAOimp implements PerfilDAO,Serializable {

    protected static EntityManager getEntityManager() {
        return EntityManagerFactoryPool.getInstance().getEmf().createEntityManager();
    }

    @Override
    public void salvar(Perfil perfil) throws Exception {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();

            if (perfil.getId() == null || perfil.getId() == 0) {
                entityManager.persist(perfil);
            } else {
                entityManager.merge(perfil);
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
    }

    @Override
    public void atualizar(Perfil perfil) throws Exception {
        salvar(perfil);
    }

    @Override
    public void excluir(Perfil perfil) throws Exception {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.getReference(Perfil.class, perfil.getId()));
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

    @Override
    public List<Perfil> todosPerfis() throws Exception {
        EntityManager em = getEntityManager();
        try {
            String sql = " SELECT obj FROM " + Perfil.class.getSimpleName() + " obj "
                    + " WHERE 1=1 ";

            Query createQuery = em.createQuery(sql);

            List<Perfil> objects = createQuery.getResultList();
            return objects;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getSimpleName()).log(Level.WARNING, null, ex);

            return new ArrayList<Perfil>();
        } finally {
            em.close();
        }
    }

    @Override
    public Perfil procurarPerfil(Integer id) throws Exception {
        EntityManager em = getEntityManager();
        try {
           return em.find(Perfil.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Perfil> procurar(String nome) throws Exception {
     EntityManager em = getEntityManager();
        try {
            String sql = " SELECT obj FROM " + Perfil.class.getSimpleName() + " obj "
                    + " WHERE obj.nome like '" + nome + "'";

            Query createQuery = em.createQuery(sql);

            List<Perfil> objects = createQuery.getResultList();
            return objects;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getSimpleName()).log(Level.WARNING, null, ex);

            return new ArrayList<Perfil>();
        } finally {
            em.close();
        }
    }
}
