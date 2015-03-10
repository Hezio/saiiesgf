package br.com.ies.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Aluno extends Pessoa {

    private String matricula;
    private String periodo;
    @ManyToMany
    private List<Disciplina> disciplinas;
    @OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY)
    private List<FichaPessoa> fichasAluno;
    @OneToOne(mappedBy = "aluno")
    private Usuario usuario;

    public Aluno() {
    }

    public Aluno(Integer idPessoa, String nome, String CPF, String dtNascimento, Endereco endereco, String matricula, String periodo) {
        super(idPessoa, nome, CPF, dtNascimento, endereco);
        this.matricula = matricula;
        this.periodo = periodo;
    }

    public Aluno(String nome, String CPF, String dtNascimento, Endereco endereco, String matricula, String periodo) {
        super(nome, CPF, dtNascimento, endereco);
        this.matricula = matricula;
        this.periodo = periodo;
    }

    public Aluno(Pessoa pessoa) {
        super(pessoa.getIdPessoa(), pessoa.getNome(), pessoa.getCPF(), pessoa.getDtNascimento(), pessoa.getEndereco());
        matricula = null;
        periodo = null;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public List<FichaPessoa> getFichasAluno() {
        return fichasAluno;
    }

    public void setFichasAluno(List<FichaPessoa> fichasAluno) {
        this.fichasAluno = fichasAluno;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
}
