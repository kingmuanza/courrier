package net.keama.workspace.servlets.courrier;
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
import net.keama.workspace.dao.CourrierDAO;
import net.keama.workspace.entity.Courrier;
import net.keama.workspace.entity.Structure;
import net.keama.workspace.service.StructureService;

@WebServlet(name="VoirCourrierServlet", urlPatterns={"/VoirCourrierServlet"})
public class VoirCourrierServlet extends HttpServlet {

    StructureService structureService = new StructureService();
    CourrierDAO courrierDAO = new CourrierDAO();

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

        List<Structure> structures = structureService.getAll();

        List<Courrier> courriers = courrierDAO.getall();
        List<Courrier> courriersEntrants = new ArrayList<Courrier>();
        if (courriers != null && !courriers.isEmpty()) {
            for (Courrier c : courriers) {
                if(c.getStatut()!=null && c.getStatut().equals("Entrant")){
                    courriersEntrants.add(c);
                }
            }
        }

        request.setAttribute("structures", structures);
        request.setAttribute("courriers", courriersEntrants);

        //Récupération des notification
        if (httpSession.getAttribute("creation") != null) {
            request.setAttribute("creation", (boolean) httpSession.getAttribute("creation"));
            httpSession.removeAttribute("creation");
        }
        if (httpSession.getAttribute("modification") != null) {
            request.setAttribute("modification", (boolean) httpSession.getAttribute("modification"));
            httpSession.removeAttribute("modification");
        }
        if (httpSession.getAttribute("suppression") != null) {
            request.setAttribute("suppression", (boolean) httpSession.getAttribute("suppression"));
            httpSession.removeAttribute("suppression");
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/courrier/voir.jsp").forward(request, response);
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
        
        response.sendRedirect("VoirCourrierServlet");
    }


}
