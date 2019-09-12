package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.ReunionEmploye;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class ReunionEmployeDAO {
    public boolean ajouter(ReunionEmploye reunionEmploye) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(reunionEmploye);
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
    
    public boolean modifier(ReunionEmploye reunionEmploye) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(reunionEmploye);
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
    
    public boolean supprimer (ReunionEmploye reunionEmploye) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(reunionEmploye);
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
    
    public ReunionEmploye get (int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        ReunionEmploye reunionEmploye = (ReunionEmploye) session.get(ReunionEmploye.class, id);
        if(reunionEmploye==null){
            throw new RuntimeException();
        }
        else{
            Hibernate.initialize(reunionEmploye.getEmploye());
            Hibernate.initialize(reunionEmploye.getReunion());
        }
        
        session.getTransaction().commit();
        session.close();
        
        return reunionEmploye;
    }
    
    public List<ReunionEmploye> getall(){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<ReunionEmploye> reunionEmployes = session.createCriteria(ReunionEmploye.class).list();
        for(ReunionEmploye reunionEmploye : reunionEmployes){
            Hibernate.initialize(reunionEmploye.getEmploye());
            Hibernate.initialize(reunionEmploye.getReunion());
        } 
        
        session.getTransaction().commit();
        session.close();
        
        return reunionEmployes;
        
    }
}
