package net.keama.workspace.dao;

import java.util.ArrayList;
import java.util.List;
import net.keama.workspace.entity.Poste;
import net.keama.workspace.entity.Structure;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class StructureDAO {

    private List<Structure> structuressAclasser = null;

    public boolean ajouter(Structure structure) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(structure);
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

    public boolean modifier(Structure structure) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(structure);
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

    public boolean supprimer(Structure structure) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(structure);
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

    public Structure get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Structure structure = (Structure) session.get(Structure.class, id);
        if (structure == null) {
            throw new RuntimeException();
        } else {
            Hibernate.initialize(structure.getStructures());
            Hibernate.initialize(structure.getStructure());
            Hibernate.initialize(structure.getSite());
        }

        session.getTransaction().commit();
        session.close();

        return structure;
    }

    public List<Structure> getall() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Structure> all = new ArrayList<Structure>();
        List<Structure> parents = session.createCriteria(Structure.class, "structure")
                .add(Restrictions.isNull("structure.structure"))
                .list();
        all.addAll(parents);
        List<Structure> structures = session.createCriteria(Structure.class, "structure")
                .createAlias("structure.structure", "parent")
                .addOrder(Order.asc("parent.idstructure"))
                .list();
        all.addAll(structures);
        for (Structure s : all) {
            Hibernate.initialize(s.getStructure());
            Hibernate.initialize(s.getStructures());
        }
        session.getTransaction().commit();
        session.close();

        return all;

    }

    public List<Structure> getFilsAll(int idparent) {
        System.out.println("Récupération  : " + structuressAclasser);
        if (structuressAclasser == null) {
            structuressAclasser = new ArrayList<Structure>();
            System.out.println("La liste est null ####################################");
        }
        structuressAclasser.add(this.get(idparent));

        if (this.get(idparent).getStructures() != null && !get(idparent).getStructures().isEmpty()) {
            for(Structure structure : this.get(idparent).getStructures()){
                this.getFilsAll(structure.getIdstructure());
            }
            return structuressAclasser;
            
        }else{
            return null;
        }
        
    }
    
}
