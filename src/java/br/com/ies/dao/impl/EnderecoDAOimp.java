package br.com.ies.dao.impl;

import br.com.ies.bean.Cidade;
import java.util.List;

import br.com.ies.bean.Endereco;
import br.com.ies.bean.Estado;
import br.com.ies.dao.EnderecoDAO;
import static br.com.ies.dao.impl.UsuarioDAOImp.getEntityManager;
import br.com.ies.util.EntityManagerFactoryPool;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class EnderecoDAOimp implements EnderecoDAO,Serializable {

    protected static EntityManager getEntityManager() {
        return EntityManagerFactoryPool.getInstance().getEmf().createEntityManager();
    }

    

   
    public Cidade procurarCidade(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cidade.class, id);
        } finally {
            em.close();
        }
    }
    
    public Estado procurarEstado(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estado.class, id);
        } finally {
            em.close();
        }
    }
   
    
    public List<Cidade> listaCidades (Long idEstado)throws Exception{
        EntityManager em = getEntityManager();
        try {
            String sql = " SELECT obj FROM " + Cidade.class.getSimpleName() + " obj "
                    + " INNER JOIN  obj.estado estado"
                    + " WHERE  estado.cod_estado = "+ idEstado+" order by obj.nome_cidade";

            Query createQuery = em.createQuery(sql);

            List<Cidade> objects = createQuery.getResultList();
            return objects;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getSimpleName()).log(Level.WARNING, null, ex);

            return new ArrayList<Cidade>();
        } finally {
            em.close();
        }
    }
    
    public List<Estado> listaEstados()throws Exception{
        EntityManager em = getEntityManager();
        try {
            String sql = " SELECT obj FROM " + Estado.class.getSimpleName() + " obj "
                    + " WHERE 1=1 ";

            Query createQuery = em.createQuery(sql);

            List<Estado> objects = createQuery.getResultList();
            return objects;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getSimpleName()).log(Level.WARNING, null, ex);

            return new ArrayList<Estado>();
        } finally {
            em.close();
        }
    }
    
    
}
