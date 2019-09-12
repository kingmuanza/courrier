package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.Employe;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class EmployeDAO {

    public boolean ajouter(Employe employe) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(employe);
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

    public boolean modifier(Employe employe) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(employe);
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

    public boolean supprimer(Employe employe) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(employe);
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

    public Employe get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Employe employe = (Employe) session.get(Employe.class, id);
        if (employe == null) {
            throw new RuntimeException();
        } else {
            Hibernate.initialize(employe.getImageLogo());
            Hibernate.initialize(employe.getPoste());
            if (employe.getPoste() != null) {
                Hibernate.initialize(employe.getPoste().getStructure());
                if (employe.getPoste().getStructure() != null) {
                    Hibernate.initialize(employe.getPoste().getStructure().getSite());
                    if (employe.getPoste().getStructure().getSite() != null) {
                        Hibernate.initialize(employe.getPoste().getStructure().getSite().getEntreprise());
                    }
                }
            }

        }

        session.getTransaction().commit();
        session.close();

        return employe;
    }

    public List<Employe> getall() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Employe> employes = session.createCriteria(Employe.class).list();
        for (Employe employe : employes) {
            Hibernate.initialize(employe.getPoste());
            Hibernate.initialize(employe.getImageLogo());
            if (employe.getPoste() != null) {
                Hibernate.initialize(employe.getPoste().getStructure());
                if (employe.getPoste().getStructure() != null) {
                    Hibernate.initialize(employe.getPoste().getStructure().getSite());
                    if (employe.getPoste().getStructure().getSite() != null) {
                        Hibernate.initialize(employe.getPoste().getStructure().getSite().getEntreprise());
                    }
                }
            }
        }

        session.getTransaction().commit();
        session.close();

        return employes;

    }

}
