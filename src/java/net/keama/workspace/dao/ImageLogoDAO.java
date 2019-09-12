package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.ImageLogo;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class ImageLogoDAO {
    public boolean ajouter(ImageLogo imageLogo) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(imageLogo);
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
    
    public boolean modifier(ImageLogo imageLogo) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(imageLogo);
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
    
    public boolean supprimer (ImageLogo imageLogo) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(imageLogo);
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
    
    public ImageLogo get (int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        ImageLogo imageLogo = (ImageLogo) session.get(ImageLogo.class, id);
        if(imageLogo==null){
            throw new RuntimeException();
        }
        else{
            
        }
        
        session.getTransaction().commit();
        session.close();
        
        return imageLogo;
    }
    
    public List<ImageLogo> getall(){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<ImageLogo> imageLogos = session.createCriteria(ImageLogo.class).list();
        
        session.getTransaction().commit();
        session.close();
        
        return imageLogos;
        
    }
}
