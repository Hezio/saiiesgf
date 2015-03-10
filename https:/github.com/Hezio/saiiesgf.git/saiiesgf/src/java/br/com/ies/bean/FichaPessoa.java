package br.com.ies.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class FichaPessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long idFichaPessoa;
    @ManyToOne
    private Professor professor;
    @ManyToOne
    private Aluno aluno;
    @ManyToOne
    private ModeloFicha modeloFicha;
    @OneToMany(mappedBy = "fichaPessoa", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<RespostaFicha> respostaFichas;

    public ModeloFicha getModeloFicha() {
        return modeloFicha;
    }

    public void setModeloFicha(ModeloFicha modeloFicha) {
        this.modeloFicha = modeloFicha;
    }

    public List<RespostaFicha> getRespostaFichas() {
        return respostaFichas;
    }

    public void setRespostaFichas(List<RespostaFicha> respostaFichas) {
        this.respostaFichas = respostaFichas;
    }

    public Long getIdFichaPessoa() {
        return idFichaPessoa;
    }

    public void setIdFichaPessoa(Long idFichaPessoa) {
        this.idFichaPessoa = idFichaPessoa;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.idFichaPessoa != null ? this.idFichaPessoa.hashCode() : 0);
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
        final FichaPessoa other = (FichaPessoa) obj;
        if (this.idFichaPessoa != other.idFichaPessoa && (this.idFichaPessoa == null || !this.idFichaPessoa.equals(other.idFichaPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FichaPessoa{" + "idFichaPessoa=" + idFichaPessoa + '}';
    }
}
