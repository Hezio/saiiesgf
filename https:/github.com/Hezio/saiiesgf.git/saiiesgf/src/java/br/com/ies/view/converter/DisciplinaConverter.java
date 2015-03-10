/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.view.converter;

import java.br.com.ies.bean.Disciplina;
import java.br.com.ies.dao.impl.DisciplinaDAOimp;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "DisciplinaConverter")
public class DisciplinaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Long id = Long.parseLong(value.replaceAll("\\D", ""));
            Disciplina disciplina = new DisciplinaDAOimp().procurarDisciplina(id);
            return disciplina;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(value);
    }     
}
