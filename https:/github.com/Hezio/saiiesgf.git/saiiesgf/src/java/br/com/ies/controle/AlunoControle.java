package br.com.ies.controle;

import java.br.com.ies.bean.Aluno;
import java.br.com.ies.bean.Cidade;
import java.br.com.ies.bean.Disciplina;
import java.br.com.ies.bean.Estado;
import java.br.com.ies.dao.impl.AlunoDAOimp;
import java.br.com.ies.dao.impl.DisciplinaDAOimp;
import java.br.com.ies.dao.impl.EnderecoDAOimp;
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
public class AlunoControle implements Serializable{

    private Aluno aluno;
    private Estado estado;
    private List<Aluno> alunosFiltrados;
    private DataModel<Aluno> listaAlunos;
    private List<Estado> listaEstados;
    private List<Disciplina> disciplinas;

    public AlunoControle() {
        carregaDados();
        aluno = new Aluno();
    }

    private void carregaDados() {
        try {
            alunosFiltrados = new AlunoDAOimp().listaAlunos();
            listaAlunos = new ListDataModel<Aluno>(alunosFiltrados);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    

    public void prepararAdicionarAluno(ActionEvent actionEvent) {
        aluno = new Aluno();
        disciplinas = new ArrayList<Disciplina>();
    }

    public void excluirAluno(Aluno student) {

        if (student != null) {
            FacesMessage msg = null;
            boolean sucess = false;
            try {
                new AlunoDAOimp().excluir(student);
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
    }

    public void adicionarAluno() throws Exception {
        FacesMessage msg = null;
        boolean sucess = false;
        try {
            aluno.setDisciplinas(disciplinas);
            new AlunoDAOimp().salvar(aluno);
            carregaDados();
            sucess = true;
            
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Registrado com sucesso!");
        } catch (Exception e) {
            sucess = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Registrar!", e.getMessage());
        }
        RequestContext context = RequestContext.getCurrentInstance();

        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("sucess", sucess);
    }

    public void carregarAluno(Aluno student){
        try {
            aluno = new AlunoDAOimp().procurar(student.getIdPessoa());
            disciplinas = new DisciplinaDAOimp().listaDisciplinasAluno(aluno.getIdPessoa());
            estado = aluno.getEndereco().getCidade().getEstado();
        } catch (Exception ex) {
            Logger.getLogger(AlunoControle.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public List<Estado> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(List<Estado> listaEstados) {
        this.listaEstados = listaEstados;
    }

    public List<Aluno> getAlunosFiltrados() {
        return alunosFiltrados;
    }

    public void setAlunosFiltrados(List<Aluno> alunosFiltrados) {
        this.alunosFiltrados = alunosFiltrados;
    }

    public DataModel<Aluno> getListaAlunos() {
        return listaAlunos;
    }

    public void setListaAlunos(DataModel<Aluno> listaAlunos) {
        this.listaAlunos = listaAlunos;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    
}
