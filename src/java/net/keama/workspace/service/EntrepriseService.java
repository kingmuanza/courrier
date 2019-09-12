
package net.keama.workspace.service;

import java.util.List;
import net.keama.workspace.dao.EntrepriseDAO;
import net.keama.workspace.entity.Entreprise;

public class EntrepriseService {
    EntrepriseDAO entrepriseDAO = new EntrepriseDAO();
    
    public boolean ajouter (Entreprise entreprise){
        boolean isGood = entrepriseDAO.ajouter(entreprise);
        return isGood ;
    }
    
    public boolean modififer (Entreprise entreprise){
        boolean isGood = entrepriseDAO.modifier(entreprise);
        return isGood ;
    }
    
    public boolean supprimer (Entreprise entreprise){
        boolean isGood = entrepriseDAO.supprimer(entreprise);
        return isGood ;
    }
    
    public Entreprise get(int id){
        Entreprise entreprise = entrepriseDAO.get(id);
        return entreprise ;
    }
    
    public List<Entreprise> getAll(){
        List<Entreprise> entreprises = entrepriseDAO.getall();
        return entreprises ;
    }
}
