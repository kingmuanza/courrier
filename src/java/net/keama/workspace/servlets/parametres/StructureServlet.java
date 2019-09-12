package net.keama.workspace.servlets.parametres;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.dao.SiteDAO;
import net.keama.workspace.dao.StructureDAO;
import net.keama.workspace.entity.Entreprise;
import net.keama.workspace.entity.Site;
import net.keama.workspace.entity.Structure;
import net.keama.workspace.service.EntrepriseService;

@WebServlet(name = "StructureServlet", urlPatterns = {"/StructureServlet"})
public class StructureServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntrepriseService entrepriseService = new EntrepriseService();
        StructureDAO structureDAO = new StructureDAO();
        SiteDAO siteDAO = new SiteDAO();
        List<Structure> structures = new ArrayList<Structure>();
        structures = structureDAO.getFilsAll(1);
        Entreprise entreprise = entrepriseService.get(1);
        request.setAttribute("entreprise", entreprise);
        request.setAttribute("structures", structures);
        request.setAttribute("sites", siteDAO.getall());

        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/parametres/structure.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntrepriseService entrepriseService = new EntrepriseService();
        StructureDAO structureDAO = new StructureDAO();
        SiteDAO siteDAO = new SiteDAO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        HttpSession httpSession = request.getSession();

        //Récupération de l'action
        String action = request.getParameter("action");
        if (action != null && !action.isEmpty() && action.equals("supprimer")) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                Structure structure = structureDAO.get(i);
                try {
                    if (structureDAO.supprimer(structure)) {
                        httpSession.setAttribute("suppression", true);
                    }
                } catch (Exception e) {

                }

            }
        } else {
            String id = request.getParameter("id");
            //Paramètres
            String nom = request.getParameter("nom");
            String sigle = request.getParameter("sigle");
            String parent = request.getParameter("parent");
            int idparent = 0;
            Structure structureParente = null;
            if (parent != null && !parent.isEmpty()) {
                idparent = Integer.parseInt(parent);
                structureParente = structureDAO.get(idparent);
            }
            String siteStructure = request.getParameter("site");
            int idsite = 0;
            Site site = null;
            if (siteStructure != null && !siteStructure.isEmpty()) {
                idsite = Integer.parseInt(siteStructure);
                site = siteDAO.get(idsite);
            }

            //Fin Paramètres
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                Structure structure = structureDAO.get(i);
                structure.setNom(nom);
                structure.setSigle(sigle);
                structure.setStructure(structureParente);
                structure.setSite(site);
                if (structureDAO.modifier(structure)) {
                    httpSession.setAttribute("modification", true);
                } else {
                    httpSession.setAttribute("modification", false);
                }
            } else {
                Structure structure = new Structure();
                structure.setNom(nom);
                structure.setSigle(sigle);
                structure.setStructure(structureParente);
                structure.setSite(site);
                if (structureDAO.ajouter(structure)) {
                    httpSession.setAttribute("creation", true);
                } else {
                    httpSession.setAttribute("creation", false);
                }
            }
        }

        response.sendRedirect("parametres#/structure");
    }

}
