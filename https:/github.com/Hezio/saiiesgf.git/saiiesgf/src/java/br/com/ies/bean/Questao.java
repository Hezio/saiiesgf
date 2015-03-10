package br.com.ies.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Questao implements Serializable {
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private int idQuestao;
    private String nomeQuestao;
    @ManyToMany(mappedBy = "questoes")
    private List<ModeloFicha> modelosFicha;
    @ManyToMany
    private List<ItemResposta> itensResposta;
    
    

    public List<ItemResposta> getItensResposta() {
        return itensResposta;
    }

    public void setItensResposta(List<ItemResposta> itensResposta) {
        this.itensResposta = itensResposta;
    }
    
    public List<ModeloFicha> getModelosFicha() {
        return modelosFicha;
    }

    public void setModelosFicha(List<ModeloFicha> modelosFicha) {
        this.modelosFicha = modelosFicha;
    }
    
    public int getIdQuestao() {
        return idQuestao;
    }

    public void setIdQuestao(int idQuestao) {
        this.idQuestao = idQuestao;
    }

    public String getNomeQuestao() {
        return nomeQuestao;
    }

    public void setNomeQuestao(String nomeQuestao) {
        this.nomeQuestao = nomeQuestao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.idQuestao;
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
        final Questao other = (Questao) obj;
        if (this.idQuestao != other.idQuestao) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Questao{" + "idQuestao=" + idQuestao + '}';
    }
    
    
}
