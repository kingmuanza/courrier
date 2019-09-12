package net.keama.workspace.servlets.reunion;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import net.keama.workspace.dao.ReunionDAO;
import net.keama.workspace.dao.UtilisateurDAO;
import net.keama.workspace.entity.Employe;
import net.keama.workspace.entity.Reunion;
import net.keama.workspace.entity.ReunionEmploye;
import net.keama.workspace.entity.Utilisateur;
import net.keama.workspace.service.EmployeService;

@WebServlet(name="AfficherReunionServlet", urlPatterns={"/AfficherReunionServlet"})
public class AfficherReunionServlet extends HttpServlet {

    ReunionDAO reunionDAO = new ReunionDAO();
    EmployeService employeService = new EmployeService();
    UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = null;
        Utilisateur u = null;
        String id = request.getParameter("id");
        if(id!=null && !id.isEmpty()){
            int i = Integer.parseInt(id);
            Reunion reunion = reunionDAO.get(i);
            request.setAttribute("reunion", reunion);
        }
        List<Reunion> reunions = reunionDAO.getall();
        List<Reunion> reunionAvenir = new ArrayList<Reunion>();
        u = (Utilisateur) httpSession.getAttribute("utilisateur");
        utilisateur = utilisateurDAO.get(u.getIdutilisateur());
        
        
        Date oday = new Date();
        if (reunions != null && !reunions.isEmpty() && utilisateur != null) {
            for (Reunion c : reunions) {
                if (utilisateur.getEmploye().getIdemploye() == c.getEmploye().getIdemploye() && c.getDateDebut().after(oday)) {
                    reunionAvenir.add(c);
                }
                if (utilisateur.getEmploye().getIdemploye() != c.getEmploye().getIdemploye() && c.getDateDebut().after(oday) && c.getReunionEmployes() != null) {
                    for (ReunionEmploye re : c.getReunionEmployes()) {
                        if (re.getEmploye().getIdemploye() == utilisateur.getEmploye().getIdemploye()) {
                            reunionAvenir.add(c);
                        }
                    }
                }
            }
        }
        List<Employe> employes = employeService.getAll();
        request.setAttribute("reunions", reunionAvenir);
        
        //request.setAttribute("reunions", reunions);
        request.setAttribute("employes", employes);

        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/reunion/afficher_1.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Reunion arrivé ");
        String action = request.getParameter("action");
        System.out.println("Action : " + action);
        if (action != null && !action.isEmpty() && action.equals("terminer")) {
            String id = request.getParameter("id");
            System.out.println("ID : " + id);
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                Reunion reunion = reunionDAO.get(i);
                reunion.setStatut("Prêt");
                if (reunionDAO.modifier(reunion)) {
                    System.out.println("Reunion prêt : " + reunion.getRef());
                    response.sendRedirect("reunion#/tous");
                }
            }
        }
        if (action != null && !action.isEmpty() && action.equals("archiver")) {
            String id = request.getParameter("id");
            System.out.println("ID : " + id);
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                Reunion reunion = reunionDAO.get(i);
                reunion.setStatut("Archivé");
                if (reunionDAO.modifier(reunion)) {
                    System.out.println("Reunion prêt : " + reunion.getRef());
                    response.sendRedirect("reunion#/mes/light");
                }
            }
        }
    }


}
