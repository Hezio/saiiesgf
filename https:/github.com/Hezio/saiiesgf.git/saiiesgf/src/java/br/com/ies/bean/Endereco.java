package br.com.ies.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Integer id;
    private String rua;
    private String complemento;
    private String bairro;
    @ManyToOne
    private Cidade cidade;

    public Endereco() {
    }

    public Endereco(Integer id, String rua, String complemento, String bairro) {
        this.id = id;
        this.rua = rua;
        this.complemento = complemento;
        this.bairro = bairro;
    }

    public Endereco(String rua, String complemento, String bairro) {
        this.id = null;
        this.rua = rua;
        this.complemento = complemento;
        this.bairro = bairro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public boolean equals(Object obj) {
        Endereco outro = (Endereco) obj;
        boolean igual = true;
        igual &= getRua().equals(outro.getRua());
        igual &= getBairro().equals(outro.getBairro());
        igual &= getComplemento().equals(outro.getComplemento());
        return igual;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Endereco{" + "id=" + id + '}';
    }
    
    
}
