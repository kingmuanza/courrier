package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.Correspondant;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class CorrespondantDAO {
    public boolean ajouter(Correspondant correspondant) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(correspondant);
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
    
    public boolean modifier(Correspondant correspondant) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(correspondant);
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
    
    public boolean supprimer (Correspondant correspondant) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(correspondant);
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
    
    public Correspondant get (int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        Correspondant correspondant = (Correspondant) session.get(Correspondant.class, id);
        if(correspondant==null){
            throw new RuntimeException();
        }
        else{
            Hibernate.initialize(correspondant.getEntreprisePartenaire());
        }
        
        session.getTransaction().commit();
        session.close();
        
        return correspondant;
    }
    
    public List<Correspondant> getall(){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<Correspondant> correspondants = session.createCriteria(Correspondant.class).list();
        for(Correspondant correspondant : correspondants){
            Hibernate.initialize(correspondant.getEntreprisePartenaire());
        }
        
        session.getTransaction().commit();
        session.close();
        
        return correspondants;
        
    }
}
