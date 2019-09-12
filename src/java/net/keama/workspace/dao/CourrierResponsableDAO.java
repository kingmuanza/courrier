package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.Courrier;
import net.keama.workspace.entity.CourrierResponsable;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class CourrierResponsableDAO {

    public boolean ajouter(CourrierResponsable courrierResponsable) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(courrierResponsable);
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

    public boolean modifier(CourrierResponsable courrierResponsable) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(courrierResponsable);
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

    public boolean supprimer(CourrierResponsable courrierResponsable) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(courrierResponsable);
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

    public CourrierResponsable get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        CourrierResponsable courrierResponsable = (CourrierResponsable) session.get(CourrierResponsable.class, id);
        if (courrierResponsable == null) {
            throw new RuntimeException();
        } else {
            Hibernate.initialize(courrierResponsable.getCourrier());
            Hibernate.initialize(courrierResponsable.getEmploye());
            Hibernate.initialize(courrierResponsable.getCourrierProcedureTache());

        }

        session.getTransaction().commit();
        session.close();

        return courrierResponsable;
    }

    public CourrierResponsable getSuivant(CourrierResponsable cr) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        if (cr.getCourrierProcedureTache()!=null) {
            CourrierResponsable courrierResponsable = (CourrierResponsable) session.createCriteria(CourrierResponsable.class, "courrierResponsable")
                    .createAlias("courrierResponsable.courrierProcedureEnCours", "procedure")
                    .createAlias("courrierResponsable.courrierProcedureTache", "tacheProcedure")
                    .add(Restrictions.eq("procedure.idcourrierProcedureEnCours", cr.getCourrierProcedureEnCours().getIdcourrierProcedureEnCours()))
                    .add(Restrictions.eq("tacheProcedure.ordre", cr.getCourrierProcedureTache().getOrdre() + 1))
                    .list()
                    .get(0);
            if (courrierResponsable == null) {
                throw new RuntimeException();
            } else {
                Hibernate.initialize(courrierResponsable.getCourrier());
                Hibernate.initialize(courrierResponsable.getEmploye());
            }

            session.getTransaction().commit();
            session.close();
            return courrierResponsable;
        }else{
            return null ;
        }

        
    }

    public List<CourrierResponsable> getall() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<CourrierResponsable> courrierResponsables = session.createCriteria(CourrierResponsable.class).list();
        for (CourrierResponsable courrierResponsable : courrierResponsables) {
            Hibernate.initialize(courrierResponsable.getCourrier());
            Hibernate.initialize(courrierResponsable.getEmploye());
        }

        session.getTransaction().commit();
        session.close();

        return courrierResponsables;

    }
    
    public List<CourrierResponsable> getTerminees(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<CourrierResponsable> courrierResponsables = session.createCriteria(CourrierResponsable.class, "courrierResponsable")
                .createAlias("courrierResponsable.employe", "il")
                .add(Restrictions.eq("courrierResponsable.statut", "Terminé"))
                .add(Restrictions.eq("il.idemploye", id))
                .list();
                
        for(CourrierResponsable courrierResponsable : courrierResponsables){
            Hibernate.initialize(courrierResponsable.getCourrier());
            if(courrierResponsable.getCourrierProcedureEnCours()!=null){
                Hibernate.initialize(courrierResponsable.getCourrierProcedureEnCours().getCourrierProcedure());
            }
        }
        
        
        session.getTransaction().commit();
        session.close();

        return courrierResponsables;
    }
    public List<CourrierResponsable> getEnCours(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<CourrierResponsable> courrierResponsables = session.createCriteria(CourrierResponsable.class, "courrierResponsable")
                .createAlias("courrierResponsable.employe", "il")
                .add(Restrictions.eq("courrierResponsable.statut", "En cours"))
                .add(Restrictions.eq("il.idemploye", id))
                .list();
                
        for(CourrierResponsable courrierResponsable : courrierResponsables){
            Hibernate.initialize(courrierResponsable.getCourrier());
            if(courrierResponsable.getCourrierProcedureEnCours()!=null){
                Hibernate.initialize(courrierResponsable.getCourrierProcedureEnCours().getCourrierProcedure());
            }
        }
        
        
        session.getTransaction().commit();
        session.close();

        return courrierResponsables;
    }
    
    public List<CourrierResponsable> getEnRetard(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<CourrierResponsable> courrierResponsables = session.createCriteria(CourrierResponsable.class, "courrierResponsable")
                .createAlias("courrierResponsable.employe", "il")
                .add(Restrictions.eq("courrierResponsable.statut", "En retard"))
                .add(Restrictions.eq("il.idemploye", id))
                .list();
                
        for(CourrierResponsable courrierResponsable : courrierResponsables){
            Hibernate.initialize(courrierResponsable.getCourrier());
            if(courrierResponsable.getCourrierProcedureEnCours()!=null){
                Hibernate.initialize(courrierResponsable.getCourrierProcedureEnCours().getCourrierProcedure());
            }
        }
        
        
        session.getTransaction().commit();
        session.close();

        return courrierResponsables;
    }
}
