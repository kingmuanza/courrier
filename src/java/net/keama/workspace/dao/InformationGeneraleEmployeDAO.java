
package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.InformationGeneraleEmploye;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class InformationGeneraleEmployeDAO {
    public boolean ajouter(InformationGeneraleEmploye informationGeneraleEmploye) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(informationGeneraleEmploye);
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
    
    public boolean modifier(InformationGeneraleEmploye informationGeneraleEmploye) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(informationGeneraleEmploye);
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
    
    public boolean supprimer (InformationGeneraleEmploye informationGeneraleEmploye) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(informationGeneraleEmploye);
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
    
    public InformationGeneraleEmploye get (int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        InformationGeneraleEmploye informationGeneraleEmploye = (InformationGeneraleEmploye) session.get(InformationGeneraleEmploye.class, id);
        if(informationGeneraleEmploye==null){
            throw new RuntimeException();
        }
        else{
            
        }
        
        session.getTransaction().commit();
        session.close();
        
        return informationGeneraleEmploye;
    }
    
    public List<InformationGeneraleEmploye> getall(){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<InformationGeneraleEmploye> informationGeneraleEmployes = session.createCriteria(InformationGeneraleEmploye.class).list();
        
        session.getTransaction().commit();
        session.close();
        
        return informationGeneraleEmployes;
        
    }
}
