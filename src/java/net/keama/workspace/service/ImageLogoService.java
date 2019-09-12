package net.keama.workspace.service;

import java.util.List;
import net.keama.workspace.dao.ImageLogoDAO;
import net.keama.workspace.entity.ImageLogo;

public class ImageLogoService {
    ImageLogoDAO imageLogoDAO = new ImageLogoDAO();
    
    public boolean ajouter (ImageLogo imageLogo){
        boolean isGood = imageLogoDAO.ajouter(imageLogo);
        return isGood ;
    }
    
    public boolean modififer (ImageLogo imageLogo){
        boolean isGood = imageLogoDAO.modifier(imageLogo);
        return isGood ;
    }
    
    public boolean supprimer (ImageLogo imageLogo){
        boolean isGood = imageLogoDAO.supprimer(imageLogo);
        return isGood ;
    }
    
    public ImageLogo get(int id){
        ImageLogo imageLogo = imageLogoDAO.get(id);
        return imageLogo ;
    }
    
    public List<ImageLogo> getAll(){
        List<ImageLogo> imageLogos = imageLogoDAO.getall();
        return imageLogos ;
    }
}
