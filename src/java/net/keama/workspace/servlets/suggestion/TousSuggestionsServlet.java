package net.keama.workspace.servlets.suggestion;
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
import net.keama.workspace.dao.SuggestionDAO;
import net.keama.workspace.dao.UtilisateurDAO;
import net.keama.workspace.entity.Suggestion;
import net.keama.workspace.entity.Utilisateur;

@WebServlet(name="TousSuggestionsServlet", urlPatterns={"/TousSuggestionsServlet"})
public class TousSuggestionsServlet extends HttpServlet {

    SuggestionDAO suggestionDAO = new SuggestionDAO();
    UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        HttpSession httpSession = request.getSession();
        Utilisateur u = null;
        Utilisateur utilisateur = null;
        u = (Utilisateur) httpSession.getAttribute("utilisateur");
        utilisateur = utilisateurDAO.get(u.getIdutilisateur());
        
        
        List<Suggestion> suggestions = suggestionDAO.getall();
        
        request.setAttribute("suggestions", suggestions);

        //Récupération des notification
        if(httpSession.getAttribute("creation")!=null){
            request.setAttribute("creation", (boolean) httpSession.getAttribute("creation"));
            httpSession.removeAttribute("creation");
        }
        if(httpSession.getAttribute("modification")!=null){
            request.setAttribute("modification", (boolean) httpSession.getAttribute("modification"));
            httpSession.removeAttribute("modification");
        }
        if(httpSession.getAttribute("suppression")!=null){
            request.setAttribute("suppression", (boolean) httpSession.getAttribute("suppression"));
            httpSession.removeAttribute("suppression");
        }

        String id = request.getParameter("id");
        if(id!=null && !id.isEmpty()){
            if(!suggestions.isEmpty()){
                this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/suggestion/tous_light.jsp").forward(request, response);
            }else{
                this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/suggestion/tous.jsp").forward(request, response);
            }
        }else{
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/suggestion/tous.jsp").forward(request, response);
        }
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        HttpSession httpSession = request.getSession();
        
        //Récupération de l'action
        String action = request.getParameter("action");
        if (action!=null && !action.isEmpty() && action.equals("supprimer")) {
            String id = request.getParameter("id");
            if(id!=null && !id.isEmpty()){
                if(true){
                    httpSession.setAttribute("suppression", true);
                }else{
                    httpSession.setAttribute("suppression", false);
                }
            }
        }else{
            String id = request.getParameter("id");
            //Paramètres

            //Fin Paramètres

            if(id!=null && !id.isEmpty()){
                if(true){
                    httpSession.setAttribute("modification", true);
                }else{
                    httpSession.setAttribute("modification", false);
                }
            }else{
                if(true){
                    httpSession.setAttribute("creation", true);
                }else{
                    httpSession.setAttribute("creation", false);
                }
            }
        }
        
        response.sendRedirect("TousInformationsServlet");
    }


}
