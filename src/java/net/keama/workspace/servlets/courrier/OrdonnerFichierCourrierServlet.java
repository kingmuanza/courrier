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
import net.keama.workspace.dao.CourrierDAO;
import net.keama.workspace.dao.CourrierFichierDAO;
import net.keama.workspace.entity.Courrier;
import net.keama.workspace.entity.CourrierFichier;

@WebServlet(name="OrdonnerFichierCourrierServlet", urlPatterns={"/OrdonnerFichierCourrierServlet"})
public class OrdonnerFichierCourrierServlet extends HttpServlet {

    CourrierDAO courrierDAO = new CourrierDAO();
    CourrierFichierDAO courrierFichierDAO = new CourrierFichierDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        HttpSession httpSession = request.getSession();
        
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            int identity = Integer.parseInt(id);
            Courrier courrier = courrierDAO.get(identity);
            List<CourrierFichier> courrierFichiers = courrierFichierDAO.getallbyCourrier(identity);
            
            request.setAttribute("courrierFichiers", courrierFichiers);
            request.setAttribute("courrier", courrier);
        }
        request.setAttribute("attributs", "attributs");

        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/courrier/ordonner_fichier_courrier.jsp").forward( request, response );
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
        
        response.sendRedirect("OrdonnerFichierCourrierServlet");
    }


}
