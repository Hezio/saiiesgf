/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.view.util;

import java.br.com.ies.bean.Disciplina;
import java.br.com.ies.bean.Estado;
import java.br.com.ies.bean.Perfil;
import java.br.com.ies.bean.Professor;
import java.br.com.ies.controle.AlunoControle;
import java.br.com.ies.dao.impl.DisciplinaDAOimp;
import java.br.com.ies.dao.impl.EnderecoDAOimp;
import java.br.com.ies.dao.impl.PerfilDAOimp;
import java.br.com.ies.dao.impl.ProfessorDAOimp;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;


@ManagedBean
@ViewScoped
public class UtilMB implements Serializable{
    
    
    public synchronized List<SelectItem> getComboEstado() {
        List<SelectItem> items = new ArrayList<SelectItem>();
        try {
            List<Estado> lista = new EnderecoDAOimp().listaEstados();
            if (lista != null) {
                items.add(new SelectItem(null, ""));
                for (Estado estado : lista) {
                    items.add(new SelectItem(estado, estado.getNome_estado()));
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(AlunoControle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }
    
    public synchronized List<SelectItem> getComboPerfil() {
        List<SelectItem> items = new ArrayList<SelectItem>();
        try {
            List<Perfil> lista = new PerfilDAOimp().todosPerfis();
            if (lista != null) {
                items.add(new SelectItem(null, ""));
                for (Perfil estado : lista) {
                    items.add(new SelectItem(estado, estado.getNome()));
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(AlunoControle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }
    
    public synchronized List<SelectItem> getComboDisciplina() {
        List<SelectItem> items = new ArrayList<SelectItem>();
        try {
            List<Disciplina> lista = new DisciplinaDAOimp().listaDisciplinas();
            if (lista != null) {
                
                for (Disciplina estado : lista) {
                    items.add(new SelectItem(estado, estado.getNome_disciplina()));
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(AlunoControle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }
    
    public synchronized List<SelectItem> getComboProfessores() {
        List<SelectItem> items = new ArrayList<SelectItem>();
        try {
            List<Professor> lista = new ProfessorDAOimp().listaProfessor();
            if (lista != null) {
                
                for (Professor prof : lista) {
                    items.add(new SelectItem(prof, prof.getNome()));
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(AlunoControle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }
    
    
}
