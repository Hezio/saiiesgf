/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.util;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author hezio
 */
public class EntityManagerFactoryPool implements Serializable{
    
    protected static EntityManagerFactory emf;
    protected static EntityManagerFactoryPool instance;

    private EntityManagerFactoryPool() {
        try {
            emf = Persistence.createEntityManagerFactory("SaiiesPU");
        } catch (Exception ex) {
            Logger.getLogger(EntityManagerFactoryPool.class.getSimpleName()).log(Level.SEVERE, null, ex);
        }
    }

    public static EntityManagerFactory getEmf() {
        return emf;
    }

    public static void setEmf(EntityManagerFactory emf) {
        EntityManagerFactoryPool.emf = emf;
    }

    public static EntityManagerFactoryPool getInstance() {
        if (instance == null) {
            instance = new EntityManagerFactoryPool();
        }
        return instance;
    }
    
}
