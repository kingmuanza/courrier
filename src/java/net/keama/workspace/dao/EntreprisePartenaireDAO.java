package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.Correspondant;
import net.keama.workspace.entity.EntreprisePartenaire;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class EntreprisePartenaireDAO {
    public boolean ajouter(EntreprisePartenaire entreprisePartenaire) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(entreprisePartenaire);
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
    
    public boolean modifier(EntreprisePartenaire entreprisePartenaire) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(entreprisePartenaire);
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
    
    public boolean supprimer (EntreprisePartenaire entreprisePartenaire) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(entreprisePartenaire);
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
    
    public EntreprisePartenaire get (int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        EntreprisePartenaire entreprisePartenaire = (EntreprisePartenaire) session.get(EntreprisePartenaire.class, id);
        if(entreprisePartenaire==null){
            throw new RuntimeException();
        }
        else{
            Hibernate.initialize(entreprisePartenaire.getCorrespondants());
            if(entreprisePartenaire.getCorrespondants()!=null){
                for(Correspondant c : entreprisePartenaire.getCorrespondants()){
                    Hibernate.initialize(c);
                    if(c!=null){
                        Hibernate.initialize(c.getCourriers());
                    }
                }
            }
        }
        
        session.getTransaction().commit();
        session.close();
        
        return entreprisePartenaire;
    }
    
    public List<EntreprisePartenaire> getall(){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<EntreprisePartenaire> entreprisePartenaires = session.createCriteria(EntreprisePartenaire.class).list();
        for(EntreprisePartenaire entreprisePartenaire : entreprisePartenaires){
            Hibernate.initialize(entreprisePartenaire.getCorrespondants());
            if(entreprisePartenaire.getCorrespondants()!=null){
                for(Correspondant c : entreprisePartenaire.getCorrespondants()){
                    Hibernate.initialize(c);
                    if(c!=null){
                        Hibernate.initialize(c.getCourriers());
                    }
                }
            }
        }
        
        session.getTransaction().commit();
        session.close();
        
        return entreprisePartenaires;
        
    }
}
