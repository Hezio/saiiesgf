/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.view.converter;

import java.br.com.ies.bean.Cidade;
import java.br.com.ies.dao.impl.EnderecoDAOimp;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "CidadeConverter")
public class CidadeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Long id = Long.parseLong(value.replaceAll("\\D", ""));
            Cidade cidade = new EnderecoDAOimp().procurarCidade(id);
            return cidade;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(value);
    } 
}
