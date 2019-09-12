
package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.SalleReunion;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class SalleReunionDAO {
    public boolean ajouter(SalleReunion salleReunion) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(salleReunion);
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
    
    public boolean modifier(SalleReunion salleReunion) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(salleReunion);
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
    
    public boolean supprimer (SalleReunion salleReunion) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(salleReunion);
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
    
    public SalleReunion get (int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        SalleReunion salleReunion = (SalleReunion) session.get(SalleReunion.class, id);
        if(salleReunion==null){
            throw new RuntimeException();
        }
        else{
            
        }
        
        session.getTransaction().commit();
        session.close();
        
        return salleReunion;
    }
    
    public List<SalleReunion> getall(){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<SalleReunion> salleReunions = session.createCriteria(SalleReunion.class).list();
        
        session.getTransaction().commit();
        session.close();
        
        return salleReunions;
        
    }
}
