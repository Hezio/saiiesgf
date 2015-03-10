package br.com.ies.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.ies.bean.Usuario;
import static br.com.ies.dao.impl.ProfessorDAOimp.getEntityManager;
import br.com.ies.util.EntityManagerFactoryPool;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UsuarioDAOImp implements Serializable {

    protected static EntityManager getEntityManager() {
        return EntityManagerFactoryPool.getInstance().getEmf().createEntityManager();
    }

    public void atualizar(Usuario usuario) throws Exception {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();

            if (usuario.getId() == null || usuario.getId() == 0) {
                entityManager.persist(usuario);
            } else {
                entityManager.merge(usuario);
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

    public void salvar(Usuario usuario) throws Exception {
        atualizar(usuario);
    }

    public void excluir(Usuario usuario) throws Exception {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.getReference(Usuario.class, usuario.getId()));
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

    public Usuario buscaUsuario(Integer id) throws Exception{
        EntityManager em = getEntityManager();
        try {
            Usuario aluno = em.find(Usuario.class, id);
            return aluno;
        } finally {
            em.close();
        }
    }
    
    private List<Usuario> findUsuarioEntities(boolean all, int max, int offset) {
        EntityManager em = getEntityManager();
        try {
            String sql = " SELECT obj FROM " + Usuario.class.getSimpleName() + " obj "
                    + " INNER JOIN obj.perfil AS p "
                    + " WHERE 1=1 ";

            Query createQuery = em.createQuery(sql);

            if (max > 0) {
                createQuery.setMaxResults(max);
            }

            if (offset > 0) {
                createQuery.setFirstResult(offset);
            }

            List<Usuario> objects = createQuery.getResultList();
            for (Usuario objeto : objects) {
                carregaLazy(objeto);
            }

            return objects;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getSimpleName()).log(Level.WARNING, null, ex);

            return new ArrayList<Usuario>();
        } finally {
            em.close();
        }

    }

    public List<Usuario> buscaTodos() throws Exception {
        return findUsuarioEntities(true, -1, -1);
    }

    private Usuario carregaLazy(Usuario user) {


        return user;
    }

    public Usuario loga(Usuario usuario) throws Exception {
        EntityManager em = getEntityManager();
        try {
            String sql = " SELECT obj FROM " + Usuario.class.getSimpleName() + " obj "
                    + " INNER JOIN obj.perfil AS p "
                    + " WHERE obj.login = '"+usuario.getLogin()+"' AND obj.senha = '"+usuario.getSenha()+"'";
             Query createQuery = em.createQuery(sql);
             Usuario user  = (Usuario) createQuery.getSingleResult();
            if (user == null) {
                 throw new Exception("Usuario e senha não conferem.");
            }else{
                return user;
            } 
        } catch (Exception se) {
            throw new Exception("Erro ao tentar achar dados do usuario" + se.getMessage());
        } finally {
            em.close();
        }
    }
    
     public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        }catch (Exception ex) {
            Logger.getLogger(this.getClass().getSimpleName()).log(Level.WARNING, null, ex);
            return 0;
        } finally {
            em.close();
        }
    }
}
