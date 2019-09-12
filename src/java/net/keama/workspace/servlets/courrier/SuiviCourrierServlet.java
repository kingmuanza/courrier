package net.keama.workspace.servlets.courrier;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.dao.CourrierDAO;
import net.keama.workspace.dao.CourrierProcedureDAO;
import net.keama.workspace.dao.CourrierProcedureEnCoursDAO;
import net.keama.workspace.dao.CourrierTypeDAO;
import net.keama.workspace.entity.Courrier;
import net.keama.workspace.entity.CourrierProcedure;
import net.keama.workspace.entity.CourrierProcedureEnCours;
import net.keama.workspace.entity.CourrierProcedureTache;
import net.keama.workspace.entity.CourrierResponsable;
import net.keama.workspace.entity.CourrierType;
import net.keama.workspace.service.EmployeService;

@WebServlet(name = "SuiviCourrierServlet", urlPatterns = {"/SuiviCourrierServlet"})
public class SuiviCourrierServlet extends HttpServlet {

    CourrierDAO courrierDAO = new CourrierDAO();
    CourrierTypeDAO courrierTypeDAO = new CourrierTypeDAO();
    EmployeService employeService = new EmployeService();
    CourrierProcedureEnCoursDAO courrierProcedureEnCoursDAO = new CourrierProcedureEnCoursDAO();
    CourrierProcedureDAO courrierProcedureDAO = new CourrierProcedureDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession httpSession = request.getSession();

        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            int i = Integer.parseInt(id);
            int a = 0;
            int b = 0;
            int c = 0;
            Courrier courrier = courrierDAO.get(i);
            CourrierType courrierType = courrierTypeDAO.get(courrier.getNature());

            if (courrier.getCourrierProcedureEnCourses() != null && courrier.getCourrierProcedureEnCourses().iterator().hasNext()) {
                CourrierProcedureEnCours courrierProcedureEnCours = courrierProcedureEnCoursDAO.get(courrier.getCourrierProcedureEnCourses().iterator().next().getIdcourrierProcedureEnCours());
                CourrierProcedure courrierProcedure = courrierProcedureDAO.get(courrierProcedureEnCours.getCourrierProcedure().getIdcourrierProcedure());
                int nb_jours = 0 ;
                int k = 1 ;
                Date debutProcedure = new Date();
                Double ratio = new Double(0);
                for(CourrierProcedureTache cpt : courrierProcedure.getCourrierProcedureTaches()){
                    nb_jours += cpt.getDuree();
                    k++;
                    
                }
                request.setAttribute("nb_jours", nb_jours);
                ratio = (Double) (1.0*nb_jours/k) ;
                request.setAttribute("ratio", ratio);
                for (CourrierResponsable cr : courrierProcedureEnCours.getCourrierResponsables()) {
                    if (cr.getStatut() != null && cr.getStatut().equals("Terminé")) {
                        a++;
                    }
                    if (cr.getStatut() != null && cr.getStatut().equals("En cours")) {
                        b++;
                    }
                    
                    c++;
                }
                
                
                request.setAttribute("a", a);
                request.setAttribute("b", b);
                request.setAttribute("c", c);
            }

            request.setAttribute("courrier", courrier);
            request.setAttribute("courrierType", courrierType);
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/courrier/suivi.jsp").forward(request, response);
        } else {
            response.sendRedirect("courrier#/tous");
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

        response.sendRedirect("SuiviCourrierServlet");
    }

}
