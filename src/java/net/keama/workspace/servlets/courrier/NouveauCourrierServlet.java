package net.keama.workspace.servlets.courrier;

import java.io.IOException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import net.keama.workspace.dao.CourrierDAO;
import net.keama.workspace.entity.Courrier;
import net.keama.workspace.entity.Structure;
import net.keama.workspace.service.StructureService;

@WebServlet(name = "NouveauCourrierServlet", urlPatterns = {"/nouveauCourrier"})
public class NouveauCourrierServlet extends HttpServlet {

    StructureService structureService = new StructureService();
    CourrierDAO courrierDAO = new CourrierDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession httpSession = request.getSession();

        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            int idcourrier = Integer.parseInt(id);
            Courrier courrier = courrierDAO.get(idcourrier);
            request.setAttribute("courrier", courrier);
        }

        List<Structure> structures = structureService.getAll();

        List<Courrier> courriers = courrierDAO.getall();
        List<Courrier> courriersEntrants = new ArrayList<Courrier>();
        if (courriers != null && !courriers.isEmpty()) {
            for (Courrier c : courriers) {
                if(c.getStatut()!=null && c.getStatut().equals("Entrant")){
                    courriersEntrants.add(c);
                }
            }
        }

        request.setAttribute("structures", structures);
        request.setAttribute("courriers", courriersEntrants);

        //Récupération des notification
        if (httpSession.getAttribute("creation") != null) {
            request.setAttribute("creation", (boolean) httpSession.getAttribute("creation"));
            httpSession.removeAttribute("creation");
        }
        if (httpSession.getAttribute("modification") != null) {
            request.setAttribute("modification", (boolean) httpSession.getAttribute("modification"));
            httpSession.removeAttribute("modification");
        }
        if (httpSession.getAttribute("suppression") != null) {
            request.setAttribute("suppression", (boolean) httpSession.getAttribute("suppression"));
            httpSession.removeAttribute("suppression");
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/courrier/nouveau.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        HttpSession httpSession = request.getSession();

        //Récupération de l'action
        String action = request.getParameter("action");
        if (action != null && !action.isEmpty() && action.equals("supprimer")) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                int idcourrier = Integer.parseInt(id);
                Courrier courrier = courrierDAO.get(idcourrier);
                if (courrierDAO.supprimer(courrier)) {
                    System.out.println("Le courrier a été supprimé avec succès");
                    httpSession.setAttribute("suppression", true);
                } else {
                    System.out.println("Erreur de suppression du courrier");
                    httpSession.setAttribute("suppression", false);
                }
            }
            response.sendRedirect("courrier#/tous");

        } else {
            //Récupération de l'ID
            String id = request.getParameter("id");
            //Paramètres
            String ref = request.getParameter("ref");
            System.out.println("ref : " + ref);
            String service = request.getParameter("service");
            System.out.println("service : " + service);
            String expediteur = request.getParameter("expediteur");
            System.out.println("expediteur : " + expediteur);
            String date_emission = request.getParameter("date_emission");
            System.out.println("date_emission : " + date_emission);
            String objet = request.getParameter("objet");
            System.out.println("objet : " + objet);
            String adresse = request.getParameter("adresse");
            System.out.println("adresse : " + adresse);
            String statut = request.getParameter("statut");
            System.out.println("statut : " + statut);
            String nature = request.getParameter("nature");
            System.out.println("nature : " + nature);
            String importance = request.getParameter("importance");
            System.out.println("importance : " + importance);
            String support = request.getParameter("support");
            System.out.println("support : " + support);
            String note = request.getParameter("note");
            System.out.println("note : " + note);

            //Conversion des types 
            Date dateEmission = null;
            int idstructure = Integer.parseInt(service);
            int importance_en_chiffre = Integer.parseInt(importance);
            Structure structure = structureService.get(idstructure);
            try {
                dateEmission = sdf.parse(date_emission);
                System.out.println("Date : " + dateEmission);
            } catch (ParseException ex) {

                dateEmission = new Date();
            }
            System.out.println("Date convertie : " + dateEmission);

            //Création de l'objet et enregistrement
            Courrier courrier = null;
            if (id != null && !id.isEmpty()) {
                int idcourrier = Integer.parseInt(id);
                courrier = courrierDAO.get(idcourrier);
                courrier.setAdresseExpediteur(adresse);
                courrier.setDateEmission(dateEmission);
                courrier.setDateEnr(new Date());
                courrier.setExpediteur(expediteur);
                courrier.setImportance(importance_en_chiffre);
                courrier.setNature(nature);
                courrier.setNote(note);
                courrier.setObjet(objet);
                courrier.setStatut("Prêt");
                courrier.setStructure(structure);
                courrier.setSupport(support);
                if (courrierDAO.modifier(courrier)) {
                    httpSession.setAttribute("modification", true);
                } else {
                    httpSession.setAttribute("modification", false);
                }
            } else {
                
            }
            response.sendRedirect("courrier#/id/" + courrier.getIdcourrier());
        }

    }

}
