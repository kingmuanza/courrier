package net.keama.workspace.servlets.tache;

import javax.servlet.http.HttpSession;
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
import net.keama.workspace.dao.EmployeDAO;
import net.keama.workspace.dao.PosteDAO;
import net.keama.workspace.dao.TacheDAO;
import net.keama.workspace.dao.UtilisateurDAO;
import net.keama.workspace.entity.Employe;
import net.keama.workspace.entity.Poste;
import net.keama.workspace.entity.Tache;
import net.keama.workspace.entity.TacheEmploye;
import net.keama.workspace.entity.Utilisateur;

@WebServlet(name = "RoueTacheServlet", urlPatterns = {"/RoueTacheServlet"})
public class RoueTacheServlet extends HttpServlet {

    EmployeDAO employeDAO = new EmployeDAO();
    TacheDAO tacheDAO = new TacheDAO();
    UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PosteDAO posteDAO = new PosteDAO();
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = null;
        Utilisateur u = null;
        u = (Utilisateur) httpSession.getAttribute("utilisateur");
        utilisateur = utilisateurDAO.get(u.getIdutilisateur());
        
        List<Tache> taches = tacheDAO.getall();
        List<Tache> taches_enretard = new ArrayList<Tache>();
        List<Tache> taches_encours = new ArrayList<Tache>();
        List<Tache> taches_terminees = new ArrayList<Tache>();
        List<Employe> employes = employeDAO.getall();
        
        for (Tache t : taches) {
            if (t.getDateFin().before(new Date()) && t.getStatut() != null && !t.getStatut().equals("Terminé")) {
                taches_enretard.add(t);
            }
            if (t.getStatut() != null && t.getStatut().equals("Terminé")) {
                taches_terminees.add(t);
            }
            if (t.getDateFin().after(new Date()) && t.getDateDebut().before(new Date())) {
                taches_encours.add(t);
            }
        }

        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            taches_enretard = new ArrayList<Tache>();
            taches_encours = new ArrayList<Tache>();
            taches_terminees = new ArrayList<Tache>();
            int i = Integer.parseInt(id);
            Employe employe = employeDAO.get(i);
            for (Tache t : taches) {
                if (t.getTacheEmployes() != null) {
                    for (TacheEmploye te : t.getTacheEmployes()) {
                        if (te.getEmploye().getIdemploye()==employe.getIdemploye() && t.getDateFin().before(new Date()) && t.getStatut() != null && !t.getStatut().equals("Terminé")) {
                            taches_enretard.add(t);
                        }
                        if (te.getEmploye().getIdemploye()==employe.getIdemploye() && t.getStatut() != null && t.getStatut().equals("Terminé")) {
                            taches_terminees.add(t);
                        }
                        if (te.getEmploye().getIdemploye()==employe.getIdemploye() && t.getDateFin().after(new Date()) && t.getDateDebut().before(new Date())) {
                            taches_encours.add(t);
                        }
                    }
                }
            }
            request.setAttribute("employe", employe);
        }
        request.setAttribute("retard", taches_enretard);
        request.setAttribute("cours", taches_encours);
        request.setAttribute("terminees", taches_terminees);
        request.setAttribute("employes", employes);
        
        request.setAttribute("postes", posteDAO.getFilsAll(utilisateur.getEmploye().getPoste().getIdposte()));
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/tache/roue.jsp").forward(request, response);
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

        response.sendRedirect("RoueTacheServlet");
    }

}
