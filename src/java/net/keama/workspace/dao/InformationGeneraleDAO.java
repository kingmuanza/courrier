package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.InformationGenerale;
import net.keama.workspace.entity.InformationGeneraleEmploye;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

public class InformationGeneraleDAO {

    public boolean ajouter(InformationGenerale informationGenerale) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(informationGenerale);
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

    public boolean modifier(InformationGenerale informationGenerale) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(informationGenerale);
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

    public boolean supprimer(InformationGenerale informationGenerale) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(informationGenerale);
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

    public InformationGenerale get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        InformationGenerale informationGenerale = (InformationGenerale) session.get(InformationGenerale.class, id);
        if (informationGenerale == null) {
            throw new RuntimeException();
        } else {
            Hibernate.initialize(informationGenerale.getEmploye());
            Hibernate.initialize(informationGenerale.getInformationGeneraleEmployes());
            if (informationGenerale.getInformationGeneraleEmployes() != null && !informationGenerale.getInformationGeneraleEmployes().isEmpty()) {
                for (InformationGeneraleEmploye ige : informationGenerale.getInformationGeneraleEmployes()) {
                    Hibernate.initialize(ige.getEmploye());
                }
            }
        }

        session.getTransaction().commit();
        session.close();

        return informationGenerale;
    }

    public List<InformationGenerale> getall() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<InformationGenerale> informationGenerales = session.createCriteria(InformationGenerale.class).addOrder(Order.desc("dateEnregistrement")).list();

        for (InformationGenerale informationGenerale : informationGenerales) {
            Hibernate.initialize(informationGenerale.getEmploye());
            Hibernate.initialize(informationGenerale.getInformationGeneraleEmployes());
            if (informationGenerale.getInformationGeneraleEmployes() != null && !informationGenerale.getInformationGeneraleEmployes().isEmpty()) {
                for (InformationGeneraleEmploye ige : informationGenerale.getInformationGeneraleEmployes()) {
                    Hibernate.initialize(ige.getEmploye());
                }
            }
        }

        session.getTransaction().commit();
        session.close();

        return informationGenerales;

    }

    public int getNombre() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Long result = (Long) session.createCriteria(InformationGenerale.class, "infos")
                .setProjection(Projections.rowCount())
                .uniqueResult();

        session.getTransaction().commit();
        session.close();
        return result.intValue();
    }
    
    public int getMyNombre(int i) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Long result = (Long) session.createCriteria(InformationGenerale.class, "infos")
                .createAlias("infos.informationGeneraleEmployes", "lui")
                .createAlias("lui.employe", "il")
                .add(Restrictions.eq("il.idemploye", i))
                .setProjection(Projections.rowCount())
                .uniqueResult();

        session.getTransaction().commit();
        session.close();
        return result.intValue();
    }
    
    public List<InformationGenerale> getMyInfos(int i) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<InformationGenerale> results = session.createCriteria(InformationGenerale.class, "infos")
                .createAlias("infos.informationGeneraleEmployes", "lui")
                .createAlias("lui.employe", "il")
                .add(Restrictions.eq("il.idemploye", i))
                .addOrder(Order.asc("infos.dateEnregistrement"))
                .setMaxResults(10)
                .list();

        session.getTransaction().commit();
        session.close();
        return results;
    }
}
