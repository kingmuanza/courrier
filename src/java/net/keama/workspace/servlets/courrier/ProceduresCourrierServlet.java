package net.keama.workspace.servlets.courrier;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.dao.CourrierProcedureDAO;
import net.keama.workspace.entity.CourrierProcedure;

@WebServlet(name = "ProceduresCourrierServlet", urlPatterns = {"/ProceduresCourrierServlet"})
public class ProceduresCourrierServlet extends HttpServlet {

    CourrierProcedureDAO courrierProcedureDAO = new CourrierProcedureDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession httpSession = request.getSession();

        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            int i = Integer.parseInt(id);
            CourrierProcedure procedure = courrierProcedureDAO.get(i);
            request.setAttribute("procedure", procedure);
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/courrier/procedures_id.jsp").forward(request, response);
        } else {
            List<CourrierProcedure> procedures = courrierProcedureDAO.getall();
            request.setAttribute("procedures", procedures);
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/courrier/procedures.jsp").forward(request, response);
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

        response.sendRedirect("ProceduresCourrierServlet");
    }

}
