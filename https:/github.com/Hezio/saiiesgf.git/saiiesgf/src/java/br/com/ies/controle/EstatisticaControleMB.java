/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.controle;

import java.br.com.ies.bean.ItemResposta;
import java.br.com.ies.bean.ModeloFicha;
import java.br.com.ies.bean.Professor;
import java.br.com.ies.bean.Questao;
import java.br.com.ies.dao.impl.EstatisticaDAOimp;
import java.br.com.ies.dao.impl.FichaPessoaDAOImp;
import java.br.com.ies.dao.impl.ProfessorDAOimp;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


@ManagedBean
@ViewScoped
public class EstatisticaControleMB implements Serializable {

    private ModeloFicha modeloFicha;
    private int totalAvaliacaoesRespondidas;
    private Professor professor;

    public EstatisticaControleMB() {

        try {

            String id = getRequestParam("tipoFicha");
            String idProfessor = getRequestParam("idProf");
            
            if(idProfessor != null){
                professor = new ProfessorDAOimp().procurarProfesor(Integer.parseInt(idProfessor));
            }
            
            if (id != null) {
                modeloFicha = new FichaPessoaDAOImp().procurarModeloFicha(Integer.parseInt(id));
                if (modeloFicha != null) {
                    carregarTotalAvaliacaoRespondidas();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(AvaliacaoPessoaMB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void buscar(){
        carregarTotalAvaliacaoRespondidas();
    }

    public int getQtdRespondida(Questao questao, ItemResposta itemResposta){
        Integer idProfessor = professor != null ? professor.getIdPessoa() : null;
        return getQtdRespondida(questao, itemResposta, idProfessor);
    }
    
    
    private int getQtdRespondida(Questao questao, ItemResposta itemResposta, Integer idProfessor) {
        try {
            if (questao != null && itemResposta != null) {
                return new EstatisticaDAOimp().getCountQtdRespondida(questao.getIdQuestao(), itemResposta.getIdItemResposta(), idProfessor);
            }
        } catch (Exception e) {
            Logger.getLogger(AvaliacaoPessoaMB.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }

    private void carregarTotalAvaliacaoRespondidas() {
        try {
            Integer idProfessor = professor != null ? professor.getIdPessoa() : null;
            totalAvaliacaoesRespondidas = new EstatisticaDAOimp().getCountAvaliacoes(modeloFicha.getIdModeloFicha(), idProfessor);
        } catch (Exception e) {
            Logger.getLogger(AvaliacaoPessoaMB.class.getName()).log(Level.SEVERE, null, e);
            totalAvaliacaoesRespondidas = 0;
        }
    }

    private String getRequestParam(String bean) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        return request.getParameter(bean);
    }

    public ModeloFicha getModeloFicha() {
        return modeloFicha;
    }

    public void setModeloFicha(ModeloFicha modeloFicha) {
        this.modeloFicha = modeloFicha;
    }

    public int getTotalAvaliacaoesRespondidas() {
        return totalAvaliacaoesRespondidas;
    }

    public void setTotalAvaliacaoesRespondidas(int totalAvaliacaoesRespondidas) {
        this.totalAvaliacaoesRespondidas = totalAvaliacaoesRespondidas;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
    
    
}
