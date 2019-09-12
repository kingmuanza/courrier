package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.CourrierFichier;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class CourrierFichierDAO {

    public boolean ajouter(CourrierFichier courrierFichier) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(courrierFichier);
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

    public boolean modifier(CourrierFichier courrierFichier) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(courrierFichier);
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

    public boolean supprimer(CourrierFichier courrierFichier) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(courrierFichier);
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

    public CourrierFichier get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        CourrierFichier courrierFichier = (CourrierFichier) session.get(CourrierFichier.class, id);
        if (courrierFichier == null) {
            throw new RuntimeException();
        } else {

        }

        session.getTransaction().commit();
        session.close();

        return courrierFichier;
    }

    public List<CourrierFichier> getall() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<CourrierFichier> courrierFichiers = session.createCriteria(CourrierFichier.class).list();

        session.getTransaction().commit();
        session.close();

        return courrierFichiers;

    }

    public List<CourrierFichier> getallbyCourrier(int idcourrier) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<CourrierFichier> courrierFichiers = session.createCriteria(CourrierFichier.class, "courrierFichier")
                .createAlias("courrierFichier.courrier", "courrier")
                .add(Restrictions.eq("courrier.idcourrier", idcourrier))
                .addOrder(Order.asc("courrierFichier.ordre"))
                .list();

        session.getTransaction().commit();
        session.close();

        return courrierFichiers;

    }
}
