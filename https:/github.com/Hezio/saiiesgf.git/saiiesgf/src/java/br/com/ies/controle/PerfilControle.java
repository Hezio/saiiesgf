package br.com.ies.controle;

import java.br.com.ies.bean.Perfil;
import java.br.com.ies.dao.impl.PerfilDAOimp;
import java.io.Serializable;
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

import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class PerfilControle implements Serializable {

    private Perfil perfil;
    private List<Perfil> perfisFiltrados;
    private DataModel<Perfil> listaPerfis;

    public PerfilControle() {
        carregaDados();
    }

    private void carregaDados() {
        try {
            perfisFiltrados = new PerfilDAOimp().todosPerfis();
            listaPerfis = new ListDataModel<Perfil>(perfisFiltrados);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void carregarPerfil(Perfil profile){
        try {
            perfil = new PerfilDAOimp().procurarPerfil(profile.getId());
        } catch (Exception ex) {
            Logger.getLogger(PerfilControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void excluirPerfil(Perfil profile) {

        FacesMessage msg = null;
        boolean sucess = false;

        try {
            new PerfilDAOimp().excluir(profile);
            carregaDados();
            sucess = true;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Excluido com sucesso!");
        } catch (Exception e) {
            sucess = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Excluir!", e.getMessage());
        }
        RequestContext context = RequestContext.getCurrentInstance();

        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("sucess", sucess);
    }
    
     public void prepararAdicionarPerfil(ActionEvent actionEvent) {
        perfil = new Perfil();
    }

    public void adicionarPerfil() {
        FacesMessage msg = null;
        boolean sucess = false;
        try {
            new PerfilDAOimp().salvar(perfil);
            carregaDados();
            sucess = true;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Registrado com sucesso!");
        } catch (Exception e) {
            sucess = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Cadastrar", e.getMessage());
        }
        RequestContext context = RequestContext.getCurrentInstance();

        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("sucess", sucess);
    }

    public DataModel<Perfil> getListaPerfis() {
        return listaPerfis;
    }

    public void setListaPerfis(DataModel<Perfil> listaPerfis) {
        this.listaPerfis = listaPerfis;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

}
