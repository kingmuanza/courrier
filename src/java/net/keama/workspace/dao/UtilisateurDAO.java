package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.Utilisateur;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class UtilisateurDAO {

    public boolean ajouter(Utilisateur utilisateur) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(utilisateur);
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

    public boolean modifier(Utilisateur utilisateur) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(utilisateur);
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

    public boolean supprimer(Utilisateur utilisateur) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(utilisateur);
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

    public Utilisateur get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Utilisateur utilisateur = (Utilisateur) session.get(Utilisateur.class, id);
        if (utilisateur == null) {
            throw new RuntimeException();
        } else {
            Hibernate.initialize(utilisateur.getEmploye());
            if (utilisateur.getEmploye() != null) {
                Hibernate.initialize(utilisateur.getEmploye().getImageLogo());
                Hibernate.initialize(utilisateur.getEmploye().getPoste());
                if (utilisateur.getEmploye().getPoste() != null) {
                    Hibernate.initialize(utilisateur.getEmploye().getPoste().getStructure());
                    if (utilisateur.getEmploye().getPoste().getStructure() != null) {
                        Hibernate.initialize(utilisateur.getEmploye().getPoste().getStructure().getSite());
                        if(utilisateur.getEmploye().getPoste().getStructure().getSite()!=null){
                            Hibernate.initialize(utilisateur.getEmploye().getPoste().getStructure().getSite().getEntreprise());
                        }
                    }
                }
            }
           
        }

        session.getTransaction().commit();
        session.close();

        return utilisateur;
    }

    public List<Utilisateur> getall() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Utilisateur> utilisateurs = session.createCriteria(Utilisateur.class).list();

        session.getTransaction().commit();
        session.close();

        return utilisateurs;

    }
}
