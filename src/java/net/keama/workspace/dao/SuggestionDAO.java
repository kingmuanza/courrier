package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.Suggestion;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class SuggestionDAO {
    public boolean ajouter(Suggestion suggestion) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(suggestion);
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
    
    public boolean modifier(Suggestion suggestion) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(suggestion);
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
    
    public boolean supprimer (Suggestion suggestion) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(suggestion);
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
    
    public Suggestion get (int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        Suggestion suggestion = (Suggestion) session.get(Suggestion.class, id);
        if(suggestion==null){
            throw new RuntimeException();
        }
        else{
            Hibernate.initialize(suggestion.getEmploye());
        }
        
        session.getTransaction().commit();
        session.close();
        
        return suggestion;
    }
    
    public List<Suggestion> getall(){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<Suggestion> suggestions = session.createCriteria(Suggestion.class).list();
        for(Suggestion suggestion : suggestions){
            Hibernate.initialize(suggestion.getEmploye());
        } 
        
        session.getTransaction().commit();
        session.close();
        
        return suggestions;
        
    }
}
