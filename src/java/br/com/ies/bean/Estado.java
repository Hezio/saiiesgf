package br.com.ies.bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Estado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long cod_estado;
    private String sigla_estado;
    private String nome_estado;
    @OneToMany(mappedBy = "estado", fetch = FetchType.LAZY)
    private List<Cidade> cidades;

    public Estado() {
        // TODO Auto-generated constructor stub
    }

    public Estado(Long cod_estado, String sigla_estado, String nome_estado) {
        this.cod_estado = cod_estado;
        this.sigla_estado = sigla_estado;
        this.nome_estado = nome_estado;
    }

    public Long getCod_estado() {
        return cod_estado;
    }

    public void setCod_estado(Long cod_estado) {
        this.cod_estado = cod_estado;
    }

    public String getSigla_estado() {
        return sigla_estado;
    }

    public void setSigla_estado(String sigla_estado) {
        this.sigla_estado = sigla_estado;
    }

    public String getNome_estado() {
        return nome_estado;
    }

    public void setNome_estado(String nom_estado) {
        this.nome_estado = nom_estado;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + (this.cod_estado != null ? this.cod_estado.hashCode() : 0);
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
        final Estado other = (Estado) obj;
        if (this.cod_estado != other.cod_estado && (this.cod_estado == null || !this.cod_estado.equals(other.cod_estado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Estado{" + "cod_estado=" + cod_estado + '}';
    }
    
    
}
