package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.CourrierType;
import net.keama.workspace.entity.Reunion;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class CourrierTypeDAO {
    public boolean ajouter(CourrierType courrierType) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(courrierType);
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
    
    public boolean modifier(CourrierType courrierType) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(courrierType);
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
    
    public boolean supprimer (CourrierType courrierType) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(courrierType);
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
    
    public CourrierType get (int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        CourrierType courrierType = (CourrierType) session.get(CourrierType.class, id);
        if(courrierType==null){
            throw new RuntimeException();
        }
        else{
            Hibernate.initialize(courrierType.getCourrierProcedures());
        }
        
        session.getTransaction().commit();
        session.close();
        
        return courrierType;
    }
    
    public CourrierType get (String nom) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        CourrierType courrierType = (CourrierType) session.createCriteria(CourrierType.class)
                .add(Restrictions.eq("nom", nom))
                .list().get(0);
        
        if(courrierType==null){
            return null ;
        }
        else{
            Hibernate.initialize(courrierType.getCourrierProcedures());
        }
        
        session.getTransaction().commit();
        session.close();
        
        return courrierType;
    }
    
    public List<CourrierType> getall(){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<CourrierType> courrierTypes = session.createCriteria(CourrierType.class).list();
        for(CourrierType courrierType : courrierTypes){
            Hibernate.initialize(courrierType.getCourrierProcedures());
        }
        
        session.getTransaction().commit();
        session.close();
        
        return courrierTypes;
        
    }
}
