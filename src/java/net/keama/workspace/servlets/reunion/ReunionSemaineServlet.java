package net.keama.workspace.servlets.reunion;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.dao.ReunionDAO;
import net.keama.workspace.dao.UtilisateurDAO;
import net.keama.workspace.entity.Reunion;
import net.keama.workspace.entity.ReunionEmploye;
import net.keama.workspace.entity.Utilisateur;

@WebServlet(name = "ReunionSemaineServlet", urlPatterns = {"/ReunionSemaineServlet"})
public class ReunionSemaineServlet extends HttpServlet {

    ReunionDAO reunionDAO = new ReunionDAO();
    UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = null;

        utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        int idemploye = utilisateur.getEmploye().getIdemploye();
        System.out.println("ID Employé : "+idemploye);
        
        Date oday = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(oday);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        System.out.println("Jour : "+cal.getTime());
        
        Date lundi = cal.getTime();
        System.out.println("Lundi : "+lundi);
        List<Reunion> reunionLundi = reunionDAO.getall(lundi, idemploye);
        System.out.println("Reunions du Lundi : "+reunionLundi.size());
        request.setAttribute("reunionLundi", reunionLundi);
        
        cal.add(Calendar.DATE, 1);
        Date mardi = cal.getTime();
        System.out.println("Mardi : "+mardi);
        List<Reunion> reunionMardi = reunionDAO.getall(mardi, idemploye);
        System.out.println("Reunions du Mardi : "+reunionMardi.size());
        request.setAttribute("reunionMardi", reunionMardi);
        
        cal.add(Calendar.DATE, 1);
        Date mercredi = cal.getTime();
        System.out.println("Mercredi : "+mercredi);
        List<Reunion> reunionMercredi = reunionDAO.getall(mercredi, idemploye);
        System.out.println("Reunions du Mercredi : "+reunionMercredi.size());
        request.setAttribute("reunionMercredi", reunionMercredi);
        
        cal.add(Calendar.DATE, 1);
        Date jeudi = cal.getTime();
        System.out.println("Jeudi : "+jeudi);
        List<Reunion> reunionJeudi = reunionDAO.getall(jeudi, idemploye);
        System.out.println("Reunions du Jeudi : "+reunionJeudi.size());
        request.setAttribute("reunionJeudi", reunionJeudi);
        
        cal.add(Calendar.DATE, 1);
        Date vendredi = cal.getTime();
        System.out.println("Vendredi : "+vendredi);
        List<Reunion> reunionVendredi = reunionDAO.getall(vendredi, idemploye);
        System.out.println("Reunions du Vendredi : "+reunionVendredi.size());
        request.setAttribute("reunionVendredi", reunionVendredi);
        
        cal.add(Calendar.DATE, 1);
        Date samedi = cal.getTime();
        System.out.println("Samedi : "+samedi);
        List<Reunion> reunionSamedi = reunionDAO.getall(samedi, idemploye);
        System.out.println("Reunions du Samedi : "+reunionSamedi.size());
        request.setAttribute("reunionSamedi", reunionSamedi);
        
        cal.add(Calendar.DATE, 1);
        Date dimanche = cal.getTime();
        request.setAttribute("lundi", lundi);
        request.setAttribute("dimanche", dimanche);
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/reunion/semaine.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
