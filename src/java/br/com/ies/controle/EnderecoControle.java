package br.com.ies.controle;

import br.com.ies.bean.Estado;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.ies.dao.impl.EnderecoDAOimp;
import java.util.List;

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
