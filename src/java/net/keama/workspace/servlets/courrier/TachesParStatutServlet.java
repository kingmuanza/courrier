package net.keama.workspace.servlets.courrier;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.dao.CourrierResponsableDAO;
import net.keama.workspace.entity.CourrierResponsable;
import net.keama.workspace.entity.Utilisateur;
import net.keama.workspace.service.UtilisateurService;

@WebServlet(name = "TachesParStatutServlet", urlPatterns = {"/TachesParStatutServlet"})
public class TachesParStatutServlet extends HttpServlet {

    UtilisateurService utilisateurService = new UtilisateurService();
    CourrierResponsableDAO courrierResponsableDAO = new CourrierResponsableDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = null;
        utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        utilisateur = utilisateurService.get(utilisateur.getIdutilisateur());
        int idemploye = 0;
        idemploye = utilisateur.getEmploye().getIdemploye();

        List<CourrierResponsable> courriersResponsables = null;

        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            if (id.equals("0")) {
                courriersResponsables = courrierResponsableDAO.getTerminees(idemploye);
                request.setAttribute("courriersResponsables", courriersResponsables);
                request.setAttribute("titre", "Tâches Terminées");
                
                this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/courrier/tache_terminees.jsp").forward(request, response);
            }
            if (id.equals("1")) {
                courriersResponsables = courrierResponsableDAO.getEnCours(idemploye);
                request.setAttribute("courriersResponsables", courriersResponsables);
                request.setAttribute("titre", "Tâches en cours");
                
                this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/courrier/tache_terminees.jsp").forward(request, response);
            }
            if (id.equals("2")) {
                courriersResponsables = courrierResponsableDAO.getEnRetard(idemploye);
                request.setAttribute("courriersResponsables", courriersResponsables);
                request.setAttribute("titre", "Tâches en retard");
                
                this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/courrier/tache_terminees.jsp").forward(request, response);
            }

            
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
            //Paramètres

            //Fin Paramètres
            if (id != null && !id.isEmpty()) {
                if (true) {
                    httpSession.setAttribute("modification", true);
                } else {
                    httpSession.setAttribute("modification", false);
                }
            } else if (true) {
                httpSession.setAttribute("creation", true);
            } else {
                httpSession.setAttribute("creation", false);
            }
        }

        response.sendRedirect("TachesParStatutServlet");
    }

}
