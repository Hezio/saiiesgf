/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.view.converter;

import br.com.ies.bean.Perfil;
import br.com.ies.dao.impl.PerfilDAOimp;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "PerfilConverter")
public class PerfilConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
         try {
            Integer id = Integer.parseInt(value.replaceAll("\\D", ""));
            Perfil estado = new PerfilDAOimp().procurarPerfil(id);
            return estado;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
       return String.valueOf(value);
    }
    
}
