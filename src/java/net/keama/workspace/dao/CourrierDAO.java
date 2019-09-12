package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.Courrier;
import net.keama.workspace.entity.CourrierFichier;
import net.keama.workspace.entity.CourrierProcedureEnCours;
import net.keama.workspace.entity.CourrierResponsable;
import net.keama.workspace.entity.EntreprisePartenaire;
import net.keama.workspace.entity.Reunion;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class CourrierDAO {

    public boolean ajouter(Courrier courrier) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(courrier);
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

    public boolean modifier(Courrier courrier) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(courrier);
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

    public boolean supprimer(Courrier courrier) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(courrier);
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

    public Courrier get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Courrier courrier = (Courrier) session.get(Courrier.class, id);
        if (courrier == null) {
            throw new RuntimeException();
        } else {
            Hibernate.initialize(courrier.getCourrierProcedureEnCourses());
            for(CourrierProcedureEnCours cpec : courrier.getCourrierProcedureEnCourses()){
                Hibernate.initialize(cpec.getCourrierProcedure());
            }
            Hibernate.initialize(courrier.getStructure());
            Hibernate.initialize(courrier.getCorrespondant());
            Hibernate.initialize(courrier.getCourrierFichiers());
            if (courrier.getCourrierFichiers() != null && !courrier.getCourrierFichiers().isEmpty()) {
                for (CourrierFichier cf : courrier.getCourrierFichiers()) {
                    Hibernate.initialize(cf);
                }
            }
            Hibernate.initialize(courrier.getCourrierResponsables());
            if (courrier.getCourrierResponsables() != null && !courrier.getCourrierResponsables().isEmpty()) {
                for (CourrierResponsable cr : courrier.getCourrierResponsables()) {
                    Hibernate.initialize(cr.getEmploye());
                    Hibernate.initialize(cr.getCourrierProcedureTache());
                    if (cr.getEmploye() != null) {

                    }
                }
            }
            Hibernate.initialize(courrier.getCourrierProcedureEnCourses());
            for(CourrierProcedureEnCours cpe : courrier.getCourrierProcedureEnCourses()){
                Hibernate.initialize(cpe.getCourrierResponsables());
                for(CourrierResponsable cr : cpe.getCourrierResponsables()){
                    Hibernate.initialize(cr.getEmploye());
                }
            }
        }

        session.getTransaction().commit();
        session.close();

        return courrier;
    }
    
    public List<Courrier> getall(EntreprisePartenaire entreprise) {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        List<Courrier> courriers = session.createCriteria(Courrier.class, "courrier")
                .createAlias("courrier.correspondant", "lui")
                .createAlias("lui.entreprisePartenaire", "entreprise")
                .add(Restrictions.eq("entreprise.identreprisePartenaire", entreprise.getIdentreprisePartenaire()))
                .list();
        
        session.getTransaction().commit();
        session.close();
        return courriers;
        
    }

    public List<Courrier> getall() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Courrier> courriers = session.createCriteria(Courrier.class).addOrder(Order.asc("dateEmission")).list();
        for (Courrier courrier : courriers) {
            Hibernate.initialize(courrier.getStructure());
            Hibernate.initialize(courrier.getCourrierFichiers());
            Hibernate.initialize(courrier.getCorrespondant());

            if (courrier.getCourrierFichiers() != null && !courrier.getCourrierFichiers().isEmpty()) {
                for (CourrierFichier cf : courrier.getCourrierFichiers()) {
                    Hibernate.initialize(cf);
                }
            }
            Hibernate.initialize(courrier.getCourrierProcedureEnCourses());
            for(CourrierProcedureEnCours cpec : courrier.getCourrierProcedureEnCourses()){
                Hibernate.initialize(cpec.getCourrierProcedure());
            }
            Hibernate.initialize(courrier.getCourrierResponsables());
            if (courrier.getCourrierResponsables() != null && !courrier.getCourrierResponsables().isEmpty()) {
                for (CourrierResponsable cr : courrier.getCourrierResponsables()) {
                    Hibernate.initialize(cr.getEmploye());
                    if (cr.getEmploye() != null) {

                    }
                }
            }
        }

        session.getTransaction().commit();
        session.close();

        return courriers;

    }
    public List<Courrier> getall(boolean b) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Courrier> courriers = session.createCriteria(Courrier.class).addOrder(Order.asc("dateEmission")).list();
        for (Courrier courrier : courriers) {
            Hibernate.initialize(courrier.getCourrierProcedureEnCourses());
            for(CourrierProcedureEnCours cpec : courrier.getCourrierProcedureEnCourses()){
                Hibernate.initialize(cpec.getCourrierProcedure());
            }
            Hibernate.initialize(courrier.getCourrierResponsables());
            if (courrier.getCourrierResponsables() != null && !courrier.getCourrierResponsables().isEmpty()) {
                for (CourrierResponsable cr : courrier.getCourrierResponsables()) {
                    Hibernate.initialize(cr.getEmploye());
                    Hibernate.initialize(cr.getCourrierProcedureTache());
                    if (cr.getEmploye() != null) {

                    }
                }
            }
        }
        session.getTransaction().commit();
        session.close();

        return courriers;

    }
    
    public List<Courrier> getCourriersOday(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<Courrier> courriers = session.createCriteria(Courrier.class, "courrier")
                .createAlias("courrier.courrierResponsables", "lui")
                .createAlias("lui.employe", "il")
                .add(Restrictions.eq("courrier.statut", "Transmis"))
                .add(Restrictions.eq("lui.statut", "En cours"))
                .add(Restrictions.eq("il.idemploye", id))
                .list();
                
        for(Courrier courrier : courriers){
            Hibernate.initialize(courrier.getCourrierFichiers());
        }
        
        
        session.getTransaction().commit();
        session.close();

        return courriers;
    }
    
    public List<Courrier> getTerminees(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<Courrier> courriers = session.createCriteria(Courrier.class, "courrier")
                .createAlias("courrier.courrierResponsables", "lui")
                .createAlias("lui.employe", "il")
                .add(Restrictions.eq("lui.statut", "Terminé"))
                .add(Restrictions.eq("il.idemploye", id))
                .list();
                
        for(Courrier courrier : courriers){
            Hibernate.initialize(courrier.getCourrierFichiers());
        }
        
        
        session.getTransaction().commit();
        session.close();

        return courriers;
    }
}
