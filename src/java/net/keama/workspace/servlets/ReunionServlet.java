package net.keama.workspace.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.keama.workspace.dao.EmployeDAO;
import net.keama.workspace.dao.ReunionDAO;
import net.keama.workspace.dao.SalleReunionDAO;
import net.keama.workspace.entity.Employe;
import net.keama.workspace.entity.Entreprise;
import net.keama.workspace.entity.Reunion;
import net.keama.workspace.entity.ReunionEmploye;
import net.keama.workspace.entity.SalleReunion;
import net.keama.workspace.entity.Utilisateur;
import net.keama.workspace.service.EntrepriseService;
import net.keama.workspace.service.UtilisateurService;

@WebServlet(name = "ReunionServlet", urlPatterns = {"/reunion"})
public class ReunionServlet extends HttpServlet {

    UtilisateurService utilisateurService = new UtilisateurService();
    EntrepriseService entrepriseService = new EntrepriseService();
    EmployeDAO employeDAO = new EmployeDAO();
    SalleReunionDAO salleReunionDAO = new SalleReunionDAO();
    ReunionDAO reunionDAO = new ReunionDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = null;

        utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        
        if (utilisateur != null) {
            utilisateur = utilisateurService.get(utilisateur.getIdutilisateur());
            Entreprise entreprise = entrepriseService.get(1);
            request.setAttribute("entreprise", entreprise);
            Utilisateur u = utilisateur;
            request.setAttribute("utilisateur", u);
            
            List<Employe> employes = employeDAO.getall();
            request.setAttribute("employes", employes);
            
            List<SalleReunion> salleReunions = salleReunionDAO.getall();
            request.setAttribute("salleReunions", salleReunions);
            
            //Réunions à venir
            List<Reunion> reunions = reunionDAO.getall();
            List<Reunion> reunionAvenir = new ArrayList<Reunion>();
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
            request.setAttribute("reunionAvenir", reunionAvenir.size());
            //Fin des Réunions à venir
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/reunion.jsp").forward(request, response);
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
