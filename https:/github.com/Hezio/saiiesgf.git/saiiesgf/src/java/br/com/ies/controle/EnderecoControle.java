package br.com.ies.controle;

import java.br.com.ies.bean.Estado;
import java.br.com.ies.dao.impl.EnderecoDAOimp;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class EnderecoControle implements Serializable {

   
    private List<Estado> listaEstados;

    public EnderecoControle() {
        carregaDados();
    }

    private void carregaDados() {
        try {

            listaEstados = new EnderecoDAOimp().listaEstados();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
     public List<Estado> getListaPerfisSelect() {
        return listaEstados;
    }
}
