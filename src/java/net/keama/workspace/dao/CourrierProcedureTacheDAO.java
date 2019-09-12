package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.CourrierProcedureTache;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class CourrierProcedureTacheDAO {
    public boolean ajouter(CourrierProcedureTache courrierProcedureTache) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(courrierProcedureTache);
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
    
    public boolean modifier(CourrierProcedureTache courrierProcedureTache) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(courrierProcedureTache);
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
    
    public boolean supprimer (CourrierProcedureTache courrierProcedureTache) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(courrierProcedureTache);
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
    
    public CourrierProcedureTache get (int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        CourrierProcedureTache courrierProcedureTache = (CourrierProcedureTache) session.get(CourrierProcedureTache.class, id);
        if(courrierProcedureTache==null){
            throw new RuntimeException();
        }
        else{
            
        }
        
        session.getTransaction().commit();
        session.close();
        
        return courrierProcedureTache;
    }
    
    public List<CourrierProcedureTache> getall(){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<CourrierProcedureTache> courrierProcedureTaches = session.createCriteria(CourrierProcedureTache.class).list();
        
        session.getTransaction().commit();
        session.close();
        
        return courrierProcedureTaches;
        
    }
}
