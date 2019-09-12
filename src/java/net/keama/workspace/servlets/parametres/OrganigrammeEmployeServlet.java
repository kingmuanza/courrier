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
import net.keama.workspace.entity.Poste;

@WebServlet(name="OrganigrammeEmployeServlet", urlPatterns={"/organigrammeEmploye"})
public class OrganigrammeEmployeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("id");
        if(id!=null && !id.isEmpty()){
            int i = Integer.parseInt(id);
            PosteDAO posteDAO = new PosteDAO();
            Poste poste = posteDAO.get(i);
            Poste parent = posteDAO.get(poste.getPoste().getIdposte());
            parent.setPoste(null);
            request.setAttribute("postes", posteDAO.getFilsAll(i));
            request.setAttribute("poste", parent);
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/parametres/organigrammeEmploye.jsp").forward( request, response );
        }else{
            PosteDAO posteDAO = new PosteDAO();
            request.setAttribute("postes", posteDAO.getFilsAll(1));
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/parametres/organigrammeEmploye.jsp").forward( request, response );
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
        
        response.sendRedirect("OrganigrammeEmployeServlet");
    }


}
