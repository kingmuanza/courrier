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
import java.util.List;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.dao.TacheDAO;
import net.keama.workspace.dao.TacheEmployeDAO;
import net.keama.workspace.dao.UtilisateurDAO;
import net.keama.workspace.entity.Employe;
import net.keama.workspace.entity.Tache;
import net.keama.workspace.entity.TacheEmploye;
import net.keama.workspace.entity.Utilisateur;
import net.keama.workspace.service.EmployeService;

@WebServlet(name="AVenirTacheServlet", urlPatterns={"/AVenirTacheServlet"})
public class AVenirTacheServlet extends HttpServlet {

    TacheDAO tacheDAO = new TacheDAO();
    TacheEmployeDAO tacheEmployeDAO = new TacheEmployeDAO();
    EmployeService employeService = new EmployeService();
    UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession httpSession = request.getSession();
        Utilisateur u = null;
        Utilisateur utilisateur = null;
        u = (Utilisateur) httpSession.getAttribute("utilisateur");
        utilisateur = utilisateurDAO.get(u.getIdutilisateur());

        List<Tache> taches = tacheDAO.getall();
        List<TacheEmploye> tacheEmployes = tacheEmployeDAO.getall();
        List<Tache> mes_taches = new ArrayList<Tache>();
        List<Employe> employes = employeService.getAll();
        List<Integer> ids = new ArrayList<Integer>() ;
        
        for(TacheEmploye te : tacheEmployes){
            if(te.getEmploye().getIdemploye()==utilisateur.getEmploye().getIdemploye()){
                //mes_taches.add(te.getTache());
                ids.add(te.getTache().getIdtache());
                
            }
        }
        for(int i : ids){
            mes_taches.add(tacheDAO.get(i));
        }
        String idtache = request.getParameter("idtache");
        if(idtache!=null && !idtache.isEmpty()){
            int i = Integer.parseInt(idtache);
            Tache t = tacheDAO.get(i);
            mes_taches = new ArrayList<Tache>();
            mes_taches.add(t);
        }
        request.setAttribute("attribut", "attribut");
        request.setAttribute("taches", mes_taches);
        request.setAttribute("employes", employes);
        
        String id = request.getParameter("id");
        
        if (id != null && !id.isEmpty()) {
            if (!taches.isEmpty()) {
                this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/tache/avenir_light.jsp").forward(request, response);
            } else {
                this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/tache/avenir.jsp").forward(request, response);
            }
        } else {
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/tache/avenir.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession httpSession = request.getSession();
        Utilisateur u = null;
        Utilisateur utilisateur = null;
        u = (Utilisateur) httpSession.getAttribute("utilisateur");
        utilisateur = utilisateurDAO.get(u.getIdutilisateur());

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
                tache.setStatut("Terminé");
                tache.setDateTerminee(new Date());
                tache.setEmployeByIdemploye(utilisateur.getEmploye());
                if (tacheDAO.modifier(tache)) {
                    //System.out.println("Tache prêt : " + tache.getRef());
                    response.sendRedirect("tache#/avenir/light/"+tache.getIdtache());
                }
            }
        }
    }


}
