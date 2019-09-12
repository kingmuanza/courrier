
package net.keama.workspace.dao;

import java.util.List;
import net.keama.workspace.entity.Message;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class MessageDAO {
    public boolean ajouter(Message message) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(message);
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
    
    public boolean modifier(Message message) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(message);
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
    
    public boolean supprimer (Message message) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(message);
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
    
    public Message get (int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        Message message = (Message) session.get(Message.class, id);
        if(message==null){
            throw new RuntimeException();
        }
        else{
            
        }
        
        session.getTransaction().commit();
        session.close();
        
        return message;
    }
    
    public List<Message> getall(){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<Message> messages = session.createCriteria(Message.class).list();
        
        session.getTransaction().commit();
        session.close();
        
        return messages;
        
    }
    
    public List<Message> getall(int emetteur, int recepteur){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        List<Message> messages = session.createCriteria(Message.class, "message")
                .createAlias("message.employeByIdrecepteur", "recepteur")
                .createAlias("message.employeByIdemetteur", "emetteur")
                .add(Restrictions.or(Restrictions.eq("emetteur.idemploye", recepteur), Restrictions.eq("emetteur.idemploye", emetteur)))
                .add(Restrictions.or(Restrictions.eq("recepteur.idemploye", recepteur), Restrictions.eq("recepteur.idemploye", emetteur)))
                .addOrder(Order.asc("message.dateEnvoi"))
                .list();
        
        for(Message message : messages){
            Hibernate.initialize(message.getEmployeByIdemetteur());
            Hibernate.initialize(message.getEmployeByIdrecepteur());
        }
        session.getTransaction().commit();
        session.close();
        
        return messages;
        
    }
}
