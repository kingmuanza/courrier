package net.keama.workspace.servlets.tache;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.dao.ProceduresDAO;
import net.keama.workspace.entity.Procedures;

@WebServlet(name = "ProcedureServlet", urlPatterns = {"/ProcedureServlet"})
public class ProcedureServlet extends HttpServlet {

    ProceduresDAO proceduresDAO = new ProceduresDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession httpSession = request.getSession();

        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            int i = Integer.parseInt(id);
            Procedures procedure = proceduresDAO.get(i);
            request.setAttribute("procedure", procedure);
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/tache/procedures_id.jsp").forward(request, response);
        } else {
            List<Procedures> procedures = proceduresDAO.getall();
            request.setAttribute("procedures", procedures);
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/tache/procedures.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        
        response.sendRedirect("ProcedureServlet");
    }

}
