package br.com.ies.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Professor extends Pessoa {

    private String cracha;
    private String formacao;
    @ManyToOne
    private Disciplina disciplina;
    @OneToMany(mappedBy = "professor")
    private List<FichaPessoa> fichasProfessor;
    @OneToOne(mappedBy = "professor")
    private Usuario usuario;

    public Professor() {
    }

    public Professor(Integer idPessoa, String nome, String CPF, String dtNascimento, Endereco endereco, String cracha, String formacao) {
        super(idPessoa, nome, CPF, dtNascimento, endereco);
        this.cracha = cracha;
        this.formacao = formacao;
    }

    public Professor(String nome, String CPF, String dtNascimento, Endereco endereco, String cracha, String formacao) {
        super(nome, CPF, dtNascimento, endereco);
        this.cracha = cracha;
        this.formacao = formacao;
    }

    public String getCracha() {
        return cracha;
    }

    public void setCracha(String cracha) {
        this.cracha = cracha;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public List<FichaPessoa> getFichasProfessor() {
        return fichasProfessor;
    }

    public void setFichasProfessor(List<FichaPessoa> fichasProfessor) {
        this.fichasProfessor = fichasProfessor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
}
