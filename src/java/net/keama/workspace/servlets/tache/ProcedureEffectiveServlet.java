package net.keama.workspace.servlets.tache;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import static java.lang.Math.random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.dao.ProcedureEffectiveDAO;
import net.keama.workspace.dao.ProceduresDAO;
import net.keama.workspace.dao.TacheProcedureEffectueDAO;
import net.keama.workspace.entity.ProcedureEffective;
import net.keama.workspace.entity.Procedures;
import net.keama.workspace.entity.TacheProcedure;
import net.keama.workspace.entity.TacheProcedureEffectue;

@WebServlet(name = "ProcedureEffectiveServlet", urlPatterns = {"/ProcedureEffectiveServlet"})
public class ProcedureEffectiveServlet extends HttpServlet {

    ProceduresDAO proceduresDAO = new ProceduresDAO();
    ProcedureEffectiveDAO procedureEffectiveDAO = new ProcedureEffectiveDAO();
    TacheProcedureEffectueDAO tacheProcedureEffectueDAO = new TacheProcedureEffectueDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession httpSession = request.getSession();

        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            int identity = Integer.parseInt(id);
            ProcedureEffective procedureEffective = procedureEffectiveDAO.get(identity);
            request.setAttribute("procedure", procedureEffective);
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/tache/procedures_encours_id.jsp").forward(request, response);

        } else {
            List<ProcedureEffective> procedures = procedureEffectiveDAO.getall();
            request.setAttribute("procedures", procedures);
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/tache/procedures_encours.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String action = request.getParameter("action");
        if (action != null && !action.isEmpty() && id != null && !id.isEmpty()) {
            int i = Integer.parseInt(id);
            Procedures procedure = proceduresDAO.get(i);
            ProcedureEffective procedureEffective = new ProcedureEffective();
            procedureEffective.setProcedures(procedure);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Random random = new Random();
            String ref = sdf.format(date).replace("-", "") + random.nextInt(1000);
            procedureEffective.setRef(ref);
            procedureEffective.setDateDebut(date);
            procedureEffective.setStatut("Démarré");
            if (procedureEffectiveDAO.ajouter(procedureEffective)) {
                for (TacheProcedure tacheProcedure : procedure.getTacheProcedures()) {
                    TacheProcedureEffectue tacheProcedureEffectue = new TacheProcedureEffectue();
                    tacheProcedureEffectue.setDateDebut(date);
                    Date datefin = new Date(date.getTime() + (tacheProcedure.getDureeEnJour() * 1000 * 60 * 60 * 24));
                    tacheProcedureEffectue.setDateFin(datefin);
                    tacheProcedureEffectue.setProcedureEffective(procedureEffective);
                    tacheProcedureEffectue.setStatut("En cours");
                    tacheProcedureEffectue.setTacheProcedure(tacheProcedure);
                    tacheProcedureEffectueDAO.ajouter(tacheProcedureEffectue);
                }
            }

        }
        response.sendRedirect("tache#/procedures");
    }

}
