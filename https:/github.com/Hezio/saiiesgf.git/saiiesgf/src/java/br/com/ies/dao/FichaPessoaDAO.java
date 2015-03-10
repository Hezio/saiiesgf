/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.dao;

import java.br.com.ies.bean.FichaPessoa;
import java.br.com.ies.bean.ItemResposta;
import java.br.com.ies.bean.ModeloFicha;


public interface FichaPessoaDAO {

    public ModeloFicha procurarModeloFicha(Integer idModelo) throws Exception;

    public void alterar(FichaPessoa avaliacao) throws Exception;

    public void salvar(FichaPessoa avaliacao) throws Exception;
    
    public ItemResposta findItemResposta(Long id) throws Exception;
}
