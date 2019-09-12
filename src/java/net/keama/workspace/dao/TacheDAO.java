package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.Tache;
import net.keama.workspace.entity.TacheDocument;
import net.keama.workspace.entity.TacheEmploye;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class TacheDAO {
    public boolean ajouter(Tache tache) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(tache);
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
    
    public boolean modifier(Tache tache) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(tache);
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
    
    public boolean supprimer (Tache tache) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(tache);
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
    
    public Tache get (int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        Tache tache = (Tache) session.get(Tache.class, id);
        if(tache==null){
            throw new RuntimeException();
        }
        else{
            Hibernate.initialize(tache.getEmployeByIdemploye());
            Hibernate.initialize(tache.getEmployeByIdordonnateur());
            Hibernate.initialize(tache.getTacheEmployes());
            for(TacheEmploye te : tache.getTacheEmployes()){
                Hibernate.initialize(te.getEmploye());
            }
            Hibernate.initialize(tache.getTacheDocuments());
            for(TacheDocument td : tache.getTacheDocuments()){
                Hibernate.initialize(td.getEmploye());
            }
        }
        
        session.getTransaction().commit();
        session.close();
        
        return tache;
    }
    
    public List<Tache> getall(){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<Tache> taches = session.createCriteria(Tache.class).list();
        for(Tache tache : taches){
            Hibernate.initialize(tache.getEmployeByIdemploye());
            Hibernate.initialize(tache.getEmployeByIdordonnateur());
            Hibernate.initialize(tache.getTacheEmployes());
            for(TacheEmploye te : tache.getTacheEmployes()){
                Hibernate.initialize(te.getEmploye());
            }
            Hibernate.initialize(tache.getTacheDocuments());
            for(TacheDocument td : tache.getTacheDocuments()){
                Hibernate.initialize(td.getEmploye());
            }
        } 
        
        session.getTransaction().commit();
        session.close();
        
        return taches;
        
    }
    
    public List<Tache> getallMine(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<Tache> taches = session.createCriteria(Tache.class, "tache")
                .createAlias("tache.tacheEmployes", "lui")
                .createAlias("lui.employe", "il")
                .add(Restrictions.eq("il.idemploye", id))
                .list();
        
        for(Tache tache : taches){
            Hibernate.initialize(tache.getEmployeByIdemploye());
            Hibernate.initialize(tache.getEmployeByIdordonnateur());
            Hibernate.initialize(tache.getTacheEmployes());
            for(TacheEmploye te : tache.getTacheEmployes()){
                Hibernate.initialize(te.getEmploye());
            }
            Hibernate.initialize(tache.getTacheDocuments());
            for(TacheDocument td : tache.getTacheDocuments()){
                Hibernate.initialize(td.getEmploye());
            }
        } 
        
        session.getTransaction().commit();
        session.close();
        
        return taches;
    }
}
