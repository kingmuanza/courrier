package net.keama.workspace.servlets.tache;

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
import net.keama.workspace.dao.TacheDAO;
import net.keama.workspace.dao.TacheEmployeDAO;
import net.keama.workspace.dao.UtilisateurDAO;
import net.keama.workspace.entity.Employe;
import net.keama.workspace.entity.Tache;
import net.keama.workspace.entity.TacheEmploye;
import net.keama.workspace.entity.Utilisateur;
import net.keama.workspace.service.EmployeService;

@WebServlet(name = "MesTachesServlet", urlPatterns = {"/MesTachesServlet"})
public class MesTachesServlet extends HttpServlet {

    TacheDAO tacheDAO = new TacheDAO();
    TacheEmployeDAO tacheEmployeDAO = new TacheEmployeDAO();
    EmployeService employeService = new EmployeService();
    UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession httpSession = request.getSession();
        Utilisateur u = null;
        Utilisateur utilisateur = null;
        u = (Utilisateur) httpSession.getAttribute("utilisateur");
        utilisateur = utilisateurDAO.get(u.getIdutilisateur());

        List<Tache> taches = tacheDAO.getall();
        List<Tache> mes_taches = new ArrayList<Tache>();
        
        
        for(Tache t : taches){
            if(t.getEmployeByIdordonnateur().getIdemploye()==utilisateur.getEmploye().getIdemploye()){
                mes_taches.add(t);
            }
        }
        request.setAttribute("attribut", "attribut");
        request.setAttribute("taches", mes_taches);
        
        String id = request.getParameter("id");
        
        if (id != null && !id.isEmpty()) {
            if (!taches.isEmpty()) {
                this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/tache/mes_taches_light.jsp").forward(request, response);
            } else {
                this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/tache/mes_taches.jsp").forward(request, response);
            }
        } else {
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/tache/mes_taches.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Tache arrivé ");
        String action = request.getParameter("action");
        System.out.println("Action : " + action);
        if (action != null && !action.isEmpty() && action.equals("terminer")) {
            String id = request.getParameter("id");
            System.out.println("ID : " + id);
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                Tache tache = tacheDAO.get(i);
                tache.setStatut("Prêt");
                if (tacheDAO.modifier(tache)) {
                    //System.out.println("Tache prêt : " + tache.getRef());
                    response.sendRedirect("tache#/tous");
                }
            }
        }
    }

}
