package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.Courrier;
import net.keama.workspace.entity.CourrierResponsableCommentaireFichier;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class CourrierResponsableCommentaireFichierDAO {
    public boolean ajouter(CourrierResponsableCommentaireFichier sourrierResponsableCommentaireFichier) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(sourrierResponsableCommentaireFichier);
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
    
    public boolean modifier(CourrierResponsableCommentaireFichier sourrierResponsableCommentaireFichier) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(sourrierResponsableCommentaireFichier);
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
    
    public boolean supprimer (CourrierResponsableCommentaireFichier sourrierResponsableCommentaireFichier) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(sourrierResponsableCommentaireFichier);
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
    
    public CourrierResponsableCommentaireFichier get (int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        CourrierResponsableCommentaireFichier sourrierResponsableCommentaireFichier = (CourrierResponsableCommentaireFichier) session.get(CourrierResponsableCommentaireFichier.class, id);
        if(sourrierResponsableCommentaireFichier==null){
            throw new RuntimeException();
        }
        else{
            Hibernate.initialize(sourrierResponsableCommentaireFichier.getCourrierResponsableCommentaire());
        }
        
        session.getTransaction().commit();
        session.close();
        
        return sourrierResponsableCommentaireFichier;
    }
    
    public List<CourrierResponsableCommentaireFichier> getall(){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<CourrierResponsableCommentaireFichier> sourrierResponsableCommentaireFichiers = session.createCriteria(CourrierResponsableCommentaireFichier.class).list();
        for(CourrierResponsableCommentaireFichier sourrierResponsableCommentaireFichier : sourrierResponsableCommentaireFichiers){
            Hibernate.initialize(sourrierResponsableCommentaireFichier.getCourrierResponsableCommentaire());
        } 
        
        session.getTransaction().commit();
        session.close();
        
        return sourrierResponsableCommentaireFichiers;
        
    }
    
    public List<CourrierResponsableCommentaireFichier> getall(Courrier courrier){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<CourrierResponsableCommentaireFichier> sourrierResponsableCommentaireFichiers = session.createCriteria(CourrierResponsableCommentaireFichier.class).list();
                
        
        for(CourrierResponsableCommentaireFichier sourrierResponsableCommentaireFichier : sourrierResponsableCommentaireFichiers){
            Hibernate.initialize(sourrierResponsableCommentaireFichier.getCourrierResponsableCommentaire());
        } 
        
        session.getTransaction().commit();
        session.close();
        
        return sourrierResponsableCommentaireFichiers;
        
    }
}
