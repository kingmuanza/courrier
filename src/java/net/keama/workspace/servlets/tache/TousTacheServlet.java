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
import net.keama.workspace.dao.TacheDAO;
import net.keama.workspace.entity.Tache;
import net.keama.workspace.entity.Employe;
import net.keama.workspace.service.EmployeService;

@WebServlet(name="TousTacheServlet", urlPatterns={"/TousTacheServlet"})
public class TousTacheServlet extends HttpServlet {

    TacheDAO tacheDAO = new TacheDAO();
    EmployeService employeService = new EmployeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Tache> taches = tacheDAO.getall();
        List<Employe> employes = employeService.getAll();

        request.setAttribute("attribut", "attribut");
        request.setAttribute("taches", taches);
        request.setAttribute("employes", employes);

        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/tache/tous.jsp").forward(request, response);
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
