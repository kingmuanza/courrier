package net.keama.workspace.servlets.parametres;

import java.io.IOException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.entity.Entreprise;
import net.keama.workspace.service.EntrepriseService;

@WebServlet(name = "EntrepriseServlet", urlPatterns = {"/entreprise"})
public class EntrepriseServlet extends HttpServlet {
    
    EntrepriseService entrepriseService = new EntrepriseService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Entreprise entreprise = entrepriseService.get(1);
        request.setAttribute("entreprise", entreprise);
        request.setAttribute("attributs", "attributs");

        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/parametres/entreprise.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        
        //Récupération des paramètres
        String nom = request.getParameter("nom");
        System.out.println("Nom : "+nom);
        
        String sigle = request.getParameter("sigle");
        System.out.println("Sigle : "+sigle);
        
        String numeroContribuable = request.getParameter("rib");
        System.out.println("numeroContribuable : "+numeroContribuable);
        
        String date_creation = request.getParameter("date_creation");
        System.out.println("date_creation : "+date_creation);
        
        String adresse = request.getParameter("adresse");
        System.out.println("adresse : "+adresse);
        
        String adresse_complement = request.getParameter("adresse_complement");
        System.out.println("adresse_complement : "+adresse_complement);
        
        String bp = request.getParameter("bp");
        System.out.println("bp : "+bp);
        
        String fax = request.getParameter("fax");
        System.out.println("fax : "+fax);
        
        String tel1 = request.getParameter("tel1");
        System.out.println("tel1 : "+tel1);
        
        String tel2 = request.getParameter("tel2");
        System.out.println("tel2 : "+tel2);
        
        String tel3 = request.getParameter("tel3");
        System.out.println("tel3 : "+tel3);
        
        String email1 = request.getParameter("email1");
        System.out.println("email1 : "+email1);
        
        String email2 = request.getParameter("email2");
        System.out.println("email2 : "+email2);
        
        String email3 = request.getParameter("email3");
        System.out.println("email3 : "+email3);
        
        
        //Conversion des types 
        Date dateCreation = null ;
        
        try {
            dateCreation = sdf.parse(date_creation);
            System.out.println("Date : "+ dateCreation);
        } catch (ParseException ex) {
            
            dateCreation = new Date() ;
        }
        System.out.println("Date convertie : "+dateCreation);
        
        //Création de l'objet
        
        Entreprise entreprise = entrepriseService.get(1);
        entreprise.setAdresse(adresse);
        entreprise.setAdresseComplement(adresse_complement);
        entreprise.setBp(bp);
        entreprise.setDateCreation(dateCreation);
        entreprise.setEmail1(email1);
        entreprise.setEmail2(email2);
        entreprise.setEmail3(email3);
        entreprise.setFax(fax);
        entreprise.setNom(nom);
        entreprise.setNumeroContribuable(numeroContribuable);
        entreprise.setSigle(sigle);
        entreprise.setTel1(tel1);
        entreprise.setTel2(tel2);
        entreprise.setTel3(tel3);
        
        //Enregistrement
        System.out.println("entreprise : "+entreprise.toString());
        entrepriseService.modififer(entreprise);
        System.out.println("entreprise après modification : "+entreprise.toString());
        
        //Préaparation du retour
        System.out.println("C quoi le pob !!!");
        response.sendRedirect("parametres#/entreprise");
    }

}
