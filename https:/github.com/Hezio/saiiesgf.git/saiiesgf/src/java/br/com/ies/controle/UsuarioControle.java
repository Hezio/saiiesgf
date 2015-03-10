package br.com.ies.controle;

import java.br.com.ies.bean.Aluno;
import java.br.com.ies.bean.Perfil;
import java.br.com.ies.bean.Pessoa;
import java.br.com.ies.bean.Professor;
import java.br.com.ies.bean.Usuario;
import java.br.com.ies.dao.impl.UsuarioDAOImp;
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
public class UsuarioControle implements Serializable{

    private Usuario usuario;
    private List<Usuario> usuariosFiltrados;
    private DataModel<Usuario> listaUsuarios;
    private String tipoPessoa;

    public UsuarioControle() {
        carregaDados();
        usuario = new Usuario();
        usuario.setPerfil(new Perfil());
    }

    private void carregaDados() {
        try {
            usuariosFiltrados = new UsuarioDAOImp().buscaTodos();
            listaUsuarios = new ListDataModel<Usuario>(usuariosFiltrados);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void prepararAdicionarUsuario(ActionEvent actionEvent) {
        usuario = new Usuario();
    }

    public void excluirUsuario(Usuario user) {
        FacesMessage msg = null;
        boolean sucess = false;
        try {
            new UsuarioDAOImp().excluir(user);
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

    public void carregarUsuario(Usuario user) {
        try {
            usuario = new UsuarioDAOImp().buscaUsuario(user.getId());
        } catch (Exception ex) {
            Logger.getLogger(UsuarioControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Aluno criarAluno(Usuario user, Aluno pessoa) {
        pessoa = new Aluno();
        pessoa.setNome(user.getNome());
        return pessoa;
    }

    private Professor criarProfessor(Usuario user, Professor pessoa) {
        pessoa = new Professor();
        pessoa.setNome(user.getNome());
        return pessoa;
    }

    public void adicionarUsuario(ActionEvent actionEvent) throws Exception {
        FacesMessage msg = null;
        boolean sucess = false;
        try {

            if (usuario.getId() == null) {
                Pessoa pessoa = null;
                if (tipoPessoa.equalsIgnoreCase("0")) {
                    pessoa = new Professor();
                    usuario.setProfessor(criarProfessor(usuario, (Professor)pessoa));
                } else {
                    pessoa = new Aluno();
                    usuario.setAluno(criarAluno(usuario, (Aluno)pessoa));
                }
            }


            new UsuarioDAOImp().salvar(usuario);
            carregaDados();
            sucess = true;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Registrado com sucesso!");
        } catch (Exception e) {
            sucess = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Cadastrar!", e.getMessage());
        }
        RequestContext context = RequestContext.getCurrentInstance();

        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("sucess", sucess);
    }

    public DataModel<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(DataModel<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }
}
