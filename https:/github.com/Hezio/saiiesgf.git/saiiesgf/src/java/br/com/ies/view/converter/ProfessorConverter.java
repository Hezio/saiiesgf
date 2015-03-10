/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.view.converter;

import java.br.com.ies.bean.Professor;
import java.br.com.ies.dao.impl.ProfessorDAOimp;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "ProfessorConverter")
public class ProfessorConverter implements Converter{
    
      @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
         try {
            Integer id = Integer.parseInt(value.replaceAll("\\D", ""));
            Professor prof = new ProfessorDAOimp().procurarProfesor(id);
            return prof;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
       return String.valueOf(value);
    }
    
    
}
