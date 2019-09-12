
package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.ProfilUtilisateur;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class ProfilUtilisateurDAO {
    public boolean ajouter(ProfilUtilisateur profilUtilisateur) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(profilUtilisateur);
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
    
    public boolean modifier(ProfilUtilisateur profilUtilisateur) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(profilUtilisateur);
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
    
    public boolean supprimer (ProfilUtilisateur profilUtilisateur) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(profilUtilisateur);
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
    
    public ProfilUtilisateur get (int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        ProfilUtilisateur profilUtilisateur = (ProfilUtilisateur) session.get(ProfilUtilisateur.class, id);
        if(profilUtilisateur==null){
            throw new RuntimeException();
        }
        else{
            
        }
        
        session.getTransaction().commit();
        session.close();
        
        return profilUtilisateur;
    }
    
    public List<ProfilUtilisateur> getall(){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<ProfilUtilisateur> profilUtilisateurs = session.createCriteria(ProfilUtilisateur.class).list();
        
        session.getTransaction().commit();
        session.close();
        
        return profilUtilisateurs;
        
    }
}
