package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.TacheEmploye;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class TacheEmployeDAO {
    public boolean ajouter(TacheEmploye tacheEmploye) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(tacheEmploye);
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
    
    public boolean modifier(TacheEmploye tacheEmploye) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(tacheEmploye);
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
    
    public boolean supprimer (TacheEmploye tacheEmploye) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(tacheEmploye);
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
    
    public TacheEmploye get (int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        TacheEmploye tacheEmploye = (TacheEmploye) session.get(TacheEmploye.class, id);
        if(tacheEmploye==null){
            throw new RuntimeException();
        }
        else{
            Hibernate.initialize(tacheEmploye.getEmploye());
            Hibernate.initialize(tacheEmploye.getTache().getEmployeByIdordonnateur());
        }
        
        session.getTransaction().commit();
        session.close();
        
        return tacheEmploye;
    }
    
    public List<TacheEmploye> getall(){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<TacheEmploye> tacheEmployes = session.createCriteria(TacheEmploye.class).list();
        for(TacheEmploye tacheEmploye : tacheEmployes){
            Hibernate.initialize(tacheEmploye.getEmploye());
            Hibernate.initialize(tacheEmploye.getTache().getEmployeByIdordonnateur());
        } 
        
        session.getTransaction().commit();
        session.close();
        
        return tacheEmployes;
        
    }
}
