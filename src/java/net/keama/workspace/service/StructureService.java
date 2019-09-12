package net.keama.workspace.service;

import java.util.List;
import net.keama.workspace.dao.StructureDAO;
import net.keama.workspace.entity.Structure;

public class StructureService {
    StructureDAO structureDAO = new StructureDAO();
    
    public boolean ajouter (Structure structure){
        boolean isGood = structureDAO.ajouter(structure);
        return isGood ;
    }
    
    public boolean modififer (Structure structure){
        boolean isGood = structureDAO.modifier(structure);
        return isGood ;
    }
    
    public boolean supprimer (Structure structure){
        boolean isGood = structureDAO.supprimer(structure);
        return isGood ;
    }
    
    public Structure get(int id){
        Structure structure = structureDAO.get(id);
        return structure ;
    }
    
    public List<Structure> getAll(){
        List<Structure> structures = structureDAO.getall();
        return structures ;
    }
}
