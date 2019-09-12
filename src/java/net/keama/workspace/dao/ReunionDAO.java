package net.keama.workspace.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.keama.workspace.entity.Reunion;
import net.keama.workspace.entity.ReunionEmploye;
import net.keama.workspace.entity.ReunionRapport;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class ReunionDAO {

    public boolean ajouter(Reunion reunion) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(reunion);
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

    public boolean modifier(Reunion reunion) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(reunion);
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

    public boolean supprimer(Reunion reunion) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(reunion);
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

    public Reunion get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Reunion reunion = (Reunion) session.get(Reunion.class, id);
        if (reunion == null) {
            throw new RuntimeException();
        } else {
            Hibernate.initialize(reunion.getReunionEmployes());
            Hibernate.initialize(reunion.getSalleReunion());
            Hibernate.initialize(reunion.getEmploye());
            Hibernate.initialize(reunion.getReunionRapports());
            if (reunion.getReunionRapports() != null && !reunion.getReunionRapports().isEmpty()) {
                for (ReunionRapport rr : reunion.getReunionRapports()) {
                    Hibernate.initialize(rr);
                }
            }

            if (reunion.getReunionEmployes() != null && !reunion.getReunionEmployes().isEmpty()) {
                for (ReunionEmploye re : reunion.getReunionEmployes()) {
                    Hibernate.initialize(re.getEmploye().getPoste());
                }
            }
        }

        session.getTransaction().commit();
        session.close();

        return reunion;
    }

    public List<Reunion> getall() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Reunion> reunions = session.createCriteria(Reunion.class).list();
        for (Reunion reunion : reunions) {
            Hibernate.initialize(reunion.getReunionEmployes());
            Hibernate.initialize(reunion.getEmploye());
            Hibernate.initialize(reunion.getReunionRapports());
            if (reunion.getReunionRapports() != null && !reunion.getReunionRapports().isEmpty()) {
                for (ReunionRapport rr : reunion.getReunionRapports()) {
                    Hibernate.initialize(rr);
                }
            }
            if (reunion.getReunionEmployes() != null && !reunion.getReunionEmployes().isEmpty()) {
                for (ReunionEmploye re : reunion.getReunionEmployes()) {
                    Hibernate.initialize(re.getEmploye());
                }
            }
            Hibernate.initialize(reunion.getSalleReunion());
        }

        session.getTransaction().commit();
        session.close();

        return reunions;

    }

    public List<Reunion> getall(Date date, int i) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Reunion> reunions = session.createCriteria(Reunion.class, "reunions")
                .createAlias("reunions.reunionEmployes", "lui")
                .createAlias("lui.employe", "il")
                .add(Restrictions.eq("il.idemploye", i))
                .list();
        List<Reunion> reunionParDate = new ArrayList<Reunion>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        Date debut = cal.getTime();
        cal.add(Calendar.DATE, 1);
        Date fin = cal.getTime();
        for (Reunion reunion : reunions) {
            Hibernate.initialize(reunion.getReunionEmployes());
            Hibernate.initialize(reunion.getEmploye());
            Hibernate.initialize(reunion.getReunionRapports());
            if (reunion.getReunionRapports() != null && !reunion.getReunionRapports().isEmpty()) {
                for (ReunionRapport rr : reunion.getReunionRapports()) {
                    Hibernate.initialize(rr);
                }
            }
            if (reunion.getReunionEmployes() != null && !reunion.getReunionEmployes().isEmpty()) {
                for (ReunionEmploye re : reunion.getReunionEmployes()) {
                    Hibernate.initialize(re.getEmploye());
                }
            }
            Hibernate.initialize(reunion.getSalleReunion());
        }
        for(Reunion reunion : reunions){
            if(reunion.getDateDebut().after(debut)&&reunion.getDateDebut().before(fin)){
                reunionParDate.add(reunion);
            }
        }
        session.getTransaction().commit();
        session.close();

        return reunionParDate;

    }

    public List<Reunion> getMesReunionsAVenir(int i) {
        Date oday = new Date();

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Reunion> reunions = session.createCriteria(Reunion.class, "reunions")
                .createAlias("reunions.reunionEmployes", "lui")
                .createAlias("lui.employe", "il")
                .add(Restrictions.eq("il.idemploye", i))
                .add(Restrictions.gt("reunions.dateDebut", oday))
                .list();
        for (Reunion r : reunions) {
            Hibernate.initialize(r.getSalleReunion());
            Hibernate.initialize(r.getEmploye());
            Hibernate.initialize(r.getReunionEmployes());
            Hibernate.initialize(r.getReunionRapports());
        }
        session.getTransaction().commit();
        session.close();

        return reunions;
    }

    public List<Reunion> getMesReunionsAujourdhui(int i) {
        Date oday = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(oday);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        Date debut = cal.getTime();
        cal.add(Calendar.DATE, 1);
        Date fin = cal.getTime();

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Reunion> reunions = session.createCriteria(Reunion.class, "reunions")
                .createAlias("reunions.reunionEmployes", "lui")
                .createAlias("lui.employe", "il")
                .add(Restrictions.eq("il.idemploye", i))
                .add(Restrictions.gt("reunions.dateDebut", debut))
                .add(Restrictions.lt("reunions.dateDebut", fin))
                .list();
        for (Reunion r : reunions) {
            Hibernate.initialize(r.getSalleReunion());
            Hibernate.initialize(r.getEmploye());
            Hibernate.initialize(r.getReunionEmployes());
            Hibernate.initialize(r.getReunionRapports());
        }
        session.getTransaction().commit();
        session.close();

        return reunions;
    }
}
