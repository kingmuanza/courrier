package net.keama.workspace.dao;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import net.keama.workspace.entity.Courrier;
import net.keama.workspace.entity.CourrierProcedure;
import net.keama.workspace.entity.CourrierProcedureEnCours;
import net.keama.workspace.entity.CourrierProcedureTache;
import net.keama.workspace.entity.CourrierResponsable;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class CourrierProcedureEnCoursDAO {
    
    CourrierResponsableDAO courrierResponsableDAO = new CourrierResponsableDAO();

    public boolean ajouter(CourrierProcedureEnCours courrierProcedureEnCours) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(courrierProcedureEnCours);
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

    public boolean modifier(CourrierProcedureEnCours courrierProcedureEnCours) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(courrierProcedureEnCours);
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

    public boolean supprimer(CourrierProcedureEnCours courrierProcedureEnCours) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(courrierProcedureEnCours);
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

    public CourrierProcedureEnCours get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        CourrierProcedureEnCours courrierProcedureEnCours = (CourrierProcedureEnCours) session.get(CourrierProcedureEnCours.class, id);
        if (courrierProcedureEnCours == null) {
            throw new RuntimeException();
        } else {
            Hibernate.initialize(courrierProcedureEnCours.getCourrierResponsables());
        }

        session.getTransaction().commit();
        session.close();

        return courrierProcedureEnCours;
    }

    public List<CourrierProcedureEnCours> getall() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<CourrierProcedureEnCours> courrierProcedureEnCourss = session.createCriteria(CourrierProcedureEnCours.class).list();
        for(CourrierProcedureEnCours courrierProcedureEnCours : courrierProcedureEnCourss ){
            Hibernate.initialize(courrierProcedureEnCours.getCourrierResponsables());
            for(CourrierResponsable cr : courrierProcedureEnCours.getCourrierResponsables()){
                Hibernate.initialize(cr.getCourrierProcedureTache());
            }
        }

        session.getTransaction().commit();
        session.close();

        return courrierProcedureEnCourss;

    }

    public boolean creer(CourrierProcedure courrierProcedure, Courrier courrier) {
        CourrierProcedureEnCours courrierProcedureEnCours = new CourrierProcedureEnCours();
        courrierProcedureEnCours.setCourrier(courrier);
        courrierProcedureEnCours.setCourrierProcedure(courrierProcedure);
        courrierProcedureEnCours.setDateDebut(new Date());

        Random random = new Random();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date_emission = formatter.format(new Date());
        int randomNumber = random.nextInt(1000);

        courrierProcedureEnCours.setRef(courrierProcedure.getRef() + "/" + date_emission.replace("-", "").replace(":", "").replace(" ", "") + "/" + randomNumber);
        courrierProcedureEnCours.setStatut("Démarré");
        if (this.ajouter(courrierProcedureEnCours)) {
            for (CourrierProcedureTache cpt : courrierProcedure.getCourrierProcedureTaches()) {
                CourrierResponsable courrierResponsable = new CourrierResponsable();
                courrierResponsable.setCourrier(courrier);
                courrierResponsable.setCourrierProcedureEnCours(courrierProcedureEnCours);
                courrierResponsable.setCourrierProcedureTache(cpt);
                Date oday = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(oday);
                cal.add(Calendar.DATE, 0);
                Date debut = cal.getTime();
                Date fin = cal.getTime();
                if(cpt.getOrdre()==1){
                    courrierResponsable.setDateRecuperation(debut);
                    cal.add(Calendar.DATE, cpt.getDuree());
                    fin = cal.getTime();
                    courrierResponsable.setDateLimite(fin);
                    courrierResponsable.setStatut("En cours");
                    
                }else{
                    courrierResponsable.setStatut("En attente");
                }
                courrierResponsable.setEmploye(cpt.getEmploye());
                courrierResponsable.setLibelle(cpt.getLibelle());
                courrierResponsable.setTypeApprobation(cpt.getTypeApprobation());
                courrierResponsableDAO.ajouter(courrierResponsable);
            }
            return true;
        }else{
            return false ;
        }

        
    }
}
