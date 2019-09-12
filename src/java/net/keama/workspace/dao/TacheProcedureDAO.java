package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.TacheProcedure;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class TacheProcedureDAO {
    public boolean ajouter(TacheProcedure tacheProcedure) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(tacheProcedure);
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
    
    public boolean modifier(TacheProcedure tacheProcedure) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(tacheProcedure);
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
    
    public boolean supprimer (TacheProcedure tacheProcedure) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(tacheProcedure);
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
    
    public TacheProcedure get (int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        TacheProcedure tacheProcedure = (TacheProcedure) session.get(TacheProcedure.class, id);
        if(tacheProcedure==null){
            throw new RuntimeException();
        }
        else{
            Hibernate.initialize(tacheProcedure.getTacheProcedureEffectues());
            
        }
            
        
        session.getTransaction().commit();
        session.close();
        
        return tacheProcedure;
    }
    
    public List<TacheProcedure> getall(){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<TacheProcedure> tacheProcedures = session.createCriteria(TacheProcedure.class).list();
        for(TacheProcedure tacheProcedure : tacheProcedures){
            Hibernate.initialize(tacheProcedure.getTacheProcedureEffectues());
        } 
        
        session.getTransaction().commit();
        session.close();
        
        return tacheProcedures;
        
    }
}
