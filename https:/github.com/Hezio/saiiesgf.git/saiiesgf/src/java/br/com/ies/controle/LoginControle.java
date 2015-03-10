package br.com.ies.controle;

import java.br.com.ies.bean.Usuario;
import java.br.com.ies.dao.impl.UsuarioDAOImp;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class LoginControle implements Serializable {

    private boolean administrador;
    private boolean professor;
    private boolean aluno;
    private Usuario usuario = new Usuario();

    public String logar() {
        try {
            usuario = new UsuarioDAOImp().loga(usuario);
            usuario.setLogado(true);
            verificaPerfil();
            return "sucesso";
        } catch (Exception ex) {
            Logger.getLogger(UsuarioControle.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ex.getMessage()));
            return "login";
        }
    }

    public void sair() {
        try {
            usuario = new Usuario();
            String url = null;
            FacesContext context = FacesContext.getCurrentInstance();
            url = context.getExternalContext().getRequestContextPath() +"/login.xhtml";
            context.getExternalContext().redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(LoginControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void protegeLogin() {
        if (usuario == null || usuario.getLogado() == false) {
            try {
                String url = null;
                FacesContext context = FacesContext.getCurrentInstance();
                url = context.getExternalContext().getRequestContextPath() + "/login.xhtml";
                context.getExternalContext().redirect(url);
            } catch (IOException ex) {
                Logger.getLogger(UsuarioControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void verificaPerfil() {
        if (usuario.getPerfil().getNome().equalsIgnoreCase("administrador")) {
            administrador = true;
            professor = true;
            aluno = true;
        } else if (usuario.getPerfil().getNome().equalsIgnoreCase("professor")) {
            administrador = false;
            professor = true;
            aluno = false;
        } else if (usuario.getPerfil().getNome().equalsIgnoreCase("aluno")) {
            administrador = false;
            professor = false;
            aluno = true;
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public boolean isProfessor() {
        return professor;
    }

    public void setProfessor(boolean professor) {
        this.professor = professor;
    }

    public boolean isAluno() {
        return aluno;
    }

    public void setAluno(boolean aluno) {
        this.aluno = aluno;
    }
}
