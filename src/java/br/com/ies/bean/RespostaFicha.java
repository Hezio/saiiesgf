package br.com.ies.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RespostaFicha implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long idRespostaFicha;
    @ManyToOne
    private FichaPessoa fichaPessoa;
    @ManyToOne
    private Questao questao;
    @ManyToOne
    private ItemResposta itemResposta;

    
    
    public Questao getQuestao() {
        return questao;
    }

    public void setQuestao(Questao questao) {
        this.questao = questao;
    }

    public ItemResposta getItemResposta() {
        return itemResposta;
    }

    public void setItemResposta(ItemResposta itemResposta) {
        this.itemResposta = itemResposta;
    }

    public Long getIdRespostaFicha() {
        return idRespostaFicha;
    }

    public void setIdRespostaFicha(Long idRespostaFicha) {
        this.idRespostaFicha = idRespostaFicha;
    }

    

    public FichaPessoa getFichaPessoa() {
        return fichaPessoa;
    }

    public void setFichaPessoa(FichaPessoa fichaPessoa) {
        this.fichaPessoa = fichaPessoa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.idRespostaFicha != null ? this.idRespostaFicha.hashCode() : 0);
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
        final RespostaFicha other = (RespostaFicha) obj;
        if (this.idRespostaFicha != other.idRespostaFicha && (this.idRespostaFicha == null || !this.idRespostaFicha.equals(other.idRespostaFicha))) {
            return false;
        }
        return true;
    }

   

    @Override
    public String toString() {
        return "RespostaFicha{" + "idRespostaFicha=" + idRespostaFicha + '}';
    }
}
