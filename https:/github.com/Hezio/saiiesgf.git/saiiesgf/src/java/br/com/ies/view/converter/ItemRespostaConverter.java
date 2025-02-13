/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.view.converter;

import java.br.com.ies.bean.ItemResposta;
import java.br.com.ies.dao.impl.FichaPessoaDAOImp;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "ItemRespostaConverter")
public class ItemRespostaConverter implements Converter{
    
     @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Long id = Long.parseLong(value.replaceAll("\\D", ""));
            ItemResposta item = new FichaPessoaDAOImp().findItemResposta(id);
            return item;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(value);
    }  
    
    
}
