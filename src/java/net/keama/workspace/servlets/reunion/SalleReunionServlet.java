package net.keama.workspace.servlets.reunion;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.dao.SalleReunionDAO;
import net.keama.workspace.entity.SalleReunion;

@WebServlet(name="SalleReunionServlet", urlPatterns={"/SalleReunionServlet"})
public class SalleReunionServlet extends HttpServlet {

    SalleReunionDAO salleReunionDAO = new SalleReunionDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        HttpSession httpSession = request.getSession();
        
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            int identity = Integer.parseInt(id);
            SalleReunion salle = salleReunionDAO.get(identity);
            request.setAttribute("salle", salle);
        }
        List<SalleReunion> salles = salleReunionDAO.getall();
        request.setAttribute("salles", salles);

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

        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/reunion/salle.jsp").forward( request, response );
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
            String nom = request.getParameter("nom");
            String capacite = request.getParameter("capacite");
            int c = Integer.parseInt(capacite);
            String statut = request.getParameter("statut");
            //Fin Paramètres

            if(id!=null && !id.isEmpty()){
                int i = Integer.parseInt(id);
                SalleReunion salleReunion = salleReunionDAO.get(i);
                salleReunion.setCapacite(c);
                salleReunion.setNom(nom);
                salleReunion.setStatut(statut);
                if(salleReunionDAO.modifier(salleReunion)){
                    httpSession.setAttribute("modification", true);
                }else{
                    httpSession.setAttribute("modification", false);
                }
            }else{
                SalleReunion salleReunion = new SalleReunion(nom, c, statut, null);
                if(salleReunionDAO.ajouter(salleReunion)){
                    httpSession.setAttribute("creation", true);
                }else{
                    httpSession.setAttribute("creation", false);
                }
            }
        }
        
        response.sendRedirect("reunion#/salle");
    }


}
