package br.com.ies.dao;

import br.com.ies.bean.Cidade;
import java.util.List;

import br.com.ies.bean.Estado;

public interface EnderecoDAO {

    

    List<Estado> listaEstados() throws Exception;

    List<Cidade> listaCidades(Long idEstado) throws Exception;

    Cidade procurarCidade(Long id);

    Estado procurarEstado(Long id);
}
