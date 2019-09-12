
package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.InformationGeneraleDocument;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class InformationGeneraleDocumentDAO {
    public boolean ajouter(InformationGeneraleDocument informationGeneraleDocument) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(informationGeneraleDocument);
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
    
    public boolean modifier(InformationGeneraleDocument informationGeneraleDocument) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(informationGeneraleDocument);
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
    
    public boolean supprimer (InformationGeneraleDocument informationGeneraleDocument) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(informationGeneraleDocument);
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
    
    public InformationGeneraleDocument get (int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        InformationGeneraleDocument informationGeneraleDocument = (InformationGeneraleDocument) session.get(InformationGeneraleDocument.class, id);
        if(informationGeneraleDocument==null){
            throw new RuntimeException();
        }
        else{
            
        }
        
        session.getTransaction().commit();
        session.close();
        
        return informationGeneraleDocument;
    }
    
    public List<InformationGeneraleDocument> getall(){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<InformationGeneraleDocument> informationGeneraleDocuments = session.createCriteria(InformationGeneraleDocument.class).list();
        
        session.getTransaction().commit();
        session.close();
        
        return informationGeneraleDocuments;
        
    }
}
