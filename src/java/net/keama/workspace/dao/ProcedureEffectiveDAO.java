package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.ProcedureEffective;
import net.keama.workspace.entity.TacheProcedureEffectue;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class ProcedureEffectiveDAO {
    public boolean ajouter(ProcedureEffective procedureEffective) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(procedureEffective);
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
    
    public boolean modifier(ProcedureEffective procedureEffective) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(procedureEffective);
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
    
    public boolean supprimer (ProcedureEffective procedureEffective) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(procedureEffective);
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
    
    public ProcedureEffective get (int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        ProcedureEffective procedureEffective = (ProcedureEffective) session.get(ProcedureEffective.class, id);
        if(procedureEffective==null){
            throw new RuntimeException();
        }
        else{
            Hibernate.initialize(procedureEffective.getProcedures());
            Hibernate.initialize(procedureEffective.getTacheProcedureEffectues());
            for(TacheProcedureEffectue tpe : procedureEffective.getTacheProcedureEffectues()){
                Hibernate.initialize(tpe.getTacheProcedure().getEmploye().getPoste());
            }
        }
        
        session.getTransaction().commit();
        session.close();
        
        return procedureEffective;
    }
    
    public List<ProcedureEffective> getall(){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<ProcedureEffective> procedureEffectives = session.createCriteria(ProcedureEffective.class).list();
        for(ProcedureEffective procedureEffective : procedureEffectives){
            Hibernate.initialize(procedureEffective.getProcedures());
            Hibernate.initialize(procedureEffective.getTacheProcedureEffectues());
            for(TacheProcedureEffectue tpe : procedureEffective.getTacheProcedureEffectues()){
                Hibernate.initialize(tpe.getTacheProcedure().getEmploye().getPoste());
            }
        } 
        
        session.getTransaction().commit();
        session.close();
        
        return procedureEffectives;
        
    }
}
