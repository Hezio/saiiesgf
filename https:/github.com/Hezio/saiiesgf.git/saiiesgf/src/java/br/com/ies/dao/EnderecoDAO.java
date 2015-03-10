package br.com.ies.dao;

import java.br.com.ies.bean.Cidade;
import java.br.com.ies.bean.Estado;
import java.util.List;

public interface EnderecoDAO {

    

    List<Estado> listaEstados() throws Exception;

    List<Cidade> listaCidades(Long idEstado) throws Exception;

    Cidade procurarCidade(Long id);

    Estado procurarEstado(Long id);
}
