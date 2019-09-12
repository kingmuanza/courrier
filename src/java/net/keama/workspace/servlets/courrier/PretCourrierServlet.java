package net.keama.workspace.servlets.courrier;

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
import net.keama.workspace.dao.CourrierDAO;
import net.keama.workspace.dao.CourrierResponsableDAO;
import net.keama.workspace.entity.Courrier;
import net.keama.workspace.entity.CourrierResponsable;
import net.keama.workspace.entity.Employe;
import net.keama.workspace.service.EmployeService;

@WebServlet(name = "PretCourrierServlet", urlPatterns = {"/PretCourrierServlet"})
public class PretCourrierServlet extends HttpServlet {

    CourrierDAO courrierDAO = new CourrierDAO();
    CourrierResponsableDAO courrierResponsableDAO = new CourrierResponsableDAO();
    EmployeService employeService = new EmployeService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession httpSession = request.getSession();
        List<Courrier> courriers = courrierDAO.getall();
        List<Courrier> courriersPrets = new ArrayList<Courrier>();
        if (courriers != null && !courriers.isEmpty()) {
            for (Courrier c : courriers) {
                if(c.getStatut()!=null && c.getStatut().equals("Prêt")){
                    courriersPrets.add(c);
                }
            }
        }
        List<Employe> employes = employeService.getAll();

        request.setAttribute("attribut", "attribut");
        request.setAttribute("courriers", courriersPrets);
        request.setAttribute("employes", employes);
        
        //Récupération des notification
        if (httpSession.getAttribute("creation") != null) {
            request.setAttribute("creation", (boolean) httpSession.getAttribute("creation"));
            httpSession.removeAttribute("creation");
        }
        if (httpSession.getAttribute("modification") != null) {
            request.setAttribute("modification", (boolean) httpSession.getAttribute("modification"));
            httpSession.removeAttribute("modification");
        }
        if (httpSession.getAttribute("suppression") != null) {
            request.setAttribute("suppression", (boolean) httpSession.getAttribute("suppression"));
            httpSession.removeAttribute("suppression");
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/courrier/pret.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Transmission arrivée ");
        String idcourrier = request.getParameter("courrier");
        System.out.println("Courrier : "+idcourrier);
        if(idcourrier!=null && !idcourrier.isEmpty()){
            int i = Integer.parseInt(idcourrier);
            Courrier courrier = courrierDAO.get(i);
            String idemploye = request.getParameter("employe");
            System.out.println("Employe : "+idemploye);
            if(idemploye!=null && !idemploye.isEmpty()){
                int i1 = Integer.parseInt(idemploye);
                Employe employe = employeService.get(i1);
                CourrierResponsable courrierResponsable = new CourrierResponsable();
                courrierResponsable.setCourrier(courrier);
                courrierResponsable.setEmploye(employe);
                courrierResponsable.setStatut("En cours");
                courrierResponsable.setTypeApprobation("Cotation");
                courrierResponsable.setDateRecuperation(new Date());
                if(courrierResponsableDAO.ajouter(courrierResponsable)){
                    System.out.println("Sauvegarde réussie !!! ");
                    courrier.setStatut("Transmis");
                    if(courrierDAO.modifier(courrier)){
                        response.sendRedirect("courrier#/transmis/");
                    }
                    
                }
            }
        }
        
    }
}
