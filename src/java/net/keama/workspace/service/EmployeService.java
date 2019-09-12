package net.keama.workspace.service;

import java.util.List;
import net.keama.workspace.dao.EmployeDAO;
import net.keama.workspace.entity.Employe;
import net.keama.workspace.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;

public class EmployeService {
    
    EmployeDAO employeDAO = new EmployeDAO();
    
    public boolean ajouter (Employe employe){
        boolean isGood = employeDAO.ajouter(employe);
        return isGood ;
    }
    
    public boolean modififer (Employe employe){
        boolean isGood = employeDAO.modifier(employe);
        return isGood ;
    }
    
    public boolean supprimer (Employe employe){
        boolean isGood = employeDAO.supprimer(employe);
        return isGood ;
    }
    
    public Employe get(int id){
        Employe employe = employeDAO.get(id);
        return employe ;
    }
    
    
    public List<Employe> getAll(){
        List<Employe> employes = employeDAO.getall();
        return employes ;
    }
    
}
