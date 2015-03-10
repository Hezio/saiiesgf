/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.dao.impl;

import br.com.ies.bean.FichaPessoa;
import br.com.ies.bean.ItemResposta;
import br.com.ies.bean.ModeloFicha;
import br.com.ies.bean.Questao;
import br.com.ies.dao.FichaPessoaDAO;
import static br.com.ies.dao.impl.AlunoDAOimp.getEntityManager;
import br.com.ies.enuns.TipoModeloFicha;
import br.com.ies.util.EntityManagerFactoryPool;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gustavo
 */
public class FichaPessoaDAOImp implements FichaPessoaDAO,Serializable {

    protected static EntityManager getEntityManager() {
        return EntityManagerFactoryPool.getInstance().getEmf().createEntityManager();
    }

    public void salvar(FichaPessoa avaliacao) throws Exception {

        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();

            if (avaliacao.getIdFichaPessoa() == null || avaliacao.getIdFichaPessoa() == 0) {
                entityManager.persist(avaliacao);
            } else {
                entityManager.merge(avaliacao);
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

    public void alterar(FichaPessoa avaliacao) throws Exception {
        salvar(avaliacao);
    }

    @Override
    public ModeloFicha procurarModeloFicha(Integer idModelo) throws Exception {
        EntityManager em = getEntityManager();
        ModeloFicha ficha = null;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("Select obj from ModeloFicha  obj where obj.tipoModeloFicha = ").append(idModelo);
            Query query = em.createQuery(sb.toString());
            ficha = (ModeloFicha) query.getSingleResult();
            carregaLazyModelo(ficha);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getSimpleName()).log(Level.SEVERE, null, ex);
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
        return ficha;
    }

    @Override
    public ItemResposta findItemResposta(Long id) throws Exception {
        EntityManager em = getEntityManager();
        ItemResposta item = null;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("Select obj from ItemResposta  obj where obj.id = ").append(id);
            Query query = em.createQuery(sb.toString());
            item = (ItemResposta) query.getSingleResult();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getSimpleName()).log(Level.SEVERE, null, ex);
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
        return item;
    }
    
    
    
    public int getCountFichasRespondidas(TipoModeloFicha modeloFicha) {
        EntityManager em = getEntityManager();
        try {
            String sql = " SELECT COUNT( obj )"
                    + " FROM  FichaPessoa obj "
                    + " LEFT JOIN obj.professor AS professor "
                    + " LEFT JOIN obj.aluno AS aluno "
                    + " LEFT JOIN obj.modeloFicha AS mf "
                    + " WHERE mf.tipoModeloFicha = "+modeloFicha.ordinal();
            
            if(TipoModeloFicha.ALUNO_INSTITUICAO.equals(modeloFicha)){
                sql +=" AND professor.idPessoa IS NULL AND aluno.id IS NOT NULL";
            }else if(TipoModeloFicha.ALUNO_PROFESSOR.equals(modeloFicha)){
                sql +=" AND professor.idPessoa IS NOT NULL AND aluno.id IS NOT NULL";
            }else{
                sql +=" AND professor.idPessoa IS NOT NULL AND aluno.id IS NULL";
            }


            Query createQuery = em.createQuery(sql);
            return ((Long) createQuery.getResultList().get(0)).intValue();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getSimpleName()).log(Level.WARNING, null, ex);
            return 0;
        } finally {
            em.close();
        }
    }

    private void carregaLazyModelo(ModeloFicha ficha) {
        if (ficha.getQuestoes() != null) {
            ficha.getQuestoes().size();
            for (Questao questao : ficha.getQuestoes()) {
                if (questao.getItensResposta() != null) {
                    questao.getItensResposta().size();
                }
            }

        }


    }
}
