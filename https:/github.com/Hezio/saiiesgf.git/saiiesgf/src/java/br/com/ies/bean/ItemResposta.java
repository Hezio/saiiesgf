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
public class ItemResposta implements Serializable{
    

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long idItemResposta;
    private String nomeItemResposta;
    @ManyToMany(mappedBy = "itensResposta")
    private List<Questao> questoes;

    
    
    public List<Questao> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<Questao> questoes) {
        this.questoes = questoes;
    }
    
    public String getNomeItemResposta() {
        return nomeItemResposta;
    }

    public void setNomeItemResposta(String nomeItemResposta) {
        this.nomeItemResposta = nomeItemResposta;
    }

    public Long getIdItemResposta() {
        return idItemResposta;
    }

    public void setIdItemResposta(Long idItemResposta) {
        this.idItemResposta = idItemResposta;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.idItemResposta != null ? this.idItemResposta.hashCode() : 0);
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
        final ItemResposta other = (ItemResposta) obj;
        if (this.idItemResposta != other.idItemResposta && (this.idItemResposta == null || !this.idItemResposta.equals(other.idItemResposta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ItemResposta{" + "idItemResposta=" + idItemResposta + '}';
    }
    
    
}
