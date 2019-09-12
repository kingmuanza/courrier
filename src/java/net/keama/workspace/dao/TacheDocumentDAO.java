package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.TacheDocument;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class TacheDocumentDAO {
    public boolean ajouter(TacheDocument tacheDocument) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(tacheDocument);
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
    
    public boolean modifier(TacheDocument tacheDocument) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(tacheDocument);
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
    
    public boolean supprimer (TacheDocument tacheDocument) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(tacheDocument);
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
    
    public TacheDocument get (int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        TacheDocument tacheDocument = (TacheDocument) session.get(TacheDocument.class, id);
        if(tacheDocument==null){
            throw new RuntimeException();
        }
        else{
            Hibernate.initialize(tacheDocument.getEmploye());
        }
        
        session.getTransaction().commit();
        session.close();
        
        return tacheDocument;
    }
    
    public List<TacheDocument> getall(){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<TacheDocument> tacheDocuments = session.createCriteria(TacheDocument.class).list();
        for(TacheDocument tacheDocument : tacheDocuments){
            Hibernate.initialize(tacheDocument.getEmploye());
        } 
        
        session.getTransaction().commit();
        session.close();
        
        return tacheDocuments;
        
    }
}
