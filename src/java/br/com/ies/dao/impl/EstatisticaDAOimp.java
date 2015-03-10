/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.dao.impl;

import br.com.ies.dao.EstatisticaDAO;
import br.com.ies.util.EntityManagerFactoryPool;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gustavo
 */
public class EstatisticaDAOimp implements EstatisticaDAO,Serializable{
    
    protected static EntityManager getEntityManager() {
        return EntityManagerFactoryPool.getInstance().getEmf().createEntityManager();
    }
    
    @Override
    public int getCountAvaliacoes(Long idModelo){
        return getCountAvaliacoes(idModelo, null);
    }
    
    @Override
    public int getCountAvaliacoes(Long idModelo, Integer idProfessor) {
        EntityManager em = getEntityManager();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT COUNT( obj )");
            sb.append(" FROM FichaPessoa obj");
            sb.append(" INNER JOIN obj.modeloFicha AS modelo");
            sb.append(" WHERE modelo.id=").append(idModelo);
            
            if(idProfessor != null){
                sb.append(" AND obj.professor.idPessoa=").append(idProfessor);
            }

            Query createQuery = em.createQuery(sb.toString());
            return ((Long) createQuery.getResultList().get(0)).intValue();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getSimpleName()).log(Level.WARNING, null, ex);
            return 0;
        } finally {
            em.close();
        }
    }
    
    @Override
     public int getCountQtdRespondida(int idQuestao, Long idItemResposta) {
        return getCountQtdRespondida(idQuestao, idItemResposta, null);
    }
    
     @Override
     public int getCountQtdRespondida(int idQuestao, Long idItemResposta, Integer IdProfessor) {
        EntityManager em = getEntityManager();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(" select count(distinct idrespostaficha) as qtd");
            sb.append(" from respostaficha rf ");
            sb.append(" left join fichapessoa fp on(rf.fichapessoa_idfichapessoa = fp.idfichapessoa)");
            sb.append(" where rf.itemresposta_iditemresposta  = ").append(idItemResposta);
            sb.append(" and rf.questao_idquestao = ").append(idQuestao);
            
            if(IdProfessor != null){
                sb.append(" and fp.professor_idpessoa = ").append(IdProfessor);
            }

            Query createQuery = em.createNativeQuery(sb.toString());
            return((BigInteger) createQuery.getResultList().get(0)).intValue();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getSimpleName()).log(Level.WARNING, null, ex);
            return 0;
        } finally {
            em.close();
        }
    }
    
    
    
    
}
