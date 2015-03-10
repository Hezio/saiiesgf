package br.com.ies.bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
@Entity
public class Disciplina implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long id_disciplina;
    private String nome_disciplina;
    @ManyToMany(mappedBy = "disciplinas")
    private List<Aluno> alunos;
    @OneToMany(mappedBy = "disciplina", fetch = FetchType.LAZY)
    private List<Professor> professores;

    public Disciplina() {
        // TODO Auto-generated constructor stub
    }

    public Disciplina(long id_disciplina, String nome_disciplina) {
        super();
        this.id_disciplina = id_disciplina;
        this.nome_disciplina = nome_disciplina;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public String getNome_disciplina() {
        return nome_disciplina;
    }

    public void setNome_disciplina(String nome_disciplina) {
        this.nome_disciplina = nome_disciplina;
    }

    public Long getId_disciplina() {
        return id_disciplina;
    }

    public void setId_disciplina(Long id_disciplina) {
        this.id_disciplina = id_disciplina;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.id_disciplina != null ? this.id_disciplina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Disciplina other = (Disciplina) obj;
        if (this.id_disciplina != other.id_disciplina && (this.id_disciplina == null || !this.id_disciplina.equals(other.id_disciplina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Disciplina{" + "id_disciplina=" + id_disciplina + '}';
    }
    
    
}
