package net.keama.workspace.servlets;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.entity.Entreprise;
import net.keama.workspace.entity.Utilisateur;
import net.keama.workspace.service.EntrepriseService;
import net.keama.workspace.service.UtilisateurService;

@WebServlet(name="SuggestionServlet", urlPatterns={"/suggestion"})
public class SuggestionServlet extends HttpServlet {

    UtilisateurService utilisateurService = new UtilisateurService();
    EntrepriseService entrepriseService = new EntrepriseService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = null;

        utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        if (utilisateur != null) {
            Entreprise entreprise = entrepriseService.get(1);
            request.setAttribute("entreprise", entreprise);
            Utilisateur u = utilisateurService.get(utilisateur.getIdutilisateur());
            request.setAttribute("utilisateur", u);
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/suggestion.jsp").forward(request, response);
        }else{
            response.sendRedirect("index.htm");
        }
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String id = request.getParameter("id");
        String parametre = request.getParameter("parametre");

        response.sendRedirect("courrier");
    }


}
