package net.keama.workspace.servlets.courrier;

import javax.servlet.http.HttpSession;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.dao.CorrespondantDAO;
import net.keama.workspace.dao.CourrierDAO;
import net.keama.workspace.dao.CourrierFichierDAO;
import net.keama.workspace.dao.CourrierProcedureDAO;
import net.keama.workspace.dao.CourrierProcedureEnCoursDAO;
import net.keama.workspace.dao.CourrierTypeDAO;
import net.keama.workspace.dao.EntreprisePartenaireDAO;
import net.keama.workspace.entity.Correspondant;
import net.keama.workspace.entity.Courrier;
import net.keama.workspace.entity.CourrierFichier;
import net.keama.workspace.entity.CourrierProcedure;
import net.keama.workspace.entity.CourrierType;
import net.keama.workspace.entity.EntreprisePartenaire;
import net.keama.workspace.service.StructureService;

@WebServlet(name = "NouveauCourrierServlet2", urlPatterns = {"/NouveauCourrierServlet2"})
public class NouveauCourrierServlet2 extends HttpServlet {

    StructureService structureService = new StructureService();
    CourrierDAO courrierDAO = new CourrierDAO();
    CourrierTypeDAO courrierTypeDAO = new CourrierTypeDAO();
    CourrierFichierDAO courrierFichierDAO = new CourrierFichierDAO();
    CorrespondantDAO correspondantDAO = new CorrespondantDAO();
    EntreprisePartenaireDAO entreprisePartenaireDAO = new EntreprisePartenaireDAO();
    CourrierProcedureEnCoursDAO courrierProcedureEnCoursDAO = new CourrierProcedureEnCoursDAO();
    CourrierProcedureDAO courrierProcedureDAO = new CourrierProcedureDAO();

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

        List<CourrierType> courrierTypes = courrierTypeDAO.getall();
        List<Correspondant> correspondants = correspondantDAO.getall();
        List<EntreprisePartenaire> entreprises = entreprisePartenaireDAO.getall();

        List<Courrier> courriers = courrierDAO.getall();
        List<Courrier> courriersEntrants = new ArrayList<Courrier>();
        if (courriers != null && !courriers.isEmpty()) {
            for (Courrier c : courriers) {
                if (c.getStatut() != null && c.getStatut().equals("Entrant")) {
                    courriersEntrants.add(c);
                }
            }
        }

        request.setAttribute("courriers", courriersEntrants);
        request.setAttribute("courrierTypes", courrierTypes);
        request.setAttribute("correspondants", correspondants);
        request.setAttribute("entreprises", entreprises);

        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/courrier/nouveau2.jsp").forward(request, response);
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
                if (courrier.getCourrierFichiers() != null && !courrier.getCourrierFichiers().isEmpty()) {
                    for (CourrierFichier cf : courrier.getCourrierFichiers()) {
                        courrierFichierDAO.supprimer(cf);
                    }
                }
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
            String statut = request.getParameter("statut");
            System.out.println("correspondant : " + request.getParameterValues("correspondant"));
            System.out.println("importance : " + request.getParameter("importance"));
            String correspondant = request.getParameterValues("correspondant")[0];
            Correspondant corres = null;
            if (correspondant != null && !correspondant.isEmpty()) {
                correspondant = correspondant.trim();
                int idcorrespondant = Integer.parseInt(correspondant);
                corres = correspondantDAO.get(idcorrespondant);
            }

            String objet = request.getParameter("objet");
            String date_emission = request.getParameter("date_emission");
            Date dateEmission = new Date();
            try {
                dateEmission = sdf.parse(date_emission);
            } catch (ParseException ex) {
                Logger.getLogger(NouveauCourrierServlet2.class.getName()).log(Level.SEVERE, null, ex);
            }
            String nature = request.getParameter("nature");

            String importance = request.getParameter("importance");
            String support = request.getParameter("support");
            String note = request.getParameter("note");
            int importance_en_chiffre = Integer.parseInt(importance);

            Courrier courrier = null;
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                int idcourrier = Integer.parseInt(id);
                courrier = courrierDAO.get(idcourrier);

                courrier.setDateEmission(dateEmission);
                courrier.setDateEnr(new Date());

                courrier.setImportance(1);
                courrier.setNature(nature);
                courrier.setNote(note);
                courrier.setObjet(objet);
                courrier.setStatut("Prêt");
                courrier.setSupport(support);
                courrier.setCorrespondant(corres);
                courrier.setExpediteur(corres.getEntreprisePartenaire().getNom());
                courrier.setNote(note);
                courrier.setSupport(support);
                if (courrierDAO.modifier(courrier)) {
                    String typeCourrier = request.getParameter("typeCourrier");
                    if (typeCourrier != null && !typeCourrier.isEmpty()) {
                        int idtypeCourrier = Integer.parseInt(typeCourrier);
                        CourrierType courrierType = courrierTypeDAO.get(idtypeCourrier);
                        if(courrierType.getCourrierProcedures()!=null && !courrierType.getCourrierProcedures().isEmpty()){
                            CourrierProcedure courrierProcedure = courrierType.getCourrierProcedures().iterator().next();
                            courrierProcedure = courrierProcedureDAO.get(courrierProcedure.getIdcourrierProcedure());
                            if(courrierProcedureEnCoursDAO.creer(courrierProcedure, courrier)){
                                courrier.setStatut("Transmis");
                                courrierDAO.modifier(courrier);
                            }
                        }
                    }
                    
                } else {
                    
                }
            } else {

            }
        }

    }

}
