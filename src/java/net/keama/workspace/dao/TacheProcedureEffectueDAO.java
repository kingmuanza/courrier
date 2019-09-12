package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.TacheProcedureEffectue;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class TacheProcedureEffectueDAO {
    public boolean ajouter(TacheProcedureEffectue tacheProcedureEffectue) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(tacheProcedureEffectue);
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
    
    public boolean modifier(TacheProcedureEffectue tacheProcedureEffectue) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(tacheProcedureEffectue);
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
    
    public boolean supprimer (TacheProcedureEffectue tacheProcedureEffectue) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(tacheProcedureEffectue);
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
    
    public TacheProcedureEffectue get (int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        TacheProcedureEffectue tacheProcedureEffectue = (TacheProcedureEffectue) session.get(TacheProcedureEffectue.class, id);
        if(tacheProcedureEffectue==null){
            throw new RuntimeException();
        }
        else{
            Hibernate.initialize(tacheProcedureEffectue.getProcedureEffective());
            Hibernate.initialize(tacheProcedureEffectue.getTacheProcedure().getEmploye());
        }
        
        session.getTransaction().commit();
        session.close();
        
        return tacheProcedureEffectue;
    }
    
    public List<TacheProcedureEffectue> getall(){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<TacheProcedureEffectue> tacheProcedureEffectues = session.createCriteria(TacheProcedureEffectue.class).list();
        for(TacheProcedureEffectue tacheProcedureEffectue : tacheProcedureEffectues){
            Hibernate.initialize(tacheProcedureEffectue.getProcedureEffective());
            Hibernate.initialize(tacheProcedureEffectue.getTacheProcedure().getEmploye());
        } 
        
        session.getTransaction().commit();
        session.close();
        
        return tacheProcedureEffectues;
        
    }
}
