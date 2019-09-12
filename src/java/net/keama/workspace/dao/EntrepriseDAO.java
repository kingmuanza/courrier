package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.Entreprise;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class EntrepriseDAO {
    public boolean ajouter(Entreprise entreprise) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(entreprise);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            isGood = false;
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }
    
    public boolean modifier(Entreprise entreprise) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(entreprise);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            isGood = false;
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }
    
    public boolean supprimer (Entreprise entreprise) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(entreprise);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            isGood = false;
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }
    
    public Entreprise get (int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        Entreprise entreprise = (Entreprise) session.get(Entreprise.class, id);
        if(entreprise==null){
            throw new RuntimeException();
        }
        else{
            Hibernate.initialize(entreprise.getSites());
        }
        
        session.getTransaction().commit();
        session.close();
        
        return entreprise;
    }
    
    public List<Entreprise> getall(){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<Entreprise> entreprises = session.createCriteria(Entreprise.class).list();
        for(Entreprise entreprise : entreprises){
            Hibernate.initialize(entreprise.getSites());
        }
        
        session.getTransaction().commit();
        session.close();
        
        return entreprises;
        
    }
}
