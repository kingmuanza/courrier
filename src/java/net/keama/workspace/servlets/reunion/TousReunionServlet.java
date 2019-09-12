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
import net.keama.workspace.dao.ReunionDAO;
import net.keama.workspace.entity.Courrier;
import net.keama.workspace.entity.Employe;
import net.keama.workspace.entity.Reunion;
import net.keama.workspace.service.EmployeService;

@WebServlet(name="TousReunionServlet", urlPatterns={"/TousReunionServlet"})
public class TousReunionServlet extends HttpServlet {

    ReunionDAO reunionDAO = new ReunionDAO();
    EmployeService employeService = new EmployeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Reunion> reunions = reunionDAO.getall();
        List<Reunion> reunionsEntrants = new ArrayList<Reunion>();
        if (reunions != null && !reunions.isEmpty()) {
            for (Reunion c : reunions) {
                if(c.getStatut()!=null && c.getStatut().equals("Entrant")){
                    reunionsEntrants.add(c);
                }
            }
        }
        List<Employe> employes = employeService.getAll();

        request.setAttribute("attribut", "attribut");
        request.setAttribute("reunions", reunions);
        request.setAttribute("employes", employes);
        String id = request.getParameter("id");
        if(id!=null && !id.isEmpty()){
            if(!reunions.isEmpty()){
                this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/reunion/tous.jsp").forward(request, response);
            }else{
                this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/reunion/tous.jsp").forward(request, response);
            }
        }else{
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/reunion/tous.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Reunion arrivé ");
        String action = request.getParameter("action");
        System.out.println("Action : " + action);
        if (action != null && !action.isEmpty() && action.equals("terminer")) {
            String id = request.getParameter("id");
            System.out.println("ID : " + id);
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                Reunion reunion = reunionDAO.get(i);
                reunion.setStatut("Prêt");
                if (reunionDAO.modifier(reunion)) {
                    System.out.println("Reunion prêt : " + reunion.getRef());
                    response.sendRedirect("reunion#/tous");
                }
            }
        }
    }

}
