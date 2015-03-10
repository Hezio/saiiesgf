/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.dao;

public interface EstatisticaDAO {

    public int getCountAvaliacoes(Long idModelo);

    public int getCountAvaliacoes(Long idModelo, Integer idProfessor);

    public int getCountQtdRespondida(int idQuestao, Long idItemResposta);

    public int getCountQtdRespondida(int idQuestao, Long idItemResposta, Integer idProfessor);
}
