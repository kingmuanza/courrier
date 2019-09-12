package net.keama.workspace.service;

import java.util.List;
import net.keama.workspace.dao.UtilisateurDAO;
import net.keama.workspace.entity.Utilisateur;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.Session;

public class UtilisateurService {
    UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
    
    public boolean ajouter (Utilisateur utilisateur){
        boolean isGood = utilisateurDAO.ajouter(utilisateur);
        return isGood ;
    }
    
    public boolean modififer (Utilisateur utilisateur){
        boolean isGood = utilisateurDAO.modifier(utilisateur);
        return isGood ;
    }
    
    public boolean supprimer (Utilisateur utilisateur){
        boolean isGood = utilisateurDAO.supprimer(utilisateur);
        return isGood ;
    }
    
    public Utilisateur get(int id){
        Utilisateur utilisateur = utilisateurDAO.get(id);
        return utilisateur ;
    }
    
    public Utilisateur get (String login, String passe){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        Utilisateur utilisateur = null ;
        List<Utilisateur> utilisateurs = session.createCriteria(Utilisateur.class).list();
        for(Utilisateur u : utilisateurs){
            if(u.getLogin().equals(login)&&u.getPasse().equals(passe)){
                utilisateur = u ;
            }
        }
        
        session.getTransaction().commit();
        session.close();
        
        return utilisateur ;
    }
    
    public List<Utilisateur> getAll(){
        List<Utilisateur> utilisateurs = utilisateurDAO.getall();
        return utilisateurs ;
    }
}
