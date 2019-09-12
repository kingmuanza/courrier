
package net.keama.workspace.service;

import java.util.List;
import net.keama.workspace.dao.SiteDAO;
import net.keama.workspace.entity.Site;

public class SiteService {
    SiteDAO siteDAO = new SiteDAO();
    
    public boolean ajouter (Site site){
        boolean isGood = siteDAO.ajouter(site);
        return isGood ;
    }
    
    public boolean modififer (Site site){
        boolean isGood = siteDAO.modifier(site);
        return isGood ;
    }
    
    public boolean supprimer (Site site){
        boolean isGood = siteDAO.supprimer(site);
        return isGood ;
    }
    
    public Site get(int id){
        Site site = siteDAO.get(id);
        return site ;
    }
    
    public List<Site> getAll(){
        List<Site> sites = siteDAO.getall();
        return sites ;
    }
}
