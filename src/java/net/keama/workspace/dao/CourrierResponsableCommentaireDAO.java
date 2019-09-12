package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.Courrier;
import net.keama.workspace.entity.CourrierResponsableCommentaire;
import net.keama.workspace.entity.CourrierResponsableCommentaireFichier;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class CourrierResponsableCommentaireDAO {
    public boolean ajouter(CourrierResponsableCommentaire courrierResponsableCommentaire) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(courrierResponsableCommentaire);
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
    
    public boolean modifier(CourrierResponsableCommentaire courrierResponsableCommentaire) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(courrierResponsableCommentaire);
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
    
    public boolean supprimer (CourrierResponsableCommentaire courrierResponsableCommentaire) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(courrierResponsableCommentaire);
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
    
    public CourrierResponsableCommentaire get (int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        CourrierResponsableCommentaire courrierResponsableCommentaire = (CourrierResponsableCommentaire) session.get(CourrierResponsableCommentaire.class, id);
        if(courrierResponsableCommentaire==null){
            throw new RuntimeException();
        }
        else{
            Hibernate.initialize(courrierResponsableCommentaire.getCourrierResponsable());
            Hibernate.initialize(courrierResponsableCommentaire.getCourrierResponsableCommentaireFichiers());
            for(CourrierResponsableCommentaireFichier crcf : courrierResponsableCommentaire.getCourrierResponsableCommentaireFichiers()){
                Hibernate.initialize(crcf);
            }
        }
        
        session.getTransaction().commit();
        session.close();
        
        return courrierResponsableCommentaire;
    }
    
    public List<CourrierResponsableCommentaire> getall(){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<CourrierResponsableCommentaire> courrierResponsableCommentaires = session.createCriteria(CourrierResponsableCommentaire.class).list();
        for(CourrierResponsableCommentaire courrierResponsableCommentaire : courrierResponsableCommentaires){
            Hibernate.initialize(courrierResponsableCommentaire.getCourrierResponsable());
            Hibernate.initialize(courrierResponsableCommentaire.getCourrierResponsableCommentaireFichiers());
            for(CourrierResponsableCommentaireFichier crcf : courrierResponsableCommentaire.getCourrierResponsableCommentaireFichiers()){
                Hibernate.initialize(crcf);
            }
        } 
        
        session.getTransaction().commit();
        session.close();
        
        return courrierResponsableCommentaires;
        
    }
    
    public List<CourrierResponsableCommentaire> getall(Courrier courrier){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<CourrierResponsableCommentaire> courrierResponsableCommentaires = session.createCriteria(CourrierResponsableCommentaire.class, "commentaire")
                .createAlias("commentaire.courrierResponsable", "lui")
                .createAlias("lui.courrier", "il")
                .add(Restrictions.eq("il.idcourrier", courrier.getIdcourrier()))
                .addOrder(Order.asc("dateEnregistrement"))
                .list();
        
        for(CourrierResponsableCommentaire courrierResponsableCommentaire : courrierResponsableCommentaires){
            Hibernate.initialize(courrierResponsableCommentaire.getCourrierResponsable().getEmploye());
            Hibernate.initialize(courrierResponsableCommentaire.getCourrierResponsableCommentaireFichiers());
            for(CourrierResponsableCommentaireFichier crcf : courrierResponsableCommentaire.getCourrierResponsableCommentaireFichiers()){
                Hibernate.initialize(crcf);
            }
        } 
        
        session.getTransaction().commit();
        session.close();
        
        return courrierResponsableCommentaires;
        
    }
}
