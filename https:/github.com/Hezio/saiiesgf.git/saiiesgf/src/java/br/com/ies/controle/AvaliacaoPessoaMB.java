/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.controle;

import java.br.com.ies.bean.FichaPessoa;
import java.br.com.ies.bean.ModeloFicha;
import java.br.com.ies.bean.Professor;
import java.br.com.ies.bean.Questao;
import java.br.com.ies.bean.RespostaFicha;
import java.br.com.ies.dao.impl.FichaPessoaDAOImp;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class AvaliacaoPessoaMB implements Serializable {

    private ModeloFicha modeloFicha;
    private HashMap<Questao, RespostaFicha> respostas;
    private FichaPessoa entity;
    private Professor professor;

    public AvaliacaoPessoaMB() {
        try {
            entity = new FichaPessoa();

            String id = getRequestParam("tipoFicha");
            if (id != null) {
                modeloFicha = new FichaPessoaDAOImp().procurarModeloFicha(Integer.parseInt(id));
                if (modeloFicha != null) {
                    popularHash();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(AvaliacaoPessoaMB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String getRequestParam(String bean) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        return request.getParameter(bean);
    }

    private void popularHash() {
        respostas = new HashMap<Questao, RespostaFicha>();
        if (modeloFicha.getQuestoes() != null) {
            for (Questao questao : modeloFicha.getQuestoes()) {
                respostas.put(questao, new RespostaFicha());
            }
        }

    }

    public Object getSessionBean(String bean) {
        FacesContext context = FacesContext.getCurrentInstance();
        Object object = context.getExternalContext().getSessionMap().get(bean);
        return object;
    }

    public String salvarAvaliacao() {
        try {
            LoginControle lc = (LoginControle) getSessionBean("loginControle");
            entity.setModeloFicha(modeloFicha);
            entity.setProfessor(professor);
            if (lc != null && lc.getUsuario() != null) {
                if (lc.getUsuario().getAluno() != null) {
                    entity.setAluno(lc.getUsuario().getAluno());
                } else if (lc.getUsuario().getProfessor() != null) {
                    entity.setProfessor(lc.getUsuario().getProfessor());
                }
            }

            List<RespostaFicha> list = getListaRespostas(respostas);
            entity.setRespostaFichas(list);
            new FichaPessoaDAOImp().salvar(entity);

            FacesMessage msg = null;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Registrado com sucesso!");
            RequestContext context = RequestContext.getCurrentInstance();

            FacesContext.getCurrentInstance().addMessage(null, msg);
            context.addCallbackParam("sucesso", true);

            return "sucesso";
        } catch (Exception ex) {
            Logger.getLogger(AvaliacaoPessoaMB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private List<RespostaFicha> getListaRespostas(HashMap<Questao, RespostaFicha> answers) {
        List<RespostaFicha> lista = new ArrayList<RespostaFicha>();
        if (answers != null) {
            for (Entry<Questao, RespostaFicha> map : answers.entrySet()) {
                RespostaFicha respostaFicha = map.getValue();
                respostaFicha.setFichaPessoa(entity);
                respostaFicha.setQuestao(map.getKey());
                lista.add(respostaFicha);
            }
        }
        return lista;
    }

    public ModeloFicha getModeloFicha() {
        return modeloFicha;
    }

    public void setModeloFicha(ModeloFicha modeloFicha) {
        this.modeloFicha = modeloFicha;
    }

    public HashMap<Questao, RespostaFicha> getRespostas() {
        return respostas;
    }

    public void setRespostas(HashMap<Questao, RespostaFicha> respostas) {
        this.respostas = respostas;
    }

    public FichaPessoa getEntity() {
        return entity;
    }

    public void setEntity(FichaPessoa entity) {
        this.entity = entity;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
    
    
}
