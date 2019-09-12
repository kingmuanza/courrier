package net.keama.workspace.servlets.parametres;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.dao.PosteDAO;
import net.keama.workspace.dao.SiteDAO;
import net.keama.workspace.dao.StructureDAO;
import net.keama.workspace.entity.Entreprise;
import net.keama.workspace.entity.Poste;
import net.keama.workspace.entity.Structure;
import net.keama.workspace.service.EntrepriseService;

@WebServlet(name="PosteServlet", urlPatterns={"/PosteServlet"})
public class PosteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        PosteDAO posteDAO = new PosteDAO();
        SiteDAO siteDAO = new SiteDAO();
        StructureDAO structureDAO = new StructureDAO();
        EntrepriseService entrepriseService = new EntrepriseService();
        
        request.setAttribute("postes", posteDAO.getFilsAll(1));
        request.setAttribute("sites", siteDAO.getall());
        request.setAttribute("structures", structureDAO.getFilsAll(1));
        request.setAttribute("entreprise", entrepriseService.get(1));
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/parametres/poste.jsp").forward( request, response );
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntrepriseService entrepriseService = new EntrepriseService();
        StructureDAO structureDAO = new StructureDAO();
        PosteDAO posteDAO = new PosteDAO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        HttpSession httpSession = request.getSession();

        //Récupération de l'action
        String action = request.getParameter("action");
        if (action != null && !action.isEmpty() && action.equals("supprimer")) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                Poste poste = posteDAO.get(i);
                try {
                    if (posteDAO.supprimer(poste)) {
                        httpSession.setAttribute("suppression", true);
                    }
                } catch (Exception e) {

                }

            }
        } else {
            String id = request.getParameter("id");
            //Paramètres
            String libelle = request.getParameter("nom");
            String sigle = request.getParameter("sigle");
            String parent = request.getParameter("parent");
            int idparent = 0;
            Poste posteParent = null;
            if (parent != null && !parent.isEmpty()) {
                idparent = Integer.parseInt(parent);
                posteParent = posteDAO.get(idparent);
            }
            String idstructure = request.getParameter("structure");
            int i = 0;
            Structure structure = null;
            if (idstructure != null && !idstructure.isEmpty()) {
                i = Integer.parseInt(idstructure);
                structure = structureDAO.get(i);
            }

            //Fin Paramètres
            if (id != null && !id.isEmpty()) {
                int j = Integer.parseInt(id);
                Poste poste = posteDAO.get(j);
                poste.setLibelle(libelle);
                poste.setSigle(sigle);
                poste.setStructure(structure);
                poste.setPoste(posteParent);
                if (posteDAO.modifier(poste)) {
                    httpSession.setAttribute("modification", true);
                } else {
                    httpSession.setAttribute("modification", false);
                }
            } else {
                Poste poste = new Poste();
                poste.setLibelle(libelle);
                poste.setSigle(sigle);
                poste.setStructure(structure);
                poste.setPoste(posteParent);
                
                if (posteDAO.ajouter(poste)) {
                    httpSession.setAttribute("creation", true);
                } else {
                    httpSession.setAttribute("creation", false);
                }
            }
        }

        response.sendRedirect("parametres#/poste");
    }


}
