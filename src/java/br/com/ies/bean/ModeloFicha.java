package br.com.ies.bean;

import br.com.ies.enuns.TipoModeloFicha;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class ModeloFicha implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long idModeloFicha;
    private TipoModeloFicha tipoModeloFicha;
    @ManyToMany
    private List<Questao> questoes;
    

    public TipoModeloFicha getTipoModeloFicha() {
        return tipoModeloFicha;
    }

    public void setTipoModeloFicha(TipoModeloFicha tipoModeloFicha) {
        this.tipoModeloFicha = tipoModeloFicha;
    }

    public Long getIdModeloFicha() {
        return idModeloFicha;
    }

    public void setIdModeloFicha(Long idModeloFicha) {
        this.idModeloFicha = idModeloFicha;
    }

    public List<Questao> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<Questao> questoes) {
        this.questoes = questoes;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.idModeloFicha != null ? this.idModeloFicha.hashCode() : 0);
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
        final ModeloFicha other = (ModeloFicha) obj;
        if (this.idModeloFicha != other.idModeloFicha && (this.idModeloFicha == null || !this.idModeloFicha.equals(other.idModeloFicha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ModeloFicha{" + "idModeloFicha=" + idModeloFicha + '}';
    }
    
    
    
}
