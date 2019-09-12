package net.keama.workspace.service;

import java.util.List;
import net.keama.workspace.dao.PosteDAO;
import net.keama.workspace.entity.Poste;

public class PosteService {
    PosteDAO posteDAO = new PosteDAO();
    
    public boolean ajouter (Poste poste){
        boolean isGood = posteDAO.ajouter(poste);
        return isGood ;
    }
    
    public boolean modififer (Poste poste){
        boolean isGood = posteDAO.modifier(poste);
        return isGood ;
    }
    
    public boolean supprimer (Poste poste){
        boolean isGood = posteDAO.supprimer(poste);
        return isGood ;
    }
    
    public Poste get(int id){
        Poste poste = posteDAO.get(id);
        return poste ;
    }
    
    public List<Poste> getAll(){
        List<Poste> postes = posteDAO.getall();
        return postes ;
    }
}
