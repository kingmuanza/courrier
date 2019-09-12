package net.keama.workspace.servlets.courrier;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.dao.CorrespondantDAO;
import net.keama.workspace.dao.CourrierDAO;
import net.keama.workspace.dao.EntreprisePartenaireDAO;
import net.keama.workspace.entity.Correspondant;
import net.keama.workspace.entity.Courrier;
import net.keama.workspace.entity.EntreprisePartenaire;

@WebServlet(name = "CorrespondantServlet", urlPatterns = {"/CorrespondantServlet"})
public class CorrespondantServlet extends HttpServlet {

    CourrierDAO courrierDAO = new CourrierDAO();
    CorrespondantDAO correspondantDAO = new CorrespondantDAO();
    EntreprisePartenaireDAO entreprisePartenaireDAO = new EntreprisePartenaireDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession httpSession = request.getSession();

        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            int i = Integer.parseInt(id);
            EntreprisePartenaire entreprise = entreprisePartenaireDAO.get(i);
            request.setAttribute("entreprise", entreprise);
            List<Courrier> courriers = courrierDAO.getall(entreprise);
            request.setAttribute("courriers", courriers);
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/courrier/correspondant_id.jsp").forward(request, response);

        } else {

            List<EntreprisePartenaire> entreprises = entreprisePartenaireDAO.getall();
            request.setAttribute("entreprises", entreprises);

            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/courrier/correspondant.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        HttpSession httpSession = request.getSession();

        //Récupération de l'action
        String action = request.getParameter("action");
        if (action != null && !action.isEmpty() && action.equals("supprimer")) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                if (true) {
                    httpSession.setAttribute("suppression", true);
                } else {
                    httpSession.setAttribute("suppression", false);
                }
            }
        } else {
            String id = request.getParameter("id");
            String entreprise = request.getParameter("entreprise");
            EntreprisePartenaire entreprisePartenaire = null;
            if (entreprise != null && !entreprise.isEmpty()) {
                entreprise = entreprise.trim();
                int i = Integer.parseInt(entreprise);
                entreprisePartenaire = entreprisePartenaireDAO.get(i);
            }
            //Fin Paramètres
            //Paramètres Correspondant
            String nom_complet = request.getParameter("nom");
            String poste = request.getParameter("poste");
            String tel = request.getParameter("tel");
            String email = request.getParameter("email");
            Correspondant correspondant = null;
            //Fin Paramètres
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                correspondant = correspondantDAO.get(i);
                correspondant.setEmail(email);
                correspondant.setEntreprisePartenaire(entreprisePartenaire);
                correspondant.setNomComplet(nom_complet);
                correspondant.setPoste(poste);
                if (true) {
                    httpSession.setAttribute("modification", true);
                } else {
                    httpSession.setAttribute("modification", false);
                }
            } else {
                correspondant = new Correspondant();
                correspondant.setEmail(email);
                correspondant.setEntreprisePartenaire(entreprisePartenaire);
                correspondant.setNomComplet(nom_complet);
                correspondant.setPoste(poste);
                if (correspondantDAO.ajouter(correspondant)) {
                    httpSession.setAttribute("creation", true);
                    try (PrintWriter out = response.getWriter()) {
                        out.println(correspondant.getIdcorrespondant());
                        System.out.println("ID du correspondant : " + correspondant.getIdcorrespondant());
                    }
                } else {
                    httpSession.setAttribute("creation", false);
                }
            }
        }
    }
}
