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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.dao.TacheDAO;
import net.keama.workspace.entity.Employe;
import net.keama.workspace.entity.Tache;
import net.keama.workspace.entity.TacheEmploye;
import net.keama.workspace.service.EmployeService;

@WebServlet(name = "EvaluationTacheServlet", urlPatterns = {"/EvaluationTacheServlet"})
public class EvaluationTacheServlet extends HttpServlet {

    TacheDAO tacheDAO = new TacheDAO();
    EmployeService employeService = new EmployeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Tache> taches = tacheDAO.getall();
        Set<Tache> tachesEntrants = new HashSet<Tache>();
        if (taches != null && !taches.isEmpty()) {
            for (Tache c : taches) {
                if (c.getStatut() != null && c.getStatut().equals("Entrant")) {
                    tachesEntrants.add(c);
                }
            }
        }
        List<Employe> employes = employeService.getAll();
        List<Employe> employes_finaux = new ArrayList<Employe>();
        for (Employe e : employes) {
            for (Tache t : taches) {
                if (t.getDateDebut().before(new Date()) && t.getDateFin().after(new Date())) {
                    if (t.getTacheEmployes() != null) {
                        for (TacheEmploye te : t.getTacheEmployes()) {
                            if (te.getEmploye().getIdemploye() == e.getIdemploye()) {
                                tachesEntrants.add(te.getTache());
                            }
                        }
                    }
                }
            }
            e.setTachesForIdemploye(null);
            e.setTachesForIdemploye(tachesEntrants);
            employes_finaux.add(e);
            tachesEntrants = new HashSet<Tache>();
        }

        request.setAttribute("taches", taches);
        request.setAttribute("employes", employes_finaux);

        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/tache/evaluation.jsp").forward(request, response);
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

        response.sendRedirect("EvaluationTacheServlet");
    }

}
