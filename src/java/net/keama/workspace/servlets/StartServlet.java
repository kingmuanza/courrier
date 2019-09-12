package net.keama.workspace.servlets;

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
import net.keama.workspace.dao.CourrierDAO;
import net.keama.workspace.dao.CourrierResponsableDAO;
import net.keama.workspace.dao.InformationGeneraleDAO;
import net.keama.workspace.dao.ReunionDAO;
import net.keama.workspace.dao.TacheDAO;
import net.keama.workspace.dao.UtilisateurDAO;
import net.keama.workspace.entity.Courrier;
import net.keama.workspace.entity.CourrierResponsable;
import net.keama.workspace.entity.Entreprise;
import net.keama.workspace.entity.InformationGenerale;
import net.keama.workspace.entity.Reunion;
import net.keama.workspace.entity.ReunionEmploye;
import net.keama.workspace.entity.Tache;
import net.keama.workspace.entity.Utilisateur;
import net.keama.workspace.service.EmployeService;
import net.keama.workspace.service.EntrepriseService;
import net.keama.workspace.service.UtilisateurService;

@WebServlet(name = "StartServlet", urlPatterns = {"/start"})
public class StartServlet extends HttpServlet {

    UtilisateurService utilisateurService = new UtilisateurService();
    EntrepriseService entrepriseService = new EntrepriseService();
    ReunionDAO reunionDAO = new ReunionDAO();
    EmployeService employeService = new EmployeService();
    UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
    CourrierDAO courrierDAO = new CourrierDAO();
    TacheDAO tacheDAO = new TacheDAO();
    InformationGeneraleDAO informationGeneraleDAO = new InformationGeneraleDAO();
    CourrierResponsableDAO courrierResponsableDAO = new CourrierResponsableDAO();

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
            int idemploye = 0 ;
            idemploye = utilisateur.getEmploye().getIdemploye();
            
            //Informations générales
            List<InformationGenerale> infos = informationGeneraleDAO.getMyInfos(idemploye);
            request.setAttribute("infos", infos);
            //Réunions a venir
            List<Reunion> reunionAvenir = reunionDAO.getMesReunionsAVenir(idemploye);
            request.setAttribute("reunions", reunionAvenir);
            //Réunions aujourd'hui
            List<Reunion> reunionODay = reunionDAO.getMesReunionsAujourdhui(idemploye);
            request.setAttribute("reunionODay", reunionODay);
            //Courriers à traiter aujourd'hui
            List<Courrier> courriers = courrierDAO.getCourriersOday(idemploye);
            request.setAttribute("courriers", courriers);
            //Tâche à traiter aujourd'hui
            List<Tache> taches = tacheDAO.getallMine(idemploye);
            request.setAttribute("taches", taches);
            //Correstion des informations
            for(CourrierResponsable cr : courrierResponsableDAO.getEnCours(idemploye)){
                if(cr.getDateLimite()!=null && cr.getDateLimite().before(new Date())){
                    cr.setStatut("En retard");
                    courrierResponsableDAO.modifier(cr);
                }
            }
            //Evaluation du personnel
            int a , b, c ;
            a = courrierResponsableDAO.getTerminees(idemploye).size();
            b = courrierResponsableDAO.getEnCours(idemploye).size();
            c = courrierResponsableDAO.getEnRetard(idemploye).size();
            request.setAttribute("a", a);
            request.setAttribute("b", b);
            request.setAttribute("c", c);
            

            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/start.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.htm");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String login = request.getParameter("login");
        String passe = request.getParameter("passe");

        HttpSession httpSession = request.getSession();

        Utilisateur utilisateur = null;
        utilisateur = utilisateurService.get(login, passe);
        if (utilisateur != null) {
            int id = utilisateur.getIdutilisateur();
            response.getWriter().print("IDUtilisateur : " + id);
            utilisateur = utilisateurService.get(id);
            httpSession.setAttribute("utilisateur", utilisateur);

        } else {
            response.getWriter().print("null");
        }

    }

}
