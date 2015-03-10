package br.com.ies.controle;

import java.br.com.ies.bean.Cidade;
import java.br.com.ies.bean.Estado;
import java.br.com.ies.bean.Professor;
import java.br.com.ies.dao.impl.EnderecoDAOimp;
import java.br.com.ies.dao.impl.ProfessorDAOimp;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class ProfessorControle implements Serializable{

    private Professor professor;
    private Estado estado;
    private List<Professor> professoresFiltrados;
    private DataModel<Professor> listaProfessores;

    public ProfessorControle() {
        carregaDados();
        professor = new Professor();
    }

    private void carregaDados() {
        try {
            professoresFiltrados = new ProfessorDAOimp().listaProfessor();
            listaProfessores = new ListDataModel<Professor>(professoresFiltrados);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void carregarProfessor(Professor prof) {
        try {
            professor = new ProfessorDAOimp().procurarProfesor(prof.getIdPessoa());
        } catch (Exception ex) {
            Logger.getLogger(ProfessorControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void excluirProfessor(Professor prof) {
        FacesMessage msg = null;
        boolean sucess = false;

        try {
            new ProfessorDAOimp().excluir(prof);
            carregaDados();
            sucess = true;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
                    "Excluido com sucesso!");
        } catch (Exception e) {
            sucess = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao Excluir!", e.getMessage());
        }
        RequestContext context = RequestContext.getCurrentInstance();

        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("sucess", sucess);
    }

    public void adicionarProfessor() throws Exception {
        FacesMessage msg = null;
        boolean sucess = false;
        try {
            new ProfessorDAOimp().salvar(professor);
            carregaDados();
            sucess = true;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
                    "Registrado com sucesso!");
        } catch (Exception e) {
            sucess = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao Cadastrar!", e.getMessage());
        }
        RequestContext context = RequestContext.getCurrentInstance();

        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("sucess", sucess);
    }

   
     public synchronized List<SelectItem> getComboCidade() {
        if (estado != null) {
            try {
                List<SelectItem> items = new ArrayList<SelectItem>();
                List<Cidade> lista = new EnderecoDAOimp().listaCidades(estado.getCod_estado());
                if (lista != null) {
                    for (Cidade cidade : lista) {
                        items.add(new SelectItem(cidade, cidade.getNome_cidade()));
                    }
                }
                return items;
            } catch (Exception ex) {
                Logger.getLogger(AlunoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public DataModel<Professor> getListaProfessores() {
        return listaProfessores;
    }

    public void setListaProfessores(DataModel<Professor> listaProfessores) {
        this.listaProfessores = listaProfessores;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void prepararAdicionarProfessor(ActionEvent actionEvent) {
        professor = new Professor();
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Professor> getProfessoresFiltrados() {
        return professoresFiltrados;
    }

    public void setProfessoresFiltrados(List<Professor> professoresFiltrados) {
        this.professoresFiltrados = professoresFiltrados;
    }

   
}
