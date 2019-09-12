package net.keama.workspace.servlets.courrier;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.dao.CourrierDAO;
import net.keama.workspace.dao.CourrierResponsableDAO;
import net.keama.workspace.entity.Courrier;
import net.keama.workspace.entity.CourrierResponsable;
import net.keama.workspace.entity.Employe;
import net.keama.workspace.service.EmployeService;

@WebServlet(name="TransmisCourrierServlet", urlPatterns={"/TransmisCourrierServlet"})
public class TransmisCourrierServlet extends HttpServlet {

    CourrierDAO courrierDAO = new CourrierDAO();
    CourrierResponsableDAO courrierResponsableDAO = new CourrierResponsableDAO();
    EmployeService employeService = new EmployeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Courrier> courriers = courrierDAO.getall();
        List<CourrierResponsable> courrierResponsables = courrierResponsableDAO.getall();
        List<Courrier> courriersTransmis = new ArrayList<Courrier>();
        if (courriers != null && !courriers.isEmpty()) {
            for (Courrier c : courriers) {
                if(c.getStatut()!=null && c.getStatut().equals("Transmis")){
                    courriersTransmis.add(c);
                }
            }
        }
        
        List<Employe> employes = employeService.getAll();
        System.out.println("courrierResponsables : " + courrierResponsables.size());
        request.setAttribute("attribut", "attribut");
        request.setAttribute("courriers", courriersTransmis);
        request.setAttribute("courrierResponsables", courrierResponsables);
        request.setAttribute("employes", employes);

        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/courrier/transmis.jsp").forward(request, response);
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
