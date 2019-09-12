package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.Procedures;
import net.keama.workspace.entity.TacheProcedure;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class ProceduresDAO {

    public boolean ajouter(Procedures procedures) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(procedures);
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

    public boolean modifier(Procedures procedures) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(procedures);
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

    public boolean supprimer(Procedures procedures) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(procedures);
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

    public Procedures get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Procedures procedures = (Procedures) session.get(Procedures.class, id);
        if (procedures == null) {
            throw new RuntimeException();
        } else {
            Hibernate.initialize(procedures.getTacheProcedures());
            for (TacheProcedure tp : procedures.getTacheProcedures()) {
                Hibernate.initialize(tp.getEmploye());
                if(tp.getEmploye()!=null){
                    Hibernate.initialize(tp.getEmploye().getPoste());
                }
            }
        }

        session.getTransaction().commit();
        session.close();

        return procedures;
    }

    public List<Procedures> getall() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Procedures> proceduress = session.createCriteria(Procedures.class).list();
        for (Procedures procedures : proceduress) {
            Hibernate.initialize(procedures.getTacheProcedures());
            for (TacheProcedure tp : procedures.getTacheProcedures()) {
                Hibernate.initialize(tp.getEmploye());
                if(tp.getEmploye()!=null){
                    Hibernate.initialize(tp.getEmploye().getPoste());
                }
            }
        }

        session.getTransaction().commit();
        session.close();

        return proceduress;

    }
}
