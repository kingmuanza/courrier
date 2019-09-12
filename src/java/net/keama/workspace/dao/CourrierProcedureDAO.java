package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.CourrierProcedure;
import net.keama.workspace.entity.CourrierProcedureTache;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class CourrierProcedureDAO {
    public boolean ajouter(CourrierProcedure courrierProcedure) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(courrierProcedure);
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
    
    public boolean modifier(CourrierProcedure courrierProcedure) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(courrierProcedure);
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
    
    public boolean supprimer (CourrierProcedure courrierProcedure) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(courrierProcedure);
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
    
    public CourrierProcedure get (int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        CourrierProcedure courrierProcedure = (CourrierProcedure) session.get(CourrierProcedure.class, id);
        if(courrierProcedure==null){
            throw new RuntimeException();
        }
        else{
            Hibernate.initialize(courrierProcedure.getCourrierProcedureTaches());
            for(CourrierProcedureTache cpt : courrierProcedure.getCourrierProcedureTaches()){
                Hibernate.initialize(cpt.getEmploye().getPoste());
            }
            Hibernate.initialize(courrierProcedure.getCourrierType());        
        }
        
        session.getTransaction().commit();
        session.close();
        
        return courrierProcedure;
    }
    
    public List<CourrierProcedure> getall(){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<CourrierProcedure> courrierProcedures = session.createCriteria(CourrierProcedure.class).list();
        for(CourrierProcedure courrierProcedure : courrierProcedures){
            Hibernate.initialize(courrierProcedure.getCourrierProcedureTaches());
            for(CourrierProcedureTache cpt : courrierProcedure.getCourrierProcedureTaches()){
                Hibernate.initialize(cpt.getEmploye().getPoste());
            }
            Hibernate.initialize(courrierProcedure.getCourrierType());     
        }
        
        session.getTransaction().commit();
        session.close();
        
        return courrierProcedures;
        
    }
}
