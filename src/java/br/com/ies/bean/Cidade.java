package br.com.ies.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Cidade implements Serializable {

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long cod_cidade;
    private String nome_cidade;
    @ManyToOne
    private Estado estado;

    public Cidade() {
        // TODO Auto-generated constructor stub
    }

    public Cidade(Long cod_cidade, String nome_cidade) {
        this.cod_cidade = cod_cidade;
        this.nome_cidade = nome_cidade;
    }

    public Long getCod_cidade() {
        return cod_cidade;
    }

    public void setCod_cidade(Long cod_cidade) {
        this.cod_cidade = cod_cidade;
    }

    public String getNome_cidade() {
        return nome_cidade;
    }

    public void setNome_cidade(String nome_cidade) {
        this.nome_cidade = nome_cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (this.cod_cidade != null ? this.cod_cidade.hashCode() : 0);
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
        final Cidade other = (Cidade) obj;
        if (this.cod_cidade != other.cod_cidade && (this.cod_cidade == null || !this.cod_cidade.equals(other.cod_cidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cidade{" + "cod_cidade=" + cod_cidade + '}';
    }
    
    
}
