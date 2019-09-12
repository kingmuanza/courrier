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
import java.util.Objects;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.dao.ProcedureEffectiveDAO;
import net.keama.workspace.dao.ProceduresDAO;
import net.keama.workspace.dao.TacheProcedureEffectueDAO;
import net.keama.workspace.entity.ProcedureEffective;
import net.keama.workspace.entity.Procedures;
import net.keama.workspace.entity.TacheProcedureEffectue;
import net.keama.workspace.entity.Utilisateur;

@WebServlet(name = "TacheProcedureServlet", urlPatterns = {"/TacheProcedureServlet"})
public class TacheProcedureServlet extends HttpServlet {
    
    TacheProcedureEffectueDAO tacheProcedureEffectueDAO = new TacheProcedureEffectueDAO();
    ProcedureEffectiveDAO procedureEffectiveDAO = new ProcedureEffectiveDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = null;

        utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            int identity = Integer.parseInt(id);
            TacheProcedureEffectue tacheProcedureEffectue = tacheProcedureEffectueDAO.get(identity);
            ProcedureEffective procedure = procedureEffectiveDAO.get(tacheProcedureEffectue.getProcedureEffective().getIdprocedureEffective());
            request.setAttribute("tache", tacheProcedureEffectue);
            request.setAttribute("procedure", procedure);
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/tache/tache_procedure_id.jsp").forward(request, response);
        } else {
            List<TacheProcedureEffectue> taches = tacheProcedureEffectueDAO.getall();
            List<TacheProcedureEffectue> mes_taches = new ArrayList<TacheProcedureEffectue>();
            for(TacheProcedureEffectue tpe : taches){
                if(Objects.equals(tpe.getTacheProcedure().getEmploye().getIdemploye(), utilisateur.getEmploye().getIdemploye())){
                    mes_taches.add(tpe);
                }
            }
            request.setAttribute("taches", mes_taches);
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/tache/tache_procedure.jsp").forward(request, response);

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

        response.sendRedirect("TacheProcedureServlet");
    }

}
