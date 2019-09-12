package net.keama.workspace.servlets.reunion;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.dao.CourrierDAO;
import net.keama.workspace.entity.Courrier;
import net.keama.workspace.entity.Employe;
import net.keama.workspace.service.EmployeService;

@WebServlet(name="NotificationReunionServlet", urlPatterns={"/NotificationReunionServlet"})
public class NotificationReunionServlet extends HttpServlet {

    CourrierDAO courrierDAO = new CourrierDAO();
    EmployeService employeService = new EmployeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Courrier> courriers = courrierDAO.getall();
        List<Courrier> courriersEntrants = new ArrayList<Courrier>();
        if (courriers != null && !courriers.isEmpty()) {
            for (Courrier c : courriers) {
                if(c.getStatut()!=null && c.getStatut().equals("Entrant")){
                    courriersEntrants.add(c);
                }
            }
        }
        List<Employe> employes = employeService.getAll();

        request.setAttribute("attribut", "attribut");
        request.setAttribute("courriers", courriersEntrants);
        request.setAttribute("employes", employes);

        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/reunion/notifications.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Courrier arrivé ");
        String action = request.getParameter("action");
        System.out.println("Action : " + action);
        if (action != null && !action.isEmpty() && action.equals("terminer")) {
            String id = request.getParameter("id");
            System.out.println("ID : " + id);
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                Courrier courrier = courrierDAO.get(i);
                courrier.setStatut("Prêt");
                if (courrierDAO.modifier(courrier)) {
                    System.out.println("Courrier prêt : " + courrier.getRef());
                    response.sendRedirect("courrier#/tous");
                }
            }
        }
    }

}
