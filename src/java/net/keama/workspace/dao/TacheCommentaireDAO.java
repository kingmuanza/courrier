package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.TacheCommentaire;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class TacheCommentaireDAO {
    public boolean ajouter(TacheCommentaire tacheCommentaire) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(tacheCommentaire);
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
    
    public boolean modifier(TacheCommentaire tacheCommentaire) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(tacheCommentaire);
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
    
    public boolean supprimer (TacheCommentaire tacheCommentaire) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(tacheCommentaire);
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
    
    public TacheCommentaire get (int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        TacheCommentaire tacheCommentaire = (TacheCommentaire) session.get(TacheCommentaire.class, id);
        if(tacheCommentaire==null){
            throw new RuntimeException();
        }
        else{
            Hibernate.initialize(tacheCommentaire.getEmploye());
        }
        
        session.getTransaction().commit();
        session.close();
        
        return tacheCommentaire;
    }
    
    public List<TacheCommentaire> getall(){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<TacheCommentaire> tacheCommentaires = session.createCriteria(TacheCommentaire.class).list();
        for(TacheCommentaire tacheCommentaire : tacheCommentaires){
            Hibernate.initialize(tacheCommentaire.getEmploye());
        } 
        
        session.getTransaction().commit();
        session.close();
        
        return tacheCommentaires;
        
    }
}
