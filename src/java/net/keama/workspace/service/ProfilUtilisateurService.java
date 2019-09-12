package net.keama.workspace.service;

import java.util.List;
import net.keama.workspace.dao.ProfilUtilisateurDAO;
import net.keama.workspace.entity.ProfilUtilisateur;

public class ProfilUtilisateurService {
    ProfilUtilisateurDAO profilUtilisateurDAO = new ProfilUtilisateurDAO();
    
    public boolean ajouter (ProfilUtilisateur profilUtilisateur){
        boolean isGood = profilUtilisateurDAO.ajouter(profilUtilisateur);
        return isGood ;
    }
    
    public boolean modififer (ProfilUtilisateur profilUtilisateur){
        boolean isGood = profilUtilisateurDAO.modifier(profilUtilisateur);
        return isGood ;
    }
    
    public boolean supprimer (ProfilUtilisateur profilUtilisateur){
        boolean isGood = profilUtilisateurDAO.supprimer(profilUtilisateur);
        return isGood ;
    }
    
    public ProfilUtilisateur get(int id){
        ProfilUtilisateur profilUtilisateur = profilUtilisateurDAO.get(id);
        return profilUtilisateur ;
    }
    
    public List<ProfilUtilisateur> getAll(){
        List<ProfilUtilisateur> profilUtilisateurs = profilUtilisateurDAO.getall();
        return profilUtilisateurs ;
    }
}
